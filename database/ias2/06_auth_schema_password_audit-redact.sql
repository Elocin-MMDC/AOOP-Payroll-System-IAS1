-- Preserve historical password-change events but remove credential hashes.
SET SQL_SAFE_UPDATES = 0;

UPDATE auditlog
SET oldValue = "[REDACTED]",
    newValue = "[REDACTED]"
WHERE entityModified = 'UserAccount'
    AND LOWER(attributeModified) = 'password'
    AND (oldValue IS NOT NULL OR newValue IS NOT NULL);