package util;

import db.DBConnection;
import java.sql.*;
import java.util.*;
import util.PasswordCryptoUtil.HashResult;

/**
 * Phase 2, step 5: converts every legacy unsalted SHA-256 password hash to salted
 * PBKDF2-HMAC-SHA256 without needing the original passwords. Each legacy hash is
 * wrapped as PBKDF2(SHA-256 hash, new salt, 600,000 iterations); PasswordUtil
 * pre-hashes with SHA-256 when verifying these accounts.
 *
 * Runs in one transaction and verifies every converted row before committing;
 * any error rolls back everything. Safe to re-run: only unconverted rows are touched.
 */
public final class LegacyPasswordBackfillTool {

    private static final String LEGACY_SHA256_PATTERN = "^[0-9a-fA-F]{64}$";
    private record Row(int id, String legacyHash) {}

    public static void main(String[] args) {
        if (args.length == 0 || !args[0].equals("--backup-taken")) {
            System.err.println("FAIL: Take and verify a database backup first (see README_SETUP.md),");
            System.err.println("      then re-run with the argument --backup-taken.");
            System.exit(1);
        }

        String q = "SELECT userID, password FROM useraccount "
                + "WHERE passwordSalt IS NULL OR passwordSalt = '' ORDER BY userID FOR UPDATE";
        String u = "UPDATE useraccount SET password = ?, passwordSalt = ? "
                + "WHERE userID = ? AND (passwordSalt IS NULL OR passwordSalt = '')";
        String check = "SELECT password, passwordSalt FROM useraccount WHERE userID = ?";

        try (Connection c = DBConnection.getConnection()) {
            c.setAutoCommit(false);
            try {
                List<Row> rows = new ArrayList<>();
                try (PreparedStatement p = c.prepareStatement(q); ResultSet r = p.executeQuery()) {
                    while (r.next()) {
                        String hash = r.getString(2);
                        if (hash == null || !hash.matches(LEGACY_SHA256_PATTERN)) {
                            throw new IllegalStateException("userID " + r.getInt(1)
                                    + " has no salt but is not a legacy SHA-256 hash.");
                        }
                        rows.add(new Row(r.getInt(1), hash));
                    }
                }
                if (rows.isEmpty()) {
                    c.rollback();
                    System.out.println("PASS: No legacy SHA-256 hashes remain. Nothing to do.");
                    return;
                }
                System.out.println("PASS: Preflight complete. Legacy hashes found=" + rows.size());

                int updated = 0;
                try (PreparedStatement p = c.prepareStatement(u)) {
                    for (Row row : rows) {
                        HashResult wrapped = PasswordUtil.wrapLegacySha256Hash(row.legacyHash());
                        p.setString(1, wrapped.hash());
                        p.setString(2, wrapped.salt());
                        p.setInt(3, row.id());
                        if (p.executeUpdate() != 1) throw new IllegalStateException("Update count mismatch.");
                        updated++;
                    }
                }

                // Re-read each row and confirm the stored PBKDF2 value matches its original legacy hash
                int verified = 0;
                try (PreparedStatement p = c.prepareStatement(check)) {
                    for (Row row : rows) {
                        p.setInt(1, row.id());
                        try (ResultSet r = p.executeQuery()) {
                            if (!r.next()) throw new IllegalStateException("Row missing after update.");
                            String salt = r.getString(2);
                            if (salt == null || !salt.startsWith(PasswordUtil.WRAPPED_LEGACY_SALT_PREFIX)) {
                                throw new IllegalStateException("Salt marker missing for userID " + row.id());
                            }
                            String rawSalt = salt.substring(PasswordUtil.WRAPPED_LEGACY_SALT_PREFIX.length());
                            if (!PasswordCryptoUtil.verifyPassword(row.legacyHash().toLowerCase(), r.getString(1), rawSalt)) {
                                throw new IllegalStateException("PBKDF2 verification failed for userID " + row.id());
                            }
                            if (r.getString(1).equalsIgnoreCase(row.legacyHash())) {
                                throw new IllegalStateException("Legacy hash still stored for userID " + row.id());
                            }
                        }
                        verified++;
                    }
                }
                if (updated != rows.size() || verified != rows.size()) {
                    throw new IllegalStateException("Row-count verification failed.");
                }

                System.out.println("PASS: Stored values verified. Rows=" + verified);
                c.commit();
                System.out.println("PASS: Backfill committed. Legacy hashes converted=" + updated);
            } catch (Exception e) {
                c.rollback();
                System.err.println("FAIL: Backfill rolled back. No changes committed.");
                System.err.println("Reason: " + e.getMessage());
                System.exit(1);
            }
        } catch (SQLException e) {
            System.err.println("FAIL: Database connection error: " + e.getMessage());
            System.exit(1);
        }
    }
}
