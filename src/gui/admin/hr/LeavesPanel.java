package gui.admin.hr;

import java.awt.CardLayout;

public class LeavesPanel extends javax.swing.JPanel {

    private final AdminHRPortal hrPortal;
    
    public LeavesPanel(AdminHRPortal hrPortal) {
        this.hrPortal = hrPortal;
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabelHelloAdmin = new javax.swing.JLabel();
        jLabelLeavesSmall = new javax.swing.JLabel();
        jPanelRecordsBox = new javax.swing.JPanel();
        jComboBoxSearchByStatus = new javax.swing.JComboBox<>();
        jLabelLeaveManagement = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableLeaves = new javax.swing.JTable();
        jButtonView = new javax.swing.JButton();
        jLabelSeachByEmployeeID = new javax.swing.JLabel();
        jTextFieldSearchById = new javax.swing.JTextField();

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
        jLabelHelloAdmin.setBounds(30, 30, 137, 29);

        jLabelLeavesSmall.setText("Leaves");
        jPanel1.add(jLabelLeavesSmall);
        jLabelLeavesSmall.setBounds(30, 60, 100, 16);

        jPanelRecordsBox.setBackground(new java.awt.Color(255, 255, 255));
        jPanelRecordsBox.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanelRecordsBox.setLayout(null);

        jComboBoxSearchByStatus.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jComboBoxSearchByStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select", "Pending", "Approved", "Rejected" }));
        jComboBoxSearchByStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxSearchByStatusActionPerformed(evt);
            }
        });
        jPanelRecordsBox.add(jComboBoxSearchByStatus);
        jComboBoxSearchByStatus.setBounds(780, 30, 260, 40);

        jLabelLeaveManagement.setBackground(new java.awt.Color(255, 255, 255));
        jLabelLeaveManagement.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelLeaveManagement.setForeground(new java.awt.Color(0, 0, 0));
        jLabelLeaveManagement.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelLeaveManagement.setText("LEAVE MANAGEMENT");
        jPanelRecordsBox.add(jLabelLeaveManagement);
        jLabelLeaveManagement.setBounds(20, 90, 160, 40);

        jTableLeaves.setAutoCreateRowSorter(true);
        jTableLeaves.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Leave ID", "Request Date", "Employee ID", "Name", "Start Date", "End Date", "Days", "Leave Type", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableLeaves.setFocusable(false);
        jTableLeaves.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTableLeaves.setShowGrid(true);
        jTableLeaves.getTableHeader().setResizingAllowed(false);
        jTableLeaves.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jTableLeaves);

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

        jLabelSeachByEmployeeID.setBackground(new java.awt.Color(255, 255, 255));
        jLabelSeachByEmployeeID.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelSeachByEmployeeID.setForeground(new java.awt.Color(0, 0, 0));
        jLabelSeachByEmployeeID.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelSeachByEmployeeID.setText("Search by Employee ID :");
        jPanelRecordsBox.add(jLabelSeachByEmployeeID);
        jLabelSeachByEmployeeID.setBounds(20, 30, 180, 40);

        jTextFieldSearchById.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        jTextFieldSearchById.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldSearchByIdActionPerformed(evt);
            }
        });
        jTextFieldSearchById.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldSearchByIdKeyPressed(evt);
            }
        });
        jPanelRecordsBox.add(jTextFieldSearchById);
        jTextFieldSearchById.setBounds(200, 30, 260, 40);

        jPanel1.add(jPanelRecordsBox);
        jPanelRecordsBox.setBounds(30, 100, 1060, 650);

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBoxSearchByStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxSearchByStatusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxSearchByStatusActionPerformed

    private void jButtonViewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonViewActionPerformed
        // TODO add your handling code here:
        CardLayout cardLayout = (CardLayout) hrPortal.getPanelParentCard().getLayout();
        cardLayout.show(hrPortal.getPanelParentCard(), "ViewLeave");
    }//GEN-LAST:event_jButtonViewActionPerformed

    private void jTextFieldSearchByIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldSearchByIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldSearchByIdActionPerformed

    private void jTextFieldSearchByIdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldSearchByIdKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldSearchByIdKeyPressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonView;
    private javax.swing.JComboBox<String> jComboBoxSearchByStatus;
    private javax.swing.JLabel jLabelHelloAdmin;
    private javax.swing.JLabel jLabelLeaveManagement;
    private javax.swing.JLabel jLabelLeavesSmall;
    private javax.swing.JLabel jLabelSeachByEmployeeID;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanelRecordsBox;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableLeaves;
    private javax.swing.JTextField jTextFieldSearchById;
    // End of variables declaration//GEN-END:variables
}