package model.dao;

import db.DBConnection;
import model.pojo.SupportRequest;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SupportRequestDAO {

    // Retrieve support request by ID
    public SupportRequest getById(int ticketID) {
        String sql = "SELECT * FROM SupportRequest WHERE ticketID = ?";
        try (Connection conn = DBConnection.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, ticketID);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToSupportRequest(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Retrieve all support requests of an employee
    public List<SupportRequest> getByEmployeeID(int employeeID) {
        List<SupportRequest> list = new ArrayList<>();
        String sql = "SELECT * FROM SupportRequest WHERE employeeID = ? ORDER BY ticketID DESC";

        try (Connection conn = DBConnection.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, employeeID);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    SupportRequest sr = mapResultSetToSupportRequest(rs);
                    list.add(sr);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
    
    // Insert new support ticket and return the generated ID
    public int insertAndGetId(SupportRequest sr) {
        String sql = """
            INSERT INTO SupportRequest
              (date, employeeID, assignedTeam, severity, subject, description, resolvedBy, status)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?)
        """;

        try (Connection conn = DBConnection.getConnection(); 
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setDate(1, Date.valueOf(sr.getDate()));
            ps.setInt(2, sr.getEmployeeID());
            ps.setString(3, sr.getAssignedTeam());
            ps.setString(4, sr.getSeverity());
            ps.setString(5, sr.getSubject());
            ps.setString(6, sr.getDescription());
            if (sr.getResolvedBy() != null) {
                ps.setInt(7, sr.getResolvedBy());
            } else {
                ps.setNull(7, Types.INTEGER);
            }
            ps.setString(8, sr.getStatus());

            int affected = ps.executeUpdate();
            if (affected == 0) {
                throw new SQLException("Creating support ticket failed, no rows affected.");
            }

            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    return keys.getInt(1);
                } else {
                    throw new SQLException("Creating support ticket failed, no ID obtained.");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }
    
    // Update support request
    public boolean update(SupportRequest request) {
        String sql = "UPDATE SupportRequest SET assignedTeam = ?, severity = ?, subject = ?, description = ?, resolvedBy = ?, status = ?, updatedAt = ? WHERE ticketID = ?";
        try (Connection conn = DBConnection.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, request.getAssignedTeam());
            stmt.setString(2, request.getSeverity());
            stmt.setString(3, request.getSubject());
            stmt.setString(4, request.getDescription());
            if (request.getResolvedBy() != null) {
                stmt.setInt(5, request.getResolvedBy());
            } else {
                stmt.setNull(5, Types.INTEGER);
            }
            stmt.setString(6, request.getStatus());
            stmt.setTimestamp(7, Timestamp.valueOf(request.getUpdatedAt()));
            stmt.setInt(8, request.getTicketID());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // Retrieves all support requests assigned to the specified team
    public List<SupportRequest> getByAssignedTeam(String team) {
        List<SupportRequest> results = new ArrayList<>();
        String sql = """
        SELECT ticketID,
               date,
               employeeID,
               severity,
               subject,
               status
          FROM SupportRequest
         WHERE assignedTeam = ?
         ORDER BY ticketID DESC
    """;

        try (Connection conn = DBConnection.getConnection(); 
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, team);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    SupportRequest sr = new SupportRequest();
                    sr.setTicketID(rs.getInt("ticketID"));
                    sr.setDate(rs.getDate("date").toLocalDate());
                    sr.setEmployeeID(rs.getInt("employeeID"));
                    sr.setSeverity(rs.getString("severity"));
                    sr.setSubject(rs.getString("subject"));
                    sr.setStatus(rs.getString("status"));

                    results.add(sr);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return results;
    }
    
    // Count total pending support requests of the specified team
    public long countPendingByTeam(String team) {
        String sql = "SELECT COUNT(*) FROM SupportRequest WHERE assignedTeam = ? AND status = 'Pending'";
        try (Connection conn = DBConnection.getConnection(); 
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, team);
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

    // Count total pending support requests of an employee
    public long countPendingByEmployeeId(int empId) {
        String sql = "SELECT COUNT(*) FROM SupportRequest WHERE employeeID = ? AND status = 'Pending'";
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

    // Map ResultSet row to SupportRequest
    private SupportRequest mapResultSetToSupportRequest(ResultSet rs) {
        try {
            SupportRequest request = new SupportRequest();
            request.setTicketID(rs.getInt("ticketID"));
            request.setDate(rs.getDate("date").toLocalDate());
            request.setEmployeeID(rs.getInt("employeeID"));
            request.setAssignedTeam(rs.getString("assignedTeam"));
            request.setSeverity(rs.getString("severity"));
            request.setSubject(rs.getString("subject"));
            request.setDescription(rs.getString("description"));
            int resolvedBy = rs.getInt("resolvedBy");
            request.setResolvedBy(rs.wasNull() ? null : resolvedBy);
            request.setStatus(rs.getString("status"));
            request.setCreatedAt(rs.getTimestamp("createdAt").toLocalDateTime());
            request.setUpdatedAt(rs.getTimestamp("updatedAt").toLocalDateTime());
            return request;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
