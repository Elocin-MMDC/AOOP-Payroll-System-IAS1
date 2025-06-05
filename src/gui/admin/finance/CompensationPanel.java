package gui.admin.finance;

import java.awt.CardLayout;

public class CompensationPanel extends javax.swing.JPanel {

    private final AdminFinancePortal financePortal;
    
    public CompensationPanel(AdminFinancePortal financePortal) {
        this.financePortal = financePortal;
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabelHelloAdmin = new javax.swing.JLabel();
        jLabelCompensationSmall = new javax.swing.JLabel();
        jPanelRecordsBox = new javax.swing.JPanel();
        jLabelEmployeeCompensation = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableEmployeeCompensation = new javax.swing.JTable();
        jButtonView = new javax.swing.JButton();
        jTextFieldSearchById4 = new javax.swing.JTextField();
        jLabelSeachByEmployeeID = new javax.swing.JLabel();

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

        jLabelCompensationSmall.setText("Compensation");
        jPanel1.add(jLabelCompensationSmall);
        jLabelCompensationSmall.setBounds(30, 60, 100, 16);

        jPanelRecordsBox.setBackground(new java.awt.Color(255, 255, 255));
        jPanelRecordsBox.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanelRecordsBox.setLayout(null);

        jLabelEmployeeCompensation.setBackground(new java.awt.Color(255, 255, 255));
        jLabelEmployeeCompensation.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelEmployeeCompensation.setForeground(new java.awt.Color(0, 0, 0));
        jLabelEmployeeCompensation.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelEmployeeCompensation.setText("EMPLOYEE COMPENSATION");
        jPanelRecordsBox.add(jLabelEmployeeCompensation);
        jLabelEmployeeCompensation.setBounds(20, 90, 240, 40);

        jTableEmployeeCompensation.setAutoCreateRowSorter(true);
        jTableEmployeeCompensation.setModel(new javax.swing.table.DefaultTableModel(
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
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Employee ID", "Last Name", "First Name", "SSS#", "PhilHealth#", "TIN", "Pag-Ibig#"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
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
        jTableEmployeeCompensation.setFocusable(false);
        jTableEmployeeCompensation.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTableEmployeeCompensation.setShowGrid(true);
        jTableEmployeeCompensation.getTableHeader().setResizingAllowed(false);
        jTableEmployeeCompensation.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jTableEmployeeCompensation);

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

        jTextFieldSearchById4.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        jTextFieldSearchById4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldSearchById4ActionPerformed(evt);
            }
        });
        jTextFieldSearchById4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldSearchById4KeyPressed(evt);
            }
        });
        jPanelRecordsBox.add(jTextFieldSearchById4);
        jTextFieldSearchById4.setBounds(200, 30, 260, 40);

        jLabelSeachByEmployeeID.setBackground(new java.awt.Color(255, 255, 255));
        jLabelSeachByEmployeeID.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelSeachByEmployeeID.setForeground(new java.awt.Color(0, 0, 0));
        jLabelSeachByEmployeeID.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelSeachByEmployeeID.setText("Search by Employee ID :");
        jPanelRecordsBox.add(jLabelSeachByEmployeeID);
        jLabelSeachByEmployeeID.setBounds(20, 30, 180, 40);

        jPanel1.add(jPanelRecordsBox);
        jPanelRecordsBox.setBounds(30, 100, 1060, 650);

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonViewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonViewActionPerformed
        // TODO add your handling code here:
        CardLayout cardLayout = (CardLayout) financePortal.getPanelParentCard().getLayout();
        cardLayout.show(financePortal.getPanelParentCard(), "ViewCompensation");
    }//GEN-LAST:event_jButtonViewActionPerformed

    private void jTextFieldSearchById4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldSearchById4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldSearchById4ActionPerformed

    private void jTextFieldSearchById4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldSearchById4KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldSearchById4KeyPressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonView;
    private javax.swing.JLabel jLabelCompensationSmall;
    private javax.swing.JLabel jLabelEmployeeCompensation;
    private javax.swing.JLabel jLabelHelloAdmin;
    private javax.swing.JLabel jLabelSeachByEmployeeID;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanelRecordsBox;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableEmployeeCompensation;
    private javax.swing.JTextField jTextFieldSearchById4;
    // End of variables declaration//GEN-END:variables
}