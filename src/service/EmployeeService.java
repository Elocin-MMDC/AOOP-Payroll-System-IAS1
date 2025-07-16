package service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import model.dao.*;
import model.pojo.*;
import util.AccessControlUtil;
import util.Session;
import util.ValidationUtil;

public class EmployeeService {
    
    private final EmployeeDAO employeeDao = new EmployeeDAO();
    private final EmployeeViewDAO efdDao = new EmployeeViewDAO();
    private final EmployeeViewForHRDAO empInfoHRViewDao = new EmployeeViewForHRDAO();
    private final AddressDAO addressDao = new AddressDAO();
    private final GovInformationDAO govDao = new GovInformationDAO();
    private final SalaryDAO salaryDao = new SalaryDAO();
    private final PositionsDAO positionDao = new PositionsDAO();
    private final WorkStatusDAO workStatusDao = new WorkStatusDAO();
    private final GenderDAO genderDao = new GenderDAO();
    private final RoleDAO roleDao = new RoleDAO();
    private final UserAccountDAO userDao = new UserAccountDAO();
    private final AuditLogDAO auditDao = new AuditLogDAO();
    private final AllowanceTypeDAO allowanceTypeDao = new AllowanceTypeDAO();
    private final LeaveTypeDAO leaveTypeDao = new LeaveTypeDAO();
    private final LeaveBalanceDAO leaveBalanceDao = new LeaveBalanceDAO();

    public int getNextEmployeeId() {
        AccessControlUtil.requireRole("HR Admin");
        return employeeDao.getNextEmployeeId();
    }

    public List<EmployeeView> getAllActiveEmployees() {
        AccessControlUtil.requireRole("HR Admin", "Finance Admin");
        return efdDao.getAllEmployees();
    }
    
    public EmployeeView getEmployeeById(int employeeID) {
        AccessControlUtil.requireRole("HR Admin", "Finance Admin");
        return efdDao.getById(employeeID);
    }
    
    public EmployeeViewForHR getEmpHRViewById(int employeeID) {
        AccessControlUtil.requireRole("HR Admin");
        return empInfoHRViewDao.getById(employeeID);
    }
    
    public List<Gender> getAllGenderNames() {
        AccessControlUtil.requireRole("HR Admin");
        return genderDao.getAll();
    }

    public List<WorkStatus> getAllWorkStatusNames() {
        AccessControlUtil.requireRole("HR Admin");
        return workStatusDao.getAll();
    }

    public List<Role> getAllRoleNames() {
        AccessControlUtil.requireRole("HR Admin");
        return roleDao.getAllRoles();
    }

    public List<Positions> getAllPositionTitles() {
        AccessControlUtil.requireRole("HR Admin");
        return positionDao.getAll();
    }

    public List<Employee> getAllSupervisorNames() {
        AccessControlUtil.requireRole("HR Admin");
        return employeeDao.getAllSupervisors();
    }

    public String getDepartmentNameByPosition(String positionTitle) {
        AccessControlUtil.requireRole("HR Admin");
        return positionDao.getDepartmentNameByPositionTitle(positionTitle);
    }
    
    public Map<String, BigDecimal> getAllowancesByPosition(String positionTitle) {
        AccessControlUtil.requireRole("HR Admin");
        
        List<Allowance> list = positionDao.getAllowancesByPositionTitle(positionTitle);
        Map<String, BigDecimal> result = new HashMap<>();

        for (Allowance a : list) {
            AllowanceType at = allowanceTypeDao.getById(a.getAllowanceTypeID());
            if (at != null) {
                result.put(at.getAllowanceName(), a.getAmount());
            }
        }
        return result;
    }
    
