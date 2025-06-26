package model.pojo;

import java.math.BigDecimal;
import java.time.LocalDate;

public class MonthlyPayrollSummaryReportView {
    private int employeeID;
    private LocalDate payStartDate;
    private LocalDate payEndDate;
    private String employeeName;
    private String position;
    private String department;
    private BigDecimal grossIncome;
    private String sssNumber;
    private BigDecimal sssContribution;
    private String philHealthNumber;
    private BigDecimal philHealthContribution;
    private String pagIbigNumber;
    private BigDecimal pagIbigContribution;
    private String tin;
    private BigDecimal withholdingTax;
    private BigDecimal netIncome;

    public MonthlyPayrollSummaryReportView(int employeeID, LocalDate payStartDate, LocalDate payEndDate, String employeeName, String position, String department, BigDecimal grossIncome, String sssNumber, BigDecimal sssContribution, String philHealthNumber, BigDecimal philHealthContribution, String pagIbigNumber, BigDecimal pagIbigContribution, String tin, BigDecimal withholdingTax, BigDecimal netIncome) {
        this.employeeID = employeeID;
        this.payStartDate = payStartDate;
        this.payEndDate = payEndDate;
        this.employeeName = employeeName;
        this.position = position;
        this.department = department;
        this.grossIncome = grossIncome;
        this.sssNumber = sssNumber;
        this.sssContribution = sssContribution;
        this.philHealthNumber = philHealthNumber;
        this.philHealthContribution = philHealthContribution;
        this.pagIbigNumber = pagIbigNumber;
        this.pagIbigContribution = pagIbigContribution;
        this.tin = tin;
        this.withholdingTax = withholdingTax;
        this.netIncome = netIncome;
    }
    
    public MonthlyPayrollSummaryReportView() {
        
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public LocalDate getPayStartDate() {
        return payStartDate;
    }

    public LocalDate getPayEndDate() {
        return payEndDate;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public String getPosition() {
        return position;
    }

    public String getDepartment() {
        return department;
    }

    public BigDecimal getGrossIncome() {
        return grossIncome;
    }

    public String getSssNumber() {
        return sssNumber;
    }

    public BigDecimal getSssContribution() {
        return sssContribution;
    }

    public String getPhilHealthNumber() {
        return philHealthNumber;
    }

    public BigDecimal getPhilHealthContribution() {
        return philHealthContribution;
    }

    public String getPagIbigNumber() {
        return pagIbigNumber;
    }

    public BigDecimal getPagIbigContribution() {
        return pagIbigContribution;
    }

    public String getTin() {
        return tin;
    }

    public BigDecimal getWithholdingTax() {
        return withholdingTax;
    }

    public BigDecimal getNetIncome() {
        return netIncome;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public void setPayStartDate(LocalDate payStartDate) {
        this.payStartDate = payStartDate;
    }

    public void setPayEndDate(LocalDate payEndDate) {
        this.payEndDate = payEndDate;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setGrossIncome(BigDecimal grossIncome) {
        this.grossIncome = grossIncome;
    }

    public void setSssNumber(String sssNumber) {
        this.sssNumber = sssNumber;
    }

    public void setSssContribution(BigDecimal sssContribution) {
        this.sssContribution = sssContribution;
    }

    public void setPhilHealthNumber(String philHealthNumber) {
        this.philHealthNumber = philHealthNumber;
    }

    public void setPhilHealthContribution(BigDecimal philHealthContribution) {
        this.philHealthContribution = philHealthContribution;
    }

    public void setPagIbigNumber(String pagIbigNumber) {
        this.pagIbigNumber = pagIbigNumber;
    }

    public void setPagIbigContribution(BigDecimal pagIbigContribution) {
        this.pagIbigContribution = pagIbigContribution;
    }

    public void setTin(String tin) {
        this.tin = tin;
    }

    public void setWithholdingTax(BigDecimal withholdingTax) {
        this.withholdingTax = withholdingTax;
    }

    public void setNetIncome(BigDecimal netIncome) {
        this.netIncome = netIncome;
    }
    
}
