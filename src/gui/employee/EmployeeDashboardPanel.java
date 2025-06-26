package gui.employee;

import javax.swing.SwingUtilities;
import model.pojo.EmployeeView;
import service.DashboardService;
import util.PasswordUtil;
import util.UIUtil;

public class EmployeeDashboardPanel extends javax.swing.JPanel {

    private final EmployeePortal employeePortal;
    private final DashboardService dashboardService;

    public EmployeeDashboardPanel(EmployeePortal employeePortal) {
        this.employeePortal = employeePortal;
        this.dashboardService = new DashboardService();
        initComponents();
        UIUtil.setGreeting(jLabelHelloEmployee, "Employee");
        UIUtil.startClock(jLabelDateAndTime, "MMMM dd, yyyy HH:mm:ss");
        loadMetrics();
        checkTemporaryPassword();
    }
    
    protected final void loadMetrics() {
        try {
            EmployeeView profile = dashboardService.getProfile();
            jLabelEmployeeID.setText(String.valueOf(profile.getEmployeeID()));
            jLabelPosition.setText(profile.getPositionTitle());
            jLabelDepartment.setText(profile.getDepartmentName());
            jLabelDepartmentCount.setText(String.valueOf(dashboardService.getTotalDepartments()));
            jLabelPendingLeaveCount.setText(String.valueOf(dashboardService.getTotalPendingLeavesByEmployeeId()));
            jLabelPendingOvertimeCount.setText(String.valueOf(dashboardService.getTotalPendingOvertimeByEmployeeId()));
            jLabelPendingSupportRequest.setText(String.valueOf(dashboardService.getTotalPendingTicketsByEmployeeId()));
        } catch (Exception e) {
            UIUtil.showErrorMessage(this, "Failed to retrieve data.", "Error");
        }
    }
    
