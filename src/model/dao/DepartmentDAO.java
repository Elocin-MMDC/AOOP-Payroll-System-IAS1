package model.dao;

import db.DBConnection;
import java.sql.*;

public class DepartmentDAO {

    // Count total departments
    public long countAll() {
        String sql = "SELECT COUNT(*) FROM Department";
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
}