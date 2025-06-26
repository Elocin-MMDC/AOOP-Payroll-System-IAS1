package gui.admin.it;

import java.awt.CardLayout;
import model.pojo.EmployeeView;
import model.pojo.Role;
import model.pojo.UserAccount;
import service.AccountService;
import util.UIUtil;

public class ViewAccountPanel extends javax.swing.JPanel {
    
    private final AdminITPortal itPortal;
    private final AccountService accountService;

    public ViewAccountPanel(AdminITPortal itPortal) {
        this.itPortal = itPortal;
        this.accountService = new AccountService();
        initComponents();
        UIUtil.setGreeting(jLabelHelloAdmin, "Admin");
        UIUtil.applyUsernameFormat(jTextFieldUsername);
        populateDropdowns();
    }
    
    private void populateDropdowns() {
        for (Role r : accountService.getAllRoles()) {
            jComboBoxRole.addItem(r.getRoleName());
        }

        jComboBoxAccountStatus.addItem("Active");
        jComboBoxAccountStatus.addItem("Deactivated");
    }
    
    public void loadSelectedAccount(int userId) {
        UserAccount ua = accountService.getAccountById(userId);
        if (ua == null) {
            UIUtil.showErrorMessage(this, "User account not found.", "Error");
            return;
        }

        EmployeeView efd = accountService.getEmployeeDetails(ua.getEmployeeID());
        Role role = accountService.getRoleById(ua.getRoleID());

        jTextFieldUserID.setText(String.valueOf(ua.getUserID()));
        jTextFieldEmployeeID.setText(String.valueOf(ua.getEmployeeID()));
        jTextFieldFullName.setText(efd.getFirstName() + " " + efd.getLastName());
        jTextFieldPosition.setText(efd.getPositionTitle());
        jTextFieldDepartment.setText(efd.getDepartmentName());
        jTextFieldUsername.setText(ua.getUsername());
        jComboBoxRole.setSelectedItem(role.getRoleName());
        jComboBoxAccountStatus.setSelectedItem(ua.getAccountStatus());
    }
    
    private void resetPassword() {
        int userID = Integer.parseInt(jTextFieldUserID.getText());

        if (!UIUtil.showConfirmation(this, "Are you sure you want to reset the password for User ID " + userID + "?")) {
            return;
        }

        try {
            boolean success = accountService.resetPassword(userID);
            if (success) {
                itPortal.getSecurityPanel().loadAuditLogs();
                itPortal.getSecurityPanel().loadLoginLogs();
                UIUtil.showInfoMessage(this, "Password has been reset to 'temppassword'.", "Success");
            } else {
                UIUtil.showErrorMessage(this, "Failed to reset password.", "Error");
            }
        } catch (Exception ex) {
            UIUtil.showErrorMessage(this, ex.getMessage(), "System Error");
        }
    }
    