    // Warns the user if their current password is still set to the default "temppassword"
    private void checkTemporaryPassword() {
        SwingUtilities.invokeLater(() -> {
            try {
                String pwd = dashboardService.getCurrentUserPassword();
                if (PasswordUtil.sha256Hash("temppassword").equals(pwd)) {
                    UIUtil.showWarningMessage(this, "<html>Your password is currently "
                            + "set to <b>\"temppassword\"</b>.<br>Please go to <b>Profile</b> "
                            + "&gt; <b>Change Password</b> to update<br>it for security.</html>", 
                            "Security Notice");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
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
        jPanelEmployeeDetails = new javax.swing.JPanel();
        jLabelEmployeeNumber5 = new javax.swing.JLabel();
        jLabelPosition1 = new javax.swing.JLabel();
        jLabelDepartment1 = new javax.swing.JLabel();
        jLabelEmployeeID = new javax.swing.JLabel();
        jLabelPosition = new javax.swing.JLabel();
        jLabelDepartment = new javax.swing.JLabel();
        jPanelPendingSupportRequest = new javax.swing.JPanel();
        jLabelPendingSupportRequest = new javax.swing.JLabel();
        jLabelPendingHRTickets = new javax.swing.JLabel();
        jLabelHelloEmployee = new javax.swing.JLabel();
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
        jLabelDateAndTime.setForeground(new java.awt.Color(0, 43, 175));
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

        jPanelEmployeeDetails.setBackground(new java.awt.Color(255, 255, 255));
        jPanelEmployeeDetails.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanelEmployeeDetails.setLayout(null);

        jLabelEmployeeNumber5.setBackground(new java.awt.Color(255, 255, 255));
        jLabelEmployeeNumber5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabelEmployeeNumber5.setForeground(new java.awt.Color(0, 0, 0));
        jLabelEmployeeNumber5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelEmployeeNumber5.setText("Employee ID :");
        jPanelEmployeeDetails.add(jLabelEmployeeNumber5);
        jLabelEmployeeNumber5.setBounds(20, 10, 130, 50);

        jLabelPosition1.setBackground(new java.awt.Color(255, 255, 255));
        jLabelPosition1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabelPosition1.setForeground(new java.awt.Color(0, 0, 0));
        jLabelPosition1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelPosition1.setText("Postion         :");
        jPanelEmployeeDetails.add(jLabelPosition1);
        jLabelPosition1.setBounds(20, 60, 130, 50);

        jLabelDepartment1.setBackground(new java.awt.Color(255, 255, 255));
        jLabelDepartment1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabelDepartment1.setForeground(new java.awt.Color(0, 0, 0));
        jLabelDepartment1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelDepartment1.setText("Department  :");
        jPanelEmployeeDetails.add(jLabelDepartment1);
        jLabelDepartment1.setBounds(20, 110, 130, 50);

        jLabelEmployeeID.setBackground(new java.awt.Color(255, 255, 255));
        jLabelEmployeeID.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabelEmployeeID.setForeground(new java.awt.Color(0, 0, 0));
        jLabelEmployeeID.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jPanelEmployeeDetails.add(jLabelEmployeeID);
        jLabelEmployeeID.setBounds(150, 10, 250, 50);

        jLabelPosition.setBackground(new java.awt.Color(255, 255, 255));
        jLabelPosition.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabelPosition.setForeground(new java.awt.Color(0, 0, 0));
        jLabelPosition.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jPanelEmployeeDetails.add(jLabelPosition);
        jLabelPosition.setBounds(150, 60, 250, 50);

        jLabelDepartment.setBackground(new java.awt.Color(255, 255, 255));
        jLabelDepartment.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabelDepartment.setForeground(new java.awt.Color(0, 0, 0));
        jLabelDepartment.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jPanelEmployeeDetails.add(jLabelDepartment);
        jLabelDepartment.setBounds(150, 110, 250, 50);

        jPanelHRDashboard.add(jPanelEmployeeDetails);
        jPanelEmployeeDetails.setBounds(90, 140, 420, 160);

        jPanelPendingSupportRequest.setBackground(new java.awt.Color(255, 255, 255));
        jPanelPendingSupportRequest.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanelPendingSupportRequest.setLayout(null);

        jLabelPendingSupportRequest.setBackground(new java.awt.Color(255, 255, 255));
        jLabelPendingSupportRequest.setFont(new java.awt.Font("Segoe UI", 1, 56)); // NOI18N
        jLabelPendingSupportRequest.setForeground(new java.awt.Color(0, 0, 0));
        jLabelPendingSupportRequest.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelPendingSupportRequest.setText("0");
        jPanelPendingSupportRequest.add(jLabelPendingSupportRequest);
        jLabelPendingSupportRequest.setBounds(20, 50, 380, 100);

        jLabelPendingHRTickets.setBackground(new java.awt.Color(255, 255, 255));
        jLabelPendingHRTickets.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabelPendingHRTickets.setForeground(new java.awt.Color(0, 0, 0));
        jLabelPendingHRTickets.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelPendingHRTickets.setText("Pending Support Request");
        jPanelPendingSupportRequest.add(jLabelPendingHRTickets);
        jLabelPendingHRTickets.setBounds(20, 0, 240, 50);

        jPanelHRDashboard.add(jPanelPendingSupportRequest);
        jPanelPendingSupportRequest.setBounds(600, 550, 420, 160);

        jLabelHelloEmployee.setBackground(new java.awt.Color(255, 255, 255));
        jLabelHelloEmployee.setFont(new java.awt.Font("Segoe UI", 1, 21)); // NOI18N
        jLabelHelloEmployee.setForeground(new java.awt.Color(0, 0, 0));
        jLabelHelloEmployee.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelHelloEmployee.setText("Hello, Employee!");
        jPanelHRDashboard.add(jLabelHelloEmployee);
        jLabelHelloEmployee.setBounds(30, 30, 610, 29);

        jLabelDashboardSmall.setText("Dashboard");
        jPanelHRDashboard.add(jLabelDashboardSmall);
        jLabelDashboardSmall.setBounds(30, 60, 140, 16);

        add(jPanelHRDashboard, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabelDashboardSmall;
    private javax.swing.JLabel jLabelDateAndTIme;
    private javax.swing.JLabel jLabelDateAndTime;
    private javax.swing.JLabel jLabelDepartment;
    private javax.swing.JLabel jLabelDepartment1;
    private javax.swing.JLabel jLabelDepartmentCount;
    private javax.swing.JLabel jLabelEmployeeID;
    private javax.swing.JLabel jLabelEmployeeNumber5;
    private javax.swing.JLabel jLabelHelloEmployee;
    private javax.swing.JLabel jLabelPendingHRTickets;
    private javax.swing.JLabel jLabelPendingLeaveCount;
    private javax.swing.JLabel jLabelPendingLeaveRequest;
    private javax.swing.JLabel jLabelPendingOvertimeCount;
    private javax.swing.JLabel jLabelPendingOvertimeRequest;
    private javax.swing.JLabel jLabelPendingSupportRequest;
    private javax.swing.JLabel jLabelPosition;
    private javax.swing.JLabel jLabelPosition1;
    private javax.swing.JLabel jLabelTotalDepartments;
    private javax.swing.JPanel jPanelDateAndTimeBox;
    private javax.swing.JPanel jPanelDepartmentsBox;
    private javax.swing.JPanel jPanelEmployeeDetails;
    private javax.swing.JPanel jPanelHRDashboard;
    private javax.swing.JPanel jPanelPendingLeaveBox;
    private javax.swing.JPanel jPanelPendingOvertimeBox;
    private javax.swing.JPanel jPanelPendingSupportRequest;
    // End of variables declaration//GEN-END:variables
}