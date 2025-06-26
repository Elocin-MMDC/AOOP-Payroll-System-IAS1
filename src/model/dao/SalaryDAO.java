package model.dao;

import db.DBConnection;
import model.pojo.Salary;
import java.sql.*;

public class SalaryDAO {
    
    // Retrieve a salary record by ID
    public Salary getById(int salaryID) {
        String sql = "SELECT * FROM Salary WHERE salaryID = ?";
        try (Connection conn = DBConnection.getConnection(); 
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, salaryID);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Salary salary = new Salary();
                    salary.setSalaryID(rs.getInt("salaryID"));
                    salary.setBasicSalary(rs.getBigDecimal("basicSalary"));
                    salary.setSemiMonthlyRate(rs.getBigDecimal("semiMonthlyRate"));
                    salary.setHourlyRate(rs.getBigDecimal("hourlyRate"));

                    Timestamp created = rs.getTimestamp("createdAt");
                    Timestamp updated = rs.getTimestamp("updatedAt");
                    if (created != null) {
                        salary.setCreatedAt(created.toLocalDateTime());
                    }
                    if (updated != null) {
                        salary.setUpdatedAt(updated.toLocalDateTime());
                    }

                    return salary;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Insert new salary for new employee
    public int insert(Salary salary) {
        String sql = "INSERT INTO Salary (basicSalary, semiMonthlyRate, hourlyRate) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            ps.setBigDecimal(1, salary.getBasicSalary());
            ps.setBigDecimal(2, salary.getSemiMonthlyRate());
            ps.setBigDecimal(3, salary.getHourlyRate());

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
    
    // Update salary
    public boolean update(Salary salary) {
        String sql = "UPDATE Salary SET basicSalary = ?, semiMonthlyRate = ?, hourlyRate = ? WHERE salaryID = ?";
        try (Connection conn = DBConnection.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setBigDecimal(1, salary.getBasicSalary());
            stmt.setBigDecimal(2, salary.getSemiMonthlyRate());
            stmt.setBigDecimal(3, salary.getHourlyRate());
            stmt.setInt(4, salary.getSalaryID());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}