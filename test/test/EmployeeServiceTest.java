package test;

import java.lang.reflect.Method;
import model.dao.EmployeeDAO;
import model.pojo.Employee;
import model.pojo.UserAccount;
import service.EmployeeService;
import util.Session;
import org.junit.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Random;
import model.dao.GovInformationDAO;
import model.dao.SalaryDAO;
import model.dao.UserAccountDAO;
import model.pojo.AuditLog;
import model.pojo.EmployeeView;
import model.pojo.EmployeeViewForHR;
import model.pojo.Gender;
import model.pojo.GovInformation;
import model.pojo.Positions;
import model.pojo.Role;
import model.pojo.Salary;
import model.pojo.WorkStatus;

import static org.junit.Assert.*;

public class EmployeeServiceTest {

    private static EmployeeService service;
    private static EmployeeDAO employeeDao;
    private static UserAccountDAO userAccountDao;
    private static GovInformationDAO govDao;

    private static String firstName;
    private static String lastName;
    private static int insertedId;

    // Setup
    @BeforeClass
    public static void setUp() throws Exception {
        employeeDao = new EmployeeDAO();
        service = new EmployeeService();
        userAccountDao = new UserAccountDAO();
        govDao = new GovInformationDAO();

        // Mock logged-in HR Admin (userID = 7, Brad San Jose)
        UserAccount admin = new UserAccount();
        admin.setUserID(7);
        Session.setCurrentUser(admin);

        long stamp = System.currentTimeMillis() % 1_000_000;
        firstName = "JUnit" + stamp;
        lastName = "Tester";

        boolean created = service.createEmployee(
                lastName, firstName,
                LocalDate.of(1995, 5, 5),
                "091-234-567",
                "Male",
                "123 Test St", "", "Metro", "", "1234",
                "Regular", "Employee", "Payroll Manager", "Select",
                makeUniqueSSS(),
                makeUniqueDigits(12),
                makeUniqueTIN(),
                makeUniqueDigits(12),
                new BigDecimal("15000.00"),
                new BigDecimal("7500.00"),
                new BigDecimal("93.75")
        );
        assertTrue("createEmployee() should return true", created);

        // fetch the new employee’s ID for later cleanup
        String full = firstName + " " + lastName;
        insertedId = employeeDao.getIdByFullName(full);
        assertTrue("New employee should be found", insertedId > 0);
    }

    // Tests
    @Test
    public void testEmployeeInserted() {
        Employee e = employeeDao.getById(insertedId);
        assertNotNull(e);
        assertEquals(firstName, e.getFirstName());
        assertEquals(lastName, e.getLastName());
    }

    @Test
    public void testGetNextEmployeeId() {
        int next = service.getNextEmployeeId();
        assertTrue("next ID should be greater than the one we just inserted",
                next > insertedId);
    }

    @Test
    public void testGetAllActiveEmployees() {
        List<EmployeeView> alive = service.getAllActiveEmployees();
        assertNotNull(alive);
        assertFalse(alive.isEmpty());
        assertTrue(alive.stream().anyMatch(v -> v.getEmployeeID() == insertedId));
    }

    @Test
    public void testGetEmployeeViewById() {
        EmployeeView view = service.getEmployeeById(insertedId);
        assertNotNull(view);
        assertEquals(firstName, view.getFirstName());
    }

    @Test
    public void testGetEmpHRViewById() {
        EmployeeViewForHR hrView = service.getEmpHRViewById(insertedId);
        assertNotNull(hrView);
        assertEquals(insertedId, hrView.getEmployeeID());
    }

    @Test
    public void testGetAllGenderNames() {
        List<Gender> list = service.getAllGenderNames();
        assertNotNull(list);
        assertFalse(list.isEmpty());
    }

    @Test
    public void testGetAllWorkStatusNames() {
        List<WorkStatus> list = service.getAllWorkStatusNames();
        assertNotNull(list);
        assertFalse(list.isEmpty());
    }

    @Test
    public void testGetAllRoleNames() {
        List<Role> list = service.getAllRoleNames();
        assertNotNull(list);
        assertFalse(list.isEmpty());
    }

    @Test
    public void testGetAllPositionTitles() {
        List<Positions> list = service.getAllPositionTitles();
        assertNotNull(list);
        assertFalse(list.isEmpty());
    }

    @Test
    public void testGetAllSupervisorNames() {
        List<Employee> list = service.getAllSupervisorNames();
        assertNotNull(list);
        assertFalse(list.isEmpty());
    }

    @Test
    public void testGetDepartmentNameByPosition() {
        String dept = service.getDepartmentNameByPosition("Payroll Manager");
        assertNotNull("Department should not be null for Payroll Manager", dept);
    }
    
