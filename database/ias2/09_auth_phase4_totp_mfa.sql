-- Phase 4: TOTP-Based MFA Enforcement

-- Script 1: Add mfaEnabled flag to useraccount table. Every account starts unenrolled and
-- is required to enroll an authenticator app at its next login.
ALTER TABLE useraccount
ADD COLUMN mfaEnabled BOOLEAN NOT NULL DEFAULT FALSE AFTER mustChangePassword;

-- Script 2: Separate table for the encrypted TOTP secret, enrollment timestamp,
-- and replay-prevention data (last accepted 30-second time step).
CREATE TABLE IF NOT EXISTS usertotp (
    userID INT PRIMARY KEY,
    encryptedSecret VARCHAR(255) NOT NULL,
    enrolledAt DATETIME NULL,
    lastUsedTimeStep BIGINT NULL,
    createdAt DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_totp_user_account
        FOREIGN KEY (userID) REFERENCES UserAccount(userID)
        ON DELETE CASCADE,

    -- Secrets must be stored as AES-256-GCM envelopes, never as plaintext Base32
    CONSTRAINT chk_totp_secret_encrypted CHECK (encryptedSecret LIKE 'v1:%')
);
