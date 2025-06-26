package model.dao;

import db.DBConnection;
import model.pojo.UserAccountView;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserAccountViewDAO {
    
    // Retrieve record from UserAccountView by userID
    public UserAccountView getById(int userID) {
        String query = "SELECT * FROM UserAccountView WHERE userID = ?";
        try (Connection conn = DBConnection.getConnection(); 
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, userID);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    UserAccountView account = new UserAccountView();
                    account.setUserID(rs.getInt("userID"));
                    account.setEmployeeID(rs.getInt("employeeID"));
                    account.setFullName(rs.getString("fullName"));
                    account.setUsername(rs.getString("username"));
                    account.setRoleName(rs.getString("roleName"));
                    account.setAccountStatus(rs.getString("accountStatus"));
                    return account;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    // Retrieve all records from the UserAccountView table
    public List<UserAccountView> getAll() {
        List<UserAccountView> list = new ArrayList<>();
        String query = "SELECT * FROM UserAccountView ORDER BY userID";

        try (Connection conn = DBConnection.getConnection(); 
             PreparedStatement ps = conn.prepareStatement(query); 
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                UserAccountView account = new UserAccountView();
                account.setUserID(rs.getInt("userID"));
                account.setEmployeeID(rs.getInt("employeeID"));
                account.setFullName(rs.getString("fullName"));
                account.setUsername(rs.getString("username"));
                account.setRoleName(rs.getString("roleName"));
                account.setAccountStatus(rs.getString("accountStatus"));

                list.add(account);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
    
}
