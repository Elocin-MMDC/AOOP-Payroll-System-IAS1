package gui.employee;

public class EmployeeDashboardPanel extends javax.swing.JPanel {

    private final EmployeePortal employeePortal;
    
    public EmployeeDashboardPanel(EmployeePortal employeePortal) {
        this.employeePortal = employeePortal;
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelHRDashboard = new javax.swing.JPanel();
        jPanelDepartmentsBox = new javax.swing.JPanel();
        jLabelTotalDepartments = new javax.swing.JLabel();
        jLabelInsertDepartments = new javax.swing.JLabel();
        jPanelDateAndTimeBox = new javax.swing.JPanel();
        jLabelInsertDateAndTime = new javax.swing.JLabel();
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
        jLabelInsertEmployeeNumber = new javax.swing.JLabel();
        jLabelInsertPosition = new javax.swing.JLabel();
        jLabelInsertDepartment = new javax.swing.JLabel();
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

        jLabelInsertDepartments.setBackground(new java.awt.Color(255, 255, 255));
        jLabelInsertDepartments.setFont(new java.awt.Font("Segoe UI", 1, 56)); // NOI18N
        jLabelInsertDepartments.setForeground(new java.awt.Color(0, 0, 0));
        jLabelInsertDepartments.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelInsertDepartments.setText("0");
        jPanelDepartmentsBox.add(jLabelInsertDepartments);
        jLabelInsertDepartments.setBounds(20, 50, 380, 100);

        jPanelHRDashboard.add(jPanelDepartmentsBox);
        jPanelDepartmentsBox.setBounds(90, 340, 420, 160);

        jPanelDateAndTimeBox.setBackground(new java.awt.Color(255, 255, 255));
        jPanelDateAndTimeBox.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanelDateAndTimeBox.setLayout(null);

        jLabelInsertDateAndTime.setBackground(new java.awt.Color(255, 255, 255));
        jLabelInsertDateAndTime.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabelInsertDateAndTime.setForeground(new java.awt.Color(0, 43, 175));
        jLabelInsertDateAndTime.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelInsertDateAndTime.setText("January 1, 2025 12:00:00");
        jPanelDateAndTimeBox.add(jLabelInsertDateAndTime);
        jLabelInsertDateAndTime.setBounds(20, 60, 380, 60);

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
        jLabelEmployeeNumber5.setText("Employee # :");
        jPanelEmployeeDetails.add(jLabelEmployeeNumber5);
        jLabelEmployeeNumber5.setBounds(20, 10, 120, 50);

        jLabelPosition1.setBackground(new java.awt.Color(255, 255, 255));
        jLabelPosition1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabelPosition1.setForeground(new java.awt.Color(0, 0, 0));
        jLabelPosition1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelPosition1.setText("Postion        :");
        jPanelEmployeeDetails.add(jLabelPosition1);
        jLabelPosition1.setBounds(20, 60, 120, 50);

        jLabelDepartment1.setBackground(new java.awt.Color(255, 255, 255));
        jLabelDepartment1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabelDepartment1.setForeground(new java.awt.Color(0, 0, 0));
        jLabelDepartment1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelDepartment1.setText("Department :");
        jPanelEmployeeDetails.add(jLabelDepartment1);
        jLabelDepartment1.setBounds(20, 110, 120, 50);

        jLabelInsertEmployeeNumber.setBackground(new java.awt.Color(255, 255, 255));
        jLabelInsertEmployeeNumber.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabelInsertEmployeeNumber.setForeground(new java.awt.Color(0, 0, 0));
        jLabelInsertEmployeeNumber.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jPanelEmployeeDetails.add(jLabelInsertEmployeeNumber);
        jLabelInsertEmployeeNumber.setBounds(150, 10, 250, 50);

        jLabelInsertPosition.setBackground(new java.awt.Color(255, 255, 255));
        jLabelInsertPosition.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabelInsertPosition.setForeground(new java.awt.Color(0, 0, 0));
        jLabelInsertPosition.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jPanelEmployeeDetails.add(jLabelInsertPosition);
        jLabelInsertPosition.setBounds(150, 60, 250, 50);

        jLabelInsertDepartment.setBackground(new java.awt.Color(255, 255, 255));
        jLabelInsertDepartment.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabelInsertDepartment.setForeground(new java.awt.Color(0, 0, 0));
        jLabelInsertDepartment.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jPanelEmployeeDetails.add(jLabelInsertDepartment);
        jLabelInsertDepartment.setBounds(150, 110, 250, 50);

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
        jLabelHelloEmployee.setBounds(30, 30, 190, 29);

        jLabelDashboardSmall.setText("Dashboard");
        jPanelHRDashboard.add(jLabelDashboardSmall);
        jLabelDashboardSmall.setBounds(30, 60, 140, 16);

        add(jPanelHRDashboard, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabelDashboardSmall;
    private javax.swing.JLabel jLabelDateAndTIme;
    private javax.swing.JLabel jLabelDepartment1;
    private javax.swing.JLabel jLabelEmployeeNumber5;
    private javax.swing.JLabel jLabelHelloEmployee;
    private javax.swing.JLabel jLabelInsertDateAndTime;
    private javax.swing.JLabel jLabelInsertDepartment;
    private javax.swing.JLabel jLabelInsertDepartments;
    private javax.swing.JLabel jLabelInsertEmployeeNumber;
    private javax.swing.JLabel jLabelInsertPosition;
    private javax.swing.JLabel jLabelPendingHRTickets;
    private javax.swing.JLabel jLabelPendingLeaveCount;
    private javax.swing.JLabel jLabelPendingLeaveRequest;
    private javax.swing.JLabel jLabelPendingOvertimeCount;
    private javax.swing.JLabel jLabelPendingOvertimeRequest;
    private javax.swing.JLabel jLabelPendingSupportRequest;
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