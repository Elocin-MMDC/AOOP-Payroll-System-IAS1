package gui.employee;

import java.util.List;
import javax.swing.table.DefaultTableModel;
import model.pojo.Payslip;
import service.PayrollService;
import service.ReportService;
import util.Session;
import util.UIUtil;

public class PayslipPanel extends javax.swing.JPanel {
    
    private final EmployeePortal employeePortal;
    private final PayrollService payrollService;
    private final ReportService reportService;

    public PayslipPanel(EmployeePortal employeePortal) {
        this.employeePortal = employeePortal;
        this.payrollService = new PayrollService();
        this.reportService = new ReportService();
        initComponents();
        UIUtil.setGreeting(jLabelHelloEmployee, "Employee");
        loadPayslipRecords();
    }
    
    public final void loadPayslipRecords() {
        int empId = Session.getCurrentUser().getEmployeeID();
        List<Payslip> payslips = payrollService.getAllPayslipRecords(empId);

        String[] cols = {
            "Payslip ID",
            "Pay Start Date",
            "Pay End Date",
            "Gross Income",
            "Total Deductions",
            "Total Benefits",
            "Net Income"
        };

        UIUtil.styleTable(jTablePayslipRecords, cols);
        DefaultTableModel model = (DefaultTableModel) jTablePayslipRecords.getModel();
        model.setRowCount(0);
        
        for (Payslip p : payslips) {
            model.addRow(new Object[]{
                p.getPayslipID(),
                p.getPayStartDate(),
                p.getPayEndDate(),
                p.getGrossIncome(),
                p.getTotalDeductions(),
                p.getTotalBenefits(),
                p.getNetIncome()
            });
        }
        
        UIUtil.installSearchFilter(jTablePayslipRecords, jTextFieldSearch, 0, 1, 2, 3, 4, 5, 6);
    }
    
    private void generatePayslip() {
        int row = jTablePayslipRecords.getSelectedRow();
        if (row == -1) {
            UIUtil.showErrorMessage(this, "Please select a payslip.", "No Selection");
            return;
        }

        int payslipID = (int) jTablePayslipRecords.getValueAt(row, 0);

        reportService.generatePayslip(payslipID);
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
        jTablePayslipRecords = new javax.swing.JTable();
        jButtonGeneratePayslip = new javax.swing.JButton();
        jTextFieldSearch = new javax.swing.JTextField();
        jLabelSeach = new javax.swing.JLabel();

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
        jLabelHelloEmployee.setBounds(30, 30, 630, 29);

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

        jTablePayslipRecords.setAutoCreateRowSorter(true);
        jTablePayslipRecords.setModel(new javax.swing.table.DefaultTableModel(
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
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Payslip ID", "Pay Start Date", "Pay End Date", "Gross Income", "Total Deductions", "Total Benefits", "Net Income"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class
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
        jTablePayslipRecords.setFocusable(false);
        jTablePayslipRecords.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTablePayslipRecords.setShowGrid(true);
        jTablePayslipRecords.getTableHeader().setResizingAllowed(false);
        jTablePayslipRecords.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jTablePayslipRecords);

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
        jPanelPayslipBox.add(jTextFieldSearch);
        jTextFieldSearch.setBounds(100, 30, 260, 40);

        jLabelSeach.setBackground(new java.awt.Color(255, 255, 255));
        jLabelSeach.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelSeach.setForeground(new java.awt.Color(0, 0, 0));
        jLabelSeach.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelSeach.setText("Search :");
        jPanelPayslipBox.add(jLabelSeach);
        jLabelSeach.setBounds(20, 30, 80, 40);

        jPanel1.add(jPanelPayslipBox);
        jPanelPayslipBox.setBounds(30, 110, 1060, 640);

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonGeneratePayslipActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGeneratePayslipActionPerformed
        // TODO add your handling code here:
        generatePayslip();
    }//GEN-LAST:event_jButtonGeneratePayslipActionPerformed

    private void jTextFieldSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldSearchActionPerformed

    private void jTextFieldSearchKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldSearchKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldSearchKeyPressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonGeneratePayslip;
    private javax.swing.JLabel jLabelHelloEmployee;
    private javax.swing.JLabel jLabelPayrollRecords;
    private javax.swing.JLabel jLabelPayslipSmall;
    private javax.swing.JLabel jLabelSeach;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanelPayslipBox;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTablePayslipRecords;
    private javax.swing.JTextField jTextFieldSearch;
    // End of variables declaration//GEN-END:variables
}