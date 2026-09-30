-- MotorPH IAS2 - PII Encryption at Rest
-- Step 3: Finalize the govinformation schema AFTER successful data migration.
-- Do not run this while lookup columns still contain NULL values.

ALTER TABLE govinformation
  MODIFY sssNumberLookup BINARY(32) NOT NULL,
  MODIFY philHealthNumberLookup BINARY(32) NOT NULL,
  MODIFY tinLookup BINARY(32) NOT NULL,
  MODIFY pagIbigNumberLookup BINARY(32) NOT NULL,
  DROP INDEX sssNumber,
  DROP INDEX philHealthNumber,
  DROP INDEX tin,
  DROP INDEX pagIbigNumber,
  ADD UNIQUE KEY uq_gov_sss_lookup (sssNumberLookup),
  ADD UNIQUE KEY uq_gov_philhealth_lookup (philHealthNumberLookup),
  ADD UNIQUE KEY uq_gov_tin_lookup (tinLookup),
  ADD UNIQUE KEY uq_gov_pagibig_lookup (pagIbigNumberLookup),
  ADD CONSTRAINT chkSssEncrypted CHECK (sssNumber LIKE 'v1:%'),
  ADD CONSTRAINT chkPhilHealthEncrypted CHECK (philHealthNumber LIKE 'v1:%'),
  ADD CONSTRAINT chkTinEncrypted CHECK (tin LIKE 'v1:%'),
  ADD CONSTRAINT chkPagIbigEncrypted CHECK (pagIbigNumber LIKE 'v1:%');

-- Expected final state:
-- 1) All four lookup columns are BINARY(32) NOT NULL.
-- 2) Duplicate detection is enforced through keyed HMAC lookup columns.
-- 3) Raw government-ID columns accept only the versioned v1: ciphertext envelope.
-- 4) Authorized decryption remains in the Java application layer.
