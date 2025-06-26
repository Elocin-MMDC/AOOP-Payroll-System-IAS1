package gui.employee;

import java.awt.CardLayout;
import model.pojo.EmployeeView;
import model.pojo.OvertimeRequest;
import service.RequestService;
import util.UIUtil;

public class ViewOvertimePanel extends javax.swing.JPanel {
    
    private final EmployeePortal employeePortal;
    private final RequestService requestService;

    public ViewOvertimePanel(EmployeePortal employeePortal) {
        this.employeePortal = employeePortal;
        requestService = new RequestService();
        initComponents();
        UIUtil.setGreeting(jLabelHelloEmployee, "Employee");
    }
    
    public void loadSelectedOvertime(int overtimeID) {
        OvertimeRequest or = requestService.getOvertimeDetails(overtimeID);
        
        if (or == null) {
            return;
        }

        EmployeeView efd = requestService.getEmployeeDetails(or.getEmployeeID());

        jTextFieldOvertimeRequestID.setText(String.valueOf(or.getOvertimeID()));
        jTextFieldRequestDate.setText(or.getDate().toString());
        jTextFieldEmployeeID.setText(String.valueOf(or.getEmployeeID()));
        jTextFieldFullName.setText(efd.getFirstName() + " " + efd.getLastName());
        jTextFieldPosition.setText(efd.getPositionTitle());
        jTextFieldDepartment.setText(efd.getDepartmentName());
        jTextFieldSupervisor.setText(efd.getSupervisorName());
        jTextFieldOvertimeWorkDate.setText(or.getOvertimeWorkDate().toString());
        jTextFieldOvertimeHours.setText(or.getOvertimeHours().toPlainString());
        jTextAreaReason.setText(or.getReason());
        jTextFieldApprovedBy.setText(
                or.getApprovedBy() != null ? String.valueOf(or.getApprovedBy()) : ""
        );
        jTextFieldStatus.setText(or.getStatus());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabelHelloEmployee = new javax.swing.JLabel();
        jLabelOvertimeSmall = new javax.swing.JLabel();
        jPanelOvertimeRequestBox = new javax.swing.JPanel();
        jLabelOvertimeRequestID = new javax.swing.JLabel();
        jLabelRequestDate = new javax.swing.JLabel();
        jLabelEmployeeNumber = new javax.swing.JLabel();
        jLabelName = new javax.swing.JLabel();
        jLabelPosition = new javax.swing.JLabel();
        jLabelDepartment = new javax.swing.JLabel();
        jLabelBack = new javax.swing.JLabel();
        jTextFieldDepartment = new javax.swing.JTextField();
        jTextFieldPosition = new javax.swing.JTextField();
        jTextFieldFullName = new javax.swing.JTextField();
        jTextFieldEmployeeID = new javax.swing.JTextField();
        jTextFieldRequestDate = new javax.swing.JTextField();
        jTextFieldOvertimeRequestID = new javax.swing.JTextField();
        jLabelSupervisor = new javax.swing.JLabel();
        jLabelOvertimeWorkDate = new javax.swing.JLabel();
        jLabelOvertimeHours = new javax.swing.JLabel();
        jLabelReason = new javax.swing.JLabel();
        jLabelApprovedBy = new javax.swing.JLabel();
        jLabelStatus = new javax.swing.JLabel();
        jTextFieldStatus = new javax.swing.JTextField();
        jTextFieldApprovedBy = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaReason = new javax.swing.JTextArea();
        jTextFieldOvertimeHours = new javax.swing.JTextField();
        jTextFieldOvertimeWorkDate = new javax.swing.JTextField();
        jTextFieldSupervisor = new javax.swing.JTextField();

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
        jLabelHelloEmployee.setBounds(30, 30, 580, 29);

        jLabelOvertimeSmall.setText("Overtime > View Record");
        jPanel1.add(jLabelOvertimeSmall);
        jLabelOvertimeSmall.setBounds(30, 60, 180, 16);

        jPanelOvertimeRequestBox.setBackground(new java.awt.Color(255, 255, 255));
        jPanelOvertimeRequestBox.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanelOvertimeRequestBox.setLayout(null);

        jLabelOvertimeRequestID.setBackground(new java.awt.Color(255, 255, 255));
        jLabelOvertimeRequestID.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelOvertimeRequestID.setForeground(new java.awt.Color(0, 0, 0));
        jLabelOvertimeRequestID.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelOvertimeRequestID.setText("Overtime Request ID :");
        jPanelOvertimeRequestBox.add(jLabelOvertimeRequestID);
        jLabelOvertimeRequestID.setBounds(30, 150, 290, 40);

        jLabelRequestDate.setBackground(new java.awt.Color(255, 255, 255));
        jLabelRequestDate.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelRequestDate.setForeground(new java.awt.Color(0, 0, 0));
        jLabelRequestDate.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelRequestDate.setText("Date :");
        jPanelOvertimeRequestBox.add(jLabelRequestDate);
        jLabelRequestDate.setBounds(30, 190, 290, 40);

        jLabelEmployeeNumber.setBackground(new java.awt.Color(255, 255, 255));
        jLabelEmployeeNumber.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelEmployeeNumber.setForeground(new java.awt.Color(0, 0, 0));
        jLabelEmployeeNumber.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelEmployeeNumber.setText("Employee ID :");
        jPanelOvertimeRequestBox.add(jLabelEmployeeNumber);
        jLabelEmployeeNumber.setBounds(30, 230, 290, 40);

        jLabelName.setBackground(new java.awt.Color(255, 255, 255));
        jLabelName.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelName.setForeground(new java.awt.Color(0, 0, 0));
        jLabelName.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelName.setText("Name :");
        jPanelOvertimeRequestBox.add(jLabelName);
        jLabelName.setBounds(30, 270, 290, 40);

        jLabelPosition.setBackground(new java.awt.Color(255, 255, 255));
        jLabelPosition.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelPosition.setForeground(new java.awt.Color(0, 0, 0));
        jLabelPosition.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelPosition.setText("Position :");
        jPanelOvertimeRequestBox.add(jLabelPosition);
        jLabelPosition.setBounds(30, 310, 290, 40);

        jLabelDepartment.setBackground(new java.awt.Color(255, 255, 255));
        jLabelDepartment.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelDepartment.setForeground(new java.awt.Color(0, 0, 0));
        jLabelDepartment.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelDepartment.setText("Department :");
        jPanelOvertimeRequestBox.add(jLabelDepartment);
        jLabelDepartment.setBounds(30, 350, 290, 40);

        jLabelBack.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/back-icon.png"))); // NOI18N
        jLabelBack.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelBackMouseClicked(evt);
            }
        });
        jPanelOvertimeRequestBox.add(jLabelBack);
        jLabelBack.setBounds(0, 560, 60, 60);

        jTextFieldDepartment.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jTextFieldDepartment.setEnabled(false);
        jTextFieldDepartment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldDepartmentActionPerformed(evt);
            }
        });
        jPanelOvertimeRequestBox.add(jTextFieldDepartment);
        jTextFieldDepartment.setBounds(180, 350, 350, 40);

        jTextFieldPosition.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jTextFieldPosition.setEnabled(false);
        jTextFieldPosition.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldPositionActionPerformed(evt);
            }
        });
        jPanelOvertimeRequestBox.add(jTextFieldPosition);
        jTextFieldPosition.setBounds(180, 310, 350, 40);

        jTextFieldFullName.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jTextFieldFullName.setEnabled(false);
        jTextFieldFullName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldFullNameActionPerformed(evt);
            }
        });
        jPanelOvertimeRequestBox.add(jTextFieldFullName);
        jTextFieldFullName.setBounds(180, 270, 350, 40);

        jTextFieldEmployeeID.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jTextFieldEmployeeID.setEnabled(false);
        jTextFieldEmployeeID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldEmployeeIDActionPerformed(evt);
            }
        });
        jPanelOvertimeRequestBox.add(jTextFieldEmployeeID);
        jTextFieldEmployeeID.setBounds(180, 230, 350, 40);

        jTextFieldRequestDate.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jTextFieldRequestDate.setEnabled(false);
        jTextFieldRequestDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldRequestDateActionPerformed(evt);
            }
        });
        jPanelOvertimeRequestBox.add(jTextFieldRequestDate);
        jTextFieldRequestDate.setBounds(180, 190, 350, 40);

        jTextFieldOvertimeRequestID.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jTextFieldOvertimeRequestID.setEnabled(false);
        jTextFieldOvertimeRequestID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldOvertimeRequestIDActionPerformed(evt);
            }
        });
        jPanelOvertimeRequestBox.add(jTextFieldOvertimeRequestID);
        jTextFieldOvertimeRequestID.setBounds(180, 150, 350, 40);

        jLabelSupervisor.setBackground(new java.awt.Color(255, 255, 255));
        jLabelSupervisor.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelSupervisor.setForeground(new java.awt.Color(0, 0, 0));
        jLabelSupervisor.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelSupervisor.setText("Supervisor :");
        jPanelOvertimeRequestBox.add(jLabelSupervisor);
        jLabelSupervisor.setBounds(540, 150, 290, 40);

        jLabelOvertimeWorkDate.setBackground(new java.awt.Color(255, 255, 255));
        jLabelOvertimeWorkDate.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelOvertimeWorkDate.setForeground(new java.awt.Color(0, 0, 0));
        jLabelOvertimeWorkDate.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelOvertimeWorkDate.setText("Overtime Work Date :");
        jPanelOvertimeRequestBox.add(jLabelOvertimeWorkDate);
        jLabelOvertimeWorkDate.setBounds(540, 190, 290, 40);

        jLabelOvertimeHours.setBackground(new java.awt.Color(255, 255, 255));
        jLabelOvertimeHours.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelOvertimeHours.setForeground(new java.awt.Color(0, 0, 0));
        jLabelOvertimeHours.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelOvertimeHours.setText("Overtime Hours :");
        jPanelOvertimeRequestBox.add(jLabelOvertimeHours);
        jLabelOvertimeHours.setBounds(540, 230, 290, 40);

        jLabelReason.setBackground(new java.awt.Color(255, 255, 255));
        jLabelReason.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelReason.setForeground(new java.awt.Color(0, 0, 0));
        jLabelReason.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelReason.setText("Reason :");
        jPanelOvertimeRequestBox.add(jLabelReason);
        jLabelReason.setBounds(540, 270, 290, 40);

        jLabelApprovedBy.setBackground(new java.awt.Color(255, 255, 255));
        jLabelApprovedBy.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelApprovedBy.setForeground(new java.awt.Color(0, 0, 0));
        jLabelApprovedBy.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelApprovedBy.setText("Approved By :");
        jPanelOvertimeRequestBox.add(jLabelApprovedBy);
        jLabelApprovedBy.setBounds(540, 310, 290, 40);

        jLabelStatus.setBackground(new java.awt.Color(255, 255, 255));
        jLabelStatus.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelStatus.setForeground(new java.awt.Color(0, 0, 0));
        jLabelStatus.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelStatus.setText("Status :");
        jPanelOvertimeRequestBox.add(jLabelStatus);
        jLabelStatus.setBounds(540, 350, 290, 40);

        jTextFieldStatus.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jTextFieldStatus.setEnabled(false);
        jTextFieldStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldStatusActionPerformed(evt);
            }
        });
        jPanelOvertimeRequestBox.add(jTextFieldStatus);
        jTextFieldStatus.setBounds(690, 350, 350, 40);

        jTextFieldApprovedBy.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jTextFieldApprovedBy.setEnabled(false);
        jTextFieldApprovedBy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldApprovedByActionPerformed(evt);
            }
        });
        jPanelOvertimeRequestBox.add(jTextFieldApprovedBy);
        jTextFieldApprovedBy.setBounds(690, 310, 350, 40);

        jTextAreaReason.setColumns(20);
        jTextAreaReason.setLineWrap(true);
        jTextAreaReason.setRows(5);
        jTextAreaReason.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jTextAreaReason.setEnabled(false);
        jScrollPane1.setViewportView(jTextAreaReason);

        jPanelOvertimeRequestBox.add(jScrollPane1);
        jScrollPane1.setBounds(690, 270, 350, 40);

        jTextFieldOvertimeHours.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jTextFieldOvertimeHours.setEnabled(false);
        jTextFieldOvertimeHours.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldOvertimeHoursActionPerformed(evt);
            }
        });
        jPanelOvertimeRequestBox.add(jTextFieldOvertimeHours);
        jTextFieldOvertimeHours.setBounds(690, 230, 350, 40);

        jTextFieldOvertimeWorkDate.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jTextFieldOvertimeWorkDate.setEnabled(false);
        jTextFieldOvertimeWorkDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldOvertimeWorkDateActionPerformed(evt);
            }
        });
        jPanelOvertimeRequestBox.add(jTextFieldOvertimeWorkDate);
        jTextFieldOvertimeWorkDate.setBounds(690, 190, 350, 40);

        jTextFieldSupervisor.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jTextFieldSupervisor.setEnabled(false);
        jTextFieldSupervisor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldSupervisorActionPerformed(evt);
            }
        });
        jPanelOvertimeRequestBox.add(jTextFieldSupervisor);
        jTextFieldSupervisor.setBounds(690, 150, 350, 40);

        jPanel1.add(jPanelOvertimeRequestBox);
        jPanelOvertimeRequestBox.setBounds(30, 110, 1060, 640);

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void jLabelBackMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelBackMouseClicked
        // TODO add your handling code here:
        CardLayout cardLayout = (CardLayout) employeePortal.getPanelParentCard().getLayout();
        cardLayout.show(employeePortal.getPanelParentCard(), "Overtime");
    }//GEN-LAST:event_jLabelBackMouseClicked

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

    private void jTextFieldOvertimeRequestIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldOvertimeRequestIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldOvertimeRequestIDActionPerformed

    private void jTextFieldStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldStatusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldStatusActionPerformed

    private void jTextFieldApprovedByActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldApprovedByActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldApprovedByActionPerformed

    private void jTextFieldOvertimeHoursActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldOvertimeHoursActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldOvertimeHoursActionPerformed

    private void jTextFieldOvertimeWorkDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldOvertimeWorkDateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldOvertimeWorkDateActionPerformed

    private void jTextFieldSupervisorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldSupervisorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldSupervisorActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabelApprovedBy;
    private javax.swing.JLabel jLabelBack;
    private javax.swing.JLabel jLabelDepartment;
    private javax.swing.JLabel jLabelEmployeeNumber;
    private javax.swing.JLabel jLabelHelloEmployee;
    private javax.swing.JLabel jLabelName;
    private javax.swing.JLabel jLabelOvertimeHours;
    private javax.swing.JLabel jLabelOvertimeRequestID;
    private javax.swing.JLabel jLabelOvertimeSmall;
    private javax.swing.JLabel jLabelOvertimeWorkDate;
    private javax.swing.JLabel jLabelPosition;
    private javax.swing.JLabel jLabelReason;
    private javax.swing.JLabel jLabelRequestDate;
    private javax.swing.JLabel jLabelStatus;
    private javax.swing.JLabel jLabelSupervisor;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanelOvertimeRequestBox;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextAreaReason;
    private javax.swing.JTextField jTextFieldApprovedBy;
    private javax.swing.JTextField jTextFieldDepartment;
    private javax.swing.JTextField jTextFieldEmployeeID;
    private javax.swing.JTextField jTextFieldFullName;
    private javax.swing.JTextField jTextFieldOvertimeHours;
    private javax.swing.JTextField jTextFieldOvertimeRequestID;
    private javax.swing.JTextField jTextFieldOvertimeWorkDate;
    private javax.swing.JTextField jTextFieldPosition;
    private javax.swing.JTextField jTextFieldRequestDate;
    private javax.swing.JTextField jTextFieldStatus;
    private javax.swing.JTextField jTextFieldSupervisor;
    // End of variables declaration//GEN-END:variables
}