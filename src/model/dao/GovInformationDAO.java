package model.dao;

import db.DBConnection;
import model.pojo.GovInformation;
import util.PiiCryptoUtil;
import java.sql.*;

public class GovInformationDAO {

    private static final String SSS_CONTEXT = "MotorPH:govinformation:sssNumber:v1";
    private static final String PHILHEALTH_CONTEXT = "MotorPH:govinformation:philHealthNumber:v1";
    private static final String TIN_CONTEXT = "MotorPH:govinformation:tin:v1";
    private static final String PAGIBIG_CONTEXT = "MotorPH:govinformation:pagIbigNumber:v1";

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
                gov.setSssNumber(PiiCryptoUtil.decrypt(rs.getString("sssNumber"), SSS_CONTEXT));
                gov.setPhilHealthNumber(PiiCryptoUtil.decrypt(rs.getString("philHealthNumber"), PHILHEALTH_CONTEXT));
                gov.setTin(PiiCryptoUtil.decrypt(rs.getString("tin"), TIN_CONTEXT));
                gov.setPagIbigNumber(PiiCryptoUtil.decrypt(rs.getString("pagIbigNumber"), PAGIBIG_CONTEXT));
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
        String sql = "INSERT INTO GovInformation (sssNumber, sssNumberLookup, philHealthNumber, philHealthNumberLookup, tin, tinLookup, pagIbigNumber, pagIbigNumberLookup) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection(); 
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            ps.setString(1, PiiCryptoUtil.encrypt(gov.getSssNumber(), SSS_CONTEXT));
            ps.setBytes(2, PiiCryptoUtil.lookupHmac(gov.getSssNumber(), SSS_CONTEXT));
            ps.setString(3, PiiCryptoUtil.encrypt(gov.getPhilHealthNumber(), PHILHEALTH_CONTEXT));
            ps.setBytes(4, PiiCryptoUtil.lookupHmac(gov.getPhilHealthNumber(), PHILHEALTH_CONTEXT));
            ps.setString(5, PiiCryptoUtil.encrypt(gov.getTin(), TIN_CONTEXT));
            ps.setBytes(6, PiiCryptoUtil.lookupHmac(gov.getTin(), TIN_CONTEXT));
            ps.setString(7, PiiCryptoUtil.encrypt(gov.getPagIbigNumber(), PAGIBIG_CONTEXT));
            ps.setBytes(8, PiiCryptoUtil.lookupHmac(gov.getPagIbigNumber(), PAGIBIG_CONTEXT));

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
        String sql = "UPDATE GovInformation SET sssNumber = ?, sssNumberLookup = ?, philHealthNumber = ?, philHealthNumberLookup = ?, tin = ?, tinLookup = ?, pagIbigNumber = ?, pagIbigNumberLookup = ? WHERE govID = ?";

        try (Connection conn = DBConnection.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, PiiCryptoUtil.encrypt(govInfo.getSssNumber(), SSS_CONTEXT));
            stmt.setBytes(2, PiiCryptoUtil.lookupHmac(govInfo.getSssNumber(), SSS_CONTEXT));
            stmt.setString(3, PiiCryptoUtil.encrypt(govInfo.getPhilHealthNumber(), PHILHEALTH_CONTEXT));
            stmt.setBytes(4, PiiCryptoUtil.lookupHmac(govInfo.getPhilHealthNumber(), PHILHEALTH_CONTEXT));
            stmt.setString(5, PiiCryptoUtil.encrypt(govInfo.getTin(), TIN_CONTEXT));
            stmt.setBytes(6, PiiCryptoUtil.lookupHmac(govInfo.getTin(), TIN_CONTEXT));
            stmt.setString(7, PiiCryptoUtil.encrypt(govInfo.getPagIbigNumber(), PAGIBIG_CONTEXT));
            stmt.setBytes(8, PiiCryptoUtil.lookupHmac(govInfo.getPagIbigNumber(), PAGIBIG_CONTEXT));
            stmt.setInt(9, govInfo.getGovID());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // Check if a record exists with the given SSS number
    public boolean existsBySSSNumber(String sssNumber) {
        String sql = "SELECT COUNT(*) FROM GovInformation WHERE sssNumberLookup = ?";
        try (Connection conn = DBConnection.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setBytes(1, PiiCryptoUtil.lookupHmac(sssNumber, SSS_CONTEXT));
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
        String sql = "SELECT COUNT(*) FROM GovInformation WHERE philHealthNumberLookup = ?";
        try (Connection conn = DBConnection.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setBytes(1, PiiCryptoUtil.lookupHmac(philHealthNumber, PHILHEALTH_CONTEXT));
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
        String sql = "SELECT COUNT(*) FROM GovInformation WHERE tinLookup = ?";
        try (Connection conn = DBConnection.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setBytes(1, PiiCryptoUtil.lookupHmac(tin, TIN_CONTEXT));
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
        String sql = "SELECT COUNT(*) FROM GovInformation WHERE pagIbigNumberLookup = ?";
        try (Connection conn = DBConnection.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setBytes(1, PiiCryptoUtil.lookupHmac(pagIbigNumber, PAGIBIG_CONTEXT));
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
        String sql = "SELECT COUNT(*) FROM GovInformation WHERE sssNumberLookup = ? AND govID <> ?";
        try (Connection con = DBConnection.getConnection(); 
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setBytes(1, PiiCryptoUtil.lookupHmac(sssNumber, SSS_CONTEXT));
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
        String sql = "SELECT COUNT(*) FROM GovInformation WHERE philHealthNumberLookup = ? AND govID <> ?";
        try (Connection con = DBConnection.getConnection(); 
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setBytes(1, PiiCryptoUtil.lookupHmac(philHealth, PHILHEALTH_CONTEXT));
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
        String sql = "SELECT COUNT(*) FROM GovInformation WHERE tinLookup = ? AND govID <> ?";
        try (Connection con = DBConnection.getConnection(); 
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setBytes(1, PiiCryptoUtil.lookupHmac(tin, TIN_CONTEXT));
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
        String sql = "SELECT COUNT(*) FROM GovInformation WHERE pagIbigNumberLookup = ? AND govID <> ?";
        try (Connection con = DBConnection.getConnection(); 
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setBytes(1, PiiCryptoUtil.lookupHmac(pagIbig, PAGIBIG_CONTEXT));
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