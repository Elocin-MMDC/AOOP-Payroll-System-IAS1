package service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import model.dao.AttendanceDAO;
import model.dao.AuditLogDAO;
import model.dao.EmployeeViewDAO;
import model.dao.MonthlyPayrollSummaryReportViewDAO;
import model.pojo.EmployeeView;
import model.pojo.SSSMatrix;
import model.pojo.PagIbigMatrix;
import model.pojo.PhilHealthMatrix;
import model.pojo.WithholdingTaxMatrix;
import model.dao.PagIbigMatrixDAO;
import model.dao.PayrollProcessDAO;
import model.dao.PayslipDAO;
import model.dao.PhilHealthMatrixDAO;
import model.dao.SSSMatrixDAO;
import model.dao.WithholdingTaxMatrixDAO;
import model.pojo.AuditLog;
import model.pojo.MonthlyPayrollSummaryReportView;
import model.pojo.PayrollProcess;
import model.pojo.Payslip;
import util.AccessControlUtil;

public class PayrollService implements PayrollCalculator {

    private final AttendanceDAO attendanceDao = new AttendanceDAO();
    private final SSSMatrixDAO sssDao = new SSSMatrixDAO();
    private final PhilHealthMatrixDAO philHealthDao = new PhilHealthMatrixDAO();
    private final PagIbigMatrixDAO pagIbigDao = new PagIbigMatrixDAO();
    private final WithholdingTaxMatrixDAO taxDao = new WithholdingTaxMatrixDAO();
    private final PayslipDAO payslipDao = new PayslipDAO();
    private final PayrollProcessDAO payrollProcessDao = new PayrollProcessDAO();
    private final EmployeeViewDAO efdDao = new EmployeeViewDAO();
    private final AuditLogDAO auditDao = new AuditLogDAO();
    private final MonthlyPayrollSummaryReportViewDAO reportViewDao = new MonthlyPayrollSummaryReportViewDAO();

    @Override
    public BigDecimal calculateGrossIncome(EmployeeView emp, LocalDate payStartDate, LocalDate payEndDate) {
        BigDecimal hourlyRate = emp.getHourlyRate();
        BigDecimal regularHours = attendanceDao.getTotalRegularHours(emp.getEmployeeID(), payStartDate, payEndDate);
        BigDecimal overtimeHours = attendanceDao.getTotalOvertimeHours(emp.getEmployeeID(), payStartDate, payEndDate);
        BigDecimal regularIncome = hourlyRate.multiply(regularHours);
        BigDecimal overtimeIncome = hourlyRate.multiply(overtimeHours).multiply(new BigDecimal("1.25"));
        return regularIncome.add(overtimeIncome);
    }
    
    @Override
    public int calculateDaysWorked(EmployeeView emp, LocalDate payStartDate, LocalDate payEndDate) {
        return attendanceDao.getTotalDaysWorked(emp.getEmployeeID(), payStartDate, payEndDate);
    }

    @Override
    public BigDecimal calculateTotalBenefits(EmployeeView emp) {
        return emp.getRiceSubsidy()
                .add(emp.getClothingAllowance())
                .add(emp.getPhoneAllowance());
    }

    @Override
    public BigDecimal calculateSssContribution(BigDecimal grossIncome) {
        SSSMatrix sss = sssDao.getByGrossIncome(grossIncome);
        return sss != null ? sss.getContributionAmount() : BigDecimal.ZERO;
    }

    @Override
    public BigDecimal calculatePhilhealthContribution(BigDecimal grossIncome) {
        PhilHealthMatrix philHealth = philHealthDao.getByGrossIncome(grossIncome);
        if (philHealth != null) {
            if (philHealth.getPremiumRate().compareTo(BigDecimal.ZERO) > 0) {
                return grossIncome.multiply(philHealth.getPremiumRate())
                        .divide(new BigDecimal("2"), 2, RoundingMode.HALF_UP); // Employee share
            } else {
                return philHealth.getContributionAmount()
                        .divide(new BigDecimal("2"), 2, RoundingMode.HALF_UP); // Fixed contribution amount (divided to 2 for employee share)
            }
        }
        return BigDecimal.ZERO;
    }

    @Override
    public BigDecimal calculatePagIbigContribution(BigDecimal grossIncome) {
        PagIbigMatrix pagIbig = pagIbigDao.getByGrossIncome(grossIncome);
        if (pagIbig != null) {
            BigDecimal contribution = grossIncome.multiply(pagIbig.getEmployeeRate());
            return contribution.min(pagIbig.getMaxContribution());
        }
        return BigDecimal.ZERO;
    }

    @Override
    public BigDecimal calculateTotalContributions(BigDecimal sss, BigDecimal philhealth, BigDecimal pagibig) {
        return sss.add(philhealth).add(pagibig);
    }

    @Override
    public BigDecimal calculateTaxableIncome(BigDecimal grossIncome, BigDecimal totalContributions) {
        return grossIncome.subtract(totalContributions); // Benefits are non-taxable
    }

