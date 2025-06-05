package gui.admin.hr;

import java.awt.CardLayout;

public class ViewTicketPanel extends javax.swing.JPanel {
    
    private final AdminHRPortal hrPortal;

    public ViewTicketPanel(AdminHRPortal hrPortal) {
        this.hrPortal = hrPortal;
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabelHelloAdmin = new javax.swing.JLabel();
        jLabelViewRecordSmall = new javax.swing.JLabel();
        jPanelViewTicketsBox = new javax.swing.JPanel();
        jTextFieldEmployeeID = new javax.swing.JTextField();
        jLabelEmployeeID = new javax.swing.JLabel();
        jLabelName = new javax.swing.JLabel();
        jLabelBack = new javax.swing.JLabel();
        jLabelTicketID = new javax.swing.JLabel();
        jTextFieldTicketID = new javax.swing.JTextField();
        jLabelAttendanceDate = new javax.swing.JLabel();
        jTextFieldRequestDate = new javax.swing.JTextField();
        jTextFieldFullName = new javax.swing.JTextField();
        jLabelAssignedTeam = new javax.swing.JLabel();
        jTextFieldAssignedTeam = new javax.swing.JTextField();
        jLabelSeverity = new javax.swing.JLabel();
        jTextFieldSeverity = new javax.swing.JTextField();
        jLabelSubject = new javax.swing.JLabel();
        jTextFieldSubject = new javax.swing.JTextField();
        jLabelIssueDescription = new javax.swing.JLabel();
        jLabelResolvedBy = new javax.swing.JLabel();
        jTextFieldResolvedBy = new javax.swing.JTextField();
        jLabelPosition = new javax.swing.JLabel();
        jTextFieldPosition = new javax.swing.JTextField();
        jLabelDepartment = new javax.swing.JLabel();
        jTextFieldDepartment = new javax.swing.JTextField();
        jLabelStatus = new javax.swing.JLabel();
        jTextFieldStatus = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaIssueDescription = new javax.swing.JTextArea();
        jButtonReject = new javax.swing.JButton();
        jButtonResolve = new javax.swing.JButton();

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

        jLabelViewRecordSmall.setText("Tickets > View Record");
        jPanel1.add(jLabelViewRecordSmall);
        jLabelViewRecordSmall.setBounds(30, 60, 180, 16);

        jPanelViewTicketsBox.setBackground(new java.awt.Color(255, 255, 255));
        jPanelViewTicketsBox.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanelViewTicketsBox.setLayout(null);

        jTextFieldEmployeeID.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        jTextFieldEmployeeID.setEnabled(false);
        jTextFieldEmployeeID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldEmployeeIDActionPerformed(evt);
            }
        });
        jPanelViewTicketsBox.add(jTextFieldEmployeeID);
        jTextFieldEmployeeID.setBounds(180, 240, 350, 40);

        jLabelEmployeeID.setBackground(new java.awt.Color(255, 255, 255));
        jLabelEmployeeID.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelEmployeeID.setForeground(new java.awt.Color(0, 0, 0));
        jLabelEmployeeID.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelEmployeeID.setText("Employee ID :");
        jPanelViewTicketsBox.add(jLabelEmployeeID);
        jLabelEmployeeID.setBounds(30, 240, 290, 40);

        jLabelName.setBackground(new java.awt.Color(255, 255, 255));
        jLabelName.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelName.setForeground(new java.awt.Color(0, 0, 0));
        jLabelName.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelName.setText("Name :");
        jPanelViewTicketsBox.add(jLabelName);
        jLabelName.setBounds(30, 280, 290, 40);

        jLabelBack.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/back-icon.png"))); // NOI18N
        jLabelBack.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelBackMouseClicked(evt);
            }
        });
        jPanelViewTicketsBox.add(jLabelBack);
        jLabelBack.setBounds(0, 560, 60, 60);

        jLabelTicketID.setBackground(new java.awt.Color(255, 255, 255));
        jLabelTicketID.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelTicketID.setForeground(new java.awt.Color(0, 0, 0));
        jLabelTicketID.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelTicketID.setText("Ticket ID :");
        jPanelViewTicketsBox.add(jLabelTicketID);
        jLabelTicketID.setBounds(30, 160, 290, 40);

        jTextFieldTicketID.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        jTextFieldTicketID.setEnabled(false);
        jTextFieldTicketID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldTicketIDActionPerformed(evt);
            }
        });
        jPanelViewTicketsBox.add(jTextFieldTicketID);
        jTextFieldTicketID.setBounds(180, 160, 350, 40);

        jLabelAttendanceDate.setBackground(new java.awt.Color(255, 255, 255));
        jLabelAttendanceDate.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelAttendanceDate.setForeground(new java.awt.Color(0, 0, 0));
        jLabelAttendanceDate.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelAttendanceDate.setText("Date :");
        jPanelViewTicketsBox.add(jLabelAttendanceDate);
        jLabelAttendanceDate.setBounds(30, 200, 290, 40);

        jTextFieldRequestDate.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        jTextFieldRequestDate.setEnabled(false);
        jTextFieldRequestDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldRequestDateActionPerformed(evt);
            }
        });
        jPanelViewTicketsBox.add(jTextFieldRequestDate);
        jTextFieldRequestDate.setBounds(180, 200, 350, 40);

        jTextFieldFullName.setEnabled(false);
        jTextFieldFullName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldFullNameActionPerformed(evt);
            }
        });
        jPanelViewTicketsBox.add(jTextFieldFullName);
        jTextFieldFullName.setBounds(180, 280, 350, 40);

        jLabelAssignedTeam.setBackground(new java.awt.Color(255, 255, 255));
        jLabelAssignedTeam.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelAssignedTeam.setForeground(new java.awt.Color(0, 0, 0));
        jLabelAssignedTeam.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelAssignedTeam.setText("Assigned Team :");
        jPanelViewTicketsBox.add(jLabelAssignedTeam);
        jLabelAssignedTeam.setBounds(540, 160, 290, 40);

        jTextFieldAssignedTeam.setEnabled(false);
        jTextFieldAssignedTeam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldAssignedTeamActionPerformed(evt);
            }
        });
        jPanelViewTicketsBox.add(jTextFieldAssignedTeam);
        jTextFieldAssignedTeam.setBounds(690, 160, 350, 40);

        jLabelSeverity.setBackground(new java.awt.Color(255, 255, 255));
        jLabelSeverity.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelSeverity.setForeground(new java.awt.Color(0, 0, 0));
        jLabelSeverity.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelSeverity.setText("Severity :");
        jPanelViewTicketsBox.add(jLabelSeverity);
        jLabelSeverity.setBounds(540, 200, 290, 40);

        jTextFieldSeverity.setEnabled(false);
        jTextFieldSeverity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldSeverityActionPerformed(evt);
            }
        });
        jPanelViewTicketsBox.add(jTextFieldSeverity);
        jTextFieldSeverity.setBounds(690, 200, 350, 40);

        jLabelSubject.setBackground(new java.awt.Color(255, 255, 255));
        jLabelSubject.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelSubject.setForeground(new java.awt.Color(0, 0, 0));
        jLabelSubject.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelSubject.setText("Subject :");
        jPanelViewTicketsBox.add(jLabelSubject);
        jLabelSubject.setBounds(540, 240, 290, 40);

        jTextFieldSubject.setEnabled(false);
        jTextFieldSubject.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldSubjectActionPerformed(evt);
            }
        });
        jPanelViewTicketsBox.add(jTextFieldSubject);
        jTextFieldSubject.setBounds(690, 240, 350, 40);

        jLabelIssueDescription.setBackground(new java.awt.Color(255, 255, 255));
        jLabelIssueDescription.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelIssueDescription.setForeground(new java.awt.Color(0, 0, 0));
        jLabelIssueDescription.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelIssueDescription.setText("Issue Description :");
        jPanelViewTicketsBox.add(jLabelIssueDescription);
        jLabelIssueDescription.setBounds(540, 280, 290, 40);

        jLabelResolvedBy.setBackground(new java.awt.Color(255, 255, 255));
        jLabelResolvedBy.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelResolvedBy.setForeground(new java.awt.Color(0, 0, 0));
        jLabelResolvedBy.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelResolvedBy.setText("Resolved by :");
        jPanelViewTicketsBox.add(jLabelResolvedBy);
        jLabelResolvedBy.setBounds(540, 320, 290, 40);

        jTextFieldResolvedBy.setEnabled(false);
        jTextFieldResolvedBy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldResolvedByActionPerformed(evt);
            }
        });
        jPanelViewTicketsBox.add(jTextFieldResolvedBy);
        jTextFieldResolvedBy.setBounds(690, 320, 350, 40);

        jLabelPosition.setBackground(new java.awt.Color(255, 255, 255));
        jLabelPosition.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelPosition.setForeground(new java.awt.Color(0, 0, 0));
        jLabelPosition.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelPosition.setText("Position :");
        jPanelViewTicketsBox.add(jLabelPosition);
        jLabelPosition.setBounds(30, 320, 290, 40);

        jTextFieldPosition.setEnabled(false);
        jTextFieldPosition.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldPositionActionPerformed(evt);
            }
        });
        jPanelViewTicketsBox.add(jTextFieldPosition);
        jTextFieldPosition.setBounds(180, 320, 350, 40);

        jLabelDepartment.setBackground(new java.awt.Color(255, 255, 255));
        jLabelDepartment.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelDepartment.setForeground(new java.awt.Color(0, 0, 0));
        jLabelDepartment.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelDepartment.setText("Department :");
        jPanelViewTicketsBox.add(jLabelDepartment);
        jLabelDepartment.setBounds(30, 360, 290, 40);

        jTextFieldDepartment.setEnabled(false);
        jTextFieldDepartment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldDepartmentActionPerformed(evt);
            }
        });
        jPanelViewTicketsBox.add(jTextFieldDepartment);
        jTextFieldDepartment.setBounds(180, 360, 350, 40);

        jLabelStatus.setBackground(new java.awt.Color(255, 255, 255));
        jLabelStatus.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelStatus.setForeground(new java.awt.Color(0, 0, 0));
        jLabelStatus.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelStatus.setText("Status :");
        jPanelViewTicketsBox.add(jLabelStatus);
        jLabelStatus.setBounds(540, 360, 290, 40);

        jTextFieldStatus.setEnabled(false);
        jTextFieldStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldStatusActionPerformed(evt);
            }
        });
        jPanelViewTicketsBox.add(jTextFieldStatus);
        jTextFieldStatus.setBounds(690, 360, 350, 40);

        jTextAreaIssueDescription.setColumns(20);
        jTextAreaIssueDescription.setLineWrap(true);
        jTextAreaIssueDescription.setRows(5);
        jTextAreaIssueDescription.setEnabled(false);
        jScrollPane1.setViewportView(jTextAreaIssueDescription);

        jPanelViewTicketsBox.add(jScrollPane1);
        jScrollPane1.setBounds(690, 280, 350, 40);

        jButtonReject.setBackground(new java.awt.Color(158, 0, 30));
        jButtonReject.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButtonReject.setForeground(new java.awt.Color(255, 255, 255));
        jButtonReject.setText("Reject");
        jButtonReject.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRejectActionPerformed(evt);
            }
        });
        jPanelViewTicketsBox.add(jButtonReject);
        jButtonReject.setBounds(870, 570, 170, 40);

        jButtonResolve.setBackground(new java.awt.Color(0, 135, 0));
        jButtonResolve.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButtonResolve.setForeground(new java.awt.Color(255, 255, 255));
        jButtonResolve.setText("Resolve");
        jButtonResolve.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonResolveActionPerformed(evt);
            }
        });
        jPanelViewTicketsBox.add(jButtonResolve);
        jButtonResolve.setBounds(680, 570, 170, 40);

        jPanel1.add(jPanelViewTicketsBox);
        jPanelViewTicketsBox.setBounds(30, 110, 1060, 640);

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void jLabelBackMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelBackMouseClicked
        // TODO add your handling code here:
        CardLayout cardLayout = (CardLayout) hrPortal.getPanelParentCard().getLayout();
        cardLayout.show(hrPortal.getPanelParentCard(), "Tickets");
    }//GEN-LAST:event_jLabelBackMouseClicked

    private void jTextFieldEmployeeIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldEmployeeIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldEmployeeIDActionPerformed

    private void jTextFieldTicketIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldTicketIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldTicketIDActionPerformed

    private void jTextFieldRequestDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldRequestDateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldRequestDateActionPerformed

    private void jTextFieldFullNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldFullNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldFullNameActionPerformed

    private void jTextFieldAssignedTeamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldAssignedTeamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldAssignedTeamActionPerformed

    private void jTextFieldSeverityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldSeverityActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldSeverityActionPerformed

    private void jTextFieldSubjectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldSubjectActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldSubjectActionPerformed

    private void jTextFieldResolvedByActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldResolvedByActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldResolvedByActionPerformed

    private void jTextFieldPositionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldPositionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldPositionActionPerformed

    private void jTextFieldDepartmentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldDepartmentActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldDepartmentActionPerformed

    private void jTextFieldStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldStatusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldStatusActionPerformed

    private void jButtonRejectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRejectActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonRejectActionPerformed

    private void jButtonResolveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonResolveActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonResolveActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonReject;
    private javax.swing.JButton jButtonResolve;
    private javax.swing.JLabel jLabelAssignedTeam;
    private javax.swing.JLabel jLabelAttendanceDate;
    private javax.swing.JLabel jLabelBack;
    private javax.swing.JLabel jLabelDepartment;
    private javax.swing.JLabel jLabelEmployeeID;
    private javax.swing.JLabel jLabelHelloAdmin;
    private javax.swing.JLabel jLabelIssueDescription;
    private javax.swing.JLabel jLabelName;
    private javax.swing.JLabel jLabelPosition;
    private javax.swing.JLabel jLabelResolvedBy;
    private javax.swing.JLabel jLabelSeverity;
    private javax.swing.JLabel jLabelStatus;
    private javax.swing.JLabel jLabelSubject;
    private javax.swing.JLabel jLabelTicketID;
    private javax.swing.JLabel jLabelViewRecordSmall;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanelViewTicketsBox;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextAreaIssueDescription;
    private javax.swing.JTextField jTextFieldAssignedTeam;
    private javax.swing.JTextField jTextFieldDepartment;
    private javax.swing.JTextField jTextFieldEmployeeID;
    private javax.swing.JTextField jTextFieldFullName;
    private javax.swing.JTextField jTextFieldPosition;
    private javax.swing.JTextField jTextFieldRequestDate;
    private javax.swing.JTextField jTextFieldResolvedBy;
    private javax.swing.JTextField jTextFieldSeverity;
    private javax.swing.JTextField jTextFieldStatus;
    private javax.swing.JTextField jTextFieldSubject;
    private javax.swing.JTextField jTextFieldTicketID;
    // End of variables declaration//GEN-END:variables
}