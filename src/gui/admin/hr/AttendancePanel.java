package gui.admin.hr;

import java.awt.CardLayout;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.pojo.Attendance;
import service.AttendanceService;
import util.UIUtil;

public class AttendancePanel extends javax.swing.JPanel {

    private final AdminHRPortal hrPortal;
    private final AttendanceService attendanceService;
    
    public AttendancePanel(AdminHRPortal hrPortal) {
        this.hrPortal = hrPortal;
        this.attendanceService = new AttendanceService();
        initComponents();
        UIUtil.setGreeting(jLabelHelloAdmin, "Admin");
        loadAttendanceHistory();
    }
    
    public final void loadAttendanceHistory() {
        List<Attendance> records = attendanceService.getAllRecords();

        String[] cols = {
            "Attendance ID",
            "Attendance Date",
            "Employee ID",
            "Clock In",
            "Clock Out",
            "Regular Hours",
            "Overtime Hours",
            "Status"
        };

        UIUtil.styleTable(jTableAttendanceRecords, cols);

        DefaultTableModel model = (DefaultTableModel) jTableAttendanceRecords.getModel();
        model.setRowCount(0);
        
        for (Attendance a : records) {
            model.addRow(new Object[]{
                a.getAttendanceID(),
                a.getDate(),
                a.getEmployeeID(),
                a.getClockIn(),
                a.getClockOut(),
                a.getRegularHours(),
                a.getOvertimeHours(),
                a.getStatus()
            });
        }

        UIUtil.installSearchFilter(jTableAttendanceRecords, jTextFieldSearch, 0, 1, 2, 3, 4, 5, 6, 7);
    }
    
    private void onViewClicked() {
        int row = jTableAttendanceRecords.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(
                    this,
                    "Please select an attendance record to view.",
                    "No Selection",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        int attendanceID = (int) jTableAttendanceRecords.getValueAt(row, 0);
        hrPortal.getViewAttendancePanel().loadSelectedAttendance(attendanceID);

        CardLayout cardLayout = (CardLayout) hrPortal.getPanelParentCard().getLayout();
        cardLayout.show(hrPortal.getPanelParentCard(), "ViewAttendance");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabelHelloAdmin = new javax.swing.JLabel();
        jLabelAttendanceSmall = new javax.swing.JLabel();
        jPanelRecordsBox = new javax.swing.JPanel();
        jButtonView = new javax.swing.JButton();
        jLabelAttendanceRecords = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableAttendanceRecords = new javax.swing.JTable();
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
        jLabelHelloAdmin.setBounds(30, 30, 580, 29);

        jLabelAttendanceSmall.setText("Attendance");
        jPanel1.add(jLabelAttendanceSmall);
        jLabelAttendanceSmall.setBounds(30, 60, 100, 16);

        jPanelRecordsBox.setBackground(new java.awt.Color(255, 255, 255));
        jPanelRecordsBox.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanelRecordsBox.setLayout(null);

        jButtonView.setBackground(new java.awt.Color(0, 43, 89));
        jButtonView.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButtonView.setForeground(new java.awt.Color(255, 255, 255));
        jButtonView.setText("View");
        jButtonView.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonViewActionPerformed(evt);
            }
        });
        jPanelRecordsBox.add(jButtonView);
        jButtonView.setBounds(870, 580, 170, 40);

        jLabelAttendanceRecords.setBackground(new java.awt.Color(255, 255, 255));
        jLabelAttendanceRecords.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelAttendanceRecords.setForeground(new java.awt.Color(0, 0, 0));
        jLabelAttendanceRecords.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelAttendanceRecords.setText("ATTENDANCE RECORDS");
        jPanelRecordsBox.add(jLabelAttendanceRecords);
        jLabelAttendanceRecords.setBounds(20, 90, 210, 40);

        jTableAttendanceRecords.setAutoCreateRowSorter(true);
        jTableAttendanceRecords.setModel(new javax.swing.table.DefaultTableModel(
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
                "Attendance ID", "Attendance Date", "Employee ID", "Clock-In", "Clock-Out", "Regular Hours", "Overtime Hours", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
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
        jTableAttendanceRecords.setFocusable(false);
        jTableAttendanceRecords.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTableAttendanceRecords.setShowGrid(true);
        jTableAttendanceRecords.getTableHeader().setResizingAllowed(false);
        jTableAttendanceRecords.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jTableAttendanceRecords);

        jPanelRecordsBox.add(jScrollPane1);
        jScrollPane1.setBounds(20, 130, 1020, 420);

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
        jPanelRecordsBox.add(jTextFieldSearch);
        jTextFieldSearch.setBounds(100, 30, 260, 40);

        jLabelSeach.setBackground(new java.awt.Color(255, 255, 255));
        jLabelSeach.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelSeach.setForeground(new java.awt.Color(0, 0, 0));
        jLabelSeach.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelSeach.setText("Search :");
        jPanelRecordsBox.add(jLabelSeach);
        jLabelSeach.setBounds(20, 30, 80, 40);

        jPanel1.add(jPanelRecordsBox);
        jPanelRecordsBox.setBounds(30, 100, 1060, 650);

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonViewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonViewActionPerformed
        // TODO add your handling code here:
        onViewClicked();
    }//GEN-LAST:event_jButtonViewActionPerformed

    private void jTextFieldSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldSearchActionPerformed

    private void jTextFieldSearchKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldSearchKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldSearchKeyPressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonView;
    private javax.swing.JLabel jLabelAttendanceRecords;
    private javax.swing.JLabel jLabelAttendanceSmall;
    private javax.swing.JLabel jLabelHelloAdmin;
    private javax.swing.JLabel jLabelSeach;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanelRecordsBox;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableAttendanceRecords;
    private javax.swing.JTextField jTextFieldSearch;
    // End of variables declaration//GEN-END:variables
}