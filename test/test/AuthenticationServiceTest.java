package test;

import gui.admin.finance.AdminFinancePortal;
import gui.admin.hr.AdminHRPortal;
import gui.admin.it.AdminITPortal;
import model.dao.*;
import model.pojo.*;
import org.junit.*;
import org.junit.rules.TestName;
import service.AuthenticationService;
import util.PasswordUtil;
import util.Session;
import javax.swing.JFrame;
import static org.junit.Assert.*;
import service.AuthenticationService.AuthenticationException;

public class AuthenticationServiceTest {

    private static AuthenticationService service;

    private static UserAccountDAO userDao;
    private static EmployeeDAO empDao;
    private static GovInformationDAO govDao;
    private static LoginLogDAO logDao;

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
        service = new AuthenticationService();
        userDao = new UserAccountDAO();
        empDao = new EmployeeDAO();
        govDao = new GovInformationDAO();
        logDao = new LoginLogDAO();

        hrAdmin = userDao.getById(7); // HR Admin account
        financeAdmin = userDao.getById(11); // Finance Admin account
        itAdmin = userDao.getById(5); // IT Admin account
        employee = userDao.getById(13); // Regular employee account

        assertNotNull(hrAdmin);
        assertNotNull(financeAdmin);
        assertNotNull(itAdmin);
        assertNotNull(employee);
    }

    // Runs code as specified user account
    private void runAs(UserAccount u, Runnable code) {
        UserAccount prev = Session.getCurrentUser();
        Session.setCurrentUser(u);
        try {
            code.run();
        } finally {
            Session.setCurrentUser(prev);
        }
    }

    // Valid login sets session and returns correct user
    @Test
    public void testLogin_success() throws Exception {
        String tmpPw = "tmp-" + System.nanoTime();
        String oldHash = hrAdmin.getPassword();

        hrAdmin.setPassword(tmpPw);
        userDao.update(hrAdmin);

        UserAccount logged = service.login(hrAdmin.getUsername(), tmpPw);
        System.out.println("   Logged userID=" + logged.getUserID());
        assertEquals(hrAdmin.getUserID(), logged.getUserID());
        assertEquals(hrAdmin.getUserID(), Session.getCurrentUser().getUserID());
        System.out.println("   ✔ Login & Session set");

        service.logout();

        hrAdmin.setPassword(oldHash);
        userDao.update(hrAdmin);
    }

    // Null username should trigger authentication exception
    @Test(expected = AuthenticationException.class)
    public void testLogin_nullUsernameThrows() throws Exception {
        service.login(null, "whatever");
    }

    // Invalid password should trigger authentication exception
    @Test(expected = AuthenticationException.class)
    public void testLogin_badPasswordThrows() throws Exception {
        service.login(hrAdmin.getUsername(), "wrong-pw");
    }

    // Locks out account after 3 failed login attempts
    @Test
    public void test_Login_lockoutAfterThreeFailures() throws Exception {
        String password = financeAdmin.getPassword();

        // 3 failed attempts
        for (int i = 1; i <= 3; i++) {
            try {
                service.login(financeAdmin.getUsername(), "bad" + i);
            } catch (AuthenticationException ex) {
                System.out.println("   Failure " + i + "/3 recorded");
            }
        }

        assertTrue("Should now be locked", service.isLockedOut(financeAdmin.getUserID()));

        try {
            service.login(financeAdmin.getUsername(), password);
            fail("Lockout not enforced");
        } catch (AuthenticationException ex) {
            System.out.println("   ✔ Lock-out enforced");
        }

    }

    // Password is changed when correct current password is provided
    @Test
    public void testChangePassword_success() throws Exception {
        String oldHash = employee.getPassword();
        String currPw = "pw-" + System.currentTimeMillis();
        String newPw = "new-" + System.nanoTime();
        employee.setPassword(currPw);
        userDao.update(employee);

        service.changePassword(employee.getUserID(), currPw, newPw);
        String dbHash = userDao.getById(employee.getUserID()).getPassword();
        assertTrue(PasswordUtil.verify(newPw, dbHash));
        System.out.println("   ✔ changePassword updated hash");

        employee.setPassword(oldHash); // restore
        userDao.update(employee);
    }

    // Incorrect current password should throw exception
    @Test(expected = AuthenticationException.class)
    public void testChangePassword_wrongCurrentThrows() throws Exception {
        service.changePassword(employee.getUserID(), "bad-current", "whatever");
    }

    // Verify identity should return true for correct details and false otherwise
    @Test
    public void testVerifyIdentity() {
        Employee e = empDao.getById(employee.getEmployeeID());
        GovInformation gi = govDao.getById(e.getGovID());

        boolean ok = service.verifyIdentity(employee.getEmployeeID(),
                e.getBirthday().toString(),
                gi.getSssNumber());
        boolean bad = service.verifyIdentity(employee.getEmployeeID(),
                "1900-01-01",
                "00-0000000-0");
        System.out.println("   verifyIdentity ok=" + ok + " bad=" + bad);
        assertTrue(ok);
        assertFalse(bad);
    }

    // Successfully resets password when identity is valid
    @Test
    public void testResetPassword_success() throws Exception {
        Employee e = empDao.getById(employee.getEmployeeID());
        GovInformation gi = govDao.getById(e.getGovID());
        String oldHash = employee.getPassword();
        String newPw = "reset-" + System.nanoTime();

        service.resetPassword(employee.getUsername(),
                e.getBirthday().toString(),
                gi.getSssNumber(),
                newPw);
        assertTrue(PasswordUtil.verify(newPw, userDao.getById(employee.getUserID()).getPassword()));
        System.out.println("   ✔ resetPassword changed hash");

        employee.setPassword(oldHash);
        userDao.update(employee);
    }

    // Invalid identity should throw authentication exception
    @Test(expected = AuthenticationException.class)
    public void testResetPassword_badIdentityThrows() throws Exception {
        service.resetPassword(employee.getUsername(),
                "1900-12-31",
                "00-0000000-0",
                "whatever");
    }

    // Verifies user has authorized role from list
    @Test
    public void testIsAuthorized() {
        assertTrue(service.isAuthorized(hrAdmin, "HR Admin", "IT Admin"));
        assertFalse(service.isAuthorized(employee, "HR Admin"));
        System.out.println("   ✔ isAuthorized matches role list");
    }

    // getCurrentRole and isAdmin helper behaviour per user type
    @Test
    public void testGetCurrentRole_and_isAdmin() {
        runAs(hrAdmin, () -> {
            assertEquals("HR Admin", service.getCurrentRole().getRoleName());
            assertTrue(service.isAdmin());
        });
        runAs(employee, () -> {
            assertEquals("Employee", service.getCurrentRole().getRoleName());
            assertFalse(service.isAdmin());
        });
    }

    // Admin users are routed to correct portal frame
    @Test
    public void testLaunchAdminPortal_dispatch() {
        runAs(hrAdmin, () -> {
            JFrame f = service.launchAdminPortal();
            assertEquals(AdminHRPortal.class, f.getClass());
        });
        runAs(financeAdmin, () -> {
            JFrame f = service.launchAdminPortal();
            assertEquals(AdminFinancePortal.class, f.getClass());
        });
        runAs(itAdmin, () -> {
            JFrame f = service.launchAdminPortal();
            assertEquals(AdminITPortal.class, f.getClass());
        });
        runAs(employee, () -> {
            assertNull(service.launchAdminPortal());
        });
        System.out.println("   ✔ launchAdminPortal returned correct frames");
    }

    // Retrieves non-blank full name from session user
    @Test
    public void testGetCurrentUserFullName() {
        runAs(employee, () -> {
            String full = service.getCurrentUserFullName();
            System.out.println("   Full name=" + full);
            assertFalse(full.isBlank());
        });
    }

    // Cleanup
    @AfterClass
    public static void tearDown() {
        logDao.clearLockState(hrAdmin.getUserID());
        logDao.clearLockState(financeAdmin.getUserID());
        Session.setCurrentUser(null);
    }

}
