package model.dao;

import db.DBConnection;
import model.pojo.UserAccount;
import util.PasswordCryptoUtil;
import util.PasswordUtil;
import java.sql.*;

public class UserAccountDAO {

    // Retrieve user account by ID
    public UserAccount getById(int userId) {
        String sql = "SELECT * FROM UserAccount WHERE userID = ?";
        try (Connection con = DBConnection.getConnection(); 
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return mapResultSetToUserAccount(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Retrieve user account by username
    public UserAccount getByUsername(String username) {
        String sql = "SELECT * FROM UserAccount WHERE username = ? AND accountStatus = 'Active'";
        try (Connection con = DBConnection.getConnection(); 
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return mapResultSetToUserAccount(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Insert new user account for the new employee
    public int insert(UserAccount user) {
        String sql = "INSERT INTO UserAccount (employeeID, username, password, passwordSalt, roleID, accountStatus) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            PasswordUtil.applyHashedPassword(user, user.getPassword());
            String hashedPassword = user.getPassword();
            String passwordSalt = user.getPasswordSalt();

            ps.setInt(1, user.getEmployeeID());
            ps.setString(2, user.getUsername());
            ps.setString(3, hashedPassword);
            ps.setString(4, passwordSalt);
            ps.setInt(5, user.getRoleID());
            ps.setString(6, user.getAccountStatus());
            int rows = ps.executeUpdate();
            if (rows > 0) {
                ResultSet keys = ps.getGeneratedKeys();
                if (keys.next()) {
                    return keys.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    // Update user account
    public boolean update(UserAccount user) {
        String sql = "UPDATE UserAccount SET username = ?, password = ?, passwordSalt = ?, roleID = ?, accountStatus = ?, updatedAt = NOW() WHERE userID = ?";
        try (Connection con = DBConnection.getConnection(); 
             PreparedStatement ps = con.prepareStatement(sql)) {

            String hashedPassword = user.getPassword();
            String passwordSalt = user.getPasswordSalt();
            
            ps.setString(1, user.getUsername());
            ps.setString(2, hashedPassword);
            ps.setString(3, passwordSalt);
            ps.setInt(4, user.getRoleID());
            ps.setString(5, user.getAccountStatus());
            ps.setInt(6, user.getUserID());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Map ResultSet row to UserAccount POJO
    private UserAccount mapResultSetToUserAccount(ResultSet rs) throws SQLException {
        UserAccount u = new UserAccount();
        u.setUserID(rs.getInt("userID"));
        u.setEmployeeID(rs.getInt("employeeID"));
        u.setUsername(rs.getString("username"));
        u.setPassword(rs.getString("password"));
        u.setRoleID(rs.getInt("roleID"));
        u.setAccountStatus(rs.getString("accountStatus"));
        u.setCreatedAt(rs.getTimestamp("createdAt").toLocalDateTime());
        u.setUpdatedAt(rs.getTimestamp("updatedAt").toLocalDateTime());
        Timestamp re = rs.getTimestamp("reactivatedAt");
        u.setReactivatedAt(re != null ? re.toLocalDateTime() : null);
        Timestamp de = rs.getTimestamp("deactivatedAt");
        u.setDeactivatedAt(de != null ? de.toLocalDateTime() : null);
        u.setMustChangePassword(rs.getBoolean("mustChangePassword"));
        u.setPasswordSalt(rs.getString("passwordSalt"));
        return u;
    }
    
    // Update user's username
    public boolean updateUsername(int userID, String newUsername) {
        String sql = "UPDATE UserAccount SET username = ?, updatedAt = NOW() WHERE userID = ?";
        try (Connection con = DBConnection.getConnection(); 
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setString(1, newUsername);
            ps.setInt(2, userID);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Update user's role
    public boolean updateRole(int userID, int newRoleID) {
        String sql = "UPDATE UserAccount SET roleID = ?, updatedAt = NOW() WHERE userID = ?";
        try (Connection con = DBConnection.getConnection(); 
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setInt(1, newRoleID);
            ps.setInt(2, userID);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Update user's account status
    public boolean updateStatus(int userID, String newStatus) {
        String sql = "UPDATE UserAccount SET accountStatus = ?, updatedAt = NOW() WHERE userID = ?";
        try (Connection con = DBConnection.getConnection(); 
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setString(1, newStatus);
            ps.setInt(2, userID);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Update user's mustChangePassword flag
    public boolean updateMustChangePasswordFlag(int userID, boolean mustChangePassword) {
        String sql = "UPDATE UserAccount SET mustChangePassword = ?, updatedAt = NOW() WHERE userID = ?";
        try (Connection con = DBConnection.getConnection(); 
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setBoolean(1, mustChangePassword);
            ps.setInt(2, userID);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Update user's password and set mustChangePassword flag to false
    public boolean updatePasswordAndClearMustChange(int userID, String newHashedPassword, String passwordSalt) {
        String sql = "UPDATE UserAccount SET password = ?, passwordSalt = ?, mustChangePassword = FALSE, updatedAt = NOW() WHERE userID = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, newHashedPassword);
            ps.setString(2, passwordSalt);
            ps.setInt(3, userID);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Update user's password and set mustChangePassword flag to true
    public boolean updatePasswordAndEnforceMustChange(int userID, String newHashedPassword, String passwordSalt) {
        String sql = "UPDATE UserAccount SET password = ?, passwordSalt = ?, mustChangePassword = TRUE, updatedAt = NOW() WHERE userID = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, newHashedPassword);
            ps.setString(2, passwordSalt);
            ps.setInt(3, userID);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Reset user's password
    public boolean resetPassword(int userID, String newHashedPassword, String passwordSalt) {
        String sql = "UPDATE UserAccount SET password = ?, passwordSalt = ?, updatedAt = NOW() WHERE userID = ?";
        try (Connection con = DBConnection.getConnection(); 
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setString(1, newHashedPassword);
            ps.setString(2, passwordSalt);
            ps.setInt(3, userID);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // Deactivate user's account by employee ID
    public boolean deactivateByEmployeeID(int employeeID) {
        String sql = "UPDATE UserAccount SET accountStatus = 'Deactivated', deactivatedAt = NOW(), updatedAt = NOW() WHERE employeeID = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, employeeID);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // Retrieve user ID by user's employee ID
    public int getUserIDByEmployeeID(int employeeID) {
        String sql = "SELECT userID FROM UserAccount WHERE employeeID = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, employeeID);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("userID");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
    
    // Checks if a username already exists in the database
    public boolean usernameExists(String username) {
        String sql = "SELECT COUNT(*) FROM UserAccount WHERE username = ?";
        try (Connection conn = DBConnection.getConnection(); 
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            return rs.next() && rs.getInt(1) > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return true;
        }
    }
    
    // Retrieve username associated with the given user ID
    public String getUsernameById(int userID) {
        String sql = "SELECT username FROM UserAccount WHERE userID = ?";
        try (Connection conn = DBConnection.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userID);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("username");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}