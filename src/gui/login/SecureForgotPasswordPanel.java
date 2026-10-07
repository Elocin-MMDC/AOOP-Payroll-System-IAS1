package gui.login;

import java.awt.Color;
import java.util.Objects;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import service.PasswordRecoveryService;
import util.UIUtil;

public class SecureForgotPasswordPanel extends JPanel {

    private final LoginPortal loginPortal;
    private final JTextField emailField = new JTextField();

    public SecureForgotPasswordPanel(LoginPortal loginPortal) {
        this.loginPortal = loginPortal;
        setBackground(Color.WHITE);
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        JLabel title = new JLabel("Forgot Password");
        title.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 28));
        title.setForeground(new Color(12, 72, 92));
        add(title, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 130, 370, 40));

        JLabel subtitle = new JLabel("Enter your email to receive a password reset code.");
        subtitle.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14));
        subtitle.setForeground(new Color(12, 72, 92));
        add(subtitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 170, 370, 20));

        JLabel emailLabel = new JLabel("Email");
        emailLabel.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14));
        emailLabel.setForeground(new Color(12, 72, 92));
        add(emailLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 215, 160, 20));

        emailField.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14));
        add(emailField, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 240, 370, 40));

        JButton submitButton = new JButton("SUBMIT");
        submitButton.setBackground(new Color(0, 135, 0));
        submitButton.setForeground(Color.WHITE);
        submitButton.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 18));
        submitButton.addActionListener(event -> submitEmailReset());
        add(submitButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 360, 370, 50));

        JLabel jLabelBack = new javax.swing.JLabel();
        jLabelBack.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/back-icon.png"))); // NOI18N
        jLabelBack.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                loginPortal.showLoginPanel();
            }
        });
        add(jLabelBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 70, 80));
    }

    private void submitEmailReset() {
        String email = emailField.getText() == null ? "" : emailField.getText().trim();
        if (email.isEmpty()) {
            UIUtil.showWarningMessage(this, "Please enter your email address.", "Reset Password");
            return;
        }

        try {
            PasswordRecoveryService service = new PasswordRecoveryService();
            boolean success = service.requestPasswordResetOTP(email);
            if (success) {
                UIUtil.showInfoMessage(this,
                        "If an account exists for this email, a recovery code has been sent.",
                        "Password Reset");
                emailField.setText("");
                
                OtpVerificationDialog otpDialog = new OtpVerificationDialog(loginPortal, email);
                otpDialog.setVisible(true);
            } else {
                UIUtil.showErrorMessage(this, "Unable to send reset email at this time.", "Reset Password Failed");
            }
        } catch (Exception ex) {
            UIUtil.showErrorMessage(this, ex.getMessage(), "Reset Password Failed");
        }
    }
}
