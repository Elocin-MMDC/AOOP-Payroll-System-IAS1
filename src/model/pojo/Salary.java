package model.pojo;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Salary {
    private int salaryID;
    private BigDecimal basicSalary;
    private BigDecimal semiMonthlyRate;
    private BigDecimal hourlyRate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Salary(int salaryID, BigDecimal basicSalary, BigDecimal semiMonthlyRate, BigDecimal hourlyRate, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.salaryID = salaryID;
        this.basicSalary = basicSalary;
        this.semiMonthlyRate = semiMonthlyRate;
        this.hourlyRate = hourlyRate;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Salary() {
        
    }

    public int getSalaryID() {
        return salaryID;
    }

    public BigDecimal getBasicSalary() {
        return basicSalary;
    }

    public BigDecimal getSemiMonthlyRate() {
        return semiMonthlyRate;
    }

    public BigDecimal getHourlyRate() {
        return hourlyRate;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setSalaryID(int salaryID) {
        this.salaryID = salaryID;
    }

    public void setBasicSalary(BigDecimal basicSalary) {
        this.basicSalary = basicSalary;
    }

    public void setSemiMonthlyRate(BigDecimal semiMonthlyRate) {
        this.semiMonthlyRate = semiMonthlyRate;
    }

    public void setHourlyRate(BigDecimal hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
    
}
