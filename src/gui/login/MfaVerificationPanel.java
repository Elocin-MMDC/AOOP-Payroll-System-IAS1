package gui.login;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import model.pojo.UserAccount;
import service.AuthenticationService;
import service.AuthenticationService.PreAuthChallenge;
import service.MfaService;
import util.QrCodeUtil;
import util.TotpUtil;
import util.UIUtil;

/**
 * Second login step: enrolls an authenticator app (first login) or verifies the
 * current 6-digit TOTP code before any role portal can be opened.
 */
public class MfaVerificationPanel extends JPanel {

    private static final Color BRAND = new Color(12, 72, 92);
    private static final int QR_SIZE = 180;

    private final LoginPortal loginPortal;
    private final AuthenticationService authService = new AuthenticationService();

    private final JLabel title = new JLabel();
    private final JLabel subtitle = new JLabel();
    private final JLabel qrLabel = new JLabel();
    private final JLabel manualKeyLabel = new JLabel("Can't scan? Enter this key manually:");
    private final JTextField manualKeyField = new JTextField();
    private final JLabel codeLabel = new JLabel("6-digit authentication code");
    private final JTextField codeField = new JTextField();
    private final JButton verifyButton = new JButton("VERIFY");
    private final JLabel cancelLabel = new JLabel("Cancel and return to login");

    private String preAuthToken;
    private boolean enrollment;

    public MfaVerificationPanel(LoginPortal loginPortal) {
        this.loginPortal = loginPortal;
        setBackground(Color.WHITE);
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        title.setFont(new Font("Segoe UI", Font.BOLD, 24));
        title.setForeground(BRAND);

        subtitle.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        subtitle.setForeground(BRAND);

        qrLabel.setHorizontalAlignment(SwingConstants.CENTER);

        manualKeyLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        manualKeyField.setEditable(false);
        manualKeyField.setFont(new Font("Monospaced", Font.BOLD, 13));
        manualKeyField.setHorizontalAlignment(SwingConstants.CENTER);

        codeField.setFont(new Font("Monospaced", Font.BOLD, 20));
        codeField.setHorizontalAlignment(SwingConstants.CENTER);
        codeField.addActionListener(event -> submitCode());

        verifyButton.setBackground(BRAND);
        verifyButton.setForeground(Color.WHITE);
        verifyButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
        verifyButton.addActionListener(event -> submitCode());

        cancelLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        cancelLabel.setForeground(BRAND);
        cancelLabel.setHorizontalAlignment(SwingConstants.CENTER);
        cancelLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        cancelLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cancel();
            }
        });

        add(title, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 20, 370, 36));
        add(subtitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 56, 370, 50));
        add(qrLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(155, 108, QR_SIZE, QR_SIZE));
        add(manualKeyLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 292, 370, 20));
        add(manualKeyField, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 312, 370, 30));
        add(codeLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 352, 370, 22));
        add(codeField, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 376, 370, 44));
        add(verifyButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 436, 370, 50));
        add(cancelLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 500, 370, 22));
    }

    /**
     * Prepares the panel for a new pre-authenticated login. For users without MFA,
     * a fresh secret and QR code are generated for enrollment.
     */
    public boolean prepare(PreAuthChallenge challenge) {
        preAuthToken = challenge.token();
        enrollment = challenge.enrollmentRequired();
        codeField.setText("");
        clearEnrollmentDetails();

        if (enrollment) {
            try {
                MfaService.EnrollmentDetails details = authService.startMfaEnrollment(preAuthToken);
                title.setText("Set up two-factor authentication");
                subtitle.setText("<html>Scan this QR code with an authenticator app (e.g. Google or Microsoft "
                        + "Authenticator), then enter the 6-digit code it shows.</html>");
                qrLabel.setIcon(new ImageIcon(QrCodeUtil.generateQrImage(details.otpAuthUri(), QR_SIZE)));
                manualKeyField.setText(TotpUtil.formatSecretForDisplay(details.base32Secret()));
            } catch (Exception ex) {
                UIUtil.showErrorMessage(loginPortal, ex.getMessage(), "MFA Setup Failed");
                cancel();
                return false;
            }
        } else {
            title.setText("Two-factor authentication");
            subtitle.setText("<html>Enter the 6-digit code from your authenticator app to continue.</html>");
        }

        qrLabel.setVisible(enrollment);
        manualKeyLabel.setVisible(enrollment);
        manualKeyField.setVisible(enrollment);
        return true;
    }

    private void submitCode() {
        String code = codeField.getText().trim();
        if (!TotpUtil.isWellFormedCode(code)) {
            UIUtil.showWarningMessage(this, "Please enter the 6-digit code from your authenticator app.", "Invalid Code");
            return;
        }

        UserAccount user;
        try {
            user = authService.completeLogin(preAuthToken, code);
        } catch (AuthenticationService.AuthenticationException ex) {
            codeField.setText("");
            UIUtil.showErrorMessage(this, ex.getMessage(), "Verification Failed");
            // Token is gone after lockout or expiry; send the user back to the login screen
            String message = ex.getMessage();
            if (message.contains("locked") || message.contains("expired")
                    || message.contains("not configured") || message.contains("Invalid username")) {
                cancel();
            }
            return;
        }

        if (enrollment) {
            UIUtil.showInfoMessage(this, "Two-factor authentication is now enabled for your account.", "MFA Enabled");
        }
        resetState();

        if (Boolean.TRUE.equals(user.getMustChangePassword())) {
            UIUtil.showInfoMessage(this, "You are required to change your password before proceeding.", "Password Change Required");
            loginPortal.showEnforcedPasswordReset();
            return;
        }

        loginPortal.openUserPortal();
    }

    private void cancel() {
        authService.cancelLogin(preAuthToken);
        resetState();
        loginPortal.showLoginPanel();
    }

    private void resetState() {
        preAuthToken = null;
        codeField.setText("");
        clearEnrollmentDetails();
    }

    // The enrollment secret is only held on screen while needed
    private void clearEnrollmentDetails() {
        qrLabel.setIcon(null);
        manualKeyField.setText("");
    }
}
