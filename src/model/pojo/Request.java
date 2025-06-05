package model.pojo;

import java.time.LocalDate;
import java.time.LocalDateTime;


public abstract class Request {
    protected int requestID;           // leaveID, overtimeID, or ticketID mapped here
    protected int employeeID;
    protected LocalDate date;
    protected String status;           // e.g. Pending, Approved, Rejected, Resolved
    protected Integer approvedOrResolvedBy;  // userID who approved/resolved, nullable
    protected LocalDateTime createdAt;
    protected LocalDateTime updatedAt;

    public Request(int requestID, int employeeID, LocalDate date, String status, Integer approvedOrResolvedBy, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.requestID = requestID;
        this.employeeID = employeeID;
        this.date = date;
        this.status = status;
        this.approvedOrResolvedBy = approvedOrResolvedBy;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public int getRequestID() {
        return requestID;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getStatus() {
        return status;
    }

    public Integer getApprovedOrResolvedBy() {
        return approvedOrResolvedBy;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setRequestID(int requestID) {
        this.requestID = requestID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setApprovedOrResolvedBy(Integer approvedOrResolvedBy) {
        this.approvedOrResolvedBy = approvedOrResolvedBy;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
    
}
