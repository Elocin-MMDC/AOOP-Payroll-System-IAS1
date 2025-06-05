package gui.employee;

import java.awt.CardLayout;

public class PayslipPanel extends javax.swing.JPanel {
    
    private EmployeePortal employeePortal;

    public PayslipPanel(EmployeePortal employeePortal) {
        this.employeePortal = employeePortal;
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabelHelloEmployee = new javax.swing.JLabel();
        jLabelPayslipSmall = new javax.swing.JLabel();
        jPanelPayslipBox = new javax.swing.JPanel();
        jLabelPayrollRecords = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableEmployeeRecords = new javax.swing.JTable();
        jButtonGeneratePayslip = new javax.swing.JButton();
        jLabelilterByPayPeriod = new javax.swing.JLabel();
        jComboBoxFilterByPayPeriod = new javax.swing.JComboBox<>();

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

        jLabelPayslipSmall.setText("Payslip");
        jPanel1.add(jLabelPayslipSmall);
        jLabelPayslipSmall.setBounds(30, 60, 180, 16);

        jPanelPayslipBox.setBackground(new java.awt.Color(255, 255, 255));
        jPanelPayslipBox.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanelPayslipBox.setLayout(null);

        jLabelPayrollRecords.setBackground(new java.awt.Color(255, 255, 255));
        jLabelPayrollRecords.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelPayrollRecords.setForeground(new java.awt.Color(0, 0, 0));
        jLabelPayrollRecords.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelPayrollRecords.setText("PAYROLL RECORDS");
        jPanelPayslipBox.add(jLabelPayrollRecords);
        jLabelPayrollRecords.setBounds(20, 110, 180, 40);

        jTableEmployeeRecords.setAutoCreateRowSorter(true);
        jTableEmployeeRecords.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Payslip ID", "Pay Period", "Gross Income", "Total Deductions", "Total Allowances", "Net Income"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
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

        jPanelPayslipBox.add(jScrollPane1);
        jScrollPane1.setBounds(20, 150, 1020, 390);

        jButtonGeneratePayslip.setBackground(new java.awt.Color(0, 135, 0));
        jButtonGeneratePayslip.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButtonGeneratePayslip.setForeground(new java.awt.Color(255, 255, 255));
        jButtonGeneratePayslip.setText("Generate Payslip");
        jButtonGeneratePayslip.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGeneratePayslipActionPerformed(evt);
            }
        });
        jPanelPayslipBox.add(jButtonGeneratePayslip);
        jButtonGeneratePayslip.setBounds(780, 570, 260, 40);

        jLabelilterByPayPeriod.setBackground(new java.awt.Color(255, 255, 255));
        jLabelilterByPayPeriod.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelilterByPayPeriod.setForeground(new java.awt.Color(0, 0, 0));
        jLabelilterByPayPeriod.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelilterByPayPeriod.setText("Select Pay Period :");
        jPanelPayslipBox.add(jLabelilterByPayPeriod);
        jLabelilterByPayPeriod.setBounds(20, 40, 140, 40);

        jComboBoxFilterByPayPeriod.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jComboBoxFilterByPayPeriod.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select", "January 2024", "February 2024", "March 2024", "April 2024", "May 2024", "June 2024", "July 2024", "August 2024", "September 2024", "October 2024", "November 2024", "December 2024" }));
        jComboBoxFilterByPayPeriod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxFilterByPayPeriodActionPerformed(evt);
            }
        });
        jPanelPayslipBox.add(jComboBoxFilterByPayPeriod);
        jComboBoxFilterByPayPeriod.setBounds(180, 40, 250, 40);

        jPanel1.add(jPanelPayslipBox);
        jPanelPayslipBox.setBounds(30, 110, 1060, 640);

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonGeneratePayslipActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGeneratePayslipActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonGeneratePayslipActionPerformed

    private void jComboBoxFilterByPayPeriodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxFilterByPayPeriodActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxFilterByPayPeriodActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonGeneratePayslip;
    private javax.swing.JComboBox<String> jComboBoxFilterByPayPeriod;
    private javax.swing.JLabel jLabelHelloEmployee;
    private javax.swing.JLabel jLabelPayrollRecords;
    private javax.swing.JLabel jLabelPayslipSmall;
    private javax.swing.JLabel jLabelilterByPayPeriod;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanelPayslipBox;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableEmployeeRecords;
    // End of variables declaration//GEN-END:variables
}