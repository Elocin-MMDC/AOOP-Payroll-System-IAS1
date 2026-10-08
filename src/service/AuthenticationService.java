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
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.swing.JFrame;
import model.dao.AuditLogDAO;
import model.dao.EmployeeDAO;
import model.dao.EmployeeViewDAO;
import model.pojo.AuditLog;
import model.pojo.EmployeeView;
import model.pojo.Role;
import util.OtpUtil;
import util.Session;

public class AuthenticationService {

    private final UserAccountDAO userDao = new UserAccountDAO();
    private final RoleDAO roleDao = new RoleDAO();
    private final LoginLogDAO logDao = new LoginLogDAO();
    private final EmployeeDAO employeeDao = new EmployeeDAO();

    private static final int MAX_ATTEMPTS = 3;
    private static final int LOCKOUT_DURATION_MINUTES = 30;
    private static final int PRE_AUTH_TOKEN_MINUTES = 5;

    // Pending logins keyed by SHA-256 hash of the pre-authentication token; the raw token is never stored
    private static final Map<String, PendingLogin> PENDING_LOGINS = new ConcurrentHashMap<>();

    private record PendingLogin(int userID, boolean enrollmentRequired, LocalDateTime expiresAt) {}

    // Returned after a correct password; grants nothing except access to the TOTP step
    public record PreAuthChallenge(String token, String username, boolean enrollmentRequired) {}

    // Step 1 of login: verifies the password and enforces account lockout policy.
    // On success, issues a short-lived pre-authentication token. The session is only
    // established by completeLogin after a valid TOTP code.
    public PreAuthChallenge login(String username, String password) throws AuthenticationException {
        // A new login attempt never inherits a previous session
        Session.clear();

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
        if (!PasswordUtil.verifyUserPassword(password, user)) {
            recordFailedAttempt(user.getUserID());
            throw new AuthenticationException("Invalid username or password.");
        }

        // Password verified. SUCCESS is not recorded yet, so the lockout counter keeps
        // counting failed TOTP attempts that follow a correct password.
        return issuePreAuthToken(user);
    }

    // Starts authenticator enrollment for a user who has not yet enabled MFA
    public MfaService.EnrollmentDetails startMfaEnrollment(String preAuthToken) throws AuthenticationException {
        PendingLogin pending = resolvePendingLogin(preAuthToken);
        if (!pending.enrollmentRequired()) {
            throw new AuthenticationException("MFA is already set up for this account.");
        }

        UserAccount user = userDao.getById(pending.userID());
        if (user == null) {
            cancelLogin(preAuthToken);
            throw new AuthenticationException("Invalid username or password.");
        }
        return new MfaService().beginEnrollment(user);
    }

    // Step 2 of login: verifies the 6-digit TOTP (or confirms enrollment), then establishes the session
    public UserAccount completeLogin(String preAuthToken, String code) throws AuthenticationException {
        PendingLogin pending = resolvePendingLogin(preAuthToken);
        int userID = pending.userID();
        MfaService mfaService = new MfaService();

        if (isLockedOut(userID)) {
            cancelLogin(preAuthToken);
            mfaService.auditMfaEvent(userID, userID, "MFA_LOGIN_VERIFICATION", "LOCKED");
            throw new AuthenticationException("Account locked. Please try again later.");
        }

        UserAccount user = userDao.getById(userID);
        if (user == null || !"Active".equals(user.getAccountStatus())) {
            cancelLogin(preAuthToken);
            throw new AuthenticationException("Invalid username or password.");
        }

        boolean verified;
        if (pending.enrollmentRequired()) {
            // confirmEnrollment records its own MFA_ENROLLMENT audit event
            verified = mfaService.confirmEnrollment(userID, code);
        } else {
            MfaService.VerificationResult result = mfaService.verifyCode(userID, code);
            mfaService.auditMfaEvent(userID, userID, "MFA_LOGIN_VERIFICATION", result.name());
            if (result == MfaService.VerificationResult.NOT_ENROLLED) {
                cancelLogin(preAuthToken);
                throw new AuthenticationException("MFA is not configured correctly for this account. Please contact your IT Administrator.");
            }
            verified = result == MfaService.VerificationResult.SUCCESS;
        }

        if (!verified) {
            // Failed TOTP attempts share the existing lockout counter with failed passwords
            if (recordFailedAttempt(userID)) {
                cancelLogin(preAuthToken);
                throw new AuthenticationException("Too many failed attempts. Account locked. Please try again later.");
            }
            throw new AuthenticationException("Invalid or already used authentication code.");
        }

        // Successful login
        cancelLogin(preAuthToken);
        recordLoginAttempt(userID, "SUCCESS", 0, false, null);
        UserAccount authenticatedUser = userDao.getById(userID);
        Session.setCurrentUser(authenticatedUser);
        return authenticatedUser;
    }