    public boolean softDeleteEmployee(int employeeID) {
        AccessControlUtil.requireRole("HR Admin");
        
        boolean success = employeeDao.softDelete(employeeID);

        if (!success) {
            return false;
        }

        // Deactivate user account
        boolean accountDeactivated = userDao.deactivateByEmployeeID(employeeID);

        // Log both employee deletion and account deactivation
        int currentUserID = Session.getCurrentUser().getUserID();
        LocalDateTime now = LocalDateTime.now();

        // Log employee soft delete
        AuditLog deleteLog = new AuditLog();
        deleteLog.setUserID(currentUserID);
        deleteLog.setCreatedAt(now);
        deleteLog.setAction("DELETE");
        deleteLog.setEntityModified("Employee");
        deleteLog.setEntityID(employeeID);
        deleteLog.setAttributeModified("isDeleted");
        deleteLog.setOldValue("FALSE");
        deleteLog.setNewValue("TRUE");
        auditDao.insert(deleteLog);

        // Log user account deactivation
        if (accountDeactivated) {
            int userID = userDao.getUserIDByEmployeeID(employeeID);
            AuditLog accountLog = new AuditLog();
            accountLog.setUserID(currentUserID);
            accountLog.setCreatedAt(now);
            accountLog.setAction("UPDATE");
            accountLog.setEntityModified("UserAccount");
            accountLog.setEntityID(userID);
            accountLog.setAttributeModified("accountStatus");
            accountLog.setOldValue("Active");
            accountLog.setNewValue("Deactivated");
            auditDao.insert(accountLog);
        }

        return true;
    }
    
