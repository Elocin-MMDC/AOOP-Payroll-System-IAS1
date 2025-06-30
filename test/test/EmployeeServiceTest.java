package test;

import java.math.BigDecimal;
import java.time.LocalDate;
import model.dao.UserAccountDAO;
import model.pojo.EmployeeView;
import model.pojo.UserAccount;
import org.junit.*;
import org.junit.rules.TestName;
import service.EmployeeService;
import util.Session;
import java.util.List;
import java.util.Random;
import model.dao.EmployeeDAO;
import model.dao.GovInformationDAO;
import model.pojo.Employee;
import model.pojo.EmployeeViewForHR;
import model.pojo.GovInformation;
import static org.junit.Assert.*;
import service.EmployeeService.ValidationException;

public class EmployeeServiceTest {
    
    private static EmployeeService service;

    private static UserAccountDAO userDao;
    private static EmployeeDAO empDao;
    private static GovInformationDAO govDao;

    private static UserAccount hrAdmin;
    private static UserAccount financeAdmin;
    private static UserAccount employee;

    private static int seedEmpId;

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
        service = new EmployeeService();
        userDao = new UserAccountDAO();
        empDao = new EmployeeDAO();
        govDao = new GovInformationDAO();

        hrAdmin = userDao.getById(7); // HR Admin account
        financeAdmin = userDao.getById(11); // Finance Admin account
        employee = userDao.getById(13); // Regular employee account
        
        Session.setCurrentUser(hrAdmin);

        // Seed an employee record for update tests
        String fn = "JUnitSeed";
        String ln = "Tester" + (System.currentTimeMillis() % 100000);

        boolean ok = service.createEmployee(
                ln, fn,
                LocalDate.of(1995, 5, 5),
                "099-555-666",
                "Male",
                "123 Seed St", "", "Metro", "", "1234",
                "Regular", "Employee", "Payroll Manager", "Select",
                makeUniqueSSS(), makeUniqueDigits(12), makeUniqueTIN(), makeUniqueDigits(12),
                new BigDecimal("12000"), new BigDecimal("6000"), new BigDecimal("75.00"));
        assertTrue(ok);
        seedEmpId = empDao.getIdByFullName(fn + " " + ln);
        System.out.println("▶ " + "setUp()");
        System.out.println("   Seed employee created with ID=" + seedEmpId);
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
    
    // Verifies toString() output for getEmployeeById() as HR Admin
    @Test
    public void testGetEmployeeById_toString_asHrAdmin() {
        int id = 10001;
        runAs(hrAdmin, () -> {
            EmployeeView ev = service.getEmployeeById(id);
            assertNotNull(ev);
            String s = ev.toString();
            System.out.println("   toString() = " + s);
            assertFalse(s.isBlank());
            assertTrue("String should contain employee ID", s.contains(String.valueOf(id)));
        });
    }

    // Verifies toString() output for getEmployeeById() as Finance Admin
    @Test
    public void testGetEmployeeById_toString_asFinanceAdmin() {
        int id = 10001;
        runAs(financeAdmin, () -> {
            EmployeeView ev = service.getEmployeeById(id);
            assertNotNull(ev);
            String s = ev.toString();
            System.out.println("   toString() = " + s);
            assertFalse(s.isBlank());
            assertTrue(s.contains(String.valueOf(id)));
        });
    }
    
    // HR Admin can fetch next employee ID
    @Test
    public void testGetNextEmployeeId_asHRAdmin() {
        runAs(hrAdmin, () -> {
            int nextId = service.getNextEmployeeId();
            System.out.println("   Next employee ID=" + nextId);
            assertTrue(nextId > 0);
        });
    }

    // Access denied for non-HR when getting next employee ID
    @Test
    public void testGetNextEmployeeId_deniedForEmployee() {
        expectDenied(employee, () -> service.getNextEmployeeId());
    }

    // Finance Admin can list all active employees
    @Test
    public void testGetAllActiveEmployees_asFinanceAdmin() {
        runAs(financeAdmin, () -> {
            List<EmployeeView> list = service.getAllActiveEmployees();
            System.out.println("   Active employees found=" + list.size());
            assertNotNull(list);
        });
    }

    // Employee role denied when listing active employees
    @Test
    public void testGetAllActiveEmployees_deniedForEmployee() {
        expectDenied(employee, () -> service.getAllActiveEmployees());
    }