    // Invalidates a pre-authentication token (on cancel, success, or lockout)
    public void cancelLogin(String preAuthToken) {
        if (preAuthToken != null && !preAuthToken.isEmpty()) {
            PENDING_LOGINS.remove(OtpUtil.hashValue(preAuthToken));
        }
    }

    // Reauthenticates the current user with password and current TOTP before MFA can be
    // disabled or reset. Failures are audited and count toward the existing lockout.
    public void reauthenticateForMfaChange(String password, String code) throws AuthenticationException {
        UserAccount current = Session.getCurrentUser();
        if (current == null) {
            throw new AuthenticationException("Please log in again.");
        }

        int userID = current.getUserID();
        MfaService mfaService = new MfaService();

        if (isLockedOut(userID)) {
            mfaService.auditMfaEvent(userID, userID, "MFA_REAUTHENTICATION", "LOCKED");
            throw new AuthenticationException("Account locked. Please try again later.");
        }

        UserAccount user = userDao.getById(userID);
        boolean passwordOk = user != null && password != null && !password.isEmpty()
                && verifyPasswordSafely(password, user);

        // Only check (and consume) the TOTP when the password is correct
        MfaService.VerificationResult totpResult = passwordOk
                ? mfaService.verifyCode(userID, code)
                : MfaService.VerificationResult.INVALID_CODE;

        if (!passwordOk || totpResult != MfaService.VerificationResult.SUCCESS) {
            mfaService.auditMfaEvent(userID, userID, "MFA_REAUTHENTICATION",
                    passwordOk ? totpResult.name() : "INVALID_PASSWORD");
            if (recordFailedAttempt(userID)) {
                throw new AuthenticationException("Too many failed attempts. Account locked. Please try again later.");
            }
            throw new AuthenticationException("Reauthentication failed. Check your password and authentication code.");
        }

        mfaService.auditMfaEvent(userID, userID, "MFA_REAUTHENTICATION", "SUCCESS");
    }

    // Log out the current user
    public void logout() {
        Session.clear();
    }

    // Refresh the current user's data from the database
    public void refreshCurrentUser() {
        UserAccount currentUser = Session.getCurrentUser();
        if (currentUser != null) {
            UserAccount refreshedUser = userDao.getById(currentUser.getUserID());
            Session.setCurrentUser(refreshedUser);
        }
    }

    // Change the password for the given user after verifying current password
    public void changePassword(int userId, String currentPassword, String newPassword) throws AuthenticationException {
        // Fetch user
        UserAccount user = userDao.getById(userId);
        if (user == null) {
            throw new AuthenticationException("User not found.");
        }

        // Verify current password
        if (!PasswordUtil.verifyUserPassword(currentPassword, user)) {
            throw new AuthenticationException("Current password is incorrect.");
        }

        // Check if the new password is the same as the current password
        if (PasswordUtil.verifyUserPassword(newPassword, user)) {
            throw new AuthenticationException("New password cannot be the same as current password.");
        }

        // Validate password policy (length, breach check)
        if (!PasswordUtil.isPasswordLengthOkay(newPassword)) {
            throw new AuthenticationException("New password must be at least 8 characters long.");
        }
        if (PasswordUtil.isPasswordCompromised(newPassword)) {
            throw new AuthenticationException(
                    "New password has been compromised in a data breach. Please choose a different password.");
        }

        // Hash and apply new password
        PasswordUtil.applyHashedPassword(user, newPassword);
        boolean ok = userDao.update(user);
        if (!ok) {
            throw new AuthenticationException("Failed to update password.");
        }

        // Audit log the change. For security, the password hashes aren't logged
        AuditLogDAO auditDao = new AuditLogDAO();
        AuditLog log = new AuditLog();
        log.setUserID(user.getUserID());
        log.setCreatedAt(LocalDateTime.now());
        log.setAction("UPDATE");
        log.setEntityModified("UserAccount");
        log.setEntityID(user.getUserID());
        log.setAttributeModified("password");
        log.setOldValue("[REDACTED]");
        log.setNewValue("[REDACTED]");
        auditDao.insert(log);
    }