    public boolean createEmployee(
            String lastName,
            String firstName,
            LocalDate birthday,
            String phoneNumber,
            String genderName,
            String street,
            String barangay,
            String city,
            String province,
            String zipCode,
            String workStatusName,
            String roleName,
            String positionTitle,
            String supervisorFullName,
            String sssNumber,
            String philHealthNumber,
            String tin,
            String pagIbigNumber,
            BigDecimal basicSalary,
            BigDecimal semiMonthlyRate,
            BigDecimal hourlyRate
    ) {
        AccessControlUtil.requireRole("HR Admin");
        
        if (!ValidationUtil.isAgeValid(birthday)) {
            throw new ValidationException("Invalid birthdate: must be between 18 and 100 years old.");
        }
        if (!ValidationUtil.isValidPhone(phoneNumber)) {
            throw new ValidationException("Invalid phone number: must follow the format NNN-NNN-NNN.");
        }
        if (!ValidationUtil.isValidZip(zipCode)) {
            throw new ValidationException("Invalid ZIP code: must be exactly 4 digits.");
        }
        if (!ValidationUtil.isValidSSS(sssNumber)) {
            throw new ValidationException("Invalid SSS number: must follow NN-NNNNNNN-N format.");
        }
        if (govDao.existsBySSSNumber(sssNumber)) {
            throw new ValidationException("SSS number is not valid.");
        }
        if (!ValidationUtil.isValidPhilHealth(philHealthNumber)) {
            throw new ValidationException("Invalid PhilHealth number: must be 12 digits.");
        }
        if (govDao.existsByPhilHealthNumber(philHealthNumber)) {
            throw new ValidationException("PhilHealth number is not valid.");
        }
        if (!ValidationUtil.isValidTIN(tin)) {
            throw new ValidationException("Invalid TIN: must follow NNN-NNN-NNN-NNN format.");
        }
        if (govDao.existsByTIN(tin)) {
            throw new ValidationException("TIN is not valid.");
        }
        if (!ValidationUtil.isValidPagIbig(pagIbigNumber)) {
            throw new ValidationException("Invalid Pag-IBIG number: must be 12 digits.");
        }
        if (govDao.existsByPagIbigNumber(pagIbigNumber)) {
            throw new ValidationException("Pag-IBIG number is not valid.");
        }

        // Resolve IDs
        int genderID = genderDao.getIdByName(genderName);
        int workStatusID = workStatusDao.getIdByName(workStatusName);
        int positionID = positionDao.getIdByTitle(positionTitle);
        int roleID = roleDao.getIdByName(roleName);

        if (barangay.isEmpty()) {
            barangay = null;
        }
        if (province.isEmpty()) {
            province = null;
        }
        if (zipCode.isEmpty())
            zipCode = null;

        Integer supervisorID;
        if (supervisorFullName.equals("Select")) {
            supervisorID = null;
        }
        else {
            supervisorID = employeeDao.getIdByFullName(supervisorFullName);
        }

        // Insert Address
        Address address = new Address();
        address.setStreet(street);
        address.setBarangay(barangay);
        address.setCity(city);
        address.setProvince(province);
        address.setZipCode(zipCode);
        int addressID = addressDao.insert(address);

        // Insert GovInformation
        GovInformation govInfo = new GovInformation();
        govInfo.setSssNumber(sssNumber);
        govInfo.setPhilHealthNumber(philHealthNumber);
        govInfo.setTin(tin);
        govInfo.setPagIbigNumber(pagIbigNumber);
        int govID = govDao.insert(govInfo);

        // Insert Salary
        Salary salary = new Salary();
        salary.setBasicSalary(basicSalary);
        salary.setSemiMonthlyRate(semiMonthlyRate);
        salary.setHourlyRate(hourlyRate);
        int salaryID = salaryDao.insert(salary);

        // Insert Employee
        Employee emp = new Employee();
        emp.setLastName(lastName);
        emp.setFirstName(firstName);
        emp.setBirthday(birthday);
        emp.setPhoneNumber(phoneNumber);
        emp.setGenderID(genderID);
        emp.setAddressID(addressID);
        emp.setWorkStatusID(workStatusID);
        emp.setPositionID(positionID);
        emp.setGovID(govID);
        emp.setSalaryID(salaryID);
        emp.setSupervisorID(supervisorID);
        int employeeID = employeeDao.insert(emp);

        // Insert UserAccount
        String username = generateUniqueUsername(firstName, lastName);
        UserAccount user = new UserAccount();
        user.setEmployeeID(employeeID);
        user.setUsername(username);
        user.setPassword("temppassword");
        user.setRoleID(roleID);
        user.setAccountStatus("Active");
        int userID = userDao.insert(user);

        // Insert default Leave Balances for every new employee
        List<LeaveType> leaveTypes = leaveTypeDao.getAll();

        for (LeaveType leaveType : leaveTypes) {
            String leaveName = leaveType.getLeaveName();

            // Skip based on gender
            if ((genderName.equalsIgnoreCase("Male") && leaveName.equalsIgnoreCase("Maternity"))
                    || (genderName.equalsIgnoreCase("Female") && leaveName.equalsIgnoreCase("Paternity"))) {
                continue;
            }

            LeaveBalance lb = new LeaveBalance();
            lb.setEmployeeID(employeeID);
            lb.setLeaveTypeID(leaveType.getLeaveTypeID());
            lb.setEntitledDays(leaveType.getEntitlementDays());
            lb.setUsedDays(0);
            lb.setRemainingDays(leaveType.getEntitlementDays());
            leaveBalanceDao.insert(lb);
        }

        // Audit Logs
        AuditLog logNewEmployee = new AuditLog();
        logNewEmployee.setUserID(Session.getCurrentUser().getUserID());
        logNewEmployee.setCreatedAt(LocalDateTime.now());
        logNewEmployee.setAction("INSERT");
        logNewEmployee.setEntityModified("Employee");
        logNewEmployee.setEntityID(employeeID);
        logNewEmployee.setAttributeModified("all");
        logNewEmployee.setOldValue(null);
        logNewEmployee.setNewValue(null);
        auditDao.insert(logNewEmployee);

        AuditLog logNewUser = new AuditLog();
        logNewUser.setUserID(Session.getCurrentUser().getUserID());
        logNewUser.setCreatedAt(LocalDateTime.now());
        logNewUser.setAction("INSERT");
        logNewUser.setEntityModified("UserAccount");
        logNewUser.setEntityID(userID);
        logNewUser.setAttributeModified("all");
        logNewUser.setOldValue(null);
        logNewUser.setNewValue(null);
        auditDao.insert(logNewUser);

        return true;
    }
    
