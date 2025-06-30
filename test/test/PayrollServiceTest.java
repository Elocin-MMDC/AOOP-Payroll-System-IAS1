package test;

import model.dao.*;
import model.pojo.*;
import org.junit.*;
import org.junit.rules.TestName;
import service.PayrollService;
import util.Session;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import static org.junit.Assert.*;

public class PayrollServiceTest {

    private static PayrollService service;

    private static UserAccountDAO userDao;
    private static EmployeeViewDAO empDao;
    private static AttendanceDAO attDao;
    private static SSSMatrixDAO sssDao;
    private static PhilHealthMatrixDAO philHealthDao;
    private static PagIbigMatrixDAO pagIbigDao;
    private static WithholdingTaxMatrixDAO taxDao;

    private static UserAccount financeAdmin;
    private static UserAccount hrAdmin;
    private static UserAccount itAdmin;
    private static UserAccount employee;

    private static EmployeeView sampleEmp;
    private static LocalDate payStart;
    private static LocalDate payEnd;

    // Test name banner
    @Rule
    public TestName testName = new TestName();

    @Before
    public void announce() {
        System.out.println("\n▶ " + testName.getMethodName());
    }

    // Setup
    @BeforeClass
    public static void setUpClass() {
        service = new PayrollService();

        userDao = new UserAccountDAO();
        empDao = new EmployeeViewDAO();
        attDao = new AttendanceDAO();
        sssDao = new SSSMatrixDAO();
        philHealthDao = new PhilHealthMatrixDAO();
        pagIbigDao = new PagIbigMatrixDAO();
        taxDao = new WithholdingTaxMatrixDAO();

        hrAdmin = userDao.getById(7); // HR Admin account
        financeAdmin = userDao.getById(11); // Finance Admin account
        itAdmin = userDao.getById(5); // IT Admin account
        employee = userDao.getById(13); // Regular employee account

        assertNotNull(financeAdmin);
        assertNotNull(hrAdmin);
        assertNotNull(itAdmin);
        assertNotNull(employee);

        // Sample employee and pay period
        sampleEmp = empDao.getById(employee.getEmployeeID());
        assertNotNull(sampleEmp);
        payStart = LocalDate.of(2024, 6, 1);
        payEnd = LocalDate.of(2024, 6, 30);
    }

    // Runs code as specified user account
    private void runAs(UserAccount u, Runnable r) {
        UserAccount prev = Session.getCurrentUser();
        Session.setCurrentUser(u);
        try {
            r.run();
        } finally {
            Session.setCurrentUser(prev);
        }
    }

    // Asserts that given action throws SecurityException for given user
    private void expectDenied(UserAccount u, Runnable r) {
        try {
            runAs(u, r);
            fail("Expected SecurityException");
        } catch (SecurityException ok) {
            System.out.println("   ✔ Access denied (role=" + u.getRoleID() + ")");
        }
    }

    // Verifies gross-income computation matches manual formula
    @Test
    public void testCalculateGrossIncome_matchesManual() {
        BigDecimal hr = sampleEmp.getHourlyRate();
        BigDecimal regHrs = attDao.getTotalRegularHours(sampleEmp.getEmployeeID(), payStart, payEnd);
        BigDecimal otHrs = attDao.getTotalOvertimeHours(sampleEmp.getEmployeeID(), payStart, payEnd);

        BigDecimal expected = hr.multiply(regHrs)
                .add(hr.multiply(otHrs).multiply(new BigDecimal("1.25")));
        BigDecimal actual = service.calculateGrossIncome(sampleEmp, payStart, payEnd);

        System.out.println("   Expected=" + expected + " Actual=" + actual);
        assertEquals(0, expected.compareTo(actual));
        System.out.println("   ✔ Gross income formula correct");
    }

    // Verifies days-worked calculation matches DAO total
    @Test
    public void testCalculateDaysWorked_matchesDAO() {
        int daoDays = attDao.getTotalDaysWorked(sampleEmp.getEmployeeID(), payStart, payEnd);
        int calcDays = service.calculateDaysWorked(sampleEmp, payStart, payEnd);
        System.out.println("   DAO=" + daoDays + " Calc=" + calcDays);
        assertEquals(daoDays, calcDays);
        System.out.println("   ✔ Days‑worked matches DAO");
    }

    // Ensures total benefits equals sum of individual allowances
    @Test
    public void testCalculateTotalBenefits_manualMatch() {
        BigDecimal manual = sampleEmp.getRiceSubsidy()
                .add(sampleEmp.getClothingAllowance())
                .add(sampleEmp.getPhoneAllowance());
        BigDecimal auto = service.calculateTotalBenefits(sampleEmp);
        System.out.println("   Manual=" + manual + " Auto=" + auto);
        assertEquals(0, manual.compareTo(auto));
        System.out.println("   ✔ Benefit sum consistent");
    }

