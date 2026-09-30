package util;

import db.DBConnection;
import java.sql.*;
import java.util.*;

public final class PiiDataMigrationTool {
    private static final String[] C = {
        "MotorPH:govinformation:sssNumber:v1",
        "MotorPH:govinformation:philHealthNumber:v1",
        "MotorPH:govinformation:tin:v1",
        "MotorPH:govinformation:pagIbigNumber:v1"
    };
    private record Row(int id, String[] v) {}

    public static void main(String[] args) {
        String q = "SELECT govID,sssNumber,philHealthNumber,tin,pagIbigNumber," +
                   "sssNumberLookup,philHealthNumberLookup,tinLookup,pagIbigNumberLookup " +
                   "FROM govinformation ORDER BY govID FOR UPDATE";
        String u = "UPDATE govinformation SET sssNumber=?,sssNumberLookup=?," +
                   "philHealthNumber=?,philHealthNumberLookup=?,tin=?,tinLookup=?," +
                   "pagIbigNumber=?,pagIbigNumberLookup=? WHERE govID=?";

        try (Connection c = DBConnection.getConnection()) {
            c.setAutoCommit(false);
            try {
                List<Row> rows = new ArrayList<>();
                try (PreparedStatement p = c.prepareStatement(q); ResultSet r = p.executeQuery()) {
                    while (r.next()) {
                        String[] v = {r.getString(2),r.getString(3),r.getString(4),r.getString(5)};
                        for (String s : v)
                            if (s == null || PiiCryptoUtil.isEncrypted(s))
                                throw new IllegalStateException("Expected plaintext-only source data.");
                        for (int i=6;i<=9;i++)
                            if (r.getBytes(i) != null)
                                throw new IllegalStateException("Lookup data already exists.");
                        rows.add(new Row(r.getInt(1), v));
                    }
                }
                if (rows.isEmpty()) throw new IllegalStateException("No rows found.");
                System.out.println("PASS: Preflight complete. Rows ready=" + rows.size());

                int updated = 0;
                try (PreparedStatement p = c.prepareStatement(u)) {
                    for (Row row : rows) {
                        for (int i=0;i<4;i++) {
                            p.setString(i*2+1, PiiCryptoUtil.encrypt(row.v()[i], C[i]));
                            p.setBytes(i*2+2, PiiCryptoUtil.lookupHmac(row.v()[i], C[i]));
                        }
                        p.setInt(9, row.id());
                        if (p.executeUpdate() != 1) throw new IllegalStateException("Update count mismatch.");
                        updated++;
                    }
                }

                int verified = 0;
                try (PreparedStatement p = c.prepareStatement(q); ResultSet r = p.executeQuery()) {
                    while (r.next()) {
                        if (verified >= rows.size()) throw new IllegalStateException("Extra row found.");
                        Row old = rows.get(verified);
                        if (r.getInt(1) != old.id()) throw new IllegalStateException("Row order mismatch.");
                        for (int i=0;i<4;i++) {
                            String d = PiiCryptoUtil.decrypt(r.getString(i+2), C[i]);
                            if (!Objects.equals(old.v()[i], d))
                                throw new IllegalStateException("Decryption verification failed.");
                            if (!Arrays.equals(PiiCryptoUtil.lookupHmac(old.v()[i], C[i]), r.getBytes(i+6)))
                                throw new IllegalStateException("Lookup verification failed.");
                        }
                        verified++;
                    }
                }
                if (updated != rows.size() || verified != rows.size())
                    throw new IllegalStateException("Row-count verification failed.");

                System.out.println("PASS: Stored values verified. Rows=" + verified);
                c.commit();
                System.out.println("PASS: Migration committed. Rows migrated=" + updated);
            } catch (Exception e) {
                c.rollback();
                System.err.println("FAIL: Migration rolled back. No changes committed.");
                System.err.println("Reason: " + e.getMessage());
                System.exit(1);
            }
        } catch (Exception e) {
            System.err.println("FAIL: Migration could not start.");
            System.err.println("Reason: " + e.getMessage());
            System.exit(1);
        }
    }
}
