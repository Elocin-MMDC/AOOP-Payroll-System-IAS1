package model.dao;

import java.sql.*;
import model.pojo.PayslipView;
import db.DBConnection;

public class PayslipViewDAO {

    // Retrieve payslip by ID for payslip generation
    public PayslipView getById(int payslipID) {
        String sql = "SELECT * FROM PayslipView WHERE payslipID = ?";
        try (Connection conn = DBConnection.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, payslipID);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    PayslipView view = new PayslipView();
                    view.setPayslipID(rs.getInt("payslipID"));
                    view.setEmployeeID(rs.getInt("employeeID"));
                    view.setEmployeeName(rs.getString("employeeName"));
                    view.setPayStartDate(rs.getString("payStartDate"));
                    view.setPayEndDate(rs.getString("payEndDate"));
                    view.setEmployeePositionDepartment(rs.getString("employeePositionDepartment"));
                    view.setMonthlyRate(rs.getBigDecimal("monthlyRate"));
                    view.setHourlyRate(rs.getBigDecimal("hourlyRate"));
                    view.setRegularHours(rs.getBigDecimal("regularHours"));
                    view.setOvertimeHours(rs.getBigDecimal("overtimeHours"));
                    view.setGrossIncome(rs.getBigDecimal("grossIncome"));
                    view.setRiceSubsidy(rs.getBigDecimal("riceSubsidy"));
                    view.setPhoneAllowance(rs.getBigDecimal("phoneAllowance"));
                    view.setClothingAllowance(rs.getBigDecimal("clothingAllowance"));
                    view.setTotalBenefits(rs.getBigDecimal("totalBenefits"));
                    view.setSssContribution(rs.getBigDecimal("sssContribution"));
                    view.setPhilHealthContribution(rs.getBigDecimal("philHealthContribution"));
                    view.setPagIbigContribution(rs.getBigDecimal("pagIbigContribution"));
                    view.setWithholdingTax(rs.getBigDecimal("withholdingTax"));
                    view.setTotalDeductions(rs.getBigDecimal("totalDeductions"));
                    view.setNetIncome(rs.getBigDecimal("netIncome"));
                    return view;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
}
