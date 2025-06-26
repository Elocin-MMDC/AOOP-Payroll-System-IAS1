package model.dao;

import db.DBConnection;
import model.pojo.Allowance;
import java.sql.*;

public class AllowanceDAO {
    
    // Retrieve allowance by ID
    public Allowance getById(int id) {
        String sql = "SELECT * FROM Allowance WHERE allowanceID = ?";
        try (Connection conn = DBConnection.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Allowance a = new Allowance();
                a.setAllowanceID(rs.getInt("allowanceID"));
                a.setAllowanceTypeID(rs.getInt("allowanceTypeID"));
                a.setAmount(rs.getBigDecimal("amount"));
                a.setDescription(rs.getString("description"));
                return a;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}