# MotorPH IAS2 - Team Setup Guides

-------------------------------------------------------------

## A. PII Encryption at Rest Team Setup
This guide sets up the PII Encryption at Rest control for a local development copy of MotorPH.

### Important safety rules

- Do not commit `db.properties` or database passwords.
- Do not commit AES/HMAC keys.
- Each team member should use their own local keys.
- Do not run the migration tool more than once on the same database.
- Do not run the application after Step 2 until Step 5 has completed successfully.

### 1. Create a separate local IAS2 database

The original `database/payrollsystem_db.sql` hardcodes `payrollsystem_db` in its `CREATE DATABASE` and `USE` statements. Do not import that file directly when creating the IAS2 copy.

1. Make a temporary copy of `database/payrollsystem_db.sql` outside the repository.
2. In the temporary copy only, change the database name in the `CREATE DATABASE` statement from `payrollsystem_db` to `payrollsystem_db_ias2`.
3. Change the `USE payrollsystem_db;` statement in that temporary copy to `USE payrollsystem_db_ias2;`.
4. Import the edited temporary copy using MySQL Workbench or your local MySQL client.
5. Update your local `src/config/db.properties` so it points to `payrollsystem_db_ias2`. Do not commit that file.

If the application uses a limited local database account such as `motorph_app`, give that account the same application privileges on `payrollsystem_db_ias2` that it has on the original local database. Do not grant unnecessary administrative privileges.
### 2. Prepare the schema

Run:

```text
database/ias2/01_pii_schema_prepare.sql
```

This expands the four government-ID columns, adds four `BINARY(32)` HMAC lookup columns, and removes the old plaintext-format CHECK constraints.

### 3. Generate your own local keys

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

### 4. Compile

Run:

```text
"C:\Program Files\NetBeans-22\netbeans\extide\ant\bin\ant.bat" compile
```

Expected: `BUILD SUCCESSFUL`.

### 5. Migrate the existing plaintext data

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

### 6. Finalize the schema

Only after the migration succeeds, run:

```text
database/ias2/03_pii_schema_finalize.sql
```

This:

- makes the four HMAC lookup columns `NOT NULL`
- moves uniqueness enforcement to the HMAC lookup columns
- adds `v1:%` ciphertext guardrails

### 7. Run the application

Run the application normally and verify that authorized Employee/HR screens show readable government IDs while raw database values remain encrypted.

### Implementation details

- AES-256-GCM
- 12-byte random nonce
- 128-bit GCM tag
- `v1:` versioned Base64 envelope
- field-specific AAD context
- HMAC-SHA-256 lookup values
- separate AES and HMAC keys
- keys stored outside the repository

### Files

- `src/util/PiiCryptoUtil.java`
- `src/util/PiiDataMigrationTool.java`
- `database/ias2/01_pii_schema_prepare.sql`
- `database/ias2/03_pii_schema_finalize.sql`

-----------------------------------------------------

## B. Authentication Hardening Team Setup
This controls consists of four phases:
- **Phase 1:** First-Login Reset Enforcement
- **Phase 2:** Salted Adaptive Password Hashing & Policy Hardening
- **Phase 3:** Email OTP Recovery Replacement
- **Phase 4:** TOTP-Based MFA Enforcement

### Phase 1: First-Login Reset Enforcement

This phase enables required password changes for new accounts and accounts whose passwords are reset by an IT Admin.

**Prerequisite:** complete the PII Encryption at Rest setup above and use the local `payrollsystem_db_ias2` database.

### 1. Update the schema

Run this script once against `payrollsystem_db_ias2`:

```text
database/ias2/04_auth_phase1_mustChangePassword.sql
```

**Expected Result:**
The migration adds `mustChangePassword` to `UserAccount`.
Existing rows keep the migration's initial value of FALSE/`0`; new account rows default to TRUE/`1`. 

**Note:** To require a password change for an existing account, an IT Admin must use **Reset Password** for that account.
**Important:** Do not test the authentication flow until the Authentication **Phase 2 migration** outlined below is done.

### Implementation details

- At login, `mustChangePassword` determines whether the user must choose a new password before entering the role portal. A successful change clears the flag and returns the user to the login screen.
- Temporary passwords use Java `SecureRandom` to generate 12 random bytes, encoded as URL-safe Base64 without padding. They are shown to the IT Admin **once** for delivery. After logging in with a temporary credential, an immediate password reset is enforced.
- The old dashboard check that hashes and compares the literal `temppassword` is currently commented out. Required changes are enforced by the login flag instead.

### Key files
- `database/ias2/04_auth_phase1_mustChangePassword.sql`
- `src/gui/login/EnforcedPasswordResetPanel.java`

----------------------------------------------------------------------

## Phase 2: Salted Adaptive Password Hashing & Policy Hardening

