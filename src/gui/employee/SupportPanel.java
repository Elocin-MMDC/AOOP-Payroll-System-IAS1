package gui.employee;

import java.awt.CardLayout;

public class SupportPanel extends javax.swing.JPanel {
    
    private final EmployeePortal employeePortal;

    public SupportPanel(EmployeePortal employeePortal) {
        this.employeePortal = employeePortal;
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabelHelloEmployee = new javax.swing.JLabel();
        jLabelSupportSmall = new javax.swing.JLabel();
        jPanelSupportRequestBox = new javax.swing.JPanel();
        jLabelSubject = new javax.swing.JLabel();
        jLabelAssignedTeam = new javax.swing.JLabel();
        jComboBoxAssignedTeam = new javax.swing.JComboBox<>();
        jButtonSubmit = new javax.swing.JButton();
        jLabelTicketHistory = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableTicketHistory = new javax.swing.JTable();
        jLabelDescription = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextAreaDescription = new javax.swing.JTextArea();
        jTextFieldSubject = new javax.swing.JTextField();
        jButtonView = new javax.swing.JButton();
        jLabelAsterisk1 = new javax.swing.JLabel();
        jLabelSeverity = new javax.swing.JLabel();
        jComboBoxSeverity = new javax.swing.JComboBox<>();
        jLabelAsterisk3 = new javax.swing.JLabel();
        jLabelAsterisk2 = new javax.swing.JLabel();
        jLabelAsterisk4 = new javax.swing.JLabel();

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

        jLabelSupportSmall.setText("Support");
        jPanel1.add(jLabelSupportSmall);
        jLabelSupportSmall.setBounds(30, 60, 180, 16);

        jPanelSupportRequestBox.setBackground(new java.awt.Color(255, 255, 255));
        jPanelSupportRequestBox.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanelSupportRequestBox.setLayout(null);

        jLabelSubject.setBackground(new java.awt.Color(255, 255, 255));
        jLabelSubject.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelSubject.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelSubject.setText("Subject   :");
        jPanelSupportRequestBox.add(jLabelSubject);
        jLabelSubject.setBounds(20, 40, 140, 40);

        jLabelAssignedTeam.setBackground(new java.awt.Color(255, 255, 255));
        jLabelAssignedTeam.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelAssignedTeam.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelAssignedTeam.setText("Assigned Team   :");
        jPanelSupportRequestBox.add(jLabelAssignedTeam);
        jLabelAssignedTeam.setBounds(20, 120, 140, 40);

        jComboBoxAssignedTeam.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jComboBoxAssignedTeam.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select", "HR", "Finance", "IT" }));
        jComboBoxAssignedTeam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxAssignedTeamActionPerformed(evt);
            }
        });
        jPanelSupportRequestBox.add(jComboBoxAssignedTeam);
        jComboBoxAssignedTeam.setBounds(180, 120, 250, 40);

        jButtonSubmit.setBackground(new java.awt.Color(0, 135, 0));
        jButtonSubmit.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButtonSubmit.setForeground(new java.awt.Color(255, 255, 255));
        jButtonSubmit.setText("Submit");
        jButtonSubmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSubmitActionPerformed(evt);
            }
        });
        jPanelSupportRequestBox.add(jButtonSubmit);
        jButtonSubmit.setBounds(860, 80, 180, 40);

        jLabelTicketHistory.setBackground(new java.awt.Color(255, 255, 255));
        jLabelTicketHistory.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelTicketHistory.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelTicketHistory.setText("TICKET HISTORY");
        jPanelSupportRequestBox.add(jLabelTicketHistory);
        jLabelTicketHistory.setBounds(20, 180, 140, 40);

        jTableTicketHistory.setAutoCreateRowSorter(true);
        jTableTicketHistory.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Ticket ID", "Request Date", "Assigned Team", "Severity", "Subject", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableTicketHistory.setFocusable(false);
        jTableTicketHistory.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTableTicketHistory.setShowGrid(true);
        jTableTicketHistory.getTableHeader().setResizingAllowed(false);
        jTableTicketHistory.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(jTableTicketHistory);

        jPanelSupportRequestBox.add(jScrollPane2);
        jScrollPane2.setBounds(20, 220, 1020, 330);

        jLabelDescription.setBackground(new java.awt.Color(255, 255, 255));
        jLabelDescription.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelDescription.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelDescription.setText("Description   :");
        jPanelSupportRequestBox.add(jLabelDescription);
        jLabelDescription.setBounds(460, 40, 110, 40);

        jTextAreaDescription.setColumns(20);
        jTextAreaDescription.setLineWrap(true);
        jTextAreaDescription.setRows(5);
        jScrollPane3.setViewportView(jTextAreaDescription);

        jPanelSupportRequestBox.add(jScrollPane3);
        jScrollPane3.setBounds(560, 40, 250, 120);

        jTextFieldSubject.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        jTextFieldSubject.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldSubjectActionPerformed(evt);
            }
        });
        jPanelSupportRequestBox.add(jTextFieldSubject);
        jTextFieldSubject.setBounds(180, 40, 250, 40);

        jButtonView.setBackground(new java.awt.Color(0, 43, 89));
        jButtonView.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButtonView.setForeground(new java.awt.Color(255, 255, 255));
        jButtonView.setText("View");
        jButtonView.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonViewActionPerformed(evt);
            }
        });
        jPanelSupportRequestBox.add(jButtonView);
        jButtonView.setBounds(860, 570, 180, 40);

        jLabelAsterisk1.setBackground(new java.awt.Color(255, 255, 255));
        jLabelAsterisk1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelAsterisk1.setForeground(new java.awt.Color(255, 0, 0));
        jLabelAsterisk1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelAsterisk1.setText("*");
        jPanelSupportRequestBox.add(jLabelAsterisk1);
        jLabelAsterisk1.setBounds(520, 30, 40, 50);

        jLabelSeverity.setBackground(new java.awt.Color(255, 255, 255));
        jLabelSeverity.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelSeverity.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelSeverity.setText("Severity   :");
        jPanelSupportRequestBox.add(jLabelSeverity);
        jLabelSeverity.setBounds(20, 80, 140, 40);

        jComboBoxSeverity.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jComboBoxSeverity.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select", "Low", "Medium", "High" }));
        jComboBoxSeverity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxSeverityActionPerformed(evt);
            }
        });
        jPanelSupportRequestBox.add(jComboBoxSeverity);
        jComboBoxSeverity.setBounds(180, 80, 250, 40);

        jLabelAsterisk3.setBackground(new java.awt.Color(255, 255, 255));
        jLabelAsterisk3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelAsterisk3.setForeground(new java.awt.Color(255, 0, 0));
        jLabelAsterisk3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelAsterisk3.setText("*");
        jPanelSupportRequestBox.add(jLabelAsterisk3);
        jLabelAsterisk3.setBounds(50, 70, 60, 50);

        jLabelAsterisk2.setBackground(new java.awt.Color(255, 255, 255));
        jLabelAsterisk2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelAsterisk2.setForeground(new java.awt.Color(255, 0, 0));
        jLabelAsterisk2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelAsterisk2.setText("*");
        jPanelSupportRequestBox.add(jLabelAsterisk2);
        jLabelAsterisk2.setBounds(100, 110, 50, 50);

        jLabelAsterisk4.setBackground(new java.awt.Color(255, 255, 255));
        jLabelAsterisk4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelAsterisk4.setForeground(new java.awt.Color(255, 0, 0));
        jLabelAsterisk4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelAsterisk4.setText("*");
        jPanelSupportRequestBox.add(jLabelAsterisk4);
        jLabelAsterisk4.setBounds(50, 30, 50, 50);

        jPanel1.add(jPanelSupportRequestBox);
        jPanelSupportRequestBox.setBounds(30, 110, 1060, 640);

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBoxAssignedTeamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxAssignedTeamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxAssignedTeamActionPerformed

    private void jButtonSubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSubmitActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonSubmitActionPerformed

    private void jTextFieldSubjectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldSubjectActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldSubjectActionPerformed

    private void jButtonViewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonViewActionPerformed
        // TODO add your handling code here:
        CardLayout cardLayout = (CardLayout) employeePortal.getPanelParentCard().getLayout();
        cardLayout.show(employeePortal.getPanelParentCard(), "ViewTicket");
    }//GEN-LAST:event_jButtonViewActionPerformed

    private void jComboBoxSeverityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxSeverityActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxSeverityActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonSubmit;
    private javax.swing.JButton jButtonView;
    private javax.swing.JComboBox<String> jComboBoxAssignedTeam;
    private javax.swing.JComboBox<String> jComboBoxSeverity;
    private javax.swing.JLabel jLabelAssignedTeam;
    private javax.swing.JLabel jLabelAsterisk1;
    private javax.swing.JLabel jLabelAsterisk2;
    private javax.swing.JLabel jLabelAsterisk3;
    private javax.swing.JLabel jLabelAsterisk4;
    private javax.swing.JLabel jLabelDescription;
    private javax.swing.JLabel jLabelHelloEmployee;
    private javax.swing.JLabel jLabelSeverity;
    private javax.swing.JLabel jLabelSubject;
    private javax.swing.JLabel jLabelSupportSmall;
    private javax.swing.JLabel jLabelTicketHistory;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanelSupportRequestBox;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTableTicketHistory;
    private javax.swing.JTextArea jTextAreaDescription;
    private javax.swing.JTextField jTextFieldSubject;
    // End of variables declaration//GEN-END:variables
}