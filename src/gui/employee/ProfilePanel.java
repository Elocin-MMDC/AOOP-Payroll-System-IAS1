package gui.employee;

import java.awt.CardLayout;
import model.dao.EmployeeViewDAO;
import model.pojo.EmployeeView;
import util.Session;
import util.UIUtil;

public class ProfilePanel extends javax.swing.JPanel {
    
    private final EmployeePortal employeePortal;

    public ProfilePanel(EmployeePortal employeePortal) {
        this.employeePortal = employeePortal;
        initComponents();
        UIUtil.setGreeting(jLabelHelloEmployee, "Employee");
        loadProfile();
    }
    
    private void loadProfile() {
        int empId = Session.getCurrentUser().getEmployeeID();
        
        EmployeeView efd = new EmployeeViewDAO().getById(empId);
        
        if (efd != null) {
            jTextFieldEmployeeID.setText(String.valueOf(efd.getEmployeeID()));
            jTextFieldLastName.setText(efd.getLastName());
            jTextFieldFirstName.setText(efd.getFirstName());
            jTextFieldGender.setText(efd.getGender());
            jTextFieldBirthday.setText(efd.getBirthday().toString());
            jTextFieldPhoneNumber.setText(efd.getPhoneNumber());
            jTextAreaAddress.setText(efd.getFullAddress());
            jTextFieldWorkStatus.setText(efd.getWorkStatus());
            jTextFieldRole.setText(efd.getRole());
            jTextFieldPosition.setText(efd.getPositionTitle());
            jTextFieldDepartment.setText(efd.getDepartmentName());
            jTextFieldSupervisor.setText(efd.getSupervisorName());
            jTextFieldSssNumber.setText(efd.getSssNumber());
            jTextFieldPhilHealthNumber.setText(efd.getPhilHealthNumber());
            jTextFieldTin.setText(efd.getTin());
            jTextFieldPagIbigNumber.setText(efd.getPagIbigNumber());
            jTextFieldBasicSalary.setText(efd.getBasicSalary().toPlainString());
            jTextFieldSemiMonthlyRate.setText(efd.getSemiMonthlyRate().toPlainString());
            jTextFieldHourlyRate.setText(efd.getHourlyRate().toPlainString());
            jTextFieldRiceSubsidy.setText(efd.getRiceSubsidy().toPlainString());
            jTextFieldPhoneAllowance.setText(efd.getPhoneAllowance().toPlainString());
            jTextFieldClothingAllowance.setText(efd.getClothingAllowance().toPlainString());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabelHelloEmployee = new javax.swing.JLabel();
        jLabelProfileSmall = new javax.swing.JLabel();
        jPanelProfileBox = new javax.swing.JPanel();
        jLabelClothingAllowance = new javax.swing.JLabel();
        jLabelPhoneAllowance = new javax.swing.JLabel();
        jLabelRiceSubsidy = new javax.swing.JLabel();
        jLabelHourlyRate = new javax.swing.JLabel();
        jLabelBasicSalary = new javax.swing.JLabel();
        jLabelPagIbigNumber = new javax.swing.JLabel();
        jLabelTin = new javax.swing.JLabel();
        jLabelPhilHealthNumber = new javax.swing.JLabel();
        jLabelSssNumber = new javax.swing.JLabel();
        jTextFieldSssNumber = new javax.swing.JTextField();
        jTextFieldPhilHealthNumber = new javax.swing.JTextField();
        jTextFieldTin = new javax.swing.JTextField();
        jTextFieldPagIbigNumber = new javax.swing.JTextField();
        jTextFieldSemiMonthlyRate = new javax.swing.JTextField();
        jTextFieldHourlyRate = new javax.swing.JTextField();
        jTextFieldRiceSubsidy = new javax.swing.JTextField();
        jTextFieldPhoneAllowance = new javax.swing.JTextField();
        jTextFieldClothingAllowance = new javax.swing.JTextField();
        jTextFieldPhoneNumber = new javax.swing.JTextField();
        jTextFieldLastName = new javax.swing.JTextField();
        jTextFieldEmployeeID = new javax.swing.JTextField();
        jLabelEmployeeNumber = new javax.swing.JLabel();
        jLabelLastName = new javax.swing.JLabel();
        jLabelFirstName = new javax.swing.JLabel();
        jLabelEmployeeDateOfBirth = new javax.swing.JLabel();
        jLabelPhoneNumber = new javax.swing.JLabel();
        jLabelWorkStatus = new javax.swing.JLabel();
        jLabelDepartment = new javax.swing.JLabel();
        jLabelPosition = new javax.swing.JLabel();
        jLabelSupervisor = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaAddress = new javax.swing.JTextArea();
        jLabelAddress2 = new javax.swing.JLabel();
        jLabelSemiMonthlyRate = new javax.swing.JLabel();
        jTextFieldBasicSalary = new javax.swing.JTextField();
        jLabelGender = new javax.swing.JLabel();
        jLabelRole = new javax.swing.JLabel();
        jTextFieldFirstName = new javax.swing.JTextField();
        jTextFieldSupervisor = new javax.swing.JTextField();
        jTextFieldBirthday = new javax.swing.JTextField();
        jTextFieldWorkStatus = new javax.swing.JTextField();
        jTextFieldDepartment = new javax.swing.JTextField();
        jTextFieldPosition = new javax.swing.JTextField();
        jTextFieldRole = new javax.swing.JTextField();
        jTextFieldGender = new javax.swing.JTextField();
        jButtonChangePassword = new javax.swing.JButton();

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
        jLabelHelloEmployee.setBounds(30, 30, 610, 29);

        jLabelProfileSmall.setText("Profile");
        jPanel1.add(jLabelProfileSmall);
        jLabelProfileSmall.setBounds(30, 60, 180, 16);

        jPanelProfileBox.setBackground(new java.awt.Color(255, 255, 255));
        jPanelProfileBox.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanelProfileBox.setLayout(null);

        jLabelClothingAllowance.setBackground(new java.awt.Color(255, 255, 255));
        jLabelClothingAllowance.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelClothingAllowance.setForeground(new java.awt.Color(0, 0, 0));
        jLabelClothingAllowance.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelClothingAllowance.setText("Clothing Allowance :");
        jPanelProfileBox.add(jLabelClothingAllowance);
        jLabelClothingAllowance.setBounds(550, 530, 270, 40);

        jLabelPhoneAllowance.setBackground(new java.awt.Color(255, 255, 255));
        jLabelPhoneAllowance.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelPhoneAllowance.setForeground(new java.awt.Color(0, 0, 0));
        jLabelPhoneAllowance.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelPhoneAllowance.setText("Phone Allowance :");
        jPanelProfileBox.add(jLabelPhoneAllowance);
        jLabelPhoneAllowance.setBounds(550, 490, 270, 40);

        jLabelRiceSubsidy.setBackground(new java.awt.Color(255, 255, 255));
        jLabelRiceSubsidy.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelRiceSubsidy.setForeground(new java.awt.Color(0, 0, 0));
        jLabelRiceSubsidy.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelRiceSubsidy.setText("Rice Subsidy :");
        jPanelProfileBox.add(jLabelRiceSubsidy);
        jLabelRiceSubsidy.setBounds(550, 450, 270, 40);

        jLabelHourlyRate.setBackground(new java.awt.Color(255, 255, 255));
        jLabelHourlyRate.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelHourlyRate.setForeground(new java.awt.Color(0, 0, 0));
        jLabelHourlyRate.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelHourlyRate.setText("Hourly Rate :");
        jPanelProfileBox.add(jLabelHourlyRate);
        jLabelHourlyRate.setBounds(550, 410, 270, 40);

        jLabelBasicSalary.setBackground(new java.awt.Color(255, 255, 255));
        jLabelBasicSalary.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelBasicSalary.setForeground(new java.awt.Color(0, 0, 0));
        jLabelBasicSalary.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelBasicSalary.setText("Basic Salary :");
        jPanelProfileBox.add(jLabelBasicSalary);
        jLabelBasicSalary.setBounds(550, 330, 270, 40);

        jLabelPagIbigNumber.setBackground(new java.awt.Color(255, 255, 255));
        jLabelPagIbigNumber.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelPagIbigNumber.setForeground(new java.awt.Color(0, 0, 0));
        jLabelPagIbigNumber.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelPagIbigNumber.setText("Pag-Ibig No. :");
        jPanelProfileBox.add(jLabelPagIbigNumber);
        jLabelPagIbigNumber.setBounds(550, 290, 270, 40);

        jLabelTin.setBackground(new java.awt.Color(255, 255, 255));
        jLabelTin.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelTin.setForeground(new java.awt.Color(0, 0, 0));
        jLabelTin.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelTin.setText("TIN :");
        jPanelProfileBox.add(jLabelTin);
        jLabelTin.setBounds(550, 250, 270, 40);

        jLabelPhilHealthNumber.setBackground(new java.awt.Color(255, 255, 255));
        jLabelPhilHealthNumber.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelPhilHealthNumber.setForeground(new java.awt.Color(0, 0, 0));
        jLabelPhilHealthNumber.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelPhilHealthNumber.setText("PhilHealth No. :");
        jPanelProfileBox.add(jLabelPhilHealthNumber);
        jLabelPhilHealthNumber.setBounds(550, 210, 270, 40);

        jLabelSssNumber.setBackground(new java.awt.Color(255, 255, 255));
        jLabelSssNumber.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelSssNumber.setForeground(new java.awt.Color(0, 0, 0));
        jLabelSssNumber.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelSssNumber.setText("SSS No. :");
        jPanelProfileBox.add(jLabelSssNumber);
        jLabelSssNumber.setBounds(550, 170, 270, 40);

        jTextFieldSssNumber.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jTextFieldSssNumber.setEnabled(false);
        jTextFieldSssNumber.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldSssNumberActionPerformed(evt);
            }
        });
        jPanelProfileBox.add(jTextFieldSssNumber);
        jTextFieldSssNumber.setBounds(700, 170, 330, 40);

        jTextFieldPhilHealthNumber.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jTextFieldPhilHealthNumber.setEnabled(false);
        jTextFieldPhilHealthNumber.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldPhilHealthNumberActionPerformed(evt);
            }
        });
        jPanelProfileBox.add(jTextFieldPhilHealthNumber);
        jTextFieldPhilHealthNumber.setBounds(700, 210, 330, 40);

        jTextFieldTin.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jTextFieldTin.setEnabled(false);
        jTextFieldTin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldTinActionPerformed(evt);
            }
        });
        jPanelProfileBox.add(jTextFieldTin);
        jTextFieldTin.setBounds(700, 250, 330, 40);

        jTextFieldPagIbigNumber.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jTextFieldPagIbigNumber.setEnabled(false);
        jTextFieldPagIbigNumber.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldPagIbigNumberActionPerformed(evt);
            }
        });
        jPanelProfileBox.add(jTextFieldPagIbigNumber);
        jTextFieldPagIbigNumber.setBounds(700, 290, 330, 40);

        jTextFieldSemiMonthlyRate.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jTextFieldSemiMonthlyRate.setEnabled(false);
        jTextFieldSemiMonthlyRate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldSemiMonthlyRateActionPerformed(evt);
            }
        });
        jPanelProfileBox.add(jTextFieldSemiMonthlyRate);
        jTextFieldSemiMonthlyRate.setBounds(700, 370, 330, 40);

        jTextFieldHourlyRate.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jTextFieldHourlyRate.setEnabled(false);
        jTextFieldHourlyRate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldHourlyRateActionPerformed(evt);
            }
        });
        jPanelProfileBox.add(jTextFieldHourlyRate);
        jTextFieldHourlyRate.setBounds(700, 410, 330, 40);

        jTextFieldRiceSubsidy.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jTextFieldRiceSubsidy.setEnabled(false);
        jTextFieldRiceSubsidy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldRiceSubsidyActionPerformed(evt);
            }
        });
        jPanelProfileBox.add(jTextFieldRiceSubsidy);
        jTextFieldRiceSubsidy.setBounds(700, 450, 330, 40);

        jTextFieldPhoneAllowance.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jTextFieldPhoneAllowance.setEnabled(false);
        jTextFieldPhoneAllowance.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldPhoneAllowanceActionPerformed(evt);
            }
        });
        jPanelProfileBox.add(jTextFieldPhoneAllowance);
        jTextFieldPhoneAllowance.setBounds(700, 490, 330, 40);

        jTextFieldClothingAllowance.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jTextFieldClothingAllowance.setEnabled(false);
        jTextFieldClothingAllowance.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldClothingAllowanceActionPerformed(evt);
            }
        });
        jPanelProfileBox.add(jTextFieldClothingAllowance);
        jTextFieldClothingAllowance.setBounds(700, 530, 330, 40);

        jTextFieldPhoneNumber.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jTextFieldPhoneNumber.setEnabled(false);
        jTextFieldPhoneNumber.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldPhoneNumberActionPerformed(evt);
            }
        });
        jPanelProfileBox.add(jTextFieldPhoneNumber);
        jTextFieldPhoneNumber.setBounds(180, 370, 340, 40);

        jTextFieldLastName.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jTextFieldLastName.setEnabled(false);
        jTextFieldLastName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldLastNameActionPerformed(evt);
            }
        });
        jPanelProfileBox.add(jTextFieldLastName);
        jTextFieldLastName.setBounds(180, 170, 340, 40);

        jTextFieldEmployeeID.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jTextFieldEmployeeID.setEnabled(false);
        jTextFieldEmployeeID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldEmployeeIDActionPerformed(evt);
            }
        });
        jPanelProfileBox.add(jTextFieldEmployeeID);
        jTextFieldEmployeeID.setBounds(180, 130, 340, 40);

        jLabelEmployeeNumber.setBackground(new java.awt.Color(255, 255, 255));
        jLabelEmployeeNumber.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelEmployeeNumber.setForeground(new java.awt.Color(0, 0, 0));
        jLabelEmployeeNumber.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelEmployeeNumber.setText("Employee ID :");
        jPanelProfileBox.add(jLabelEmployeeNumber);
        jLabelEmployeeNumber.setBounds(30, 130, 290, 40);

        jLabelLastName.setBackground(new java.awt.Color(255, 255, 255));
        jLabelLastName.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelLastName.setForeground(new java.awt.Color(0, 0, 0));
        jLabelLastName.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelLastName.setText("Last Name :");
        jPanelProfileBox.add(jLabelLastName);
        jLabelLastName.setBounds(30, 170, 290, 40);

        jLabelFirstName.setBackground(new java.awt.Color(255, 255, 255));
        jLabelFirstName.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelFirstName.setForeground(new java.awt.Color(0, 0, 0));
        jLabelFirstName.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelFirstName.setText("First Name :");
        jPanelProfileBox.add(jLabelFirstName);
        jLabelFirstName.setBounds(30, 210, 290, 40);

        jLabelEmployeeDateOfBirth.setBackground(new java.awt.Color(255, 255, 255));
        jLabelEmployeeDateOfBirth.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelEmployeeDateOfBirth.setForeground(new java.awt.Color(0, 0, 0));
        jLabelEmployeeDateOfBirth.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelEmployeeDateOfBirth.setText("Date of Birth :");
        jPanelProfileBox.add(jLabelEmployeeDateOfBirth);
        jLabelEmployeeDateOfBirth.setBounds(30, 290, 290, 40);

        jLabelPhoneNumber.setBackground(new java.awt.Color(255, 255, 255));
        jLabelPhoneNumber.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelPhoneNumber.setForeground(new java.awt.Color(0, 0, 0));
        jLabelPhoneNumber.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelPhoneNumber.setText("Phone No. :");
        jPanelProfileBox.add(jLabelPhoneNumber);
        jLabelPhoneNumber.setBounds(30, 370, 290, 40);

        jLabelWorkStatus.setBackground(new java.awt.Color(255, 255, 255));
        jLabelWorkStatus.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelWorkStatus.setForeground(new java.awt.Color(0, 0, 0));
        jLabelWorkStatus.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelWorkStatus.setText("Work Status :");
        jPanelProfileBox.add(jLabelWorkStatus);
        jLabelWorkStatus.setBounds(30, 410, 290, 40);

        jLabelDepartment.setBackground(new java.awt.Color(255, 255, 255));
        jLabelDepartment.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelDepartment.setForeground(new java.awt.Color(0, 0, 0));
        jLabelDepartment.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelDepartment.setText("Department :");
        jPanelProfileBox.add(jLabelDepartment);
        jLabelDepartment.setBounds(30, 450, 290, 40);

        jLabelPosition.setBackground(new java.awt.Color(255, 255, 255));
        jLabelPosition.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelPosition.setForeground(new java.awt.Color(0, 0, 0));
        jLabelPosition.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelPosition.setText("Position :");
        jPanelProfileBox.add(jLabelPosition);
        jLabelPosition.setBounds(30, 490, 290, 40);

        jLabelSupervisor.setBackground(new java.awt.Color(255, 255, 255));
        jLabelSupervisor.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelSupervisor.setForeground(new java.awt.Color(0, 0, 0));
        jLabelSupervisor.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelSupervisor.setText("Supervisor :");
        jPanelProfileBox.add(jLabelSupervisor);
        jLabelSupervisor.setBounds(550, 130, 270, 40);

        jScrollPane1.setEnabled(false);

        jTextAreaAddress.setColumns(20);
        jTextAreaAddress.setLineWrap(true);
        jTextAreaAddress.setRows(5);
        jTextAreaAddress.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jTextAreaAddress.setEnabled(false);
        jScrollPane1.setViewportView(jTextAreaAddress);

        jPanelProfileBox.add(jScrollPane1);
        jScrollPane1.setBounds(180, 330, 340, 40);

        jLabelAddress2.setBackground(new java.awt.Color(255, 255, 255));
        jLabelAddress2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelAddress2.setForeground(new java.awt.Color(0, 0, 0));
        jLabelAddress2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelAddress2.setText("Address :");
        jPanelProfileBox.add(jLabelAddress2);
        jLabelAddress2.setBounds(30, 330, 290, 40);

        jLabelSemiMonthlyRate.setBackground(new java.awt.Color(255, 255, 255));
        jLabelSemiMonthlyRate.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelSemiMonthlyRate.setForeground(new java.awt.Color(0, 0, 0));
        jLabelSemiMonthlyRate.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelSemiMonthlyRate.setText("Semi-Monthly Rate :");
        jPanelProfileBox.add(jLabelSemiMonthlyRate);
        jLabelSemiMonthlyRate.setBounds(550, 370, 270, 40);

        jTextFieldBasicSalary.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jTextFieldBasicSalary.setEnabled(false);
        jTextFieldBasicSalary.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldBasicSalaryActionPerformed(evt);
            }
        });
        jPanelProfileBox.add(jTextFieldBasicSalary);
        jTextFieldBasicSalary.setBounds(700, 330, 330, 40);

        jLabelGender.setBackground(new java.awt.Color(255, 255, 255));
        jLabelGender.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelGender.setForeground(new java.awt.Color(0, 0, 0));
        jLabelGender.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelGender.setText("Gender :");
        jPanelProfileBox.add(jLabelGender);
        jLabelGender.setBounds(30, 250, 290, 40);

        jLabelRole.setBackground(new java.awt.Color(255, 255, 255));
        jLabelRole.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelRole.setForeground(new java.awt.Color(0, 0, 0));
        jLabelRole.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelRole.setText("Role :");
        jPanelProfileBox.add(jLabelRole);
        jLabelRole.setBounds(30, 530, 290, 40);

        jTextFieldFirstName.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jTextFieldFirstName.setEnabled(false);
        jTextFieldFirstName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldFirstNameActionPerformed(evt);
            }
        });
        jPanelProfileBox.add(jTextFieldFirstName);
        jTextFieldFirstName.setBounds(180, 210, 340, 40);

        jTextFieldSupervisor.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jTextFieldSupervisor.setEnabled(false);
        jTextFieldSupervisor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldSupervisorActionPerformed(evt);
            }
        });
        jPanelProfileBox.add(jTextFieldSupervisor);
        jTextFieldSupervisor.setBounds(700, 130, 330, 40);

        jTextFieldBirthday.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jTextFieldBirthday.setEnabled(false);
        jTextFieldBirthday.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldBirthdayActionPerformed(evt);
            }
        });
        jPanelProfileBox.add(jTextFieldBirthday);
        jTextFieldBirthday.setBounds(180, 290, 340, 40);

        jTextFieldWorkStatus.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jTextFieldWorkStatus.setEnabled(false);
        jTextFieldWorkStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldWorkStatusActionPerformed(evt);
            }
        });
        jPanelProfileBox.add(jTextFieldWorkStatus);
        jTextFieldWorkStatus.setBounds(180, 410, 340, 40);

        jTextFieldDepartment.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jTextFieldDepartment.setEnabled(false);
        jTextFieldDepartment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldDepartmentActionPerformed(evt);
            }
        });
        jPanelProfileBox.add(jTextFieldDepartment);
        jTextFieldDepartment.setBounds(180, 450, 340, 40);

        jTextFieldPosition.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jTextFieldPosition.setEnabled(false);
        jTextFieldPosition.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldPositionActionPerformed(evt);
            }
        });
        jPanelProfileBox.add(jTextFieldPosition);
        jTextFieldPosition.setBounds(180, 490, 340, 40);

        jTextFieldRole.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jTextFieldRole.setEnabled(false);
        jTextFieldRole.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldRoleActionPerformed(evt);
            }
        });
        jPanelProfileBox.add(jTextFieldRole);
        jTextFieldRole.setBounds(180, 530, 340, 40);

        jTextFieldGender.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jTextFieldGender.setEnabled(false);
        jTextFieldGender.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldGenderActionPerformed(evt);
            }
        });
        jPanelProfileBox.add(jTextFieldGender);
        jTextFieldGender.setBounds(180, 250, 340, 40);

        jButtonChangePassword.setBackground(new java.awt.Color(0, 43, 89));
        jButtonChangePassword.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButtonChangePassword.setForeground(new java.awt.Color(255, 255, 255));
        jButtonChangePassword.setText("Change Password");
        jButtonChangePassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonChangePasswordActionPerformed(evt);
            }
        });
        jPanelProfileBox.add(jButtonChangePassword);
        jButtonChangePassword.setBounds(810, 40, 220, 40);

        jPanel1.add(jPanelProfileBox);
        jPanelProfileBox.setBounds(30, 110, 1060, 640);

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

    private void jTextFieldRiceSubsidyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldRiceSubsidyActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldRiceSubsidyActionPerformed

    private void jTextFieldPhoneAllowanceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldPhoneAllowanceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldPhoneAllowanceActionPerformed

    private void jTextFieldClothingAllowanceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldClothingAllowanceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldClothingAllowanceActionPerformed

    private void jTextFieldPhoneNumberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldPhoneNumberActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldPhoneNumberActionPerformed

    private void jTextFieldLastNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldLastNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldLastNameActionPerformed

    private void jTextFieldEmployeeIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldEmployeeIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldEmployeeIDActionPerformed

    private void jTextFieldBasicSalaryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldBasicSalaryActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldBasicSalaryActionPerformed

    private void jTextFieldFirstNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldFirstNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldFirstNameActionPerformed

    private void jTextFieldSupervisorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldSupervisorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldSupervisorActionPerformed

    private void jTextFieldBirthdayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldBirthdayActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldBirthdayActionPerformed

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

    private void jTextFieldGenderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldGenderActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldGenderActionPerformed

    private void jButtonChangePasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonChangePasswordActionPerformed
        // TODO add your handling code here:
        CardLayout cardLayout = (CardLayout) employeePortal.getPanelParentCard().getLayout();
        cardLayout.show(employeePortal.getPanelParentCard(), "ChangePassword");
    }//GEN-LAST:event_jButtonChangePasswordActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonChangePassword;
    private javax.swing.JLabel jLabelAddress2;
    private javax.swing.JLabel jLabelBasicSalary;
    private javax.swing.JLabel jLabelClothingAllowance;
    private javax.swing.JLabel jLabelDepartment;
    private javax.swing.JLabel jLabelEmployeeDateOfBirth;
    private javax.swing.JLabel jLabelEmployeeNumber;
    private javax.swing.JLabel jLabelFirstName;
    private javax.swing.JLabel jLabelGender;
    private javax.swing.JLabel jLabelHelloEmployee;
    private javax.swing.JLabel jLabelHourlyRate;
    private javax.swing.JLabel jLabelLastName;
    private javax.swing.JLabel jLabelPagIbigNumber;
    private javax.swing.JLabel jLabelPhilHealthNumber;
    private javax.swing.JLabel jLabelPhoneAllowance;
    private javax.swing.JLabel jLabelPhoneNumber;
    private javax.swing.JLabel jLabelPosition;
    private javax.swing.JLabel jLabelProfileSmall;
    private javax.swing.JLabel jLabelRiceSubsidy;
    private javax.swing.JLabel jLabelRole;
    private javax.swing.JLabel jLabelSemiMonthlyRate;
    private javax.swing.JLabel jLabelSssNumber;
    private javax.swing.JLabel jLabelSupervisor;
    private javax.swing.JLabel jLabelTin;
    private javax.swing.JLabel jLabelWorkStatus;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanelProfileBox;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextAreaAddress;
    private javax.swing.JTextField jTextFieldBasicSalary;
    private javax.swing.JTextField jTextFieldBirthday;
    private javax.swing.JTextField jTextFieldClothingAllowance;
    private javax.swing.JTextField jTextFieldDepartment;
    private javax.swing.JTextField jTextFieldEmployeeID;
    private javax.swing.JTextField jTextFieldFirstName;
    private javax.swing.JTextField jTextFieldGender;
    private javax.swing.JTextField jTextFieldHourlyRate;
    private javax.swing.JTextField jTextFieldLastName;
    private javax.swing.JTextField jTextFieldPagIbigNumber;
    private javax.swing.JTextField jTextFieldPhilHealthNumber;
    private javax.swing.JTextField jTextFieldPhoneAllowance;
    private javax.swing.JTextField jTextFieldPhoneNumber;
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