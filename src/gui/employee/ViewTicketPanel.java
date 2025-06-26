package gui.employee;

import java.awt.CardLayout;
import java.time.format.DateTimeFormatter;
import model.pojo.EmployeeView;
import model.pojo.SupportRequest;
import service.RequestService;
import util.UIUtil;

public class ViewTicketPanel extends javax.swing.JPanel {
    
    private final EmployeePortal employeePortal;
    private final RequestService requestService;

    public ViewTicketPanel(EmployeePortal employeePortal) {
        this.employeePortal = employeePortal;
        this.requestService = new RequestService();
        initComponents();
        UIUtil.setGreeting(jLabelHelloEmployee, "Employee");
    }
    
    public void loadSelectedTicket(int ticketID) {
        SupportRequest sr = requestService.getSupportDetails(ticketID);
        if (sr == null) {
            return;
        }
        
        EmployeeView efd = requestService.getEmployeeDetails(sr.getEmployeeID());

        jTextFieldTicketID.setText(String.valueOf(sr.getTicketID()));
        DateTimeFormatter dateFmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        jTextFieldRequestDate.setText(sr.getDate().format(dateFmt));
        jTextFieldEmployeeID.setText(String.valueOf(sr.getEmployeeID()));
        jTextFieldFullName.setText(efd.getFirstName() + " " + efd.getLastName());
        jTextFieldPosition.setText(efd.getPositionTitle());
        jTextFieldDepartment.setText(efd.getDepartmentName());
        jTextFieldAssignedTeam.setText(sr.getAssignedTeam());
        jTextFieldSeverity.setText(sr.getSeverity());
        jTextFieldSubject.setText(sr.getSubject());
        jTextAreaIssueDescription.setText(sr.getDescription());
        if (sr.getResolvedBy() != null) {
            String resolverName = requestService.getUserFullName(sr.getResolvedBy());
            jTextFieldResolvedBy.setText(resolverName);
        } else {
            jTextFieldResolvedBy.setText("");
        }
        jTextFieldStatus.setText(sr.getStatus());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabelHelloEmployee = new javax.swing.JLabel();
        jLabelViewTicketSmall = new javax.swing.JLabel();
        jPanelLeaveRequestBox = new javax.swing.JPanel();
        jLabelAttendanceDate = new javax.swing.JLabel();
        jLabelTicketID = new javax.swing.JLabel();
        jLabelEmployeeNumber = new javax.swing.JLabel();
        jLabelName = new javax.swing.JLabel();
        jLabelPosition = new javax.swing.JLabel();
        jLabelDepartment = new javax.swing.JLabel();
        jTextFieldDepartment = new javax.swing.JTextField();
        jTextFieldPosition = new javax.swing.JTextField();
        jTextFieldFullName = new javax.swing.JTextField();
        jTextFieldEmployeeID = new javax.swing.JTextField();
        jTextFieldRequestDate = new javax.swing.JTextField();
        jTextFieldTicketID = new javax.swing.JTextField();
        jLabelAssignedTeam = new javax.swing.JLabel();
        jLabelSeverity = new javax.swing.JLabel();
        jLabelSubject = new javax.swing.JLabel();
        jLabelIssueDescription = new javax.swing.JLabel();
        jLabelResolvedBy = new javax.swing.JLabel();
        jLabelStatus = new javax.swing.JLabel();
        jLabelBack = new javax.swing.JLabel();
        jTextFieldStatus = new javax.swing.JTextField();
        jTextFieldResolvedBy = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaIssueDescription = new javax.swing.JTextArea();
        jTextFieldSubject = new javax.swing.JTextField();
        jTextFieldSeverity = new javax.swing.JTextField();
        jTextFieldAssignedTeam = new javax.swing.JTextField();

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
        jLabelHelloEmployee.setBounds(30, 30, 600, 29);

        jLabelViewTicketSmall.setText("Support > View Record");
        jPanel1.add(jLabelViewTicketSmall);
        jLabelViewTicketSmall.setBounds(30, 60, 180, 16);

        jPanelLeaveRequestBox.setBackground(new java.awt.Color(255, 255, 255));
        jPanelLeaveRequestBox.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanelLeaveRequestBox.setLayout(null);

        jLabelAttendanceDate.setBackground(new java.awt.Color(255, 255, 255));
        jLabelAttendanceDate.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelAttendanceDate.setForeground(new java.awt.Color(0, 0, 0));
        jLabelAttendanceDate.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelAttendanceDate.setText("Date :");
        jPanelLeaveRequestBox.add(jLabelAttendanceDate);
        jLabelAttendanceDate.setBounds(30, 200, 290, 40);

        jLabelTicketID.setBackground(new java.awt.Color(255, 255, 255));
        jLabelTicketID.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelTicketID.setForeground(new java.awt.Color(0, 0, 0));
        jLabelTicketID.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelTicketID.setText("Ticket ID :");
        jPanelLeaveRequestBox.add(jLabelTicketID);
        jLabelTicketID.setBounds(30, 160, 290, 40);

        jLabelEmployeeNumber.setBackground(new java.awt.Color(255, 255, 255));
        jLabelEmployeeNumber.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelEmployeeNumber.setForeground(new java.awt.Color(0, 0, 0));
        jLabelEmployeeNumber.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelEmployeeNumber.setText("Employee ID :");
        jPanelLeaveRequestBox.add(jLabelEmployeeNumber);
        jLabelEmployeeNumber.setBounds(30, 240, 290, 40);

        jLabelName.setBackground(new java.awt.Color(255, 255, 255));
        jLabelName.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelName.setForeground(new java.awt.Color(0, 0, 0));
        jLabelName.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelName.setText("Name :");
        jPanelLeaveRequestBox.add(jLabelName);
        jLabelName.setBounds(30, 280, 290, 40);

        jLabelPosition.setBackground(new java.awt.Color(255, 255, 255));
        jLabelPosition.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelPosition.setForeground(new java.awt.Color(0, 0, 0));
        jLabelPosition.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelPosition.setText("Position :");
        jPanelLeaveRequestBox.add(jLabelPosition);
        jLabelPosition.setBounds(30, 320, 290, 40);

        jLabelDepartment.setBackground(new java.awt.Color(255, 255, 255));
        jLabelDepartment.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelDepartment.setForeground(new java.awt.Color(0, 0, 0));
        jLabelDepartment.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelDepartment.setText("Department :");
        jPanelLeaveRequestBox.add(jLabelDepartment);
        jLabelDepartment.setBounds(30, 360, 290, 40);

        jTextFieldDepartment.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jTextFieldDepartment.setEnabled(false);
        jTextFieldDepartment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldDepartmentActionPerformed(evt);
            }
        });
        jPanelLeaveRequestBox.add(jTextFieldDepartment);
        jTextFieldDepartment.setBounds(180, 360, 350, 40);

        jTextFieldPosition.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jTextFieldPosition.setEnabled(false);
        jTextFieldPosition.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldPositionActionPerformed(evt);
            }
        });
        jPanelLeaveRequestBox.add(jTextFieldPosition);
        jTextFieldPosition.setBounds(180, 320, 350, 40);

        jTextFieldFullName.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jTextFieldFullName.setEnabled(false);
        jTextFieldFullName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldFullNameActionPerformed(evt);
            }
        });
        jPanelLeaveRequestBox.add(jTextFieldFullName);
        jTextFieldFullName.setBounds(180, 280, 350, 40);

        jTextFieldEmployeeID.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jTextFieldEmployeeID.setEnabled(false);
        jTextFieldEmployeeID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldEmployeeIDActionPerformed(evt);
            }
        });
        jPanelLeaveRequestBox.add(jTextFieldEmployeeID);
        jTextFieldEmployeeID.setBounds(180, 240, 350, 40);

        jTextFieldRequestDate.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jTextFieldRequestDate.setEnabled(false);
        jTextFieldRequestDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldRequestDateActionPerformed(evt);
            }
        });
        jPanelLeaveRequestBox.add(jTextFieldRequestDate);
        jTextFieldRequestDate.setBounds(180, 200, 350, 40);

        jTextFieldTicketID.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jTextFieldTicketID.setEnabled(false);
        jTextFieldTicketID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldTicketIDActionPerformed(evt);
            }
        });
        jPanelLeaveRequestBox.add(jTextFieldTicketID);
        jTextFieldTicketID.setBounds(180, 160, 350, 40);

        jLabelAssignedTeam.setBackground(new java.awt.Color(255, 255, 255));
        jLabelAssignedTeam.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelAssignedTeam.setForeground(new java.awt.Color(0, 0, 0));
        jLabelAssignedTeam.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelAssignedTeam.setText("Assigned Team :");
        jPanelLeaveRequestBox.add(jLabelAssignedTeam);
        jLabelAssignedTeam.setBounds(540, 160, 290, 40);

        jLabelSeverity.setBackground(new java.awt.Color(255, 255, 255));
        jLabelSeverity.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelSeverity.setForeground(new java.awt.Color(0, 0, 0));
        jLabelSeverity.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelSeverity.setText("Severity :");
        jPanelLeaveRequestBox.add(jLabelSeverity);
        jLabelSeverity.setBounds(540, 200, 290, 40);

        jLabelSubject.setBackground(new java.awt.Color(255, 255, 255));
        jLabelSubject.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelSubject.setForeground(new java.awt.Color(0, 0, 0));
        jLabelSubject.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelSubject.setText("Subject :");
        jPanelLeaveRequestBox.add(jLabelSubject);
        jLabelSubject.setBounds(540, 240, 290, 40);

        jLabelIssueDescription.setBackground(new java.awt.Color(255, 255, 255));
        jLabelIssueDescription.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelIssueDescription.setForeground(new java.awt.Color(0, 0, 0));
        jLabelIssueDescription.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelIssueDescription.setText("Issue Description :");
        jPanelLeaveRequestBox.add(jLabelIssueDescription);
        jLabelIssueDescription.setBounds(540, 280, 290, 40);

        jLabelResolvedBy.setBackground(new java.awt.Color(255, 255, 255));
        jLabelResolvedBy.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelResolvedBy.setForeground(new java.awt.Color(0, 0, 0));
        jLabelResolvedBy.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelResolvedBy.setText("Resolved by :");
        jPanelLeaveRequestBox.add(jLabelResolvedBy);
        jLabelResolvedBy.setBounds(540, 320, 290, 40);

        jLabelStatus.setBackground(new java.awt.Color(255, 255, 255));
        jLabelStatus.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelStatus.setForeground(new java.awt.Color(0, 0, 0));
        jLabelStatus.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelStatus.setText("Status :");
        jPanelLeaveRequestBox.add(jLabelStatus);
        jLabelStatus.setBounds(540, 360, 290, 40);

        jLabelBack.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/back-icon.png"))); // NOI18N
        jLabelBack.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelBackMouseClicked(evt);
            }
        });
        jPanelLeaveRequestBox.add(jLabelBack);
        jLabelBack.setBounds(0, 560, 60, 60);

        jTextFieldStatus.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jTextFieldStatus.setEnabled(false);
        jTextFieldStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldStatusActionPerformed(evt);
            }
        });
        jPanelLeaveRequestBox.add(jTextFieldStatus);
        jTextFieldStatus.setBounds(690, 360, 350, 40);

        jTextFieldResolvedBy.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jTextFieldResolvedBy.setEnabled(false);
        jTextFieldResolvedBy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldResolvedByActionPerformed(evt);
            }
        });
        jPanelLeaveRequestBox.add(jTextFieldResolvedBy);
        jTextFieldResolvedBy.setBounds(690, 320, 350, 40);

        jTextAreaIssueDescription.setColumns(20);
        jTextAreaIssueDescription.setLineWrap(true);
        jTextAreaIssueDescription.setRows(5);
        jTextAreaIssueDescription.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jTextAreaIssueDescription.setEnabled(false);
        jScrollPane1.setViewportView(jTextAreaIssueDescription);

        jPanelLeaveRequestBox.add(jScrollPane1);
        jScrollPane1.setBounds(690, 280, 350, 40);

        jTextFieldSubject.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jTextFieldSubject.setEnabled(false);
        jTextFieldSubject.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldSubjectActionPerformed(evt);
            }
        });
        jPanelLeaveRequestBox.add(jTextFieldSubject);
        jTextFieldSubject.setBounds(690, 240, 350, 40);

        jTextFieldSeverity.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jTextFieldSeverity.setEnabled(false);
        jTextFieldSeverity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldSeverityActionPerformed(evt);
            }
        });
        jPanelLeaveRequestBox.add(jTextFieldSeverity);
        jTextFieldSeverity.setBounds(690, 200, 350, 40);

        jTextFieldAssignedTeam.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jTextFieldAssignedTeam.setEnabled(false);
        jTextFieldAssignedTeam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldAssignedTeamActionPerformed(evt);
            }
        });
        jPanelLeaveRequestBox.add(jTextFieldAssignedTeam);
        jTextFieldAssignedTeam.setBounds(690, 160, 350, 40);

        jPanel1.add(jPanelLeaveRequestBox);
        jPanelLeaveRequestBox.setBounds(30, 110, 1060, 640);

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldDepartmentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldDepartmentActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldDepartmentActionPerformed

    private void jTextFieldPositionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldPositionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldPositionActionPerformed

    private void jTextFieldFullNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldFullNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldFullNameActionPerformed

    private void jTextFieldEmployeeIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldEmployeeIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldEmployeeIDActionPerformed

    private void jTextFieldRequestDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldRequestDateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldRequestDateActionPerformed

    private void jTextFieldTicketIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldTicketIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldTicketIDActionPerformed

    private void jLabelBackMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelBackMouseClicked
        // TODO add your handling code here:
        CardLayout cardLayout = (CardLayout) employeePortal.getPanelParentCard().getLayout();
        cardLayout.show(employeePortal.getPanelParentCard(), "Support");
    }//GEN-LAST:event_jLabelBackMouseClicked

    private void jTextFieldStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldStatusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldStatusActionPerformed

    private void jTextFieldResolvedByActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldResolvedByActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldResolvedByActionPerformed

    private void jTextFieldSubjectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldSubjectActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldSubjectActionPerformed

    private void jTextFieldSeverityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldSeverityActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldSeverityActionPerformed

    private void jTextFieldAssignedTeamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldAssignedTeamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldAssignedTeamActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabelAssignedTeam;
    private javax.swing.JLabel jLabelAttendanceDate;
    private javax.swing.JLabel jLabelBack;
    private javax.swing.JLabel jLabelDepartment;
    private javax.swing.JLabel jLabelEmployeeNumber;
    private javax.swing.JLabel jLabelHelloEmployee;
    private javax.swing.JLabel jLabelIssueDescription;
    private javax.swing.JLabel jLabelName;
    private javax.swing.JLabel jLabelPosition;
    private javax.swing.JLabel jLabelResolvedBy;
    private javax.swing.JLabel jLabelSeverity;
    private javax.swing.JLabel jLabelStatus;
    private javax.swing.JLabel jLabelSubject;
    private javax.swing.JLabel jLabelTicketID;
    private javax.swing.JLabel jLabelViewTicketSmall;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanelLeaveRequestBox;
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