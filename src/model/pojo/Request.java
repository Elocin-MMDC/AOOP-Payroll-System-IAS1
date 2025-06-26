package model.pojo;

import java.time.LocalDate;
import java.time.LocalDateTime;

public abstract class Request {
    protected int employeeID;
    protected LocalDate date;
    protected String status;
    protected LocalDateTime createdAt;
    protected LocalDateTime updatedAt;

    public Request(int employeeID, LocalDate date, String status, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.employeeID = employeeID;
        this.date = date;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
    
    public Request() {
        
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
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

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
    
}
