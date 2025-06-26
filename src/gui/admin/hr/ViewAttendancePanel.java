package gui.admin.hr;

import java.awt.CardLayout;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import javax.swing.JTextField;
import javax.swing.text.AbstractDocument;
import model.pojo.Attendance;
import model.pojo.EmployeeView;
import service.AttendanceService;
import service.EmployeeService;
import util.TimeFormatFilter;
import util.UIUtil;

public class ViewAttendancePanel extends javax.swing.JPanel {
    
    private final AdminHRPortal hrPortal;
    private final AttendanceService attendanceService;
    private final EmployeeService employeeService;

    public ViewAttendancePanel(AdminHRPortal hrPortal) {
        this.hrPortal = hrPortal;
        this.attendanceService = new AttendanceService();
        this.employeeService = new EmployeeService();
        initComponents();
        UIUtil.setGreeting(jLabelHelloAdmin, "Admin");
        applyTimeFilter(jTextFieldClockInTime);
        applyTimeFilter(jTextFieldClockOutTime);
    }
    
    private void applyTimeFilter(JTextField textField) {
        ((AbstractDocument) textField.getDocument()).setDocumentFilter(new TimeFormatFilter());
    }
    
    public void loadSelectedAttendance(int attendanceID) {
        Attendance a = attendanceService.getById(attendanceID);
        if (a == null) {
            return;
        }

        EmployeeView efd = employeeService.getEmployeeById(a.getEmployeeID());
        DateTimeFormatter timeFmt = DateTimeFormatter.ofPattern("HH:mm");

        jTextFieldAttendanceID.setText(String.valueOf(a.getAttendanceID()));
        jTextFieldAttendanceDate.setText(a.getDate().toString());
        jTextFieldEmployeeID.setText(String.valueOf(a.getEmployeeID()));
        jTextFieldFullName.setText(efd.getFirstName() + " " + efd.getLastName());
        jTextFieldPosition.setText(efd.getPositionTitle());
        jTextFieldClockInTime.setText(a.getClockIn() != null ? a.getClockIn().format(timeFmt) : "");
        jTextFieldClockOutTime.setText(a.getClockOut() != null ? a.getClockOut().format(timeFmt) : "");
        jTextFieldRegularHours.setText(a.getRegularHours() != null ? a.getRegularHours().toPlainString() : "");
        jTextFieldOvertimeHours.setText(a.getOvertimeHours() != null ? a.getOvertimeHours().toPlainString() : "");
        jTextFieldStatus.setText(a.getStatus() != null ? a.getStatus() : "");
    }
    
