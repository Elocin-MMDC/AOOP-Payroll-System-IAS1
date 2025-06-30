package test;

import model.dao.UserAccountDAO;
import model.pojo.UserAccount;
import model.pojo.AuditLog;
import model.pojo.LoginLog;
import org.junit.*;
import org.junit.rules.TestName;
import service.LogService;
import util.Session;
import java.util.List;
import static org.junit.Assert.*;

public class LogServiceTest {

    private static LogService  service;

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
    public static void setUpClass() {
        service  = new LogService();
        userDao  = new UserAccountDAO();

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
        try { code.run(); }
        finally { Session.setCurrentUser(prev); }
    }

    // Asserts that given action throws SecurityException for given user
    private void expectDenied(UserAccount u, Runnable code) {
        try {
            runAs(u, code);
            fail("Expected SecurityException");
        } catch (SecurityException ok) {
            System.out.println("   ✔ Access denied for roleID=" + u.getRoleID());
        }
    }

    // IT Admin can retrieve all audit logs
    @Test
    public void testGetAllAuditLogs_asITAdmin() {
        runAs(itAdmin, () -> {
            List<AuditLog> logs = service.getAllAuditLogs();
            System.out.println("   Audit logs returned=" + logs.size());
            assertNotNull(logs);
        });
    }

    // IT Admin can retrieve all login logs
    @Test
    public void testGetAllLoginLogs_asITAdmin() {
        runAs(itAdmin, () -> {
            List<LoginLog> logs = service.getAllLoginLogs();
            System.out.println("   Login logs returned=" + logs.size());
            assertNotNull(logs);
        });
    }

    // HR Admin denied access to audit logs
    @Test
    public void testGetAllAuditLogs_deniedForHrAdmin() {
        expectDenied(hrAdmin, () -> service.getAllAuditLogs());
    }

    // Finance Admin denied access to audit logs
    @Test
    public void testGetAllAuditLogs_deniedForFinanceAdmin() {
        expectDenied(financeAdmin, () -> service.getAllAuditLogs());
    }

    // Regular employee denied access to audit logs
    @Test
    public void testGetAllAuditLogs_deniedForEmployee() {
        expectDenied(employee, () -> service.getAllAuditLogs());
    }

    // HR Admin denied access to login logs
    @Test
    public void testGetAllLoginLogs_deniedForHrAdmin() {
        expectDenied(hrAdmin, () -> service.getAllLoginLogs());
    }

    // Finance Admin denied access to login logs
    @Test
    public void testGetAllLoginLogs_deniedForFinanceAdmin() {
        expectDenied(financeAdmin, () -> service.getAllLoginLogs());
    }

    // Regular employee denied access to login logs
    @Test
    public void testGetAllLoginLogs_deniedForEmployee() {
        expectDenied(employee, () -> service.getAllLoginLogs());
    }

    // Cleanup
    @AfterClass
    public static void tearDown() {
        Session.setCurrentUser(null);
    }
    
}