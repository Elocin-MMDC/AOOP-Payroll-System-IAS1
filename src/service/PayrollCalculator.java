package service;

import java.math.BigDecimal;
import java.time.LocalDate;
import model.pojo.EmployeeView;

public interface PayrollCalculator {

    BigDecimal calculateGrossIncome(EmployeeView employee, LocalDate payStartDate, LocalDate payEndDate);
    
    int calculateDaysWorked(EmployeeView employee, LocalDate payStartDate, LocalDate payEndDate);

    BigDecimal calculateTotalBenefits(EmployeeView employee);

    BigDecimal calculateSssContribution(BigDecimal grossIncome);

    BigDecimal calculatePhilhealthContribution(BigDecimal grossIncome);

    BigDecimal calculatePagIbigContribution(BigDecimal grossIncome);

    BigDecimal calculateTotalContributions(BigDecimal sss, BigDecimal philhealth, BigDecimal pagibig);

    BigDecimal calculateTaxableIncome(BigDecimal grossIncome, BigDecimal totalContributions);

    BigDecimal calculateWithholdingTax(BigDecimal taxableIncome);

    BigDecimal calculateTotalDeductions(BigDecimal sss, BigDecimal philhealth, BigDecimal pagibig, BigDecimal withholdingTax);

    BigDecimal calculateNetIncome(BigDecimal grossIncome, BigDecimal totalBenefits, BigDecimal totalDeductions);
    
}