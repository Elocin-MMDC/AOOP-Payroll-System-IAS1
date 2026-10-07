package gui.login;

import java.awt.Color;
import java.util.Arrays;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import service.AuthenticationService;
import service.PasswordRecoveryService;
import util.Session;
import util.UIUtil;

public class EnforcedPasswordResetPanel extends JPanel {

    private final LoginPortal loginPortal;
    private final JPasswordField newPasswordField = new JPasswordField();
    private final JPasswordField confirmPasswordField = new JPasswordField();
    private String recoveryEmail;
    private String recoveryToken;

    public EnforcedPasswordResetPanel(LoginPortal loginPortal) {
        this.loginPortal = loginPortal;
        setBackground(Color.WHITE);
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        JLabel title = new JLabel("Create your new password");
        title.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 26));
        title.setForeground(new Color(12, 72, 92));
        add(title, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 90, 370, 40));

        JLabel subtitle = new JLabel("<html>Choose a strong password to continue.</html>");
        subtitle.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14));
        subtitle.setForeground(new Color(12, 72, 92));
        add(subtitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 135, 270, 56));

        JLabel newPasswordLabel = new JLabel("New password");
        add(newPasswordLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 215, 370, 22));
        add(newPasswordField, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 240, 370, 40));

        JLabel confirmPasswordLabel = new JLabel("Confirm new password");
        add(confirmPasswordLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 300, 370, 22));
        add(confirmPasswordField, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 325, 370, 40));

        JButton submitButton = new JButton("CONTINUE");
        submitButton.setBackground(new Color(12, 72, 92));
        submitButton.setForeground(Color.WHITE);
        submitButton.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 16));
        submitButton.addActionListener(event -> submitPassword());
        add(submitButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 410, 370, 50));
    }

    public void prepareRecoveryReset(String email, String token) {
        recoveryEmail = email;
        recoveryToken = token;
        newPasswordField.setText("");
        confirmPasswordField.setText("");
    }

    private void submitPassword() {
        char[] newPassword = newPasswordField.getPassword();
        char[] confirmation = confirmPasswordField.getPassword();
        try {
            if (newPassword.length == 0 || confirmation.length == 0) {
                UIUtil.showWarningMessage(this, "Both password fields are required.", "Reset Password");
                return;
            }
            if (!Arrays.equals(newPassword, confirmation)) {
                UIUtil.showErrorMessage(this, "The passwords do not match.", "Reset Password");
                return;
            }

            AuthenticationService authService = new AuthenticationService();
            if (recoveryToken != null) {
                new PasswordRecoveryService().resetPasswordWithToken(
                    recoveryEmail, recoveryToken, new String(newPassword));
                    recoveryEmail = null;
                    recoveryToken = null;
            } else {
                authService.completeEnforcedPasswordReset(
                    Session.getCurrentUser().getUserID(),
                    new String(newPassword));
            }

            newPasswordField.setText("");
            confirmPasswordField.setText("");

            UIUtil.showInfoMessage(loginPortal, "Password updated successfully. Please log in again.", "Password Updated");
            
            // After successful password reset, ask user to log in again
            authService.logout();
            loginPortal.showLoginPanel();

        } catch (Exception ex) {
            UIUtil.showErrorMessage(this, ex.getMessage(), "Reset Password Failed");

        } finally {
            Arrays.fill(newPassword, '\0');
            Arrays.fill(confirmation, '\0');
        }
    }
}