package service;

import gui.admin.finance.AdminFinancePortal;
import gui.admin.hr.AdminHRPortal;
import gui.admin.it.AdminITPortal;
import java.time.LocalDate;
import model.dao.LoginLogDAO;
import model.dao.RoleDAO;
import model.dao.UserAccountDAO;
import model.pojo.LoginLog;
import model.pojo.UserAccount;
import util.PasswordUtil;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;
import javax.swing.JFrame;
import model.dao.AuditLogDAO;
import model.dao.EmployeeDAO;
import model.dao.EmployeeViewDAO;
import model.pojo.AuditLog;
import model.pojo.EmployeeView;
import model.pojo.Role;
import util.Session;

public class AuthenticationService {
    
    private final UserAccountDAO userDao = new UserAccountDAO();
    private final RoleDAO roleDao = new RoleDAO();
    private final LoginLogDAO logDao = new LoginLogDAO();
    private final EmployeeDAO employeeDao = new EmployeeDAO();
    
    private static final int MAX_ATTEMPTS = 3;
    private static final int LOCKOUT_DURATION_MINUTES = 30;

    // Authenticates a user by verifying credentials and enforcing account lockout policy
    public UserAccount login(String username, String password) throws AuthenticationException {
        if (username == null || password == null) {
            throw new AuthenticationException("Username and password must not be null.");
        }

        UserAccount user = userDao.getByUsername(username);
        if (user == null) {
            throw new AuthenticationException("Invalid username or password.");
        }

        // Check for existing lockout
        if (isLockedOut(user.getUserID())) {
            throw new AuthenticationException("Account locked. Please try again later.");
        }

        // Verify password
        if (!PasswordUtil.verify(password, user.getPassword())) {
            int failures = getConsecutiveFailures(user.getUserID()) + 1;
            boolean locked = failures >= MAX_ATTEMPTS;
            LocalDateTime lockEnd = locked ? LocalDateTime.now().plusMinutes(LOCKOUT_DURATION_MINUTES) : null;
            recordLoginAttempt(user.getUserID(), "FAILED", failures, locked, lockEnd);
            throw new AuthenticationException("Invalid username or password.");
        }

        // Successful login
        recordLoginAttempt(user.getUserID(), "SUCCESS", 0, false, null);
        Session.setCurrentUser(user);
        return user;
    }

    // Log out the current user
    public void logout() {
        Session.clear();
    }

    // Change the password for the given user after verifying current password
    public void changePassword(int userId, String currentPassword, String newPassword) throws AuthenticationException {
        // Fetch user
        UserAccount user = userDao.getById(userId);
        if (user == null) {
            throw new AuthenticationException("User not found.");
        }

        // Verify current password
        if (!PasswordUtil.verify(currentPassword, user.getPassword())) {
            throw new AuthenticationException("Current password is incorrect.");
        }

        // Capture old hash
        String oldHash = user.getPassword();

        // Apply new password
        user.setPassword(newPassword);
        boolean ok = userDao.update(user);
        if (!ok) {
            throw new AuthenticationException("Failed to update password.");
        }

        // Audit log the change
        AuditLogDAO auditDao = new AuditLogDAO();
        AuditLog log = new AuditLog();
        log.setUserID(user.getUserID());
        log.setCreatedAt(LocalDateTime.now());
        log.setAction("UPDATE");
        log.setEntityModified("UserAccount");
        log.setEntityID(user.getUserID());
        log.setAttributeModified("password");
        log.setOldValue(oldHash);
        log.setNewValue(PasswordUtil.sha256Hash(user.getPassword()));
        auditDao.insert(log);
    }
    

    public boolean verifyIdentity(int employeeID, String birthdayStr, String sssNumber) {
        // Basic null / format checks
        if (birthdayStr == null || sssNumber == null) {
            return false;
        }

        LocalDate dob = parseDateSafely(birthdayStr);
        if (dob == null) {
            return false;
        }

        return employeeDao.verifyIdentity(employeeID, dob, sssNumber);
    }

    private LocalDate parseDateSafely(String dateStr) {
        try {
            return LocalDate.parse(dateStr);
        } catch (DateTimeParseException ex) {
            return null;
        }
    }

