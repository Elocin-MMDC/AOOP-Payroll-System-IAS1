package gui.admin.finance;

import java.awt.CardLayout;

public class ReportsPanel extends javax.swing.JPanel {

    private final AdminFinancePortal financePortal;
    
    public ReportsPanel(AdminFinancePortal financePortal) {
        this.financePortal = financePortal;
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabelHelloAdmin = new javax.swing.JLabel();
        jLabelReportsSmall = new javax.swing.JLabel();
        jPanelPayrollBox = new javax.swing.JPanel();
        jComboBoxFilterByPayPeriod = new javax.swing.JComboBox<>();
        jLabelilterByPayPeriod = new javax.swing.JLabel();
        jLabelPayrollRecords = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableEmployeeRecords = new javax.swing.JTable();
        jButtonGenerateReport = new javax.swing.JButton();

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

        jLabelReportsSmall.setText("Reports");
        jPanel1.add(jLabelReportsSmall);
        jLabelReportsSmall.setBounds(30, 60, 100, 16);

        jPanelPayrollBox.setBackground(new java.awt.Color(255, 255, 255));
        jPanelPayrollBox.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanelPayrollBox.setLayout(null);

        jComboBoxFilterByPayPeriod.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jComboBoxFilterByPayPeriod.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select", "January 2024", "February 2024", "March 2024", "April 2024", "May 2024", "June 2024", "July 2024", "August 2024", "September 2024", "October 2024", "November 2024", "December 2024" }));
        jComboBoxFilterByPayPeriod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxFilterByPayPeriodActionPerformed(evt);
            }
        });
        jPanelPayrollBox.add(jComboBoxFilterByPayPeriod);
        jComboBoxFilterByPayPeriod.setBounds(170, 30, 260, 40);

        jLabelilterByPayPeriod.setBackground(new java.awt.Color(255, 255, 255));
        jLabelilterByPayPeriod.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelilterByPayPeriod.setForeground(new java.awt.Color(0, 0, 0));
        jLabelilterByPayPeriod.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelilterByPayPeriod.setText("Select Pay Period :");
        jPanelPayrollBox.add(jLabelilterByPayPeriod);
        jLabelilterByPayPeriod.setBounds(20, 30, 140, 40);

        jLabelPayrollRecords.setBackground(new java.awt.Color(255, 255, 255));
        jLabelPayrollRecords.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelPayrollRecords.setForeground(new java.awt.Color(0, 0, 0));
        jLabelPayrollRecords.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelPayrollRecords.setText("PAYROLL RECORDS");
        jPanelPayrollBox.add(jLabelPayrollRecords);
        jLabelPayrollRecords.setBounds(20, 90, 180, 40);

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
        jScrollPane1.setBounds(20, 130, 1020, 420);

        jButtonGenerateReport.setBackground(new java.awt.Color(0, 135, 0));
        jButtonGenerateReport.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButtonGenerateReport.setForeground(new java.awt.Color(255, 255, 255));
        jButtonGenerateReport.setText("Generate Report");
        jButtonGenerateReport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGenerateReportActionPerformed(evt);
            }
        });
        jPanelPayrollBox.add(jButtonGenerateReport);
        jButtonGenerateReport.setBounds(780, 580, 260, 40);

        jPanel1.add(jPanelPayrollBox);
        jPanelPayrollBox.setBounds(30, 100, 1060, 650);

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBoxFilterByPayPeriodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxFilterByPayPeriodActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxFilterByPayPeriodActionPerformed

    private void jButtonGenerateReportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGenerateReportActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonGenerateReportActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonGenerateReport;
    private javax.swing.JComboBox<String> jComboBoxFilterByPayPeriod;
    private javax.swing.JLabel jLabelHelloAdmin;
    private javax.swing.JLabel jLabelPayrollRecords;
    private javax.swing.JLabel jLabelReportsSmall;
    private javax.swing.JLabel jLabelilterByPayPeriod;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanelPayrollBox;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableEmployeeRecords;
    // End of variables declaration//GEN-END:variables
}