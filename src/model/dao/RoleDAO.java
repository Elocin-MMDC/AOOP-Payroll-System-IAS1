package model.dao;

import db.DBConnection;
import model.pojo.Role;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoleDAO {

    // Retrieve all roles
    public List<Role> getAllRoles() {
        List<Role> roleList = new ArrayList<>();
        String sql = "SELECT * FROM Role ORDER BY roleID";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Role role = new Role();
                role.setRoleID(rs.getInt("roleID"));
                role.setRoleName(rs.getString("roleName"));
                role.setDescription(rs.getString("description"));
                roleList.add(role);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return roleList;
    }

    // Get Role by ID
    public Role getById(int roleID) {
        Role role = null;
        String sql = "SELECT * FROM Role WHERE roleID = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, roleID);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                role = new Role();
                role.setRoleID(rs.getInt("roleID"));
                role.setRoleName(rs.getString("roleName"));
                role.setDescription(rs.getString("description"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return role;
    }
    
    // Retrieve role ID by role name
    public int getIdByName(String roleName) {
        String sql = "SELECT roleID FROM Role WHERE roleName = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, roleName);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("roleID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
}
