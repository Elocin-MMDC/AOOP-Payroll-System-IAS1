package model.dao;

import db.DBConnection;
import model.pojo.WorkStatus;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WorkStatusDAO {

    // Retrieve all work statuses
    public List<WorkStatus> getAll() {
        List<WorkStatus> list = new ArrayList<>();
        String sql = "SELECT * FROM WorkStatus ORDER BY workStatusID";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                WorkStatus ws = new WorkStatus();
                ws.setWorkStatusID(rs.getInt("workStatusID"));
                ws.setWorkStatusName(rs.getString("workStatusName"));
                ws.setDescription(rs.getString("description"));
                list.add(ws);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
    
    // Get ID by workstatus name
    public int getIdByName(String workStatusName) {
        String sql = "SELECT workStatusID FROM WorkStatus WHERE workStatusName = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, workStatusName);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("workStatusID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
    
}
