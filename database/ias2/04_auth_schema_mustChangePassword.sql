
-- Add mustChangePassword boolean column to users column
ALTER TABLE useraccount
ADD mustChangePassword BOOLEAN NOT NULL DEFAULT FALSE;

-- Make it default to true for new rows
ALTER TABLE useraccount 
ALTER COLUMN mustChangePassword SET DEFAULT TRUE;

