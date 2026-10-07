-- Add email column to useraccount table 
ALTER TABLE useraccount 
ADD COLUMN email VARCHAR(255) NULL AFTER username, 
ADD UNIQUE INDEX idx_useraccount_email (email);

-- Email OTP Recovery Table Migration
CREATE TABLE IF NOT EXISTS emailrecoveryotp (
    otpID INT AUTO_INCREMENT PRIMARY KEY,
    userID INT NOT NULL,
    otpHash VARCHAR(64) NOT NULL,
    expiresAt DATETIME NOT NULL,
    attemptCount INT NOT NULL DEFAULT 0,
    isConsumed TINYINT(1) NOT NULL DEFAULT 0,
    recoveryTokenHash VARCHAR(64) NULL,
    recoveryTokenExpiresAt DATETIME NULL,
    createdAt DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    consumedAt DATETIME NULL,
    
    CONSTRAINT fk_otp_user_account 
        FOREIGN KEY (userID) REFERENCES UserAccount(userID) 
        ON DELETE CASCADE,
        
    INDEX idx_user_active_otp (userID, isConsumed, expiresAt),
    INDEX idx_recovery_token (recoveryTokenHash)
)