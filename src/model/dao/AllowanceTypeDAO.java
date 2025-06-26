package model.dao;

import db.DBConnection;
import model.pojo.AllowanceType;
import java.sql.*;

public class AllowanceTypeDAO {

    // Retrieve allowance type by ID
    public AllowanceType getById(int id) {
        String sql = "SELECT * FROM AllowanceType WHERE allowanceTypeID = ?";
        try (Connection conn = DBConnection.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                AllowanceType type = new AllowanceType();
                type.setAllowanceTypeID(rs.getInt("allowanceTypeID"));
                type.setAllowanceName(rs.getString("allowanceName"));
                return type;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
