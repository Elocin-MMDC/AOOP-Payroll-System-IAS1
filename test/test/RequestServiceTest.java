package test;

import model.dao.*;
import model.pojo.*;
import org.junit.*;
import org.junit.rules.TestName;
import service.RequestService;
import util.Session;
import java.util.List;
import static org.junit.Assert.*;

public class RequestServiceTest {

    private static RequestService service;
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
        service = new RequestService();
        userDao = new UserAccountDAO();

        hrAdmin = userDao.getById(7); // HR Admin account
        financeAdmin = userDao.getById(11); // Finance Admin account
        itAdmin = userDao.getById(5); // IT Admin account
        employee = userDao.getById(13); // Regular employee account
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

    // Leaves
    // Returns non-empty list of leave types
    @Test
    public void testGetLeaveTypes() {
        List<LeaveType> types = service.getLeaveTypes();
        System.out.println("   Leave types count=" + types.size());
        assertFalse(types.isEmpty());
    }

    // Returns leave balances list (can be empty) for employee
    @Test
    public void testGetLeaveBalances_forEmployee() {
        List<LeaveBalance> balances = service.getLeaveBalances(employee.getEmployeeID());
        System.out.println("   Leave balance entries=" + balances.size());
        assertNotNull(balances);
    }

    // Returns leave history list for employee
    @Test
    public void testGetLeaveHistory_forEmployee() {
        List<LeaveRequest> history = service.getLeaveHistory(employee.getEmployeeID());
        System.out.println("   Leave history size=" + history.size());
        assertNotNull(history);
    }

    // Invalid leave-ID returns null
    @Test
    public void testGetLeaveDetails_invalidId() {
        LeaveRequest lr = service.getLeaveDetails(-1);
        System.out.println("   Leave details lookup result=" + lr);
        assertNull(lr);
    }

    // HR Admin can view all leave requests
    @Test
    public void testGetAllLeaveRequests_asHrAdmin() {
        runAs(hrAdmin, () -> {
            List<LeaveRequest> list = service.getAllLeaveRequests();
            System.out.println("   All leave requests count=" + list.size());
            assertNotNull(list);
        });
    }

    // Regular employee denied when fetching all leave requests
    @Test
    public void testGetAllLeaveRequests_deniedForEmployee() {
        expectDenied(employee, () -> service.getAllLeaveRequests());
    }

    // Overtime
    // Returns overtime history list for employee
    @Test
    public void testGetOvertimeHistory() {
        List<OvertimeRequest> list = service.getOvertimeHistory(employee.getEmployeeID());
        System.out.println("   Overtime history size=" + list.size());
        assertNotNull(list);
    }

    // Invalid overtime-ID returns null
    @Test
    public void testGetOvertimeDetails_invalidId() {
        OvertimeRequest or = service.getOvertimeDetails(-1);
        System.out.println("   Overtime details lookup=" + or);
        assertNull(or);
    }

    // HR Admin can view all overtime requests
    @Test
    public void testGetAllOvertimeRequests_asHrAdmin() {
        runAs(hrAdmin, () -> {
            List<OvertimeRequest> list = service.getAllOvertimeRequests();
            System.out.println("   Overtime requests count=" + list.size());
            assertNotNull(list);
        });
    }

    // Regular employee denied when fetching all overtime requests
    @Test
    public void testGetAllOvertimeRequests_deniedForEmployee() {
        expectDenied(employee, () -> service.getAllOvertimeRequests());
    }

    // Support
    // Returns support history list for employee
    @Test
    public void testGetSupportHistory_employee() {
        List<SupportRequest> list = service.getSupportHistory(employee.getEmployeeID());
        System.out.println("   Support history size=" + list.size());
        assertNotNull(list);
    }

    // Invalid support-ID returns null
    @Test
    public void testGetSupportDetails_invalidId() {
        SupportRequest sr = service.getSupportDetails(-1);
        System.out.println("   Support details=" + sr);
        assertNull(sr);
    }

    // HR Admin can view team support history
    @Test
    public void testGetSupportHistoryByTeam_asHrAdmin() {
        runAs(hrAdmin, () -> {
            List<SupportRequest> list = service.getSupportHistoryByTeam("HR");
            System.out.println("   HR team tickets=" + list.size());
            assertNotNull(list);
        });
    }
    
    // Finance Admin can view team support history
    @Test
    public void testGetSupportHistoryByTeam_asFinanceAdmin() {
        runAs(financeAdmin, () -> {
            List<SupportRequest> list = service.getSupportHistoryByTeam("Finance");
            System.out.println("   Finance team tickets=" + list.size());
            assertNotNull(list);
        });
    }
    
    // IT Admin can view team support history
    @Test
    public void testGetSupportHistoryByTeam_asITAdmin() {
        runAs(itAdmin, () -> {
            List<SupportRequest> list = service.getSupportHistoryByTeam("IT");
            System.out.println("   IT team tickets=" + list.size());
            assertNotNull(list);
        });
    }

    // Employee role denied when fetching team support history
    @Test
    public void testGetSupportHistoryByTeam_deniedForEmployee() {
        expectDenied(employee, () -> service.getSupportHistoryByTeam("HR"));
    }

    // Shared
    // Unknown user ID returns empty full name
    @Test
    public void testGetUserFullName_unknownUser() {
        String name = service.getUserFullName(-1);
        System.out.println("   Full name result='" + name + "'");
        assertEquals("", name);
    }
    
    // Known user ID returns correct full name
    @Test
    public void testGetUserFullName_knownUser() {
        String name = service.getUserFullName(1);
        System.out.println("   Full name result='" + name + "'");
        assertEquals("Manuel III Garcia", name);
    }

    // Invalid employee ID returns null employee details
    @Test
    public void testGetEmployeeDetails_invalidId() {
        EmployeeView view = service.getEmployeeDetails(-1);
        System.out.println("   Employee details=" + view);
        assertNull(view);
    }
    
    // Valid employee ID returns employee details
    @Test
    public void testGetEmployeeDetails_validId() {
        EmployeeView view = service.getEmployeeDetails(10001);
        System.out.println("   Employee details=" + view);
        assertNotNull(view);
    }

    // Cleanup
    @AfterClass
    public static void tearDown() {
        Session.setCurrentUser(null);
    }
    
}