package gui.admin.finance;

import gui.employee.EmployeePortal;
import gui.login.LoginPortal;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class AdminFinancePortal extends javax.swing.JFrame {
    private final Color defaultPanelColor = Color.WHITE;
    private final Color hoverPanelColor = new Color(33, 105, 84);
    private final FinanceDashboardPanel financeDashboardPanel;
    private final TicketsPanel ticketsPanel;
    private final ViewTicketPanel viewTicketPanel;
    private final CompensationPanel compensationPanel;
    private final ViewCompensationPanel viewCompensationPanel;
    private final PayrollPanel payrollPanel;
    private final ReportsPanel reportsPanel;

    public AdminFinancePortal() {
        setFlatLafUI();
        this.setTitle("MotorPH Finance Portal");
        setWindowIcon();
        financeDashboardPanel = new FinanceDashboardPanel(this);
        ticketsPanel = new TicketsPanel(this);
        viewTicketPanel = new ViewTicketPanel(this);
        compensationPanel = new CompensationPanel(this);
        viewCompensationPanel = new ViewCompensationPanel(this);
        payrollPanel = new PayrollPanel(this);
        reportsPanel = new ReportsPanel(this);
        initComponents();
        addHooverEffectToTabs();
        addPanels();
        this.setLocationRelativeTo(null);
    }
    
    private void setFlatLafUI() {
        try {
            com.formdev.flatlaf.FlatIntelliJLaf.setup();
        } catch (Exception ex) {
            System.err.println("Failed to initialize FlatLaf.");
        }
    }
    
    private void setWindowIcon() {
        try {
            Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/title-green-motor-logo.png"));
            this.setIconImage(icon);
        } catch (Exception e) {
            System.err.println("Icon image not found.");
        }
    }
    
    public JPanel getPanelParentCard() {
        return this.jPanelParentCard;
    }
    
    private void addPanels() {
        getPanelParentCard().add(financeDashboardPanel, "FinanceDashboard");
        getPanelParentCard().add(ticketsPanel, "Tickets");
        getPanelParentCard().add(viewTicketPanel, "ViewTicket");
        getPanelParentCard().add(compensationPanel, "Compensation");
        getPanelParentCard().add(viewCompensationPanel, "ViewCompensation");
        getPanelParentCard().add(payrollPanel, "Payroll");
        getPanelParentCard().add(reportsPanel, "Reports");
    }
    
    private void addHooverEffectToTabs() {
        addHoverEffect(jLabelFinanceDashboard, jPanelFinanceDashboard);
        addHoverEffect(jLabelEmployeePortal, jPanelEmployeePortal);
        addHoverEffect(jLabelTickets, jPanelTickets);
        addHoverEffect(jLabelCompensation, jPanelCompensation);
        addHoverEffect(jLabelPayroll, jPanelPayroll);
        addHoverEffect(jLabelReports, jPanelReports);
    }

    private void addHoverEffect(JLabel label, JPanel panel) {
        Font originalFont = label.getFont();

        label.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panel.setBackground(hoverPanelColor);
                label.setFont(originalFont.deriveFont(Font.BOLD));
                label.setForeground(Color.WHITE);
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panel.setBackground(defaultPanelColor);
                label.setFont(originalFont);
                label.setForeground(Color.BLACK);
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelWhiteMain = new javax.swing.JPanel();
        jPanelBlueNavigationMenu = new javax.swing.JPanel();
        jPanelTickets = new javax.swing.JPanel();
        jLabelTickets = new javax.swing.JLabel();
        jPanelFinanceDashboard = new javax.swing.JPanel();
        jLabelFinanceDashboard = new javax.swing.JLabel();
        jPanelEmployeePortal = new javax.swing.JPanel();
        jLabelEmployeePortal = new javax.swing.JLabel();
        jPanelCompensation = new javax.swing.JPanel();
        jLabelCompensation = new javax.swing.JLabel();
        jPanelPayroll = new javax.swing.JPanel();
        jLabelPayroll = new javax.swing.JLabel();
        jLabelMotorPH = new javax.swing.JLabel();
        jPanelReports = new javax.swing.JPanel();
        jLabelReports = new javax.swing.JLabel();
        jLabelMorPHLogo = new javax.swing.JLabel();
        jLabelLogOut = new javax.swing.JLabel();
        jPanelParentCard = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setMinimumSize(new java.awt.Dimension(1366, 768));
        setResizable(false);
        setSize(new java.awt.Dimension(1366, 768));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanelWhiteMain.setBackground(new java.awt.Color(255, 255, 255));
        jPanelWhiteMain.setForeground(new java.awt.Color(255, 255, 255));
        jPanelWhiteMain.setMaximumSize(new java.awt.Dimension(1366, 768));
        jPanelWhiteMain.setMinimumSize(new java.awt.Dimension(1366, 768));
        jPanelWhiteMain.setName(""); // NOI18N
        jPanelWhiteMain.setPreferredSize(new java.awt.Dimension(1366, 768));
        jPanelWhiteMain.setLayout(null);

        jPanelBlueNavigationMenu.setBackground(new java.awt.Color(218, 242, 228));
        jPanelBlueNavigationMenu.setPreferredSize(new java.awt.Dimension(200, 600));
        jPanelBlueNavigationMenu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanelTickets.setBackground(new java.awt.Color(255, 255, 255));
        jPanelTickets.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelTickets.setBackground(new java.awt.Color(255, 255, 255));
        jLabelTickets.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabelTickets.setForeground(new java.awt.Color(0, 0, 0));
        jLabelTickets.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelTickets.setText("Tickets");
        jLabelTickets.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelTicketsMouseClicked(evt);
            }
        });
        jPanelTickets.add(jLabelTickets, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 180, 40));

        jPanelBlueNavigationMenu.add(jPanelTickets, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 300, 180, 40));

        jPanelFinanceDashboard.setBackground(new java.awt.Color(255, 255, 255));
        jPanelFinanceDashboard.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelFinanceDashboard.setBackground(new java.awt.Color(255, 255, 255));
        jLabelFinanceDashboard.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabelFinanceDashboard.setForeground(new java.awt.Color(0, 0, 0));
        jLabelFinanceDashboard.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelFinanceDashboard.setText("Dashboard");
        jLabelFinanceDashboard.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelFinanceDashboardMouseClicked(evt);
            }
        });
        jPanelFinanceDashboard.add(jLabelFinanceDashboard, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 180, 40));

        jPanelBlueNavigationMenu.add(jPanelFinanceDashboard, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, 180, 40));

        jPanelEmployeePortal.setBackground(new java.awt.Color(255, 255, 255));
        jPanelEmployeePortal.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelEmployeePortal.setBackground(new java.awt.Color(255, 255, 255));
        jLabelEmployeePortal.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabelEmployeePortal.setForeground(new java.awt.Color(0, 0, 0));
        jLabelEmployeePortal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelEmployeePortal.setText("Employee Portal");
        jLabelEmployeePortal.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jLabelEmployeePortalMouseMoved(evt);
            }
        });
        jLabelEmployeePortal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelEmployeePortalMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabelEmployeePortalMouseEntered(evt);
            }
        });
        jLabelEmployeePortal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jLabelEmployeePortalKeyPressed(evt);
            }
        });
        jPanelEmployeePortal.add(jLabelEmployeePortal, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 180, 40));

        jPanelBlueNavigationMenu.add(jPanelEmployeePortal, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, 180, 40));

        jPanelCompensation.setBackground(new java.awt.Color(255, 255, 255));
        jPanelCompensation.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelCompensation.setBackground(new java.awt.Color(255, 255, 255));
        jLabelCompensation.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabelCompensation.setForeground(new java.awt.Color(0, 0, 0));
        jLabelCompensation.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelCompensation.setText("Compensation");
        jLabelCompensation.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelCompensationMouseClicked(evt);
            }
        });
        jPanelCompensation.add(jLabelCompensation, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 180, 40));

        jPanelBlueNavigationMenu.add(jPanelCompensation, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 350, 180, 40));

        jPanelPayroll.setBackground(new java.awt.Color(255, 255, 255));
        jPanelPayroll.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelPayroll.setBackground(new java.awt.Color(255, 255, 255));
        jLabelPayroll.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabelPayroll.setForeground(new java.awt.Color(0, 0, 0));
        jLabelPayroll.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelPayroll.setText("Payroll");
        jLabelPayroll.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelPayrollMouseClicked(evt);
            }
        });
        jPanelPayroll.add(jLabelPayroll, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 180, 40));

        jPanelBlueNavigationMenu.add(jPanelPayroll, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 400, 180, 40));

        jLabelMotorPH.setBackground(new java.awt.Color(255, 255, 255));
        jLabelMotorPH.setFont(new java.awt.Font("Segoe UI", 1, 28)); // NOI18N
        jLabelMotorPH.setForeground(new java.awt.Color(33, 105, 84));
        jLabelMotorPH.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelMotorPH.setText("MotorPH");
        jPanelBlueNavigationMenu.add(jLabelMotorPH, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 30, -1, 40));

        jPanelReports.setBackground(new java.awt.Color(255, 255, 255));
        jPanelReports.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelReports.setBackground(new java.awt.Color(255, 255, 255));
        jLabelReports.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabelReports.setForeground(new java.awt.Color(0, 0, 0));
        jLabelReports.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelReports.setText("Reports");
        jLabelReports.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelReportsMouseClicked(evt);
            }
        });
        jPanelReports.add(jLabelReports, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 180, 40));

        jPanelBlueNavigationMenu.add(jPanelReports, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 450, 180, 40));

        jLabelMorPHLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/green-motor-logo.png"))); // NOI18N
        jPanelBlueNavigationMenu.add(jLabelMorPHLogo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 50, 40));

        jLabelLogOut.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/green-log-out-icon.png"))); // NOI18N
        jLabelLogOut.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelLogOutMouseClicked(evt);
            }
        });
        jPanelBlueNavigationMenu.add(jLabelLogOut, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 660, -1, -1));

        jPanelWhiteMain.add(jPanelBlueNavigationMenu);
        jPanelBlueNavigationMenu.setBounds(10, 10, 220, 750);

        jPanelParentCard.setBackground(new java.awt.Color(204, 255, 255));
        jPanelParentCard.setMaximumSize(new java.awt.Dimension(1120, 750));
        jPanelParentCard.setMinimumSize(new java.awt.Dimension(1120, 750));
        jPanelParentCard.setPreferredSize(new java.awt.Dimension(1120, 750));
        jPanelParentCard.setLayout(new java.awt.CardLayout());
        jPanelWhiteMain.add(jPanelParentCard);
        jPanelParentCard.setBounds(240, 10, 1120, 750);

        getContentPane().add(jPanelWhiteMain, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1370, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabelReportsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelReportsMouseClicked
        // TODO add your handling code here:
        CardLayout cardLayout = (CardLayout) getPanelParentCard().getLayout();
        cardLayout.show(getPanelParentCard(), "Reports");
    }//GEN-LAST:event_jLabelReportsMouseClicked

    private void jLabelPayrollMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelPayrollMouseClicked
        // TODO add your handling code here:
        CardLayout cardLayout = (CardLayout) getPanelParentCard().getLayout();
        cardLayout.show(getPanelParentCard(), "Payroll");
    }//GEN-LAST:event_jLabelPayrollMouseClicked

    private void jLabelEmployeePortalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jLabelEmployeePortalKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabelEmployeePortalKeyPressed

    private void jLabelEmployeePortalMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelEmployeePortalMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabelEmployeePortalMouseEntered

    private void jLabelEmployeePortalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelEmployeePortalMouseClicked
        // TODO add your handling code here:
        EmployeePortal employeePortal = new EmployeePortal();
        employeePortal.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabelEmployeePortalMouseClicked

    private void jLabelEmployeePortalMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelEmployeePortalMouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabelEmployeePortalMouseMoved

    private void jLabelTicketsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelTicketsMouseClicked
        // TODO add your handling code here:
        CardLayout cardLayout = (CardLayout) getPanelParentCard().getLayout();
        cardLayout.show(getPanelParentCard(), "Tickets");
    }//GEN-LAST:event_jLabelTicketsMouseClicked

    private void jLabelFinanceDashboardMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelFinanceDashboardMouseClicked
        // TODO add your handling code here:
        CardLayout cardLayout = (CardLayout) getPanelParentCard().getLayout();
        cardLayout.show(getPanelParentCard(), "FinanceDashboard");
    }//GEN-LAST:event_jLabelFinanceDashboardMouseClicked

    private void jLabelLogOutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelLogOutMouseClicked
        // TODO add your handling code here:
        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to log out?",
                "Confirm Logout",
                JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {
            LoginPortal loginPortal = new LoginPortal();
            loginPortal.setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_jLabelLogOutMouseClicked

    private void jLabelCompensationMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelCompensationMouseClicked
        // TODO add your handling code here:
        CardLayout cardLayout = (CardLayout) getPanelParentCard().getLayout();
        cardLayout.show(getPanelParentCard(), "Compensation");
    }//GEN-LAST:event_jLabelCompensationMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabelCompensation;
    private javax.swing.JLabel jLabelEmployeePortal;
    private javax.swing.JLabel jLabelFinanceDashboard;
    private javax.swing.JLabel jLabelLogOut;
    private javax.swing.JLabel jLabelMorPHLogo;
    private javax.swing.JLabel jLabelMotorPH;
    private javax.swing.JLabel jLabelPayroll;
    private javax.swing.JLabel jLabelReports;
    private javax.swing.JLabel jLabelTickets;
    private javax.swing.JPanel jPanelBlueNavigationMenu;
    private javax.swing.JPanel jPanelCompensation;
    private javax.swing.JPanel jPanelEmployeePortal;
    private javax.swing.JPanel jPanelFinanceDashboard;
    private javax.swing.JPanel jPanelParentCard;
    private javax.swing.JPanel jPanelPayroll;
    private javax.swing.JPanel jPanelReports;
    private javax.swing.JPanel jPanelTickets;
    private javax.swing.JPanel jPanelWhiteMain;
    // End of variables declaration//GEN-END:variables
}
