# MotorPH IAS2 - PII Encryption at Rest Team Setup

This guide sets up the PII Encryption at Rest control for a local development copy of MotorPH.

## Important safety rules

- Do not commit `db.properties` or database passwords.
- Do not commit AES/HMAC keys.
- Each team member should use their own local keys.
- Do not run the migration tool more than once on the same database.
- Do not run the application after Step 2 until Step 5 has completed successfully.

## 1. Create a separate local IAS2 database

The original `database/payrollsystem_db.sql` hardcodes `payrollsystem_db` in its `CREATE DATABASE` and `USE` statements. Do not import that file directly when creating the IAS2 copy.

1. Make a temporary copy of `database/payrollsystem_db.sql` outside the repository.
2. In the temporary copy only, change the database name in the `CREATE DATABASE` statement from `payrollsystem_db` to `payrollsystem_db_ias2`.
3. Change the `USE payrollsystem_db;` statement in that temporary copy to `USE payrollsystem_db_ias2;`.
4. Import the edited temporary copy using MySQL Workbench or your local MySQL client.
5. Update your local `src/config/db.properties` so it points to `payrollsystem_db_ias2`. Do not commit that file.

If the application uses a limited local database account such as `motorph_app`, give that account the same application privileges on `payrollsystem_db_ias2` that it has on the original local database. Do not grant unnecessary administrative privileges.
## 2. Prepare the schema

Run:

```text
database/ias2/01_pii_schema_prepare.sql
```

This expands the four government-ID columns, adds four `BINARY(32)` HMAC lookup columns, and removes the old plaintext-format CHECK constraints.

## 3. Generate your own local keys

From PowerShell, use cryptographically random 32-byte keys for:

- `MOTORPH_PII_AES_KEY`
- `MOTORPH_PII_HMAC_KEY`

The application expects Base64-encoded 32-byte keys. Store them as User environment variables and never put them in source code, SQL, screenshots, or chat.

Create them without printing the key values:

```powershell
$aes = New-Object byte[] 32
$rng = [Security.Cryptography.RandomNumberGenerator]::Create()
$rng.GetBytes($aes)
[Environment]::SetEnvironmentVariable('MOTORPH_PII_AES_KEY', [Convert]::ToBase64String($aes), 'User')
[Array]::Clear($aes, 0, $aes.Length)

$hmac = New-Object byte[] 32
$rng.GetBytes($hmac)
[Environment]::SetEnvironmentVariable('MOTORPH_PII_HMAC_KEY', [Convert]::ToBase64String($hmac), 'User')
[Array]::Clear($hmac, 0, $hmac.Length)
$rng.Dispose()
```

Re-open the terminal after creating the user environment variables so new processes can read them.

## 4. Compile

Run:

```text
"C:\Program Files\NetBeans-22\netbeans\extide\ant\bin\ant.bat" compile
```

Expected: `BUILD SUCCESSFUL`.

## 5. Migrate the existing plaintext data

Run the reusable migration tool:

```text
"C:\Program Files\NetBeans-22\netbeans\extide\ant\bin\ant.bat" -Dmain.class=util.PiiDataMigrationTool run
```

Expected output includes:

```text
PASS: Preflight complete. Rows ready=<count>
PASS: Stored values verified. Rows=<count>
PASS: Migration committed. Rows migrated=<count>
```

If the tool reports `FAIL`, do not run it again until the database state has been checked.

## 6. Finalize the schema

Only after the migration succeeds, run:

```text
database/ias2/03_pii_schema_finalize.sql
```

This:

- makes the four HMAC lookup columns `NOT NULL`
- moves uniqueness enforcement to the HMAC lookup columns
- adds `v1:%` ciphertext guardrails

## 7. Run the application

Run the application normally and verify that authorized Employee/HR screens show readable government IDs while raw database values remain encrypted.

## Implementation details

- AES-256-GCM
- 12-byte random nonce
- 128-bit GCM tag
- `v1:` versioned Base64 envelope
- field-specific AAD context
- HMAC-SHA-256 lookup values
- separate AES and HMAC keys
- keys stored outside the repository

## Files

- `src/util/PiiCryptoUtil.java`
- `src/util/PiiDataMigrationTool.java`
- `database/ias2/01_pii_schema_prepare.sql`
- `database/ias2/03_pii_schema_finalize.sql`

-----------------------------------------------------

# Authentication Hardening Team Setup

# Phase 1: First-Login Reset Enforcement

This phase enables required password changes for new accounts and accounts whose passwords are reset by an IT Admin.

Prerequisite: complete the PII Encryption at Rest setup above and use the local `payrollsystem_db_ias2` database. Apply this migration before testing the authentication flow.

## 1. Update the schema

Run this script once against `payrollsystem_db_ias2`:

```text
database/ias2/04_auth_schema_must-change-password.sql
```

The migration adds `mustChangePassword` to `UserAccount`. Existing rows keep the migration's initial value of `FALSE`; new account rows default to `TRUE`. To require a password change for an existing account, an IT Admin must use **Reset Password** for that account. Do not rerun this script; the column already exists after a successful run.

## 2. Compile

From the repository root, run:

```text
ant compile
```

Expected result: `BUILD SUCCESSFUL`.

## 3. Verify forced password reset

1. Sign in as an IT Admin and open an account record.
2. Select **Reset Password**, confirm, and copy the temporary password from the dialog. The same password cannot be retrieved after the dialog closes; another reset generates a replacement.
3. Sign in as that account with the temporary password. The application should require a password change before opening the role portal.
4. Enter and confirm a new password. The application returns to the login screen after the update.
5. Sign in with the new password. The account should now proceed to its role portal without another forced reset.
6. Confirm the reset event appears in the audit log. The `mustChangePassword` flag should be `FALSE` after the new password is accepted.

## Implementation details

- Temporary passwords use Java `SecureRandom` to generate 12 random bytes, encoded as URL-safe Base64 without padding. They are shown to the IT Admin once for delivery. Password resets for this after login is now enforced.
- At login, `mustChangePassword` determines whether the user must choose a new password before entering the role portal. A successful change clears the flag and returns the user to the login screen.
- The old dashboard check that hashes and compares the literal `temppassword` is currently commented out. Required changes are enforced by the login flag instead.
- Password resets are recorded in the audit log as `FORCED_PASSWORD_RESET`.

## Key files

- `database/ias2/04_auth_schema_must-change-password.sql`
- `src/service/AccountService.java`
- `src/service/AuthenticationService.java`
- `src/model/dao/UserAccountDAO.java`
- `src/gui/admin/it/ViewAccountPanel.java`
- `src/gui/login/LoginPanel.java`
- `src/gui/login/EnforcedPasswordResetPanel.java`
- `src/gui/employee/EmployeeDashboardPanel.java`
- `test/test/AuthenticationServiceTest.java`