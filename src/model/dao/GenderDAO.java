package model.dao;

import db.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.pojo.Gender;

public class GenderDAO {

    // Retrive all genders
    public List<Gender> getAll() {
        List<Gender> genderList = new ArrayList<>();
        String sql = "SELECT * FROM Gender ORDER BY genderID";

        try (Connection con = DBConnection.getConnection(); 
             PreparedStatement ps = con.prepareStatement(sql); 
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Gender gender = new Gender();
                gender.setGenderID(rs.getInt("genderID"));
                gender.setGenderName(rs.getString("genderName"));
                genderList.add(gender);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return genderList;
    }

    // Retrive gender by ID
    public Gender getById(int genderID) {
        Gender gender = null;
        String sql = "SELECT * FROM Gender WHERE genderID = ?";

        try (Connection con = DBConnection.getConnection(); 
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, genderID);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                gender = new Gender();
                gender.setGenderID(rs.getInt("genderID"));
                gender.setGenderName(rs.getString("genderName"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return gender;
    }
    
    // Get genderID by genderName
    public int getIdByName(String genderName) {
        String sql = "SELECT genderID FROM Gender WHERE genderName = ?";
        try (Connection conn = DBConnection.getConnection(); 
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, genderName);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("genderID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }   
    
}
