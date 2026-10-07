-- Allow audit events for recovery attempts that cannot be associated with an account.
ALTER TABLE auditlog
MODIFY COLUMN userID INT NULL;