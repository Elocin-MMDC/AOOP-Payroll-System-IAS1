package gui.admin.hr;

import java.awt.CardLayout;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.swing.table.DefaultTableModel;
import model.pojo.LeaveRequest;
import model.pojo.LeaveType;
import service.RequestService;
import util.UIUtil;

public class LeavesPanel extends javax.swing.JPanel {

    private final AdminHRPortal hrPortal;
    private final RequestService requestService;
    
    public LeavesPanel(AdminHRPortal hrPortal) {
        this.hrPortal = hrPortal;
        this.requestService = new RequestService();
        initComponents();
        UIUtil.setGreeting(jLabelHelloAdmin, "Admin");
        loadLeaveHistory();
    }
    
    public final void loadLeaveHistory() {
        List<LeaveRequest> leaves = requestService.getAllLeaveRequests();

        String[] cols = {
            "Leave ID",
            "Request Date",
            "Employee ID",
            "Start Date",
            "End Date",
            "Days",
            "Leave Type",
            "Status"
        };

        UIUtil.styleTable(jTableLeaveRecords, cols);
        
        DefaultTableModel model = (DefaultTableModel) jTableLeaveRecords.getModel();

        Map<Integer, String> typeNames = requestService.getLeaveTypes().stream()
                .collect(Collectors.toMap(LeaveType::getLeaveTypeID, LeaveType::getLeaveName));

        for (LeaveRequest lr : leaves) {
            model.addRow(new Object[]{
                lr.getLeaveID(),
                lr.getDate(),
                lr.getEmployeeID(),
                lr.getStartDate(),
                lr.getEndDate(),
                lr.getLeaveDays(),
                typeNames.getOrDefault(lr.getLeaveTypeID(), ""),
                lr.getStatus()
            });
        }

        UIUtil.installSearchFilter(jTableLeaveRecords, jTextFieldSearch, 0, 1, 2, 3, 4, 5, 6, 7);
    }
    
    protected void onViewClicked() {
        int row = jTableLeaveRecords.getSelectedRow();
        if (row < 0) {
            UIUtil.showWarningMessage(this, "Please select a leave request to view.", "No Selection");
            return;
        }

        int leaveID = (int) jTableLeaveRecords.getValueAt(row, 0);
        int employeeID = (int) jTableLeaveRecords.getValueAt(row, 2);
        
        hrPortal.getViewLeavePanel().loadSelectedLeave(leaveID);
        hrPortal.getViewLeavePanel().loadEmployeeLeaveBalance(employeeID);

        CardLayout cardLayout = (CardLayout) hrPortal.getPanelParentCard().getLayout();
        cardLayout.show(hrPortal.getPanelParentCard(), "ViewLeave");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabelHelloAdmin = new javax.swing.JLabel();
        jLabelLeavesSmall = new javax.swing.JLabel();
        jPanelRecordsBox = new javax.swing.JPanel();
        jLabelLeaveManagement = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableLeaveRecords = new javax.swing.JTable();
        jButtonView = new javax.swing.JButton();
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
        jLabelHelloAdmin.setBounds(30, 30, 560, 29);

        jLabelLeavesSmall.setText("Leaves");
        jPanel1.add(jLabelLeavesSmall);
        jLabelLeavesSmall.setBounds(30, 60, 100, 16);

        jPanelRecordsBox.setBackground(new java.awt.Color(255, 255, 255));
        jPanelRecordsBox.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanelRecordsBox.setLayout(null);

        jLabelLeaveManagement.setBackground(new java.awt.Color(255, 255, 255));
        jLabelLeaveManagement.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelLeaveManagement.setForeground(new java.awt.Color(0, 0, 0));
        jLabelLeaveManagement.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelLeaveManagement.setText("LEAVE MANAGEMENT");
        jPanelRecordsBox.add(jLabelLeaveManagement);
        jLabelLeaveManagement.setBounds(20, 90, 160, 40);

        jTableLeaveRecords.setAutoCreateRowSorter(true);
        jTableLeaveRecords.setModel(new javax.swing.table.DefaultTableModel(
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
                "Leave ID", "Request Date", "Employee ID", "Start Date", "End Date", "Days", "Leave Type", "Status"
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
        jTableLeaveRecords.setFocusable(false);
        jTableLeaveRecords.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTableLeaveRecords.setShowGrid(true);
        jTableLeaveRecords.getTableHeader().setResizingAllowed(false);
        jTableLeaveRecords.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jTableLeaveRecords);

        jPanelRecordsBox.add(jScrollPane1);
        jScrollPane1.setBounds(20, 130, 1020, 420);

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
    private javax.swing.JLabel jLabelHelloAdmin;
    private javax.swing.JLabel jLabelLeaveManagement;
    private javax.swing.JLabel jLabelLeavesSmall;
    private javax.swing.JLabel jLabelSeach;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanelRecordsBox;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableLeaveRecords;
    private javax.swing.JTextField jTextFieldSearch;
    // End of variables declaration//GEN-END:variables
}