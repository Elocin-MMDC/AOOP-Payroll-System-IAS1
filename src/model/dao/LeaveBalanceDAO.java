package model.dao;

import db.DBConnection;
import model.pojo.LeaveBalance;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class LeaveBalanceDAO {

    // Retrieve leave balances by employee ID
    public List<LeaveBalance> getByEmployeeId(int employeeId) {
        List<LeaveBalance> list = new ArrayList<>();
        String sql = "SELECT * FROM LeaveBalance WHERE employeeID = ?";

        try (Connection conn = DBConnection.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, employeeId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    list.add(mapResultSetToLeaveBalance(rs));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    // Insert new leave balance for regular employees
    public void insert(LeaveBalance lb) {
        String sql = "INSERT INTO LeaveBalance (employeeID, leaveTypeID, entitledDays, usedDays, remainingDays) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, lb.getEmployeeID());
            stmt.setInt(2, lb.getLeaveTypeID());
            stmt.setInt(3, lb.getEntitledDays());
            stmt.setInt(4, lb.getUsedDays());
            stmt.setInt(5, lb.getRemainingDays());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Update used and remaining days
    public void updateUsedAndRemainingDays(int leaveBalanceId, int usedDays, int remainingDays) {
        String sql = "UPDATE LeaveBalance SET usedDays = ?, remainingDays = ?, updatedAt = CURRENT_TIMESTAMP WHERE leaveBalanceID = ?";

        try (Connection conn = DBConnection.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, usedDays);
            stmt.setInt(2, remainingDays);
            stmt.setInt(3, leaveBalanceId);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    // Update leave balance
    public boolean update(LeaveBalance lb) {
        String sql = """
        UPDATE LeaveBalance
        SET 
            employeeID = ?,
            leaveTypeID = ?,
            entitledDays = ?,
            usedDays = ?,
            remainingDays = ?,
            updatedAt = ?
        WHERE leaveBalanceID = ?
    """;

        try (Connection conn = DBConnection.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, lb.getEmployeeID());
            stmt.setInt(2, lb.getLeaveTypeID());
            stmt.setInt(3, lb.getEntitledDays());
            stmt.setInt(4, lb.getUsedDays());
            stmt.setInt(5, lb.getRemainingDays());
            stmt.setTimestamp(6, Timestamp.valueOf(LocalDateTime.now()));
            stmt.setInt(7, lb.getLeaveBalanceID());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Map ResultSet row to LeaveBalance POJO
    private LeaveBalance mapResultSetToLeaveBalance(ResultSet rs) throws SQLException {
        LeaveBalance lb = new LeaveBalance();
        lb.setLeaveBalanceID(rs.getInt("leaveBalanceID"));
        lb.setEmployeeID(rs.getInt("employeeID"));
        lb.setLeaveTypeID(rs.getInt("leaveTypeID"));
        lb.setEntitledDays(rs.getInt("entitledDays"));
        lb.setUsedDays(rs.getInt("usedDays"));
        lb.setRemainingDays(rs.getInt("remainingDays"));
        lb.setCreatedAt(rs.getTimestamp("createdAt").toLocalDateTime());
        lb.setUpdatedAt(rs.getTimestamp("updatedAt").toLocalDateTime());
        return lb;
    }
}
