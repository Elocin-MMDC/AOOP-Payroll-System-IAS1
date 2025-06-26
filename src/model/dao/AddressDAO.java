package model.dao;

import db.DBConnection;
import model.pojo.Address;
import java.sql.*;

public class AddressDAO {

    // Retrieve address by ID
    public Address getById(int id) {
        Address address = null;
        String sql = "SELECT * FROM Address WHERE addressID = ?";

        try (Connection con = DBConnection.getConnection(); 
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                address = new Address();
                address.setAddressID(rs.getInt("addressID"));
                address.setStreet(rs.getString("street"));
                address.setBarangay(rs.getString("barangay"));
                address.setCity(rs.getString("city"));
                address.setProvince(rs.getString("province"));
                address.setZipCode(rs.getString("zipCode"));
                address.setCreatedAt(rs.getTimestamp("createdAt").toLocalDateTime());
                address.setUpdatedAt(rs.getTimestamp("updatedAt").toLocalDateTime());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return address;
    }
    
    // Update address
    public boolean update(Address address) {
        String sql = "UPDATE Address SET street = ?, barangay = ?, city = ?, province = ?, zipCode = ? WHERE addressID = ?";

        try (Connection conn = DBConnection.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, address.getStreet());

            if (address.getBarangay() != null) {
                stmt.setString(2, address.getBarangay());
            } else {
                stmt.setNull(2, Types.VARCHAR);
            }

            stmt.setString(3, address.getCity());

            if (address.getProvince() != null) {
                stmt.setString(4, address.getProvince());
            } else {
                stmt.setNull(4, Types.VARCHAR);
            }

            if (address.getZipCode() != null) {
                stmt.setString(5, address.getZipCode());
            } else {
                stmt.setNull(5, Types.VARCHAR);
            }

            stmt.setInt(6, address.getAddressID());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // Insert address
    public int insert(Address address) {
        String sql = "INSERT INTO Address (street, barangay, city, province, zipCode) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection(); 
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            ps.setString(1, address.getStreet());
            ps.setString(2, address.getBarangay());
            ps.setString(3, address.getCity());
            ps.setString(4, address.getProvince());
            ps.setString(5, address.getZipCode());

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
}