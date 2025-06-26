package model.dao;

import db.DBConnection;
import model.pojo.AuditLog;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AuditLogDAO {

    // Get all audit logs
    public List<AuditLog> getAll() {
        List<AuditLog> logs = new ArrayList<>();
        String sql = "SELECT * FROM AuditLog ORDER BY auditID DESC";

        try (Connection conn = DBConnection.getConnection(); 
             Statement stmt = conn.createStatement(); 
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                logs.add(mapResultSetToAuditLog(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return logs;
    }

    // Insert a new audit log
    public boolean insert(AuditLog log) {
        String sql = "INSERT INTO AuditLog (userID, createdAt, action, entityModified, entityID, attributeModified, oldValue, newValue) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, log.getUserID());
            stmt.setTimestamp(2, Timestamp.valueOf(log.getCreatedAt()));
            stmt.setString(3, log.getAction());
            stmt.setString(4, log.getEntityModified());
            stmt.setInt(5, log.getEntityID());
            stmt.setString(6, log.getAttributeModified());

            if (log.getOldValue() != null) {
                stmt.setString(7, log.getOldValue());
            } else {
                stmt.setNull(7, Types.VARCHAR);
            }

            if (log.getNewValue() != null) {
                stmt.setString(8, log.getNewValue());
            } else {
                stmt.setNull(8, Types.VARCHAR);
            }

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // Map ResultSet row to AuditLog POJO
    private AuditLog mapResultSetToAuditLog(ResultSet rs) throws SQLException {
        AuditLog log = new AuditLog();
        log.setAuditID(rs.getInt("auditID"));
        log.setUserID(rs.getInt("userID"));
        log.setCreatedAt(rs.getTimestamp("createdAt").toLocalDateTime());
        log.setAction(rs.getString("action"));
        log.setEntityModified(rs.getString("entityModified"));
        log.setEntityID(rs.getInt("entityID"));
        log.setAttributeModified(rs.getString("attributeModified"));
        log.setOldValue(rs.getString("oldValue"));
        log.setNewValue(rs.getString("newValue"));
        return log;
    }
}
