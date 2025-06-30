package test;

import model.dao.UserAccountDAO;
import model.pojo.UserAccount;
import model.pojo.Attendance;
import org.junit.*;
import org.junit.rules.TestName;
import service.AttendanceService;
import util.Session;
import java.util.List;
import static org.junit.Assert.*;

public class AttendanceServiceTest {

    private static AttendanceService service;
    private static UserAccountDAO userDao;
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
        service = new AttendanceService();
        userDao = new UserAccountDAO();
        employee = userDao.getById(13);
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

    // Verifies getHistory() returns a non-null attendance list
    @Test
    public void testGetHistory_returnsList() {
        runAs(employee, () -> {
            List<Attendance> list = service.getHistory(employee.getEmployeeID());
            System.out.println("   Attendance history size = " + list.size());
            assertNotNull(list);
        });
    }

    // Verifies initial attendance state is not null
    @Test
    public void testGetState_initialState() {
        runAs(employee, () -> {
            AttendanceService.State state = service.getState();
            System.out.println("   Attendance state = " + state);
            assertNotNull(state);
        });
    }

    // Verifies getFormattedClockIn() returns a string (may be empty)
    @Test
    public void testFormattedClockIn_ifNotClockedIn() {
        runAs(employee, () -> {
            String clockIn = service.getFormattedClockIn();
            System.out.println("   Formatted clock-in = '" + clockIn + "'");
            assertNotNull(clockIn);
        });
    }

    // Verifies getFormattedClockOut() returns a string (may be empty)
    @Test
    public void testFormattedClockOut_ifNotClockedOut() {
        runAs(employee, () -> {
            String clockOut = service.getFormattedClockOut();
            System.out.println("   Formatted clock-out = '" + clockOut + "'");
            assertNotNull(clockOut);
        });
    }

    // Verifies getFormattedHoursWorked() returns a valid string
    @Test
    public void testFormattedHoursWorked_outputFormat() {
        runAs(employee, () -> {
            String hours = service.getFormattedHoursWorked();
            System.out.println("   Formatted hours worked = '" + hours + "'");
            assertNotNull(hours);
        });
    }

    // Cleanup
    @AfterClass
    public static void tearDownClass() {
        Session.setCurrentUser(null);
    }
}