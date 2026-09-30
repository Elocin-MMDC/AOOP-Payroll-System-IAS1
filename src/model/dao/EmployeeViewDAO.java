package model.dao;

import db.DBConnection;
import model.pojo.EmployeeView;
import util.PiiCryptoUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeViewDAO {

    private static final String SSS_CONTEXT = "MotorPH:govinformation:sssNumber:v1";
    private static final String PHILHEALTH_CONTEXT = "MotorPH:govinformation:philHealthNumber:v1";
    private static final String TIN_CONTEXT = "MotorPH:govinformation:tin:v1";
    private static final String PAGIBIG_CONTEXT = "MotorPH:govinformation:pagIbigNumber:v1";

    // Retrieve all records from the EmployeeView table
    public List<EmployeeView> getAllEmployees() {
        List<EmployeeView> list = new ArrayList<>();
        String query = "SELECT * FROM EmployeeView WHERE isDeleted = FALSE";

        try (Connection conn = DBConnection.getConnection(); 
             PreparedStatement ps = conn.prepareStatement(query); 
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(mapResultSetToEmployee(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    // Retrieve employee record by ID
    public EmployeeView getById(int employeeID) {
        String query = "SELECT * FROM EmployeeView WHERE employeeID = ?";

        try (Connection conn = DBConnection.getConnection(); 
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, employeeID);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return mapResultSetToEmployee(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    // Map ResultSet row to EmployeeView POJO
    private EmployeeView mapResultSetToEmployee(ResultSet rs) throws SQLException {
        EmployeeView emp = new EmployeeView();
        emp.setEmployeeID(rs.getInt("employeeID"));
        emp.setLastName(rs.getString("lastName"));
        emp.setFirstName(rs.getString("firstName"));
        emp.setBirthday(rs.getDate("birthday").toLocalDate());
        emp.setPhoneNumber(rs.getString("phoneNumber"));
        emp.setGender(rs.getString("gender"));
        emp.setWorkStatus(rs.getString("workStatus"));
        emp.setDepartmentName(rs.getString("departmentName"));
        emp.setPositionTitle(rs.getString("positionTitle"));
        emp.setFullAddress(rs.getString("fullAddress"));
        emp.setSssNumber(PiiCryptoUtil.decrypt(rs.getString("sssNumber"), SSS_CONTEXT));
        emp.setPhilHealthNumber(PiiCryptoUtil.decrypt(rs.getString("philHealthNumber"), PHILHEALTH_CONTEXT));
        emp.setTin(PiiCryptoUtil.decrypt(rs.getString("tin"), TIN_CONTEXT));
        emp.setPagIbigNumber(PiiCryptoUtil.decrypt(rs.getString("pagIbigNumber"), PAGIBIG_CONTEXT));
        emp.setBasicSalary(rs.getBigDecimal("basicSalary"));
        emp.setSemiMonthlyRate(rs.getBigDecimal("semiMonthlyRate"));
        emp.setHourlyRate(rs.getBigDecimal("hourlyRate"));
        emp.setSupervisorName(rs.getString("supervisorName"));
        emp.setRole(rs.getString("role"));
        emp.setRiceSubsidy(rs.getBigDecimal("riceSubsidy"));
        emp.setPhoneAllowance(rs.getBigDecimal("phoneAllowance"));
        emp.setClothingAllowance(rs.getBigDecimal("clothingAllowance"));
        emp.setIsDeleted(rs.getBoolean("isDeleted"));
        return emp;
    }
}
