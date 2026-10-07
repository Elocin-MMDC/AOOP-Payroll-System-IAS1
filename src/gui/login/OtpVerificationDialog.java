package gui.login;

import service.PasswordRecoveryService;
import util.OtpUtil;
import javax.swing.*;
import java.awt.*;
import java.util.function.Consumer;

public class OtpVerificationDialog extends Dialog {
    private final PasswordRecoveryService recoveryService = new PasswordRecoveryService();
    private final Consumer<String> onVerified;
    private JLabel lblMaskedEmail;
    private JTextField txtOtpCode;
    private JButton btnResend;
    private JButton btnVerify;
    private JButton btnCancel;
    private LoginPortal loginPortal;
    private String rawEmail;

    public OtpVerificationDialog(Frame parent, String email) {
        this(parent, email, null);
    }

    public OtpVerificationDialog(Frame parent, String email, Consumer<String> onVerified) {
        super(parent, "Verify Security Code", true);
        this.loginPortal = parent instanceof LoginPortal ? (LoginPortal) parent : null;
        this.onVerified = onVerified;
        if (loginPortal == null && onVerified == null) {
            throw new IllegalArgumentException("OTP recovery requires a LoginPortal parent.");
        }
        this.rawEmail = email;

        initComponents();
        this.setSize(480, 320);
        this.setLocationRelativeTo(parent);
        this.setResizable(false);
    }

    private void initComponents() {
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 0, 8, 0);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.weightx = 1.0;

        // Title Header
        JLabel lblTitle = new JLabel("Enter One-Time Password");
        lblTitle.setFont(new Font("SansSerif", Font.BOLD, 16));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        mainPanel.add(lblTitle, gbc);

        // Masked Email Notice
        lblMaskedEmail = new JLabel("OTP sent to: " + OtpUtil.maskEmail(rawEmail));
        lblMaskedEmail.setFont(new Font("SansSerif", Font.PLAIN, 12));
        lblMaskedEmail.setForeground(Color.GRAY);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 3;
        mainPanel.add(lblMaskedEmail, gbc);

        // OTP Label
        JLabel lblOtp = new JLabel("6-Digit Code:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.weightx = 0;
        mainPanel.add(lblOtp, gbc);

        // OTP Input Field
        txtOtpCode = new JTextField(12);
        txtOtpCode.setFont(new Font("Monospaced", Font.BOLD, 16));
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.weightx = 1.0;
        gbc.insets = new Insets(8, 8, 8, 8);
        mainPanel.add(txtOtpCode, gbc);

        // Resend Button
        btnResend = new JButton("Resend OTP");
        btnResend.addActionListener(e -> handleResendOTP());
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.weightx = 0;
        gbc.insets = new Insets(8, 8, 8, 0);
        mainPanel.add(btnResend, gbc);

        // Spacing
        gbc.gridy = 3;
        gbc.gridwidth = 3;
        gbc.insets = new Insets(10, 0, 10, 0);
        mainPanel.add(Box.createVerticalStrut(10), gbc);

        // Button Panel
        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 10, 0));
        btnVerify = new JButton("Verify");
        btnVerify.addActionListener(e -> handleVerifyOTP());
        buttonPanel.add(btnVerify);

        btnCancel = new JButton("Cancel");
        btnCancel.addActionListener(e -> dispose());
        buttonPanel.add(btnCancel);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 3;
        gbc.insets = new Insets(8, 0, 0, 0);
        mainPanel.add(buttonPanel, gbc);

        this.add(mainPanel);
    }

    private void handleVerifyOTP() {
        String inputOtp = txtOtpCode.getText().trim();

        if (inputOtp.isEmpty() || inputOtp.length() != 6) {
            JOptionPane.showMessageDialog(this, 
                "Please enter a valid 6-digit OTP code.", 
                "Invalid Input", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Verify OTP with backend and retrieve recovery token
        String recoveryToken = recoveryService.verifyOTP(rawEmail, inputOtp);

        if (recoveryToken != null) {
            if (onVerified != null) {
                this.dispose();
                onVerified.accept(recoveryToken);
                return;
            }

            JOptionPane.showMessageDialog(this, 
                "OTP verified successfully. Please choose your new password.", 
                "Success", JOptionPane.INFORMATION_MESSAGE);

            this.dispose(); // Close OTP Dialog
            loginPortal.showRecoveryPasswordReset(rawEmail, recoveryToken);

        } else {
            JOptionPane.showMessageDialog(this, 
                "Invalid or expired OTP. Please try again or request a new code.", 
                "Verification Failed", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void handleResendOTP() {
        boolean sent = recoveryService.requestPasswordResetOTP(rawEmail);
        if (sent) {
            JOptionPane.showMessageDialog(this, 
                "A new OTP code has been sent to your email address.", 
                "OTP Resent", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}