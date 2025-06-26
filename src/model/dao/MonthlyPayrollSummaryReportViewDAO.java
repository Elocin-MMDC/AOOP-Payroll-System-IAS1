package model.dao;

import db.DBConnection;
import model.pojo.MonthlyPayrollSummaryReportView;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MonthlyPayrollSummaryReportViewDAO {
    
    // Retrieve report by pay period
    public List<MonthlyPayrollSummaryReportView> getByPayPeriod(LocalDate payStartDate, LocalDate payEndDate) {
        List<MonthlyPayrollSummaryReportView> records = new ArrayList<>();

        String sql = """
        SELECT * FROM MonthlyPayrollSummaryReportView
        WHERE payStartDate = ? AND payEndDate = ?
        ORDER BY employeeID
    """;

        try (Connection conn = DBConnection.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDate(1, Date.valueOf(payStartDate));
            stmt.setDate(2, Date.valueOf(payEndDate));

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    MonthlyPayrollSummaryReportView record = new MonthlyPayrollSummaryReportView();
                    record.setEmployeeID(rs.getInt("employeeID"));
                    record.setPayStartDate(rs.getDate("payStartDate").toLocalDate());
                    record.setPayEndDate(rs.getDate("payEndDate").toLocalDate());
                    record.setEmployeeName(rs.getString("employeeName"));
                    record.setPosition(rs.getString("position"));
                    record.setDepartment(rs.getString("department"));
                    record.setGrossIncome(rs.getBigDecimal("grossIncome"));
                    record.setSssNumber(rs.getString("sssNumber"));
                    record.setSssContribution(rs.getBigDecimal("sssContribution"));
                    record.setPhilHealthNumber(rs.getString("philHealthNumber"));
                    record.setPhilHealthContribution(rs.getBigDecimal("philHealthContribution"));
                    record.setPagIbigNumber(rs.getString("pagIbigNumber"));
                    record.setPagIbigContribution(rs.getBigDecimal("pagIbigContribution"));
                    record.setTin(rs.getString("tin"));
                    record.setWithholdingTax(rs.getBigDecimal("withholdingTax"));
                    record.setNetIncome(rs.getBigDecimal("netIncome"));
                    records.add(record);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return records;
    }
    
}
