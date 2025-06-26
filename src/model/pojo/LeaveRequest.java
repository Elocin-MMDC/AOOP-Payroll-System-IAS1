package model.pojo;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class LeaveRequest extends Request {
    private int leaveID;
    private int leaveTypeID;
    private LocalDate startDate;
    private LocalDate endDate;
    private int leaveDays;
    private String reason;
    private Integer approvedBy;

    public LeaveRequest(int leaveID, int leaveTypeID, LocalDate startDate, LocalDate endDate, int leaveDays, String reason, Integer approvedBy, int employeeID, LocalDate date, String status, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(employeeID, date, status, createdAt, updatedAt);
        this.leaveID = leaveID;
        this.leaveTypeID = leaveTypeID;
        this.startDate = startDate;
        this.endDate = endDate;
        this.leaveDays = leaveDays;
        this.reason = reason;
        this.approvedBy = approvedBy;
    }

    public LeaveRequest() {
        
    }

    public int getLeaveID() {
        return leaveID;
    }

    public int getLeaveTypeID() {
        return leaveTypeID;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public int getLeaveDays() {
        return leaveDays;
    }

    public String getReason() {
        return reason;
    }

    public Integer getApprovedBy() {
        return approvedBy;
    }

    @Override
    public int getEmployeeID() {
        return employeeID;
    }

    @Override
    public LocalDate getDate() {
        return date;
    }

    @Override
    public String getStatus() {
        return status;
    }

    @Override
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @Override
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setLeaveID(int leaveID) {
        this.leaveID = leaveID;
    }

    public void setLeaveTypeID(int leaveTypeID) {
        this.leaveTypeID = leaveTypeID;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public void setLeaveDays(int leaveDays) {
        this.leaveDays = leaveDays;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public void setApprovedBy(Integer approvedBy) {
        this.approvedBy = approvedBy;
    }

    @Override
    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    @Override
    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

}