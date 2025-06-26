package gui.employee;

import java.awt.CardLayout;
import java.util.Arrays;
import service.AuthenticationService;
import util.Session;
import util.UIUtil;

public class ChangePasswordPanel extends javax.swing.JPanel {
    
    private final EmployeePortal employeePortal;

    public ChangePasswordPanel(EmployeePortal employeePortal) {
        this.employeePortal = employeePortal;
        initComponents();
        UIUtil.setGreeting(jLabelHelloEmployee, "Employee");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabelHelloEmployee = new javax.swing.JLabel();
        jLabelProfileSmall = new javax.swing.JLabel();
        jPanelChangePasswordBox = new javax.swing.JPanel();
        jLabelCurrentPassword = new javax.swing.JLabel();
        jLabelNewPassword = new javax.swing.JLabel();
        jLabelConfirmNewPassword = new javax.swing.JLabel();
        jButtonSubmit = new javax.swing.JButton();
        jLabelBack = new javax.swing.JLabel();
        jPasswordFieldCurrentPassword = new javax.swing.JPasswordField();
        jPasswordFieldCofirmNewPassword = new javax.swing.JPasswordField();
        jPasswordFieldNewPassword = new javax.swing.JPasswordField();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(1120, 750));
        jPanel1.setLayout(null);

        jLabelHelloEmployee.setBackground(new java.awt.Color(255, 255, 255));
        jLabelHelloEmployee.setFont(new java.awt.Font("Segoe UI", 1, 21)); // NOI18N
        jLabelHelloEmployee.setForeground(new java.awt.Color(0, 0, 0));
        jLabelHelloEmployee.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelHelloEmployee.setText("Hello, Employee!");
        jPanel1.add(jLabelHelloEmployee);
        jLabelHelloEmployee.setBounds(30, 30, 640, 29);

        jLabelProfileSmall.setText("Profile > Change Password");
        jPanel1.add(jLabelProfileSmall);
        jLabelProfileSmall.setBounds(30, 60, 180, 16);

        jPanelChangePasswordBox.setBackground(new java.awt.Color(255, 255, 255));
        jPanelChangePasswordBox.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanelChangePasswordBox.setLayout(null);

        jLabelCurrentPassword.setBackground(new java.awt.Color(255, 255, 255));
        jLabelCurrentPassword.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelCurrentPassword.setForeground(new java.awt.Color(0, 0, 0));
        jLabelCurrentPassword.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelCurrentPassword.setText("Current Password :");
        jPanelChangePasswordBox.add(jLabelCurrentPassword);
        jLabelCurrentPassword.setBounds(260, 160, 290, 40);

        jLabelNewPassword.setBackground(new java.awt.Color(255, 255, 255));
        jLabelNewPassword.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelNewPassword.setForeground(new java.awt.Color(0, 0, 0));
        jLabelNewPassword.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelNewPassword.setText("New Password :");
        jPanelChangePasswordBox.add(jLabelNewPassword);
        jLabelNewPassword.setBounds(260, 200, 290, 40);

        jLabelConfirmNewPassword.setBackground(new java.awt.Color(255, 255, 255));
        jLabelConfirmNewPassword.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelConfirmNewPassword.setForeground(new java.awt.Color(0, 0, 0));
        jLabelConfirmNewPassword.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelConfirmNewPassword.setText("Confirm New Password :");
        jPanelChangePasswordBox.add(jLabelConfirmNewPassword);
        jLabelConfirmNewPassword.setBounds(260, 240, 290, 40);

