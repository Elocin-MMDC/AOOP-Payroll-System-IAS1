package model.dao;

import db.DBConnection;
import model.pojo.PayrollProcess;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PayrollProcessDAO {

    // Inserts a new record and returns the generated payrollProcessID
    public int insertAndReturnID(PayrollProcess process) {
        String sql = """
            INSERT INTO PayrollProcess (processedBy, dateProcessed, payStartDate, payEndDate, createdAt, updatedAt)
            VALUES (?, ?, ?, ?, ?, ?)
        """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, process.getProcessedBy());
            stmt.setDate(2, Date.valueOf(process.getDateProcessed()));
            stmt.setDate(3, Date.valueOf(process.getPayStartDate()));
            stmt.setDate(4, Date.valueOf(process.getPayEndDate()));
            stmt.setTimestamp(5, Timestamp.valueOf(process.getCreatedAt()));
            stmt.setTimestamp(6, Timestamp.valueOf(process.getUpdatedAt()));

            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                System.err.println("Failed to insert payroll process. No rows affected.");
                return -1;
            }

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                } else {
                    System.err.println("Failed to retrieve generated payrollProcessID.");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }

    // Checks if payroll has already been processed for a given pay period
    public boolean existsForPeriod(LocalDate startDate, LocalDate endDate) {
        String sql = """
            SELECT COUNT(*) FROM PayrollProcess
            WHERE payStartDate = ? AND payEndDate = ?
        """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDate(1, Date.valueOf(startDate));
            stmt.setDate(2, Date.valueOf(endDate));

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    
    // Retrieve all available pay periods for exporting
    public List<PayrollProcess> getAllPayPeriods() {
        List<PayrollProcess> periods = new ArrayList<>();
        String sql = "SELECT payStartDate, payEndDate FROM PayrollProcess ORDER BY payEndDate DESC";

        try (Connection conn = DBConnection.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(sql); 
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                PayrollProcess process = new PayrollProcess();
                process.setPayStartDate(rs.getDate("payStartDate").toLocalDate());
                process.setPayEndDate(rs.getDate("payEndDate").toLocalDate());
                periods.add(process);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return periods;
    }
}
