package gui.login;

import gui.employee.*;
import gui.admin.hr.*;
import gui.admin.finance.*;
import gui.admin.it.*;
import java.awt.CardLayout;

public class LoginPanel extends javax.swing.JPanel {
    
    private final LoginPortal loginPortal;

    public LoginPanel(LoginPortal loginPortal) {
        this.loginPortal = loginPortal;
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelLoginBox = new javax.swing.JPanel();
        jLabelPassword = new javax.swing.JLabel();
        jLabelWelcome = new javax.swing.JLabel();
        jPasswordFieldPassword = new javax.swing.JPasswordField();
        jTextFieldUsername = new javax.swing.JTextField();
        jButtonLogin = new javax.swing.JButton();
        jLabelForgotPassword = new javax.swing.JLabel();
        jLabelUsername = new javax.swing.JLabel();
        jLabelPleaseLoginInHere = new javax.swing.JLabel();
        jLabelPayrollManagementSystem = new javax.swing.JLabel();
        jLabelMotorPH = new javax.swing.JLabel();
        jLabelMotorLogo = new javax.swing.JLabel();
        jLabelMorPHLogo = new javax.swing.JLabel();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanelLoginBox.setBackground(new java.awt.Color(255, 255, 255));
        jPanelLoginBox.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelPassword.setBackground(new java.awt.Color(255, 255, 255));
        jLabelPassword.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        jLabelPassword.setForeground(new java.awt.Color(12, 72, 92));
        jLabelPassword.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelPassword.setText("Password");
        jPanelLoginBox.add(jLabelPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 360, 160, 20));

        jLabelWelcome.setBackground(new java.awt.Color(255, 255, 255));
        jLabelWelcome.setFont(new java.awt.Font("Segoe UI", 1, 28)); // NOI18N
        jLabelWelcome.setForeground(new java.awt.Color(12, 72, 92));
        jLabelWelcome.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelWelcome.setText("Welcome");
        jPanelLoginBox.add(jLabelWelcome, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 190, 140, 40));

        jPasswordFieldPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPasswordFieldPasswordActionPerformed(evt);
            }
        });
        jPanelLoginBox.add(jPasswordFieldPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 380, 370, 40));

        jTextFieldUsername.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldUsernameActionPerformed(evt);
            }
        });
        jPanelLoginBox.add(jTextFieldUsername, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 310, 370, 40));

        jButtonLogin.setBackground(new java.awt.Color(12, 72, 92));
        jButtonLogin.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButtonLogin.setForeground(new java.awt.Color(255, 255, 255));
        jButtonLogin.setText("LOGIN");
        jButtonLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLoginActionPerformed(evt);
            }
        });
        jPanelLoginBox.add(jButtonLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 480, 370, 50));

        jLabelForgotPassword.setBackground(new java.awt.Color(255, 255, 255));
        jLabelForgotPassword.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabelForgotPassword.setForeground(new java.awt.Color(12, 72, 92));
        jLabelForgotPassword.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelForgotPassword.setText("Forgot password?");
        jLabelForgotPassword.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelForgotPasswordMouseClicked(evt);
            }
        });
        jPanelLoginBox.add(jLabelForgotPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 420, 110, 20));

        jLabelUsername.setBackground(new java.awt.Color(255, 255, 255));
        jLabelUsername.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        jLabelUsername.setForeground(new java.awt.Color(12, 72, 92));
        jLabelUsername.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelUsername.setText("Username");
        jPanelLoginBox.add(jLabelUsername, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 290, 160, 20));

        jLabelPleaseLoginInHere.setBackground(new java.awt.Color(255, 255, 255));
        jLabelPleaseLoginInHere.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabelPleaseLoginInHere.setForeground(new java.awt.Color(12, 72, 92));
        jLabelPleaseLoginInHere.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelPleaseLoginInHere.setText("Please login here");
        jPanelLoginBox.add(jLabelPleaseLoginInHere, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 230, 160, 20));

        jLabelPayrollManagementSystem.setBackground(new java.awt.Color(255, 255, 255));
        jLabelPayrollManagementSystem.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabelPayrollManagementSystem.setForeground(new java.awt.Color(12, 72, 92));
        jLabelPayrollManagementSystem.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelPayrollManagementSystem.setText("Payroll Management System");
        jPanelLoginBox.add(jLabelPayrollManagementSystem, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 100, -1, 40));

        jLabelMotorPH.setBackground(new java.awt.Color(255, 255, 255));
        jLabelMotorPH.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabelMotorPH.setForeground(new java.awt.Color(12, 72, 92));
        jLabelMotorPH.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelMotorPH.setText("MotorPH ");
        jPanelLoginBox.add(jLabelMotorPH, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 70, -1, 40));
        jPanelLoginBox.add(jLabelMotorLogo, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 40, -1, 40));

        jLabelMorPHLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/bluegreen-motor-logo.png"))); // NOI18N
        jPanelLoginBox.add(jLabelMorPHLogo, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 40, -1, 40));

        add(jPanelLoginBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 490, 570));
    }// </editor-fold>//GEN-END:initComponents

    private void jPasswordFieldPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPasswordFieldPasswordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jPasswordFieldPasswordActionPerformed

    private void jTextFieldUsernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldUsernameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldUsernameActionPerformed

    private void jButtonLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLoginActionPerformed
        // TODO add your handling code here:
        EmployeePortal employeePortal = new EmployeePortal();
        employeePortal.setVisible(true);
        loginPortal.dispose();
        
    }//GEN-LAST:event_jButtonLoginActionPerformed

    private void jLabelForgotPasswordMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelForgotPasswordMouseClicked
        // TODO add your handling code here:
        CardLayout cardLayout = (CardLayout) loginPortal.getPanelParentCard().getLayout();
        cardLayout.show(loginPortal.getPanelParentCard(), "ForgotPassword");
        
//        JOptionPane.showMessageDialog(this,
//            "Please contact IT support to reset your password.",
//            "Forgot Password",
//            JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_jLabelForgotPasswordMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonLogin;
    private javax.swing.JLabel jLabelForgotPassword;
    private javax.swing.JLabel jLabelMorPHLogo;
    private javax.swing.JLabel jLabelMotorLogo;
    private javax.swing.JLabel jLabelMotorPH;
    private javax.swing.JLabel jLabelPassword;
    private javax.swing.JLabel jLabelPayrollManagementSystem;
    private javax.swing.JLabel jLabelPleaseLoginInHere;
    private javax.swing.JLabel jLabelUsername;
    private javax.swing.JLabel jLabelWelcome;
    private javax.swing.JPanel jPanelLoginBox;
    private javax.swing.JPasswordField jPasswordFieldPassword;
    private javax.swing.JTextField jTextFieldUsername;
    // End of variables declaration//GEN-END:variables
}