
package service;

import model.dao.EmailOtpDAO;
import model.dao.AuditLogDAO;
import model.dao.UserAccountDAO;
import model.pojo.AuditLog;
import model.pojo.UserAccount;
import util.OtpUtil;
import util.PasswordUtil;
import service.AuthenticationService;
import java.time.LocalDateTime;

public class PasswordRecoveryService {

    private final EmailOtpDAO otpDAO = new EmailOtpDAO();
    private final UserAccountDAO userAccountDao = new UserAccountDAO();
    private static final int OTP_REQUEST_LIMIT = 10;
    private static final int OTP_REQUEST_WINDOW_MINUTES = 60;

    /**
     * Step 1: Request OTP for a given user email.
     */
    public boolean requestPasswordResetOTP(String email) {
        int auditUserID = 0;
        String outcome = "FAILED";
        try {
            UserAccount user = userAccountDao.findByEmail(email);
            if (user == null) {
                auditRecoveryEvent(0, "OTP_REQUEST", "ACCOUNT_NOT_FOUND");
                return true; 
            }

            auditUserID = user.getUserID();
            String otpCode = OtpUtil.generate6DigitOTP();
            EmailOtpDAO.OtpRequestResult result = otpDAO.createOTP(user.getUserID(), otpCode, 10,
                    OTP_REQUEST_LIMIT, OTP_REQUEST_WINDOW_MINUTES);
            if (result == EmailOtpDAO.OtpRequestResult.RATE_LIMITED) {
                auditRecoveryEvent(auditUserID, "OTP_REQUEST", "RATE_LIMITED");
                return true;
            }

            EmailService.sendOTPEmail(email, otpCode);
            outcome = "SENT";
            auditRecoveryEvent(auditUserID, "OTP_REQUEST", outcome);
            return true;
        } catch (Exception e) {
            auditRecoveryEvent(auditUserID, "OTP_REQUEST", outcome);
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Step 2: Verify submitted 6-digit OTP and issue a single-use recovery token.
     */
    public String verifyOTP(String email, String plainOtp) {
        int auditUserID = 0;
        try {
            UserAccount user = userAccountDao.findByEmail(email);
            if (user == null) {
                auditRecoveryEvent(0, "OTP_VERIFICATION", "ACCOUNT_NOT_FOUND");
                return null;
            }

            auditUserID = user.getUserID();
            String recoveryToken = otpDAO.verifyAndConsumeOTP(auditUserID, plainOtp);
            auditRecoveryEvent(auditUserID, "OTP_VERIFICATION",
                    recoveryToken == null ? "FAILED" : "SUCCESS");
            return recoveryToken;
        } catch (Exception e) {
            auditRecoveryEvent(auditUserID, "OTP_VERIFICATION", "ERROR");
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Step 3: Complete password reset.
     */
    public void resetPasswordWithToken(String email, String rawRecoveryToken, String newPassword) throws Exception {
        int auditUserID = 0;
        try {
            UserAccount user = userAccountDao.findByEmail(email);
            if (user == null) {
                throw new IllegalArgumentException("Unable to find account for password recovery.");
            }

            auditUserID = user.getUserID();
            boolean isValidToken = otpDAO.validateRecoveryToken(auditUserID, rawRecoveryToken);
            if (!isValidToken) {
                throw new IllegalArgumentException("The recovery code is invalid or expired. Please request a new code.");
            }

            if (!PasswordUtil.isPasswordLengthOkay(newPassword)) {
                throw new IllegalArgumentException("Password must be between 8 and 64 characters.");
            }

            if (PasswordUtil.isPasswordCompromised(newPassword)) {
                throw new IllegalArgumentException(
                        "The password you have chosen is known to be compromised. Please choose a different password.");
            }

            AuthenticationService authenticationService = new AuthenticationService();
            authenticationService.completeEnforcedPasswordReset(auditUserID, newPassword);
            auditRecoveryEvent(auditUserID, "PASSWORD_RESET", "SUCCESS");
        } catch (Exception e) {
            auditRecoveryEvent(auditUserID, "PASSWORD_RESET", "FAILED");
            throw e;
        }
    }

    private void auditRecoveryEvent(int userID, String event, String outcome) {
        AuditLog log = new AuditLog();
        log.setUserID(userID);
        log.setCreatedAt(LocalDateTime.now());
        log.setAction("INSERT");
        log.setEntityModified("PasswordRecovery");
        log.setEntityID(Math.max(userID, 0));
        log.setAttributeModified(event);
        log.setNewValue(outcome);

        if (!new AuditLogDAO().insert(log)) {
            System.err.println("Failed to write password recovery audit event: " + event);
        }
    }
}