package model.dao;

import db.DBConnection;
import model.pojo.UserTotp;
import java.sql.*;

public class UserTotpDAO {

    // Retrieve the TOTP record (enrolled or pending) for the given user
    public UserTotp getByUserId(int userID) {
        String sql = "SELECT * FROM usertotp WHERE userID = ?";
        try (Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, userID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return mapResultSetToUserTotp(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Stores a new pending (not yet confirmed) encrypted secret, replacing any earlier
     * pending secret. Enrolled secrets are never overwritten here.
     */
    public boolean savePendingSecret(int userID, String encryptedSecret) {
        String sql = """
            INSERT INTO usertotp (userID, encryptedSecret, enrolledAt, lastUsedTimeStep)
            VALUES (?, ?, NULL, NULL)
            ON DUPLICATE KEY UPDATE
                encryptedSecret = IF(enrolledAt IS NULL, VALUES(encryptedSecret), encryptedSecret),
                createdAt = IF(enrolledAt IS NULL, NOW(), createdAt)
            """;
        try (Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, userID);
            ps.setString(2, encryptedSecret);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Atomically records the accepted time step. Returns false if this step (or a later one)
     * was already used, so the same code can never be accepted twice.
     */
    public boolean consumeTimeStep(int userID, long timeStep) throws SQLException {
        String sql = """
            UPDATE usertotp SET lastUsedTimeStep = ?
            WHERE userID = ? AND enrolledAt IS NOT NULL
                AND (lastUsedTimeStep IS NULL OR lastUsedTimeStep < ?)
            """;
        try (Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setLong(1, timeStep);
            ps.setInt(2, userID);
            ps.setLong(3, timeStep);
            return ps.executeUpdate() == 1;
        }
    }

    /**
     * Confirms a pending enrollment and sets useraccount.mfaEnabled in one transaction.
     * The confirming code's time step is consumed so it cannot be replayed at login.
     */
    public boolean activateEnrollment(int userID, long timeStep) throws SQLException {
        String activateSql = """
            UPDATE usertotp SET enrolledAt = NOW(), lastUsedTimeStep = ?
            WHERE userID = ? AND enrolledAt IS NULL
            """;
        String enableSql = "UPDATE UserAccount SET mfaEnabled = TRUE, updatedAt = NOW() WHERE userID = ?";

        try (Connection con = DBConnection.getConnection()) {
            con.setAutoCommit(false);
            try (PreparedStatement activateStmt = con.prepareStatement(activateSql);
                    PreparedStatement enableStmt = con.prepareStatement(enableSql)) {

                activateStmt.setLong(1, timeStep);
                activateStmt.setInt(2, userID);
                if (activateStmt.executeUpdate() != 1) {
                    con.rollback();
                    return false;
                }

                enableStmt.setInt(1, userID);
                enableStmt.executeUpdate();

                con.commit();
                return true;
            } catch (SQLException e) {
                con.rollback();
                throw e;
            }
        }
    }

    /**
     * Removes the user's TOTP secret and clears useraccount.mfaEnabled in one transaction.
     * The user must enroll a new authenticator at their next login.
     */
    public boolean clearEnrollment(int userID) throws SQLException {
        String deleteSql = "DELETE FROM usertotp WHERE userID = ?";
        String disableSql = "UPDATE UserAccount SET mfaEnabled = FALSE, updatedAt = NOW() WHERE userID = ?";

        try (Connection con = DBConnection.getConnection()) {
            con.setAutoCommit(false);
            try (PreparedStatement deleteStmt = con.prepareStatement(deleteSql);
                    PreparedStatement disableStmt = con.prepareStatement(disableSql)) {

                deleteStmt.setInt(1, userID);
                deleteStmt.executeUpdate();

                disableStmt.setInt(1, userID);
                boolean updated = disableStmt.executeUpdate() > 0;

                con.commit();
                return updated;
            } catch (SQLException e) {
                con.rollback();
                throw e;
            }
        }
    }

    // Map ResultSet row to UserTotp POJO
    private UserTotp mapResultSetToUserTotp(ResultSet rs) throws SQLException {
        UserTotp t = new UserTotp();
        t.setUserID(rs.getInt("userID"));
        t.setEncryptedSecret(rs.getString("encryptedSecret"));
        Timestamp enrolled = rs.getTimestamp("enrolledAt");
        t.setEnrolledAt(enrolled != null ? enrolled.toLocalDateTime() : null);
        long lastStep = rs.getLong("lastUsedTimeStep");
        t.setLastUsedTimeStep(rs.wasNull() ? null : lastStep);
        t.setCreatedAt(rs.getTimestamp("createdAt").toLocalDateTime());
        return t;
    }
}
