package gui.employee;

import java.awt.CardLayout;

public class OvertimePanel extends javax.swing.JPanel {
    
    private final EmployeePortal employeePortal;

    public OvertimePanel(EmployeePortal employeePortal) {
        this.employeePortal = employeePortal;
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabelHelloEmployee = new javax.swing.JLabel();
        jLabelOvertimeSmall = new javax.swing.JLabel();
        jPanelOvertimeRequestBox = new javax.swing.JPanel();
        jLabelOvertimeDate = new javax.swing.JLabel();
        jLabelOvertimeHours = new javax.swing.JLabel();
        jComboBoxOvertimeHours = new javax.swing.JComboBox<>();
        jDateChooserOvertimeDate = new com.toedter.calendar.JDateChooser();
        jButtonSubmit = new javax.swing.JButton();
        jLabelOvertimeHistory = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableOvertimeHistory = new javax.swing.JTable();
        jLabelReason = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextAreaReason = new javax.swing.JTextArea();
        jButtonView = new javax.swing.JButton();
        jLabelAsterisk1 = new javax.swing.JLabel();
        jLabelAsterisk2 = new javax.swing.JLabel();

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

        jLabelOvertimeSmall.setText("Overtime");
        jPanel1.add(jLabelOvertimeSmall);
        jLabelOvertimeSmall.setBounds(30, 60, 180, 16);

        jPanelOvertimeRequestBox.setBackground(new java.awt.Color(255, 255, 255));
        jPanelOvertimeRequestBox.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanelOvertimeRequestBox.setLayout(null);

        jLabelOvertimeDate.setBackground(new java.awt.Color(255, 255, 255));
        jLabelOvertimeDate.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelOvertimeDate.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelOvertimeDate.setText("Overtime Date   :");
        jPanelOvertimeRequestBox.add(jLabelOvertimeDate);
        jLabelOvertimeDate.setBounds(20, 40, 140, 40);

        jLabelOvertimeHours.setBackground(new java.awt.Color(255, 255, 255));
        jLabelOvertimeHours.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelOvertimeHours.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelOvertimeHours.setText("Overtime Hours   :");
        jPanelOvertimeRequestBox.add(jLabelOvertimeHours);
        jLabelOvertimeHours.setBounds(20, 80, 140, 40);

        jComboBoxOvertimeHours.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jComboBoxOvertimeHours.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select", "1 hour", "2 hours", "3 hours", "4 hours", "5 hours", "6 hours", "7 hours", "8 hours" }));
        jComboBoxOvertimeHours.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxOvertimeHoursActionPerformed(evt);
            }
        });
        jPanelOvertimeRequestBox.add(jComboBoxOvertimeHours);
        jComboBoxOvertimeHours.setBounds(180, 80, 250, 40);

        jDateChooserOvertimeDate.setFocusable(false);
        jPanelOvertimeRequestBox.add(jDateChooserOvertimeDate);
        jDateChooserOvertimeDate.setBounds(180, 40, 250, 40);

        jButtonSubmit.setBackground(new java.awt.Color(0, 135, 0));
        jButtonSubmit.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButtonSubmit.setForeground(new java.awt.Color(255, 255, 255));
        jButtonSubmit.setText("Submit");
        jButtonSubmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSubmitActionPerformed(evt);
            }
        });
        jPanelOvertimeRequestBox.add(jButtonSubmit);
        jButtonSubmit.setBounds(860, 80, 180, 40);

        jLabelOvertimeHistory.setBackground(new java.awt.Color(255, 255, 255));
        jLabelOvertimeHistory.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelOvertimeHistory.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelOvertimeHistory.setText("OVERTIME HISTORY");
        jPanelOvertimeRequestBox.add(jLabelOvertimeHistory);
        jLabelOvertimeHistory.setBounds(20, 180, 140, 40);

        jTableOvertimeHistory.setAutoCreateRowSorter(true);
        jTableOvertimeHistory.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Overtime ID", "Request Date", "Overtime Work Date", "Hours", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableOvertimeHistory.setFocusable(false);
        jTableOvertimeHistory.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTableOvertimeHistory.setShowGrid(true);
        jTableOvertimeHistory.getTableHeader().setResizingAllowed(false);
        jTableOvertimeHistory.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(jTableOvertimeHistory);

        jPanelOvertimeRequestBox.add(jScrollPane2);
        jScrollPane2.setBounds(20, 220, 1020, 330);

        jLabelReason.setBackground(new java.awt.Color(255, 255, 255));
        jLabelReason.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelReason.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelReason.setText("Reason :");
        jPanelOvertimeRequestBox.add(jLabelReason);
        jLabelReason.setBounds(480, 40, 70, 40);

        jTextAreaReason.setColumns(20);
        jTextAreaReason.setLineWrap(true);
        jTextAreaReason.setRows(5);
        jScrollPane3.setViewportView(jTextAreaReason);

        jPanelOvertimeRequestBox.add(jScrollPane3);
        jScrollPane3.setBounds(560, 40, 250, 80);

        jButtonView.setBackground(new java.awt.Color(0, 43, 89));
        jButtonView.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButtonView.setForeground(new java.awt.Color(255, 255, 255));
        jButtonView.setText("View");
        jButtonView.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonViewActionPerformed(evt);
            }
        });
        jPanelOvertimeRequestBox.add(jButtonView);
        jButtonView.setBounds(860, 570, 180, 40);

        jLabelAsterisk1.setBackground(new java.awt.Color(255, 255, 255));
        jLabelAsterisk1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelAsterisk1.setForeground(new java.awt.Color(255, 0, 0));
        jLabelAsterisk1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelAsterisk1.setText("*");
        jPanelOvertimeRequestBox.add(jLabelAsterisk1);
        jLabelAsterisk1.setBounds(100, 30, 50, 50);

        jLabelAsterisk2.setBackground(new java.awt.Color(255, 255, 255));
        jLabelAsterisk2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelAsterisk2.setForeground(new java.awt.Color(255, 0, 0));
        jLabelAsterisk2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelAsterisk2.setText("*");
        jPanelOvertimeRequestBox.add(jLabelAsterisk2);
        jLabelAsterisk2.setBounds(110, 70, 40, 50);

        jPanel1.add(jPanelOvertimeRequestBox);
        jPanelOvertimeRequestBox.setBounds(30, 110, 1060, 640);

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBoxOvertimeHoursActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxOvertimeHoursActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxOvertimeHoursActionPerformed

    private void jButtonSubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSubmitActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonSubmitActionPerformed

    private void jButtonViewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonViewActionPerformed
        // TODO add your handling code here:
        CardLayout cardLayout = (CardLayout) employeePortal.getPanelParentCard().getLayout();
        cardLayout.show(employeePortal.getPanelParentCard(), "ViewOvertime");
    }//GEN-LAST:event_jButtonViewActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonSubmit;
    private javax.swing.JButton jButtonView;
    private javax.swing.JComboBox<String> jComboBoxOvertimeHours;
    private com.toedter.calendar.JDateChooser jDateChooserOvertimeDate;
    private javax.swing.JLabel jLabelAsterisk1;
    private javax.swing.JLabel jLabelAsterisk2;
    private javax.swing.JLabel jLabelHelloEmployee;
    private javax.swing.JLabel jLabelOvertimeDate;
    private javax.swing.JLabel jLabelOvertimeHistory;
    private javax.swing.JLabel jLabelOvertimeHours;
    private javax.swing.JLabel jLabelOvertimeSmall;
    private javax.swing.JLabel jLabelReason;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanelOvertimeRequestBox;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTableOvertimeHistory;
    private javax.swing.JTextArea jTextAreaReason;
    // End of variables declaration//GEN-END:variables
}