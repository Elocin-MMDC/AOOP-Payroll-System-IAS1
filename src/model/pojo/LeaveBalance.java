package model.pojo;

import java.time.LocalDateTime;


public class LeaveBalance {
    private int leaveBalanceID;
    private int employeeID;
    private int leaveTypeID;
    private int entitledDays;
    private int usedDays;
    private int remainingDays;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public LeaveBalance(int leaveBalanceID, int employeeID, int leaveTypeID, int entitledDays, int usedDays, int remainingDays, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.leaveBalanceID = leaveBalanceID;
        this.employeeID = employeeID;
        this.leaveTypeID = leaveTypeID;
        this.entitledDays = entitledDays;
        this.usedDays = usedDays;
        this.remainingDays = remainingDays;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public int getLeaveBalanceID() {
        return leaveBalanceID;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public int getLeaveTypeID() {
        return leaveTypeID;
    }

    public int getEntitledDays() {
        return entitledDays;
    }

    public int getUsedDays() {
        return usedDays;
    }

    public int getRemainingDays() {
        return remainingDays;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setLeaveBalanceID(int leaveBalanceID) {
        this.leaveBalanceID = leaveBalanceID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public void setLeaveTypeID(int leaveTypeID) {
        this.leaveTypeID = leaveTypeID;
    }

    public void setEntitledDays(int entitledDays) {
        this.entitledDays = entitledDays;
    }

    public void setUsedDays(int usedDays) {
        this.usedDays = usedDays;
    }

    public void setRemainingDays(int remainingDays) {
        this.remainingDays = remainingDays;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
    
}