    private void onClickUpdate() {
        try {
            int attendanceID = Integer.parseInt(jTextFieldAttendanceID.getText());
            String clockInStr = jTextFieldClockInTime.getText().trim();
            String clockOutStr = jTextFieldClockOutTime.getText().trim();
            
            if (jTextFieldRegularHours.getText().trim().isEmpty() &&
                jTextFieldOvertimeHours.getText().trim().isEmpty() && jTextFieldStatus.getText().trim().isEmpty()) {
                UIUtil.showErrorMessage(this, """
                                              This attendance record has no clock-out data. Please
                                              ensure the employee has clocked out before editing.""", "Validation Error");
                return;
            }

            if (clockInStr.isEmpty() || clockOutStr.isEmpty()) {
                UIUtil.showErrorMessage(this, "Clock-in and clock-out time are required.", "Validation Error");
                return;
            }

            DateTimeFormatter timeFmt = DateTimeFormatter.ofPattern("HH:mm");
            LocalTime clockIn, clockOut;

            try {
                clockIn = LocalTime.parse(clockInStr, timeFmt);
                clockOut = LocalTime.parse(clockOutStr, timeFmt);
            } catch (DateTimeParseException e) {
                UIUtil.showErrorMessage(this, "Invalid time format. Use HH:mm (e.g. 09:00).", "Invalid Format");
                return;
            }

            Attendance original = attendanceService.getById(attendanceID);
            if (original == null) {
                UIUtil.showErrorMessage(this, "Attendance record not found.", "Error");
                return;
            }

            if (clockIn.equals(original.getClockIn()) && clockOut.equals(original.getClockOut())) {
                UIUtil.showInfoMessage(this, "No changes detected. Update not necessary.", "No Changes");
                return;
            }

            boolean success;
            try {
                success = attendanceService.updateAttendance(attendanceID, clockIn, clockOut);
            } catch (IllegalArgumentException e) {
                UIUtil.showErrorMessage(this, e.getMessage(), "Validation Error");
                return;
            }

            if (success) {
                UIUtil.showInfoMessage(this, "Attendance record updated successfully.", "Success");
                loadSelectedAttendance(attendanceID);
                hrPortal.getAttendancePanel().loadAttendanceHistory();
            } else {
                UIUtil.showErrorMessage(this, "Failed to update attendance.", "Error");
            }

        } catch (NumberFormatException e) {
            UIUtil.showErrorMessage(this, "Invalid attendance ID.", "Error");
        } catch (Exception ex) {
            UIUtil.showErrorMessage(this, "Unexpected error: " + ex.getMessage(), "System Error");
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabelHelloAdmin = new javax.swing.JLabel();
        jLabelViewRecordSmall = new javax.swing.JLabel();
        jPanelViewAttendanceBox = new javax.swing.JPanel();
        jTextFieldEmployeeID = new javax.swing.JTextField();
        jLabelEmployeeNumber = new javax.swing.JLabel();
        jLabelName = new javax.swing.JLabel();
        jLabelBack = new javax.swing.JLabel();
        jButtonUpdate = new javax.swing.JButton();
        jLabelAttendanceID = new javax.swing.JLabel();
        jTextFieldAttendanceID = new javax.swing.JTextField();
        jLabelAttendanceDate = new javax.swing.JLabel();
        jTextFieldAttendanceDate = new javax.swing.JTextField();
        jTextFieldFullName = new javax.swing.JTextField();
        jLabelClockInTime = new javax.swing.JLabel();
        jTextFieldClockInTime = new javax.swing.JTextField();
        jLabelClockOutTime = new javax.swing.JLabel();
        jTextFieldClockOutTime = new javax.swing.JTextField();
        jLabelRegularHours = new javax.swing.JLabel();
        jTextFieldRegularHours = new javax.swing.JTextField();
        jLabelOvertimeHours = new javax.swing.JLabel();
        jTextFieldOvertimeHours = new javax.swing.JTextField();
        jLabelStatus = new javax.swing.JLabel();
        jTextFieldStatus = new javax.swing.JTextField();
        jLabelPosition = new javax.swing.JLabel();
        jTextFieldPosition = new javax.swing.JTextField();

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
        jLabelHelloAdmin.setBounds(30, 30, 600, 29);

        jLabelViewRecordSmall.setText("Attendance > View Record");
        jPanel1.add(jLabelViewRecordSmall);
        jLabelViewRecordSmall.setBounds(30, 60, 180, 16);

        jPanelViewAttendanceBox.setBackground(new java.awt.Color(255, 255, 255));
        jPanelViewAttendanceBox.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanelViewAttendanceBox.setLayout(null);

        jTextFieldEmployeeID.setBackground(new java.awt.Color(240, 240, 240));
        jTextFieldEmployeeID.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jTextFieldEmployeeID.setEnabled(false);
        jTextFieldEmployeeID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldEmployeeIDActionPerformed(evt);
            }
        });
        jPanelViewAttendanceBox.add(jTextFieldEmployeeID);
        jTextFieldEmployeeID.setBounds(180, 260, 350, 40);

        jLabelEmployeeNumber.setBackground(new java.awt.Color(255, 255, 255));
        jLabelEmployeeNumber.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelEmployeeNumber.setForeground(new java.awt.Color(0, 0, 0));
        jLabelEmployeeNumber.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelEmployeeNumber.setText("Employee ID :");
        jPanelViewAttendanceBox.add(jLabelEmployeeNumber);
        jLabelEmployeeNumber.setBounds(30, 260, 290, 40);

        jLabelName.setBackground(new java.awt.Color(255, 255, 255));
        jLabelName.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelName.setForeground(new java.awt.Color(0, 0, 0));
        jLabelName.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelName.setText("Name :");
        jPanelViewAttendanceBox.add(jLabelName);
        jLabelName.setBounds(30, 300, 290, 40);

        jLabelBack.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/back-icon.png"))); // NOI18N
        jLabelBack.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelBackMouseClicked(evt);
            }
        });
        jPanelViewAttendanceBox.add(jLabelBack);
        jLabelBack.setBounds(0, 560, 60, 60);

        jButtonUpdate.setBackground(new java.awt.Color(0, 135, 0));
        jButtonUpdate.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButtonUpdate.setForeground(new java.awt.Color(255, 255, 255));
        jButtonUpdate.setText("Update");
        jButtonUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonUpdateActionPerformed(evt);
            }
        });
        jPanelViewAttendanceBox.add(jButtonUpdate);
        jButtonUpdate.setBounds(870, 570, 170, 40);

        jLabelAttendanceID.setBackground(new java.awt.Color(255, 255, 255));
        jLabelAttendanceID.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelAttendanceID.setForeground(new java.awt.Color(0, 0, 0));
        jLabelAttendanceID.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelAttendanceID.setText("Attendance ID :");
        jPanelViewAttendanceBox.add(jLabelAttendanceID);
        jLabelAttendanceID.setBounds(30, 180, 290, 40);

        jTextFieldAttendanceID.setBackground(new java.awt.Color(240, 240, 240));
        jTextFieldAttendanceID.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jTextFieldAttendanceID.setEnabled(false);
        jTextFieldAttendanceID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldAttendanceIDActionPerformed(evt);
            }
        });
        jPanelViewAttendanceBox.add(jTextFieldAttendanceID);
        jTextFieldAttendanceID.setBounds(180, 180, 350, 40);

        jLabelAttendanceDate.setBackground(new java.awt.Color(255, 255, 255));
        jLabelAttendanceDate.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelAttendanceDate.setForeground(new java.awt.Color(0, 0, 0));
        jLabelAttendanceDate.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelAttendanceDate.setText("Date :");
        jPanelViewAttendanceBox.add(jLabelAttendanceDate);
        jLabelAttendanceDate.setBounds(30, 220, 290, 40);

        jTextFieldAttendanceDate.setBackground(new java.awt.Color(240, 240, 240));
        jTextFieldAttendanceDate.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jTextFieldAttendanceDate.setEnabled(false);
        jTextFieldAttendanceDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldAttendanceDateActionPerformed(evt);
            }
        });
        jPanelViewAttendanceBox.add(jTextFieldAttendanceDate);
        jTextFieldAttendanceDate.setBounds(180, 220, 350, 40);

        jTextFieldFullName.setBackground(new java.awt.Color(240, 240, 240));
        jTextFieldFullName.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jTextFieldFullName.setEnabled(false);
        jTextFieldFullName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldFullNameActionPerformed(evt);
            }
        });
        jPanelViewAttendanceBox.add(jTextFieldFullName);
        jTextFieldFullName.setBounds(180, 300, 350, 40);

        jLabelClockInTime.setBackground(new java.awt.Color(255, 255, 255));
        jLabelClockInTime.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelClockInTime.setForeground(new java.awt.Color(0, 0, 0));
        jLabelClockInTime.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelClockInTime.setText("Clock-In Time :");
        jPanelViewAttendanceBox.add(jLabelClockInTime);
        jLabelClockInTime.setBounds(540, 180, 290, 40);

        jTextFieldClockInTime.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jTextFieldClockInTime.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldClockInTimeActionPerformed(evt);
            }
        });
        jPanelViewAttendanceBox.add(jTextFieldClockInTime);
        jTextFieldClockInTime.setBounds(690, 180, 350, 40);

        jLabelClockOutTime.setBackground(new java.awt.Color(255, 255, 255));
        jLabelClockOutTime.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelClockOutTime.setForeground(new java.awt.Color(0, 0, 0));
        jLabelClockOutTime.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelClockOutTime.setText("Clock-Out Time :");
        jPanelViewAttendanceBox.add(jLabelClockOutTime);
        jLabelClockOutTime.setBounds(540, 220, 290, 40);

        jTextFieldClockOutTime.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jTextFieldClockOutTime.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldClockOutTimeActionPerformed(evt);
            }
        });
        jPanelViewAttendanceBox.add(jTextFieldClockOutTime);
        jTextFieldClockOutTime.setBounds(690, 220, 350, 40);

        jLabelRegularHours.setBackground(new java.awt.Color(255, 255, 255));
        jLabelRegularHours.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelRegularHours.setForeground(new java.awt.Color(0, 0, 0));
        jLabelRegularHours.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelRegularHours.setText("Regular Hours :");
        jPanelViewAttendanceBox.add(jLabelRegularHours);
        jLabelRegularHours.setBounds(540, 260, 290, 40);

        jTextFieldRegularHours.setBackground(new java.awt.Color(240, 240, 240));
        jTextFieldRegularHours.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jTextFieldRegularHours.setEnabled(false);
        jTextFieldRegularHours.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldRegularHoursActionPerformed(evt);
            }
        });
        jPanelViewAttendanceBox.add(jTextFieldRegularHours);
        jTextFieldRegularHours.setBounds(690, 260, 350, 40);

        jLabelOvertimeHours.setBackground(new java.awt.Color(255, 255, 255));
        jLabelOvertimeHours.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelOvertimeHours.setForeground(new java.awt.Color(0, 0, 0));
        jLabelOvertimeHours.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelOvertimeHours.setText("Overtime Hours :");
        jPanelViewAttendanceBox.add(jLabelOvertimeHours);
        jLabelOvertimeHours.setBounds(540, 300, 290, 40);

        jTextFieldOvertimeHours.setBackground(new java.awt.Color(240, 240, 240));
        jTextFieldOvertimeHours.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jTextFieldOvertimeHours.setEnabled(false);
        jTextFieldOvertimeHours.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldOvertimeHoursActionPerformed(evt);
            }
        });
        jPanelViewAttendanceBox.add(jTextFieldOvertimeHours);
        jTextFieldOvertimeHours.setBounds(690, 300, 350, 40);

        jLabelStatus.setBackground(new java.awt.Color(255, 255, 255));
        jLabelStatus.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelStatus.setForeground(new java.awt.Color(0, 0, 0));
        jLabelStatus.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelStatus.setText("Status :");
        jPanelViewAttendanceBox.add(jLabelStatus);
        jLabelStatus.setBounds(540, 340, 290, 40);

        jTextFieldStatus.setBackground(new java.awt.Color(240, 240, 240));
        jTextFieldStatus.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jTextFieldStatus.setEnabled(false);
        jTextFieldStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldStatusActionPerformed(evt);
            }
        });
        jPanelViewAttendanceBox.add(jTextFieldStatus);
        jTextFieldStatus.setBounds(690, 340, 350, 40);

        jLabelPosition.setBackground(new java.awt.Color(255, 255, 255));
        jLabelPosition.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelPosition.setForeground(new java.awt.Color(0, 0, 0));
        jLabelPosition.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelPosition.setText("Position :");
        jPanelViewAttendanceBox.add(jLabelPosition);
        jLabelPosition.setBounds(30, 340, 290, 40);

        jTextFieldPosition.setBackground(new java.awt.Color(240, 240, 240));
        jTextFieldPosition.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jTextFieldPosition.setEnabled(false);
        jTextFieldPosition.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldPositionActionPerformed(evt);
            }
        });
        jPanelViewAttendanceBox.add(jTextFieldPosition);
        jTextFieldPosition.setBounds(180, 340, 350, 40);

        jPanel1.add(jPanelViewAttendanceBox);
        jPanelViewAttendanceBox.setBounds(30, 110, 1060, 640);

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonUpdateActionPerformed
        // TODO add your handling code here:
        onClickUpdate();
    }//GEN-LAST:event_jButtonUpdateActionPerformed

    private void jLabelBackMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelBackMouseClicked
        // TODO add your handling code here:
        CardLayout cardLayout = (CardLayout) hrPortal.getPanelParentCard().getLayout();
        cardLayout.show(hrPortal.getPanelParentCard(), "Attendance");
    }//GEN-LAST:event_jLabelBackMouseClicked

    private void jTextFieldEmployeeIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldEmployeeIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldEmployeeIDActionPerformed

    private void jTextFieldAttendanceIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldAttendanceIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldAttendanceIDActionPerformed

    private void jTextFieldAttendanceDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldAttendanceDateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldAttendanceDateActionPerformed

    private void jTextFieldFullNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldFullNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldFullNameActionPerformed

    private void jTextFieldClockInTimeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldClockInTimeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldClockInTimeActionPerformed

    private void jTextFieldClockOutTimeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldClockOutTimeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldClockOutTimeActionPerformed

    private void jTextFieldRegularHoursActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldRegularHoursActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldRegularHoursActionPerformed

    private void jTextFieldOvertimeHoursActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldOvertimeHoursActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldOvertimeHoursActionPerformed

    private void jTextFieldStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldStatusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldStatusActionPerformed

    private void jTextFieldPositionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldPositionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldPositionActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonUpdate;
    private javax.swing.JLabel jLabelAttendanceDate;
    private javax.swing.JLabel jLabelAttendanceID;
    private javax.swing.JLabel jLabelBack;
    private javax.swing.JLabel jLabelClockInTime;
    private javax.swing.JLabel jLabelClockOutTime;
    private javax.swing.JLabel jLabelEmployeeNumber;
    private javax.swing.JLabel jLabelHelloAdmin;
    private javax.swing.JLabel jLabelName;
    private javax.swing.JLabel jLabelOvertimeHours;
    private javax.swing.JLabel jLabelPosition;
    private javax.swing.JLabel jLabelRegularHours;
    private javax.swing.JLabel jLabelStatus;
    private javax.swing.JLabel jLabelViewRecordSmall;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanelViewAttendanceBox;
    private javax.swing.JTextField jTextFieldAttendanceDate;
    private javax.swing.JTextField jTextFieldAttendanceID;
    private javax.swing.JTextField jTextFieldClockInTime;
    private javax.swing.JTextField jTextFieldClockOutTime;
    private javax.swing.JTextField jTextFieldEmployeeID;
    private javax.swing.JTextField jTextFieldFullName;
    private javax.swing.JTextField jTextFieldOvertimeHours;
    private javax.swing.JTextField jTextFieldPosition;
    private javax.swing.JTextField jTextFieldRegularHours;
    private javax.swing.JTextField jTextFieldStatus;
    // End of variables declaration//GEN-END:variables
}