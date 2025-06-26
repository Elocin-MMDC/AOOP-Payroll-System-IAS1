package gui.admin.it;

import java.awt.CardLayout;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import model.pojo.UserAccountView;
import service.AccountService;
import util.UIUtil;

public class AccountsPanel extends javax.swing.JPanel {

    private final AdminITPortal itPortal;
    private final AccountService accountService;
    
    public AccountsPanel(AdminITPortal itPortal) {
        this.itPortal = itPortal;
        this.accountService = new AccountService();
        initComponents();
        UIUtil.setGreeting(jLabelHelloAdmin, "Admin");
        loadAccountRecords();
    }
    
    public final void loadAccountRecords() {
        List<UserAccountView> accounts = accountService.getAllAccounts();

        String[] cols = {
            "User ID",
            "Employee ID",
            "Name",
            "Username",
            "Role",
            "Account Status"
        };

        UIUtil.styleTable(jTableAccountRecords, cols);
        
        DefaultTableModel model = (DefaultTableModel) jTableAccountRecords.getModel();
        model.setRowCount(0);

        for (UserAccountView ua : accounts) {
            model.addRow(new Object[]{
                ua.getUserID(),
                ua.getEmployeeID(),
                ua.getFullName(),
                ua.getUsername(),
                ua.getRoleName(),
                ua.getAccountStatus()
            });
        }

        UIUtil.installSearchFilter(jTableAccountRecords, jTextFieldSearch, 0, 1, 2, 3, 4, 5);
    }
    
    private void onViewClicked() {
        int row = jTableAccountRecords.getSelectedRow();
        if (row < 0) {
            UIUtil.showWarningMessage(this, "Please select an account to view.", "No Selection");
            return;
        }

        int userId = (int) jTableAccountRecords.getValueAt(row, 0);
        itPortal.getViewAccountPanel().loadSelectedAccount(userId);

        CardLayout cardLayout = (CardLayout) itPortal.getPanelParentCard().getLayout();
        cardLayout.show(itPortal.getPanelParentCard(), "ViewAccount");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabelHelloAdmin = new javax.swing.JLabel();
        jLabelAccountsSmall = new javax.swing.JLabel();
        jPanelRecordsBox = new javax.swing.JPanel();
        jLabelAccountManagement = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableAccountRecords = new javax.swing.JTable();
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
        jLabelHelloAdmin.setBounds(30, 30, 590, 29);

        jLabelAccountsSmall.setText("Accounts");
        jPanel1.add(jLabelAccountsSmall);
        jLabelAccountsSmall.setBounds(30, 60, 100, 16);

        jPanelRecordsBox.setBackground(new java.awt.Color(255, 255, 255));
        jPanelRecordsBox.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanelRecordsBox.setLayout(null);

        jLabelAccountManagement.setBackground(new java.awt.Color(255, 255, 255));
        jLabelAccountManagement.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelAccountManagement.setForeground(new java.awt.Color(0, 0, 0));
        jLabelAccountManagement.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelAccountManagement.setText("ACCOUNT MANAGEMENT");
        jPanelRecordsBox.add(jLabelAccountManagement);
        jLabelAccountManagement.setBounds(20, 90, 210, 40);

        jTableAccountRecords.setAutoCreateRowSorter(true);
        jTableAccountRecords.setModel(new javax.swing.table.DefaultTableModel(
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
                {null, null, null, null, null, null}
            },
            new String [] {
                "User ID", "Employee ID", "Name", "Username", "Role", "Account Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
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
        jTableAccountRecords.setFocusable(false);
        jTableAccountRecords.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTableAccountRecords.setShowGrid(true);
        jTableAccountRecords.getTableHeader().setResizingAllowed(false);
        jTableAccountRecords.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jTableAccountRecords);

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
    private javax.swing.JLabel jLabelAccountManagement;
    private javax.swing.JLabel jLabelAccountsSmall;
    private javax.swing.JLabel jLabelHelloAdmin;
    private javax.swing.JLabel jLabelSeach;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanelRecordsBox;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableAccountRecords;
    private javax.swing.JTextField jTextFieldSearch;
    // End of variables declaration//GEN-END:variables
}