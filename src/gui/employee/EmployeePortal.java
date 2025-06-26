package gui.employee;

import gui.admin.hr.*;
import gui.admin.finance.*;
import gui.admin.it.*;
import gui.login.LoginPortal;
import java.awt.CardLayout;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import service.AuthenticationService;
import util.UIUtil;

public class EmployeePortal extends javax.swing.JFrame {
    private final Color defaultPanelColor = Color.WHITE;
    private final Color hoverPanelColor = new Color(55, 128, 205);
    private final EmployeeDashboardPanel employeeDashboardPanel;
    private final ProfilePanel profilePanel;
    private final ChangePasswordPanel changePasswordPanel;
    private final AttendancePanel attendancePanel;
    private final PayslipPanel payslipPanel;
    private final LeavePanel leavePanel;
    private final ViewLeavePanel viewLeavePanel;
    private final OvertimePanel overtimePanel;
    private final ViewOvertimePanel viewOvertimePanel;
    private final SupportPanel supportPanel;
    private final ViewTicketPanel viewTicketPanel;
    private final AuthenticationService authService;

    public EmployeePortal() {
        UIUtil.setFlatLafUI();
        this.setTitle("MotorPH Employee Portal");
        setEmployeeWindowIcon();
        this.authService = new AuthenticationService();
        initComponents();
        employeeDashboardPanel = new EmployeeDashboardPanel(this);
        profilePanel = new ProfilePanel(this);
        changePasswordPanel = new ChangePasswordPanel(this);
        attendancePanel = new AttendancePanel(this);
        payslipPanel = new PayslipPanel(this);
        leavePanel = new LeavePanel(this);
        viewLeavePanel = new ViewLeavePanel(this);
        overtimePanel = new OvertimePanel(this);
        viewOvertimePanel = new ViewOvertimePanel(this);
        supportPanel = new SupportPanel(this);
        viewTicketPanel = new ViewTicketPanel(this);
        addHooverEffectToTabs();
        addPanels();
        configureAdminPortalButton();
        this.setLocationRelativeTo(null);
    }
    
    private void setEmployeeWindowIcon() {
        UIUtil.setWindowIcon(this, "/images/title-blue-motor-logo.png");
    }
    
    public JPanel getPanelParentCard() {
        return this.jPanelParentCard;
    }
    
    public EmployeeDashboardPanel getEmployeeDashboardPanel() {
        return employeeDashboardPanel;
    }
    
    public ViewLeavePanel getViewLeavePanel() {
        return viewLeavePanel;
    }
    
    public ViewOvertimePanel getViewOvertimePanel() {
        return viewOvertimePanel;
    }
    
    public ViewTicketPanel getViewTicketPanel() {
        return viewTicketPanel;
    }
    
    private void addPanels() {
        getPanelParentCard().add(employeeDashboardPanel, "EmployeeDashboard");
        getPanelParentCard().add(profilePanel, "Profile");
        getPanelParentCard().add(changePasswordPanel, "ChangePassword");
        getPanelParentCard().add(attendancePanel, "Attendance");
        getPanelParentCard().add(payslipPanel, "Payslip");
        getPanelParentCard().add(leavePanel, "Leave");
        getPanelParentCard().add(viewLeavePanel, "ViewLeave");
        getPanelParentCard().add(overtimePanel, "Overtime");
        getPanelParentCard().add(viewOvertimePanel, "ViewOvertime");
        getPanelParentCard().add(supportPanel, "Support");
        getPanelParentCard().add(viewTicketPanel, "ViewTicket");
    }

    private void addHooverEffectToTabs() {
        UIUtil.addHoverEffect(jLabelEmployeeDashboard, jPanelEmployeeDashboard, defaultPanelColor, hoverPanelColor);
        UIUtil.addHoverEffect(jLabelProfile, jPanelProfile, defaultPanelColor, hoverPanelColor);
        UIUtil.addHoverEffect(jLabelAttendance, jPanelAttendance, defaultPanelColor, hoverPanelColor);
        UIUtil.addHoverEffect(jLabelPayslip, jPanelPayslip, defaultPanelColor, hoverPanelColor);
        UIUtil.addHoverEffect(jLabelLeave, jPanelLeave, defaultPanelColor, hoverPanelColor);
        UIUtil.addHoverEffect(jLabelOvertime, jPanelOvertime, defaultPanelColor, hoverPanelColor);
        UIUtil.addHoverEffect(jLabelSupport, jPanelSupport, defaultPanelColor, hoverPanelColor);
        UIUtil.addHoverEffect(jLabelAdminPortal, jPanelAdminPortal, defaultPanelColor, hoverPanelColor);
    }
    
