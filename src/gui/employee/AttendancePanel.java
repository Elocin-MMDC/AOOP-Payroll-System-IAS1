package gui.employee;

import java.awt.CardLayout;

public class AttendancePanel extends javax.swing.JPanel {
    
    private final EmployeePortal employeePortal;

    public AttendancePanel(EmployeePortal employeePortal) {
        this.employeePortal = employeePortal;
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabelHelloEmployee = new javax.swing.JLabel();
        jLabelAttendanceSmall = new javax.swing.JLabel();
        jPanelAttendanceBox = new javax.swing.JPanel();
        jLabelClockInTime = new javax.swing.JLabel();
        jTextFieldClockInTime = new javax.swing.JTextField();
        jLabelClockOutTime = new javax.swing.JLabel();
        jTextFieldClockOutTime = new javax.swing.JTextField();
        jLabelHoursWorked1 = new javax.swing.JLabel();
        jTextFieldHoursWorked = new javax.swing.JTextField();
        jButtonClock = new javax.swing.JButton();
        jLabelHoursWorked = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableAttendanceHistory = new javax.swing.JTable();
        jPanelDateAndTimeBox = new javax.swing.JPanel();
        jLabelInsertDateAndTime = new javax.swing.JLabel();

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

        jLabelAttendanceSmall.setText("Attendance");
        jPanel1.add(jLabelAttendanceSmall);
        jLabelAttendanceSmall.setBounds(30, 60, 180, 16);

        jPanelAttendanceBox.setBackground(new java.awt.Color(255, 255, 255));
        jPanelAttendanceBox.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanelAttendanceBox.setLayout(null);

        jLabelClockInTime.setBackground(new java.awt.Color(255, 255, 255));
        jLabelClockInTime.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelClockInTime.setForeground(new java.awt.Color(0, 0, 0));
        jLabelClockInTime.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelClockInTime.setText("Clock-In Time :");
        jPanelAttendanceBox.add(jLabelClockInTime);
        jLabelClockInTime.setBounds(370, 30, 150, 40);

        jTextFieldClockInTime.setEditable(false);
        jTextFieldClockInTime.setBackground(new java.awt.Color(255, 255, 255));
        jTextFieldClockInTime.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        jTextFieldClockInTime.setFocusable(false);
        jTextFieldClockInTime.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldClockInTimeActionPerformed(evt);
            }
        });
        jPanelAttendanceBox.add(jTextFieldClockInTime);
        jTextFieldClockInTime.setBounds(520, 30, 160, 40);

        jLabelClockOutTime.setBackground(new java.awt.Color(255, 255, 255));
        jLabelClockOutTime.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelClockOutTime.setForeground(new java.awt.Color(0, 0, 0));
        jLabelClockOutTime.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelClockOutTime.setText("Clock-Out Time :");
        jPanelAttendanceBox.add(jLabelClockOutTime);
        jLabelClockOutTime.setBounds(370, 70, 150, 40);

        jTextFieldClockOutTime.setEditable(false);
        jTextFieldClockOutTime.setBackground(new java.awt.Color(255, 255, 255));
        jTextFieldClockOutTime.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        jTextFieldClockOutTime.setFocusable(false);
        jTextFieldClockOutTime.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldClockOutTimeActionPerformed(evt);
            }
        });
        jPanelAttendanceBox.add(jTextFieldClockOutTime);
        jTextFieldClockOutTime.setBounds(520, 70, 160, 40);

        jLabelHoursWorked1.setBackground(new java.awt.Color(255, 255, 255));
        jLabelHoursWorked1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelHoursWorked1.setForeground(new java.awt.Color(0, 0, 0));
        jLabelHoursWorked1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelHoursWorked1.setText("Hours Worked :");
        jPanelAttendanceBox.add(jLabelHoursWorked1);
        jLabelHoursWorked1.setBounds(370, 110, 150, 40);

        jTextFieldHoursWorked.setEditable(false);
        jTextFieldHoursWorked.setBackground(new java.awt.Color(255, 255, 255));
        jTextFieldHoursWorked.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        jTextFieldHoursWorked.setFocusable(false);
        jTextFieldHoursWorked.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldHoursWorkedActionPerformed(evt);
            }
        });
        jPanelAttendanceBox.add(jTextFieldHoursWorked);
        jTextFieldHoursWorked.setBounds(520, 110, 160, 40);

        jButtonClock.setBackground(new java.awt.Color(0, 77, 159));
        jButtonClock.setFont(new java.awt.Font("Segoe UI", 1, 32)); // NOI18N
        jButtonClock.setForeground(new java.awt.Color(255, 255, 255));
        jButtonClock.setText("Clock-In");
        jButtonClock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonClockActionPerformed(evt);
            }
        });
        jPanelAttendanceBox.add(jButtonClock);
        jButtonClock.setBounds(730, 30, 310, 120);

        jLabelHoursWorked.setBackground(new java.awt.Color(255, 255, 255));
        jLabelHoursWorked.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelHoursWorked.setForeground(new java.awt.Color(0, 0, 0));
        jLabelHoursWorked.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelHoursWorked.setText("ATTENDANCE HISTORY");
        jPanelAttendanceBox.add(jLabelHoursWorked);
        jLabelHoursWorked.setBounds(20, 180, 180, 40);

        jTableAttendanceHistory.setAutoCreateRowSorter(true);
        jTableAttendanceHistory.setModel(new javax.swing.table.DefaultTableModel(
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
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Attendance ID", "Attendance Date", "Clock-In", "Clock-Out", "Regular Hours", "Overtime Hours", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.Double.class, java.lang.String.class
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
        jTableAttendanceHistory.setFocusable(false);
        jTableAttendanceHistory.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTableAttendanceHistory.setShowGrid(true);
        jTableAttendanceHistory.getTableHeader().setResizingAllowed(false);
        jTableAttendanceHistory.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jTableAttendanceHistory);

        jPanelAttendanceBox.add(jScrollPane1);
        jScrollPane1.setBounds(20, 220, 1020, 400);

        jPanelDateAndTimeBox.setBackground(new java.awt.Color(255, 255, 255));
        jPanelDateAndTimeBox.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanelDateAndTimeBox.setLayout(null);

        jLabelInsertDateAndTime.setBackground(new java.awt.Color(255, 255, 255));
        jLabelInsertDateAndTime.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
        jLabelInsertDateAndTime.setForeground(new java.awt.Color(0, 43, 175));
        jLabelInsertDateAndTime.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelInsertDateAndTime.setText("January 1, 2025 12:00:00");
        jPanelDateAndTimeBox.add(jLabelInsertDateAndTime);
        jLabelInsertDateAndTime.setBounds(0, 30, 310, 60);

        jPanelAttendanceBox.add(jPanelDateAndTimeBox);
        jPanelDateAndTimeBox.setBounds(20, 30, 310, 120);

        jPanel1.add(jPanelAttendanceBox);
        jPanelAttendanceBox.setBounds(30, 110, 1060, 640);

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldClockInTimeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldClockInTimeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldClockInTimeActionPerformed

    private void jTextFieldClockOutTimeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldClockOutTimeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldClockOutTimeActionPerformed

    private void jTextFieldHoursWorkedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldHoursWorkedActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldHoursWorkedActionPerformed

    private void jButtonClockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonClockActionPerformed
        // TODO add your handling code 
    }//GEN-LAST:event_jButtonClockActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonClock;
    private javax.swing.JLabel jLabelAttendanceSmall;
    private javax.swing.JLabel jLabelClockInTime;
    private javax.swing.JLabel jLabelClockOutTime;
    private javax.swing.JLabel jLabelHelloEmployee;
    private javax.swing.JLabel jLabelHoursWorked;
    private javax.swing.JLabel jLabelHoursWorked1;
    private javax.swing.JLabel jLabelInsertDateAndTime;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanelAttendanceBox;
    private javax.swing.JPanel jPanelDateAndTimeBox;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableAttendanceHistory;
    private javax.swing.JTextField jTextFieldClockInTime;
    private javax.swing.JTextField jTextFieldClockOutTime;
    private javax.swing.JTextField jTextFieldHoursWorked;
    // End of variables declaration//GEN-END:variables
}