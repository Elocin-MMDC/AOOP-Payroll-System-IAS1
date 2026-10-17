package service;

import model.dao.EmailOtpDAO;
import model.dao.EmailOtpDAO.OtpRequestResult;
import model.dao.EmailOtpDAO.OtpVerifyResult;
import model.dao.EmailOtpDAO.OtpVerifyStatus;
import model.dao.AuditLogDAO;
import model.dao.UserAccountDAO;
import model.pojo.AuditLog;
import model.pojo.UserAccount;
import util.OtpUtil;
import java.time.LocalDateTime;

public class PasswordRecoveryService {

    private final EmailOtpDAO otpDAO = new EmailOtpDAO();
    private final UserAccountDAO userAccountDao = new UserAccountDAO();
    private static final int OTP_REQUEST_LIMIT = 10;
    private static final int OTP_REQUEST_WINDOW_MINUTES = 60;

    /**
     * Step 1: Request OTP for a given user email.
     */
    public OtpRequestResult requestPasswordResetOTP(String email) {
        int auditUserID = 0;
        try {
            UserAccount user = userAccountDao.findByEmail(email);
            if (user == null) {
                auditRecoveryEvent(0, "OTP_REQUEST", "ACCOUNT_NOT_FOUND");
                return OtpRequestResult.CREATED; // Prevent email enumeration
            }

            auditUserID = user.getUserID();
            String otpCode = OtpUtil.generate6DigitOTP();
            
            OtpRequestResult result = otpDAO.createOTP(user.getUserID(), otpCode, 10,
                    OTP_REQUEST_LIMIT, OTP_REQUEST_WINDOW_MINUTES);

            if (result == OtpRequestResult.RATE_LIMITED) {
                auditRecoveryEvent(auditUserID, "OTP_REQUEST", "RATE_LIMITED");
                return result;
            } else if (result == OtpRequestResult.BURST_COOLDOWN) {
                auditRecoveryEvent(auditUserID, "OTP_REQUEST", "BURST_COOLDOWN");
                return result;
            }

            EmailService.sendOTPEmail(email, otpCode);
            auditRecoveryEvent(auditUserID, "OTP_REQUEST", "SENT");
            return OtpRequestResult.CREATED;

        } catch (Exception e) {
            auditRecoveryEvent(auditUserID, "OTP_REQUEST", "FAILED");
            e.printStackTrace();
            return OtpRequestResult.FAILED;
        }
    }

    /**
     * Step 2: Verify submitted OTP and audit results.
     */
    public OtpVerifyResult verifyOTP(String email, String plainOtp) {
        int auditUserID = 0;
        try {
            UserAccount user = userAccountDao.findByEmail(email);
            if (user == null) {
                auditRecoveryEvent(0, "OTP_VERIFICATION", "ACCOUNT_NOT_FOUND");
                return new OtpVerifyResult(OtpVerifyStatus.INVALID_OR_EXPIRED, null);
            }

            auditUserID = user.getUserID();
            OtpVerifyResult result = otpDAO.verifyAndConsumeOTP(auditUserID, plainOtp);

            String outcome = switch (result.getStatus()) {
                case SUCCESS -> "SUCCESS";
                case MAX_ATTEMPTS_EXCEEDED -> "LOCKED_OUT";
                case INVALID_OR_EXPIRED -> "FAILED";
            };

            auditRecoveryEvent(auditUserID, "OTP_VERIFICATION", outcome);
            return result;

        } catch (Exception e) {
            auditRecoveryEvent(auditUserID, "OTP_VERIFICATION", "ERROR");
            e.printStackTrace();
            return new OtpVerifyResult(OtpVerifyStatus.INVALID_OR_EXPIRED, null);
        }
    }

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

            AuthenticationService authenticationService = new AuthenticationService();
            authenticationService.validateNewPassword(newPassword);

            if (!otpDAO.consumeRecoveryToken(auditUserID, rawRecoveryToken)) {
                throw new IllegalArgumentException("The recovery code is invalid or expired. Please request a new code.");
            }

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