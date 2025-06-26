package model.dao;

import db.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.pojo.EmployeeViewForHR;

public class EmployeeViewForHRDAO {

    // Retrieve all employees for HR View
    public List<EmployeeViewForHR> getAllEmployees() {
        List<EmployeeViewForHR> list = new ArrayList<>();
        String query = "SELECT * FROM EmployeeViewForHR WHERE isDeleted = FALSE";

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

    // Retrive employee by ID
    public EmployeeViewForHR getById(int employeeID) {
        String query = "SELECT * FROM EmployeeViewForHR WHERE employeeID = ?";

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

    // Map ResultSet row to EmployeeViewForHR POJO
    private EmployeeViewForHR mapResultSetToEmployee(ResultSet rs) throws SQLException {
        EmployeeViewForHR emp = new EmployeeViewForHR();
        
        emp.setEmployeeID(rs.getInt("employeeID"));
        emp.setLastName(rs.getString("lastName"));
        emp.setFirstName(rs.getString("firstName"));
        emp.setBirthday(rs.getDate("birthday").toLocalDate());
        emp.setPhoneNumber(rs.getString("phoneNumber"));
        emp.setGender(rs.getString("gender"));
        emp.setWorkStatus(rs.getString("workStatus"));
        emp.setDepartmentName(rs.getString("departmentName"));
        emp.setPositionTitle(rs.getString("positionTitle"));

        emp.setStreet(rs.getString("street"));
        emp.setBarangay(rs.getString("barangay"));
        emp.setCity(rs.getString("city"));
        emp.setProvince(rs.getString("province"));
        emp.setZipCode(rs.getString("zipCode"));

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
