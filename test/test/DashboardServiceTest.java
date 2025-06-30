package test;

import model.dao.*;
import model.pojo.*;
import org.junit.*;
import org.junit.rules.TestName;
import service.DashboardService;
import util.Session;

import static org.junit.Assert.*;

public class DashboardServiceTest {

    private static DashboardService service;
    private static UserAccountDAO userDao;

    private static UserAccount hrAdmin;
    private static UserAccount financeAdmin;
    private static UserAccount itAdmin;
    private static UserAccount employee;

    // Test name banner
    @Rule
    public TestName testName = new TestName();

    @Before
    public void announce() {
        System.out.println("\n▶ " + testName.getMethodName());
    }

    // Setup
    @BeforeClass
    public static void setUp() {
        service = new DashboardService();
        userDao = new UserAccountDAO();

        hrAdmin = userDao.getById(7); // HR Admin account
        financeAdmin = userDao.getById(11); // Finance Admin account
        itAdmin = userDao.getById(5); // IT Admin account
        employee = userDao.getById(13); // Regular employee account
        
        Session.setCurrentUser(hrAdmin); // default context
    }

    // Runs code as specified user account
    private void runAs(UserAccount user, Runnable code) {
        UserAccount prev = Session.getCurrentUser();
        Session.setCurrentUser(user);
        try {
            code.run();
        } finally {
            Session.setCurrentUser(prev);
        }
    }
    
    // Asserts that given action throws SecurityException for given user
    private void expectDenied(UserAccount user, Runnable code) {
        try {
            runAs(user, code);
            fail("Expected SecurityException");
        } catch (SecurityException ok) {
            System.out.println("   ✔ Access denied for roleID=" + user.getRoleID());
        }
    }

    // Asserts that the given value is non-negative
    private void assertNonNegative(long v) {
        assertTrue(v >= 0);
    }

    // Returns the employee view for the currently logged-in user
    @Test
    public void testGetProfile_returnsCurrentUserView() {
        EmployeeView view = service.getProfile();
        System.out.println("   Employee ID returned=" + view.getEmployeeID());
        assertEquals(hrAdmin.getEmployeeID(), view.getEmployeeID());
    }

    // Returns non-negative counts of pending requests for employee
    @Test
    public void testEmployeeSpecificPendingCounts_nonNegative() {
        long lv = service.getTotalPendingLeavesByEmployeeId();
        long ot = service.getTotalPendingOvertimeByEmployeeId();
        long tk = service.getTotalPendingTicketsByEmployeeId();
        System.out.println("   Leaves=" + lv + " OT=" + ot + " Tickets=" + tk);
        assertNonNegative(lv);
        assertNonNegative(ot);
        assertNonNegative(tk);
    }

    // Returns non-negative total number of departments
    @Test
    public void testGetTotalDepartments_nonNegative() {
        long d = service.getTotalDepartments();
        System.out.println("   Department count=" + d);
        assertNonNegative(d);
    }

    // HR Admin can view total number of employees
    @Test
    public void testGetTotalEmployees_asHrAdmin() {
        runAs(hrAdmin, () -> {
            long cnt = service.getTotalEmployees();
            System.out.println("   HR Admin sees employees=" + cnt);
            assertNonNegative(cnt);
        });
    }

    // Finance Admin can view total number of employees
    @Test
    public void testGetTotalEmployees_asFinanceAdmin() {
        runAs(financeAdmin, () -> {
            long cnt = service.getTotalEmployees();
            System.out.println("   Finance Admin sees employees=" + cnt);
            assertNonNegative(cnt);
        });
    }

    // IT Admin can view total number of employees
    @Test
    public void testGetTotalEmployees_asItAdmin() {
        runAs(itAdmin, () -> {
            long cnt = service.getTotalEmployees();
            System.out.println("   IT Admin sees employees=" + cnt);
            assertNonNegative(cnt);
        });
    }

    // HR Admin can view total pending leave requests
    @Test
    public void testGetTotalPendingLeaves_asHrAdmin() {
        runAs(hrAdmin, () -> {
            long cnt = service.getTotalPendingLeaves();
            System.out.println("   Pending leaves=" + cnt);
            assertNonNegative(cnt);
        });
    }

    // Finance Admin is denied access to pending leave requests
    @Test
    public void testGetTotalPendingLeaves_deniedForFinanceAdmin() {
        expectDenied(financeAdmin, () -> service.getTotalPendingLeaves());
    }

    // HR Admin can view total pending overtime requests
    @Test
    public void testGetTotalPendingOvertime_asHrAdmin() {
        runAs(hrAdmin, () -> {
            long cnt = service.getTotalPendingOvertime();
            System.out.println("   Pending overtime=" + cnt);
            assertNonNegative(cnt);
        });
    }

    // Finance Admin is denied access to pending overtime requests
    @Test
    public void testGetTotalPendingOvertime_deniedForFinanceAdmin() {
        expectDenied(financeAdmin, () -> service.getTotalPendingOvertime());
    }

    // HR Admin can view pending support tickets for HR team
    @Test
    public void testGetPendingTicketsForTeam_asHrAdmin() {
        runAs(hrAdmin, () -> {
            long cnt = service.getTotalPendingTicketsForTeam("HR");
            System.out.println("   HR team pending tickets=" + cnt);
            assertNonNegative(cnt);
        });
    }

    // Finance Admin can view pending support tickets for Finance team
    @Test
    public void testGetPendingTicketsForTeam_asFinanceAdmin() {
        runAs(financeAdmin, () -> {
            long cnt = service.getTotalPendingTicketsForTeam("Finance");
            System.out.println("   Finance team pending tickets=" + cnt);
            assertNonNegative(cnt);
        });
    }

    // IT Admin can view pending support tickets for IT team
    @Test
    public void testGetPendingTicketsForTeam_asItAdmin() {
        runAs(itAdmin, () -> {
            long cnt = service.getTotalPendingTicketsForTeam("IT");
            System.out.println("   IT team pending tickets=" + cnt);
            assertNonNegative(cnt);
        });
    }

    // Regular employee is denied access to team support tickets
    @Test
    public void testGetPendingTicketsForTeam_deniedForEmployee() {
        expectDenied(employee, () -> service.getTotalPendingTicketsForTeam("HR"));
    }

    // Requesting tickets for a non-existent team returns 0
    @Test
    public void testGetPendingTicketsForNonexistentTeam_returnsZero() {
        runAs(hrAdmin, () -> {
            long cnt = service.getTotalPendingTicketsForTeam("NO_SUCH_TEAM");
            System.out.println("   Non-existent team tickets=" + cnt);
            assertEquals(0, cnt);
        });
    }

    // Returns non-empty password of current session user
    @Test
    public void testGetCurrentUserPassword_notNull() {
        String pwd = service.getCurrentUserPassword();
        System.out.println("   Password='" + pwd + "'");
        assertNotNull(pwd);
        assertFalse(pwd.trim().isEmpty());
    }

    // Cleanup
    @AfterClass
    public static void tearDown() {
        Session.setCurrentUser(null);
    }
    
}