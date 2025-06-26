package model.dao;

import db.DBConnection;
import model.pojo.LoginLog;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LoginLogDAO {

    // Insert login log
    public void insert(LoginLog log) {
        String sql = "INSERT INTO LoginLog (userID, status, loginAttempt, isLocked, lockEndTime) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, log.getUserID());
            stmt.setString(2, log.getStatus());
            stmt.setInt(3, log.getLoginAttempt());
            stmt.setBoolean(4, log.isIsLocked());

            if (log.getLockEndTime() != null) {
                stmt.setTimestamp(5, Timestamp.valueOf(log.getLockEndTime()));
            } else {
                stmt.setNull(5, Types.TIMESTAMP);
            }

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Retrieve all login logs
    public List<LoginLog> getAll() {
        List<LoginLog> list = new ArrayList<>();
        String sql = "SELECT * FROM LoginLog ORDER BY createdAt DESC";

        try (Connection conn = DBConnection.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(sql); 
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                list.add(mapResultSetToLoginLog(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
    
    // Get recent login attempts limited by count
    public List<LoginLog> getRecentAttempts(int userID, int limit) {
        List<LoginLog> list = new ArrayList<>();
        String sql = "SELECT * FROM LoginLog WHERE userID = ? ORDER BY createdAt DESC LIMIT ?";

        try (Connection conn = DBConnection.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userID);
            stmt.setInt(2, limit);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    list.add(mapResultSetToLoginLog(rs));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // Map ResultSet row to LoginLog POJO
    private LoginLog mapResultSetToLoginLog(ResultSet rs) {
        try {
            LoginLog log = new LoginLog();
            log.setLoginID(rs.getInt("loginID"));
            log.setUserID(rs.getInt("userID"));
            log.setCreatedAt(rs.getTimestamp("createdAt").toLocalDateTime());
            log.setStatus(rs.getString("status"));
            log.setLoginAttempt(rs.getInt("loginAttempt"));
            log.setIsLocked(rs.getBoolean("isLocked"));

            Timestamp lockEndTime = rs.getTimestamp("lockEndTime");
            log.setLockEndTime(lockEndTime != null ? lockEndTime.toLocalDateTime() : null);

            return log;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    // Reset login lock state by logging a successful login with cleared lock fields
    public void clearLockState(int userID) {
        String sql = "INSERT INTO LoginLog (userID, status, loginAttempt, isLocked, lockEndTime) "
                + "VALUES (?, 'SUCCESS', 0, FALSE, NULL)";
        try (Connection conn = DBConnection.getConnection(); 
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, userID);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
