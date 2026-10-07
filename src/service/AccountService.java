package service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import model.dao.AuditLogDAO;
import model.dao.EmployeeDAO;
import model.dao.EmployeeViewDAO;
import model.dao.LoginLogDAO;
import model.dao.RoleDAO;
import model.dao.UserAccountDAO;
import model.dao.UserAccountViewDAO;
import model.pojo.AuditLog;
import model.pojo.EmployeeView;
import model.pojo.Role;
import model.pojo.UserAccount;
import model.pojo.UserAccountView;
import util.AccessControlUtil;
import util.PasswordUtil;
import util.Session;

public class AccountService {

    private final UserAccountDAO userDao = new UserAccountDAO();
    private final EmployeeViewDAO empDao = new EmployeeViewDAO();
    private final RoleDAO roleDao = new RoleDAO();
    private final AuditLogDAO auditDao = new AuditLogDAO();
    private final EmployeeDAO employeeDao = new EmployeeDAO();
    private final LoginLogDAO loginDao = new LoginLogDAO();
    private final UserAccountViewDAO userAccountViewDao = new UserAccountViewDAO();

    public List<UserAccountView> getAllAccounts() {
        AccessControlUtil.requireRole("IT Admin");
        return userAccountViewDao.getAll();
    }

    public EmployeeView getEmployeeDetails(int empId) {
        AccessControlUtil.requireRole("IT Admin");
        return empDao.getById(empId);
    }

    public Role getRoleById(int roleId) {
        AccessControlUtil.requireRole("IT Admin");
        return roleDao.getById(roleId);
    }

    public List<Role> getAllRoles() {
        AccessControlUtil.requireRole("IT Admin");
        return roleDao.getAllRoles();
    }

    public UserAccount getAccountById(int userId) {
        AccessControlUtil.requireRole("IT Admin");
        return userDao.getById(userId);
    }
    
    // Reset the user's password and change mustChangePassword flag to true
    // This method is intended for IT Admins to enforce a password reset for a user account
    // Returns the new temporary password for display to the IT Admin
    public String resetPassword(int userID) throws ValidationException {
        AccessControlUtil.requireRole("IT Admin");

        String newTemporaryPassword = null;
        
        // Fetch the user account
        UserAccount account = userDao.getById(userID);
        if (account == null) {
            throw new ValidationException("User account not found.");
        }
        
        // Generate a new temporary password
        newTemporaryPassword = PasswordUtil.generateRandomPassword();
        PasswordUtil.applyHashedPassword(account, newTemporaryPassword);

        boolean oldMustChangePassword = account.getMustChangePassword();

        // Update the user's password and set mustChangePassword to true
        boolean isSuccessfullyUpdated = userDao.updatePasswordAndEnforceMustChange(userID, account.getPassword(), account.getPasswordSalt());
        if (!isSuccessfullyUpdated) {
            throw new ValidationException("Failed to enforce password reset. Please try again.");
        }

        // Log audit entry for the password reset enforcement
        AuditLog log = new AuditLog();
        log.setUserID(Session.getCurrentUser().getUserID());
        log.setCreatedAt(LocalDateTime.now());
        log.setAction("UPDATE");
        log.setEntityModified("UserAccount");
        log.setEntityID(userID);
        log.setAttributeModified("mustChangePassword");
        log.setOldValue(String.valueOf(oldMustChangePassword));
        log.setNewValue("true");
        auditDao.insert(log);

        // Clear login lock state cleanly
        loginDao.clearLockState(userID);

        // Audit the lock state reset
        AuditLog resetLockLog = new AuditLog();
        resetLockLog.setUserID(Session.getCurrentUser().getUserID());
        resetLockLog.setCreatedAt(LocalDateTime.now());
        resetLockLog.setAction("INSERT");
        resetLockLog.setEntityModified("LoginLog");
        resetLockLog.setEntityID(userID);
        resetLockLog.setAttributeModified("all");
        resetLockLog.setOldValue("Locked");
        resetLockLog.setNewValue("Unlocked");
        auditDao.insert(resetLockLog);

        // Return the new temporary password
        return newTemporaryPassword;
    }