    // Checks SSS, PhilHealth, and Pag-IBIG contribution calculators
    @Test
    public void testContribution_calculators_consistent() {
        BigDecimal gross = new BigDecimal("25000");

        // SSS
        SSSMatrix sssRow = sssDao.getByGrossIncome(gross);
        BigDecimal sssExp = sssRow != null ? sssRow.getContributionAmount() : BigDecimal.ZERO;
        BigDecimal sssAct = service.calculateSssContribution(gross);
        System.out.println("   SSS exp=" + sssExp + " act=" + sssAct);
        assertEquals(0, sssExp.compareTo(sssAct));

        // PhilHealth
        PhilHealthMatrix philHealthRow = philHealthDao.getByGrossIncome(gross);
        BigDecimal philHealthExp;
        if (philHealthRow == null) {
            philHealthExp = BigDecimal.ZERO;
        } else if (philHealthRow.getPremiumRate().compareTo(BigDecimal.ZERO) > 0) {
            philHealthExp = gross.multiply(philHealthRow.getPremiumRate()).divide(new BigDecimal("2"), 2, BigDecimal.ROUND_HALF_UP);
        } else {
            philHealthExp = philHealthRow.getContributionAmount().divide(new BigDecimal("2"), 2, BigDecimal.ROUND_HALF_UP);
        }
        BigDecimal phAct = service.calculatePhilhealthContribution(gross);
        System.out.println("   PhilHealth exp=" + philHealthExp + " act=" + phAct);
        assertEquals(0, philHealthExp.compareTo(phAct));

        // Pag-Ibig
        PagIbigMatrix pagIbigRow = pagIbigDao.getByGrossIncome(gross);
        BigDecimal pagIbigExp = pagIbigRow != null
                ? gross.multiply(pagIbigRow.getEmployeeRate()).min(pagIbigRow.getMaxContribution()) : BigDecimal.ZERO;
        BigDecimal pagIbigAct = service.calculatePagIbigContribution(gross);
        System.out.println("   PagIbig exp=" + pagIbigExp + " act=" + pagIbigAct);
        assertEquals(0, pagIbigExp.compareTo(pagIbigAct));
        System.out.println("   ✔ Contribution calculators OK");
    }

    // Validates that tax flow (gross income minus total contributions = taxable income then get corresponding tax) matches matrix
    @Test
    public void testTaxFlow_consistency() {
        BigDecimal gross = new BigDecimal("93388.99");
        
        // Contributions
        BigDecimal sss = service.calculateSssContribution(gross);
        BigDecimal phil = service.calculatePhilhealthContribution(gross);
        BigDecimal pag = service.calculatePagIbigContribution(gross);
        BigDecimal totalContrib = service.calculateTotalContributions(sss, phil, pag);
        assertEquals(0, totalContrib.compareTo(sss.add(phil).add(pag)));

        // Taxable Income
        BigDecimal taxable = service.calculateTaxableIncome(gross, totalContrib);
        assertEquals(0, taxable.compareTo(gross.subtract(totalContrib)));

        // Witholding Tax
        WithholdingTaxMatrix row = taxDao.getByTaxableIncome(taxable);
        BigDecimal expectedTax;
        if (row == null) {
            expectedTax = BigDecimal.ZERO;
        } else {
            BigDecimal excess = taxable.subtract(row.getMinTaxableIncome());
            expectedTax = row.getBaseTax().add(excess.multiply(row.getExcessRate()));
        }
        BigDecimal actTax = service.calculateWithholdingTax(taxable);
        System.out.println("   ExpectedTax=" + expectedTax + " act=" + actTax);
        assertEquals(0, expectedTax.compareTo(actTax));
        System.out.println("   ✔ Tax computation matches matrix");
    }

    // Finance Admin can fetch all payroll records
    @Test
    public void testGetAllPayrollRecords_asFinanceAdmin() {
        runAs(financeAdmin, () -> {
            List<Payslip> list = service.getAllPayrollRecords();
            System.out.println("   Payslips returned=" + list.size());
            assertNotNull(list);
        });
    }

    // Finance Admin can fetch formatted pay periods
    @Test
    public void testGetFormattedPayPeriods_asFinanceAdmin() {
        runAs(financeAdmin, () -> {
            int count = service.getFormattedPayPeriods().size();
            System.out.println("   Pay periods=" + count);
            assertTrue(count > 0);
        });
    }

    // Fetching payroll records denied for non-finance roles
    @Test
    public void testGetAllPayrollRecords_deniedForHRAdmin() {
        expectDenied(hrAdmin, () -> service.getAllPayrollRecords());
    }

    @Test
    public void testGetAllPayrollRecords_deniedForITAdmin() {
        expectDenied(itAdmin, () -> service.getAllPayrollRecords());
    }

    @Test
    public void testGetAllPayrollRecords_deniedForEmployee() {
        expectDenied(employee, () -> service.getAllPayrollRecords());
    }

    // Fetching pay periods denied for non-finance roles
    @Test
    public void testGetFormattedPayPeriods_deniedForHRAdmin() {
        expectDenied(hrAdmin, () -> service.getFormattedPayPeriods());
    }

    @Test
    public void testGetFormattedPayPeriods_deniedForITAdmin() {
        expectDenied(itAdmin, () -> service.getFormattedPayPeriods());
    }

    @Test
    public void testGetFormattedPayPeriods_deniedForEmployee() {
        expectDenied(employee, () -> service.getFormattedPayPeriods());
    }

    // Finance Admin can generate a complete payslip
    @Test
    public void testComputePayslip_generation() {
        runAs(financeAdmin, () -> {
            Payslip slip = service.computePayslip(sampleEmp, -1, payStart, payEnd);
            System.out.println("   NetIncome=" + slip.getNetIncome());
            assertNotNull(slip);
            assertTrue(slip.getNetIncome().compareTo(BigDecimal.ZERO) >= 0);
            System.out.println("   ✔ Payslip generated");
        });
    }

    // Cleanup
    @AfterClass
    public static void tearDown() {
        Session.setCurrentUser(null);
    }
    
}