    // HR Admin can generate a unique username
    @Test
    public void testGenerateUniqueUsername_returnsAvailable() {
        runAs(hrAdmin, () -> {
            String uname = service.generateUniqueUsername("Sample", "Test");
            System.out.println("   Generated username=" + uname);
            assertTrue(uname.matches("[a-z]+_[a-z0-9]+"));
        });
    }

    // Employee role denied when generating username
    @Test
    public void testGenerateUniqueUsername_deniedForEmployee() {
        expectDenied(employee, () -> service.generateUniqueUsername("Ana", "User"));
    }

    // HR Admin fetches department name for a position
    @Test
    public void testGetDepartmentNameByPosition_valid() {
        runAs(hrAdmin, () -> {
            String dept = service.getDepartmentNameByPosition("HR Manager");
            System.out.println("   Department=" + dept);
            assertNotNull(dept);
        });
    }

    // Employee role denied when fetching department name
    @Test
    public void testGetDepartmentNameByPosition_deniedForEmployee() {
        expectDenied(employee, () -> service.getDepartmentNameByPosition("HR Manager"));
    }

    // HR Admin can fetch all gender names
    @Test
    public void testGetAllGenderNames() {
        runAs(hrAdmin, () -> {
            List<?> list = service.getAllGenderNames();
            System.out.println("   Gender options=" + list.size());
            assertFalse(list.isEmpty());
        });
    }

    // Invalid ZIP code triggers validation exception during employee creation
    @Test(expected = EmployeeService.ValidationException.class)
    public void testCreateEmployee_invalidZipThrows() {
        runAs(hrAdmin, () -> {
            service.createEmployee("Doe", "John", LocalDate.of(1990, 1, 1), "091-234-567",
                    "Male", "123 Street", "Brgy", "City", "Province", "12",
                    "Regular", "Employee", "HR Officer", "Select",
                    "12-1234567-1", "123456789012", "123-456-789-000", "123456789012",
                    new BigDecimal("10000"), new BigDecimal("5000"), new BigDecimal("100"));
        });
    }

    // Returns a populated log object
    @Test
    public void testCreateLog_returnsLogObject() {
        runAs(hrAdmin, () -> {
            String oldValue = "OldVal";
            String newValue = "NewVal";
            var log = service.createLog("Employee", 1, "lastName", oldValue, newValue);
            System.out.println("   Log created for attribute: " + log.getAttributeModified());
            assertEquals("Employee", log.getEntityModified());
            assertEquals("lastName", log.getAttributeModified());
        });
    }

    // Get next employeeID for creating a new employee
    @Test
    public void testGetNextEmployeeId_asHrAdmin() {
        int id = service.getNextEmployeeId();
        System.out.println("   Next Employee ID: " + id);
        assertTrue(id > 0);
    }

    // Finance Admin can fetch an employee by ID
    @Test
    public void testGetEmployeeById_validId() {
        runAs(financeAdmin, () -> {
            EmployeeView view = service.getEmployeeById(10001);
            System.out.println("   Employee fetched: " + (view != null ? view.getFullNameWithSurnameFirst() : null));
            assertNotNull(view);
        });
    }

    // Employee role denied when fetching employee by ID
    @Test
    public void testGetEmployeeById_deniedForEmployee() {
        expectDenied(employee, () -> service.getEmployeeById(10001));
    }

    // HR Admin can successfully create a new employee
    @Test
    public void testCreateEmployee_success() {
        runAs(hrAdmin, () -> {
            String fn = "John" + (System.currentTimeMillis() % 100000);
            String ln = "JUnit";
            boolean ok = service.createEmployee(
                    ln, fn, LocalDate.of(1998, 8, 8), "091-222-333", "Female",
                    "456 Sample Ave", "", "Metro", "", "1234",
                    "Regular", "Employee", "Payroll Manager", "Select",
                    makeUniqueSSS(), makeUniqueDigits(12), makeUniqueTIN(), makeUniqueDigits(12),
                    new BigDecimal("15000"), new BigDecimal("7500"), new BigDecimal("90.00"));
            System.out.println("   createEmployee() returned=" + ok);
            assertTrue(ok);
            int newId = empDao.getIdByFullName(fn + " " + ln);
            assertTrue(newId > 0);
            System.out.println("   ✔ New employee ID=" + newId);

            // Clean up test record
            empDao.softDelete(newId);
            userDao.deactivateByEmployeeID(newId);
        });
    }

