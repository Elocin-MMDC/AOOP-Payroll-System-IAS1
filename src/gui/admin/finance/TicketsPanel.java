package gui.admin.finance;

import java.awt.CardLayout;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import model.pojo.SupportRequest;
import service.RequestService;
import util.UIUtil;

public class TicketsPanel extends javax.swing.JPanel {

    private final AdminFinancePortal financePortal;
    private final RequestService requestService;
    
    public TicketsPanel(AdminFinancePortal financePortal) {
        this.financePortal = financePortal;
        this.requestService = new RequestService();
        initComponents();
        UIUtil.setGreeting(jLabelHelloAdmin, "Admin");
        loadTicketHistory();
    }
    
    public final void loadTicketHistory() {
        List<SupportRequest> tickets = requestService.getSupportHistoryByTeam("Finance");

        String[] cols = {
            "Ticket ID",
            "Request Date",
            "Employee ID",
            "Severity",
            "Subject",
            "Status"
        };

        UIUtil.styleTable(jTableTicketHistory, cols);

        DefaultTableModel model = (DefaultTableModel) jTableTicketHistory.getModel();

        for (SupportRequest sr : tickets) {

            model.addRow(new Object[]{
                sr.getTicketID(),
                sr.getDate(),
                sr.getEmployeeID(),
                sr.getSeverity(),
                sr.getSubject(),
                sr.getStatus()
            });
        }
        UIUtil.installSearchFilter(jTableTicketHistory, jTextFieldSearch, 0, 1, 2, 3, 4, 5, 6);
    }
    
    private void onViewClicked() {
        int row = jTableTicketHistory.getSelectedRow();
        if (row < 0) {
            UIUtil.showWarningMessage(this, "Please select a ticket to view.", "No Selection");
            return;
        }

        int ticketID = (int) jTableTicketHistory.getValueAt(row, 0);

        financePortal.getViewTicketPanel().loadSelectedTicket(ticketID);

        CardLayout cardLayout = (CardLayout) financePortal.getPanelParentCard().getLayout();
        cardLayout.show(financePortal.getPanelParentCard(), "ViewTicket");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabelHelloAdmin = new javax.swing.JLabel();
        jLabelTicketsSmall = new javax.swing.JLabel();
        jPanelRecordsBox = new javax.swing.JPanel();
        jLabelTicketManagement = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableTicketHistory = new javax.swing.JTable();
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
        jLabelHelloAdmin.setBounds(30, 30, 530, 29);

        jLabelTicketsSmall.setText("Tickets");
        jPanel1.add(jLabelTicketsSmall);
        jLabelTicketsSmall.setBounds(30, 60, 100, 16);

        jPanelRecordsBox.setBackground(new java.awt.Color(255, 255, 255));
        jPanelRecordsBox.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanelRecordsBox.setLayout(null);

        jLabelTicketManagement.setBackground(new java.awt.Color(255, 255, 255));
        jLabelTicketManagement.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelTicketManagement.setForeground(new java.awt.Color(0, 0, 0));
        jLabelTicketManagement.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelTicketManagement.setText("TICKET MANAGEMENT");
        jPanelRecordsBox.add(jLabelTicketManagement);
        jLabelTicketManagement.setBounds(20, 90, 180, 40);

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
                "Ticket ID", "Request Date", "Employee ID", "Severity", "Subject", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
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
        jScrollPane1.setViewportView(jTableTicketHistory);

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
    private javax.swing.JLabel jLabelSeach;
    private javax.swing.JLabel jLabelTicketManagement;
    private javax.swing.JLabel jLabelTicketsSmall;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanelRecordsBox;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableTicketHistory;
    private javax.swing.JTextField jTextFieldSearch;
    // End of variables declaration//GEN-END:variables
}