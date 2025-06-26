package gui.admin.finance;

import java.awt.CardLayout;
import model.pojo.EmployeeView;
import service.EmployeeService;
import util.UIUtil;

public class ViewCompensationPanel extends javax.swing.JPanel {

    private final AdminFinancePortal financePortal;
    private final EmployeeService employeeService;
    
    public ViewCompensationPanel(AdminFinancePortal financePortal) {
        this.financePortal = financePortal;
        this.employeeService = new EmployeeService();
        initComponents();
        UIUtil.setGreeting(jLabelHelloAdmin, "Admin");
    }

    public void loadSelectedEmployee(int empId) {
        EmployeeView e = employeeService.getEmployeeById(empId);
        if (e == null) {
            return;
        }

        // Basic info
        jTextFieldEmployeeID.setText(String.valueOf(e.getEmployeeID()));
        jTextFieldLastName.setText(e.getLastName());
        jTextFieldFirstName.setText(e.getFirstName());
        jTextFieldWorkStatus.setText(e.getWorkStatus());
        jTextFieldRole.setText(e.getRole());
        jTextFieldPosition.setText(e.getPositionTitle());
        jTextFieldDepartment.setText(e.getDepartmentName());
        jTextFieldSupervisor.setText(e.getSupervisorName());
    
        // GovInfo
        jTextFieldSssNumber.setText(e.getSssNumber());
        jTextFieldPhilHealthNumber.setText(e.getPhilHealthNumber());
        jTextFieldTin.setText(e.getTin());
        jTextFieldPagIbigNumber.setText(e.getPagIbigNumber());

        // Compensation
        jTextFieldBasicSalary.setText(e.getBasicSalary().toString());
        jTextFieldSemiMonthlyRate.setText(e.getSemiMonthlyRate().toString());
        jTextFieldHourlyRate.setText(e.getHourlyRate().toString());
        jTextFieldRiceSubsidy.setText(e.getRiceSubsidy().toString());
        jTextFieldPhoneAllowance.setText(e.getPhoneAllowance().toString());
        jTextFieldClothingAllowance.setText(e.getClothingAllowance().toString());
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabelHelloAdmin = new javax.swing.JLabel();
        jLabelCompensationSmall = new javax.swing.JLabel();
        jPanelCompensationBox = new javax.swing.JPanel();
        jLabelEmployeeID = new javax.swing.JLabel();
        jLabelLastName = new javax.swing.JLabel();
        jLabelFirstName = new javax.swing.JLabel();
        jLabelWorkStatus = new javax.swing.JLabel();
        jLabelDepartment = new javax.swing.JLabel();
        jLabelPosition = new javax.swing.JLabel();
        jLabelRole = new javax.swing.JLabel();
        jLabelSupervisor = new javax.swing.JLabel();
        jLabelSssNumber = new javax.swing.JLabel();
        jLabelPhilHealthNumber = new javax.swing.JLabel();
        jLabelTin = new javax.swing.JLabel();
        jLabelPagIbigNumber = new javax.swing.JLabel();
        jLabelBasicSalary = new javax.swing.JLabel();
        jLabelSemiMonthlyRate = new javax.swing.JLabel();
        jLabelHourlyRate = new javax.swing.JLabel();
        jLabelRiceSubsidy = new javax.swing.JLabel();
        jLabelPhoneAllowance = new javax.swing.JLabel();
        jLabelClothingAllowance = new javax.swing.JLabel();
        jTextFieldSssNumber = new javax.swing.JTextField();
        jTextFieldPhilHealthNumber = new javax.swing.JTextField();
        jTextFieldTin = new javax.swing.JTextField();
        jTextFieldPagIbigNumber = new javax.swing.JTextField();
        jTextFieldSemiMonthlyRate = new javax.swing.JTextField();
        jTextFieldHourlyRate = new javax.swing.JTextField();
        jTextFieldBasicSalary = new javax.swing.JTextField();
        jTextFieldSupervisor = new javax.swing.JTextField();
        jTextFieldLastName = new javax.swing.JTextField();
        jTextFieldFirstName = new javax.swing.JTextField();
        jLabelBack = new javax.swing.JLabel();
        jTextFieldEmployeeID = new javax.swing.JTextField();
        jTextFieldWorkStatus = new javax.swing.JTextField();
        jTextFieldDepartment = new javax.swing.JTextField();
        jTextFieldPosition = new javax.swing.JTextField();
        jTextFieldRole = new javax.swing.JTextField();
        jTextFieldPhoneAllowance = new javax.swing.JTextField();
        jTextFieldClothingAllowance = new javax.swing.JTextField();
        jTextFieldRiceSubsidy = new javax.swing.JTextField();

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
        jLabelHelloAdmin.setBounds(30, 30, 560, 29);

        jLabelCompensationSmall.setText("Compensation > View Record");
        jPanel1.add(jLabelCompensationSmall);
        jLabelCompensationSmall.setBounds(30, 60, 180, 16);

        jPanelCompensationBox.setBackground(new java.awt.Color(255, 255, 255));
        jPanelCompensationBox.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanelCompensationBox.setLayout(null);

        jLabelEmployeeID.setBackground(new java.awt.Color(255, 255, 255));
        jLabelEmployeeID.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelEmployeeID.setForeground(new java.awt.Color(0, 0, 0));
        jLabelEmployeeID.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelEmployeeID.setText("Employee ID :");
        jPanelCompensationBox.add(jLabelEmployeeID);
        jLabelEmployeeID.setBounds(20, 110, 290, 40);

        jLabelLastName.setBackground(new java.awt.Color(255, 255, 255));
        jLabelLastName.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelLastName.setForeground(new java.awt.Color(0, 0, 0));
        jLabelLastName.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelLastName.setText("Last Name :");
        jPanelCompensationBox.add(jLabelLastName);
        jLabelLastName.setBounds(20, 150, 290, 40);

        jLabelFirstName.setBackground(new java.awt.Color(255, 255, 255));
        jLabelFirstName.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelFirstName.setForeground(new java.awt.Color(0, 0, 0));
        jLabelFirstName.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelFirstName.setText("First Name :");
        jPanelCompensationBox.add(jLabelFirstName);
        jLabelFirstName.setBounds(20, 190, 290, 40);

        jLabelWorkStatus.setBackground(new java.awt.Color(255, 255, 255));
        jLabelWorkStatus.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelWorkStatus.setForeground(new java.awt.Color(0, 0, 0));
        jLabelWorkStatus.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelWorkStatus.setText("Work Status :");
        jPanelCompensationBox.add(jLabelWorkStatus);
        jLabelWorkStatus.setBounds(20, 230, 290, 40);

        jLabelDepartment.setBackground(new java.awt.Color(255, 255, 255));
        jLabelDepartment.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelDepartment.setForeground(new java.awt.Color(0, 0, 0));
        jLabelDepartment.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelDepartment.setText("Department :");
        jPanelCompensationBox.add(jLabelDepartment);
        jLabelDepartment.setBounds(20, 270, 290, 40);

        jLabelPosition.setBackground(new java.awt.Color(255, 255, 255));
        jLabelPosition.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelPosition.setForeground(new java.awt.Color(0, 0, 0));
        jLabelPosition.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelPosition.setText("Position :");
        jPanelCompensationBox.add(jLabelPosition);
        jLabelPosition.setBounds(20, 310, 290, 40);

        jLabelRole.setBackground(new java.awt.Color(255, 255, 255));
        jLabelRole.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelRole.setForeground(new java.awt.Color(0, 0, 0));
        jLabelRole.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelRole.setText("Role :");
        jPanelCompensationBox.add(jLabelRole);
        jLabelRole.setBounds(20, 350, 290, 40);

        jLabelSupervisor.setBackground(new java.awt.Color(255, 255, 255));
        jLabelSupervisor.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelSupervisor.setForeground(new java.awt.Color(0, 0, 0));
        jLabelSupervisor.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelSupervisor.setText("Supervisor :");
        jPanelCompensationBox.add(jLabelSupervisor);
        jLabelSupervisor.setBounds(20, 390, 270, 40);

        jLabelSssNumber.setBackground(new java.awt.Color(255, 255, 255));
        jLabelSssNumber.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelSssNumber.setForeground(new java.awt.Color(0, 0, 0));
        jLabelSssNumber.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelSssNumber.setText("SSS No. :");
        jPanelCompensationBox.add(jLabelSssNumber);
        jLabelSssNumber.setBounds(560, 110, 270, 40);

        jLabelPhilHealthNumber.setBackground(new java.awt.Color(255, 255, 255));
        jLabelPhilHealthNumber.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelPhilHealthNumber.setForeground(new java.awt.Color(0, 0, 0));
        jLabelPhilHealthNumber.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelPhilHealthNumber.setText("PhilHealth No. :");
        jPanelCompensationBox.add(jLabelPhilHealthNumber);
        jLabelPhilHealthNumber.setBounds(560, 150, 270, 40);

        jLabelTin.setBackground(new java.awt.Color(255, 255, 255));
        jLabelTin.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelTin.setForeground(new java.awt.Color(0, 0, 0));
        jLabelTin.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelTin.setText("TIN :");
        jPanelCompensationBox.add(jLabelTin);
        jLabelTin.setBounds(20, 430, 290, 40);

        jLabelPagIbigNumber.setBackground(new java.awt.Color(255, 255, 255));
        jLabelPagIbigNumber.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelPagIbigNumber.setForeground(new java.awt.Color(0, 0, 0));
        jLabelPagIbigNumber.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelPagIbigNumber.setText("Pag-Ibig No. :");
        jPanelCompensationBox.add(jLabelPagIbigNumber);
        jLabelPagIbigNumber.setBounds(560, 190, 270, 40);

        jLabelBasicSalary.setBackground(new java.awt.Color(255, 255, 255));
        jLabelBasicSalary.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelBasicSalary.setForeground(new java.awt.Color(0, 0, 0));
        jLabelBasicSalary.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelBasicSalary.setText("Basic Salary :");
        jPanelCompensationBox.add(jLabelBasicSalary);
        jLabelBasicSalary.setBounds(560, 230, 270, 40);

        jLabelSemiMonthlyRate.setBackground(new java.awt.Color(255, 255, 255));
        jLabelSemiMonthlyRate.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelSemiMonthlyRate.setForeground(new java.awt.Color(0, 0, 0));
        jLabelSemiMonthlyRate.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelSemiMonthlyRate.setText("Semi-Monthly Rate :");
        jPanelCompensationBox.add(jLabelSemiMonthlyRate);
        jLabelSemiMonthlyRate.setBounds(560, 270, 270, 40);

        jLabelHourlyRate.setBackground(new java.awt.Color(255, 255, 255));
        jLabelHourlyRate.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelHourlyRate.setForeground(new java.awt.Color(0, 0, 0));
        jLabelHourlyRate.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelHourlyRate.setText("Hourly Rate :");
        jPanelCompensationBox.add(jLabelHourlyRate);
        jLabelHourlyRate.setBounds(560, 310, 270, 40);

        jLabelRiceSubsidy.setBackground(new java.awt.Color(255, 255, 255));
        jLabelRiceSubsidy.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelRiceSubsidy.setForeground(new java.awt.Color(0, 0, 0));
        jLabelRiceSubsidy.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelRiceSubsidy.setText("Rice Subsidy :");
        jPanelCompensationBox.add(jLabelRiceSubsidy);
        jLabelRiceSubsidy.setBounds(560, 350, 270, 40);

        jLabelPhoneAllowance.setBackground(new java.awt.Color(255, 255, 255));
        jLabelPhoneAllowance.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelPhoneAllowance.setForeground(new java.awt.Color(0, 0, 0));
        jLabelPhoneAllowance.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelPhoneAllowance.setText("Phone Allowance :");
        jPanelCompensationBox.add(jLabelPhoneAllowance);
        jLabelPhoneAllowance.setBounds(560, 390, 270, 40);

        jLabelClothingAllowance.setBackground(new java.awt.Color(255, 255, 255));
        jLabelClothingAllowance.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelClothingAllowance.setForeground(new java.awt.Color(0, 0, 0));
        jLabelClothingAllowance.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelClothingAllowance.setText("Clothing Allowance :");
        jPanelCompensationBox.add(jLabelClothingAllowance);
        jLabelClothingAllowance.setBounds(560, 430, 270, 40);

        jTextFieldSssNumber.setBackground(new java.awt.Color(240, 240, 240));
        jTextFieldSssNumber.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jTextFieldSssNumber.setEnabled(false);
        jTextFieldSssNumber.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldSssNumberActionPerformed(evt);
            }
        });
        jPanelCompensationBox.add(jTextFieldSssNumber);
        jTextFieldSssNumber.setBounds(710, 110, 330, 40);

        jTextFieldPhilHealthNumber.setBackground(new java.awt.Color(240, 240, 240));
        jTextFieldPhilHealthNumber.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jTextFieldPhilHealthNumber.setEnabled(false);
        jTextFieldPhilHealthNumber.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldPhilHealthNumberActionPerformed(evt);
            }
        });
        jPanelCompensationBox.add(jTextFieldPhilHealthNumber);
        jTextFieldPhilHealthNumber.setBounds(710, 150, 330, 40);

        jTextFieldTin.setBackground(new java.awt.Color(240, 240, 240));
        jTextFieldTin.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jTextFieldTin.setEnabled(false);
        jTextFieldTin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldTinActionPerformed(evt);
            }
        });
        jPanelCompensationBox.add(jTextFieldTin);
        jTextFieldTin.setBounds(170, 430, 350, 40);

        jTextFieldPagIbigNumber.setBackground(new java.awt.Color(240, 240, 240));
        jTextFieldPagIbigNumber.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jTextFieldPagIbigNumber.setEnabled(false);
        jTextFieldPagIbigNumber.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldPagIbigNumberActionPerformed(evt);
            }
        });
        jPanelCompensationBox.add(jTextFieldPagIbigNumber);
        jTextFieldPagIbigNumber.setBounds(710, 190, 330, 40);

        jTextFieldSemiMonthlyRate.setBackground(new java.awt.Color(240, 240, 240));
        jTextFieldSemiMonthlyRate.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jTextFieldSemiMonthlyRate.setEnabled(false);
        jTextFieldSemiMonthlyRate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldSemiMonthlyRateActionPerformed(evt);
            }
        });
        jPanelCompensationBox.add(jTextFieldSemiMonthlyRate);
        jTextFieldSemiMonthlyRate.setBounds(710, 270, 330, 40);

        jTextFieldHourlyRate.setBackground(new java.awt.Color(240, 240, 240));
        jTextFieldHourlyRate.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jTextFieldHourlyRate.setEnabled(false);
        jTextFieldHourlyRate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldHourlyRateActionPerformed(evt);
            }
        });
        jPanelCompensationBox.add(jTextFieldHourlyRate);
        jTextFieldHourlyRate.setBounds(710, 310, 330, 40);

        jTextFieldBasicSalary.setBackground(new java.awt.Color(240, 240, 240));
        jTextFieldBasicSalary.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jTextFieldBasicSalary.setEnabled(false);
        jTextFieldBasicSalary.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldBasicSalaryActionPerformed(evt);
            }
        });
        jPanelCompensationBox.add(jTextFieldBasicSalary);
        jTextFieldBasicSalary.setBounds(710, 230, 330, 40);

        jTextFieldSupervisor.setBackground(new java.awt.Color(240, 240, 240));
        jTextFieldSupervisor.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jTextFieldSupervisor.setEnabled(false);
        jTextFieldSupervisor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldSupervisorActionPerformed(evt);
            }
        });
        jPanelCompensationBox.add(jTextFieldSupervisor);
        jTextFieldSupervisor.setBounds(170, 390, 350, 40);

        jTextFieldLastName.setBackground(new java.awt.Color(240, 240, 240));
        jTextFieldLastName.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jTextFieldLastName.setEnabled(false);
        jTextFieldLastName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldLastNameActionPerformed(evt);
            }
        });
        jPanelCompensationBox.add(jTextFieldLastName);
        jTextFieldLastName.setBounds(170, 150, 350, 40);

        jTextFieldFirstName.setBackground(new java.awt.Color(240, 240, 240));
        jTextFieldFirstName.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jTextFieldFirstName.setEnabled(false);
        jTextFieldFirstName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldFirstNameActionPerformed(evt);
            }
        });
        jPanelCompensationBox.add(jTextFieldFirstName);
        jTextFieldFirstName.setBounds(170, 190, 350, 40);

        jLabelBack.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/back-icon.png"))); // NOI18N
        jLabelBack.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelBackMouseClicked(evt);
            }
        });
        jPanelCompensationBox.add(jLabelBack);
        jLabelBack.setBounds(0, 560, 60, 60);

        jTextFieldEmployeeID.setBackground(new java.awt.Color(240, 240, 240));
        jTextFieldEmployeeID.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jTextFieldEmployeeID.setEnabled(false);
        jTextFieldEmployeeID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldEmployeeIDActionPerformed(evt);
            }
        });
        jPanelCompensationBox.add(jTextFieldEmployeeID);
        jTextFieldEmployeeID.setBounds(170, 110, 350, 40);

        jTextFieldWorkStatus.setBackground(new java.awt.Color(240, 240, 240));
        jTextFieldWorkStatus.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jTextFieldWorkStatus.setEnabled(false);
        jTextFieldWorkStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldWorkStatusActionPerformed(evt);
            }
        });
        jPanelCompensationBox.add(jTextFieldWorkStatus);
        jTextFieldWorkStatus.setBounds(170, 230, 350, 40);

        jTextFieldDepartment.setBackground(new java.awt.Color(240, 240, 240));
        jTextFieldDepartment.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jTextFieldDepartment.setEnabled(false);
        jTextFieldDepartment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldDepartmentActionPerformed(evt);
            }
        });
        jPanelCompensationBox.add(jTextFieldDepartment);
        jTextFieldDepartment.setBounds(170, 270, 350, 40);

        jTextFieldPosition.setBackground(new java.awt.Color(240, 240, 240));
        jTextFieldPosition.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jTextFieldPosition.setEnabled(false);
        jTextFieldPosition.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldPositionActionPerformed(evt);
            }
        });
        jPanelCompensationBox.add(jTextFieldPosition);
        jTextFieldPosition.setBounds(170, 310, 350, 40);

        jTextFieldRole.setBackground(new java.awt.Color(240, 240, 240));
        jTextFieldRole.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jTextFieldRole.setEnabled(false);
        jTextFieldRole.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldRoleActionPerformed(evt);
            }
        });
        jPanelCompensationBox.add(jTextFieldRole);
        jTextFieldRole.setBounds(170, 350, 350, 40);

        jTextFieldPhoneAllowance.setBackground(new java.awt.Color(240, 240, 240));
        jTextFieldPhoneAllowance.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jTextFieldPhoneAllowance.setEnabled(false);
        jTextFieldPhoneAllowance.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldPhoneAllowanceActionPerformed(evt);
            }
        });
        jPanelCompensationBox.add(jTextFieldPhoneAllowance);
        jTextFieldPhoneAllowance.setBounds(710, 390, 330, 40);

        jTextFieldClothingAllowance.setBackground(new java.awt.Color(240, 240, 240));
        jTextFieldClothingAllowance.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jTextFieldClothingAllowance.setEnabled(false);
        jTextFieldClothingAllowance.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldClothingAllowanceActionPerformed(evt);
            }
        });
        jPanelCompensationBox.add(jTextFieldClothingAllowance);
        jTextFieldClothingAllowance.setBounds(710, 430, 330, 40);

        jTextFieldRiceSubsidy.setBackground(new java.awt.Color(240, 240, 240));
        jTextFieldRiceSubsidy.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jTextFieldRiceSubsidy.setEnabled(false);
        jTextFieldRiceSubsidy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldRiceSubsidyActionPerformed(evt);
            }
        });
        jPanelCompensationBox.add(jTextFieldRiceSubsidy);
        jTextFieldRiceSubsidy.setBounds(710, 350, 330, 40);

        jPanel1.add(jPanelCompensationBox);
        jPanelCompensationBox.setBounds(30, 100, 1060, 650);

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldSssNumberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldSssNumberActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldSssNumberActionPerformed

    private void jTextFieldPhilHealthNumberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldPhilHealthNumberActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldPhilHealthNumberActionPerformed

    private void jTextFieldTinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldTinActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldTinActionPerformed

    private void jTextFieldPagIbigNumberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldPagIbigNumberActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldPagIbigNumberActionPerformed

    private void jTextFieldSemiMonthlyRateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldSemiMonthlyRateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldSemiMonthlyRateActionPerformed

    private void jTextFieldHourlyRateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldHourlyRateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldHourlyRateActionPerformed

    private void jTextFieldBasicSalaryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldBasicSalaryActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldBasicSalaryActionPerformed

    private void jTextFieldSupervisorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldSupervisorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldSupervisorActionPerformed

    private void jTextFieldLastNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldLastNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldLastNameActionPerformed

    private void jTextFieldFirstNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldFirstNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldFirstNameActionPerformed

    private void jLabelBackMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelBackMouseClicked
        // TODO add your handling code here:
        CardLayout cardLayout = (CardLayout) financePortal.getPanelParentCard().getLayout();
        cardLayout.show(financePortal.getPanelParentCard(), "Compensation");
    }//GEN-LAST:event_jLabelBackMouseClicked

    private void jTextFieldEmployeeIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldEmployeeIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldEmployeeIDActionPerformed

    private void jTextFieldWorkStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldWorkStatusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldWorkStatusActionPerformed

    private void jTextFieldDepartmentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldDepartmentActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldDepartmentActionPerformed

    private void jTextFieldPositionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldPositionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldPositionActionPerformed

    private void jTextFieldRoleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldRoleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldRoleActionPerformed

    private void jTextFieldPhoneAllowanceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldPhoneAllowanceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldPhoneAllowanceActionPerformed

    private void jTextFieldClothingAllowanceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldClothingAllowanceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldClothingAllowanceActionPerformed

    private void jTextFieldRiceSubsidyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldRiceSubsidyActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldRiceSubsidyActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabelBack;
    private javax.swing.JLabel jLabelBasicSalary;
    private javax.swing.JLabel jLabelClothingAllowance;
    private javax.swing.JLabel jLabelCompensationSmall;
    private javax.swing.JLabel jLabelDepartment;
    private javax.swing.JLabel jLabelEmployeeID;
    private javax.swing.JLabel jLabelFirstName;
    private javax.swing.JLabel jLabelHelloAdmin;
    private javax.swing.JLabel jLabelHourlyRate;
    private javax.swing.JLabel jLabelLastName;
    private javax.swing.JLabel jLabelPagIbigNumber;
    private javax.swing.JLabel jLabelPhilHealthNumber;
    private javax.swing.JLabel jLabelPhoneAllowance;
    private javax.swing.JLabel jLabelPosition;
    private javax.swing.JLabel jLabelRiceSubsidy;
    private javax.swing.JLabel jLabelRole;
    private javax.swing.JLabel jLabelSemiMonthlyRate;
    private javax.swing.JLabel jLabelSssNumber;
    private javax.swing.JLabel jLabelSupervisor;
    private javax.swing.JLabel jLabelTin;
    private javax.swing.JLabel jLabelWorkStatus;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanelCompensationBox;
    private javax.swing.JTextField jTextFieldBasicSalary;
    private javax.swing.JTextField jTextFieldClothingAllowance;
    private javax.swing.JTextField jTextFieldDepartment;
    private javax.swing.JTextField jTextFieldEmployeeID;
    private javax.swing.JTextField jTextFieldFirstName;
    private javax.swing.JTextField jTextFieldHourlyRate;
    private javax.swing.JTextField jTextFieldLastName;
    private javax.swing.JTextField jTextFieldPagIbigNumber;
    private javax.swing.JTextField jTextFieldPhilHealthNumber;
    private javax.swing.JTextField jTextFieldPhoneAllowance;
    private javax.swing.JTextField jTextFieldPosition;
    private javax.swing.JTextField jTextFieldRiceSubsidy;
    private javax.swing.JTextField jTextFieldRole;
    private javax.swing.JTextField jTextFieldSemiMonthlyRate;
    private javax.swing.JTextField jTextFieldSssNumber;
    private javax.swing.JTextField jTextFieldSupervisor;
    private javax.swing.JTextField jTextFieldTin;
    private javax.swing.JTextField jTextFieldWorkStatus;
    // End of variables declaration//GEN-END:variables
}