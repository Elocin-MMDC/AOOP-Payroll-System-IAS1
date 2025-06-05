package model.pojo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;


public class OvertimeRequest extends Request {
    private LocalDate overtimeWorkDate;
    private BigDecimal overtimeHours;
    private String reason;

    public OvertimeRequest(LocalDate overtimeWorkDate, BigDecimal overtimeHours, String reason, int requestID, int employeeID, LocalDate date, String status, Integer approvedOrResolvedBy, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(requestID, employeeID, date, status, approvedOrResolvedBy, createdAt, updatedAt);
        this.overtimeWorkDate = overtimeWorkDate;
        this.overtimeHours = overtimeHours;
        this.reason = reason;
    }

    public LocalDate getOvertimeWorkDate() {
        return overtimeWorkDate;
    }

    public BigDecimal getOvertimeHours() {
        return overtimeHours;
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

    public void setOvertimeWorkDate(LocalDate overtimeWorkDate) {
        this.overtimeWorkDate = overtimeWorkDate;
    }

    public void setOvertimeHours(BigDecimal overtimeHours) {
        this.overtimeHours = overtimeHours;
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
