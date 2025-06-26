package model.dao;

import db.DBConnection;
import model.pojo.Payslip;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PayslipDAO {

    // Retrieve all payslips
    public List<Payslip> getAll() {
        List<Payslip> list = new ArrayList<>();
        String sql = "SELECT * FROM Payslip ORDER BY payslipID DESC";
        try (Connection conn = DBConnection.getConnection(); 
             Statement stmt = conn.createStatement()) {
            
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                list.add(mapResultSetToPayslip(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    // Retrieve all payslips of an employee
    public List<Payslip> getByEmployeeId(int employeeID) {
        List<Payslip> list = new ArrayList<>();
        String sql = "SELECT * FROM Payslip WHERE employeeID = ? ORDER BY payslipID DESC";

        try (Connection conn = DBConnection.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, employeeID);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    list.add(mapResultSetToPayslip(rs));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
    
    // Insert a new payslip
    public boolean insert(Payslip payslip) {
        String sql = "INSERT INTO Payslip (employeeID, payrollProcessID, payStartDate, payEndDate, dateReleased, employeeName, employeePosition, employeeDepartment, basicSalary, hourlyRate, daysWorked, regularHours, overtimeHours, grossIncome, riceSubsidy, phoneAllowance, clothingAllowance, totalBenefits, sssContribution, philHealthContribution, pagIbigContribution, withholdingTax, totalDeductions, netIncome, createdAt, updatedAt) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, payslip.getEmployeeID());
            stmt.setInt(2, payslip.getPayrollProcessID());
            stmt.setDate(3, Date.valueOf(payslip.getPayStartDate()));
            stmt.setDate(4, Date.valueOf(payslip.getPayEndDate()));
            stmt.setDate(5, Date.valueOf(payslip.getDateReleased()));
            stmt.setString(6, payslip.getEmployeeName());
            stmt.setString(7, payslip.getEmployeePosition());
            stmt.setString(8, payslip.getEmployeeDepartment());
            stmt.setBigDecimal(9, payslip.getBasicSalary());
            stmt.setBigDecimal(10, payslip.getHourlyRate());
            stmt.setInt(11, payslip.getDaysWorked());
            stmt.setBigDecimal(12, payslip.getRegularHours());
            stmt.setBigDecimal(13, payslip.getOvertimeHours());
            stmt.setBigDecimal(14, payslip.getGrossIncome());
            stmt.setBigDecimal(15, payslip.getRiceSubsidy());
            stmt.setBigDecimal(16, payslip.getPhoneAllowance());
            stmt.setBigDecimal(17, payslip.getClothingAllowance());
            stmt.setBigDecimal(18, payslip.getTotalBenefits());
            stmt.setBigDecimal(19, payslip.getSssContribution());
            stmt.setBigDecimal(20, payslip.getPhilHealthContribution());
            stmt.setBigDecimal(21, payslip.getPagIbigContribution());
            stmt.setBigDecimal(22, payslip.getWithholdingTax());
            stmt.setBigDecimal(23, payslip.getTotalDeductions());
            stmt.setBigDecimal(24, payslip.getNetIncome());
            stmt.setTimestamp(25, Timestamp.valueOf(payslip.getCreatedAt()));
            stmt.setTimestamp(26, Timestamp.valueOf(payslip.getUpdatedAt()));
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Map ResultSet row to Payslip POJO
    private Payslip mapResultSetToPayslip(ResultSet rs) throws SQLException {
        Payslip p = new Payslip();
        p.setPayslipID(rs.getInt("payslipID"));
        p.setEmployeeID(rs.getInt("employeeID"));
        p.setPayrollProcessID(rs.getInt("payrollProcessID"));
        p.setPayStartDate(rs.getDate("payStartDate").toLocalDate());
        p.setPayEndDate(rs.getDate("payEndDate").toLocalDate());
        p.setDateReleased(rs.getDate("dateReleased").toLocalDate());
        p.setEmployeeName(rs.getString("employeeName"));
        p.setEmployeePosition(rs.getString("employeePosition"));
        p.setEmployeeDepartment(rs.getString("employeeDepartment"));
        p.setBasicSalary(rs.getBigDecimal("basicSalary"));
        p.setHourlyRate(rs.getBigDecimal("hourlyRate"));
        p.setDaysWorked(rs.getInt("daysWorked"));
        p.setRegularHours(rs.getBigDecimal("regularHours"));
        p.setOvertimeHours(rs.getBigDecimal("overtimeHours"));
        p.setGrossIncome(rs.getBigDecimal("grossIncome"));
        p.setRiceSubsidy(rs.getBigDecimal("riceSubsidy"));
        p.setPhoneAllowance(rs.getBigDecimal("phoneAllowance"));
        p.setClothingAllowance(rs.getBigDecimal("clothingAllowance"));
        p.setTotalBenefits(rs.getBigDecimal("totalBenefits"));
        p.setSssContribution(rs.getBigDecimal("sssContribution"));
        p.setPhilHealthContribution(rs.getBigDecimal("philHealthContribution"));
        p.setPagIbigContribution(rs.getBigDecimal("pagIbigContribution"));
        p.setWithholdingTax(rs.getBigDecimal("withholdingTax"));
        p.setTotalDeductions(rs.getBigDecimal("totalDeductions"));
        p.setNetIncome(rs.getBigDecimal("netIncome"));
        p.setCreatedAt(rs.getTimestamp("createdAt").toLocalDateTime());
        p.setUpdatedAt(rs.getTimestamp("updatedAt").toLocalDateTime());
        return p;
    }
}
