package model.dao;

import db.DBConnection;
import util.OtpUtil;

import java.sql.*;

public class EmailOtpDAO {

    public enum OtpRequestResult {
        CREATED,
        RATE_LIMITED
    }

    /**
     * Invalidates any existing unconsumed OTPs for the user, then inserts a new hashed OTP.
     */
    public OtpRequestResult createOTP(int userID, String plainOtp, int expirationMinutes,
            int maxRequests, int windowMinutes) throws SQLException {
        String lockUserSql = "SELECT userID FROM UserAccount WHERE userID = ? FOR UPDATE";
        String countRecentSql = """
            SELECT COUNT(*) FROM emailrecoveryotp
            WHERE userID = ? AND createdAt >= DATE_SUB(NOW(), INTERVAL ? MINUTE)
            """;
        String invalidateSql = "UPDATE emailrecoveryotp SET isConsumed = 1 WHERE userID = ? AND isConsumed = 0";
        String insertSql = """
            INSERT INTO emailrecoveryotp (userID, otpHash, expiresAt)
            VALUES (?, ?, DATE_ADD(NOW(), INTERVAL ? MINUTE))
            """;

        String otpHash = OtpUtil.hashValue(plainOtp);

        try (Connection conn = DBConnection.getConnection()) {
            conn.setAutoCommit(false);
            try {
                try (PreparedStatement lockStmt = conn.prepareStatement(lockUserSql)) {
                    lockStmt.setInt(1, userID);
                    try (ResultSet userResult = lockStmt.executeQuery()) {
                        if (!userResult.next()) {
                            throw new SQLException("User account not found while creating OTP.");
                        }
                    }
                }

                try (PreparedStatement countStmt = conn.prepareStatement(countRecentSql)) {
                    countStmt.setInt(1, userID);
                    countStmt.setInt(2, windowMinutes);
                    try (ResultSet countResult = countStmt.executeQuery()) {
                        if (countResult.next() && countResult.getInt(1) >= maxRequests) {
                            conn.commit();
                            return OtpRequestResult.RATE_LIMITED;
                        }
                    }
                }

                // Invalidate older OTPs
                try (PreparedStatement invStmt = conn.prepareStatement(invalidateSql)) {
                    invStmt.setInt(1, userID);
                    invStmt.executeUpdate();
                }

                // Insert new OTP record
                try (PreparedStatement insStmt = conn.prepareStatement(insertSql)) {
                    insStmt.setInt(1, userID);
                    insStmt.setString(2, otpHash);
                    insStmt.setInt(3, expirationMinutes);
                    insStmt.executeUpdate();
                }

                conn.commit();
                return OtpRequestResult.CREATED;
            } catch (SQLException e) {
                conn.rollback();
                throw e;
            }
        }
    }

    /**
     * Atomically verifies and consumes an OTP, returning a short-lived recovery token if valid.
     */
    public String verifyAndConsumeOTP(int userID, String plainOtp) throws SQLException {
        String selectSql = """
            SELECT otpID, attemptCount 
            FROM emailrecoveryotp 
            WHERE userID = ? AND isConsumed = 0 AND expiresAt > NOW()
            ORDER BY createdAt DESC LIMIT 1
            """;

        String expectedHash = OtpUtil.hashValue(plainOtp);

        try (Connection conn = DBConnection.getConnection()) {
            try (PreparedStatement selectStmt = conn.prepareStatement(selectSql)) {
                selectStmt.setInt(1, userID);
                ResultSet rs = selectStmt.executeQuery();

                if (!rs.next()) {
                    return null; // No valid active OTP found
                }

                int otpID = rs.getInt("otpID");
                int attemptCount = rs.getInt("attemptCount");

                if (attemptCount >= 3) {
                    // Lock out this OTP due to too many failed attempts
                    invalidateOTP(otpID);
                    return null;
                }

                // Verify hash match
                String verifyHashSql = "SELECT otpID FROM emailrecoveryotp WHERE otpID = ? AND otpHash = ?";
                try (PreparedStatement hashStmt = conn.prepareStatement(verifyHashSql)) {
                    hashStmt.setInt(1, otpID);
                    hashStmt.setString(2, expectedHash);
                    ResultSet hashRs = hashStmt.executeQuery();

                    if (!hashRs.next()) {
                        // Increment failed attempts
                        incrementAttemptCount(otpID);
                        return null;
                    }
                }

                // Atomic consumption and short-lived recovery token generation (15 min validity)
                String rawRecoveryToken = OtpUtil.generateRecoveryToken();
                String tokenHash = OtpUtil.hashValue(rawRecoveryToken);

                String consumeSql = """
                    UPDATE emailrecoveryotp 
                    SET isConsumed = 1, consumedAt = NOW(), 
                        recoveryTokenHash = ?, recoveryTokenExpiresAt = DATE_ADD(NOW(), INTERVAL 15 MINUTE)
                    WHERE otpID = ? AND isConsumed = 0
                    """;

                try (PreparedStatement consumeStmt = conn.prepareStatement(consumeSql)) {
                    consumeStmt.setString(1, tokenHash);
                    consumeStmt.setInt(2, otpID);
                    int updated = consumeStmt.executeUpdate();

                    return (updated > 0) ? rawRecoveryToken : null;
                }
            }
        }
    }

    /**
     * Checks if a recovery token is valid and unexpired before permitting a password reset.
     */
    public boolean validateRecoveryToken(int userID, String rawToken) throws SQLException {
        String sql = """
            SELECT otpID FROM emailrecoveryotp 
            WHERE userID = ? AND recoveryTokenHash = ? AND recoveryTokenExpiresAt > NOW()
            """;
        String tokenHash = OtpUtil.hashValue(rawToken);

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userID);
            stmt.setString(2, tokenHash);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        }
    }

    private void incrementAttemptCount(int otpID) throws SQLException {
        String sql = "UPDATE emailrecoveryotp SET attemptCount = attemptCount + 1 WHERE otpID = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, otpID);
            stmt.executeUpdate();
        }
    }

    private void invalidateOTP(int otpID) throws SQLException {
        String sql = "UPDATE emailrecoveryotp SET isConsumed = 1 WHERE otpID = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, otpID);
            stmt.executeUpdate();
        }
    }
}