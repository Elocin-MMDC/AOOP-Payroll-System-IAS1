package model.pojo;

import java.time.LocalDate;
import java.time.LocalDateTime;


public class SupportRequest extends Request {
    private String assignedTeam;  // HR, Finance, IT
    private String severity;      // Low, Medium, High
    private String subject;
    private String description;

    public SupportRequest(String assignedTeam, String severity, String subject, String description, int requestID, int employeeID, LocalDate date, String status, Integer approvedOrResolvedBy, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(requestID, employeeID, date, status, approvedOrResolvedBy, createdAt, updatedAt);
        this.assignedTeam = assignedTeam;
        this.severity = severity;
        this.subject = subject;
        this.description = description;
    }

    public String getAssignedTeam() {
        return assignedTeam;
    }

    public String getSeverity() {
        return severity;
    }

    public String getSubject() {
        return subject;
    }

    public String getDescription() {
        return description;
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

    public void setAssignedTeam(String assignedTeam) {
        this.assignedTeam = assignedTeam;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setDescription(String description) {
        this.description = description;
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
