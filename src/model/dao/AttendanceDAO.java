package model.dao;

import db.DBConnection;
import java.math.BigDecimal;
import model.pojo.Attendance;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AttendanceDAO {

    // Retrieve attendance record by ID
    public Attendance getById(int attendanceID) {
        String sql = "SELECT * FROM Attendance WHERE attendanceID = ?";
        try (Connection conn = DBConnection.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, attendanceID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapResultSetToAttendance(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Retrieve all attendance records
    public List<Attendance> getAll() {
        List<Attendance> list = new ArrayList<>();
        String sql = "SELECT * FROM Attendance ORDER BY attendanceID DESC";
        try (Connection conn = DBConnection.getConnection(); 
             Statement stmt = conn.createStatement()) {
            
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                list.add(mapResultSetToAttendance(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    // Get attendance records by employee ID
    public List<Attendance> getByEmployeeId(int employeeID) {
        List<Attendance> list = new ArrayList<>();
        String sql = "SELECT * FROM Attendance WHERE employeeID = ? ORDER BY attendanceID DESC";

        try (Connection conn = DBConnection.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, employeeID);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    list.add(mapResultSetToAttendance(rs));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    // Map ResultSet row to Attendance POJO
    private Attendance mapResultSetToAttendance(ResultSet rs) throws SQLException {
        Attendance attendance = new Attendance();
        attendance.setAttendanceID(rs.getInt("attendanceID"));
        attendance.setEmployeeID(rs.getInt("employeeID"));
        attendance.setDate(rs.getDate("date").toLocalDate());
        attendance.setClockIn(rs.getTime("clockIn").toLocalTime());
        Time clockOut = rs.getTime("clockOut");
        if (clockOut != null) {
            attendance.setClockOut(clockOut.toLocalTime());
        }
        attendance.setRegularHours(rs.getBigDecimal("regularHours"));
        attendance.setOvertimeHours(rs.getBigDecimal("overtimeHours"));
        attendance.setStatus(rs.getString("status"));
        attendance.setCreatedAt(rs.getTimestamp("createdAt").toLocalDateTime());
        attendance.setUpdatedAt(rs.getTimestamp("updatedAt").toLocalDateTime());
        return attendance;
    }
    
    // Insert new attendance record
    public boolean insert(Attendance attendance) {
        String sql = "INSERT INTO Attendance (employeeID, date, clockIn, clockOut, regularHours, overtimeHours, status, createdAt, updatedAt) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, attendance.getEmployeeID());
            stmt.setDate(2, Date.valueOf(attendance.getDate()));
            stmt.setTime(3, Time.valueOf(attendance.getClockIn()));
            if (attendance.getClockOut() != null) {
                stmt.setTime(4, Time.valueOf(attendance.getClockOut()));
            } else {
                stmt.setNull(4, Types.TIME);
            }
            if (attendance.getRegularHours() != null) {
                stmt.setBigDecimal(5, attendance.getRegularHours());
            } else {
                stmt.setNull(5, Types.DECIMAL);
            }
            if (attendance.getOvertimeHours() != null) {
                stmt.setBigDecimal(6, attendance.getOvertimeHours());
            } else {
                stmt.setNull(6, Types.DECIMAL);
            }
            stmt.setString(7, attendance.getStatus());
            stmt.setTimestamp(8, Timestamp.valueOf(attendance.getCreatedAt()));
            stmt.setTimestamp(9, Timestamp.valueOf(attendance.getUpdatedAt()));
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Update attendance record
    public boolean update(Attendance attendance) {
        String sql = "UPDATE Attendance SET employeeID = ?, date = ?, clockIn = ?, clockOut = ?, regularHours = ?, overtimeHours = ?, status = ?, updatedAt = ? WHERE attendanceID = ?";
        try (Connection conn = DBConnection.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, attendance.getEmployeeID());
            stmt.setDate(2, Date.valueOf(attendance.getDate()));
            stmt.setTime(3, Time.valueOf(attendance.getClockIn()));
            if (attendance.getClockOut() != null) {
                stmt.setTime(4, Time.valueOf(attendance.getClockOut()));
            } else {
                stmt.setNull(4, Types.TIME);
            }
            if (attendance.getRegularHours() != null) {
                stmt.setBigDecimal(5, attendance.getRegularHours());
            } else {
                stmt.setNull(5, Types.DECIMAL);
            }
            if (attendance.getOvertimeHours() != null) {
                stmt.setBigDecimal(6, attendance.getOvertimeHours());
            } else {
                stmt.setNull(6, Types.DECIMAL);
            }
            stmt.setString(7, attendance.getStatus());
            stmt.setTimestamp(8, Timestamp.valueOf(attendance.getUpdatedAt()));
            stmt.setInt(9, attendance.getAttendanceID());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    // Returns total regular hours worked by an employee within the given date range
    public BigDecimal getTotalRegularHours(int employeeID, LocalDate startDate, LocalDate endDate) {
        String sql = "SELECT SUM(regularHours) FROM Attendance WHERE employeeID = ? AND date BETWEEN ? AND ?";
        try (Connection conn = DBConnection.getConnection(); 
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, employeeID);
            ps.setDate(2, Date.valueOf(startDate));
            ps.setDate(3, Date.valueOf(endDate));

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getBigDecimal(1) != null ? rs.getBigDecimal(1) : BigDecimal.ZERO;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return BigDecimal.ZERO;
    }

    // Returns total overtime hours worked by an employee within the given date range
    public BigDecimal getTotalOvertimeHours(int employeeID, LocalDate startDate, LocalDate endDate) {
        String sql = "SELECT SUM(overtimeHours) FROM Attendance WHERE employeeID = ? AND date BETWEEN ? AND ?";
        try (Connection conn = DBConnection.getConnection(); 
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, employeeID);
            ps.setDate(2, Date.valueOf(startDate));
            ps.setDate(3, Date.valueOf(endDate));

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getBigDecimal(1) != null ? rs.getBigDecimal(1) : BigDecimal.ZERO;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return BigDecimal.ZERO;
    }
    
    // Returns total distinct days the employee was marked 'On-time' or 'Late' within the date range
    public int getTotalDaysWorked(int employeeID, LocalDate startDate, LocalDate endDate) {
        String sql = """
        SELECT COUNT(DISTINCT date) AS totalDays
        FROM Attendance
        WHERE employeeID = ? 
          AND date BETWEEN ? AND ?
          AND status IN ('On-time', 'Late')
    """;

        try (Connection con = DBConnection.getConnection(); 
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, employeeID);
            ps.setDate(2, java.sql.Date.valueOf(startDate));
            ps.setDate(3, java.sql.Date.valueOf(endDate));

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("totalDays");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }
    
    // Checks if any attendance records exist within the given date range
    public boolean hasAttendanceForPeriod(LocalDate startDate, LocalDate endDate) {
        String sql = "SELECT COUNT(*) FROM Attendance WHERE date BETWEEN ? AND ?";

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
}
