package gui.admin.it;

import java.awt.CardLayout;

public class SecurityPanel extends javax.swing.JPanel {

    private final AdminITPortal itPortal;
    
    public SecurityPanel(AdminITPortal itPortal) {
        this.itPortal = itPortal;
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabelHelloAdmin = new javax.swing.JLabel();
        jLabelSecuritySmall = new javax.swing.JLabel();
        jPanelRecordsBox = new javax.swing.JPanel();
        jLabelLoginLogs = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableLoginLogs = new javax.swing.JTable();
        jLabelAuditLogs = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableAuditLogs = new javax.swing.JTable();

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

        jLabelSecuritySmall.setText("Security");
        jPanel1.add(jLabelSecuritySmall);
        jLabelSecuritySmall.setBounds(30, 60, 100, 16);

        jPanelRecordsBox.setBackground(new java.awt.Color(255, 255, 255));
        jPanelRecordsBox.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanelRecordsBox.setLayout(null);

        jLabelLoginLogs.setBackground(new java.awt.Color(255, 255, 255));
        jLabelLoginLogs.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelLoginLogs.setForeground(new java.awt.Color(0, 0, 0));
        jLabelLoginLogs.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelLoginLogs.setText("LOGIN LOGS");
        jPanelRecordsBox.add(jLabelLoginLogs);
        jLabelLoginLogs.setBounds(20, 40, 210, 40);

        jTableLoginLogs.setAutoCreateRowSorter(true);
        jTableLoginLogs.setModel(new javax.swing.table.DefaultTableModel(
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
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Login ID", "Timestamp", "User ID", "Username", "Status", "Login Attempt", "Locked", "Lock End Time"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class
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
        jTableLoginLogs.setFocusable(false);
        jTableLoginLogs.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTableLoginLogs.setShowGrid(true);
        jTableLoginLogs.getTableHeader().setResizingAllowed(false);
        jTableLoginLogs.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jTableLoginLogs);

        jPanelRecordsBox.add(jScrollPane1);
        jScrollPane1.setBounds(20, 80, 1020, 220);

        jLabelAuditLogs.setBackground(new java.awt.Color(255, 255, 255));
        jLabelAuditLogs.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelAuditLogs.setForeground(new java.awt.Color(0, 0, 0));
        jLabelAuditLogs.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelAuditLogs.setText("AUDIT LOGS");
        jPanelRecordsBox.add(jLabelAuditLogs);
        jLabelAuditLogs.setBounds(20, 340, 210, 40);

        jTableAuditLogs.setAutoCreateRowSorter(true);
        jTableAuditLogs.setModel(new javax.swing.table.DefaultTableModel(
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
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Audit ID", "Timestamp", "User ID", "Action", "Entity Modified", "Entity ID", "Attribute Modified", "Old Value", "New Value"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
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
        jTableAuditLogs.setFocusable(false);
        jTableAuditLogs.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTableAuditLogs.setShowGrid(true);
        jTableAuditLogs.getTableHeader().setResizingAllowed(false);
        jTableAuditLogs.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(jTableAuditLogs);

        jPanelRecordsBox.add(jScrollPane2);
        jScrollPane2.setBounds(20, 380, 1020, 220);

        jPanel1.add(jPanelRecordsBox);
        jPanelRecordsBox.setBounds(30, 100, 1060, 650);

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabelAuditLogs;
    private javax.swing.JLabel jLabelHelloAdmin;
    private javax.swing.JLabel jLabelLoginLogs;
    private javax.swing.JLabel jLabelSecuritySmall;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanelRecordsBox;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTableAuditLogs;
    private javax.swing.JTable jTableLoginLogs;
    // End of variables declaration//GEN-END:variables
}