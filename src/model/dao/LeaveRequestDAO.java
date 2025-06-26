package model.dao;

import db.DBConnection;
import model.pojo.LeaveRequest;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LeaveRequestDAO {

    // Retrieve leave request by ID
    public LeaveRequest getById(int id) {
        String sql = "SELECT * FROM LeaveRequest WHERE leaveID = ?";

        try (Connection conn = DBConnection.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapResultSetToLeaveRequest(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Retrieve all leave requests
    public List<LeaveRequest> getAll() {
        List<LeaveRequest> list = new ArrayList<>();
        String sql = "SELECT * FROM LeaveRequest ORDER BY leaveID DESC";

        try (Connection conn = DBConnection.getConnection(); 
             Statement stmt = conn.createStatement(); 
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                list.add(mapResultSetToLeaveRequest(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    // Get leave requests by employee ID
    public List<LeaveRequest> getByEmployeeID(int employeeID) {
        List<LeaveRequest> list = new ArrayList<>();
        String sql = "SELECT * FROM LeaveRequest WHERE employeeID = ? ORDER BY leaveID DESC";

        try (Connection conn = DBConnection.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, employeeID);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    LeaveRequest leaveRequest = mapResultSetToLeaveRequest(rs);
                    list.add(leaveRequest);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    // Update leave request
    public boolean update(LeaveRequest lr) {
        String sql = "UPDATE LeaveRequest SET employeeID = ?, date = ?, leaveTypeID = ?, startDate = ?, endDate = ?, leaveDays = ?, reason = ?, status = ?, approvedBy = ? WHERE leaveID = ?";
        try (Connection conn = DBConnection.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, lr.getEmployeeID());
            stmt.setDate(2, Date.valueOf(lr.getDate()));
            stmt.setInt(3, lr.getLeaveTypeID());
            stmt.setDate(4, Date.valueOf(lr.getStartDate()));
            stmt.setDate(5, Date.valueOf(lr.getEndDate()));
            stmt.setInt(6, lr.getLeaveDays());
            stmt.setString(7, lr.getReason());
            stmt.setString(8, lr.getStatus());
            if (lr.getApprovedBy() != null) {
                stmt.setInt(9, lr.getApprovedBy());
            } else {
                stmt.setNull(9, Types.INTEGER);
            }
            stmt.setInt(10, lr.getLeaveID());
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    // Insert new leave request, and return the generated leaveID
    public int insertAndGetId(LeaveRequest lr) {
        String sql = """
            INSERT INTO LeaveRequest
              (employeeID, date, leaveTypeID, startDate, endDate, leaveDays, reason, status)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?)
        """;

        try (Connection conn = DBConnection.getConnection(); 
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, lr.getEmployeeID());
            ps.setDate(2, Date.valueOf(lr.getDate()));
            ps.setInt(3, lr.getLeaveTypeID());
            ps.setDate(4, Date.valueOf(lr.getStartDate()));
            ps.setDate(5, Date.valueOf(lr.getEndDate()));
            ps.setInt(6, lr.getLeaveDays());
            ps.setString(7, lr.getReason());
            ps.setString(8, lr.getStatus());

            int affected = ps.executeUpdate();
            if (affected == 0) {
                throw new SQLException("Insert failed, no rows affected.");
            }

            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    return keys.getInt(1);
                } else {
                    throw new SQLException("Insert succeeded but no ID returned.");
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            return -1;
        }
    }
    
    // Count all pending leave requests
    public long countPending() {
        String sql = "SELECT COUNT(*) FROM LeaveRequest WHERE status = 'Pending'";
        try (Connection conn = DBConnection.getConnection(); 
             PreparedStatement ps = conn.prepareStatement(sql); 
             ResultSet rs = ps.executeQuery()) {
            
            if (rs.next()) {
                return rs.getLong(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    // Count all pending leave requests of an employee
    public long countPendingByEmployeeId(int empId) {
        String sql = "SELECT COUNT(*) FROM LeaveRequest WHERE employeeID = ? AND status = 'Pending'";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, empId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getLong(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    // Map ResultSet row to LeaveRequest POJO
    private LeaveRequest mapResultSetToLeaveRequest(ResultSet rs) {
        try {
            LeaveRequest lr = new LeaveRequest();
            lr.setLeaveID(rs.getInt("leaveID"));
            lr.setEmployeeID(rs.getInt("employeeID"));
            lr.setDate(rs.getDate("date").toLocalDate());
            lr.setLeaveTypeID(rs.getInt("leaveTypeID"));
            lr.setStartDate(rs.getDate("startDate").toLocalDate());
            lr.setEndDate(rs.getDate("endDate").toLocalDate());
            lr.setLeaveDays(rs.getInt("leaveDays"));
            lr.setReason(rs.getString("reason"));
            lr.setStatus(rs.getString("status"));

            int approvedBy = rs.getInt("approvedBy");
            if (!rs.wasNull()) {
                lr.setApprovedBy(approvedBy);
            }

            lr.setCreatedAt(rs.getTimestamp("createdAt").toLocalDateTime());
            lr.setUpdatedAt(rs.getTimestamp("updatedAt").toLocalDateTime());
            return lr;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
