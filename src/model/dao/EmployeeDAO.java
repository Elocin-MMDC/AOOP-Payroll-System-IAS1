package model.dao;

import db.DBConnection;
import model.pojo.Employee;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {
    
    // Retieve employee by ID
    public Employee getById(int employeeID) {
        String sql = "SELECT * FROM Employee WHERE employeeID = ?";

        try (Connection con = DBConnection.getConnection(); 
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, employeeID);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Employee emp = new Employee();
                emp.setEmployeeID(rs.getInt("employeeID"));
                emp.setLastName(rs.getString("lastName"));
                emp.setFirstName(rs.getString("firstName"));
                emp.setBirthday(rs.getDate("birthday").toLocalDate());
                emp.setPhoneNumber(rs.getString("phoneNumber"));
                emp.setGenderID(rs.getInt("genderID"));
                emp.setAddressID(rs.getInt("addressID"));
                emp.setWorkStatusID(rs.getInt("workStatusID"));
                emp.setPositionID(rs.getInt("positionID"));
                emp.setGovID(rs.getInt("govID"));
                emp.setSalaryID(rs.getInt("salaryID"));

                int supervisorID = rs.getInt("supervisorID");
                if (!rs.wasNull()) {
                    emp.setSupervisorID(supervisorID);
                } else {
                    emp.setSupervisorID(null);
                }

                emp.setCreatedAt(rs.getTimestamp("createdAt").toLocalDateTime());
                emp.setUpdatedAt(rs.getTimestamp("updatedAt").toLocalDateTime());
                emp.setIsDeleted(rs.getBoolean("isDeleted"));

                Timestamp deletedAt = rs.getTimestamp("deletedAt");
                if (deletedAt != null) {
                    emp.setDeletedAt(deletedAt.toLocalDateTime());
                }

                return emp;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    // Retrive all employees
    public List<Employee> getAll() {
        List<Employee> employees = new ArrayList<>();
        String sql = "SELECT * FROM Employee WHERE isDeleted = FALSE";

        try (Connection con = DBConnection.getConnection(); 
             Statement stmt = con.createStatement(); 
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                employees.add(mapEmployee(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return employees;
    }

    // Soft delete employee
    public boolean softDelete(int employeeID) {
        String sql = """
        UPDATE Employee 
        SET isDeleted = TRUE, deletedAt = CURRENT_TIMESTAMP, updatedAt = CURRENT_TIMESTAMP 
        WHERE employeeID = ?
    """;

        try (Connection conn = DBConnection.getConnection(); 
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, employeeID);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // Undelete employee
    public boolean setIsDeletedFalse(int employeeID) {
        String sql = "UPDATE Employee SET isDeleted = FALSE, deletedAt = NULL, updatedAt = CURRENT_TIMESTAMP WHERE employeeID = ?";
        try (Connection conn = DBConnection.getConnection(); 
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, employeeID);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // Count total employees
    public long countAll() {
        String sql = "SELECT COUNT(*) FROM Employee WHERE isDeleted = FALSE";

        try (Connection conn = DBConnection.getConnection(); 
             PreparedStatement ps = conn.prepareStatement(sql); 
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                return rs.getLong(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }
    
    // Map ResultSet to Employee POJO
    private Employee mapEmployee(ResultSet rs) throws SQLException {
        Employee emp = new Employee();
        emp.setEmployeeID(rs.getInt("employeeID"));
        emp.setLastName(rs.getString("lastName"));
        emp.setFirstName(rs.getString("firstName"));
        emp.setBirthday(rs.getDate("birthday").toLocalDate());
        emp.setPhoneNumber(rs.getString("phoneNumber"));
        emp.setGenderID(rs.getInt("genderID"));
        emp.setAddressID(rs.getInt("addressID"));
        emp.setWorkStatusID(rs.getInt("workStatusID"));
        emp.setPositionID(rs.getInt("positionID"));
        emp.setGovID(rs.getInt("govID"));
        emp.setSalaryID(rs.getInt("salaryID"));
        emp.setSupervisorID(rs.getObject("supervisorID") != null ? rs.getInt("supervisorID") : null);
        emp.setCreatedAt(rs.getTimestamp("createdAt").toLocalDateTime());
        emp.setUpdatedAt(rs.getTimestamp("updatedAt").toLocalDateTime());
        emp.setIsDeleted(rs.getBoolean("isDeleted"));

        Timestamp deletedAt = rs.getTimestamp("deletedAt");
        emp.setDeletedAt(deletedAt != null ? deletedAt.toLocalDateTime() : null);

        return emp;
    }
    
    // Get next Employee ID for creating new employee
    public int getNextEmployeeId() {
        String sql = "SELECT MAX(employeeID) AS max_id FROM Employee";
        try (Connection conn = DBConnection.getConnection(); 
             PreparedStatement ps = conn.prepareStatement(sql); 
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                int maxId = rs.getInt("max_id");
                return maxId + 1;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }

    // Get all names
    public List<Employee> getAllSupervisors() {
        List<Employee> list = new ArrayList<>();
        String sql = "SELECT employeeID, firstName, lastName FROM Employee";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql); 
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Employee emp = new Employee();
                emp.setEmployeeID(rs.getInt("employeeID"));
                emp.setFirstName(rs.getString("firstName"));
                emp.setLastName(rs.getString("lastName"));
                list.add(emp);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
    
    // Get employee's ID by full name
    public int getIdByFullName(String fullName) {
        String sql = "SELECT employeeID FROM Employee WHERE CONCAT(firstName, ' ', lastName) = ?";
        try (Connection conn = DBConnection.getConnection(); 
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, fullName);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("employeeID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
    
    // Insert new employee
    public int insert(Employee emp) {
        String sql = "INSERT INTO Employee (lastName, firstName, birthday, phoneNumber, genderID, addressID, workStatusID, positionID, govID, salaryID, supervisorID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection(); 
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            ps.setString(1, emp.getLastName());
            ps.setString(2, emp.getFirstName());
            ps.setDate(3, Date.valueOf(emp.getBirthday()));
            ps.setString(4, emp.getPhoneNumber());
            ps.setInt(5, emp.getGenderID());
            ps.setInt(6, emp.getAddressID());
            ps.setInt(7, emp.getWorkStatusID());
            ps.setInt(8, emp.getPositionID());
            ps.setInt(9, emp.getGovID());
            ps.setInt(10, emp.getSalaryID());
            if (emp.getSupervisorID() != null) {
                ps.setInt(11, emp.getSupervisorID());
            } else {
                ps.setNull(11, Types.INTEGER);
            }

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
    
    // Update employee
    public boolean update(Employee employee) {
        String sql = "UPDATE Employee SET lastName = ?, firstName = ?, birthday = ?, phoneNumber = ?, genderID = ?, workStatusID = ?, positionID = ?, supervisorID = ? WHERE employeeID = ?";

        try (Connection conn = DBConnection.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, employee.getLastName());
            stmt.setString(2, employee.getFirstName());
            stmt.setDate(3, Date.valueOf(employee.getBirthday()));
            stmt.setString(4, employee.getPhoneNumber());
            stmt.setInt(5, employee.getGenderID());
            stmt.setInt(6, employee.getWorkStatusID());
            stmt.setInt(7, employee.getPositionID());

            if (employee.getSupervisorID() != null) {
                stmt.setInt(8, employee.getSupervisorID());
            } else {
                stmt.setNull(8, Types.INTEGER);
            }

            stmt.setInt(9, employee.getEmployeeID());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // Verifies if the given employeeID, birthday, and SSS number match a valid record for the forgot password feature
    public boolean verifyIdentity(int employeeID, LocalDate birthday, String sssNumber) {
        String sql = """
        SELECT 1
          FROM Employee e
          JOIN GovInformation g ON e.govID = g.govID
         WHERE e.employeeID = ?
           AND e.birthday = ?
           AND g.sssNumber = ?
         LIMIT 1
    """;

        try (Connection conn = DBConnection.getConnection(); 
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, employeeID);
            ps.setDate(2, Date.valueOf(birthday));
            ps.setString(3, sssNumber);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
