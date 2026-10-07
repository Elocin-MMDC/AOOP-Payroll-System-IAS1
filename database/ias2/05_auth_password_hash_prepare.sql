-- Script 1: Add salt column to useraccount table 
ALTER TABLE useraccount 
ADD COLUMN passwordSalt VARCHAR(64) NULL AFTER password;

-- Script 2: Mark existing SHA-256 credentials for a forced password change. A reusable SQL snippet to mark existing SHA-256 credentials for a forced password change
SET @previous_sql_safe_updates = @@SQL_SAFE_UPDATES;
SET SQL_SAFE_UPDATES = 0;

UPDATE useraccount
SET mustChangePassword = TRUE
WHERE password REGEXP '^[0-9a-fA-F]{64}$'
    AND mustChangePassword = FALSE;

SET SQL_SAFE_UPDATES = @previous_sql_safe_updates;