    @Override
    public BigDecimal calculateWithholdingTax(BigDecimal taxableIncome) {
        WithholdingTaxMatrix tax = taxDao.getByTaxableIncome(taxableIncome);
        if (tax != null) {
            BigDecimal excess = taxableIncome.subtract(tax.getMinTaxableIncome());
            BigDecimal excessTax = excess.multiply(tax.getExcessRate());
            return tax.getBaseTax().add(excessTax);
        }
        return BigDecimal.ZERO;
    }

    @Override
    public BigDecimal calculateTotalDeductions(BigDecimal sss, BigDecimal philhealth, BigDecimal pagibig, BigDecimal withholdingTax) {
        return sss.add(philhealth).add(pagibig).add(withholdingTax);
    }

    @Override
    public BigDecimal calculateNetIncome(BigDecimal grossIncome, BigDecimal totalBenefits, BigDecimal totalDeductions) {
        return grossIncome.add(totalBenefits).subtract(totalDeductions);
    }
    
    public List<Payslip> getAllPayrollRecords() {
        AccessControlUtil.requireRole("Finance Admin");
        return payslipDao.getAll();
    }
    
    public List<Payslip> getAllPayslipRecords(int employeeID) {
        return payslipDao.getByEmployeeId(employeeID);
    }
    
    public List<EmployeeView> getAllActiveEmployees() {
        AccessControlUtil.requireRole("Finance Admin");
        return efdDao.getAllEmployees();
    }
    
    public List<MonthlyPayrollSummaryReportView> getReport(LocalDate payStartDate, LocalDate payEndDate) {
        AccessControlUtil.requireRole("Finance Admin");
        return reportViewDao.getByPayPeriod(payStartDate, payEndDate);
    }
    
    public Map<String, LocalDate[]> getFormattedPayPeriods() {
        AccessControlUtil.requireRole("Finance Admin");
        List<PayrollProcess> processes = payrollProcessDao.getAllPayPeriods();
        Map<String, LocalDate[]> formatted = new LinkedHashMap<>();

        for (PayrollProcess process : processes) {
            LocalDate start = process.getPayStartDate();
            LocalDate end = process.getPayEndDate();
            String label = start.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH) + " " + start.getYear();
            formatted.put(label, new LocalDate[]{start, end});
        }

