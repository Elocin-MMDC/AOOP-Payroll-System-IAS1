package gui.admin.finance;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import model.pojo.Payslip;
import service.PayrollService;
import service.ReportService;
import util.Session;
import util.UIUtil;

public class PayrollPanel extends javax.swing.JPanel {

    private final AdminFinancePortal financePortal;
    private final PayrollService payrollService;
    private final ReportService reportService;
    
    public PayrollPanel(AdminFinancePortal financePortal) {
        this.financePortal = financePortal;
        this.payrollService = new PayrollService();
        this.reportService = new ReportService();
        initComponents();
        UIUtil.setGreeting(jLabelHelloAdmin, "Admin");
        loadPayrollRecords();
    }
    
    public final void loadPayrollRecords() {
        List<Payslip> payslips = payrollService.getAllPayrollRecords();

        String[] cols = {
            "Payslip ID",
            "Employee ID",
            "Pay Start Date",
            "Pay End Date",
            "Gross Income",
            "Total Deductions",
            "Total Benefits",
            "Net Income"
        };

        UIUtil.styleTable(jTablePayrollRecords, cols);

        DefaultTableModel model = (DefaultTableModel) jTablePayrollRecords.getModel();
        model.setRowCount(0);

        for (Payslip p : payslips) {
            model.addRow(new Object[]{
                p.getPayslipID(),
                p.getEmployeeID(),
                p.getPayStartDate(),
                p.getPayEndDate(),
                p.getGrossIncome(),
                p.getTotalDeductions(),
                p.getTotalBenefits(),
                p.getNetIncome()
            });
        }
        UIUtil.installSearchFilter(jTablePayrollRecords, jTextFieldSearch, 0, 1, 2, 3, 4, 5, 6);
    }
    
    private void runBatchProcess() {
        int selectedMonth = jComboBoxMonth.getSelectedIndex();
        if (selectedMonth == 0) {
            UIUtil.showErrorMessage(this, "Please select a valid month.", "Invalid Input");
            return;
        }

        String selectedYearStr = (String) jComboBoxYear.getSelectedItem();
        if (selectedYearStr.equals("Select")) {
            UIUtil.showErrorMessage(this, "Please select a valid year.", "Invalid Input");
            return;
        }

        int selectedYear;
        try {
            selectedYear = Integer.parseInt(selectedYearStr);
        } catch (NumberFormatException e) {
            UIUtil.showErrorMessage(this, "Invalid year format. Please select a proper year.", "Invalid Input");
            return;
        }

        LocalDate payStartDate = LocalDate.of(selectedYear, selectedMonth, 1);
        LocalDate payEndDate = payStartDate.with(TemporalAdjusters.lastDayOfMonth());

        int processedByUserID = Session.getCurrentUser().getUserID();
        try {
            boolean success = payrollService.runBatchPayrollProcess(processedByUserID, payStartDate, payEndDate);

            if (success) {
                loadPayrollRecords();
                financePortal.getReportsPanel().loadPayPeriodComboBox();
                UIUtil.showInfoMessage(this, "Payroll batch processed successfully.", "Success");
            }
        } catch (Exception ex) {
            UIUtil.showErrorMessage(this, ex.getMessage(), "Error");
        }
    }
    
