package model.pojo;

import java.math.BigDecimal;

public class PayslipView {
    private int payslipID;
    private int employeeID;
    private String employeeName;
    private String payStartDate;
    private String payEndDate;
    private String employeePositionDepartment;

    // Rates and hours
    private BigDecimal monthlyRate;
    private BigDecimal hourlyRate;
    private BigDecimal regularHours;
    private BigDecimal overtimeHours;
    private BigDecimal grossIncome;

    // Benefits
    private BigDecimal riceSubsidy;
    private BigDecimal phoneAllowance;
    private BigDecimal clothingAllowance;
    private BigDecimal totalBenefits;

    // Deductions
    private BigDecimal sssContribution;
    private BigDecimal philHealthContribution;
    private BigDecimal pagIbigContribution;
    private BigDecimal withholdingTax;
    private BigDecimal totalDeductions;

    // Take Home Pay
    private BigDecimal netIncome;

    public PayslipView(int payslipID, int employeeID, String employeeName, String payStartDate, String payEndDate, String employeePositionDepartment, BigDecimal monthlyRate, BigDecimal hourlyRate, BigDecimal regularHours, BigDecimal overtimeHours, BigDecimal grossIncome, BigDecimal riceSubsidy, BigDecimal phoneAllowance, BigDecimal clothingAllowance, BigDecimal totalBenefits, BigDecimal sssContribution, BigDecimal philHealthContribution, BigDecimal pagIbigContribution, BigDecimal withholdingTax, BigDecimal totalDeductions, BigDecimal netIncome) {
        this.payslipID = payslipID;
        this.employeeID = employeeID;
        this.employeeName = employeeName;
        this.payStartDate = payStartDate;
        this.payEndDate = payEndDate;
        this.employeePositionDepartment = employeePositionDepartment;
        this.monthlyRate = monthlyRate;
        this.hourlyRate = hourlyRate;
        this.regularHours = regularHours;
        this.overtimeHours = overtimeHours;
        this.grossIncome = grossIncome;
        this.riceSubsidy = riceSubsidy;
        this.phoneAllowance = phoneAllowance;
        this.clothingAllowance = clothingAllowance;
        this.totalBenefits = totalBenefits;
        this.sssContribution = sssContribution;
        this.philHealthContribution = philHealthContribution;
        this.pagIbigContribution = pagIbigContribution;
        this.withholdingTax = withholdingTax;
        this.totalDeductions = totalDeductions;
        this.netIncome = netIncome;
    }
    
    public PayslipView() {
        
    }

    public int getPayslipID() {
        return payslipID;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public String getPayStartDate() {
        return payStartDate;
    }

    public String getPayEndDate() {
        return payEndDate;
    }

    public String getEmployeePositionDepartment() {
        return employeePositionDepartment;
    }

    public BigDecimal getMonthlyRate() {
        return monthlyRate;
    }

    public BigDecimal getHourlyRate() {
        return hourlyRate;
    }

    public BigDecimal getRegularHours() {
        return regularHours;
    }

    public BigDecimal getOvertimeHours() {
        return overtimeHours;
    }

    public BigDecimal getGrossIncome() {
        return grossIncome;
    }

    public BigDecimal getRiceSubsidy() {
        return riceSubsidy;
    }

    public BigDecimal getPhoneAllowance() {
        return phoneAllowance;
    }

    public BigDecimal getClothingAllowance() {
        return clothingAllowance;
    }

    public BigDecimal getTotalBenefits() {
        return totalBenefits;
    }

    public BigDecimal getSssContribution() {
        return sssContribution;
    }

    public BigDecimal getPhilHealthContribution() {
        return philHealthContribution;
    }

    public BigDecimal getPagIbigContribution() {
        return pagIbigContribution;
    }

    public BigDecimal getWithholdingTax() {
        return withholdingTax;
    }

    public BigDecimal getTotalDeductions() {
        return totalDeductions;
    }

    public BigDecimal getNetIncome() {
        return netIncome;
    }

    public void setPayslipID(int payslipID) {
        this.payslipID = payslipID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public void setPayStartDate(String payStartDate) {
        this.payStartDate = payStartDate;
    }

    public void setPayEndDate(String payEndDate) {
        this.payEndDate = payEndDate;
    }

    public void setEmployeePositionDepartment(String employeePositionDepartment) {
        this.employeePositionDepartment = employeePositionDepartment;
    }

    public void setMonthlyRate(BigDecimal monthlyRate) {
        this.monthlyRate = monthlyRate;
    }

    public void setHourlyRate(BigDecimal hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public void setRegularHours(BigDecimal regularHours) {
        this.regularHours = regularHours;
    }

    public void setOvertimeHours(BigDecimal overtimeHours) {
        this.overtimeHours = overtimeHours;
    }

    public void setGrossIncome(BigDecimal grossIncome) {
        this.grossIncome = grossIncome;
    }

    public void setRiceSubsidy(BigDecimal riceSubsidy) {
        this.riceSubsidy = riceSubsidy;
    }

    public void setPhoneAllowance(BigDecimal phoneAllowance) {
        this.phoneAllowance = phoneAllowance;
    }

    public void setClothingAllowance(BigDecimal clothingAllowance) {
        this.clothingAllowance = clothingAllowance;
    }

    public void setTotalBenefits(BigDecimal totalBenefits) {
        this.totalBenefits = totalBenefits;
    }

    public void setSssContribution(BigDecimal sssContribution) {
        this.sssContribution = sssContribution;
    }

    public void setPhilHealthContribution(BigDecimal philHealthContribution) {
        this.philHealthContribution = philHealthContribution;
    }

    public void setPagIbigContribution(BigDecimal pagIbigContribution) {
        this.pagIbigContribution = pagIbigContribution;
    }

    public void setWithholdingTax(BigDecimal withholdingTax) {
        this.withholdingTax = withholdingTax;
    }

    public void setTotalDeductions(BigDecimal totalDeductions) {
        this.totalDeductions = totalDeductions;
    }

    public void setNetIncome(BigDecimal netIncome) {
        this.netIncome = netIncome;
    }
    
}
