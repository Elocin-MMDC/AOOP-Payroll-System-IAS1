package model.pojo;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class SupportRequest extends Request {
    private int ticketID;
    private String assignedTeam;
    private String severity;
    private String subject;
    private String description;
    private Integer resolvedBy;

    public SupportRequest(int ticketID, String assignedTeam, String severity, String subject, String description, Integer resolvedBy, int employeeID, LocalDate date, String status, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(employeeID, date, status, createdAt, updatedAt);
        this.ticketID = ticketID;
        this.assignedTeam = assignedTeam;
        this.severity = severity;
        this.subject = subject;
        this.description = description;
        this.resolvedBy = resolvedBy;
    }

    public SupportRequest() {

    }

    public int getTicketID() {
        return ticketID;
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

    public Integer getResolvedBy() {
        return resolvedBy;
    }

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

    public void setTicketID(int ticketID) {
        this.ticketID = ticketID;
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

    public void setResolvedBy(Integer resolvedBy) {
        this.resolvedBy = resolvedBy;
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