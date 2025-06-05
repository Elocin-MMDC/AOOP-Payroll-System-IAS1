package gui.employee;

import java.awt.CardLayout;

public class ViewLeavePanel extends javax.swing.JPanel {
    
    private final EmployeePortal employeePortal;

    public ViewLeavePanel(EmployeePortal employeePortal) {
        this.employeePortal = employeePortal;
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabelHelloEmployee = new javax.swing.JLabel();
        jLabelLeaveSmall = new javax.swing.JLabel();
        jPanelLeaveRequestBox = new javax.swing.JPanel();
        jLabelLeaveRequestID = new javax.swing.JLabel();
        jLabelRequestDate = new javax.swing.JLabel();
        jLabelEmployeeNumber = new javax.swing.JLabel();
        jLabelName = new javax.swing.JLabel();
        jLabelPosition = new javax.swing.JLabel();
        jLabelDepartment = new javax.swing.JLabel();
        jLabelSupervisor = new javax.swing.JLabel();
        jTextFieldSupervisor = new javax.swing.JTextField();
        jTextFieldDepartment = new javax.swing.JTextField();
        jTextFieldPosition = new javax.swing.JTextField();
        jTextFieldFullName = new javax.swing.JTextField();
        jTextFieldEmployeeNumber = new javax.swing.JTextField();
        jTextFieldRequestDate = new javax.swing.JTextField();
        jTextFieldLeaveRequestID = new javax.swing.JTextField();
        jLabelStartDate = new javax.swing.JLabel();
        jLabelLeaveDays = new javax.swing.JLabel();
        jLabelEndDate = new javax.swing.JLabel();
        jLabelLeaveType = new javax.swing.JLabel();
        jLabelReason = new javax.swing.JLabel();
        jLabelApprovedBy = new javax.swing.JLabel();
        jLabelStatus = new javax.swing.JLabel();
        jTextFieldStatus = new javax.swing.JTextField();
        jTextFieldApprovedBy = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaReason = new javax.swing.JTextArea();
        jTextFieldLeaveType = new javax.swing.JTextField();
        jTextFieldLeaveDays = new javax.swing.JTextField();
        jTextFieldEndDate = new javax.swing.JTextField();
        jTextFieldStartDate = new javax.swing.JTextField();
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

        jLabelLeaveSmall.setText("Leave > View Record");
        jPanel1.add(jLabelLeaveSmall);
        jLabelLeaveSmall.setBounds(30, 60, 180, 16);

        jPanelLeaveRequestBox.setBackground(new java.awt.Color(255, 255, 255));
        jPanelLeaveRequestBox.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanelLeaveRequestBox.setLayout(null);

        jLabelLeaveRequestID.setBackground(new java.awt.Color(255, 255, 255));
        jLabelLeaveRequestID.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelLeaveRequestID.setForeground(new java.awt.Color(0, 0, 0));
        jLabelLeaveRequestID.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelLeaveRequestID.setText("Leave Request ID :");
        jPanelLeaveRequestBox.add(jLabelLeaveRequestID);
        jLabelLeaveRequestID.setBounds(30, 150, 290, 40);

        jLabelRequestDate.setBackground(new java.awt.Color(255, 255, 255));
        jLabelRequestDate.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelRequestDate.setForeground(new java.awt.Color(0, 0, 0));
        jLabelRequestDate.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelRequestDate.setText("Date :");
        jPanelLeaveRequestBox.add(jLabelRequestDate);
        jLabelRequestDate.setBounds(30, 190, 290, 40);

        jLabelEmployeeNumber.setBackground(new java.awt.Color(255, 255, 255));
        jLabelEmployeeNumber.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelEmployeeNumber.setForeground(new java.awt.Color(0, 0, 0));
        jLabelEmployeeNumber.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelEmployeeNumber.setText("Employee # :");
        jPanelLeaveRequestBox.add(jLabelEmployeeNumber);
        jLabelEmployeeNumber.setBounds(30, 230, 290, 40);

        jLabelName.setBackground(new java.awt.Color(255, 255, 255));
        jLabelName.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelName.setForeground(new java.awt.Color(0, 0, 0));
        jLabelName.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelName.setText("Name :");
        jPanelLeaveRequestBox.add(jLabelName);
        jLabelName.setBounds(30, 270, 290, 40);

        jLabelPosition.setBackground(new java.awt.Color(255, 255, 255));
        jLabelPosition.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelPosition.setForeground(new java.awt.Color(0, 0, 0));
        jLabelPosition.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelPosition.setText("Position :");
        jPanelLeaveRequestBox.add(jLabelPosition);
        jLabelPosition.setBounds(30, 310, 290, 40);

        jLabelDepartment.setBackground(new java.awt.Color(255, 255, 255));
        jLabelDepartment.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelDepartment.setForeground(new java.awt.Color(0, 0, 0));
        jLabelDepartment.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelDepartment.setText("Department :");
        jPanelLeaveRequestBox.add(jLabelDepartment);
        jLabelDepartment.setBounds(30, 350, 290, 40);

        jLabelSupervisor.setBackground(new java.awt.Color(255, 255, 255));
        jLabelSupervisor.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelSupervisor.setForeground(new java.awt.Color(0, 0, 0));
        jLabelSupervisor.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelSupervisor.setText("Supervisor :");
        jPanelLeaveRequestBox.add(jLabelSupervisor);
        jLabelSupervisor.setBounds(30, 390, 290, 40);

        jTextFieldSupervisor.setEnabled(false);
        jTextFieldSupervisor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldSupervisorActionPerformed(evt);
            }
        });
        jPanelLeaveRequestBox.add(jTextFieldSupervisor);
        jTextFieldSupervisor.setBounds(180, 390, 350, 40);

        jTextFieldDepartment.setEnabled(false);
        jTextFieldDepartment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldDepartmentActionPerformed(evt);
            }
        });
        jPanelLeaveRequestBox.add(jTextFieldDepartment);
        jTextFieldDepartment.setBounds(180, 350, 350, 40);

        jTextFieldPosition.setEnabled(false);
        jTextFieldPosition.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldPositionActionPerformed(evt);
            }
        });
        jPanelLeaveRequestBox.add(jTextFieldPosition);
        jTextFieldPosition.setBounds(180, 310, 350, 40);

        jTextFieldFullName.setEnabled(false);
        jTextFieldFullName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldFullNameActionPerformed(evt);
            }
        });
        jPanelLeaveRequestBox.add(jTextFieldFullName);
        jTextFieldFullName.setBounds(180, 270, 350, 40);

        jTextFieldEmployeeNumber.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        jTextFieldEmployeeNumber.setEnabled(false);
        jTextFieldEmployeeNumber.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldEmployeeNumberActionPerformed(evt);
            }
        });
        jPanelLeaveRequestBox.add(jTextFieldEmployeeNumber);
        jTextFieldEmployeeNumber.setBounds(180, 230, 350, 40);

        jTextFieldRequestDate.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        jTextFieldRequestDate.setEnabled(false);
        jTextFieldRequestDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldRequestDateActionPerformed(evt);
            }
        });
        jPanelLeaveRequestBox.add(jTextFieldRequestDate);
        jTextFieldRequestDate.setBounds(180, 190, 350, 40);

        jTextFieldLeaveRequestID.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        jTextFieldLeaveRequestID.setEnabled(false);
        jTextFieldLeaveRequestID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldLeaveRequestIDActionPerformed(evt);
            }
        });
        jPanelLeaveRequestBox.add(jTextFieldLeaveRequestID);
        jTextFieldLeaveRequestID.setBounds(180, 150, 350, 40);

        jLabelStartDate.setBackground(new java.awt.Color(255, 255, 255));
        jLabelStartDate.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelStartDate.setForeground(new java.awt.Color(0, 0, 0));
        jLabelStartDate.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelStartDate.setText("Start Date :");
        jPanelLeaveRequestBox.add(jLabelStartDate);
        jLabelStartDate.setBounds(540, 150, 290, 40);

        jLabelLeaveDays.setBackground(new java.awt.Color(255, 255, 255));
        jLabelLeaveDays.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelLeaveDays.setForeground(new java.awt.Color(0, 0, 0));
        jLabelLeaveDays.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelLeaveDays.setText("Leave Days :");
        jPanelLeaveRequestBox.add(jLabelLeaveDays);
        jLabelLeaveDays.setBounds(540, 230, 290, 40);

        jLabelEndDate.setBackground(new java.awt.Color(255, 255, 255));
        jLabelEndDate.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelEndDate.setForeground(new java.awt.Color(0, 0, 0));
        jLabelEndDate.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelEndDate.setText("End Date :");
        jPanelLeaveRequestBox.add(jLabelEndDate);
        jLabelEndDate.setBounds(540, 190, 290, 40);

        jLabelLeaveType.setBackground(new java.awt.Color(255, 255, 255));
        jLabelLeaveType.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelLeaveType.setForeground(new java.awt.Color(0, 0, 0));
        jLabelLeaveType.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelLeaveType.setText("Leave Type :");
        jPanelLeaveRequestBox.add(jLabelLeaveType);
        jLabelLeaveType.setBounds(540, 270, 290, 40);

        jLabelReason.setBackground(new java.awt.Color(255, 255, 255));
        jLabelReason.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelReason.setForeground(new java.awt.Color(0, 0, 0));
        jLabelReason.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelReason.setText("Reason :");
        jPanelLeaveRequestBox.add(jLabelReason);
        jLabelReason.setBounds(540, 310, 290, 40);

        jLabelApprovedBy.setBackground(new java.awt.Color(255, 255, 255));
        jLabelApprovedBy.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelApprovedBy.setForeground(new java.awt.Color(0, 0, 0));
        jLabelApprovedBy.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelApprovedBy.setText("Approved By :");
        jPanelLeaveRequestBox.add(jLabelApprovedBy);
        jLabelApprovedBy.setBounds(540, 350, 290, 40);

        jLabelStatus.setBackground(new java.awt.Color(255, 255, 255));
        jLabelStatus.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelStatus.setForeground(new java.awt.Color(0, 0, 0));
        jLabelStatus.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelStatus.setText("Status :");
        jPanelLeaveRequestBox.add(jLabelStatus);
        jLabelStatus.setBounds(540, 390, 290, 40);

        jTextFieldStatus.setEnabled(false);
        jTextFieldStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldStatusActionPerformed(evt);
            }
        });
        jPanelLeaveRequestBox.add(jTextFieldStatus);
        jTextFieldStatus.setBounds(690, 390, 350, 40);

        jTextFieldApprovedBy.setEnabled(false);
        jTextFieldApprovedBy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldApprovedByActionPerformed(evt);
            }
        });
        jPanelLeaveRequestBox.add(jTextFieldApprovedBy);
        jTextFieldApprovedBy.setBounds(690, 350, 350, 40);

        jTextAreaReason.setColumns(20);
        jTextAreaReason.setLineWrap(true);
        jTextAreaReason.setRows(5);
        jTextAreaReason.setEnabled(false);
        jScrollPane1.setViewportView(jTextAreaReason);

        jPanelLeaveRequestBox.add(jScrollPane1);
        jScrollPane1.setBounds(690, 310, 350, 40);

        jTextFieldLeaveType.setEnabled(false);
        jTextFieldLeaveType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldLeaveTypeActionPerformed(evt);
            }
        });
        jPanelLeaveRequestBox.add(jTextFieldLeaveType);
        jTextFieldLeaveType.setBounds(690, 270, 350, 40);

        jTextFieldLeaveDays.setEnabled(false);
        jTextFieldLeaveDays.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldLeaveDaysActionPerformed(evt);
            }
        });
        jPanelLeaveRequestBox.add(jTextFieldLeaveDays);
        jTextFieldLeaveDays.setBounds(690, 230, 350, 40);

        jTextFieldEndDate.setEnabled(false);
        jTextFieldEndDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldEndDateActionPerformed(evt);
            }
        });
        jPanelLeaveRequestBox.add(jTextFieldEndDate);
        jTextFieldEndDate.setBounds(690, 190, 350, 40);

        jTextFieldStartDate.setEnabled(false);
        jTextFieldStartDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldStartDateActionPerformed(evt);
            }
        });
        jPanelLeaveRequestBox.add(jTextFieldStartDate);
        jTextFieldStartDate.setBounds(690, 150, 350, 40);

        jLabelBack.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/back-icon.png"))); // NOI18N
        jLabelBack.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelBackMouseClicked(evt);
            }
        });
        jPanelLeaveRequestBox.add(jLabelBack);
        jLabelBack.setBounds(0, 560, 60, 60);

        jPanel1.add(jPanelLeaveRequestBox);
        jPanelLeaveRequestBox.setBounds(30, 110, 1060, 640);

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldSupervisorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldSupervisorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldSupervisorActionPerformed

    private void jTextFieldDepartmentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldDepartmentActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldDepartmentActionPerformed

    private void jTextFieldPositionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldPositionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldPositionActionPerformed

    private void jTextFieldFullNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldFullNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldFullNameActionPerformed

    private void jTextFieldEmployeeNumberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldEmployeeNumberActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldEmployeeNumberActionPerformed

    private void jTextFieldRequestDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldRequestDateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldRequestDateActionPerformed

    private void jTextFieldLeaveRequestIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldLeaveRequestIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldLeaveRequestIDActionPerformed

    private void jTextFieldStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldStatusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldStatusActionPerformed

    private void jTextFieldApprovedByActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldApprovedByActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldApprovedByActionPerformed

    private void jTextFieldLeaveTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldLeaveTypeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldLeaveTypeActionPerformed

    private void jTextFieldLeaveDaysActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldLeaveDaysActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldLeaveDaysActionPerformed

    private void jTextFieldEndDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldEndDateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldEndDateActionPerformed

    private void jTextFieldStartDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldStartDateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldStartDateActionPerformed

    private void jLabelBackMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelBackMouseClicked
        // TODO add your handling code here:
        CardLayout cardLayout = (CardLayout) employeePortal.getPanelParentCard().getLayout();
        cardLayout.show(employeePortal.getPanelParentCard(), "Leave");
    }//GEN-LAST:event_jLabelBackMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabelApprovedBy;
    private javax.swing.JLabel jLabelBack;
    private javax.swing.JLabel jLabelDepartment;
    private javax.swing.JLabel jLabelEmployeeNumber;
    private javax.swing.JLabel jLabelEndDate;
    private javax.swing.JLabel jLabelHelloEmployee;
    private javax.swing.JLabel jLabelLeaveDays;
    private javax.swing.JLabel jLabelLeaveRequestID;
    private javax.swing.JLabel jLabelLeaveSmall;
    private javax.swing.JLabel jLabelLeaveType;
    private javax.swing.JLabel jLabelName;
    private javax.swing.JLabel jLabelPosition;
    private javax.swing.JLabel jLabelReason;
    private javax.swing.JLabel jLabelRequestDate;
    private javax.swing.JLabel jLabelStartDate;
    private javax.swing.JLabel jLabelStatus;
    private javax.swing.JLabel jLabelSupervisor;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanelLeaveRequestBox;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextAreaReason;
    private javax.swing.JTextField jTextFieldApprovedBy;
    private javax.swing.JTextField jTextFieldDepartment;
    private javax.swing.JTextField jTextFieldEmployeeNumber;
    private javax.swing.JTextField jTextFieldEndDate;
    private javax.swing.JTextField jTextFieldFullName;
    private javax.swing.JTextField jTextFieldLeaveDays;
    private javax.swing.JTextField jTextFieldLeaveRequestID;
    private javax.swing.JTextField jTextFieldLeaveType;
    private javax.swing.JTextField jTextFieldPosition;
    private javax.swing.JTextField jTextFieldRequestDate;
    private javax.swing.JTextField jTextFieldStartDate;
    private javax.swing.JTextField jTextFieldStatus;
    private javax.swing.JTextField jTextFieldSupervisor;
    // End of variables declaration//GEN-END:variables
}