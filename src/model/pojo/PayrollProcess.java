package model.pojo;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class PayrollProcess {
    private int payrollProcessID;
    private int processedBy;
    private LocalDate dateProcessed;
    private LocalDate payStartDate;
    private LocalDate payEndDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public PayrollProcess(int payrollProcessID, int processedBy, LocalDate dateProcessed, LocalDate payStartDate, LocalDate payEndDate, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.payrollProcessID = payrollProcessID;
        this.processedBy = processedBy;
        this.dateProcessed = dateProcessed;
        this.payStartDate = payStartDate;
        this.payEndDate = payEndDate;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public PayrollProcess() {

    }

    public int getPayrollProcessID() {
        return payrollProcessID;
    }

    public int getProcessedBy() {
        return processedBy;
    }

    public LocalDate getDateProcessed() {
        return dateProcessed;
    }

    public LocalDate getPayStartDate() {
        return payStartDate;
    }

    public LocalDate getPayEndDate() {
        return payEndDate;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setPayrollProcessID(int payrollProcessID) {
        this.payrollProcessID = payrollProcessID;
    }

    public void setProcessedBy(int processedBy) {
        this.processedBy = processedBy;
    }

    public void setDateProcessed(LocalDate dateProcessed) {
        this.dateProcessed = dateProcessed;
    }

    public void setPayStartDate(LocalDate payStartDate) {
        this.payStartDate = payStartDate;
    }

    public void setPayEndDate(LocalDate payEndDate) {
        this.payEndDate = payEndDate;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
    
}
