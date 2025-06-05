package gui.admin.hr;

import java.awt.CardLayout;

public class ViewLeavePanel extends javax.swing.JPanel {
    
    private final AdminHRPortal hrPortal;

    public ViewLeavePanel(AdminHRPortal hrPortal) {
        this.hrPortal = hrPortal;
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabelHelloAdmin = new javax.swing.JLabel();
        jLabelViewRecordSmall = new javax.swing.JLabel();
        jPanelViewLeaveBox = new javax.swing.JPanel();
        jTextFieldEmployeeID = new javax.swing.JTextField();
        jLabelEmployeeID = new javax.swing.JLabel();
        jLabelName = new javax.swing.JLabel();
        jLabelBack = new javax.swing.JLabel();
        jLabelLeaveID = new javax.swing.JLabel();
        jTextFieldLeaveID = new javax.swing.JTextField();
        jLabelRequestDate = new javax.swing.JLabel();
        jTextFieldRequestDate = new javax.swing.JTextField();
        jTextFieldFullName = new javax.swing.JTextField();
        jLabelStartDate = new javax.swing.JLabel();
        jTextFieldStartDate = new javax.swing.JTextField();
        jLabelEndDate = new javax.swing.JLabel();
        jTextFieldEndDate = new javax.swing.JTextField();
        jLabelLeaveDays = new javax.swing.JLabel();
        jTextFieldLeaveType = new javax.swing.JTextField();
        jLabelLeaveType = new javax.swing.JLabel();
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
        jTextFieldLeaveDays = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableLeaveBalance = new javax.swing.JTable();
        jLabelLeaveBalance = new javax.swing.JLabel();

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

        jLabelViewRecordSmall.setText("Leaves > View Record");
        jPanel1.add(jLabelViewRecordSmall);
        jLabelViewRecordSmall.setBounds(30, 60, 180, 16);

        jPanelViewLeaveBox.setBackground(new java.awt.Color(255, 255, 255));
        jPanelViewLeaveBox.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanelViewLeaveBox.setLayout(null);

        jTextFieldEmployeeID.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        jTextFieldEmployeeID.setEnabled(false);
        jTextFieldEmployeeID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldEmployeeIDActionPerformed(evt);
            }
        });
        jPanelViewLeaveBox.add(jTextFieldEmployeeID);
        jTextFieldEmployeeID.setBounds(180, 130, 350, 40);

        jLabelEmployeeID.setBackground(new java.awt.Color(255, 255, 255));
        jLabelEmployeeID.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelEmployeeID.setForeground(new java.awt.Color(0, 0, 0));
        jLabelEmployeeID.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelEmployeeID.setText("Employee ID :");
        jPanelViewLeaveBox.add(jLabelEmployeeID);
        jLabelEmployeeID.setBounds(30, 130, 290, 40);

        jLabelName.setBackground(new java.awt.Color(255, 255, 255));
        jLabelName.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelName.setForeground(new java.awt.Color(0, 0, 0));
        jLabelName.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelName.setText("Name :");
        jPanelViewLeaveBox.add(jLabelName);
        jLabelName.setBounds(30, 170, 290, 40);

        jLabelBack.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/back-icon.png"))); // NOI18N
        jLabelBack.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelBackMouseClicked(evt);
            }
        });
        jPanelViewLeaveBox.add(jLabelBack);
        jLabelBack.setBounds(0, 560, 60, 60);

        jLabelLeaveID.setBackground(new java.awt.Color(255, 255, 255));
        jLabelLeaveID.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelLeaveID.setForeground(new java.awt.Color(0, 0, 0));
        jLabelLeaveID.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelLeaveID.setText("Leave ID :");
        jPanelViewLeaveBox.add(jLabelLeaveID);
        jLabelLeaveID.setBounds(30, 50, 290, 40);

        jTextFieldLeaveID.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        jTextFieldLeaveID.setEnabled(false);
        jTextFieldLeaveID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldLeaveIDActionPerformed(evt);
            }
        });
        jPanelViewLeaveBox.add(jTextFieldLeaveID);
        jTextFieldLeaveID.setBounds(180, 50, 350, 40);

        jLabelRequestDate.setBackground(new java.awt.Color(255, 255, 255));
        jLabelRequestDate.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelRequestDate.setForeground(new java.awt.Color(0, 0, 0));
        jLabelRequestDate.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelRequestDate.setText("Date :");
        jPanelViewLeaveBox.add(jLabelRequestDate);
        jLabelRequestDate.setBounds(30, 90, 290, 40);

        jTextFieldRequestDate.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        jTextFieldRequestDate.setEnabled(false);
        jTextFieldRequestDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldRequestDateActionPerformed(evt);
            }
        });
        jPanelViewLeaveBox.add(jTextFieldRequestDate);
        jTextFieldRequestDate.setBounds(180, 90, 350, 40);

        jTextFieldFullName.setEnabled(false);
        jTextFieldFullName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldFullNameActionPerformed(evt);
            }
        });
        jPanelViewLeaveBox.add(jTextFieldFullName);
        jTextFieldFullName.setBounds(180, 170, 350, 40);

        jLabelStartDate.setBackground(new java.awt.Color(255, 255, 255));
        jLabelStartDate.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelStartDate.setForeground(new java.awt.Color(0, 0, 0));
        jLabelStartDate.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelStartDate.setText("Start Date :");
        jPanelViewLeaveBox.add(jLabelStartDate);
        jLabelStartDate.setBounds(540, 50, 290, 40);

        jTextFieldStartDate.setEnabled(false);
        jTextFieldStartDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldStartDateActionPerformed(evt);
            }
        });
        jPanelViewLeaveBox.add(jTextFieldStartDate);
        jTextFieldStartDate.setBounds(690, 50, 350, 40);

        jLabelEndDate.setBackground(new java.awt.Color(255, 255, 255));
        jLabelEndDate.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelEndDate.setForeground(new java.awt.Color(0, 0, 0));
        jLabelEndDate.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelEndDate.setText("End Date :");
        jPanelViewLeaveBox.add(jLabelEndDate);
        jLabelEndDate.setBounds(540, 90, 290, 40);

        jTextFieldEndDate.setEnabled(false);
        jTextFieldEndDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldEndDateActionPerformed(evt);
            }
        });
        jPanelViewLeaveBox.add(jTextFieldEndDate);
        jTextFieldEndDate.setBounds(690, 90, 350, 40);

        jLabelLeaveDays.setBackground(new java.awt.Color(255, 255, 255));
        jLabelLeaveDays.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelLeaveDays.setForeground(new java.awt.Color(0, 0, 0));
        jLabelLeaveDays.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelLeaveDays.setText("Leave Days :");
        jPanelViewLeaveBox.add(jLabelLeaveDays);
        jLabelLeaveDays.setBounds(540, 130, 290, 40);

        jTextFieldLeaveType.setEnabled(false);
        jTextFieldLeaveType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldLeaveTypeActionPerformed(evt);
            }
        });
        jPanelViewLeaveBox.add(jTextFieldLeaveType);
        jTextFieldLeaveType.setBounds(690, 170, 350, 40);

        jLabelLeaveType.setBackground(new java.awt.Color(255, 255, 255));
        jLabelLeaveType.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelLeaveType.setForeground(new java.awt.Color(0, 0, 0));
        jLabelLeaveType.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelLeaveType.setText("Leave Type :");
        jPanelViewLeaveBox.add(jLabelLeaveType);
        jLabelLeaveType.setBounds(540, 170, 290, 40);

        jLabelReason.setBackground(new java.awt.Color(255, 255, 255));
        jLabelReason.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelReason.setForeground(new java.awt.Color(0, 0, 0));
        jLabelReason.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelReason.setText("Reason :");
        jPanelViewLeaveBox.add(jLabelReason);
        jLabelReason.setBounds(540, 210, 290, 40);

        jLabelPosition.setBackground(new java.awt.Color(255, 255, 255));
        jLabelPosition.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelPosition.setForeground(new java.awt.Color(0, 0, 0));
        jLabelPosition.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelPosition.setText("Position :");
        jPanelViewLeaveBox.add(jLabelPosition);
        jLabelPosition.setBounds(30, 210, 290, 40);

        jTextFieldPosition.setEnabled(false);
        jTextFieldPosition.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldPositionActionPerformed(evt);
            }
        });
        jPanelViewLeaveBox.add(jTextFieldPosition);
        jTextFieldPosition.setBounds(180, 210, 350, 40);

        jLabelDepartment.setBackground(new java.awt.Color(255, 255, 255));
        jLabelDepartment.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelDepartment.setForeground(new java.awt.Color(0, 0, 0));
        jLabelDepartment.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelDepartment.setText("Department :");
        jPanelViewLeaveBox.add(jLabelDepartment);
        jLabelDepartment.setBounds(30, 250, 290, 40);

        jTextFieldDepartment.setEnabled(false);
        jTextFieldDepartment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldDepartmentActionPerformed(evt);
            }
        });
        jPanelViewLeaveBox.add(jTextFieldDepartment);
        jTextFieldDepartment.setBounds(180, 250, 350, 40);

        jLabelApprovedBy.setBackground(new java.awt.Color(255, 255, 255));
        jLabelApprovedBy.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelApprovedBy.setForeground(new java.awt.Color(0, 0, 0));
        jLabelApprovedBy.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelApprovedBy.setText("Approved By :");
        jPanelViewLeaveBox.add(jLabelApprovedBy);
        jLabelApprovedBy.setBounds(540, 250, 290, 40);

        jTextFieldApprovedBy.setEnabled(false);
        jTextFieldApprovedBy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldApprovedByActionPerformed(evt);
            }
        });
        jPanelViewLeaveBox.add(jTextFieldApprovedBy);
        jTextFieldApprovedBy.setBounds(690, 250, 350, 40);

        jTextAreaReason.setColumns(20);
        jTextAreaReason.setLineWrap(true);
        jTextAreaReason.setRows(5);
        jTextAreaReason.setEnabled(false);
        jScrollPane1.setViewportView(jTextAreaReason);

        jPanelViewLeaveBox.add(jScrollPane1);
        jScrollPane1.setBounds(690, 210, 350, 40);

        jButtonReject.setBackground(new java.awt.Color(158, 0, 30));
        jButtonReject.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButtonReject.setForeground(new java.awt.Color(255, 255, 255));
        jButtonReject.setText("Reject");
        jButtonReject.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRejectActionPerformed(evt);
            }
        });
        jPanelViewLeaveBox.add(jButtonReject);
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
        jPanelViewLeaveBox.add(jButtonApprove);
        jButtonApprove.setBounds(680, 570, 170, 40);

        jLabelSupervisor.setBackground(new java.awt.Color(255, 255, 255));
        jLabelSupervisor.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelSupervisor.setForeground(new java.awt.Color(0, 0, 0));
        jLabelSupervisor.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelSupervisor.setText("Supervisor :");
        jPanelViewLeaveBox.add(jLabelSupervisor);
        jLabelSupervisor.setBounds(30, 290, 290, 40);

        jTextFieldSupervisor.setEnabled(false);
        jTextFieldSupervisor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldSupervisorActionPerformed(evt);
            }
        });
        jPanelViewLeaveBox.add(jTextFieldSupervisor);
        jTextFieldSupervisor.setBounds(180, 290, 350, 40);

        jLabelStatus.setBackground(new java.awt.Color(255, 255, 255));
        jLabelStatus.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelStatus.setForeground(new java.awt.Color(0, 0, 0));
        jLabelStatus.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelStatus.setText("Status :");
        jPanelViewLeaveBox.add(jLabelStatus);
        jLabelStatus.setBounds(540, 290, 290, 40);

        jTextFieldStatus.setEnabled(false);
        jTextFieldStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldStatusActionPerformed(evt);
            }
        });
        jPanelViewLeaveBox.add(jTextFieldStatus);
        jTextFieldStatus.setBounds(690, 290, 350, 40);

        jTextFieldLeaveDays.setEnabled(false);
        jTextFieldLeaveDays.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldLeaveDaysActionPerformed(evt);
            }
        });
        jPanelViewLeaveBox.add(jTextFieldLeaveDays);
        jTextFieldLeaveDays.setBounds(690, 130, 350, 40);

        jTableLeaveBalance.setAutoCreateRowSorter(true);
        jTableLeaveBalance.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Leave Type", "Entitlement Days", "Used Days", "Remaining Days"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableLeaveBalance.setFocusable(false);
        jTableLeaveBalance.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTableLeaveBalance.setShowGrid(true);
        jTableLeaveBalance.getTableHeader().setResizingAllowed(false);
        jTableLeaveBalance.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(jTableLeaveBalance);

        jPanelViewLeaveBox.add(jScrollPane2);
        jScrollPane2.setBounds(30, 400, 1010, 120);

        jLabelLeaveBalance.setBackground(new java.awt.Color(255, 255, 255));
        jLabelLeaveBalance.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelLeaveBalance.setForeground(new java.awt.Color(0, 0, 0));
        jLabelLeaveBalance.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelLeaveBalance.setText("LEAVE BALANCE");
        jPanelViewLeaveBox.add(jLabelLeaveBalance);
        jLabelLeaveBalance.setBounds(30, 360, 107, 40);

        jPanel1.add(jPanelViewLeaveBox);
        jPanelViewLeaveBox.setBounds(30, 110, 1060, 640);

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void jLabelBackMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelBackMouseClicked
        // TODO add your handling code here:
        CardLayout cardLayout = (CardLayout) hrPortal.getPanelParentCard().getLayout();
        cardLayout.show(hrPortal.getPanelParentCard(), "Leaves");
    }//GEN-LAST:event_jLabelBackMouseClicked

    private void jTextFieldEmployeeIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldEmployeeIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldEmployeeIDActionPerformed

    private void jTextFieldLeaveIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldLeaveIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldLeaveIDActionPerformed

    private void jTextFieldRequestDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldRequestDateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldRequestDateActionPerformed

    private void jTextFieldFullNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldFullNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldFullNameActionPerformed

    private void jTextFieldStartDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldStartDateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldStartDateActionPerformed

    private void jTextFieldEndDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldEndDateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldEndDateActionPerformed

    private void jTextFieldLeaveTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldLeaveTypeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldLeaveTypeActionPerformed

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

    private void jTextFieldLeaveDaysActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldLeaveDaysActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldLeaveDaysActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonApprove;
    private javax.swing.JButton jButtonReject;
    private javax.swing.JLabel jLabelApprovedBy;
    private javax.swing.JLabel jLabelBack;
    private javax.swing.JLabel jLabelDepartment;
    private javax.swing.JLabel jLabelEmployeeID;
    private javax.swing.JLabel jLabelEndDate;
    private javax.swing.JLabel jLabelHelloAdmin;
    private javax.swing.JLabel jLabelLeaveBalance;
    private javax.swing.JLabel jLabelLeaveDays;
    private javax.swing.JLabel jLabelLeaveID;
    private javax.swing.JLabel jLabelLeaveType;
    private javax.swing.JLabel jLabelName;
    private javax.swing.JLabel jLabelPosition;
    private javax.swing.JLabel jLabelReason;
    private javax.swing.JLabel jLabelRequestDate;
    private javax.swing.JLabel jLabelStartDate;
    private javax.swing.JLabel jLabelStatus;
    private javax.swing.JLabel jLabelSupervisor;
    private javax.swing.JLabel jLabelViewRecordSmall;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanelViewLeaveBox;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTableLeaveBalance;
    private javax.swing.JTextArea jTextAreaReason;
    private javax.swing.JTextField jTextFieldApprovedBy;
    private javax.swing.JTextField jTextFieldDepartment;
    private javax.swing.JTextField jTextFieldEmployeeID;
    private javax.swing.JTextField jTextFieldEndDate;
    private javax.swing.JTextField jTextFieldFullName;
    private javax.swing.JTextField jTextFieldLeaveDays;
    private javax.swing.JTextField jTextFieldLeaveID;
    private javax.swing.JTextField jTextFieldLeaveType;
    private javax.swing.JTextField jTextFieldPosition;
    private javax.swing.JTextField jTextFieldRequestDate;
    private javax.swing.JTextField jTextFieldStartDate;
    private javax.swing.JTextField jTextFieldStatus;
    private javax.swing.JTextField jTextFieldSupervisor;
    // End of variables declaration//GEN-END:variables
}