    // Verify identity then set new password
    public void resetPassword(String username, String birthday, String sssNumber, String newPassword) throws AuthenticationException {
        // Fetch and verify
        UserAccount user = userDao.getByUsername(username);
        if (user == null) {
            throw new AuthenticationException("User not found.");
        }
        if (!verifyIdentity(user.getEmployeeID(), birthday, sssNumber)) {
            throw new AuthenticationException("Verification failed.");
        }

        // Capture old value
        String oldHash = user.getPassword();

        // Apply change
        user.setPassword((newPassword));
        boolean ok = userDao.update(user);
        if (!ok) {
            throw new AuthenticationException("Failed to update password.");
        }

        // Audit
        AuditLogDAO auditDao = new AuditLogDAO();
        AuditLog log = new AuditLog();
        log.setUserID(user.getUserID());
        log.setCreatedAt(LocalDateTime.now());
        log.setAction("UPDATE");
        log.setEntityModified("UserAccount");
        log.setEntityID(user.getUserID());
        log.setAttributeModified("password");
        log.setOldValue(oldHash);
        log.setNewValue(PasswordUtil.sha256Hash(user.getPassword()));
        auditDao.insert(log);
    }

    // Check if the given userID is currently locked out based on the last login log
    public boolean isLockedOut(int userID) {
        List<LoginLog> recent = logDao.getRecentAttempts(userID, 1);
        if (recent.isEmpty()) {
            return false;
        }
        LoginLog last = recent.get(0);
        return last.isIsLocked() && last.getLockEndTime().isAfter(LocalDateTime.now());
    }

    // Returns the number of consecutive failed login attempts before the last successful login
    private int getConsecutiveFailures(int userID) {
        List<LoginLog> logs = logDao.getRecentAttempts(userID, MAX_ATTEMPTS);
        int count = 0;
        for (LoginLog log : logs) {
            if ("SUCCESS".equals(log.getStatus())) {
                break;
            }
            count++;
        }
        return count;
    }

    // Check if the current user has one of the allowed roles
    public boolean isAuthorized(UserAccount user, String... allowedRoles) {
        String roleName = roleDao.getById(user.getRoleID()).getRoleName();
        for (String allowed : allowedRoles) {
            if (allowed.equalsIgnoreCase(roleName)) {
                return true;
            }
        }
        return false;
    }

    // Record a login attempt in the log
    private void recordLoginAttempt(int userID, String status, int attemptCount, boolean locked, LocalDateTime lockEndTime) {
        LoginLog log = new LoginLog();
        log.setUserID(userID);
        log.setCreatedAt(LocalDateTime.now());
        log.setStatus(status);
        log.setLoginAttempt(attemptCount);
        log.setIsLocked(locked);
        log.setLockEndTime(lockEndTime);
        logDao.insert(log);
    }
    
    // Get current user's role
    public Role getCurrentRole() {
        var user = Session.getCurrentUser();
        if (user == null) {
            return null;
        }
        return roleDao.getById(user.getRoleID());
    }

    // Checks if the current user's role is an admin role
    public boolean isAdmin() {
        var role = getCurrentRole();
        if (role == null) {
            return false;
        }
        return switch (role.getRoleName()) {
            case "HR Admin", "Finance Admin", "IT Admin" ->
                true;
            default ->
                false;
        };
    }

    // Launches the appropriate admin portal based on the current user's role
    public JFrame launchAdminPortal() {
        var role = getCurrentRole();
        if (role == null) {
            return null;
        }

        return switch (role.getRoleName()) {
            case "HR Admin" ->
                new AdminHRPortal();
            case "Finance Admin" ->
                new AdminFinancePortal();
            case "IT Admin" ->
                new AdminITPortal();
            default ->
                null;
        };
    }
    
    // Returns the full name of the currently logged-in user
    public String getCurrentUserFullName() {
        int empId = Session.getCurrentUser().getEmployeeID();
        EmployeeView efd = new EmployeeViewDAO().getById(empId);
        if (efd == null) {
            return "";
        }
        return efd.getFirstName() + " " + efd.getLastName();
    }
   
}

class AuthenticationException extends Exception {

    public AuthenticationException(String message) {
        super(message);
    }
}