package service;

import java.sql.SQLException;
import java.time.LocalDateTime;
import model.dao.AuditLogDAO;
import model.dao.UserTotpDAO;
import model.pojo.AuditLog;
import model.pojo.UserAccount;
import model.pojo.UserTotp;
import util.AccessControlUtil;
import util.PiiCryptoUtil;
import util.Session;
import util.TotpUtil;

/**
 * Phase 4: TOTP enrollment, verification with replay prevention, and MFA reset.
 * Secrets and codes are never written to the audit log.
 */
public class MfaService {

    private final UserTotpDAO totpDao = new UserTotpDAO();

    public enum VerificationResult {
        SUCCESS,
        INVALID_CODE,
        REPLAY_REJECTED,
        NOT_ENROLLED
    }

    // Secret and URI shown once on the enrollment screen; never persisted in plaintext
    public record EnrollmentDetails(String base32Secret, String otpAuthUri) {}

    /**
     * Generates a new secret, stores it encrypted as a pending enrollment, and returns the
     * values needed to render the QR code.
     */
    public EnrollmentDetails beginEnrollment(UserAccount user) {
        String secret = TotpUtil.generateSecret();
        String encryptedSecret = PiiCryptoUtil.encrypt(secret, secretContext(user.getUserID()));

        if (!totpDao.savePendingSecret(user.getUserID(), encryptedSecret)) {
            auditMfaEvent(user.getUserID(), user.getUserID(), "MFA_ENROLLMENT", "ERROR");
            throw new IllegalStateException("Unable to start MFA enrollment. Please try again.");
        }

        auditMfaEvent(user.getUserID(), user.getUserID(), "MFA_ENROLLMENT", "SECRET_ISSUED");
        return new EnrollmentDetails(secret, TotpUtil.buildOtpAuthUri(user.getUsername(), secret));
    }

    /**
     * Confirms a pending enrollment with the first code from the authenticator app.
     */
    public boolean confirmEnrollment(int userID, String code) {
        try {
            UserTotp totp = totpDao.getByUserId(userID);
            if (totp == null || totp.isEnrolled()) {
                auditMfaEvent(userID, userID, "MFA_ENROLLMENT", "NO_PENDING_ENROLLMENT");
                return false;
            }

            long step = TotpUtil.findMatchingTimeStep(decryptSecret(totp), code);
            if (step < 0 || !totpDao.activateEnrollment(userID, step)) {
                auditMfaEvent(userID, userID, "MFA_ENROLLMENT", "FAILED");
                return false;
            }

            auditMfaEvent(userID, userID, "MFA_ENROLLMENT", "SUCCESS");
            auditMfaFlagChange(userID, userID, false, true);
            return true;
        } catch (SQLException | SecurityException e) {
            auditMfaEvent(userID, userID, "MFA_ENROLLMENT", "ERROR");
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Verifies a code for an enrolled user. A code is accepted only once: its time step
     * is consumed through an atomic update, so replays and reuse of older codes fail.
     */
    public VerificationResult verifyCode(int userID, String code) {
        UserTotp totp = totpDao.getByUserId(userID);
        if (totp == null || !totp.isEnrolled()) {
            return VerificationResult.NOT_ENROLLED;
        }

        long step = TotpUtil.findMatchingTimeStep(decryptSecret(totp), code);
        if (step < 0) {
            return VerificationResult.INVALID_CODE;
        }

        Long lastUsed = totp.getLastUsedTimeStep();
        if (lastUsed != null && step <= lastUsed) {
            return VerificationResult.REPLAY_REJECTED;
        }

        try {
            return totpDao.consumeTimeStep(userID, step)
                    ? VerificationResult.SUCCESS
                    : VerificationResult.REPLAY_REJECTED;
        } catch (SQLException e) {
            e.printStackTrace();
            return VerificationResult.INVALID_CODE;
        }
    }

    /**
     * Removes the current user's own authenticator. Callers must first reauthenticate the
     * user with AuthenticationService.reauthenticateForMfaChange.
     */
    public void resetOwnMfa() throws SQLException {
        int userID = Session.getCurrentUser().getUserID();
        clearEnrollment(userID, userID);
    }

    /**
     * IT Admin override for a lost authenticator. Callers must first reauthenticate the
     * IT Admin with AuthenticationService.reauthenticateForMfaChange.
     */
    public void adminResetMfa(int targetUserID) throws SQLException {
        AccessControlUtil.requireRole("IT Admin");
        clearEnrollment(Session.getCurrentUser().getUserID(), targetUserID);
    }

    private void clearEnrollment(int actorUserID, int targetUserID) throws SQLException {
        try {
            if (!totpDao.clearEnrollment(targetUserID)) {
                throw new SQLException("User account not found while resetting MFA.");
            }
        } catch (SQLException e) {
            auditMfaEvent(actorUserID, targetUserID, "MFA_RESET", "FAILED");
            throw e;
        }

        auditMfaEvent(actorUserID, targetUserID, "MFA_RESET", "SUCCESS");
        auditMfaFlagChange(actorUserID, targetUserID, true, false);
    }

    // Record an MFA event in the security audit table. Outcomes never contain secrets or codes.
    public void auditMfaEvent(int actorUserID, int targetUserID, String event, String outcome) {
        AuditLog log = new AuditLog();
        log.setUserID(actorUserID);
        log.setCreatedAt(LocalDateTime.now());
        log.setAction("INSERT");
        log.setEntityModified("MFA");
        log.setEntityID(Math.max(targetUserID, 0));
        log.setAttributeModified(event);
        log.setNewValue(outcome);

        if (!new AuditLogDAO().insert(log)) {
            System.err.println("Failed to write MFA audit event: " + event);
        }
    }

    private void auditMfaFlagChange(int actorUserID, int targetUserID, boolean oldValue, boolean newValue) {
        AuditLog log = new AuditLog();
        log.setUserID(actorUserID);
        log.setCreatedAt(LocalDateTime.now());
        log.setAction("UPDATE");
        log.setEntityModified("UserAccount");
        log.setEntityID(targetUserID);
        log.setAttributeModified("mfaEnabled");
        log.setOldValue(String.valueOf(oldValue));
        log.setNewValue(String.valueOf(newValue));
        new AuditLogDAO().insert(log);
    }

    private String decryptSecret(UserTotp totp) {
        return PiiCryptoUtil.decrypt(totp.getEncryptedSecret(), secretContext(totp.getUserID()));
    }

    // AES-GCM associated data binds each ciphertext to its owner, so secrets cannot be swapped between rows
    private static String secretContext(int userID) {
        return "UserTotp.secret:" + userID;
    }
}