        jButtonSubmit.setBackground(new java.awt.Color(0, 135, 0));
        jButtonSubmit.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButtonSubmit.setForeground(new java.awt.Color(255, 255, 255));
        jButtonSubmit.setText("Submit");
        jButtonSubmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSubmitActionPerformed(evt);
            }
        });
        jPanelChangePasswordBox.add(jButtonSubmit);
        jButtonSubmit.setBounds(260, 430, 520, 40);

        jLabelBack.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/back-icon.png"))); // NOI18N
        jLabelBack.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelBackMouseClicked(evt);
            }
        });
        jPanelChangePasswordBox.add(jLabelBack);
        jLabelBack.setBounds(0, 560, 60, 60);

        jPasswordFieldCurrentPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPasswordFieldCurrentPasswordActionPerformed(evt);
            }
        });
        jPanelChangePasswordBox.add(jPasswordFieldCurrentPassword);
        jPasswordFieldCurrentPassword.setBounds(440, 160, 340, 40);

        jPasswordFieldCofirmNewPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPasswordFieldCofirmNewPasswordActionPerformed(evt);
            }
        });
        jPanelChangePasswordBox.add(jPasswordFieldCofirmNewPassword);
        jPasswordFieldCofirmNewPassword.setBounds(440, 240, 340, 40);

        jPasswordFieldNewPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPasswordFieldNewPasswordActionPerformed(evt);
            }
        });
        jPanelChangePasswordBox.add(jPasswordFieldNewPassword);
        jPasswordFieldNewPassword.setBounds(440, 200, 340, 40);

        jPanel1.add(jPanelChangePasswordBox);
        jPanelChangePasswordBox.setBounds(30, 110, 1060, 640);

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonSubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSubmitActionPerformed
        // TODO add your handling code here:
        String current = new String(jPasswordFieldCurrentPassword.getPassword());
        String next = new String(jPasswordFieldNewPassword.getPassword()).trim();
        String confirm = new String(jPasswordFieldCofirmNewPassword.getPassword()).trim();

        if (current.isEmpty() || next.isEmpty() || confirm.isEmpty()) {
            UIUtil.showWarningMessage(this, "All password fields are required.", "Change Password");
            return;
        }

        if (!next.equals(confirm)) {
            UIUtil.showErrorMessage(this, "New password and confirmation do not match.", "Change Password");
            return;
        }

        // Attempt to change
        AuthenticationService authService = new AuthenticationService();
        try {
            int userId = Session.getCurrentUser().getUserID();
            authService.changePassword(userId, current, next);
            UIUtil.showInfoMessage(this, "Password changed successfully!", "Change Password");

            // Clear fields
            Arrays.fill(jPasswordFieldCurrentPassword.getPassword(), '\0');
            Arrays.fill(jPasswordFieldNewPassword.getPassword(), '\0');
            Arrays.fill(jPasswordFieldCofirmNewPassword.getPassword(), '\0');
            
            jPasswordFieldCurrentPassword.setText("");
            jPasswordFieldNewPassword.setText("");
            jPasswordFieldCofirmNewPassword.setText("");
            
        } catch (Exception ex) {
            UIUtil.showErrorMessage(this, ex.getMessage(), "Change Password Failed");
        }
    }//GEN-LAST:event_jButtonSubmitActionPerformed

    private void jLabelBackMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelBackMouseClicked
        // TODO add your handling code here:
        CardLayout cardLayout = (CardLayout) employeePortal.getPanelParentCard().getLayout();
        cardLayout.show(employeePortal.getPanelParentCard(), "Profile");
    }//GEN-LAST:event_jLabelBackMouseClicked

    private void jPasswordFieldCurrentPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPasswordFieldCurrentPasswordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jPasswordFieldCurrentPasswordActionPerformed

    private void jPasswordFieldCofirmNewPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPasswordFieldCofirmNewPasswordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jPasswordFieldCofirmNewPasswordActionPerformed

    private void jPasswordFieldNewPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPasswordFieldNewPasswordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jPasswordFieldNewPasswordActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonSubmit;
    private javax.swing.JLabel jLabelBack;
    private javax.swing.JLabel jLabelConfirmNewPassword;
    private javax.swing.JLabel jLabelCurrentPassword;
    private javax.swing.JLabel jLabelHelloEmployee;
    private javax.swing.JLabel jLabelNewPassword;
    private javax.swing.JLabel jLabelProfileSmall;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanelChangePasswordBox;
    private javax.swing.JPasswordField jPasswordFieldCofirmNewPassword;
    private javax.swing.JPasswordField jPasswordFieldCurrentPassword;
    private javax.swing.JPasswordField jPasswordFieldNewPassword;
    // End of variables declaration//GEN-END:variables
}