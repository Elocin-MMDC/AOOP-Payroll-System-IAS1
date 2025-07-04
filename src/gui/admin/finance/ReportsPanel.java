package gui.admin.finance;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.swing.table.DefaultTableModel;
import model.pojo.MonthlyPayrollSummaryReportView;
import service.PayrollService;
import service.ReportGenerationService;
import util.UIUtil;

public class ReportsPanel extends javax.swing.JPanel {

    private final AdminFinancePortal financePortal;
    private final PayrollService payrollService;
    private final ReportGenerationService reportGenerationService;
    
    public ReportsPanel(AdminFinancePortal financePortal) {
        this.financePortal = financePortal;
        this.payrollService = new PayrollService();
        this.reportGenerationService = new ReportGenerationService();
        initComponents();
        UIUtil.setGreeting(jLabelHelloAdmin, "Admin");
        loadPayPeriodComboBox();
        loadReport();
    }
    
    protected final void loadPayPeriodComboBox() {
        Map<String, LocalDate[]> periodMap = payrollService.getFormattedPayPeriods();
        jComboBoxPayPeriod.removeAllItems();
        jComboBoxPayPeriod.addItem("Select");
        for (String label : periodMap.keySet()) {
            jComboBoxPayPeriod.addItem(label);
        }
        jComboBoxPayPeriod.addActionListener(e -> loadReport());
    }
    
    private void loadReport() {
        String selectedPayPeriod = (String) jComboBoxPayPeriod.getSelectedItem();

        String[] cols = {
            "Employee ID",
            "Gross Income",
            "SSS",
            "PhilHealth",
            "PagIbig",
            "Withholding Tax",
            "Net Income"
        };

        UIUtil.styleTable(jTablePayrollReport, cols);
        DefaultTableModel model = (DefaultTableModel) jTablePayrollReport.getModel();
        model.setRowCount(0);

        if (selectedPayPeriod == null || selectedPayPeriod.equals("Select")) {
            model.setRowCount(0);
            return;
        }

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM yyyy", Locale.ENGLISH);
            YearMonth yearMonth = YearMonth.parse(selectedPayPeriod, formatter);

            LocalDate payStartDate = yearMonth.atDay(1);
            LocalDate payEndDate = yearMonth.atEndOfMonth();

            List<MonthlyPayrollSummaryReportView> report = payrollService.getReport(payStartDate, payEndDate);

            for (MonthlyPayrollSummaryReportView pr : report) {
                model.addRow(new Object[]{
                    pr.getEmployeeID(),
                    pr.getGrossIncome(),
                    pr.getSssContribution(),
                    pr.getPhilHealthContribution(),
                    pr.getPagIbigContribution(),
                    pr.getWithholdingTax(),
                    pr.getNetIncome()
                });
            }
        } catch (DateTimeParseException e) {
            UIUtil.showErrorMessage(this, "Invalid pay period format: " + selectedPayPeriod, "Parse Error");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabelHelloAdmin = new javax.swing.JLabel();
        jLabelReportsSmall = new javax.swing.JLabel();
        jPanelPayrollBox = new javax.swing.JPanel();
        jComboBoxPayPeriod = new javax.swing.JComboBox<>();
        jLabelSelectByPayPeriod = new javax.swing.JLabel();
        jLabelPayrollRecords = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTablePayrollReport = new javax.swing.JTable();
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
        jLabelHelloAdmin.setBounds(30, 30, 570, 29);

        jLabelReportsSmall.setText("Reports");
        jPanel1.add(jLabelReportsSmall);
        jLabelReportsSmall.setBounds(30, 60, 100, 16);

        jPanelPayrollBox.setBackground(new java.awt.Color(255, 255, 255));
        jPanelPayrollBox.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanelPayrollBox.setLayout(null);

        jComboBoxPayPeriod.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jComboBoxPayPeriod.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select" }));
        jComboBoxPayPeriod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxPayPeriodActionPerformed(evt);
            }
        });
        jPanelPayrollBox.add(jComboBoxPayPeriod);
        jComboBoxPayPeriod.setBounds(170, 30, 260, 40);

        jLabelSelectByPayPeriod.setBackground(new java.awt.Color(255, 255, 255));
        jLabelSelectByPayPeriod.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelSelectByPayPeriod.setForeground(new java.awt.Color(0, 0, 0));
        jLabelSelectByPayPeriod.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelSelectByPayPeriod.setText("Select Pay Period :");
        jPanelPayrollBox.add(jLabelSelectByPayPeriod);
        jLabelSelectByPayPeriod.setBounds(20, 30, 140, 40);

        jLabelPayrollRecords.setBackground(new java.awt.Color(255, 255, 255));
        jLabelPayrollRecords.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelPayrollRecords.setForeground(new java.awt.Color(0, 0, 0));
        jLabelPayrollRecords.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelPayrollRecords.setText("PAYROLL RECORDS");
        jPanelPayrollBox.add(jLabelPayrollRecords);
        jLabelPayrollRecords.setBounds(20, 90, 180, 40);

        jTablePayrollReport.setAutoCreateRowSorter(true);
        jTablePayrollReport.setModel(new javax.swing.table.DefaultTableModel(
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
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
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
                "Employee ID", "Gross Income", "SSS", "PhilHealth", "PagIbig", "Withholding Tax", "Net Income"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class
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
        jTablePayrollReport.setFocusable(false);
        jTablePayrollReport.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTablePayrollReport.setShowGrid(true);
        jTablePayrollReport.getTableHeader().setResizingAllowed(false);
        jTablePayrollReport.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jTablePayrollReport);

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

    private void jButtonGenerateReportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGenerateReportActionPerformed
        String selectedPeriod = (String) jComboBoxPayPeriod.getSelectedItem();

        if (selectedPeriod == null || selectedPeriod.equals("Select")) {
            UIUtil.showErrorMessage(this, "Please select a pay period.", "No Period Selected");
            return;
        }

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM yyyy", Locale.ENGLISH);
            YearMonth yearMonth = YearMonth.parse(selectedPeriod, formatter);
            int payMonth = yearMonth.getMonthValue();
            int payYear = yearMonth.getYear();

            ReportGenerationService reportService = new ReportGenerationService();
            reportService.generatePayrollSummaryReport(payMonth, payYear);

        } catch (Exception ex) {
            ex.printStackTrace();
            UIUtil.showErrorMessage(this, "Failed to generate report:\n" + ex.getMessage(), "Error");
        }
    }//GEN-LAST:event_jButtonGenerateReportActionPerformed

    private void jComboBoxPayPeriodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxPayPeriodActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxPayPeriodActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonGenerateReport;
    private javax.swing.JComboBox<String> jComboBoxPayPeriod;
    private javax.swing.JLabel jLabelHelloAdmin;
    private javax.swing.JLabel jLabelPayrollRecords;
    private javax.swing.JLabel jLabelReportsSmall;
    private javax.swing.JLabel jLabelSelectByPayPeriod;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanelPayrollBox;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTablePayrollReport;
    // End of variables declaration//GEN-END:variables
}