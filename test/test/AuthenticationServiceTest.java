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

    // Stores a known password for the account using the real salted PBKDF2 hashing
    private static void setKnownPassword(int userId, String plainPassword) {
        UserAccount u = userDao.getById(userId);
        PasswordUtil.applyHashedPassword(u, plainPassword);
        assertTrue(userDao.resetPassword(userId, u.getPassword(), u.getPasswordSalt()));
    }

    // Restores the original password hash and salt captured before a test changed them
    private static void restorePassword(UserAccount original) {
        userDao.resetPassword(original.getUserID(), original.getPassword(), original.getPasswordSalt());
    }

    // Correct password only issues a pre-authentication challenge; no session until TOTP (Phase 4)
    @Test
    public void testLogin_success() throws Exception {
        UserAccount original = userDao.getById(hrAdmin.getUserID());
        String tmpPw = "tmp-" + System.nanoTime();
        AuthenticationService.PreAuthChallenge challenge = null;

        try {
            setKnownPassword(hrAdmin.getUserID(), tmpPw);

            challenge = service.login(hrAdmin.getUsername(), tmpPw);
            assertNotNull(challenge.token());
            assertEquals(hrAdmin.getUsername(), challenge.username());
            assertEquals(!original.isMfaEnabled(), challenge.enrollmentRequired());
            assertNull("Session must not exist before the TOTP step", Session.getCurrentUser());
            System.out.println("   ✔ Password accepted, pre-auth challenge issued, no session yet");
        } finally {
            if (challenge != null) {
                service.cancelLogin(challenge.token());
            }
            restorePassword(original);
            Session.clear();
        }
    }

    // A forged or cancelled pre-authentication token cannot complete a login
    @Test
    public void testCompleteLogin_rejectsInvalidToken() {
        try {
            service.completeLogin("forged-token", "123456");
            fail("Forged token accepted");
        } catch (AuthenticationException ex) {
            assertTrue(ex.getMessage().contains("expired"));
            assertNull(Session.getCurrentUser());
            System.out.println("   ✔ Forged token rejected, no session created");
        }
    }

    @Test
    public void testCompleteEnforcedPasswordReset_success() throws Exception {
        UserAccount original = userDao.getById(employee.getUserID());
        boolean originalMustChange = Boolean.TRUE.equals(original.getMustChangePassword());
        String temporaryPassword = "first-login-" + System.nanoTime();
        String newPassword = "changed-" + System.nanoTime();
        AuditLogDAO auditDao = new AuditLogDAO();
        int lastAuditIdBeforeReset = auditDao.getAll().stream()
                .mapToInt(AuditLog::getAuditID)
                .max()
                .orElse(0);
        AuthenticationService.PreAuthChallenge challenge = null;

        try {
            setKnownPassword(employee.getUserID(), temporaryPassword);
            assertTrue(userDao.updateMustChangePasswordFlag(employee.getUserID(), true));

            challenge = service.login(employee.getUsername(), temporaryPassword);
            assertNotNull(challenge.token());
            assertTrue(Boolean.TRUE.equals(userDao.getById(employee.getUserID()).getMustChangePassword()));

            // The forced reset runs after the TOTP step, when the session exists
            Session.setCurrentUser(userDao.getById(employee.getUserID()));
            service.completeEnforcedPasswordReset(employee.getUserID(), newPassword);

            UserAccount updated = userDao.getById(employee.getUserID());
            assertTrue(PasswordUtil.verifyUserPassword(newPassword, updated));
            assertFalse(Boolean.TRUE.equals(updated.getMustChangePassword()));
            assertFalse(Boolean.TRUE.equals(Session.getCurrentUser().getMustChangePassword()));
            assertTrue(auditDao.getAll().stream().anyMatch(log ->
                    log.getAuditID() > lastAuditIdBeforeReset
                && log.getUserID() == employee.getUserID()
                && "UPDATE".equals(log.getAction())
                && "UserAccount".equals(log.getEntityModified())
                && log.getEntityID() == employee.getUserID()
                && "mustChangePassword".equals(log.getAttributeModified())
                && "true".equals(log.getOldValue())
                && "false".equals(log.getNewValue())));
        } finally {
            if (challenge != null) {
                service.cancelLogin(challenge.token());
            }
            restorePassword(original);
            userDao.updateMustChangePasswordFlag(employee.getUserID(), originalMustChange);
            Session.clear();
        }
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
        UserAccount original = userDao.getById(employee.getUserID());
        String currPw = "pw-" + System.currentTimeMillis();
        String newPw = "new-" + System.nanoTime();

        try {
            setKnownPassword(employee.getUserID(), currPw);

            service.changePassword(employee.getUserID(), currPw, newPw);
            assertTrue(PasswordUtil.verifyUserPassword(newPw, userDao.getById(employee.getUserID())));
            System.out.println("   ✔ changePassword updated hash");
        } finally {
            restorePassword(original);
        }
    }

    // Incorrect current password should throw exception
    @Test(expected = AuthenticationException.class)
    public void testChangePassword_wrongCurrentThrows() throws Exception {
        service.changePassword(employee.getUserID(), "bad-current", "whatever");
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