    public String generateUniqueUsername(String firstName, String lastName) {
        AccessControlUtil.requireRole("HR Admin", "IT Admin");
        
        firstName = firstName.toLowerCase().replaceAll("\\s+", "");
        lastName = lastName.toLowerCase().replaceAll("\\s+", "");

        // Try j_doe, jo_doe, joh_doe, etc
        for (int i = 1; i <= firstName.length(); i++) {
            String username = firstName.substring(0, i) + "_" + lastName;
            if (!userDao.usernameExists(username)) {
                return username;
            }
        }

        // If taken, fallback to john_doe1, john_doe2, etc
        String base = firstName + "_" + lastName;
        int counter = 1;
        String username;
        do {
            username = base + counter;
            counter++;
        } while (userDao.usernameExists(username));

        return username;
    }
   
    // Update employee record
    public boolean updateEmployee(
            int employeeID,
            String lastName,
            String firstName,
            LocalDate birthday,
            String phoneNumber,
            String genderName,
            String street,
            String barangay,
            String city,
            String province,
            String zipCode,
            String workStatusName,
            String roleName,
            String positionTitle,
            String supervisorFullName,
            String sssNumber,
            String philHealthNumber,
            String tin,
            String pagIbigNumber,
            BigDecimal basicSalary,
            BigDecimal semiMonthlyRate,
            BigDecimal hourlyRate
    ) {
        AccessControlUtil.requireRole("HR Admin");
        
        Employee current = employeeDao.getById(employeeID);
        if (current == null) {
            throw new ValidationException("Employee not found.");
        }

        int addressID = current.getAddressID();
        int govID = current.getGovID();
        int salaryID = current.getSalaryID();

        if (!ValidationUtil.isAgeValid(birthday)) {
            throw new ValidationException("Invalid birthdate: must be between 18 and 100 years old.");
        }
        if (!ValidationUtil.isValidPhone(phoneNumber)) {
            throw new ValidationException("Invalid phone number: must follow the format NNN-NNN-NNN.");
        }
        if (!ValidationUtil.isValidZip(zipCode)) {
            throw new ValidationException("Invalid ZIP code: must be exactly 4 digits.");
        }
        
        Integer supervisorID = supervisorFullName.equals("Select") ? null : employeeDao.getIdByFullName(supervisorFullName);

        if (supervisorID != null && supervisorID == employeeID) {
            throw new ValidationException("An employee cannot be their own supervisor.");
        }
        
        if (!ValidationUtil.isValidSSS(sssNumber)) {
            throw new ValidationException("Invalid SSS number: must follow NN-NNNNNNN-N format.");
        }
        if (govDao.existsBySSSNumberExcept(sssNumber, govID)) {
            throw new ValidationException("SSS number is not valid.");
        }
        if (!ValidationUtil.isValidPhilHealth(philHealthNumber)) {
            throw new ValidationException("Invalid PhilHealth number: must be 12 digits.");
        }
        if (govDao.existsByPhilHealthNumberExcept(philHealthNumber, govID)) {
            throw new ValidationException("PhilHealth number is not valid.");
        }
        if (!ValidationUtil.isValidTIN(tin)) {
            throw new ValidationException("Invalid TIN: must follow NNN-NNN-NNN-NNN format.");
        }
        if (govDao.existsByTINExcept(tin, govID)) {
            throw new ValidationException("TIN is not valid.");
        }
        if (!ValidationUtil.isValidPagIbig(pagIbigNumber)) {
            throw new ValidationException("Invalid Pag-IBIG number: must be 12 digits.");
        }
        if (govDao.existsByPagIbigNumberExcept(pagIbigNumber, govID)) {
            throw new ValidationException("Pag-IBIG number is not valid.");
        }

        int genderID = genderDao.getIdByName(genderName);
        int workStatusID = workStatusDao.getIdByName(workStatusName);
        int positionID = positionDao.getIdByTitle(positionTitle);
        int roleID = roleDao.getIdByName(roleName);

        barangay = barangay.isEmpty() ? null : barangay;
        province = province.isEmpty() ? null : province;
        zipCode = zipCode.isEmpty() ? null : zipCode;

        List<AuditLog> logs = new ArrayList<>();
        int userID = userDao.getUserIDByEmployeeID(employeeID);

        // Address
        Address currentAddress = addressDao.getById(addressID);
        if (!street.equals(currentAddress.getStreet())) {
            logs.add(createLog("Address", addressID, "street", currentAddress.getStreet(), street));
        }
        if (!Objects.equals(barangay, currentAddress.getBarangay())) {
            logs.add(createLog("Address", addressID, "barangay", currentAddress.getBarangay(), barangay));
        }
        if (!city.equals(currentAddress.getCity())) {
            logs.add(createLog("Address", addressID, "city", currentAddress.getCity(), city));
        }
        if (!Objects.equals(province, currentAddress.getProvince())) {
            logs.add(createLog("Address", addressID, "province", currentAddress.getProvince(), province));
        }
        if (!Objects.equals(zipCode, currentAddress.getZipCode())) {
            logs.add(createLog("Address", addressID, "zipCode", currentAddress.getZipCode(), zipCode));
        }

        // Update Address
        Address address = new Address();
        address.setAddressID(addressID);
        address.setStreet(street);
        address.setBarangay(barangay);
        address.setCity(city);
        address.setProvince(province);
        address.setZipCode(zipCode);
        addressDao.update(address);

        // Government Info
        GovInformation currentGov = govDao.getById(govID);
        if (!sssNumber.equals(currentGov.getSssNumber())) {
            logs.add(createLog("GovInformation", govID, "sssNumber", currentGov.getSssNumber(), sssNumber));
        }
        if (!philHealthNumber.equals(currentGov.getPhilHealthNumber())) {
            logs.add(createLog("GovInformation", govID, "philHealthNumber", currentGov.getPhilHealthNumber(), philHealthNumber));
        }
        if (!tin.equals(currentGov.getTin())) {
            logs.add(createLog("GovInformation", govID, "tin", currentGov.getTin(), tin));
        }
        if (!pagIbigNumber.equals(currentGov.getPagIbigNumber())) {
            logs.add(createLog("GovInformation", govID, "pagIbigNumber", currentGov.getPagIbigNumber(), pagIbigNumber));
        }

        // Update GovInformation
        GovInformation govInfo = new GovInformation();
        govInfo.setGovID(govID);
        govInfo.setSssNumber(sssNumber);
        govInfo.setPhilHealthNumber(philHealthNumber);
        govInfo.setTin(tin);
        govInfo.setPagIbigNumber(pagIbigNumber);
        govDao.update(govInfo);

        // Salary
        Salary currentSalary = salaryDao.getById(salaryID);
        if (basicSalary.compareTo(currentSalary.getBasicSalary()) != 0) {
            logs.add(createLog("Salary", salaryID, "basicSalary", currentSalary.getBasicSalary().toString(), basicSalary.toString()));
        }
        if (semiMonthlyRate.compareTo(currentSalary.getSemiMonthlyRate()) != 0) {
            logs.add(createLog("Salary", salaryID, "semiMonthlyRate", currentSalary.getSemiMonthlyRate().toString(), semiMonthlyRate.toString()));
        }
        if (hourlyRate.compareTo(currentSalary.getHourlyRate()) != 0) {
            logs.add(createLog("Salary", salaryID, "hourlyRate", currentSalary.getHourlyRate().toString(), hourlyRate.toString()));
        }

        // Update Salary
        Salary salary = new Salary();
        salary.setSalaryID(salaryID);
        salary.setBasicSalary(basicSalary);
        salary.setSemiMonthlyRate(semiMonthlyRate);
        salary.setHourlyRate(hourlyRate);
        salaryDao.update(salary);
        
        // Employee
        if (!lastName.equals(current.getLastName())) {
            logs.add(createLog("Employee", employeeID, "lastName", current.getLastName(), lastName));
        }
        if (!firstName.equals(current.getFirstName())) {
            logs.add(createLog("Employee", employeeID, "firstName", current.getFirstName(), firstName));
        }
        if (!birthday.equals(current.getBirthday())) {
            logs.add(createLog("Employee", employeeID, "birthday", current.getBirthday().toString(), birthday.toString()));
        }
        if (!phoneNumber.equals(current.getPhoneNumber())) {
            logs.add(createLog("Employee", employeeID, "phoneNumber", current.getPhoneNumber(), phoneNumber));
        }
        if (genderID != current.getGenderID()) {
            logs.add(createLog("Employee", employeeID, "genderID", String.valueOf(current.getGenderID()), String.valueOf(genderID)));
        }
        if (workStatusID != current.getWorkStatusID()) {
            logs.add(createLog("Employee", employeeID, "workStatusID", String.valueOf(current.getWorkStatusID()), String.valueOf(workStatusID)));
        }
        if (positionID != current.getPositionID()) {
            logs.add(createLog("Employee", employeeID, "positionID", String.valueOf(current.getPositionID()), String.valueOf(positionID)));
        }
        if (!Objects.equals(supervisorID, current.getSupervisorID())) {
            logs.add(createLog("Employee", employeeID, "supervisorID", String.valueOf(current.getSupervisorID()), String.valueOf(supervisorID)));
        }

        // Role
        int oldRoleID = userDao.getById(userID).getRoleID();
        if (oldRoleID != roleID) {
            logs.add(createLog("UserAccount", userID, "roleID", String.valueOf(oldRoleID), String.valueOf(roleID)));
        }

        // Username change if name changed
        boolean isNameChanged = !firstName.equals(current.getFirstName()) || !lastName.equals(current.getLastName());
        if (isNameChanged) {
            String currentUsername = userDao.getUsernameById(userID);
            String newUsername = generateUniqueUsername(firstName, lastName);
            if (!newUsername.equals(currentUsername)) {
                logs.add(createLog("UserAccount", userID, "username", currentUsername, newUsername));
                userDao.updateUsername(userID, newUsername);
            }
        }

        if (logs.isEmpty()) {
            throw new ValidationException("No changes were made. No updates needed.");
        }

        // Apply updates
        current.setLastName(lastName);
        current.setFirstName(firstName);
        current.setBirthday(birthday);
        current.setPhoneNumber(phoneNumber);
        current.setGenderID(genderID);
        current.setWorkStatusID(workStatusID);
        current.setPositionID(positionID);
        current.setSupervisorID(supervisorID);
        employeeDao.update(current);
        userDao.updateRole(userID, roleID);

        for (AuditLog log : logs) {
            auditDao.insert(log);
        }

        return true;
    }

    public AuditLog createLog(String entity, int entityID, String attr, String oldVal, String newVal) {
        AccessControlUtil.requireRole("HR Admin");
        
        AuditLog log = new AuditLog();
        log.setUserID(Session.getCurrentUser().getUserID());
        log.setCreatedAt(LocalDateTime.now());
        log.setAction("UPDATE");
        log.setEntityModified(entity);
        log.setEntityID(entityID);
        log.setAttributeModified(attr);
        log.setOldValue(oldVal);
        log.setNewValue(newVal);
        return log;
    }

    public static class ValidationException extends RuntimeException {

        public ValidationException(String message) {
            super(message);
        }
    }
} 