    @Test
    public void getAllowancesByPosition() {
        Map<String, BigDecimal> map = service.getAllowancesByPosition("Payroll Manager");
        assertNotNull(map);
        assertFalse("Allowances should not be empty", map.isEmpty());
    }

    @Test
    public void softDeleteEmployee() {
        boolean ok = service.softDeleteEmployee(insertedId);
        assertTrue(ok);

        Employee deleted = employeeDao.getById(insertedId);
        assertTrue("Employee should now be marked deleted", deleted.isIsDeleted());

        int userId = userAccountDao.getUserIDByEmployeeID(insertedId);
        UserAccount ua = userAccountDao.getById(userId);
        assertEquals("Deactivated", ua.getAccountStatus());
    }
    
    @Test
    public void testGenerateUniqueUsername() {
        String username = service.generateUniqueUsername("John", "Doe");
        assertNotNull("Username should not be null", username);
        //expected format: all lower-case, words separated by '_', might end with 
        // digits with digits if collisions were detected
        assertTrue("Username format invalid", username.matches("[a-z]+_[a-z]+[0-9]*"));
    }
    
    @Test
    public void testUpdateEmployee() {
        // Pull current record
        Employee before = employeeDao.getById(insertedId);
        SalaryDAO salaryDao = new SalaryDAO();
        Salary currentSalary = salaryDao.getById(before.getSalaryID());

        // Updated fields
        String newPhone = "099-888-777";
        String newSSS = makeUniqueSSS();
        String newPhil = makeUniqueDigits(12);
        String newTIN = makeUniqueTIN();
        String newPagIbig = makeUniqueDigits(12);

        // Test
        boolean ok = service.updateEmployee(
                insertedId,
                before.getLastName(),
                before.getFirstName(),
                before.getBirthday(),
                newPhone, 
                "Male", // gender
                "123 Test St", "", "Metro", "", "1234",
                "Regular",
                "Employee",
                "Payroll Manager",
                "Select",
                newSSS,
                newPhil,
                newTIN,
                newPagIbig,
                currentSalary.getBasicSalary(),
                currentSalary.getSemiMonthlyRate(),
                currentSalary.getHourlyRate()
        );

        assertTrue("updateEmployee() should succeed", ok);

        // Verify the change really updated
        Employee after = employeeDao.getById(insertedId);
        assertEquals("Phone number should be updated", newPhone, after.getPhoneNumber());
        
        GovInformation updatedGov = govDao.getById(after.getGovID());
        assertEquals("SSS number should be updated", newSSS, updatedGov.getSssNumber());
        assertEquals("PhilHealth number should be updated", newPhil, updatedGov.getPhilHealthNumber());
        assertEquals("TIN should be updated", newTIN, updatedGov.getTin());
        assertEquals("PagIbig number should be updated", newPagIbig, updatedGov.getPagIbigNumber());
    }
    
    @Test
    public void testCreateLog_withHrAdminRole() {
        String entity = "Employee";
        int entityId = 123;
        String attr = "firstName";
        String oldVal = "John";
        String newVal = "Johnny";
        AuditLog log = service.createLog(entity, entityId, attr, oldVal, newVal);

        // Assert
        assertNotNull(log);
        assertEquals(Session.getCurrentUser().getUserID(), log.getUserID());
        assertEquals("UPDATE", log.getAction());
        assertEquals(entity, log.getEntityModified());
        assertEquals(entityId, log.getEntityID());
        assertEquals(attr, log.getAttributeModified());
        assertEquals(oldVal, log.getOldValue());
        assertEquals(newVal, log.getNewValue());
        assertNotNull(log.getCreatedAt());
        assertTrue(log.getCreatedAt().isAfter(LocalDateTime.now().minusSeconds(5)));
    }
    
    // Cleanup
    @AfterClass
    public static void tearDown() {
        if (insertedId > 0) {
            // Mark row as soft deleted
            employeeDao.softDelete(insertedId);
            // Mark row as deactivated
            userAccountDao.deactivateByEmployeeID(insertedId);
        }
    }

    // Helpers
    private static String makeUniqueSSS() {
        Random r = new Random();
        return String.format("%02d-%07d-%1d",
                r.nextInt(90) + 10,
                r.nextInt(9_999_999),
                r.nextInt(10));
    }

    private static String makeUniqueDigits(int len) {
        Random r = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            sb.append(r.nextInt(10));
        }
        return sb.toString();
    }

    private static String makeUniqueTIN() {
        Random r = new Random();
        return String.format("%03d-%03d-%03d-%03d",
                r.nextInt(1000), r.nextInt(1000),
                r.nextInt(1000), r.nextInt(1000));
    }
}