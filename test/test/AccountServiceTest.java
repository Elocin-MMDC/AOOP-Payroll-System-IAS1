package test;

import model.dao.*;
import model.pojo.*;
import org.junit.*;
import org.junit.rules.TestName;
import service.AccountService;
import util.PasswordUtil;
import util.Session;
import java.util.List;
import static org.junit.Assert.*;

public class AccountServiceTest {

    private static AccountService service;

    private static UserAccountDAO userDao;
    private static RoleDAO roleDao;

    private static UserAccount itAdmin;
    private static UserAccount hrAdmin;
    private static UserAccount financeAdmin;
    private static UserAccount employee;

    private static EmployeeView empView13;

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
        service = new AccountService();
        userDao = new UserAccountDAO();
        roleDao = new RoleDAO();

        hrAdmin = userDao.getById(7); // HR Admin account
        financeAdmin = userDao.getById(11); // Finance Admin account
        itAdmin = userDao.getById(5); // IT Admin account
        employee = userDao.getById(13); // Regular employee account

        assertNotNull(itAdmin);
        assertNotNull(hrAdmin);
        assertNotNull(financeAdmin);
        assertNotNull(employee);

        empView13 = new EmployeeViewDAO().getById(employee.getEmployeeID());
        assertNotNull(empView13);
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

    // IT Admin can view all user accounts
    @Test
    public void testGetAllAccounts_asItAdmin() {
        runAs(itAdmin, () -> {
            List<UserAccountView> list = service.getAllAccounts();
            System.out.println("   Accounts=" + list.size());
            assertNotNull(list);
        });
    }

    // IT Admin can view employee details
    @Test
    public void testGetEmployeeDetails_asItAdmin() {
        runAs(itAdmin, () -> {
            EmployeeView v = service.getEmployeeDetails(empView13.getEmployeeID());
            System.out.println("   Employee firstName=" + v.getFirstName());
            assertEquals(empView13.getEmployeeID(), v.getEmployeeID());
        });
    }

    // IT Admin can retrieve a role by ID
    @Test
    public void testGetRoleById_asItAdmin() {
        int roleId = roleDao.getIdByName("Employee");
        runAs(itAdmin, () -> {
            Role r = service.getRoleById(roleId);
            System.out.println("   Role name=" + r.getRoleName());
            assertEquals("Employee", r.getRoleName());
        });
    }

    // IT Admin can retrieve all roles
    @Test
    public void testGetAllRoles_asItAdmin() {
        runAs(itAdmin, () -> {
            List<Role> roles = service.getAllRoles();
            System.out.println("   Roles count=" + roles.size());
            assertTrue(roles.size() >= 4); // assume at least 4 roles seeded
        });
    }

    // IT Admin can get account details by user ID
    @Test
    public void testGetAccountById_asItAdmin() {
        runAs(itAdmin, () -> {
            UserAccount ua = service.getAccountById(employee.getUserID());
            System.out.println("   Username=" + ua.getUsername());
            assertEquals(employee.getUsername(), ua.getUsername());
        });
    }

    // HR Admin should be denied from viewing all accounts
    @Test
    public void testGetAllAccounts_deniedForHRAdmin() {
        expectDenied(hrAdmin, () -> service.getAllAccounts());
    }

    // Finance Admin should be denied from viewing all accounts
    @Test
    public void testGetAllAccounts_deniedForFinanceAdmin() {
        expectDenied(financeAdmin, () -> service.getAllAccounts());
    }

    // Regular employee should be denied from viewing all accounts
    @Test
    public void testGetAllAccounts_deniedForEmployee() {
        expectDenied(employee, () -> service.getAllAccounts());
    }

    // Finance Admin should be denied from viewing a role
    @Test
    public void testGetRoleById_deniedForFinanceAdmin() {
        expectDenied(financeAdmin, () -> service.getRoleById(1));
    }

    // Regular employee should be denied from retrieving all roles
    @Test
    public void testGetAllRoles_deniedForEmployee() {
        expectDenied(employee, () -> service.getAllRoles());
    }

    // IT Admin can successfully reset a user’s password
    @Test
    public void testResetPassword_success() throws Exception {
        int userId = employee.getUserID();
        String oldHash = employee.getPassword();
        String expectedHash = PasswordUtil.sha256Hash("temppassword");

        runAs(itAdmin, () -> {
            try {
                boolean ok = service.resetPassword(userId);
                System.out.println("   resetPassword() returned=" + ok);
                assertTrue(ok);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        UserAccount after = userDao.getById(userId);
        assertEquals(expectedHash, after.getPassword());
        System.out.println("   ✔ Password reset hash stored");

        // Restore original password
        userDao.resetPassword(userId, oldHash);
    }

    // IT Admin can update username, role, and account status
    @Test
    public void testUpdateAccount_allFields() throws Exception {
        int userId = employee.getUserID();
        UserAccount before = userDao.getById(userId);

        String newUsername = before.getUsername() + "_junit";
        String newRoleName = "Finance Admin";
        String newStatus = "Deactivated";

        runAs(itAdmin, () -> {
            try {
                boolean ok = service.updateAccount(userId, newUsername, newRoleName, newStatus);
                System.out.println("   updateAccount() returned=" + ok);
                assertTrue(ok);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        UserAccount after = userDao.getById(userId);
        System.out.println("   Username=" + after.getUsername() + " RoleID=" + after.getRoleID() + " Status=" + after.getAccountStatus());
        assertEquals(newUsername, after.getUsername());
        assertEquals(roleDao.getIdByName(newRoleName), after.getRoleID());
        assertEquals(newStatus, after.getAccountStatus());
        System.out.println("   ✔ Account fields updated");

        // Rollback to original values
        runAs(itAdmin, () -> {
            try {
                service.updateAccount(userId, before.getUsername(), roleDao.getById(before.getRoleID()).getRoleName(), before.getAccountStatus());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    // Cleanup
    @AfterClass
    public static void tearDown() {
        Session.setCurrentUser(null);
    }
    
}