package gui.admin.hr;

import javax.swing.SwingUtilities;
import service.DashboardService;
import util.PasswordUtil;
import util.UIUtil;

public class HRDashboardPanel extends javax.swing.JPanel {
    
    private final AdminHRPortal hrPortal;
    private final DashboardService dashboardService;

    public HRDashboardPanel(AdminHRPortal hrPortal) {
        this.hrPortal = hrPortal;
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
            jLabelPendingLeaveCount.setText(String.valueOf(dashboardService.getTotalPendingLeaves()));
            jLabelPendingOvertimeCount.setText(String.valueOf(dashboardService.getTotalPendingOvertime()));
            jLabelPendingHRTicketCount.setText(String.valueOf(dashboardService.getTotalPendingTicketsForTeam("HR"))
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
        jPanelPendingLeaveBox = new javax.swing.JPanel();
        jLabelPendingLeaveCount = new javax.swing.JLabel();
        jLabelPendingLeaveRequest = new javax.swing.JLabel();
        jPanelPendingOvertimeBox = new javax.swing.JPanel();
        jLabelPendingOvertimeCount = new javax.swing.JLabel();
        jLabelPendingOvertimeRequest = new javax.swing.JLabel();
        jPanelEmployeesCountBox = new javax.swing.JPanel();
        jLabelTotalEmployeesCount = new javax.swing.JLabel();
        jLabelEmployeeCount = new javax.swing.JLabel();
        jPanelPendingTickets = new javax.swing.JPanel();
        jLabelPendingHRTicketCount = new javax.swing.JLabel();
        jLabelPendingHRTickets = new javax.swing.JLabel();
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
        jPanelDepartmentsBox.setBounds(90, 340, 420, 160);

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
        jPanelDateAndTimeBox.setBounds(90, 550, 420, 160);

        jPanelPendingLeaveBox.setBackground(new java.awt.Color(255, 255, 255));
        jPanelPendingLeaveBox.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanelPendingLeaveBox.setLayout(null);

        jLabelPendingLeaveCount.setBackground(new java.awt.Color(255, 255, 255));
        jLabelPendingLeaveCount.setFont(new java.awt.Font("Segoe UI", 1, 56)); // NOI18N
        jLabelPendingLeaveCount.setForeground(new java.awt.Color(0, 0, 0));
        jLabelPendingLeaveCount.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelPendingLeaveCount.setText("0");
        jPanelPendingLeaveBox.add(jLabelPendingLeaveCount);
        jLabelPendingLeaveCount.setBounds(20, 50, 380, 100);

        jLabelPendingLeaveRequest.setBackground(new java.awt.Color(255, 255, 255));
        jLabelPendingLeaveRequest.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabelPendingLeaveRequest.setForeground(new java.awt.Color(0, 0, 0));
        jLabelPendingLeaveRequest.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelPendingLeaveRequest.setText("Pending Leave Request");
        jPanelPendingLeaveBox.add(jLabelPendingLeaveRequest);
        jLabelPendingLeaveRequest.setBounds(20, 0, 240, 50);

        jPanelHRDashboard.add(jPanelPendingLeaveBox);
        jPanelPendingLeaveBox.setBounds(600, 140, 420, 160);

        jPanelPendingOvertimeBox.setBackground(new java.awt.Color(255, 255, 255));
        jPanelPendingOvertimeBox.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanelPendingOvertimeBox.setLayout(null);

        jLabelPendingOvertimeCount.setBackground(new java.awt.Color(255, 255, 255));
        jLabelPendingOvertimeCount.setFont(new java.awt.Font("Segoe UI", 1, 56)); // NOI18N
        jLabelPendingOvertimeCount.setForeground(new java.awt.Color(0, 0, 0));
        jLabelPendingOvertimeCount.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelPendingOvertimeCount.setText("0");
        jPanelPendingOvertimeBox.add(jLabelPendingOvertimeCount);
        jLabelPendingOvertimeCount.setBounds(20, 50, 380, 100);

        jLabelPendingOvertimeRequest.setBackground(new java.awt.Color(255, 255, 255));
        jLabelPendingOvertimeRequest.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabelPendingOvertimeRequest.setForeground(new java.awt.Color(0, 0, 0));
        jLabelPendingOvertimeRequest.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelPendingOvertimeRequest.setText("Pending Overtime Request");
        jPanelPendingOvertimeBox.add(jLabelPendingOvertimeRequest);
        jLabelPendingOvertimeRequest.setBounds(20, 0, 240, 50);

        jPanelHRDashboard.add(jPanelPendingOvertimeBox);
        jPanelPendingOvertimeBox.setBounds(600, 340, 420, 160);

        jPanelEmployeesCountBox.setBackground(new java.awt.Color(255, 255, 255));
        jPanelEmployeesCountBox.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanelEmployeesCountBox.setLayout(null);

        jLabelTotalEmployeesCount.setBackground(new java.awt.Color(255, 255, 255));
        jLabelTotalEmployeesCount.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabelTotalEmployeesCount.setForeground(new java.awt.Color(0, 0, 0));
        jLabelTotalEmployeesCount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelTotalEmployeesCount.setText("Total Employees");
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
        jPanelEmployeesCountBox.setBounds(90, 140, 420, 160);

        jPanelPendingTickets.setBackground(new java.awt.Color(255, 255, 255));
        jPanelPendingTickets.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanelPendingTickets.setLayout(null);

        jLabelPendingHRTicketCount.setBackground(new java.awt.Color(255, 255, 255));
        jLabelPendingHRTicketCount.setFont(new java.awt.Font("Segoe UI", 1, 56)); // NOI18N
        jLabelPendingHRTicketCount.setForeground(new java.awt.Color(0, 0, 0));
        jLabelPendingHRTicketCount.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelPendingHRTicketCount.setText("0");
        jPanelPendingTickets.add(jLabelPendingHRTicketCount);
        jLabelPendingHRTicketCount.setBounds(20, 50, 380, 100);

        jLabelPendingHRTickets.setBackground(new java.awt.Color(255, 255, 255));
        jLabelPendingHRTickets.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabelPendingHRTickets.setForeground(new java.awt.Color(0, 0, 0));
        jLabelPendingHRTickets.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelPendingHRTickets.setText("Pending HR Tickets");
        jPanelPendingTickets.add(jLabelPendingHRTickets);
        jLabelPendingHRTickets.setBounds(20, 0, 240, 50);

        jPanelHRDashboard.add(jPanelPendingTickets);
        jPanelPendingTickets.setBounds(600, 550, 420, 160);

        jLabelHelloAdmin.setBackground(new java.awt.Color(255, 255, 255));
        jLabelHelloAdmin.setFont(new java.awt.Font("Segoe UI", 1, 21)); // NOI18N
        jLabelHelloAdmin.setForeground(new java.awt.Color(0, 0, 0));
        jLabelHelloAdmin.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelHelloAdmin.setText("Hello, Admin!");
        jPanelHRDashboard.add(jLabelHelloAdmin);
        jLabelHelloAdmin.setBounds(30, 30, 530, 29);

        jLabelDashboardSmall.setText("HR Dashboard");
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
    private javax.swing.JLabel jLabelPendingHRTicketCount;
    private javax.swing.JLabel jLabelPendingHRTickets;
    private javax.swing.JLabel jLabelPendingLeaveCount;
    private javax.swing.JLabel jLabelPendingLeaveRequest;
    private javax.swing.JLabel jLabelPendingOvertimeCount;
    private javax.swing.JLabel jLabelPendingOvertimeRequest;
    private javax.swing.JLabel jLabelTotalDepartments;
    private javax.swing.JLabel jLabelTotalEmployeesCount;
    private javax.swing.JPanel jPanelDateAndTimeBox;
    private javax.swing.JPanel jPanelDepartmentsBox;
    private javax.swing.JPanel jPanelEmployeesCountBox;
    private javax.swing.JPanel jPanelHRDashboard;
    private javax.swing.JPanel jPanelPendingLeaveBox;
    private javax.swing.JPanel jPanelPendingOvertimeBox;
    private javax.swing.JPanel jPanelPendingTickets;
    // End of variables declaration//GEN-END:variables
}