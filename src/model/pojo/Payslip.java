package model.pojo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;


public class Payslip {
    private int payslipID;
    private int employeeID;
    private int payrollProcessID;
    private LocalDate payStartDate;
    private LocalDate payEndDate;
    private LocalDate dateReleased;
    private String employeeName;
    private String employeePosition;
    private String employeeDepartment;
    private BigDecimal basicSalary;
    private BigDecimal hourlyRate;
    private int daysWorked;
    private BigDecimal regularHours;
    private BigDecimal overtimeHours;
    private BigDecimal grossIncome;
    private BigDecimal riceSubsidy;
    private BigDecimal phoneAllowance;
    private BigDecimal clothingAllowance;
    private BigDecimal totalBenefits;
    private BigDecimal sssContribution;
    private BigDecimal philHealthContribution;
    private BigDecimal pagIbigContribution;
    private BigDecimal withholdingTax;
    private BigDecimal totalDeductions;
    private BigDecimal netIncome;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Payslip(int payslipID, int employeeID, int payrollProcessID, LocalDate payStartDate, LocalDate payEndDate, LocalDate dateReleased, String employeeName, String employeePosition, String employeeDepartment, BigDecimal basicSalary, BigDecimal hourlyRate, int daysWorked, BigDecimal regularHours, BigDecimal overtimeHours, BigDecimal grossIncome, BigDecimal riceSubsidy, BigDecimal phoneAllowance, BigDecimal clothingAllowance, BigDecimal totalBenefits, BigDecimal sssContribution, BigDecimal philHealthContribution, BigDecimal pagIbigContribution, BigDecimal withholdingTax, BigDecimal totalDeductions, BigDecimal netIncome, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.payslipID = payslipID;
        this.employeeID = employeeID;
        this.payrollProcessID = payrollProcessID;
        this.payStartDate = payStartDate;
        this.payEndDate = payEndDate;
        this.dateReleased = dateReleased;
        this.employeeName = employeeName;
        this.employeePosition = employeePosition;
        this.employeeDepartment = employeeDepartment;
        this.basicSalary = basicSalary;
        this.hourlyRate = hourlyRate;
        this.daysWorked = daysWorked;
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
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public int getPayslipID() {
        return payslipID;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public int getPayrollProcessID() {
        return payrollProcessID;
    }

    public LocalDate getPayStartDate() {
        return payStartDate;
    }

    public LocalDate getPayEndDate() {
        return payEndDate;
    }

    public LocalDate getDateReleased() {
        return dateReleased;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public String getEmployeePosition() {
        return employeePosition;
    }

    public String getEmployeeDepartment() {
        return employeeDepartment;
    }

    public BigDecimal getBasicSalary() {
        return basicSalary;
    }

    public BigDecimal getHourlyRate() {
        return hourlyRate;
    }

    public int getDaysWorked() {
        return daysWorked;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setPayslipID(int payslipID) {
        this.payslipID = payslipID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public void setPayrollProcessID(int payrollProcessID) {
        this.payrollProcessID = payrollProcessID;
    }

    public void setPayStartDate(LocalDate payStartDate) {
        this.payStartDate = payStartDate;
    }

    public void setPayEndDate(LocalDate payEndDate) {
        this.payEndDate = payEndDate;
    }

    public void setDateReleased(LocalDate dateReleased) {
        this.dateReleased = dateReleased;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public void setEmployeePosition(String employeePosition) {
        this.employeePosition = employeePosition;
    }

    public void setEmployeeDepartment(String employeeDepartment) {
        this.employeeDepartment = employeeDepartment;
    }

    public void setBasicSalary(BigDecimal basicSalary) {
        this.basicSalary = basicSalary;
    }

    public void setHourlyRate(BigDecimal hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public void setDaysWorked(int daysWorked) {
        this.daysWorked = daysWorked;
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

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
    
}
