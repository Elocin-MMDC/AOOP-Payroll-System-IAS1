package model.dao;

import db.DBConnection;
import model.pojo.EmployeeView;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeViewDAO {

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
        emp.setSssNumber(rs.getString("sssNumber"));
        emp.setPhilHealthNumber(rs.getString("philHealthNumber"));
        emp.setTin(rs.getString("tin"));
        emp.setPagIbigNumber(rs.getString("pagIbigNumber"));
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