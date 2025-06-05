package model.pojo;

import java.math.BigDecimal;


public class PagIbigMatrix {
    private int pagIbigID;
    private BigDecimal minGrossIncome;
    private BigDecimal maxGrossIncome;      // nullable
    private BigDecimal employeeRate;
    private BigDecimal employerRate;
    private BigDecimal maxContribution;

    public PagIbigMatrix(int pagIbigID, BigDecimal minGrossIncome, BigDecimal maxGrossIncome, BigDecimal employeeRate, BigDecimal employerRate, BigDecimal maxContribution) {
        this.pagIbigID = pagIbigID;
        this.minGrossIncome = minGrossIncome;
        this.maxGrossIncome = maxGrossIncome;
        this.employeeRate = employeeRate;
        this.employerRate = employerRate;
        this.maxContribution = maxContribution;
    }

    public int getPagIbigID() {
        return pagIbigID;
    }

    public BigDecimal getMinGrossIncome() {
        return minGrossIncome;
    }

    public BigDecimal getMaxGrossIncome() {
        return maxGrossIncome;
    }

    public BigDecimal getEmployeeRate() {
        return employeeRate;
    }

    public BigDecimal getEmployerRate() {
        return employerRate;
    }

    public BigDecimal getMaxContribution() {
        return maxContribution;
    }

    public void setPagIbigID(int pagIbigID) {
        this.pagIbigID = pagIbigID;
    }

    public void setMinGrossIncome(BigDecimal minGrossIncome) {
        this.minGrossIncome = minGrossIncome;
    }

    public void setMaxGrossIncome(BigDecimal maxGrossIncome) {
        this.maxGrossIncome = maxGrossIncome;
    }

    public void setEmployeeRate(BigDecimal employeeRate) {
        this.employeeRate = employeeRate;
    }

    public void setEmployerRate(BigDecimal employerRate) {
        this.employerRate = employerRate;
    }

    public void setMaxContribution(BigDecimal maxContribution) {
        this.maxContribution = maxContribution;
    }
    
}
