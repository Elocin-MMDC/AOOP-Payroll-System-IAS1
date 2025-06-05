package gui.admin.finance;

import java.awt.CardLayout;

public class PayrollPanel extends javax.swing.JPanel {

    private final AdminFinancePortal financePortal;
    
    public PayrollPanel(AdminFinancePortal financePortal) {
        this.financePortal = financePortal;
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabelHelloAdmin = new javax.swing.JLabel();
        jLabelPayrollSmall = new javax.swing.JLabel();
        jPanelPayrollBox = new javax.swing.JPanel();
        jLabelSelectMonth = new javax.swing.JLabel();
        jLabelSelectYear = new javax.swing.JLabel();
        jComboBoxYear = new javax.swing.JComboBox<>();
        jComboBoxMonth = new javax.swing.JComboBox<>();
        jButtonGeneratePayslip = new javax.swing.JButton();
        jButtonBatchProcess = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableEmployeeRecords = new javax.swing.JTable();
        jLabelPayrollRecords = new javax.swing.JLabel();
        jLabelSeachByEmployeeID = new javax.swing.JLabel();
        jTextFieldSearchById = new javax.swing.JTextField();
        jLabelilterByPayPeriod = new javax.swing.JLabel();
        jComboBoxFilterByPayPeriod = new javax.swing.JComboBox<>();

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

        jLabelPayrollSmall.setText("Payroll");
        jPanel1.add(jLabelPayrollSmall);
        jLabelPayrollSmall.setBounds(30, 60, 100, 16);

        jPanelPayrollBox.setBackground(new java.awt.Color(255, 255, 255));
        jPanelPayrollBox.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanelPayrollBox.setLayout(null);

        jLabelSelectMonth.setBackground(new java.awt.Color(255, 255, 255));
        jLabelSelectMonth.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelSelectMonth.setForeground(new java.awt.Color(0, 0, 0));
        jLabelSelectMonth.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelSelectMonth.setText("Select Month :");
        jPanelPayrollBox.add(jLabelSelectMonth);
        jLabelSelectMonth.setBounds(20, 30, 180, 40);

        jLabelSelectYear.setBackground(new java.awt.Color(255, 255, 255));
        jLabelSelectYear.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelSelectYear.setForeground(new java.awt.Color(0, 0, 0));
        jLabelSelectYear.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelSelectYear.setText("Select Year :");
        jPanelPayrollBox.add(jLabelSelectYear);
        jLabelSelectYear.setBounds(20, 70, 180, 40);

        jComboBoxYear.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jComboBoxYear.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select", "2022", "2023", "2024", "2025" }));
        jComboBoxYear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxYearActionPerformed(evt);
            }
        });
        jPanelPayrollBox.add(jComboBoxYear);
        jComboBoxYear.setBounds(170, 70, 260, 40);

        jComboBoxMonth.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jComboBoxMonth.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select", "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" }));
        jComboBoxMonth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxMonthActionPerformed(evt);
            }
        });
        jPanelPayrollBox.add(jComboBoxMonth);
        jComboBoxMonth.setBounds(170, 30, 260, 40);

        jButtonGeneratePayslip.setBackground(new java.awt.Color(0, 135, 0));
        jButtonGeneratePayslip.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButtonGeneratePayslip.setForeground(new java.awt.Color(255, 255, 255));
        jButtonGeneratePayslip.setText("Generate Payslip");
        jButtonGeneratePayslip.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGeneratePayslipActionPerformed(evt);
            }
        });
        jPanelPayrollBox.add(jButtonGeneratePayslip);
        jButtonGeneratePayslip.setBounds(780, 580, 260, 40);

        jButtonBatchProcess.setBackground(new java.awt.Color(0, 77, 159));
        jButtonBatchProcess.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButtonBatchProcess.setForeground(new java.awt.Color(255, 255, 255));
        jButtonBatchProcess.setText("Batch Process");
        jButtonBatchProcess.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBatchProcessActionPerformed(evt);
            }
        });
        jPanelPayrollBox.add(jButtonBatchProcess);
        jButtonBatchProcess.setBounds(170, 110, 260, 40);

        jTableEmployeeRecords.setAutoCreateRowSorter(true);
        jTableEmployeeRecords.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Payslip ID", "Employee ID", "Pay Period", "Name", "Gross Income", "Total Deductions", "Total Allowances", "Net Income"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableEmployeeRecords.setFocusable(false);
        jTableEmployeeRecords.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTableEmployeeRecords.setShowGrid(true);
        jTableEmployeeRecords.getTableHeader().setResizingAllowed(false);
        jTableEmployeeRecords.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jTableEmployeeRecords);

        jPanelPayrollBox.add(jScrollPane1);
        jScrollPane1.setBounds(20, 210, 1020, 340);

        jLabelPayrollRecords.setBackground(new java.awt.Color(255, 255, 255));
        jLabelPayrollRecords.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelPayrollRecords.setForeground(new java.awt.Color(0, 0, 0));
        jLabelPayrollRecords.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelPayrollRecords.setText("PAYROLL RECORDS");
        jPanelPayrollBox.add(jLabelPayrollRecords);
        jLabelPayrollRecords.setBounds(20, 170, 180, 40);

        jLabelSeachByEmployeeID.setBackground(new java.awt.Color(255, 255, 255));
        jLabelSeachByEmployeeID.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelSeachByEmployeeID.setForeground(new java.awt.Color(0, 0, 0));
        jLabelSeachByEmployeeID.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelSeachByEmployeeID.setText("Search by Employee ID :");
        jPanelPayrollBox.add(jLabelSeachByEmployeeID);
        jLabelSeachByEmployeeID.setBounds(600, 30, 180, 40);

        jTextFieldSearchById.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        jTextFieldSearchById.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldSearchByIdActionPerformed(evt);
            }
        });
        jTextFieldSearchById.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldSearchByIdKeyPressed(evt);
            }
        });
        jPanelPayrollBox.add(jTextFieldSearchById);
        jTextFieldSearchById.setBounds(780, 30, 260, 40);

        jLabelilterByPayPeriod.setBackground(new java.awt.Color(255, 255, 255));
        jLabelilterByPayPeriod.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelilterByPayPeriod.setForeground(new java.awt.Color(0, 0, 0));
        jLabelilterByPayPeriod.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelilterByPayPeriod.setText("Filter by Pay Period :");
        jPanelPayrollBox.add(jLabelilterByPayPeriod);
        jLabelilterByPayPeriod.setBounds(600, 70, 180, 40);

        jComboBoxFilterByPayPeriod.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jComboBoxFilterByPayPeriod.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select", "January 2024", "February 2024", "March 2024", "April 2024", "May 2024", "June 2024", "July 2024", "August 2024", "September 2024", "October 2024", "November 2024", "December 2024" }));
        jComboBoxFilterByPayPeriod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxFilterByPayPeriodActionPerformed(evt);
            }
        });
        jPanelPayrollBox.add(jComboBoxFilterByPayPeriod);
        jComboBoxFilterByPayPeriod.setBounds(780, 70, 260, 40);

        jPanel1.add(jPanelPayrollBox);
        jPanelPayrollBox.setBounds(30, 100, 1060, 650);

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBoxYearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxYearActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxYearActionPerformed

    private void jComboBoxMonthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxMonthActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxMonthActionPerformed

    private void jButtonGeneratePayslipActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGeneratePayslipActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonGeneratePayslipActionPerformed

    private void jButtonBatchProcessActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBatchProcessActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonBatchProcessActionPerformed

    private void jTextFieldSearchByIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldSearchByIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldSearchByIdActionPerformed

    private void jTextFieldSearchByIdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldSearchByIdKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldSearchByIdKeyPressed

    private void jComboBoxFilterByPayPeriodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxFilterByPayPeriodActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxFilterByPayPeriodActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonBatchProcess;
    private javax.swing.JButton jButtonGeneratePayslip;
    private javax.swing.JComboBox<String> jComboBoxFilterByPayPeriod;
    private javax.swing.JComboBox<String> jComboBoxMonth;
    private javax.swing.JComboBox<String> jComboBoxYear;
    private javax.swing.JLabel jLabelHelloAdmin;
    private javax.swing.JLabel jLabelPayrollRecords;
    private javax.swing.JLabel jLabelPayrollSmall;
    private javax.swing.JLabel jLabelSeachByEmployeeID;
    private javax.swing.JLabel jLabelSelectMonth;
    private javax.swing.JLabel jLabelSelectYear;
    private javax.swing.JLabel jLabelilterByPayPeriod;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanelPayrollBox;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableEmployeeRecords;
    private javax.swing.JTextField jTextFieldSearchById;
    // End of variables declaration//GEN-END:variables
}