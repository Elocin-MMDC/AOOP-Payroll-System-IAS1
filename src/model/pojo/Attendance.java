package model.pojo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;


public class Attendance {
    private int attendanceID;
    private int employeeID;
    private LocalDate date;
    private LocalTime clockIn;
    private LocalTime clockOut;
    private BigDecimal regularHours;
    private BigDecimal overtimeHours;
    private String status; // "On-time" or "Late"
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Attendance(int attendanceID, int employeeID, LocalDate date, LocalTime clockIn, LocalTime clockOut, BigDecimal regularHours, BigDecimal overtimeHours, String status, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.attendanceID = attendanceID;
        this.employeeID = employeeID;
        this.date = date;
        this.clockIn = clockIn;
        this.clockOut = clockOut;
        this.regularHours = regularHours;
        this.overtimeHours = overtimeHours;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public int getAttendanceID() {
        return attendanceID;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getClockIn() {
        return clockIn;
    }

    public LocalTime getClockOut() {
        return clockOut;
    }

    public BigDecimal getRegularHours() {
        return regularHours;
    }

    public BigDecimal getOvertimeHours() {
        return overtimeHours;
    }

    public String getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setAttendanceID(int attendanceID) {
        this.attendanceID = attendanceID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setClockIn(LocalTime clockIn) {
        this.clockIn = clockIn;
    }

    public void setClockOut(LocalTime clockOut) {
        this.clockOut = clockOut;
    }

    public void setRegularHours(BigDecimal regularHours) {
        this.regularHours = regularHours;
    }

    public void setOvertimeHours(BigDecimal overtimeHours) {
        this.overtimeHours = overtimeHours;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
    
}