package model.pojo;

import java.time.LocalDate;
import java.time.LocalDateTime;


public class LeaveRequest extends Request {
    private int leaveTypeID;
    private LocalDate startDate;
    private LocalDate endDate;
    private int leaveDays;
    private String reason;

    public LeaveRequest(int leaveTypeID, LocalDate startDate, LocalDate endDate, int leaveDays, String reason, int requestID, int employeeID, LocalDate date, String status, Integer approvedOrResolvedBy, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(requestID, employeeID, date, status, approvedOrResolvedBy, createdAt, updatedAt);
        this.leaveTypeID = leaveTypeID;
        this.startDate = startDate;
        this.endDate = endDate;
        this.leaveDays = leaveDays;
        this.reason = reason;
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

    @Override
    public int getRequestID() {
        return requestID;
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
    public Integer getApprovedOrResolvedBy() {
        return approvedOrResolvedBy;
    }

    @Override
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @Override
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
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

    @Override
    public void setRequestID(int requestID) {
        this.requestID = requestID;
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
    public void setApprovedOrResolvedBy(Integer approvedOrResolvedBy) {
        this.approvedOrResolvedBy = approvedOrResolvedBy;
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
