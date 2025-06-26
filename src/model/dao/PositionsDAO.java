package model.dao;

import db.DBConnection;
import model.pojo.Positions;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.pojo.Allowance;

public class PositionsDAO {

    // Retrieve all positions
    public List<Positions> getAll() {
        List<Positions> positionsList = new ArrayList<>();
        String sql = "SELECT * FROM Positions ORDER BY positionID";

        try (Connection con = DBConnection.getConnection(); 
             PreparedStatement ps = con.prepareStatement(sql); 
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Positions pos = new Positions();
                pos.setPositionID(rs.getInt("positionID"));
                pos.setPositionTitle(rs.getString("positionTitle"));
                pos.setDepartmentID(rs.getInt("departmentID"));
                positionsList.add(pos);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return positionsList;
    }
    
    // Get department by position title
    public String getDepartmentNameByPositionTitle(String positionTitle) {
        String sql = """
        SELECT d.departmentName
        FROM Positions p
        JOIN Department d ON p.departmentID = d.departmentID
        WHERE p.positionTitle = ?
    """;

        try (Connection conn = DBConnection.getConnection(); 
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, positionTitle);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("departmentName");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
    
    // Get allowances by position title
    public List<Allowance> getAllowancesByPositionTitle(String positionTitle) {
        List<Allowance> allowances = new ArrayList<>();

        String sql = """
        SELECT a.allowanceID, a.allowanceTypeID, a.amount, a.description
        FROM Positions p
        JOIN PositionAllowance pa ON p.positionID = pa.positionID
        JOIN Allowance a ON pa.allowanceID = a.allowanceID
        WHERE p.positionTitle = ?
    """;

        try (Connection conn = DBConnection.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, positionTitle);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Allowance allowance = new Allowance();
                    allowance.setAllowanceID(rs.getInt("allowanceID"));
                    allowance.setAllowanceTypeID(rs.getInt("allowanceTypeID"));
                    allowance.setAmount(rs.getBigDecimal("amount"));
                    allowance.setDescription(rs.getString("description"));
                    allowances.add(allowance);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return allowances;
    }
    
    // Get positionID by position title
    public int getIdByTitle(String positionTitle) {
        String sql = "SELECT positionID FROM Positions WHERE positionTitle = ?";
        try (Connection conn = DBConnection.getConnection(); 
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, positionTitle);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("positionID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
}
