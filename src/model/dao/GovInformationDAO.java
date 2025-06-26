package model.dao;

import db.DBConnection;
import model.pojo.GovInformation;
import java.sql.*;

public class GovInformationDAO {

    // Retrieve government info by ID
    public GovInformation getById(int id) {
        GovInformation gov = null;
        String sql = "SELECT * FROM GovInformation WHERE govID = ?";

        try (Connection con = DBConnection.getConnection(); 
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                gov = new GovInformation();
                gov.setGovID(rs.getInt("govID"));
                gov.setSssNumber(rs.getString("sssNumber"));
                gov.setPhilHealthNumber(rs.getString("philHealthNumber"));
                gov.setTin(rs.getString("tin"));
                gov.setPagIbigNumber(rs.getString("pagIbigNumber"));
                gov.setCreatedAt(rs.getTimestamp("createdAt").toLocalDateTime());
                gov.setUpdatedAt(rs.getTimestamp("updatedAt").toLocalDateTime());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return gov;
    }
    
    // Insert new GovernmentInfo for the new employee
    public int insert(GovInformation gov) {
        String sql = "INSERT INTO GovInformation (sssNumber, philHealthNumber, tin, pagIbigNumber) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection(); 
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            ps.setString(1, gov.getSssNumber());
            ps.setString(2, gov.getPhilHealthNumber());
            ps.setString(3, gov.getTin());
            ps.setString(4, gov.getPagIbigNumber());

            int rows = ps.executeUpdate();
            if (rows > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
    
    // Update GovernmentInfo for the existing employee
    public boolean update(GovInformation govInfo) {
        String sql = "UPDATE GovInformation SET sssNumber = ?, philHealthNumber = ?, tin = ?, pagIbigNumber = ? WHERE govID = ?";

        try (Connection conn = DBConnection.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, govInfo.getSssNumber());
            stmt.setString(2, govInfo.getPhilHealthNumber());
            stmt.setString(3, govInfo.getTin());
            stmt.setString(4, govInfo.getPagIbigNumber());
            stmt.setInt(5, govInfo.getGovID());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // Check if a record exists with the given SSS number
    public boolean existsBySSSNumber(String sssNumber) {
        String sql = "SELECT COUNT(*) FROM GovInformation WHERE sssNumber = ?";
        try (Connection conn = DBConnection.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, sssNumber);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    // Check if a record exists with the given PhilHealth number
    public boolean existsByPhilHealthNumber(String philHealthNumber) {
        String sql = "SELECT COUNT(*) FROM GovInformation WHERE philHealthNumber = ?";
        try (Connection conn = DBConnection.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, philHealthNumber);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Check if a record exists with the given TIN
    public boolean existsByTIN(String tin) {
        String sql = "SELECT COUNT(*) FROM GovInformation WHERE tin = ?";
        try (Connection conn = DBConnection.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, tin);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Check if a record exists with the given Pag-Ibig number
    public boolean existsByPagIbigNumber(String pagIbigNumber) {
        String sql = "SELECT COUNT(*) FROM GovInformation WHERE pagIbigNumber = ?";
        try (Connection conn = DBConnection.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, pagIbigNumber);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    // Check if an SSS number exists, excluding a specific govID (for update validation)
    public boolean existsBySSSNumberExcept(String sssNumber, int excludeGovID) {
        String sql = "SELECT COUNT(*) FROM GovInformation WHERE sssNumber = ? AND govID <> ?";
        try (Connection con = DBConnection.getConnection(); 
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setString(1, sssNumber);
            ps.setInt(2, excludeGovID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Check if a PhilHealth number exists, excluding a specific govID (for update validation)
    public boolean existsByPhilHealthNumberExcept(String philHealth, int excludeGovID) {
        String sql = "SELECT COUNT(*) FROM GovInformation WHERE philHealthNumber = ? AND govID <> ?";
        try (Connection con = DBConnection.getConnection(); 
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setString(1, philHealth);
            ps.setInt(2, excludeGovID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Check if TIN exists, excluding a specific govID (for update validation)
    public boolean existsByTINExcept(String tin, int excludeGovID) {
        String sql = "SELECT COUNT(*) FROM GovInformation WHERE tin = ? AND govID <> ?";
        try (Connection con = DBConnection.getConnection(); 
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setString(1, tin);
            ps.setInt(2, excludeGovID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Check if a Pag-Ibig number exists, excluding a specific govID (for update validation)
    public boolean existsByPagIbigNumberExcept(String pagIbig, int excludeGovID) {
        String sql = "SELECT COUNT(*) FROM GovInformation WHERE pagIbigNumber = ? AND govID <> ?";
        try (Connection con = DBConnection.getConnection(); 
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setString(1, pagIbig);
            ps.setInt(2, excludeGovID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}