-- MotorPH IAS2 - PII Encryption at Rest
-- Step 1: Prepare the original govinformation schema for AES-256-GCM migration.
-- Run this ONCE on a fresh local copy of the original MotorPH database.
-- Do not run the application between this step and the data migration.

ALTER TABLE govinformation
  MODIFY sssNumber VARCHAR(255) NOT NULL,
  MODIFY philHealthNumber VARCHAR(255) NOT NULL,
  MODIFY tin VARCHAR(255) NOT NULL,
  MODIFY pagIbigNumber VARCHAR(255) NOT NULL,
  ADD COLUMN sssNumberLookup BINARY(32) NULL AFTER sssNumber,
  ADD COLUMN philHealthNumberLookup BINARY(32) NULL AFTER philHealthNumber,
  ADD COLUMN tinLookup BINARY(32) NULL AFTER tin,
  ADD COLUMN pagIbigNumberLookup BINARY(32) NULL AFTER pagIbigNumber,
  DROP CHECK chkPagIbigFormat,
  DROP CHECK chkPhilHealthFormat,
  DROP CHECK chkSssFormat,
  DROP CHECK chkTinFormat;

-- Expected state after this script:
-- 1) Existing government IDs are still plaintext temporarily.
-- 2) The four lookup columns exist and are NULL.
-- 3) The old plaintext-format CHECK constraints are removed.
-- 4) The old unique indexes remain until migration is complete.