    // Change the password and clear the mustChangePassword flag
    public void completeEnforcedPasswordReset(int userId, String newPassword) throws AuthenticationException {
        // Fetch user
        UserAccount user = userDao.getById(userId);
        if (user == null) {
            throw new AuthenticationException("User not found.");
        }

        // Verify new password is not the same as the current password
        if (PasswordUtil.verifyUserPassword(newPassword, user)) {
            throw new AuthenticationException("New password cannot be the same as current password");
        }

        // Validate password policy (length, breach check)
        if (!PasswordUtil.isPasswordLengthOkay(newPassword)) {
            throw new AuthenticationException("New password must be at least 8 characters long.");
        }
        if (PasswordUtil.isPasswordCompromised(newPassword)) {
            throw new AuthenticationException(
                    "New password has been compromised in a data breach. Please choose a different password.");
        }

        PasswordUtil.applyHashedPassword(user, newPassword);

        // Apply new password and clear mustChangePassword flag
        boolean isEnforcedPasswordResetSuccessful = userDao.updatePasswordAndClearMustChange(userId, user.getPassword(),
                user.getPasswordSalt());
        if (!isEnforcedPasswordResetSuccessful) {
            throw new AuthenticationException("Failed to update password.");
        }

        // Refresh session to reflect changes
        refreshCurrentUser();

        // Audit log the change
        AuditLogDAO auditDao = new AuditLogDAO();
        AuditLog log = new AuditLog();
        log.setUserID(user.getUserID());
        log.setCreatedAt(LocalDateTime.now());
        log.setAction("UPDATE");
        log.setEntityModified("UserAccount");
        log.setEntityID(user.getUserID());
        log.setAttributeModified("mustChangePassword");
        log.setOldValue("true");
        log.setNewValue("false");
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
    public void resetPassword(String username, String birthday, String sssNumber,
            String newPassword) throws AuthenticationException {
        // Fetch and verify
        UserAccount user = userDao.getByUsername(username);
        if (user == null) {
            throw new AuthenticationException("User not found.");
        }
        if (!verifyIdentity(user.getEmployeeID(), birthday, sssNumber)) {
            throw new AuthenticationException("Verification failed.");
        }

        // Validate password policy (length, breach check)
        if (!PasswordUtil.isPasswordLengthOkay(newPassword)) {
            throw new AuthenticationException("New password must be at least 8 characters long.");
        }
        if (PasswordUtil.isPasswordCompromised(newPassword)) {
            throw new AuthenticationException(
                    "New password has been compromised in a data breach. Please choose a different password.");
        }

        // Apply change
        PasswordUtil.applyHashedPassword(user, newPassword);
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
        log.setOldValue("[REDACTED]");
        log.setNewValue("[REDACTED]");
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

    // Records a failed password or TOTP attempt and applies the lockout policy.
    // Returns true if this failure locked the account.
    private boolean recordFailedAttempt(int userID) {
        int failures = getConsecutiveFailures(userID) + 1;
        boolean locked = failures >= MAX_ATTEMPTS;
        LocalDateTime lockEnd = locked ? LocalDateTime.now().plusMinutes(LOCKOUT_DURATION_MINUTES) : null;
        recordLoginAttempt(userID, "FAILED", failures, locked, lockEnd);
        return locked;
    }

    private PreAuthChallenge issuePreAuthToken(UserAccount user) {
        // Drop expired tokens so abandoned logins do not accumulate
        PENDING_LOGINS.values().removeIf(p -> p.expiresAt().isBefore(LocalDateTime.now()));

        String token = OtpUtil.generateRecoveryToken();
        PENDING_LOGINS.put(OtpUtil.hashValue(token), new PendingLogin(user.getUserID(), !user.isMfaEnabled(),
                LocalDateTime.now().plusMinutes(PRE_AUTH_TOKEN_MINUTES)));
        return new PreAuthChallenge(token, user.getUsername(), !user.isMfaEnabled());
    }

    private PendingLogin resolvePendingLogin(String preAuthToken) throws AuthenticationException {
        if (preAuthToken == null || preAuthToken.isEmpty()) {
            throw new AuthenticationException("Your login session has expired. Please log in again.");
        }

        String tokenHash = OtpUtil.hashValue(preAuthToken);
        PendingLogin pending = PENDING_LOGINS.get(tokenHash);
        if (pending == null || pending.expiresAt().isBefore(LocalDateTime.now())) {
            PENDING_LOGINS.remove(tokenHash);
            throw new AuthenticationException("Your login session has expired. Please log in again.");
        }
        return pending;
    }

    // Legacy hash formats throw on unexpected input; treat that as a failed verification
    private boolean verifyPasswordSafely(String password, UserAccount user) {
        try {
            return PasswordUtil.verifyUserPassword(password, user);
        } catch (IllegalArgumentException ex) {
            return false;
        }
    }

    // Returns the number of consecutive failed login attempts before the last
    // successful login
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
    private void recordLoginAttempt(int userID, String status, int attemptCount, boolean locked,
            LocalDateTime lockEndTime) {
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

    public class AuthenticationException extends Exception {

        public AuthenticationException(String message) {
            super(message);
        }
    }

}

// public class AuthenticationException extends Exception {
//
// public AuthenticationException(String message) {
// super(message);
// }
// }