package test;

import model.dao.UserAccountDAO;
import model.pojo.UserAccount;
import org.junit.*;
import org.junit.rules.TestName;
import service.ReportService;
import util.Session;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class ReportServiceTest {

    private static ReportService service;

    private static UserAccountDAO userDao;
    private static UserAccount hrAdmin;
    private static UserAccount itAdmin;
    private static UserAccount financeAdmin;
    private static UserAccount employee;

    private static int samplePayslipId;
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
        System.setProperty("java.awt.headless", "true");
        service = new ReportService();
        userDao = new UserAccountDAO();

        hrAdmin = userDao.getById(7); // HR Admin account
        financeAdmin = userDao.getById(11); // Finance Admin account
        itAdmin = userDao.getById(5); // IT Admin account
        employee = userDao.getById(13); // Regular employee account

        assertNotNull(hrAdmin);
        assertNotNull(itAdmin);
        assertNotNull(financeAdmin);
        assertNotNull(employee);

        samplePayslipId = 13;
        payStart = LocalDate.of(2024, 6, 1);
        payEnd = LocalDate.of(2024, 6, 30);
    }

    // Run code as a specific user
    private void runAs(UserAccount user, Runnable r) {
        UserAccount prev = Session.getCurrentUser();
        Session.setCurrentUser(user);
        try {
            r.run();
        } finally {
            Session.setCurrentUser(prev);
        }
    }

    // Expect a SecurityException for unauthorized access
    private void expectDenied(UserAccount user, Runnable r) {
        try {
            runAs(user, r);
            fail("Expected SecurityException");
        } catch (SecurityException ok) {
            System.out.println("   ✔ Access denied (role=" + user.getRoleID() + ")");
        }
    }

    // Finance Admin can generate a payslip
    @Test
    public void testGeneratePayslip_asFinanceAdmin() {
        runAs(financeAdmin, () -> {
            try {
                service.generatePayslip(samplePayslipId);
                System.out.println("   ✔ Payslip generated without error");
            } catch (Exception ex) {
                fail("Unexpected error: " + ex.getMessage());
            }
        });
    }
    
    // Regular employee can generate a payslip
    @Test
    public void testGeneratePayslip_asEmployee() {
        runAs(employee, () -> {
            try {
                service.generatePayslip(samplePayslipId);
                System.out.println("   ✔ Payslip generated without error (Employee)");
            } catch (Exception ex) {
                fail("Unexpected error: " + ex.getMessage());
            }
        });
    }

    // Finance Admin can generate payroll summary
    @Test
    public void testGenerateMonthlyPayrollSummary_asFinanceAdmin() {
        runAs(financeAdmin, () -> {
            try {
                service.generateMonthlyPayrollSummary(payStart, payEnd);
                System.out.println("   ✔ Monthly payroll summary generated");
            } catch (Exception ex) {
                fail("Unexpected error: " + ex.getMessage());
            }
        });
    }

    // HR Admin is denied access to generate payroll summary
    @Test
    public void testGenerateMonthlyPayrollSummary_deniedForHRAdmin() {
        expectDenied(hrAdmin, () -> service.generateMonthlyPayrollSummary(payStart, payEnd));
    }
    
    // IT Admin is denied access to generate payroll summary
    @Test
    public void testGenerateMonthlyPayrollSummary_deniedForITAdmin() {
        expectDenied(itAdmin, () -> service.generateMonthlyPayrollSummary(payStart, payEnd));
    }

    // Employee is denied access to generate payroll summary
    @Test
    public void testGenerateMonthlyPayrollSummary_deniedForEmployee() {
        expectDenied(employee, () -> service.generateMonthlyPayrollSummary(payStart, payEnd));
    }

    // Cleanup
    @AfterClass
    public static void tearDown() {
        Session.setCurrentUser(null);
    }
    
}
