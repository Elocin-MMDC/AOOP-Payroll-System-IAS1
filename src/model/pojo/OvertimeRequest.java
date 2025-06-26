package model.pojo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class OvertimeRequest extends Request {
    private int overtimeID;
    private LocalDate overtimeWorkDate;
    private BigDecimal overtimeHours;
    private String reason;
    private Integer approvedBy;

    public OvertimeRequest(int overtimeID, LocalDate overtimeWorkDate, BigDecimal overtimeHours, String reason, Integer approvedBy, int employeeID, LocalDate date, String status, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(employeeID, date, status, createdAt, updatedAt);
        this.overtimeID = overtimeID;
        this.overtimeWorkDate = overtimeWorkDate;
        this.overtimeHours = overtimeHours;
        this.reason = reason;
        this.approvedBy = approvedBy;
    }

    public OvertimeRequest() {

    }

    public int getOvertimeID() {
        return overtimeID;
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

    public void setOvertimeID(int overtimeID) {
        this.overtimeID = overtimeID;
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