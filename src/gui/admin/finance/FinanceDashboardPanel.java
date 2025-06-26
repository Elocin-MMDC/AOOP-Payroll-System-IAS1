package gui.admin.finance;

import javax.swing.SwingUtilities;
import service.DashboardService;
import util.PasswordUtil;
import util.UIUtil;

public class FinanceDashboardPanel extends javax.swing.JPanel {

    private final AdminFinancePortal financePortal;
    private final DashboardService dashboardService;

    public FinanceDashboardPanel(AdminFinancePortal financePortal) {
        this.financePortal = financePortal;
        this.dashboardService = new DashboardService();
        initComponents();
        UIUtil.setGreeting(jLabelHelloAdmin, "Admin");
        UIUtil.startClock(jLabelDateAndTime, "MMMM dd, yyyy HH:mm:ss");
        loadMetrics();
    }
    
    protected final void loadMetrics() {
        try {
            jLabelEmployeeCount.setText(String.valueOf(dashboardService.getTotalEmployees()));
            jLabelDepartmentCount.setText(String.valueOf(dashboardService.getTotalDepartments()));
            jLabelPendingFinanceTicketCount.setText(String.valueOf(dashboardService.getTotalPendingTicketsForTeam("Finance"))
            );
        } catch (Exception e) {
            UIUtil.showErrorMessage(this, "Failed to retrieve data.", "Error");
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelHRDashboard = new javax.swing.JPanel();
        jPanelDepartmentsBox = new javax.swing.JPanel();
        jLabelTotalDepartments = new javax.swing.JLabel();
        jLabelDepartmentCount = new javax.swing.JLabel();
        jPanelDateAndTimeBox = new javax.swing.JPanel();
        jLabelDateAndTime = new javax.swing.JLabel();
        jLabelDateAndTIme = new javax.swing.JLabel();
        jPanelEmployeesCountBox = new javax.swing.JPanel();
        jLabelTotalEmployeesCount = new javax.swing.JLabel();
        jLabelEmployeeCount = new javax.swing.JLabel();
        jPanelPendingTickets = new javax.swing.JPanel();
        jLabelPendingFinanceTicketCount = new javax.swing.JLabel();
        jLabelPendingFinanceTickets = new javax.swing.JLabel();
        jLabelHelloAdmin = new javax.swing.JLabel();
        jLabelDashboardSmall = new javax.swing.JLabel();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanelHRDashboard.setBackground(new java.awt.Color(255, 255, 255));
        jPanelHRDashboard.setPreferredSize(new java.awt.Dimension(1120, 750));
        jPanelHRDashboard.setLayout(null);

        jPanelDepartmentsBox.setBackground(new java.awt.Color(255, 255, 255));
        jPanelDepartmentsBox.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanelDepartmentsBox.setLayout(null);

        jLabelTotalDepartments.setBackground(new java.awt.Color(255, 255, 255));
        jLabelTotalDepartments.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabelTotalDepartments.setForeground(new java.awt.Color(0, 0, 0));
        jLabelTotalDepartments.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelTotalDepartments.setText("Total Departments");
        jPanelDepartmentsBox.add(jLabelTotalDepartments);
        jLabelTotalDepartments.setBounds(20, 0, 240, 50);

        jLabelDepartmentCount.setBackground(new java.awt.Color(255, 255, 255));
        jLabelDepartmentCount.setFont(new java.awt.Font("Segoe UI", 1, 56)); // NOI18N
        jLabelDepartmentCount.setForeground(new java.awt.Color(0, 0, 0));
        jLabelDepartmentCount.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelDepartmentCount.setText("0");
        jPanelDepartmentsBox.add(jLabelDepartmentCount);
        jLabelDepartmentCount.setBounds(20, 50, 380, 100);

        jPanelHRDashboard.add(jPanelDepartmentsBox);
        jPanelDepartmentsBox.setBounds(600, 240, 420, 160);

        jPanelDateAndTimeBox.setBackground(new java.awt.Color(255, 255, 255));
        jPanelDateAndTimeBox.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanelDateAndTimeBox.setLayout(null);

        jLabelDateAndTime.setBackground(new java.awt.Color(255, 255, 255));
        jLabelDateAndTime.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabelDateAndTime.setForeground(new java.awt.Color(33, 105, 84));
        jLabelDateAndTime.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelDateAndTime.setText("January 1, 2025 12:00:00");
        jPanelDateAndTimeBox.add(jLabelDateAndTime);
        jLabelDateAndTime.setBounds(20, 60, 380, 60);

        jLabelDateAndTIme.setBackground(new java.awt.Color(255, 255, 255));
        jLabelDateAndTIme.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabelDateAndTIme.setForeground(new java.awt.Color(0, 0, 0));
        jLabelDateAndTIme.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelDateAndTIme.setText("Date and Time");
        jPanelDateAndTimeBox.add(jLabelDateAndTIme);
        jLabelDateAndTIme.setBounds(20, 0, 140, 50);

        jPanelHRDashboard.add(jPanelDateAndTimeBox);
        jPanelDateAndTimeBox.setBounds(90, 440, 420, 160);

        jPanelEmployeesCountBox.setBackground(new java.awt.Color(255, 255, 255));
        jPanelEmployeesCountBox.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanelEmployeesCountBox.setLayout(null);

        jLabelTotalEmployeesCount.setBackground(new java.awt.Color(255, 255, 255));
        jLabelTotalEmployeesCount.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabelTotalEmployeesCount.setForeground(new java.awt.Color(0, 0, 0));
        jLabelTotalEmployeesCount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelTotalEmployeesCount.setText("Total Employees Count");
        jPanelEmployeesCountBox.add(jLabelTotalEmployeesCount);
        jLabelTotalEmployeesCount.setBounds(20, 0, 240, 50);

        jLabelEmployeeCount.setBackground(new java.awt.Color(255, 255, 255));
        jLabelEmployeeCount.setFont(new java.awt.Font("Segoe UI", 1, 56)); // NOI18N
        jLabelEmployeeCount.setForeground(new java.awt.Color(0, 0, 0));
        jLabelEmployeeCount.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelEmployeeCount.setText("0");
        jPanelEmployeesCountBox.add(jLabelEmployeeCount);
        jLabelEmployeeCount.setBounds(20, 50, 380, 100);

        jPanelHRDashboard.add(jPanelEmployeesCountBox);
        jPanelEmployeesCountBox.setBounds(90, 240, 420, 160);

        jPanelPendingTickets.setBackground(new java.awt.Color(255, 255, 255));
        jPanelPendingTickets.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanelPendingTickets.setLayout(null);

        jLabelPendingFinanceTicketCount.setBackground(new java.awt.Color(255, 255, 255));
        jLabelPendingFinanceTicketCount.setFont(new java.awt.Font("Segoe UI", 1, 56)); // NOI18N
        jLabelPendingFinanceTicketCount.setForeground(new java.awt.Color(0, 0, 0));
        jLabelPendingFinanceTicketCount.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelPendingFinanceTicketCount.setText("0");
        jPanelPendingTickets.add(jLabelPendingFinanceTicketCount);
        jLabelPendingFinanceTicketCount.setBounds(20, 50, 380, 100);

        jLabelPendingFinanceTickets.setBackground(new java.awt.Color(255, 255, 255));
        jLabelPendingFinanceTickets.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabelPendingFinanceTickets.setForeground(new java.awt.Color(0, 0, 0));
        jLabelPendingFinanceTickets.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelPendingFinanceTickets.setText("Pending Finance Tickets");
        jPanelPendingTickets.add(jLabelPendingFinanceTickets);
        jLabelPendingFinanceTickets.setBounds(20, 0, 240, 50);

        jPanelHRDashboard.add(jPanelPendingTickets);
        jPanelPendingTickets.setBounds(600, 440, 420, 160);

        jLabelHelloAdmin.setBackground(new java.awt.Color(255, 255, 255));
        jLabelHelloAdmin.setFont(new java.awt.Font("Segoe UI", 1, 21)); // NOI18N
        jLabelHelloAdmin.setForeground(new java.awt.Color(0, 0, 0));
        jLabelHelloAdmin.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelHelloAdmin.setText("Hello, Admin!");
        jPanelHRDashboard.add(jLabelHelloAdmin);
        jLabelHelloAdmin.setBounds(30, 30, 520, 29);

        jLabelDashboardSmall.setText("Finance Dashboard");
        jPanelHRDashboard.add(jLabelDashboardSmall);
        jLabelDashboardSmall.setBounds(30, 60, 110, 16);

        add(jPanelHRDashboard, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabelDashboardSmall;
    private javax.swing.JLabel jLabelDateAndTIme;
    private javax.swing.JLabel jLabelDateAndTime;
    private javax.swing.JLabel jLabelDepartmentCount;
    private javax.swing.JLabel jLabelEmployeeCount;
    private javax.swing.JLabel jLabelHelloAdmin;
    private javax.swing.JLabel jLabelPendingFinanceTicketCount;
    private javax.swing.JLabel jLabelPendingFinanceTickets;
    private javax.swing.JLabel jLabelTotalDepartments;
    private javax.swing.JLabel jLabelTotalEmployeesCount;
    private javax.swing.JPanel jPanelDateAndTimeBox;
    private javax.swing.JPanel jPanelDepartmentsBox;
    private javax.swing.JPanel jPanelEmployeesCountBox;
    private javax.swing.JPanel jPanelHRDashboard;
    private javax.swing.JPanel jPanelPendingTickets;
    // End of variables declaration//GEN-END:variables
}