    private void generatePayslip() {
        int row = jTablePayrollRecords.getSelectedRow();
        if (row == -1) {
            UIUtil.showErrorMessage(this, "Please select a payslip.", "No Selection");
            return;
        }

        int payslipID = (int) jTablePayrollRecords.getValueAt(row, 0);

        reportService.generatePayslip(payslipID);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabelHelloAdmin = new javax.swing.JLabel();
        jLabelPayrollSmall = new javax.swing.JLabel();
        jPanelPayrollBox = new javax.swing.JPanel();
        jLabelSelectYear = new javax.swing.JLabel();
        jComboBoxYear = new javax.swing.JComboBox<>();
        jButtonGeneratePayslip = new javax.swing.JButton();
        jButtonBatchProcess = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTablePayrollRecords = new javax.swing.JTable();
        jLabelPayrollRecords = new javax.swing.JLabel();
        jLabelSelectMonth = new javax.swing.JLabel();
        jComboBoxMonth = new javax.swing.JComboBox<>();
        jTextFieldSearch = new javax.swing.JTextField();
        jLabelSeach = new javax.swing.JLabel();

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
        jLabelHelloAdmin.setBounds(30, 30, 550, 29);

        jLabelPayrollSmall.setText("Payroll");
        jPanel1.add(jLabelPayrollSmall);
        jLabelPayrollSmall.setBounds(30, 60, 100, 16);

        jPanelPayrollBox.setBackground(new java.awt.Color(255, 255, 255));
        jPanelPayrollBox.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanelPayrollBox.setLayout(null);

        jLabelSelectYear.setBackground(new java.awt.Color(255, 255, 255));
        jLabelSelectYear.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelSelectYear.setForeground(new java.awt.Color(0, 0, 0));
        jLabelSelectYear.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelSelectYear.setText("Select Year :");
        jPanelPayrollBox.add(jLabelSelectYear);
        jLabelSelectYear.setBounds(630, 70, 150, 40);

        jComboBoxYear.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jComboBoxYear.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select", "2024", "2025" }));
        jComboBoxYear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxYearActionPerformed(evt);
            }
        });
        jPanelPayrollBox.add(jComboBoxYear);
        jComboBoxYear.setBounds(780, 70, 260, 40);

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
        jButtonBatchProcess.setBounds(780, 110, 260, 40);

        jTablePayrollRecords.setAutoCreateRowSorter(true);
        jTablePayrollRecords.setModel(new javax.swing.table.DefaultTableModel(
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
                "Payslip ID", "Employee ID", "Pay Start Date", "Pay End Date", "Gross Income", "Total Deductions", "Total Benefits", "Net Income"
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
        jTablePayrollRecords.setFocusable(false);
        jTablePayrollRecords.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTablePayrollRecords.setShowGrid(true);
        jTablePayrollRecords.getTableHeader().setResizingAllowed(false);
        jTablePayrollRecords.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jTablePayrollRecords);

        jPanelPayrollBox.add(jScrollPane1);
        jScrollPane1.setBounds(20, 210, 1020, 340);

        jLabelPayrollRecords.setBackground(new java.awt.Color(255, 255, 255));
        jLabelPayrollRecords.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelPayrollRecords.setForeground(new java.awt.Color(0, 0, 0));
        jLabelPayrollRecords.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelPayrollRecords.setText("PAYROLL RECORDS");
        jPanelPayrollBox.add(jLabelPayrollRecords);
        jLabelPayrollRecords.setBounds(20, 170, 180, 40);

        jLabelSelectMonth.setBackground(new java.awt.Color(255, 255, 255));
        jLabelSelectMonth.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelSelectMonth.setForeground(new java.awt.Color(0, 0, 0));
        jLabelSelectMonth.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelSelectMonth.setText("Select Month :");
        jPanelPayrollBox.add(jLabelSelectMonth);
        jLabelSelectMonth.setBounds(630, 30, 150, 40);

        jComboBoxMonth.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jComboBoxMonth.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select", "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" }));
        jComboBoxMonth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxMonthActionPerformed(evt);
            }
        });
        jPanelPayrollBox.add(jComboBoxMonth);
        jComboBoxMonth.setBounds(780, 30, 260, 40);

        jTextFieldSearch.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        jTextFieldSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldSearchActionPerformed(evt);
            }
        });
        jTextFieldSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldSearchKeyPressed(evt);
            }
        });
        jPanelPayrollBox.add(jTextFieldSearch);
        jTextFieldSearch.setBounds(100, 30, 260, 40);

        jLabelSeach.setBackground(new java.awt.Color(255, 255, 255));
        jLabelSeach.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelSeach.setForeground(new java.awt.Color(0, 0, 0));
        jLabelSeach.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelSeach.setText("Search :");
        jPanelPayrollBox.add(jLabelSeach);
        jLabelSeach.setBounds(20, 30, 80, 40);

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
        generatePayslip();
    }//GEN-LAST:event_jButtonGeneratePayslipActionPerformed

    private void jButtonBatchProcessActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBatchProcessActionPerformed
        // TODO add your handling code here:
        runBatchProcess();
    }//GEN-LAST:event_jButtonBatchProcessActionPerformed

    private void jTextFieldSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldSearchActionPerformed

    private void jTextFieldSearchKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldSearchKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldSearchKeyPressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonBatchProcess;
    private javax.swing.JButton jButtonGeneratePayslip;
    private javax.swing.JComboBox<String> jComboBoxMonth;
    private javax.swing.JComboBox<String> jComboBoxYear;
    private javax.swing.JLabel jLabelHelloAdmin;
    private javax.swing.JLabel jLabelPayrollRecords;
    private javax.swing.JLabel jLabelPayrollSmall;
    private javax.swing.JLabel jLabelSeach;
    private javax.swing.JLabel jLabelSelectMonth;
    private javax.swing.JLabel jLabelSelectYear;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanelPayrollBox;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTablePayrollRecords;
    private javax.swing.JTextField jTextFieldSearch;
    // End of variables declaration//GEN-END:variables
}