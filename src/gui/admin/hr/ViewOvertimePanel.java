package gui.admin.hr;

import java.awt.CardLayout;

public class ViewOvertimePanel extends javax.swing.JPanel {
    
    private final AdminHRPortal hrPortal;

    public ViewOvertimePanel(AdminHRPortal hrPortal) {
        this.hrPortal = hrPortal;
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabelHelloAdmin = new javax.swing.JLabel();
        jLabelViewRecordSmall = new javax.swing.JLabel();
        jPanelViewOvertimeBox = new javax.swing.JPanel();
        jTextFieldEmployeeID = new javax.swing.JTextField();
        jLabelEmployeeID = new javax.swing.JLabel();
        jLabelName = new javax.swing.JLabel();
        jLabelBack = new javax.swing.JLabel();
        jLabelOvertimeID = new javax.swing.JLabel();
        jTextFieldOvertimeID = new javax.swing.JTextField();
        jLabelRequestDate = new javax.swing.JLabel();
        jTextFieldRequestDate = new javax.swing.JTextField();
        jTextFieldFullName = new javax.swing.JTextField();
        jLabelOvertimeWorkDate = new javax.swing.JLabel();
        jTextFieldOvertimeWorkDate = new javax.swing.JTextField();
        jLabelOvertimeHours = new javax.swing.JLabel();
        jTextFieldOvertimeHours = new javax.swing.JTextField();
        jLabelReason = new javax.swing.JLabel();
        jLabelPosition = new javax.swing.JLabel();
        jTextFieldPosition = new javax.swing.JTextField();
        jLabelDepartment = new javax.swing.JLabel();
        jTextFieldDepartment = new javax.swing.JTextField();
        jLabelApprovedBy = new javax.swing.JLabel();
        jTextFieldApprovedBy = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaReason = new javax.swing.JTextArea();
        jButtonReject = new javax.swing.JButton();
        jButtonApprove = new javax.swing.JButton();
        jLabelSupervisor = new javax.swing.JLabel();
        jTextFieldSupervisor = new javax.swing.JTextField();
        jLabelStatus = new javax.swing.JLabel();
        jTextFieldStatus = new javax.swing.JTextField();

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
        jLabelHelloAdmin.setBounds(30, 30, 137, 29);

        jLabelViewRecordSmall.setText("Overtime > View Record");
        jPanel1.add(jLabelViewRecordSmall);
        jLabelViewRecordSmall.setBounds(30, 60, 180, 16);

        jPanelViewOvertimeBox.setBackground(new java.awt.Color(255, 255, 255));
        jPanelViewOvertimeBox.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanelViewOvertimeBox.setLayout(null);

        jTextFieldEmployeeID.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        jTextFieldEmployeeID.setEnabled(false);
        jTextFieldEmployeeID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldEmployeeIDActionPerformed(evt);
            }
        });
        jPanelViewOvertimeBox.add(jTextFieldEmployeeID);
        jTextFieldEmployeeID.setBounds(180, 230, 350, 40);

        jLabelEmployeeID.setBackground(new java.awt.Color(255, 255, 255));
        jLabelEmployeeID.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelEmployeeID.setForeground(new java.awt.Color(0, 0, 0));
        jLabelEmployeeID.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelEmployeeID.setText("Employee ID :");
        jPanelViewOvertimeBox.add(jLabelEmployeeID);
        jLabelEmployeeID.setBounds(30, 230, 290, 40);

        jLabelName.setBackground(new java.awt.Color(255, 255, 255));
        jLabelName.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelName.setForeground(new java.awt.Color(0, 0, 0));
        jLabelName.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelName.setText("Name :");
        jPanelViewOvertimeBox.add(jLabelName);
        jLabelName.setBounds(30, 270, 290, 40);

        jLabelBack.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/back-icon.png"))); // NOI18N
        jLabelBack.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelBackMouseClicked(evt);
            }
        });
        jPanelViewOvertimeBox.add(jLabelBack);
        jLabelBack.setBounds(0, 560, 60, 60);

        jLabelOvertimeID.setBackground(new java.awt.Color(255, 255, 255));
        jLabelOvertimeID.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelOvertimeID.setForeground(new java.awt.Color(0, 0, 0));
        jLabelOvertimeID.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelOvertimeID.setText("Overtime ID :");
        jPanelViewOvertimeBox.add(jLabelOvertimeID);
        jLabelOvertimeID.setBounds(30, 150, 290, 40);

        jTextFieldOvertimeID.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        jTextFieldOvertimeID.setEnabled(false);
        jTextFieldOvertimeID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldOvertimeIDActionPerformed(evt);
            }
        });
        jPanelViewOvertimeBox.add(jTextFieldOvertimeID);
        jTextFieldOvertimeID.setBounds(180, 150, 350, 40);

        jLabelRequestDate.setBackground(new java.awt.Color(255, 255, 255));
        jLabelRequestDate.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelRequestDate.setForeground(new java.awt.Color(0, 0, 0));
        jLabelRequestDate.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelRequestDate.setText("Date :");
        jPanelViewOvertimeBox.add(jLabelRequestDate);
        jLabelRequestDate.setBounds(30, 190, 290, 40);

        jTextFieldRequestDate.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        jTextFieldRequestDate.setEnabled(false);
        jTextFieldRequestDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldRequestDateActionPerformed(evt);
            }
        });
        jPanelViewOvertimeBox.add(jTextFieldRequestDate);
        jTextFieldRequestDate.setBounds(180, 190, 350, 40);

        jTextFieldFullName.setEnabled(false);
        jTextFieldFullName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldFullNameActionPerformed(evt);
            }
        });
        jPanelViewOvertimeBox.add(jTextFieldFullName);
        jTextFieldFullName.setBounds(180, 270, 350, 40);

        jLabelOvertimeWorkDate.setBackground(new java.awt.Color(255, 255, 255));
        jLabelOvertimeWorkDate.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelOvertimeWorkDate.setForeground(new java.awt.Color(0, 0, 0));
        jLabelOvertimeWorkDate.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelOvertimeWorkDate.setText("Overtime Work Date :");
        jPanelViewOvertimeBox.add(jLabelOvertimeWorkDate);
        jLabelOvertimeWorkDate.setBounds(540, 190, 290, 40);

        jTextFieldOvertimeWorkDate.setEnabled(false);
        jTextFieldOvertimeWorkDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldOvertimeWorkDateActionPerformed(evt);
            }
        });
        jPanelViewOvertimeBox.add(jTextFieldOvertimeWorkDate);
        jTextFieldOvertimeWorkDate.setBounds(690, 190, 350, 40);

        jLabelOvertimeHours.setBackground(new java.awt.Color(255, 255, 255));
        jLabelOvertimeHours.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelOvertimeHours.setForeground(new java.awt.Color(0, 0, 0));
        jLabelOvertimeHours.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelOvertimeHours.setText("Overtime Hours :");
        jPanelViewOvertimeBox.add(jLabelOvertimeHours);
        jLabelOvertimeHours.setBounds(540, 230, 290, 40);

        jTextFieldOvertimeHours.setEnabled(false);
        jTextFieldOvertimeHours.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldOvertimeHoursActionPerformed(evt);
            }
        });
        jPanelViewOvertimeBox.add(jTextFieldOvertimeHours);
        jTextFieldOvertimeHours.setBounds(690, 230, 350, 40);

        jLabelReason.setBackground(new java.awt.Color(255, 255, 255));
        jLabelReason.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelReason.setForeground(new java.awt.Color(0, 0, 0));
        jLabelReason.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelReason.setText("Reason :");
        jPanelViewOvertimeBox.add(jLabelReason);
        jLabelReason.setBounds(540, 270, 290, 40);

        jLabelPosition.setBackground(new java.awt.Color(255, 255, 255));
        jLabelPosition.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelPosition.setForeground(new java.awt.Color(0, 0, 0));
        jLabelPosition.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelPosition.setText("Position :");
        jPanelViewOvertimeBox.add(jLabelPosition);
        jLabelPosition.setBounds(30, 310, 290, 40);

        jTextFieldPosition.setEnabled(false);
        jTextFieldPosition.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldPositionActionPerformed(evt);
            }
        });
        jPanelViewOvertimeBox.add(jTextFieldPosition);
        jTextFieldPosition.setBounds(180, 310, 350, 40);

        jLabelDepartment.setBackground(new java.awt.Color(255, 255, 255));
        jLabelDepartment.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelDepartment.setForeground(new java.awt.Color(0, 0, 0));
        jLabelDepartment.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelDepartment.setText("Department :");
        jPanelViewOvertimeBox.add(jLabelDepartment);
        jLabelDepartment.setBounds(30, 350, 290, 40);

        jTextFieldDepartment.setEnabled(false);
        jTextFieldDepartment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldDepartmentActionPerformed(evt);
            }
        });
        jPanelViewOvertimeBox.add(jTextFieldDepartment);
        jTextFieldDepartment.setBounds(180, 350, 350, 40);

        jLabelApprovedBy.setBackground(new java.awt.Color(255, 255, 255));
        jLabelApprovedBy.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelApprovedBy.setForeground(new java.awt.Color(0, 0, 0));
        jLabelApprovedBy.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelApprovedBy.setText("Approved By :");
        jPanelViewOvertimeBox.add(jLabelApprovedBy);
        jLabelApprovedBy.setBounds(540, 310, 290, 40);

        jTextFieldApprovedBy.setEnabled(false);
        jTextFieldApprovedBy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldApprovedByActionPerformed(evt);
            }
        });
        jPanelViewOvertimeBox.add(jTextFieldApprovedBy);
        jTextFieldApprovedBy.setBounds(690, 310, 350, 40);

        jTextAreaReason.setColumns(20);
        jTextAreaReason.setLineWrap(true);
        jTextAreaReason.setRows(5);
        jTextAreaReason.setEnabled(false);
        jScrollPane1.setViewportView(jTextAreaReason);

        jPanelViewOvertimeBox.add(jScrollPane1);
        jScrollPane1.setBounds(690, 270, 350, 40);

        jButtonReject.setBackground(new java.awt.Color(158, 0, 30));
        jButtonReject.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButtonReject.setForeground(new java.awt.Color(255, 255, 255));
        jButtonReject.setText("Reject");
        jButtonReject.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRejectActionPerformed(evt);
            }
        });
        jPanelViewOvertimeBox.add(jButtonReject);
        jButtonReject.setBounds(870, 570, 170, 40);

        jButtonApprove.setBackground(new java.awt.Color(0, 135, 0));
        jButtonApprove.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButtonApprove.setForeground(new java.awt.Color(255, 255, 255));
        jButtonApprove.setText("Approve");
        jButtonApprove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonApproveActionPerformed(evt);
            }
        });
        jPanelViewOvertimeBox.add(jButtonApprove);
        jButtonApprove.setBounds(680, 570, 170, 40);

        jLabelSupervisor.setBackground(new java.awt.Color(255, 255, 255));
        jLabelSupervisor.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelSupervisor.setForeground(new java.awt.Color(0, 0, 0));
        jLabelSupervisor.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelSupervisor.setText("Supervisor :");
        jPanelViewOvertimeBox.add(jLabelSupervisor);
        jLabelSupervisor.setBounds(540, 150, 290, 40);

        jTextFieldSupervisor.setEnabled(false);
        jTextFieldSupervisor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldSupervisorActionPerformed(evt);
            }
        });
        jPanelViewOvertimeBox.add(jTextFieldSupervisor);
        jTextFieldSupervisor.setBounds(690, 150, 350, 40);

        jLabelStatus.setBackground(new java.awt.Color(255, 255, 255));
        jLabelStatus.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelStatus.setForeground(new java.awt.Color(0, 0, 0));
        jLabelStatus.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelStatus.setText("Status :");
        jPanelViewOvertimeBox.add(jLabelStatus);
        jLabelStatus.setBounds(540, 350, 290, 40);

        jTextFieldStatus.setEnabled(false);
        jTextFieldStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldStatusActionPerformed(evt);
            }
        });
        jPanelViewOvertimeBox.add(jTextFieldStatus);
        jTextFieldStatus.setBounds(690, 350, 350, 40);

        jPanel1.add(jPanelViewOvertimeBox);
        jPanelViewOvertimeBox.setBounds(30, 110, 1060, 640);

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void jLabelBackMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelBackMouseClicked
        // TODO add your handling code here:
        CardLayout cardLayout = (CardLayout) hrPortal.getPanelParentCard().getLayout();
        cardLayout.show(hrPortal.getPanelParentCard(), "Overtime");
    }//GEN-LAST:event_jLabelBackMouseClicked

    private void jTextFieldEmployeeIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldEmployeeIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldEmployeeIDActionPerformed

    private void jTextFieldOvertimeIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldOvertimeIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldOvertimeIDActionPerformed

    private void jTextFieldRequestDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldRequestDateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldRequestDateActionPerformed

    private void jTextFieldFullNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldFullNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldFullNameActionPerformed

    private void jTextFieldOvertimeWorkDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldOvertimeWorkDateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldOvertimeWorkDateActionPerformed

    private void jTextFieldOvertimeHoursActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldOvertimeHoursActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldOvertimeHoursActionPerformed

    private void jTextFieldPositionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldPositionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldPositionActionPerformed

    private void jTextFieldDepartmentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldDepartmentActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldDepartmentActionPerformed

    private void jTextFieldApprovedByActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldApprovedByActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldApprovedByActionPerformed

    private void jButtonRejectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRejectActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonRejectActionPerformed

    private void jButtonApproveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonApproveActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonApproveActionPerformed

    private void jTextFieldSupervisorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldSupervisorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldSupervisorActionPerformed

    private void jTextFieldStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldStatusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldStatusActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonApprove;
    private javax.swing.JButton jButtonReject;
    private javax.swing.JLabel jLabelApprovedBy;
    private javax.swing.JLabel jLabelBack;
    private javax.swing.JLabel jLabelDepartment;
    private javax.swing.JLabel jLabelEmployeeID;
    private javax.swing.JLabel jLabelHelloAdmin;
    private javax.swing.JLabel jLabelName;
    private javax.swing.JLabel jLabelOvertimeHours;
    private javax.swing.JLabel jLabelOvertimeID;
    private javax.swing.JLabel jLabelOvertimeWorkDate;
    private javax.swing.JLabel jLabelPosition;
    private javax.swing.JLabel jLabelReason;
    private javax.swing.JLabel jLabelRequestDate;
    private javax.swing.JLabel jLabelStatus;
    private javax.swing.JLabel jLabelSupervisor;
    private javax.swing.JLabel jLabelViewRecordSmall;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanelViewOvertimeBox;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextAreaReason;
    private javax.swing.JTextField jTextFieldApprovedBy;
    private javax.swing.JTextField jTextFieldDepartment;
    private javax.swing.JTextField jTextFieldEmployeeID;
    private javax.swing.JTextField jTextFieldFullName;
    private javax.swing.JTextField jTextFieldOvertimeHours;
    private javax.swing.JTextField jTextFieldOvertimeID;
    private javax.swing.JTextField jTextFieldOvertimeWorkDate;
    private javax.swing.JTextField jTextFieldPosition;
    private javax.swing.JTextField jTextFieldRequestDate;
    private javax.swing.JTextField jTextFieldStatus;
    private javax.swing.JTextField jTextFieldSupervisor;
    // End of variables declaration//GEN-END:variables
}