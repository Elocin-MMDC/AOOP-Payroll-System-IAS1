package gui.login;

import javax.swing.JPanel;
import util.UIUtil;

public class LoginPortal extends javax.swing.JFrame {
    
    private final LoginPanel loginPanel;
    private final ForgotPasswordPanel forgotPasswordPanel;

    public LoginPortal() {
        UIUtil.setFlatLafUI();
        this.setTitle("MotorPH Login Portal");
        setLoginWindowIcon();
        loginPanel = new LoginPanel(this);
        forgotPasswordPanel = new ForgotPasswordPanel(this);
        initComponents();
        addPanels();
        this.setLocationRelativeTo(null);
    }
    private void setLoginWindowIcon() {
        UIUtil.setWindowIcon(this, "/images/title-bluegreen-motor-logo.png");
    }
    
    public JPanel getPanelParentCard() {
        return this.jPanelParentCard;
    }
    
    private void addPanels() {
        getPanelParentCard().add(loginPanel, "Login");
        getPanelParentCard().add(forgotPasswordPanel, "ForgotPassword");
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelWhiteMain = new javax.swing.JPanel();
        jPanelBlueNavigationMenu = new javax.swing.JPanel();
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

        jPanelBlueNavigationMenu.setBackground(new java.awt.Color(235, 252, 252));
        jPanelBlueNavigationMenu.setPreferredSize(new java.awt.Dimension(200, 600));
        jPanelBlueNavigationMenu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanelParentCard.setBackground(new java.awt.Color(204, 255, 255));
        jPanelParentCard.setMaximumSize(new java.awt.Dimension(1120, 750));
        jPanelParentCard.setMinimumSize(new java.awt.Dimension(1120, 750));
        jPanelParentCard.setPreferredSize(new java.awt.Dimension(1120, 750));
        jPanelParentCard.setLayout(new java.awt.CardLayout());
        jPanelBlueNavigationMenu.add(jPanelParentCard, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 90, 490, 570));

        jPanelWhiteMain.add(jPanelBlueNavigationMenu);
        jPanelBlueNavigationMenu.setBounds(10, 10, 1350, 750);

        getContentPane().add(jPanelWhiteMain, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1370, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanelBlueNavigationMenu;
    private javax.swing.JPanel jPanelParentCard;
    private javax.swing.JPanel jPanelWhiteMain;
    // End of variables declaration//GEN-END:variables
}
