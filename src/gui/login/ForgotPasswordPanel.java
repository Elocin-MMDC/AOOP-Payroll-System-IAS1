package gui.login;

import java.awt.CardLayout;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import service.AuthenticationService;
import util.UIUtil;

public class ForgotPasswordPanel extends javax.swing.JPanel {
    
    private final LoginPortal loginPortal;

    public ForgotPasswordPanel(LoginPortal loginPortal) {
        this.loginPortal = loginPortal;
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelForgotPassword = new javax.swing.JPanel();
        jLabelBirthday = new javax.swing.JLabel();
        jLabelForgotPassword = new javax.swing.JLabel();
        jTextFieldUsername = new javax.swing.JTextField();
        jButtonSubmit = new javax.swing.JButton();
        jLabelUsername = new javax.swing.JLabel();
        jLabelInstruction = new javax.swing.JLabel();
        jLabelMotorLogo = new javax.swing.JLabel();
        jLabelSssNumber = new javax.swing.JLabel();
        jPasswordFieldSssNumber = new javax.swing.JPasswordField();
        jLabelNewPassword = new javax.swing.JLabel();
        jPasswordFieldNewPassword = new javax.swing.JPasswordField();
        jPasswordFieldConfirmPassword = new javax.swing.JPasswordField();
        jLabelConfirmPassword = new javax.swing.JLabel();
        jDateChooserBirthday = new com.toedter.calendar.JDateChooser();
        jLabelBack = new javax.swing.JLabel();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanelForgotPassword.setBackground(new java.awt.Color(255, 255, 255));
        jPanelForgotPassword.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelBirthday.setBackground(new java.awt.Color(255, 255, 255));
        jLabelBirthday.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        jLabelBirthday.setForeground(new java.awt.Color(12, 72, 92));
        jLabelBirthday.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelBirthday.setText("Birthday");
        jPanelForgotPassword.add(jLabelBirthday, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 220, 160, 20));

