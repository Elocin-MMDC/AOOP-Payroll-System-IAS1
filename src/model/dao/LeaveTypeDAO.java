package model.dao;

import db.DBConnection;
import model.pojo.LeaveType;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LeaveTypeDAO {

    // Retrieve all leave types
    public List<LeaveType> getAll() {
        List<LeaveType> list = new ArrayList<>();
        String sql = "SELECT * FROM LeaveType";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement(); 
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                LeaveType leaveType = new LeaveType();
                leaveType.setLeaveTypeID(rs.getInt("leaveTypeID"));
                leaveType.setLeaveName(rs.getString("leaveName"));
                leaveType.setEntitlementDays(rs.getInt("entitlementDays"));
                list.add(leaveType);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