    // Invalid phone format triggers validation exception during employee creation
    @Test(expected = EmployeeService.ValidationException.class)
    public void testCreateEmployee_invalidPhoneThrows() {
        runAs(hrAdmin, () -> {
            service.createEmployee("Bad", "User", LocalDate.of(1995, 5, 5), "invalid-phone", "Male",
                    "1 St", "", "City", "", "1234", "Regular", "Employee", "Payroll Manager", "Select",
                    makeUniqueSSS(), makeUniqueDigits(12), makeUniqueTIN(), makeUniqueDigits(12),
                    new BigDecimal("12000"), new BigDecimal("6000"), new BigDecimal("80"));
        });
    }

    // HR Admin updates phone and government IDs for the seed employee
    @Test
    public void testUpdateEmployee_success() {
        runAs(hrAdmin, () -> {
            Employee before = empDao.getById(seedEmpId);

            String newPhone = "099-888-777";
            String newSSS = makeUniqueSSS();
            String newPhil = makeUniqueDigits(12);
            String newTIN = makeUniqueTIN();
            String newPagIbig = makeUniqueDigits(12);

            boolean ok = service.updateEmployee(seedEmpId,
                    before.getLastName(), before.getFirstName(), before.getBirthday(),
                    newPhone, "Male", "123 Seed St", "", "Metro", "", "1234",
                    "Regular", "Employee", "Payroll Manager", "Select",
                    newSSS, newPhil, newTIN, newPagIbig,
                    new BigDecimal("13000"), new BigDecimal("6500"), new BigDecimal("80.00"));
            System.out.println("   updateEmployee() returned=" + ok);
            assertTrue(ok);

            Employee after = empDao.getById(seedEmpId);
            assertEquals(newPhone, after.getPhoneNumber());
            GovInformation gov = govDao.getById(after.getGovID());
            assertEquals(newSSS, gov.getSssNumber());
            assertEquals(newPhil, gov.getPhilHealthNumber());
            assertEquals(newTIN, gov.getTin());
            assertEquals(newPagIbig, gov.getPagIbigNumber());
            System.out.println("   ✔ Phone & Gov IDs updated");
        });
    }

    // No-change update should throw validation exception
    @Test (expected = ValidationException.class)
    public void testUpdateEmployee_noChangesThrows() {
        runAs(hrAdmin, () -> {
            Employee e = empDao.getById(seedEmpId);
            service.updateEmployee(seedEmpId,
                    e.getLastName(), e.getFirstName(), e.getBirthday(), e.getPhoneNumber(),
                    "Male", "123 Seed St", "", "Metro", "", "1234",
                    "Regular", "Employee", "Payroll Manager", "Select",
                    govDao.getById(e.getGovID()).getSssNumber(),
                    govDao.getById(e.getGovID()).getPhilHealthNumber(),
                    govDao.getById(e.getGovID()).getTin(),
                    govDao.getById(e.getGovID()).getPagIbigNumber(),
                    new BigDecimal("12000"), new BigDecimal("6000"), new BigDecimal("75.00"));
        });
    }
    
    // Generates a unique SSS number for test data
    private static String makeUniqueSSS() {
        Random r = new Random();
        return String.format("%02d-%07d-%1d", r.nextInt(90) + 10, r.nextInt(9_999_999), r.nextInt(10));
    }

    // Generates a string of random digits with specified length
    private static String makeUniqueDigits(int len) {
        Random r = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            sb.append(r.nextInt(10));
        }
        return sb.toString();
    }

    // Generates a unique TIN for test data
    private static String makeUniqueTIN() {
        Random r = new Random();
        return String.format("%03d-%03d-%03d-%03d", r.nextInt(1000), r.nextInt(1000), r.nextInt(1000), r.nextInt(1000));
    }
    
    // Cleanup
    @AfterClass 
    public static void tearDown() {
        if (seedEmpId > 0) {
            empDao.softDelete(seedEmpId);
            userDao.deactivateByEmployeeID(seedEmpId);
            System.out.println("\n▶ " + "tearDown()");
            System.out.println("   ✔ Seed employee softly deleted and account deactivated (ID=" + seedEmpId + ")");
        }
        Session.setCurrentUser(null);
    }
    
}