        return formatted;
    }
    
    public boolean runBatchPayrollProcess(int processedByUserID, LocalDate payStartDate, LocalDate payEndDate) throws Exception {
        AccessControlUtil.requireRole("Finance Admin");
        LocalDate today = LocalDate.now();
        LocalDate endOfMonth = payStartDate.withDayOfMonth(payStartDate.lengthOfMonth());
        
        if (payStartDate.isAfter(today)) {
            throw new Exception("Cannot process payroll: the selected pay period is in the future.");
        }

        if (today.isBefore(endOfMonth)) {
            boolean hasAttendance = attendanceDao.hasAttendanceForPeriod(payStartDate, payEndDate);
            if (hasAttendance) {
                throw new Exception("Payroll cannot be processed yet. Please wait until the end of the month.");
            } else {
                throw new Exception("Cannot process payroll: the selected month is still ongoing.");
            }
        }

        boolean hasAttendance = attendanceDao.hasAttendanceForPeriod(payStartDate, payEndDate);
        if (!hasAttendance) {
            throw new Exception("No attendance records found for the selected pay period.");
        }

        boolean alreadyProcessed = payrollProcessDao.existsForPeriod(payStartDate, payEndDate);
        if (alreadyProcessed) {
            throw new Exception("Payroll for this period has already been processed.");
        }

        List<EmployeeView> employees = getAllActiveEmployees();

        PayrollProcess process = new PayrollProcess();
        process.setProcessedBy(processedByUserID);
        process.setDateProcessed(today);
        process.setPayStartDate(payStartDate);
        process.setPayEndDate(payEndDate);
        LocalDateTime now = LocalDateTime.now();
        process.setCreatedAt(now);
        process.setUpdatedAt(now);

        int payrollProcessID = payrollProcessDao.insertAndReturnID(process);
        if (payrollProcessID == -1) {
            throw new Exception("Failed to create payroll process. Please try again.");
        }

        for (EmployeeView emp : employees) {
            Payslip payslip = computePayslip(emp, payrollProcessID, payStartDate, payEndDate);
            payslipDao.insert(payslip);
        }
        
        AuditLog log = new AuditLog();
        log.setUserID(processedByUserID);
        log.setCreatedAt(LocalDateTime.now());
        log.setAction("INSERT");
        log.setEntityModified("PayrollProcess");
        log.setEntityID(payrollProcessID);
        log.setAttributeModified("all");
        log.setOldValue(null);
        log.setNewValue(null);
        auditDao.insert(log);

        return true;
    }
    
    public Payslip computePayslip(EmployeeView emp, int payrollProcessID, LocalDate payStartDate, LocalDate payEndDate) {
        AccessControlUtil.requireRole("Finance Admin");
        Payslip payslip = new Payslip();

        // Basic Info
        payslip.setEmployeeID(emp.getEmployeeID());
        payslip.setPayrollProcessID(payrollProcessID);
        payslip.setPayStartDate(payStartDate);
        payslip.setPayEndDate(payEndDate);
        payslip.setDateReleased(LocalDate.now());

        payslip.setEmployeeName(emp.getFullNameWithSurnameFirst());
        payslip.setEmployeePosition(emp.getPositionTitle());
        payslip.setEmployeeDepartment(emp.getDepartmentName());
        payslip.setBasicSalary(emp.getBasicSalary());
        payslip.setHourlyRate(emp.getHourlyRate());

        // Attendance Data
        BigDecimal regularHours = attendanceDao.getTotalRegularHours(emp.getEmployeeID(), payStartDate, payEndDate);
        BigDecimal overtimeHours = attendanceDao.getTotalOvertimeHours(emp.getEmployeeID(), payStartDate, payEndDate);
        int daysWorked = attendanceDao.getTotalDaysWorked(emp.getEmployeeID(), payStartDate, payEndDate);

        payslip.setRegularHours(regularHours);
        payslip.setOvertimeHours(overtimeHours);
        payslip.setDaysWorked(daysWorked);

        // Gross Income
        BigDecimal grossIncome = calculateGrossIncome(emp, payStartDate, payEndDate);
        payslip.setGrossIncome(grossIncome);
        
        // Early exit: no work, no pay
        if (grossIncome.compareTo(BigDecimal.ZERO) == 0) {
            return createZeroPayslip(emp, payrollProcessID, payStartDate, payEndDate);
        }

        // Benefits
        BigDecimal rice = emp.getRiceSubsidy();
        BigDecimal clothing = emp.getClothingAllowance();
        BigDecimal phone = emp.getPhoneAllowance();
        BigDecimal totalBenefits = calculateTotalBenefits(emp);

        payslip.setRiceSubsidy(rice);
        payslip.setClothingAllowance(clothing);
        payslip.setPhoneAllowance(phone);
        payslip.setTotalBenefits(totalBenefits);

        // Contributions
        BigDecimal sss = calculateSssContribution(grossIncome);
        BigDecimal philHealth = calculatePhilhealthContribution(grossIncome);
        BigDecimal pagIbig = calculatePagIbigContribution(grossIncome);
        BigDecimal totalContributions = calculateTotalContributions(sss, philHealth, pagIbig);

        payslip.setSssContribution(sss);
        payslip.setPhilHealthContribution(philHealth);
        payslip.setPagIbigContribution(pagIbig);

        // Tax and Deductions
        BigDecimal taxableIncome = calculateTaxableIncome(grossIncome, totalContributions);
        BigDecimal withholdingTax = calculateWithholdingTax(taxableIncome);
        BigDecimal totalDeductions = calculateTotalDeductions(sss, philHealth, pagIbig, withholdingTax);

        payslip.setWithholdingTax(withholdingTax);
        payslip.setTotalDeductions(totalDeductions);

        // Net Income
        BigDecimal netIncome = calculateNetIncome(grossIncome, totalBenefits, totalDeductions);
        payslip.setNetIncome(netIncome);

        // Timestamps
        payslip.setCreatedAt(LocalDateTime.now());
        payslip.setUpdatedAt(LocalDateTime.now());

        return payslip;
    }
    
    private Payslip createZeroPayslip(EmployeeView emp, int payrollProcessID, LocalDate payStartDate, LocalDate payEndDate) {
        AccessControlUtil.requireRole("Finance Admin");
        Payslip payslip = new Payslip();
        
        payslip.setEmployeeID(emp.getEmployeeID());
        payslip.setPayrollProcessID(payrollProcessID);
        payslip.setPayStartDate(payStartDate);
        payslip.setPayEndDate(payEndDate);
        payslip.setDateReleased(LocalDate.now());

        payslip.setEmployeeName(emp.getFullNameWithSurnameFirst());
        payslip.setEmployeePosition(emp.getPositionTitle());
        payslip.setEmployeeDepartment(emp.getDepartmentName());
        payslip.setBasicSalary(emp.getBasicSalary());
        payslip.setHourlyRate(emp.getHourlyRate());

        payslip.setRegularHours(BigDecimal.ZERO);
        payslip.setOvertimeHours(BigDecimal.ZERO);
        payslip.setDaysWorked(0);
        payslip.setGrossIncome(BigDecimal.ZERO);
        
        payslip.setRiceSubsidy(BigDecimal.ZERO);
        payslip.setClothingAllowance(BigDecimal.ZERO);
        payslip.setPhoneAllowance(BigDecimal.ZERO);
        payslip.setTotalBenefits(BigDecimal.ZERO);

        payslip.setSssContribution(BigDecimal.ZERO);
        payslip.setPhilHealthContribution(BigDecimal.ZERO);
        payslip.setPagIbigContribution(BigDecimal.ZERO);

        payslip.setWithholdingTax(BigDecimal.ZERO);
        payslip.setTotalDeductions(BigDecimal.ZERO);
        payslip.setNetIncome(BigDecimal.ZERO);

        payslip.setCreatedAt(LocalDateTime.now());
        payslip.setUpdatedAt(LocalDateTime.now());

        return payslip;
    }
    
}