    private void configureAdminPortalButton() {
        boolean isAdmin = authService.isAdmin();

        // Hide button for plain employees
        jLabelAdminPortal.setVisible(isAdmin);
        jPanelAdminPortal.setVisible(isAdmin);
        
        // Exit guard
        if (!isAdmin) {
            return;
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelWhiteMain = new javax.swing.JPanel();
        jPanelBlueNavigationMenu = new javax.swing.JPanel();
        jPanelSupport = new javax.swing.JPanel();
        jLabelSupport = new javax.swing.JLabel();
        jPanelAdminPortal = new javax.swing.JPanel();
        jLabelAdminPortal = new javax.swing.JLabel();
        jPanelPayslip = new javax.swing.JPanel();
        jLabelPayslip = new javax.swing.JLabel();
        jPanelProfile = new javax.swing.JPanel();
        jLabelProfile = new javax.swing.JLabel();
        jPanelOvertime = new javax.swing.JPanel();
        jLabelOvertime = new javax.swing.JLabel();
        jPanelAttendance = new javax.swing.JPanel();
        jLabelAttendance = new javax.swing.JLabel();
        jLabelMotorPH = new javax.swing.JLabel();
        jPanelLeave = new javax.swing.JPanel();
        jLabelLeave = new javax.swing.JLabel();
        jLabelMorPHLogo = new javax.swing.JLabel();
        jLabelLogOut = new javax.swing.JLabel();
        jPanelEmployeeDashboard = new javax.swing.JPanel();
        jLabelEmployeeDashboard = new javax.swing.JLabel();
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

        jPanelBlueNavigationMenu.setBackground(new java.awt.Color(229, 242, 255));
        jPanelBlueNavigationMenu.setPreferredSize(new java.awt.Dimension(200, 600));
        jPanelBlueNavigationMenu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanelSupport.setBackground(new java.awt.Color(255, 255, 255));
        jPanelSupport.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelSupport.setBackground(new java.awt.Color(255, 255, 255));
        jLabelSupport.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabelSupport.setForeground(new java.awt.Color(0, 0, 0));
        jLabelSupport.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelSupport.setText("Support");
        jLabelSupport.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelSupportMouseClicked(evt);
            }
        });
        jPanelSupport.add(jLabelSupport, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 180, 40));

        jPanelBlueNavigationMenu.add(jPanelSupport, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 500, 180, 40));

        jPanelAdminPortal.setBackground(new java.awt.Color(255, 255, 255));
        jPanelAdminPortal.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelAdminPortal.setBackground(new java.awt.Color(255, 255, 255));
        jLabelAdminPortal.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabelAdminPortal.setForeground(new java.awt.Color(0, 0, 0));
        jLabelAdminPortal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelAdminPortal.setText("Admin Portal");
        jLabelAdminPortal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelAdminPortalMouseClicked(evt);
            }
        });
        jPanelAdminPortal.add(jLabelAdminPortal, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 180, 40));

        jPanelBlueNavigationMenu.add(jPanelAdminPortal, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 550, 180, 40));

        jPanelPayslip.setBackground(new java.awt.Color(255, 255, 255));
        jPanelPayslip.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelPayslip.setBackground(new java.awt.Color(255, 255, 255));
        jLabelPayslip.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabelPayslip.setForeground(new java.awt.Color(0, 0, 0));
        jLabelPayslip.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelPayslip.setText("Payslip");
        jLabelPayslip.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelPayslipMouseClicked(evt);
            }
        });
        jPanelPayslip.add(jLabelPayslip, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 180, 40));

        jPanelBlueNavigationMenu.add(jPanelPayslip, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 350, 180, 40));

        jPanelProfile.setBackground(new java.awt.Color(255, 255, 255));
        jPanelProfile.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelProfile.setBackground(new java.awt.Color(255, 255, 255));
        jLabelProfile.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabelProfile.setForeground(new java.awt.Color(0, 0, 0));
        jLabelProfile.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelProfile.setText("Profile");
        jLabelProfile.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jLabelProfileMouseMoved(evt);
            }
        });
        jLabelProfile.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelProfileMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabelProfileMouseEntered(evt);
            }
        });
        jLabelProfile.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jLabelProfileKeyPressed(evt);
            }
        });
        jPanelProfile.add(jLabelProfile, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 180, 40));

        jPanelBlueNavigationMenu.add(jPanelProfile, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, 180, 40));

        jPanelOvertime.setBackground(new java.awt.Color(255, 255, 255));
        jPanelOvertime.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelOvertime.setBackground(new java.awt.Color(255, 255, 255));
        jLabelOvertime.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabelOvertime.setForeground(new java.awt.Color(0, 0, 0));
        jLabelOvertime.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelOvertime.setText("Overtime");
        jLabelOvertime.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelOvertimeMouseClicked(evt);
            }
        });
        jPanelOvertime.add(jLabelOvertime, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 180, 40));

        jPanelBlueNavigationMenu.add(jPanelOvertime, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 450, 180, 40));

        jPanelAttendance.setBackground(new java.awt.Color(255, 255, 255));
        jPanelAttendance.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelAttendance.setBackground(new java.awt.Color(255, 255, 255));
        jLabelAttendance.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabelAttendance.setForeground(new java.awt.Color(0, 0, 0));
        jLabelAttendance.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelAttendance.setText("Attendance");
        jLabelAttendance.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelAttendanceMouseClicked(evt);
            }
        });
        jPanelAttendance.add(jLabelAttendance, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 180, 40));

        jPanelBlueNavigationMenu.add(jPanelAttendance, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 300, 180, 40));

        jLabelMotorPH.setBackground(new java.awt.Color(255, 255, 255));
        jLabelMotorPH.setFont(new java.awt.Font("Segoe UI", 1, 28)); // NOI18N
        jLabelMotorPH.setForeground(new java.awt.Color(0, 43, 175));
        jLabelMotorPH.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelMotorPH.setText("MotorPH");
        jPanelBlueNavigationMenu.add(jLabelMotorPH, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 30, -1, 40));

        jPanelLeave.setBackground(new java.awt.Color(255, 255, 255));
        jPanelLeave.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelLeave.setBackground(new java.awt.Color(255, 255, 255));
        jLabelLeave.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabelLeave.setForeground(new java.awt.Color(0, 0, 0));
        jLabelLeave.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelLeave.setText("Leave");
        jLabelLeave.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelLeaveMouseClicked(evt);
            }
        });
        jPanelLeave.add(jLabelLeave, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 180, 40));

        jPanelBlueNavigationMenu.add(jPanelLeave, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 400, 180, 40));

        jLabelMorPHLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/blue-motor-logo.png"))); // NOI18N
        jPanelBlueNavigationMenu.add(jLabelMorPHLogo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 50, 40));

        jLabelLogOut.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/blue-log-out-icon.png"))); // NOI18N
        jLabelLogOut.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelLogOutMouseClicked(evt);
            }
        });
        jPanelBlueNavigationMenu.add(jLabelLogOut, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 660, -1, -1));

        jPanelEmployeeDashboard.setBackground(new java.awt.Color(255, 255, 255));
        jPanelEmployeeDashboard.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelEmployeeDashboard.setBackground(new java.awt.Color(255, 255, 255));
        jLabelEmployeeDashboard.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabelEmployeeDashboard.setForeground(new java.awt.Color(0, 0, 0));
        jLabelEmployeeDashboard.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelEmployeeDashboard.setText("Dashboard");
        jLabelEmployeeDashboard.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelEmployeeDashboardMouseClicked(evt);
            }
        });
        jPanelEmployeeDashboard.add(jLabelEmployeeDashboard, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 180, 40));

        jPanelBlueNavigationMenu.add(jPanelEmployeeDashboard, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, 180, 40));

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

    private void jLabelLeaveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelLeaveMouseClicked
        // TODO add your handling code here:
        CardLayout cardLayout = (CardLayout) getPanelParentCard().getLayout();
        cardLayout.show(getPanelParentCard(), "Leave");
    }//GEN-LAST:event_jLabelLeaveMouseClicked

    private void jLabelAttendanceMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelAttendanceMouseClicked
        // TODO add your handling code here:
        CardLayout cardLayout = (CardLayout) getPanelParentCard().getLayout();
        cardLayout.show(getPanelParentCard(), "Attendance");
    }//GEN-LAST:event_jLabelAttendanceMouseClicked

    private void jLabelOvertimeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelOvertimeMouseClicked
        // TODO add your handling code here:
        CardLayout cardLayout = (CardLayout) getPanelParentCard().getLayout();
        cardLayout.show(getPanelParentCard(), "Overtime");
    }//GEN-LAST:event_jLabelOvertimeMouseClicked

    private void jLabelProfileKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jLabelProfileKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabelProfileKeyPressed

    private void jLabelProfileMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelProfileMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabelProfileMouseEntered

    private void jLabelProfileMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelProfileMouseClicked
        // TODO add your handling code here:
        CardLayout cardLayout = (CardLayout) getPanelParentCard().getLayout();
        cardLayout.show(getPanelParentCard(), "Profile");
    }//GEN-LAST:event_jLabelProfileMouseClicked

    private void jLabelProfileMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelProfileMouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabelProfileMouseMoved

    private void jLabelPayslipMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelPayslipMouseClicked
        // TODO add your handling code here:
        CardLayout cardLayout = (CardLayout) getPanelParentCard().getLayout();
        cardLayout.show(getPanelParentCard(), "Payslip");
    }//GEN-LAST:event_jLabelPayslipMouseClicked

    private void jLabelSupportMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelSupportMouseClicked
        // TODO add your handling code here:
        CardLayout cardLayout = (CardLayout) getPanelParentCard().getLayout();
        cardLayout.show(getPanelParentCard(), "Support");
    }//GEN-LAST:event_jLabelSupportMouseClicked

    private void jLabelEmployeeDashboardMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelEmployeeDashboardMouseClicked
        // TODO add your handling code here:
        CardLayout cardLayout = (CardLayout) getPanelParentCard().getLayout();
        cardLayout.show(getPanelParentCard(), "EmployeeDashboard");
    }//GEN-LAST:event_jLabelEmployeeDashboardMouseClicked

    private void jLabelAdminPortalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelAdminPortalMouseClicked
        // TODO add your handling code here:
        AuthenticationService authService = new AuthenticationService();

        // Launch the correct admin portal (or null if not an admin)
        JFrame adminFrame = authService.launchAdminPortal();

        if (adminFrame != null) {
            this.dispose();
            adminFrame.setVisible(true);
        } else {
            UIUtil.showErrorMessage(this, "You are not authorized to open an admin portal.", "Access Denied");
        }
    }//GEN-LAST:event_jLabelAdminPortalMouseClicked

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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabelAdminPortal;
    private javax.swing.JLabel jLabelAttendance;
    private javax.swing.JLabel jLabelEmployeeDashboard;
    private javax.swing.JLabel jLabelLeave;
    private javax.swing.JLabel jLabelLogOut;
    private javax.swing.JLabel jLabelMorPHLogo;
    private javax.swing.JLabel jLabelMotorPH;
    private javax.swing.JLabel jLabelOvertime;
    private javax.swing.JLabel jLabelPayslip;
    private javax.swing.JLabel jLabelProfile;
    private javax.swing.JLabel jLabelSupport;
    private javax.swing.JPanel jPanelAdminPortal;
    private javax.swing.JPanel jPanelAttendance;
    private javax.swing.JPanel jPanelBlueNavigationMenu;
    private javax.swing.JPanel jPanelEmployeeDashboard;
    private javax.swing.JPanel jPanelLeave;
    private javax.swing.JPanel jPanelOvertime;
    private javax.swing.JPanel jPanelParentCard;
    private javax.swing.JPanel jPanelPayslip;
    private javax.swing.JPanel jPanelProfile;
    private javax.swing.JPanel jPanelSupport;
    private javax.swing.JPanel jPanelWhiteMain;
    // End of variables declaration//GEN-END:variables
}