    private void updateAccount() {
        int userID = Integer.parseInt(jTextFieldUserID.getText());
        String newUsername = jTextFieldUsername.getText().trim();
        String newRoleName = jComboBoxRole.getSelectedItem().toString();
        String newStatus = jComboBoxAccountStatus.getSelectedItem().toString();
        
        if (newUsername == null || newUsername.trim().isEmpty() || newRoleName == null 
                || "Select".equals(newRoleName) || newStatus == null || "Select".equals(newStatus)) {
            UIUtil.showErrorMessage(this, "All fields must be filled out.", "Error");
            return;
        }

        try {
            boolean success = accountService.updateAccount(userID, newUsername, newRoleName, newStatus);
            if (success) {
                // Refresh pages
                loadSelectedAccount(userID);
                itPortal.getAccountsPanel().loadAccountRecords();
                itPortal.getSecurityPanel().loadAuditLogs();
                UIUtil.showInfoMessage(this, "User account updated successfully.", "Success");
            } else {
                UIUtil.showInfoMessage(this, "No changes detected. Account not updated.", "Info");
            }
        } catch (Exception ex) {
            UIUtil.showErrorMessage(this, ex.getMessage(), "System Error");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabelHelloAdmin = new javax.swing.JLabel();
        jLabelViewRecordSmall = new javax.swing.JLabel();
        jPanelViewAccountBox = new javax.swing.JPanel();
        jTextFieldFullName = new javax.swing.JTextField();
        jLabelName = new javax.swing.JLabel();
        jLabelBack = new javax.swing.JLabel();
        jLabelUserID = new javax.swing.JLabel();
        jTextFieldUserID = new javax.swing.JTextField();
        jLabelEmployeeID = new javax.swing.JLabel();
        jTextFieldEmployeeID = new javax.swing.JTextField();
        jLabelRole = new javax.swing.JLabel();
        jLabelUsername = new javax.swing.JLabel();
        jTextFieldUsername = new javax.swing.JTextField();
        jLabelPosition = new javax.swing.JLabel();
        jTextFieldPosition = new javax.swing.JTextField();
        jLabelDepartment = new javax.swing.JLabel();
        jTextFieldDepartment = new javax.swing.JTextField();
        jButtonUpdate = new javax.swing.JButton();
        jButtonResetPassword = new javax.swing.JButton();
        jComboBoxRole = new javax.swing.JComboBox<>();
        jLabelAccountStatus = new javax.swing.JLabel();
        jComboBoxAccountStatus = new javax.swing.JComboBox<>();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(1120, 750));
        jPanel1.setLayout(null);

        jLabelHelloAdmin.setBackground(new java.awt.Color(255, 255, 255));
        jLabelHelloAdmin.setFont(new java.awt.Font("Segoe UI", 1, 21)); // NOI18N
        jLabelHelloAdmin.setForeground(new java.awt.Color(0, 0, 0));
        jLabelHelloAdmin.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelHelloAdmin.setText("Hello, Admin!");
        jPanel1.add(jLabelHelloAdmin);
        jLabelHelloAdmin.setBounds(30, 30, 640, 29);

        jLabelViewRecordSmall.setText("Accounts > View Record");
        jPanel1.add(jLabelViewRecordSmall);
        jLabelViewRecordSmall.setBounds(30, 60, 180, 16);

        jPanelViewAccountBox.setBackground(new java.awt.Color(255, 255, 255));
        jPanelViewAccountBox.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanelViewAccountBox.setLayout(null);

        jTextFieldFullName.setBackground(new java.awt.Color(240, 240, 240));
        jTextFieldFullName.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jTextFieldFullName.setEnabled(false);
        jTextFieldFullName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldFullNameActionPerformed(evt);
            }
        });
        jPanelViewAccountBox.add(jTextFieldFullName);
        jTextFieldFullName.setBounds(170, 290, 350, 40);

        jLabelName.setBackground(new java.awt.Color(255, 255, 255));
        jLabelName.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelName.setForeground(new java.awt.Color(0, 0, 0));
        jLabelName.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelName.setText("Name :");
        jPanelViewAccountBox.add(jLabelName);
        jLabelName.setBounds(20, 290, 290, 40);

        jLabelBack.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/back-icon.png"))); // NOI18N
        jLabelBack.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelBackMouseClicked(evt);
            }
        });
        jPanelViewAccountBox.add(jLabelBack);
        jLabelBack.setBounds(0, 560, 60, 60);

        jLabelUserID.setBackground(new java.awt.Color(255, 255, 255));
        jLabelUserID.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelUserID.setForeground(new java.awt.Color(0, 0, 0));
        jLabelUserID.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelUserID.setText("User ID :");
        jPanelViewAccountBox.add(jLabelUserID);
        jLabelUserID.setBounds(20, 210, 290, 40);

        jTextFieldUserID.setBackground(new java.awt.Color(240, 240, 240));
        jTextFieldUserID.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jTextFieldUserID.setEnabled(false);
        jTextFieldUserID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldUserIDActionPerformed(evt);
            }
        });
        jPanelViewAccountBox.add(jTextFieldUserID);
        jTextFieldUserID.setBounds(170, 210, 350, 40);

        jLabelEmployeeID.setBackground(new java.awt.Color(255, 255, 255));
        jLabelEmployeeID.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelEmployeeID.setForeground(new java.awt.Color(0, 0, 0));
        jLabelEmployeeID.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelEmployeeID.setText("Employee ID :");
        jPanelViewAccountBox.add(jLabelEmployeeID);
        jLabelEmployeeID.setBounds(20, 250, 290, 40);

        jTextFieldEmployeeID.setBackground(new java.awt.Color(240, 240, 240));
        jTextFieldEmployeeID.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jTextFieldEmployeeID.setEnabled(false);
        jTextFieldEmployeeID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldEmployeeIDActionPerformed(evt);
            }
        });
        jPanelViewAccountBox.add(jTextFieldEmployeeID);
        jTextFieldEmployeeID.setBounds(170, 250, 350, 40);

        jLabelRole.setBackground(new java.awt.Color(255, 255, 255));
        jLabelRole.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelRole.setForeground(new java.awt.Color(0, 0, 0));
        jLabelRole.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelRole.setText("Role :");
        jPanelViewAccountBox.add(jLabelRole);
        jLabelRole.setBounds(540, 290, 290, 40);

        jLabelUsername.setBackground(new java.awt.Color(255, 255, 255));
        jLabelUsername.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelUsername.setForeground(new java.awt.Color(0, 0, 0));
        jLabelUsername.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelUsername.setText("Username :");
        jPanelViewAccountBox.add(jLabelUsername);
        jLabelUsername.setBounds(540, 250, 290, 40);

        jTextFieldUsername.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldUsernameActionPerformed(evt);
            }
        });
        jPanelViewAccountBox.add(jTextFieldUsername);
        jTextFieldUsername.setBounds(690, 250, 350, 40);

        jLabelPosition.setBackground(new java.awt.Color(255, 255, 255));
        jLabelPosition.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelPosition.setForeground(new java.awt.Color(0, 0, 0));
        jLabelPosition.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelPosition.setText("Position :");
        jPanelViewAccountBox.add(jLabelPosition);
        jLabelPosition.setBounds(20, 330, 290, 40);

        jTextFieldPosition.setBackground(new java.awt.Color(240, 240, 240));
        jTextFieldPosition.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jTextFieldPosition.setEnabled(false);
        jTextFieldPosition.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldPositionActionPerformed(evt);
            }
        });
        jPanelViewAccountBox.add(jTextFieldPosition);
        jTextFieldPosition.setBounds(170, 330, 350, 40);

        jLabelDepartment.setBackground(new java.awt.Color(255, 255, 255));
        jLabelDepartment.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelDepartment.setForeground(new java.awt.Color(0, 0, 0));
        jLabelDepartment.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelDepartment.setText("Department :");
        jPanelViewAccountBox.add(jLabelDepartment);
        jLabelDepartment.setBounds(540, 210, 290, 40);

        jTextFieldDepartment.setBackground(new java.awt.Color(240, 240, 240));
        jTextFieldDepartment.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jTextFieldDepartment.setEnabled(false);
        jTextFieldDepartment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldDepartmentActionPerformed(evt);
            }
        });
        jPanelViewAccountBox.add(jTextFieldDepartment);
        jTextFieldDepartment.setBounds(690, 210, 350, 40);

        jButtonUpdate.setBackground(new java.awt.Color(0, 135, 0));
        jButtonUpdate.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButtonUpdate.setForeground(new java.awt.Color(255, 255, 255));
        jButtonUpdate.setText("Update");
        jButtonUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonUpdateActionPerformed(evt);
            }
        });
        jPanelViewAccountBox.add(jButtonUpdate);
        jButtonUpdate.setBounds(870, 570, 170, 40);

        jButtonResetPassword.setBackground(new java.awt.Color(0, 43, 89));
        jButtonResetPassword.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButtonResetPassword.setForeground(new java.awt.Color(255, 255, 255));
        jButtonResetPassword.setText("Reset Password");
        jButtonResetPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonResetPasswordActionPerformed(evt);
            }
        });
        jPanelViewAccountBox.add(jButtonResetPassword);
        jButtonResetPassword.setBounds(870, 30, 170, 40);

        jComboBoxRole.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jComboBoxRole.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select" }));
        jComboBoxRole.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxRoleActionPerformed(evt);
            }
        });
        jPanelViewAccountBox.add(jComboBoxRole);
        jComboBoxRole.setBounds(690, 290, 350, 40);

        jLabelAccountStatus.setBackground(new java.awt.Color(255, 255, 255));
        jLabelAccountStatus.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelAccountStatus.setForeground(new java.awt.Color(0, 0, 0));
        jLabelAccountStatus.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelAccountStatus.setText("Account Status :");
        jPanelViewAccountBox.add(jLabelAccountStatus);
        jLabelAccountStatus.setBounds(540, 330, 290, 40);

        jComboBoxAccountStatus.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jComboBoxAccountStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select" }));
        jComboBoxAccountStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxAccountStatusActionPerformed(evt);
            }
        });
        jPanelViewAccountBox.add(jComboBoxAccountStatus);
        jComboBoxAccountStatus.setBounds(690, 330, 350, 40);

        jPanel1.add(jPanelViewAccountBox);
        jPanelViewAccountBox.setBounds(30, 110, 1060, 640);

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void jLabelBackMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelBackMouseClicked
        // TODO add your handling code here:
        CardLayout cardLayout = (CardLayout) itPortal.getPanelParentCard().getLayout();
        cardLayout.show(itPortal.getPanelParentCard(), "Accounts");
    }//GEN-LAST:event_jLabelBackMouseClicked

    private void jTextFieldFullNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldFullNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldFullNameActionPerformed

    private void jTextFieldUserIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldUserIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldUserIDActionPerformed

    private void jTextFieldEmployeeIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldEmployeeIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldEmployeeIDActionPerformed

    private void jTextFieldUsernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldUsernameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldUsernameActionPerformed

    private void jTextFieldPositionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldPositionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldPositionActionPerformed

    private void jTextFieldDepartmentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldDepartmentActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldDepartmentActionPerformed

    private void jButtonUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonUpdateActionPerformed
        // TODO add your handling code here:
        updateAccount();
    }//GEN-LAST:event_jButtonUpdateActionPerformed

    private void jButtonResetPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonResetPasswordActionPerformed
        // TODO add your handling code here:
        resetPassword();
    }//GEN-LAST:event_jButtonResetPasswordActionPerformed

    private void jComboBoxRoleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxRoleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxRoleActionPerformed

    private void jComboBoxAccountStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxAccountStatusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxAccountStatusActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonResetPassword;
    private javax.swing.JButton jButtonUpdate;
    private javax.swing.JComboBox<String> jComboBoxAccountStatus;
    private javax.swing.JComboBox<String> jComboBoxRole;
    private javax.swing.JLabel jLabelAccountStatus;
    private javax.swing.JLabel jLabelBack;
    private javax.swing.JLabel jLabelDepartment;
    private javax.swing.JLabel jLabelEmployeeID;
    private javax.swing.JLabel jLabelHelloAdmin;
    private javax.swing.JLabel jLabelName;
    private javax.swing.JLabel jLabelPosition;
    private javax.swing.JLabel jLabelRole;
    private javax.swing.JLabel jLabelUserID;
    private javax.swing.JLabel jLabelUsername;
    private javax.swing.JLabel jLabelViewRecordSmall;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanelViewAccountBox;
    private javax.swing.JTextField jTextFieldDepartment;
    private javax.swing.JTextField jTextFieldEmployeeID;
    private javax.swing.JTextField jTextFieldFullName;
    private javax.swing.JTextField jTextFieldPosition;
    private javax.swing.JTextField jTextFieldUserID;
    private javax.swing.JTextField jTextFieldUsername;
    // End of variables declaration//GEN-END:variables
}