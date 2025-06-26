package model.dao;

import db.DBConnection;
import model.pojo.OvertimeRequest;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OvertimeRequestDAO {

    // Retrieve overtime request by ID
    public OvertimeRequest getById(int id) {
        String sql = "SELECT * FROM OvertimeRequest WHERE overtimeID = ?";
        try (Connection conn = DBConnection.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapResultSetToOvertimeRequest(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Retrieve all overtime requests
    public List<OvertimeRequest> getAll() {
        List<OvertimeRequest> list = new ArrayList<>();
        String sql = "SELECT * FROM OvertimeRequest ORDER BY overtimeID DESC";
        try (Connection conn = DBConnection.getConnection(); 
             Statement stmt = conn.createStatement(); 
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                list.add(mapResultSetToOvertimeRequest(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    // Retrieve overtime requests by employee ID
    public List<OvertimeRequest> getByEmployeeID(int employeeID) {
        List<OvertimeRequest> list = new ArrayList<>();
        String sql = "SELECT * FROM OvertimeRequest WHERE employeeID = ? ORDER BY overtimeID DESC";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, employeeID);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    OvertimeRequest ot = mapResultSetToOvertimeRequest(rs);
                    list.add(ot);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    // Insert new overtime request and return the generated overtimeID
    public int insertAndGetId(OvertimeRequest or) {
        String sql = """
            INSERT INTO OvertimeRequest
              (date, employeeID, overtimeWorkDate, overtimeHours, reason, approvedBy, status)
            VALUES (?, ?, ?, ?, ?, ?, ?)
        """;

        try (Connection conn = DBConnection.getConnection(); 
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setDate(1, Date.valueOf(or.getDate()));
            ps.setInt(2, or.getEmployeeID());
            ps.setDate(3, Date.valueOf(or.getOvertimeWorkDate()));
            ps.setBigDecimal(4, or.getOvertimeHours());
            ps.setString(5, or.getReason());
            if (or.getApprovedBy() != null) {
                ps.setInt(6, or.getApprovedBy());
            } else {
                ps.setNull(6, Types.INTEGER);
            }
            ps.setString(7, or.getStatus());

            int affected = ps.executeUpdate();
            if (affected == 0) {
                throw new SQLException("Inserting overtime failed, no rows affected.");
            }

            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    return keys.getInt(1);
                } else {
                    throw new SQLException("Inserting overtime failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }
    
    // Update overtime request
    public boolean update(OvertimeRequest request) {
        String sql = "UPDATE OvertimeRequest SET date = ?, employeeID = ?, overtimeWorkDate = ?, overtimeHours = ?, reason = ?, approvedBy = ?, status = ?, updatedAt = ? WHERE overtimeID = ?";
        try (Connection conn = DBConnection.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDate(1, Date.valueOf(request.getDate()));
            stmt.setInt(2, request.getEmployeeID());
            stmt.setDate(3, Date.valueOf(request.getOvertimeWorkDate()));
            stmt.setBigDecimal(4, request.getOvertimeHours());
            stmt.setString(5, request.getReason());
            if (request.getApprovedBy() != null) {
                stmt.setInt(6, request.getApprovedBy());
            } else {
                stmt.setNull(6, Types.INTEGER);
            }
            stmt.setString(7, request.getStatus());
            stmt.setTimestamp(8, Timestamp.valueOf(request.getUpdatedAt()));
            stmt.setInt(9, request.getOvertimeID());
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    // Count total pending overtime requests
    public long countPending() {
        String sql = "SELECT COUNT(*) FROM OvertimeRequest WHERE status = 'Pending'";
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

    // Count total pending overtime requests for an employee
    public long countPendingByEmployeeId(int empId) {
        String sql = "SELECT COUNT(*) FROM OvertimeRequest WHERE employeeID = ? AND status = 'Pending'";
        try (Connection c = DBConnection.getConnection(); 
             PreparedStatement ps = c.prepareStatement(sql)) {

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

    // Map ResultSet row to OvertimeRequest POJO
    private OvertimeRequest mapResultSetToOvertimeRequest(ResultSet rs) {
        try {
            OvertimeRequest req = new OvertimeRequest();
            req.setOvertimeID(rs.getInt("overtimeID"));
            req.setDate(rs.getDate("date").toLocalDate());
            req.setEmployeeID(rs.getInt("employeeID"));
            req.setOvertimeWorkDate(rs.getDate("overtimeWorkDate").toLocalDate());
            req.setOvertimeHours(rs.getBigDecimal("overtimeHours"));
            req.setReason(rs.getString("reason"));
            int approvedBy = rs.getInt("approvedBy");
            req.setApprovedBy(rs.wasNull() ? null : approvedBy);
            req.setStatus(rs.getString("status"));
            req.setCreatedAt(rs.getTimestamp("createdAt").toLocalDateTime());
            req.setUpdatedAt(rs.getTimestamp("updatedAt").toLocalDateTime());
            return req;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