    public boolean updateAccount(int userID, String newUsername, String newEmail, String newRoleName, String newStatus) throws ValidationException {
        AccessControlUtil.requireRole("IT Admin");
        
        UserAccount existing = userDao.getById(userID);
        if (existing == null) {
            throw new ValidationException("User account not found.");
        }

        String existingEmail = existing.getEmail() == null ? "" : existing.getEmail();
        String normalizedEmail = newEmail == null ? "" : newEmail.trim();

        boolean updated = false;
        List<AuditLog> logs = new ArrayList<>();
        
        int newRoleID = roleDao.getIdByName(newRoleName);
        if (newRoleID <= 0) {
            throw new ValidationException("Invalid role selected: " + newRoleName);
        }

        // Username
        if (!existing.getUsername().equals(newUsername)) {
            UserAccount existingByUsername = userDao.getByUsername(newUsername);
            if (existingByUsername != null && existingByUsername.getUserID() != userID) {
                throw new ValidationException("Username already exists. Please choose a different one.");
            }

            logs.add(createAuditLog("UPDATE", "UserAccount", userID, "username", existing.getUsername(), newUsername));
            if (!userDao.updateUsername(userID, newUsername)) {
                throw new ValidationException("Failed to update username.");
            }
            updated = true;
        }

        // Email
        if (!existingEmail.equals(normalizedEmail)) {
            logs.add(createAuditLog("UPDATE", "UserAccount", userID, "email", existingEmail, normalizedEmail));
            if (!userDao.updateEmail(userID, normalizedEmail)) {
                throw new ValidationException("Failed to update email address.");
            }
            updated = true;
        }

        // Role
        if (existing.getRoleID() != newRoleID) {
            Role oldRole = getRoleById(existing.getRoleID());
            logs.add(createAuditLog("UPDATE", "UserAccount", userID, "role", oldRole.getRoleName(), newRoleName));
            if (!userDao.updateRole(userID, newRoleID)) {
                throw new ValidationException("Failed to update role.");
            }
            updated = true;
        }

        // Account Status
        if (!existing.getAccountStatus().equals(newStatus)) {
            logs.add(createAuditLog("UPDATE", "UserAccount", userID, "accountStatus", existing.getAccountStatus(), newStatus));
            if (!userDao.updateStatus(userID, newStatus)) {
                throw new ValidationException("Failed to update account status.");
            }
            updated = true;

            int employeeID = existing.getEmployeeID();
            if ("Active".equalsIgnoreCase(newStatus)) {
                if (employeeDao.setIsDeletedFalse(employeeID)) {
                    logs.add(createAuditLog("UPDATE", "Employee", employeeID, "isDeleted", "TRUE", "FALSE"));
                }
            } else if ("Deactivated".equalsIgnoreCase(newStatus)) {
                if (employeeDao.softDelete(employeeID)) {
                    logs.add(createAuditLog("DELETE", "Employee", employeeID, "isDeleted", "FALSE", "TRUE"));
                }
            }
        }

        // Save all audit logs
        if (updated) {
            for (AuditLog log : logs) {
                auditDao.insert(log);
            }
        }

        return updated;
    }

    private AuditLog createAuditLog(String action, String entityModified, int entityID, String attribute, String oldVal, String newVal) {
        AccessControlUtil.requireRole("IT Admin");
        AuditLog log = new AuditLog();
        log.setUserID(Session.getCurrentUser().getUserID());
        log.setCreatedAt(LocalDateTime.now());
        log.setAction(action);
        log.setEntityModified(entityModified);
        log.setEntityID(entityID);
        log.setAttributeModified(attribute);
        log.setOldValue(oldVal);
        log.setNewValue(newVal);
        return log;
    }
    
}