This phase transitions credential storage from legacy single-round SHA-256 to salted, adaptive PBKDF2-HMAC-SHA256 hashing and enforces modern password validation rules.

**Prerequisite:** complete the Phase 1 setup above and use the local `payrollsystem_db_ias2` database.

### 1. Update the schema

Run this script once against `payrollsystem_db_ias2`:

```text
database/ias2/05_auth_phase2_passwordSalt.sql
```

This script adds a nullable `passwordSalt VARCHAR(64)` column to the `useraccount` table to store user-specific random salts.
It marks legacy SHA-256 credentials with `mustChangePassword = TRUE` to force a re-hash upon next login.

**Expected result:** the `UserAccount` table gains a new nullable `passwordSalt` column

### 1. Secure the audit log

Run this script once against `payrollsystem_db_ias2`:

```text
database/ias2/06_auth_phase2_password_audit-redact.sql
```

This script overwrites existing password hashes in audit logs with `[REDACTED]` for secure audits logging.

**Expected result:** the password audits display `[REDACTED]` as the values; hashes never logged.

### Implementation details

- Uses **PBKDF2-HMAC-SHA256** with **600,000 iterations** (in compliance with OWASP guidelines), a **256-bit derived key**, and a **16-byte random salt** (`SecureRandom`).
- Keeps **backward compatibility** for legacy SHA-256 credentials until they are reset and rehashed.
- Follows **NIST SP 800-63B-4, 2025** guidance for password handling:
-- Enforces a minimum length of 8 characters (MFA is planned later).
-- No password complexity rules
-- Checks against list of common/breached passwords.
- Validates passwords against the **Have I Been Pwned (HIBP)** dataset before allowing password changes.

> **HIBP integration:** The client hashes the candidate password using SHA-1 locally. Only the **first 5 characters** (prefix) of the hash are transmitted to the HIBP range API (`/range/{prefix}`). The remaining 35-character suffix is matched locally against the response list, ensuring the actual password or full hash is never exposed over the network. This is called the **k-Anonymity** model.


### Key files
- `database/ias2/05_auth_phase2_passwordSalt.sql`
- `database/ias2/06_auth_phase2_password_audit-redact.sql`
- `src/util/PasswordCryptoUtil.java`
- `src/util/PasswordBreachChecker.java`
- `src/util/PasswordUtil.java`

### Verify Login Flow (Phase 1 & 2)
#### Test Password Reset Enforcement
1. Sign in as an IT Admin and open an account record.
2. Select **Reset Password**, confirm, and copy the temporary password from the dialog. The same password cannot be retrieved after the dialog closes; another reset generates a replacement.
3. Sign in as that account with the temporary password. The application should require a password change before opening the role portal.
4. Enter and confirm a new password. The application returns to the login screen after the update.
5. Sign in with the new password. The account should now proceed to its role portal without another forced reset.
6. Confirm the reset event appears in the audit log. The `mustChangePassword` flag should be FALSE/`0` after the new password is accepted.

#### Test Password Policy Enforcement
1. Sign in on any account.
2. Navigate to the Change Password page in the Employee Portal.
3. Submitting a password with < 8 characters triggers length error.
4. Submitting a common password (e.g., `"password"`) triggers HIBP breach error.
5. Submitting a unique password (8+ chars, unbreached) updates successfully.

### Phase 3: Email OTP Recovery

#### Update schema
Run these scripts once against `payrollsystem_db_ias2` after the Phase 2 migrations:

```text
database/ias2/07_auth_phase3_email-otp_table.sql
database/ias2/08_auth_phase3_audit_nullable_user.sql
```

#### Set up SMTP
- Configure a local SMTP server or use a free Gmail account for testing. Copy `src/config/smtp.properties.example` into `src/config/smtp.properties` and update it with the SMTP host, port, username, and password. Do not commit this file.
- Update user accounts with valid email addresses for testing. The application will send OTPs to those addresses. Email addresses can be updated by the IT Admin in the Accounts page.
> Tip: You can use aliases with Gmail for testing. For example, if your Gmail address is `email@example.com`, you can use `email+aliashere@example.com` to receive emails in the same inbox.

### Implementation Details
- The OTP migration stores only OTP and recovery-token hashes. 
- OTPs expire after 10 minutes, are consumed on successful verification, has limited verification attempts, and are limited to ten requests per account per hour (abundant limit for testing). 
- Recovery tokens expire. 
- Recovery requests, verification results, and reset results are written to `AuditLog`; event values never include email addresses, OTPs, recovery tokens, or passwords. 
- The audit migration allows attempts for unknown email addresses to be recorded with a `NULL` user ID.

### Key Files
- `src/util/OtpUtil.java`
- `src/service/PasswordRecoveryService.java`
- `src/service/EmailService.java`
- `src/model/dao/EmailOtpDAO.java`
- `src/gui/login/OtpVerificationDialog.java`
- `src/config/smtp.properties.example`