        jLabelForgotPassword.setBackground(new java.awt.Color(255, 255, 255));
        jLabelForgotPassword.setFont(new java.awt.Font("Segoe UI", 1, 28)); // NOI18N
        jLabelForgotPassword.setForeground(new java.awt.Color(12, 72, 92));
        jLabelForgotPassword.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelForgotPassword.setText("Forgot Password");
        jPanelForgotPassword.add(jLabelForgotPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 70, 370, 40));

        jTextFieldUsername.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldUsernameActionPerformed(evt);
            }
        });
        jPanelForgotPassword.add(jTextFieldUsername, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 180, 370, 40));

        jButtonSubmit.setBackground(new java.awt.Color(0, 135, 0));
        jButtonSubmit.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButtonSubmit.setForeground(new java.awt.Color(255, 255, 255));
        jButtonSubmit.setText("SUBMIT");
        jButtonSubmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSubmitActionPerformed(evt);
            }
        });
        jPanelForgotPassword.add(jButtonSubmit, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 480, 370, 50));

        jLabelUsername.setBackground(new java.awt.Color(255, 255, 255));
        jLabelUsername.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        jLabelUsername.setForeground(new java.awt.Color(12, 72, 92));
        jLabelUsername.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelUsername.setText("Username");
        jPanelForgotPassword.add(jLabelUsername, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 160, 160, 20));

        jLabelInstruction.setBackground(new java.awt.Color(255, 255, 255));
        jLabelInstruction.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabelInstruction.setForeground(new java.awt.Color(12, 72, 92));
        jLabelInstruction.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelInstruction.setText("Verify your identity to reset your password");
        jPanelForgotPassword.add(jLabelInstruction, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 110, 370, 20));
        jPanelForgotPassword.add(jLabelMotorLogo, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 40, -1, 40));

        jLabelSssNumber.setBackground(new java.awt.Color(255, 255, 255));
        jLabelSssNumber.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        jLabelSssNumber.setForeground(new java.awt.Color(12, 72, 92));
        jLabelSssNumber.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelSssNumber.setText("SSS #");
        jPanelForgotPassword.add(jLabelSssNumber, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 280, 160, 20));

        jPasswordFieldSssNumber.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPasswordFieldSssNumberActionPerformed(evt);
            }
        });
        jPanelForgotPassword.add(jPasswordFieldSssNumber, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 300, 370, 40));

        jLabelNewPassword.setBackground(new java.awt.Color(255, 255, 255));
        jLabelNewPassword.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        jLabelNewPassword.setForeground(new java.awt.Color(12, 72, 92));
        jLabelNewPassword.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelNewPassword.setText("New Password");
        jPanelForgotPassword.add(jLabelNewPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 340, 160, 20));

        jPasswordFieldNewPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPasswordFieldNewPasswordActionPerformed(evt);
            }
        });
        jPanelForgotPassword.add(jPasswordFieldNewPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 360, 370, 40));

        jPasswordFieldConfirmPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPasswordFieldConfirmPasswordActionPerformed(evt);
            }
        });
        jPanelForgotPassword.add(jPasswordFieldConfirmPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 420, 370, 40));

        jLabelConfirmPassword.setBackground(new java.awt.Color(255, 255, 255));
        jLabelConfirmPassword.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        jLabelConfirmPassword.setForeground(new java.awt.Color(12, 72, 92));
        jLabelConfirmPassword.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelConfirmPassword.setText("Confirm Password");
        jPanelForgotPassword.add(jLabelConfirmPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 400, 160, 20));

        jDateChooserBirthday.setFocusable(false);
        jPanelForgotPassword.add(jDateChooserBirthday, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 240, 370, 40));

        jLabelBack.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/back-icon.png"))); // NOI18N
        jLabelBack.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelBackMouseClicked(evt);
            }
        });
        jPanelForgotPassword.add(jLabelBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 70, 80));

        add(jPanelForgotPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 490, 570));
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldUsernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldUsernameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldUsernameActionPerformed

    private void jButtonSubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSubmitActionPerformed
        // TODO add your handling code here:
        String username = jTextFieldUsername.getText().trim();
        Date birthdayDate = jDateChooserBirthday.getDate();
        if (birthdayDate == null) {
            UIUtil.showWarningMessage(this, "All fields are required.", "Reset Password");
            return;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");    
        String birthday = sdf.format(birthdayDate);
        String sssNumber = new String(jPasswordFieldSssNumber.getPassword());
        String newPassword = new String(jPasswordFieldNewPassword.getPassword());
        String confirmPassword = new String(jPasswordFieldConfirmPassword.getPassword());

        // Empty‐field checks
        if (username.isEmpty() || birthday.isEmpty() || sssNumber.isEmpty()
                || newPassword.isEmpty() || confirmPassword.isEmpty()) {
            UIUtil.showWarningMessage(this, "All fields are required.", "Reset Password");
            return;
        }

        // Password match check
        if (!newPassword.equals(confirmPassword)) {
            UIUtil.showErrorMessage(this, "New password and confirmation do not match.", "Reset Password");
            return;
        }

        // Attempt the reset
        AuthenticationService authService = new AuthenticationService();
        try {
            authService.resetPassword(username, birthday, sssNumber, newPassword);
            UIUtil.showInfoMessage(this, "Password reset successful! You may now log in with your new password.", "Reset Password");

            CardLayout cardLayout = (CardLayout) loginPortal.getPanelParentCard().getLayout();
            cardLayout.show(loginPortal.getPanelParentCard(), "Login");

        } catch (Exception ex) {
            UIUtil.showErrorMessage(this, ex.getMessage(), "Reset Password Failed");
        } finally {
            // Zero out the password arrays for security
            Arrays.fill(jPasswordFieldNewPassword.getPassword(), '\0');
            Arrays.fill(jPasswordFieldConfirmPassword.getPassword(), '\0');
        }
        
    }//GEN-LAST:event_jButtonSubmitActionPerformed

    private void jPasswordFieldSssNumberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPasswordFieldSssNumberActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jPasswordFieldSssNumberActionPerformed

    private void jPasswordFieldNewPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPasswordFieldNewPasswordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jPasswordFieldNewPasswordActionPerformed

    private void jPasswordFieldConfirmPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPasswordFieldConfirmPasswordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jPasswordFieldConfirmPasswordActionPerformed

    private void jLabelBackMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelBackMouseClicked
        // TODO add your handling code here:
        CardLayout cardLayout = (CardLayout) loginPortal.getPanelParentCard().getLayout();
        cardLayout.show(loginPortal.getPanelParentCard(), "Login");
    }//GEN-LAST:event_jLabelBackMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonSubmit;
    private com.toedter.calendar.JDateChooser jDateChooserBirthday;
    private javax.swing.JLabel jLabelBack;
    private javax.swing.JLabel jLabelBirthday;
    private javax.swing.JLabel jLabelConfirmPassword;
    private javax.swing.JLabel jLabelForgotPassword;
    private javax.swing.JLabel jLabelInstruction;
    private javax.swing.JLabel jLabelMotorLogo;
    private javax.swing.JLabel jLabelNewPassword;
    private javax.swing.JLabel jLabelSssNumber;
    private javax.swing.JLabel jLabelUsername;
    private javax.swing.JPanel jPanelForgotPassword;
    private javax.swing.JPasswordField jPasswordFieldConfirmPassword;
    private javax.swing.JPasswordField jPasswordFieldNewPassword;
    private javax.swing.JPasswordField jPasswordFieldSssNumber;
    private javax.swing.JTextField jTextFieldUsername;
    // End of variables declaration//GEN-END:variables
}