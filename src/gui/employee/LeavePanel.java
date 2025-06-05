package gui.employee;

import java.awt.CardLayout;

public class LeavePanel extends javax.swing.JPanel {
    
    private final EmployeePortal employeePortal;

    public LeavePanel(EmployeePortal employeePortal) {
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
        jLabelLeaveStartDate = new javax.swing.JLabel();
        jLabelLeaveEndDate = new javax.swing.JLabel();
        jDateChooserLeaveStartDate = new com.toedter.calendar.JDateChooser();
        jDateChooserLeaveEndDate = new com.toedter.calendar.JDateChooser();
        jLabelAsterisk = new javax.swing.JLabel();
        jLabelLeaveType = new javax.swing.JLabel();
        jLabelReason = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaReason = new javax.swing.JTextArea();
        jComboBoxLeaveType = new javax.swing.JComboBox<>();
        jButtonSubmit = new javax.swing.JButton();
        jLabelLeaveBalance = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableLeaveBalance = new javax.swing.JTable();
        jLabelLeaveHistory = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTableLeaveHistory = new javax.swing.JTable();
        jLabelAsterisk2 = new javax.swing.JLabel();
        jLabelAsterisk3 = new javax.swing.JLabel();
        jButtonView = new javax.swing.JButton();

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

        jLabelLeaveSmall.setText("Leave");
        jPanel1.add(jLabelLeaveSmall);
        jLabelLeaveSmall.setBounds(30, 60, 180, 16);

        jPanelLeaveRequestBox.setBackground(new java.awt.Color(255, 255, 255));
        jPanelLeaveRequestBox.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanelLeaveRequestBox.setLayout(null);

        jLabelLeaveStartDate.setBackground(new java.awt.Color(255, 255, 255));
        jLabelLeaveStartDate.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelLeaveStartDate.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelLeaveStartDate.setText("Leave Start Date   :");
        jPanelLeaveRequestBox.add(jLabelLeaveStartDate);
        jLabelLeaveStartDate.setBounds(20, 40, 140, 40);

        jLabelLeaveEndDate.setBackground(new java.awt.Color(255, 255, 255));
        jLabelLeaveEndDate.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelLeaveEndDate.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelLeaveEndDate.setText("Leave End Date     :");
        jPanelLeaveRequestBox.add(jLabelLeaveEndDate);
        jLabelLeaveEndDate.setBounds(20, 80, 140, 40);

        jDateChooserLeaveStartDate.setFocusable(false);
        jPanelLeaveRequestBox.add(jDateChooserLeaveStartDate);
        jDateChooserLeaveStartDate.setBounds(180, 40, 250, 40);

        jDateChooserLeaveEndDate.setFocusable(false);
        jDateChooserLeaveEndDate.setOpaque(false);
        jPanelLeaveRequestBox.add(jDateChooserLeaveEndDate);
        jDateChooserLeaveEndDate.setBounds(180, 80, 250, 40);

        jLabelAsterisk.setBackground(new java.awt.Color(255, 255, 255));
        jLabelAsterisk.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelAsterisk.setForeground(new java.awt.Color(255, 0, 0));
        jLabelAsterisk.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelAsterisk.setText("*");
        jPanelLeaveRequestBox.add(jLabelAsterisk);
        jLabelAsterisk.setBounds(100, 80, 50, 30);

        jLabelLeaveType.setBackground(new java.awt.Color(255, 255, 255));
        jLabelLeaveType.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelLeaveType.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelLeaveType.setText("Type     :");
        jPanelLeaveRequestBox.add(jLabelLeaveType);
        jLabelLeaveType.setBounds(20, 120, 70, 40);

        jLabelReason.setBackground(new java.awt.Color(255, 255, 255));
        jLabelReason.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelReason.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelReason.setText("Reason :");
        jPanelLeaveRequestBox.add(jLabelReason);
        jLabelReason.setBounds(480, 40, 70, 40);

        jTextAreaReason.setColumns(20);
        jTextAreaReason.setLineWrap(true);
        jTextAreaReason.setRows(5);
        jScrollPane1.setViewportView(jTextAreaReason);

        jPanelLeaveRequestBox.add(jScrollPane1);
        jScrollPane1.setBounds(560, 40, 250, 120);

        jComboBoxLeaveType.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jComboBoxLeaveType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select", "Vacation Leave", "Sick Leave", "Emergency Leave", "Maternity Leave", "Paternity Leave" }));
        jComboBoxLeaveType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxLeaveTypeActionPerformed(evt);
            }
        });
        jPanelLeaveRequestBox.add(jComboBoxLeaveType);
        jComboBoxLeaveType.setBounds(180, 120, 250, 40);

        jButtonSubmit.setBackground(new java.awt.Color(0, 135, 0));
        jButtonSubmit.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButtonSubmit.setForeground(new java.awt.Color(255, 255, 255));
        jButtonSubmit.setText("Submit");
        jButtonSubmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSubmitActionPerformed(evt);
            }
        });
        jPanelLeaveRequestBox.add(jButtonSubmit);
        jButtonSubmit.setBounds(860, 80, 180, 40);

        jLabelLeaveBalance.setBackground(new java.awt.Color(255, 255, 255));
        jLabelLeaveBalance.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelLeaveBalance.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelLeaveBalance.setText("LEAVE BALANCE");
        jPanelLeaveRequestBox.add(jLabelLeaveBalance);
        jLabelLeaveBalance.setBounds(20, 180, 140, 40);

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

        jPanelLeaveRequestBox.add(jScrollPane2);
        jScrollPane2.setBounds(20, 220, 1020, 120);

        jLabelLeaveHistory.setBackground(new java.awt.Color(255, 255, 255));
        jLabelLeaveHistory.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelLeaveHistory.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelLeaveHistory.setText("LEAVE HISTORY");
        jPanelLeaveRequestBox.add(jLabelLeaveHistory);
        jLabelLeaveHistory.setBounds(20, 360, 140, 40);

        jTableLeaveHistory.setAutoCreateRowSorter(true);
        jTableLeaveHistory.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Leave ID", "Request Date", "Start Date", "End Date", "Days", "Type", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableLeaveHistory.setFocusable(false);
        jTableLeaveHistory.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTableLeaveHistory.setShowGrid(true);
        jTableLeaveHistory.getTableHeader().setResizingAllowed(false);
        jTableLeaveHistory.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(jTableLeaveHistory);

        jPanelLeaveRequestBox.add(jScrollPane3);
        jScrollPane3.setBounds(20, 400, 1020, 150);

        jLabelAsterisk2.setBackground(new java.awt.Color(255, 255, 255));
        jLabelAsterisk2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelAsterisk2.setForeground(new java.awt.Color(255, 0, 0));
        jLabelAsterisk2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelAsterisk2.setText("*");
        jPanelLeaveRequestBox.add(jLabelAsterisk2);
        jLabelAsterisk2.setBounds(40, 120, 40, 40);

        jLabelAsterisk3.setBackground(new java.awt.Color(255, 255, 255));
        jLabelAsterisk3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelAsterisk3.setForeground(new java.awt.Color(255, 0, 0));
        jLabelAsterisk3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelAsterisk3.setText("*");
        jPanelLeaveRequestBox.add(jLabelAsterisk3);
        jLabelAsterisk3.setBounds(110, 40, 50, 30);

        jButtonView.setBackground(new java.awt.Color(0, 43, 89));
        jButtonView.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButtonView.setForeground(new java.awt.Color(255, 255, 255));
        jButtonView.setText("View");
        jButtonView.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonViewActionPerformed(evt);
            }
        });
        jPanelLeaveRequestBox.add(jButtonView);
        jButtonView.setBounds(860, 570, 180, 40);

        jPanel1.add(jPanelLeaveRequestBox);
        jPanelLeaveRequestBox.setBounds(30, 110, 1060, 640);

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBoxLeaveTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxLeaveTypeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxLeaveTypeActionPerformed

    private void jButtonSubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSubmitActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonSubmitActionPerformed

    private void jButtonViewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonViewActionPerformed
        // TODO add your handling code here:
        CardLayout cardLayout = (CardLayout) employeePortal.getPanelParentCard().getLayout();
        cardLayout.show(employeePortal.getPanelParentCard(), "ViewLeave");
    }//GEN-LAST:event_jButtonViewActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonSubmit;
    private javax.swing.JButton jButtonView;
    private javax.swing.JComboBox<String> jComboBoxLeaveType;
    private com.toedter.calendar.JDateChooser jDateChooserLeaveEndDate;
    private com.toedter.calendar.JDateChooser jDateChooserLeaveStartDate;
    private javax.swing.JLabel jLabelAsterisk;
    private javax.swing.JLabel jLabelAsterisk2;
    private javax.swing.JLabel jLabelAsterisk3;
    private javax.swing.JLabel jLabelHelloEmployee;
    private javax.swing.JLabel jLabelLeaveBalance;
    private javax.swing.JLabel jLabelLeaveEndDate;
    private javax.swing.JLabel jLabelLeaveHistory;
    private javax.swing.JLabel jLabelLeaveSmall;
    private javax.swing.JLabel jLabelLeaveStartDate;
    private javax.swing.JLabel jLabelLeaveType;
    private javax.swing.JLabel jLabelReason;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanelLeaveRequestBox;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTableLeaveBalance;
    private javax.swing.JTable jTableLeaveHistory;
    private javax.swing.JTextArea jTextAreaReason;
    // End of variables declaration//GEN-END:variables
}