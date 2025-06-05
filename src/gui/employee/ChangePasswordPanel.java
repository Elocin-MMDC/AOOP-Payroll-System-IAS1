package gui.employee;

import java.awt.CardLayout;

public class ChangePasswordPanel extends javax.swing.JPanel {
    
    private final EmployeePortal employeePortal;

    public ChangePasswordPanel(EmployeePortal employeePortal) {
        this.employeePortal = employeePortal;
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabelHelloEmployee = new javax.swing.JLabel();
        jLabelProfileSmall = new javax.swing.JLabel();
        jPanelChangePasswordBox = new javax.swing.JPanel();
        jTextFieldNewPassword = new javax.swing.JTextField();
        jTextFieldCurrentPassword = new javax.swing.JTextField();
        jLabelCurrentPassword = new javax.swing.JLabel();
        jLabelNewPassword = new javax.swing.JLabel();
        jLabelConfirmNewPassword = new javax.swing.JLabel();
        jTextFieldCofirmNewPassword = new javax.swing.JTextField();
        jButtonSubmit = new javax.swing.JButton();
        jLabelBack = new javax.swing.JLabel();

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
        jLabelHelloEmployee.setBounds(30, 30, 210, 29);

        jLabelProfileSmall.setText("Profile > Change Password");
        jPanel1.add(jLabelProfileSmall);
        jLabelProfileSmall.setBounds(30, 60, 180, 16);

        jPanelChangePasswordBox.setBackground(new java.awt.Color(255, 255, 255));
        jPanelChangePasswordBox.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanelChangePasswordBox.setLayout(null);

        jTextFieldNewPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldNewPasswordActionPerformed(evt);
            }
        });
        jPanelChangePasswordBox.add(jTextFieldNewPassword);
        jTextFieldNewPassword.setBounds(440, 200, 340, 40);

        jTextFieldCurrentPassword.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        jTextFieldCurrentPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldCurrentPasswordActionPerformed(evt);
            }
        });
        jPanelChangePasswordBox.add(jTextFieldCurrentPassword);
        jTextFieldCurrentPassword.setBounds(440, 160, 340, 40);

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

        jTextFieldCofirmNewPassword.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        jTextFieldCofirmNewPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldCofirmNewPasswordActionPerformed(evt);
            }
        });
        jPanelChangePasswordBox.add(jTextFieldCofirmNewPassword);
        jTextFieldCofirmNewPassword.setBounds(440, 240, 340, 40);

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

        jPanel1.add(jPanelChangePasswordBox);
        jPanelChangePasswordBox.setBounds(30, 110, 1060, 640);

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldNewPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldNewPasswordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldNewPasswordActionPerformed

    private void jTextFieldCurrentPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldCurrentPasswordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldCurrentPasswordActionPerformed

    private void jTextFieldCofirmNewPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldCofirmNewPasswordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldCofirmNewPasswordActionPerformed

    private void jButtonSubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSubmitActionPerformed
        // TODO add your handling code here:
//        CardLayout cardLayout = (CardLayout) hrPortal.getPanelParentCard().getLayout();
//        cardLayout.show(hrPortal.getPanelParentCard(), "CreateEmployee");
    }//GEN-LAST:event_jButtonSubmitActionPerformed

    private void jLabelBackMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelBackMouseClicked
        // TODO add your handling code here:
        CardLayout cardLayout = (CardLayout) employeePortal.getPanelParentCard().getLayout();
        cardLayout.show(employeePortal.getPanelParentCard(), "Profile");
    }//GEN-LAST:event_jLabelBackMouseClicked

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
    private javax.swing.JTextField jTextFieldCofirmNewPassword;
    private javax.swing.JTextField jTextFieldCurrentPassword;
    private javax.swing.JTextField jTextFieldNewPassword;
    // End of variables declaration//GEN-END:variables
}