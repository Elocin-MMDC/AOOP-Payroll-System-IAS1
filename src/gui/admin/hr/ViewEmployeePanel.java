package gui.admin.hr;

import java.awt.CardLayout;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.swing.SwingWorker;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import model.pojo.Employee;
import model.pojo.EmployeeViewForHR;
import model.pojo.Gender;
import model.pojo.Positions;
import model.pojo.Role;
import model.pojo.WorkStatus;
import service.EmployeeService;
import service.EmployeeService.ValidationException;
import util.UIUtil;

public class ViewEmployeePanel extends javax.swing.JPanel {
    
    private final AdminHRPortal hrPortal;
    private final EmployeeService employeeService;
    
    public ViewEmployeePanel(AdminHRPortal hrPortal) {
        this.hrPortal = hrPortal;
        this.employeeService = new EmployeeService();
        initComponents();
        UIUtil.setGreeting(jLabelHelloAdmin, "Admin");
        UIUtil.applyAlphaFormat(jTextFieldLastName, jTextFieldFirstName, jTextFieldBarangay, jTextFieldCity, jTextFieldProvince);
        UIUtil.applyPhoneNumberFormat(jTextFieldPhoneNumber);
        UIUtil.applyStreetFormat(jTextFieldStreet);
        UIUtil.applyZipCodeFormat(jTextFieldZipCode);
        UIUtil.applySSSFormat(jTextFieldSssNumber);
        UIUtil.apply12DigitFormat(jTextFieldPhilHealthNumber, jTextFieldPagIbigNumber);
        UIUtil.applySalaryFormat(jTextFieldBasicSalary);
        UIUtil.applyTINFormat(jTextFieldTin);
        loadComboBoxes();
        hookDynamicSalary();
        hookPositionChanges();
    }
    
    private void loadComboBoxes() {
        // Gender
        jComboBoxGender.removeAllItems();
        jComboBoxGender.addItem("Select");
        List<Gender> genders = employeeService.getAllGenderNames();
        for (Gender gender : genders) {
            jComboBoxGender.addItem(gender.getGenderName());
        }
        
        // WorkStatus
        jComboBoxWorkStatus.removeAllItems();
        jComboBoxWorkStatus.addItem("Select");
        List<WorkStatus> wses = employeeService.getAllWorkStatusNames();
        for (WorkStatus ws : wses) {
            jComboBoxWorkStatus.addItem(ws.getWorkStatusName());
        }
        
        // Role
        jComboBoxRole.removeAllItems();
        jComboBoxRole.addItem("Select");
        List<Role> roles = employeeService.getAllRoleNames();
        for (Role r : roles) {
            jComboBoxRole.addItem(r.getRoleName());
        }
        
        // Position
        jComboBoxPosition.removeAllItems();
        jComboBoxPosition.addItem("Select");
        List<Positions> positions = employeeService.getAllPositionTitles();
        for (Positions p : positions) {
            jComboBoxPosition.addItem(p.getPositionTitle());
        }
        
        // Supervisor
        jComboBoxSupervisor.removeAllItems();
        jComboBoxSupervisor.addItem("Select");

        List<Employee> supervisors = employeeService.getAllSupervisorNames();
        for (Employee supervisor : supervisors) {
            String fullName = supervisor.getFirstName() + " " + supervisor.getLastName();
            jComboBoxSupervisor.addItem(fullName);
        }
        
    }
    
    public void loadSelectedEmployee(int empId) {
        EmployeeViewForHR e = employeeService.getEmpHRViewById(empId);
        if (e == null) {
            return;
        }
        // Basic info
        jTextFieldEmployeeID.setText(String.valueOf(e.getEmployeeID()));
        jTextFieldLastName.setText(e.getLastName());
        jTextFieldFirstName.setText(e.getFirstName());
        jComboBoxGender.setSelectedItem(e.getGender());
        jComboBoxWorkStatus.setSelectedItem(e.getWorkStatus());
        jComboBoxRole.setSelectedItem(e.getRole());
        jComboBoxPosition.setSelectedItem(e.getPositionTitle());
        jTextFieldDepartment.setText(e.getDepartmentName());
        String supervisorName = e.getSupervisorName();
        jComboBoxSupervisor.setSelectedItem(supervisorName != null ? supervisorName : "Select");
        jDateChooserBirthday.setDate(java.sql.Date.valueOf(e.getBirthday()));
        jTextFieldPhoneNumber.setText(e.getPhoneNumber());

        // Address
        jTextFieldStreet.setText(e.getStreet());
        jTextFieldBarangay.setText(e.getBarangay() != null ? e.getBarangay() : "");
        jTextFieldCity.setText(e.getCity());
        jTextFieldProvince.setText(e.getProvince() != null ? e.getProvince() : "");
        jTextFieldZipCode.setText(e.getZipCode() != null ? e.getZipCode() : "");

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
    
    private void hookDynamicSalary() {
        // Semi-monthly rate and hourly rate recalculation based on basic salary value
        jTextFieldBasicSalary.getDocument().addDocumentListener(new DocumentListener() {
            private void updateRates() {
                String txt = jTextFieldBasicSalary.getText().trim();
                if (txt.isEmpty()) {
                    jTextFieldSemiMonthlyRate.setText("");
                    jTextFieldHourlyRate.setText("");
                    return;
                }
                try {
                    BigDecimal basic = new BigDecimal(txt);
                    // semi-monthly rate = basic salary / 2
                    BigDecimal semiMonthlyRate = basic.divide(BigDecimal.valueOf(2), 2, RoundingMode.HALF_UP);
                    // hourly rate = basic salary / 21 / 8 (21 working days, 8 hrs per day)
                    BigDecimal hourlyRate = basic.divide(BigDecimal.valueOf(168), 2, RoundingMode.HALF_UP);

                    jTextFieldSemiMonthlyRate.setText(semiMonthlyRate.toPlainString());
                    jTextFieldHourlyRate.setText(hourlyRate.toPlainString());
                } catch (NumberFormatException ex) {
                    jTextFieldSemiMonthlyRate.setText("");
                    jTextFieldHourlyRate.setText("");
                }
            }
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateRates();
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                updateRates();
            }
            @Override
            public void changedUpdate(DocumentEvent e) {
                updateRates();
            }
        });
    }
    
    private void hookPositionChanges() {
        jComboBoxPosition.addActionListener(e -> {
            String selectedPosition = (String) jComboBoxPosition.getSelectedItem();
            if (selectedPosition == null || selectedPosition.isEmpty()) {
                clearDependentFields();
                return;
            }

            new SwingWorker<Void, Void>() {
                private String department;
                private Map<String, BigDecimal> allowances;

                @Override
                protected Void doInBackground() throws Exception {
                    department = employeeService.getDepartmentNameByPosition(selectedPosition);
                    allowances = employeeService.getAllowancesByPosition(selectedPosition);
                    return null;
                }

                @Override
                protected void done() {
                    jTextFieldDepartment.setText(department != null ? department : "");
                    jTextFieldRiceSubsidy.setText(allowances.getOrDefault("Rice Subsidy", BigDecimal.ZERO).toString());
                    jTextFieldPhoneAllowance.setText(allowances.getOrDefault("Phone Allowance", BigDecimal.ZERO).toString());
                    jTextFieldClothingAllowance.setText(allowances.getOrDefault("Clothing Allowance", BigDecimal.ZERO).toString());
                }
            }.execute();
        });
    }

    private void clearDependentFields() {
        jTextFieldDepartment.setText("");
        jTextFieldRiceSubsidy.setText("");
        jTextFieldPhoneAllowance.setText("");
        jTextFieldClothingAllowance.setText("");
    }
    
    private void deleteEmployee() {
        String empIdText = jTextFieldEmployeeID.getText().trim();

        if (empIdText.isEmpty()) {
            UIUtil.showErrorMessage(this, "No employee selected to delete.", "Missing Employee ID");
            return;
        }

        int employeeID;
        try {
            employeeID = Integer.parseInt(empIdText);
        } catch (NumberFormatException e) {
            UIUtil.showErrorMessage(this, "Invalid employee ID format.", "Error");
            return;
        }
        
        if (!UIUtil.showConfirmation(this, "Are you sure you want to delete this employee?")) {
            return;
        }

        boolean success = employeeService.softDeleteEmployee(employeeID);

        if (success) {
            UIUtil.showInfoMessage(this, "Employee deleted successfully.", "Success");
            hrPortal.getEmployeesPanel().loadEmployeeRecords();
            CardLayout cardLayout = (CardLayout) hrPortal.getPanelParentCard().getLayout();
            cardLayout.show(hrPortal.getPanelParentCard(), "Employees");
        } else {
            UIUtil.showErrorMessage(this, "Failed to delete employee.", "Error");
        }
    }
    
    private void updateEmployee() {
        try {
            int employeeID = Integer.parseInt(jTextFieldEmployeeID.getText().trim());

            String lastName = jTextFieldLastName.getText().trim();
            String firstName = jTextFieldFirstName.getText().trim();
            String genderName = (String) jComboBoxGender.getSelectedItem();
            Date date = jDateChooserBirthday.getDate();

            if (lastName.isEmpty() || firstName.isEmpty() || genderName.equals("Select") || date == null
                    || jTextFieldPhoneNumber.getText().trim().isEmpty() || jTextFieldStreet.getText().trim().isEmpty()
                    || jTextFieldCity.getText().trim().isEmpty()
                    || ((String) jComboBoxWorkStatus.getSelectedItem()).equals("Select")
                    || ((String) jComboBoxRole.getSelectedItem()).equals("Select")
                    || ((String) jComboBoxPosition.getSelectedItem()).equals("Select")
                    || jTextFieldSssNumber.getText().trim().isEmpty() || jTextFieldPhilHealthNumber.getText().trim().isEmpty()
                    || jTextFieldTin.getText().trim().isEmpty() || jTextFieldPagIbigNumber.getText().trim().isEmpty()
                    || jTextFieldBasicSalary.getText().trim().isEmpty()) {

                UIUtil.showErrorMessage(this, "Please fill out all required fields.", "Error");
                return;
            }

            LocalDate birthday = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            String phoneNumber = jTextFieldPhoneNumber.getText().trim();
            String street = jTextFieldStreet.getText().trim();
            String barangay = jTextFieldBarangay.getText().trim();
            String city = jTextFieldCity.getText().trim();
            String province = jTextFieldProvince.getText().trim();
            String zipCode = jTextFieldZipCode.getText().trim();

            String workStatusName = (String) jComboBoxWorkStatus.getSelectedItem();
            String roleName = (String) jComboBoxRole.getSelectedItem();
            String positionTitle = (String) jComboBoxPosition.getSelectedItem();
            String supervisorFullName = (String) jComboBoxSupervisor.getSelectedItem();

            String sssNumber = jTextFieldSssNumber.getText().trim();
            String philHealthNumber = jTextFieldPhilHealthNumber.getText().trim();
            String tin = jTextFieldTin.getText().trim();
            String pagIbigNumber = jTextFieldPagIbigNumber.getText().trim();

            BigDecimal basicSalary = new BigDecimal(jTextFieldBasicSalary.getText().trim());
            BigDecimal semiMonthlyRate = new BigDecimal(jTextFieldSemiMonthlyRate.getText().trim());
            BigDecimal hourlyRate = new BigDecimal(jTextFieldHourlyRate.getText().trim());

            boolean success = employeeService.updateEmployee(
                    employeeID,
                    lastName, firstName, birthday, phoneNumber, genderName,
                    street, barangay, city, province, zipCode,
                    workStatusName, roleName, positionTitle, supervisorFullName, 
                    sssNumber, philHealthNumber, tin, pagIbigNumber,
                    basicSalary, semiMonthlyRate, hourlyRate
            );

            if (success) {
                UIUtil.showInfoMessage(this, "Employee updated successfully.", "Success");
                hrPortal.getEmployeesPanel().loadEmployeeRecords();
            }

        } catch (ValidationException ve) {
            UIUtil.showErrorMessage(this, ve.getMessage(), "Error");
        } catch (Exception e) {
            UIUtil.showErrorMessage(this, "An unexpected error occurred. Please try again.", "Error");
        }
    }
 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabelHelloAdmin = new javax.swing.JLabel();
        jLabelViewRecordSmall = new javax.swing.JLabel();
        jPanelCreateNewEmployeeBox = new javax.swing.JPanel();
        jLabelClothingAllowance = new javax.swing.JLabel();
        jLabelPhoneAllowance = new javax.swing.JLabel();
        jLabelRiceSubsidy = new javax.swing.JLabel();
        jLabelHourlyRate = new javax.swing.JLabel();
        jLabelBasicSalary = new javax.swing.JLabel();
        jLabelPagIbigNumber = new javax.swing.JLabel();
        jLabelTin = new javax.swing.JLabel();
        jLabelPhilHealthNumber = new javax.swing.JLabel();
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
        jTextFieldFirstName = new javax.swing.JTextField();
        jTextFieldLastName = new javax.swing.JTextField();
        jTextFieldDepartment = new javax.swing.JTextField();
        jLabelEmployeeNumber = new javax.swing.JLabel();
        jLabelLastName = new javax.swing.JLabel();
        jLabelFirstName = new javax.swing.JLabel();
        jLabelEmployeeDateOfBirth = new javax.swing.JLabel();
        jLabelPhoneNumber = new javax.swing.JLabel();
        jComboBoxSupervisor = new javax.swing.JComboBox<>();
        jDateChooserBirthday = new com.toedter.calendar.JDateChooser();
        jLabelSemiMonthlyRate = new javax.swing.JLabel();
        jTextFieldBasicSalary = new javax.swing.JTextField();
        jLabelGender = new javax.swing.JLabel();
        jComboBoxGender = new javax.swing.JComboBox<>();
        jLabelBack = new javax.swing.JLabel();
        jLabelStreet = new javax.swing.JLabel();
        jTextFieldStreet = new javax.swing.JTextField();
        jTextFieldBarangay = new javax.swing.JTextField();
        jLabelBarangay = new javax.swing.JLabel();
        jTextFieldCity = new javax.swing.JTextField();
        jTextFieldProvince = new javax.swing.JTextField();
        jLabelProvince = new javax.swing.JLabel();
        jLabelCity = new javax.swing.JLabel();
        jLabelZipCode = new javax.swing.JLabel();
        jTextFieldZipCode = new javax.swing.JTextField();
        jLabelWorkStatus = new javax.swing.JLabel();
        jComboBoxWorkStatus = new javax.swing.JComboBox<>();
        jLabelDepartment = new javax.swing.JLabel();
        jLabelPosition = new javax.swing.JLabel();
        jComboBoxPosition = new javax.swing.JComboBox<>();
        jLabelRole = new javax.swing.JLabel();
        jComboBoxRole = new javax.swing.JComboBox<>();
        jLabelAsterisk5 = new javax.swing.JLabel();
        jLabelAsterisk6 = new javax.swing.JLabel();
        jLabelAsterisk7 = new javax.swing.JLabel();
        jLabelAsterisk8 = new javax.swing.JLabel();
        jLabelAsterisk9 = new javax.swing.JLabel();
        jLabelAsterisk10 = new javax.swing.JLabel();
        jLabelAsterisk11 = new javax.swing.JLabel();
        jLabelAsterisk12 = new javax.swing.JLabel();
        jLabelAsterisk13 = new javax.swing.JLabel();
        jLabelAsterisk14 = new javax.swing.JLabel();
        jLabelAsterisk16 = new javax.swing.JLabel();
        jLabelAsterisk20 = new javax.swing.JLabel();
        jLabelAsterisk17 = new javax.swing.JLabel();
        jLabelAsterisk18 = new javax.swing.JLabel();
        jLabelAsterisk19 = new javax.swing.JLabel();
        jLabelSssNumber = new javax.swing.JLabel();
        jLabelSssNumber1 = new javax.swing.JLabel();
        jTextFieldEmployeeID = new javax.swing.JTextField();
        jButtonUpdate = new javax.swing.JButton();
        jButtonDelete = new javax.swing.JButton();
        jLabelSupervisor = new javax.swing.JLabel();

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

        jLabelViewRecordSmall.setText("Employees > View Record");
        jPanel1.add(jLabelViewRecordSmall);
        jLabelViewRecordSmall.setBounds(30, 60, 180, 16);

        jPanelCreateNewEmployeeBox.setBackground(new java.awt.Color(255, 255, 255));
        jPanelCreateNewEmployeeBox.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanelCreateNewEmployeeBox.setLayout(null);

        jLabelClothingAllowance.setBackground(new java.awt.Color(255, 255, 255));
        jLabelClothingAllowance.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelClothingAllowance.setForeground(new java.awt.Color(0, 0, 0));
        jLabelClothingAllowance.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelClothingAllowance.setText("Clothing Allowance :");
        jPanelCreateNewEmployeeBox.add(jLabelClothingAllowance);
        jLabelClothingAllowance.setBounds(560, 500, 270, 40);

        jLabelPhoneAllowance.setBackground(new java.awt.Color(255, 255, 255));
        jLabelPhoneAllowance.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelPhoneAllowance.setForeground(new java.awt.Color(0, 0, 0));
        jLabelPhoneAllowance.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelPhoneAllowance.setText("Phone Allowance :");
        jPanelCreateNewEmployeeBox.add(jLabelPhoneAllowance);
        jLabelPhoneAllowance.setBounds(560, 460, 270, 40);

        jLabelRiceSubsidy.setBackground(new java.awt.Color(255, 255, 255));
        jLabelRiceSubsidy.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelRiceSubsidy.setForeground(new java.awt.Color(0, 0, 0));
        jLabelRiceSubsidy.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelRiceSubsidy.setText("Rice Subsidy :");
        jPanelCreateNewEmployeeBox.add(jLabelRiceSubsidy);
        jLabelRiceSubsidy.setBounds(560, 420, 270, 40);

        jLabelHourlyRate.setBackground(new java.awt.Color(255, 255, 255));
        jLabelHourlyRate.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelHourlyRate.setForeground(new java.awt.Color(0, 0, 0));
        jLabelHourlyRate.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelHourlyRate.setText("Hourly Rate :");
        jPanelCreateNewEmployeeBox.add(jLabelHourlyRate);
        jLabelHourlyRate.setBounds(560, 380, 270, 40);

        jLabelBasicSalary.setBackground(new java.awt.Color(255, 255, 255));
        jLabelBasicSalary.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelBasicSalary.setForeground(new java.awt.Color(0, 0, 0));
        jLabelBasicSalary.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelBasicSalary.setText("Basic Salary   :");
        jPanelCreateNewEmployeeBox.add(jLabelBasicSalary);
        jLabelBasicSalary.setBounds(560, 300, 270, 40);

        jLabelPagIbigNumber.setBackground(new java.awt.Color(255, 255, 255));
        jLabelPagIbigNumber.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelPagIbigNumber.setForeground(new java.awt.Color(0, 0, 0));
        jLabelPagIbigNumber.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelPagIbigNumber.setText("Pag-Ibig No.   :");
        jPanelCreateNewEmployeeBox.add(jLabelPagIbigNumber);
        jLabelPagIbigNumber.setBounds(560, 260, 270, 40);

        jLabelTin.setBackground(new java.awt.Color(255, 255, 255));
        jLabelTin.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelTin.setForeground(new java.awt.Color(0, 0, 0));
        jLabelTin.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelTin.setText("TIN   :");
        jPanelCreateNewEmployeeBox.add(jLabelTin);
        jLabelTin.setBounds(560, 220, 270, 40);

        jLabelPhilHealthNumber.setBackground(new java.awt.Color(255, 255, 255));
        jLabelPhilHealthNumber.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelPhilHealthNumber.setForeground(new java.awt.Color(0, 0, 0));
        jLabelPhilHealthNumber.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelPhilHealthNumber.setText("PhilHealth No.   :");
        jPanelCreateNewEmployeeBox.add(jLabelPhilHealthNumber);
        jLabelPhilHealthNumber.setBounds(560, 180, 270, 40);

        jTextFieldSssNumber.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        jTextFieldSssNumber.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldSssNumberActionPerformed(evt);
            }
        });
        jPanelCreateNewEmployeeBox.add(jTextFieldSssNumber);
        jTextFieldSssNumber.setBounds(710, 140, 330, 40);

        jTextFieldPhilHealthNumber.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldPhilHealthNumberActionPerformed(evt);
            }
        });
        jPanelCreateNewEmployeeBox.add(jTextFieldPhilHealthNumber);
        jTextFieldPhilHealthNumber.setBounds(710, 180, 330, 40);

        jTextFieldTin.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        jTextFieldTin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldTinActionPerformed(evt);
            }
        });
        jPanelCreateNewEmployeeBox.add(jTextFieldTin);
        jTextFieldTin.setBounds(710, 220, 330, 40);

        jTextFieldPagIbigNumber.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldPagIbigNumberActionPerformed(evt);
            }
        });
        jPanelCreateNewEmployeeBox.add(jTextFieldPagIbigNumber);
        jTextFieldPagIbigNumber.setBounds(710, 260, 330, 40);

        jTextFieldSemiMonthlyRate.setBackground(new java.awt.Color(240, 240, 240));
        jTextFieldSemiMonthlyRate.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jTextFieldSemiMonthlyRate.setEnabled(false);
        jTextFieldSemiMonthlyRate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldSemiMonthlyRateActionPerformed(evt);
            }
        });
        jPanelCreateNewEmployeeBox.add(jTextFieldSemiMonthlyRate);
        jTextFieldSemiMonthlyRate.setBounds(710, 340, 330, 40);

        jTextFieldHourlyRate.setBackground(new java.awt.Color(240, 240, 240));
        jTextFieldHourlyRate.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jTextFieldHourlyRate.setEnabled(false);
        jTextFieldHourlyRate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldHourlyRateActionPerformed(evt);
            }
        });
        jPanelCreateNewEmployeeBox.add(jTextFieldHourlyRate);
        jTextFieldHourlyRate.setBounds(710, 380, 330, 40);

        jTextFieldRiceSubsidy.setBackground(new java.awt.Color(240, 240, 240));
        jTextFieldRiceSubsidy.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jTextFieldRiceSubsidy.setEnabled(false);
        jTextFieldRiceSubsidy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldRiceSubsidyActionPerformed(evt);
            }
        });
        jPanelCreateNewEmployeeBox.add(jTextFieldRiceSubsidy);
        jTextFieldRiceSubsidy.setBounds(710, 420, 330, 40);

        jTextFieldPhoneAllowance.setBackground(new java.awt.Color(240, 240, 240));
        jTextFieldPhoneAllowance.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jTextFieldPhoneAllowance.setEnabled(false);
        jTextFieldPhoneAllowance.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldPhoneAllowanceActionPerformed(evt);
            }
        });
        jPanelCreateNewEmployeeBox.add(jTextFieldPhoneAllowance);
        jTextFieldPhoneAllowance.setBounds(710, 460, 330, 40);

        jTextFieldClothingAllowance.setBackground(new java.awt.Color(240, 240, 240));
        jTextFieldClothingAllowance.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jTextFieldClothingAllowance.setEnabled(false);
        jTextFieldClothingAllowance.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldClothingAllowanceActionPerformed(evt);
            }
        });
        jPanelCreateNewEmployeeBox.add(jTextFieldClothingAllowance);
        jTextFieldClothingAllowance.setBounds(710, 500, 330, 40);

        jTextFieldPhoneNumber.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        jTextFieldPhoneNumber.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldPhoneNumberActionPerformed(evt);
            }
        });
        jPanelCreateNewEmployeeBox.add(jTextFieldPhoneNumber);
        jTextFieldPhoneNumber.setBounds(170, 220, 350, 40);

        jTextFieldFirstName.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        jTextFieldFirstName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldFirstNameActionPerformed(evt);
            }
        });
        jPanelCreateNewEmployeeBox.add(jTextFieldFirstName);
        jTextFieldFirstName.setBounds(170, 100, 350, 40);

        jTextFieldLastName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldLastNameActionPerformed(evt);
            }
        });
        jPanelCreateNewEmployeeBox.add(jTextFieldLastName);
        jTextFieldLastName.setBounds(170, 60, 350, 40);

        jTextFieldDepartment.setBackground(new java.awt.Color(240, 240, 240));
        jTextFieldDepartment.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jTextFieldDepartment.setEnabled(false);
        jTextFieldDepartment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldDepartmentActionPerformed(evt);
            }
        });
        jPanelCreateNewEmployeeBox.add(jTextFieldDepartment);
        jTextFieldDepartment.setBounds(710, 60, 330, 40);

        jLabelEmployeeNumber.setBackground(new java.awt.Color(255, 255, 255));
        jLabelEmployeeNumber.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelEmployeeNumber.setForeground(new java.awt.Color(0, 0, 0));
        jLabelEmployeeNumber.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelEmployeeNumber.setText("Employee ID :");
        jPanelCreateNewEmployeeBox.add(jLabelEmployeeNumber);
        jLabelEmployeeNumber.setBounds(20, 20, 290, 40);

        jLabelLastName.setBackground(new java.awt.Color(255, 255, 255));
        jLabelLastName.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelLastName.setForeground(new java.awt.Color(0, 0, 0));
        jLabelLastName.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelLastName.setText("Last Name   :");
        jPanelCreateNewEmployeeBox.add(jLabelLastName);
        jLabelLastName.setBounds(20, 60, 290, 40);

        jLabelFirstName.setBackground(new java.awt.Color(255, 255, 255));
        jLabelFirstName.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelFirstName.setForeground(new java.awt.Color(0, 0, 0));
        jLabelFirstName.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelFirstName.setText("First Name   :");
        jPanelCreateNewEmployeeBox.add(jLabelFirstName);
        jLabelFirstName.setBounds(20, 100, 290, 40);

        jLabelEmployeeDateOfBirth.setBackground(new java.awt.Color(255, 255, 255));
        jLabelEmployeeDateOfBirth.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelEmployeeDateOfBirth.setForeground(new java.awt.Color(0, 0, 0));
        jLabelEmployeeDateOfBirth.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelEmployeeDateOfBirth.setText("Date of Birth   :");
        jPanelCreateNewEmployeeBox.add(jLabelEmployeeDateOfBirth);
        jLabelEmployeeDateOfBirth.setBounds(20, 180, 290, 40);

        jLabelPhoneNumber.setBackground(new java.awt.Color(255, 255, 255));
        jLabelPhoneNumber.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelPhoneNumber.setForeground(new java.awt.Color(0, 0, 0));
        jLabelPhoneNumber.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelPhoneNumber.setText("Phone No.   :");
        jPanelCreateNewEmployeeBox.add(jLabelPhoneNumber);
        jLabelPhoneNumber.setBounds(20, 220, 290, 40);

        jComboBoxSupervisor.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jComboBoxSupervisor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select" }));
        jComboBoxSupervisor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxSupervisorActionPerformed(evt);
            }
        });
        jPanelCreateNewEmployeeBox.add(jComboBoxSupervisor);
        jComboBoxSupervisor.setBounds(710, 100, 330, 40);

        jDateChooserBirthday.setFocusable(false);
        jPanelCreateNewEmployeeBox.add(jDateChooserBirthday);
        jDateChooserBirthday.setBounds(170, 180, 350, 40);

        jLabelSemiMonthlyRate.setBackground(new java.awt.Color(255, 255, 255));
        jLabelSemiMonthlyRate.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelSemiMonthlyRate.setForeground(new java.awt.Color(0, 0, 0));
        jLabelSemiMonthlyRate.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelSemiMonthlyRate.setText("Semi-Monthly Rate :");
        jPanelCreateNewEmployeeBox.add(jLabelSemiMonthlyRate);
        jLabelSemiMonthlyRate.setBounds(560, 340, 270, 40);

        jTextFieldBasicSalary.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        jTextFieldBasicSalary.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldBasicSalaryActionPerformed(evt);
            }
        });
        jPanelCreateNewEmployeeBox.add(jTextFieldBasicSalary);
        jTextFieldBasicSalary.setBounds(710, 300, 330, 40);

        jLabelGender.setBackground(new java.awt.Color(255, 255, 255));
        jLabelGender.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelGender.setForeground(new java.awt.Color(0, 0, 0));
        jLabelGender.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelGender.setText("Gender   :");
        jPanelCreateNewEmployeeBox.add(jLabelGender);
        jLabelGender.setBounds(20, 140, 290, 40);

        jComboBoxGender.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jComboBoxGender.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select" }));
        jComboBoxGender.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxGenderActionPerformed(evt);
            }
        });
        jPanelCreateNewEmployeeBox.add(jComboBoxGender);
        jComboBoxGender.setBounds(170, 140, 350, 40);

        jLabelBack.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/back-icon.png"))); // NOI18N
        jLabelBack.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelBackMouseClicked(evt);
            }
        });
        jPanelCreateNewEmployeeBox.add(jLabelBack);
        jLabelBack.setBounds(0, 560, 60, 60);

        jLabelStreet.setBackground(new java.awt.Color(255, 255, 255));
        jLabelStreet.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelStreet.setForeground(new java.awt.Color(0, 0, 0));
        jLabelStreet.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelStreet.setText("Street   :");
        jPanelCreateNewEmployeeBox.add(jLabelStreet);
        jLabelStreet.setBounds(20, 260, 290, 40);

        jTextFieldStreet.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        jTextFieldStreet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldStreetActionPerformed(evt);
            }
        });
        jPanelCreateNewEmployeeBox.add(jTextFieldStreet);
        jTextFieldStreet.setBounds(170, 260, 350, 40);

        jTextFieldBarangay.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        jTextFieldBarangay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldBarangayActionPerformed(evt);
            }
        });
        jPanelCreateNewEmployeeBox.add(jTextFieldBarangay);
        jTextFieldBarangay.setBounds(170, 300, 350, 40);

        jLabelBarangay.setBackground(new java.awt.Color(255, 255, 255));
        jLabelBarangay.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelBarangay.setForeground(new java.awt.Color(0, 0, 0));
        jLabelBarangay.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelBarangay.setText("Barangay :");
        jPanelCreateNewEmployeeBox.add(jLabelBarangay);
        jLabelBarangay.setBounds(20, 300, 290, 40);

        jTextFieldCity.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        jTextFieldCity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldCityActionPerformed(evt);
            }
        });
        jPanelCreateNewEmployeeBox.add(jTextFieldCity);
        jTextFieldCity.setBounds(170, 340, 350, 40);

        jTextFieldProvince.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        jTextFieldProvince.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldProvinceActionPerformed(evt);
            }
        });
        jPanelCreateNewEmployeeBox.add(jTextFieldProvince);
        jTextFieldProvince.setBounds(170, 380, 350, 40);

        jLabelProvince.setBackground(new java.awt.Color(255, 255, 255));
        jLabelProvince.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelProvince.setForeground(new java.awt.Color(0, 0, 0));
        jLabelProvince.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelProvince.setText("Province :");
        jPanelCreateNewEmployeeBox.add(jLabelProvince);
        jLabelProvince.setBounds(20, 380, 290, 40);

        jLabelCity.setBackground(new java.awt.Color(255, 255, 255));
        jLabelCity.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelCity.setForeground(new java.awt.Color(0, 0, 0));
        jLabelCity.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelCity.setText("City   :");
        jPanelCreateNewEmployeeBox.add(jLabelCity);
        jLabelCity.setBounds(20, 340, 290, 40);

        jLabelZipCode.setBackground(new java.awt.Color(255, 255, 255));
        jLabelZipCode.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelZipCode.setForeground(new java.awt.Color(0, 0, 0));
        jLabelZipCode.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelZipCode.setText("Zip Code :");
        jPanelCreateNewEmployeeBox.add(jLabelZipCode);
        jLabelZipCode.setBounds(20, 420, 290, 40);

        jTextFieldZipCode.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        jTextFieldZipCode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldZipCodeActionPerformed(evt);
            }
        });
        jPanelCreateNewEmployeeBox.add(jTextFieldZipCode);
        jTextFieldZipCode.setBounds(170, 420, 350, 40);

        jLabelWorkStatus.setBackground(new java.awt.Color(255, 255, 255));
        jLabelWorkStatus.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelWorkStatus.setForeground(new java.awt.Color(0, 0, 0));
        jLabelWorkStatus.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelWorkStatus.setText("Work Status   :");
        jPanelCreateNewEmployeeBox.add(jLabelWorkStatus);
        jLabelWorkStatus.setBounds(20, 460, 290, 40);

        jComboBoxWorkStatus.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jComboBoxWorkStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select" }));
        jComboBoxWorkStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxWorkStatusActionPerformed(evt);
            }
        });
        jPanelCreateNewEmployeeBox.add(jComboBoxWorkStatus);
        jComboBoxWorkStatus.setBounds(170, 460, 350, 40);

        jLabelDepartment.setBackground(new java.awt.Color(255, 255, 255));
        jLabelDepartment.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelDepartment.setForeground(new java.awt.Color(0, 0, 0));
        jLabelDepartment.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelDepartment.setText("Department :");
        jPanelCreateNewEmployeeBox.add(jLabelDepartment);
        jLabelDepartment.setBounds(560, 60, 270, 40);

        jLabelPosition.setBackground(new java.awt.Color(255, 255, 255));
        jLabelPosition.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelPosition.setForeground(new java.awt.Color(0, 0, 0));
        jLabelPosition.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelPosition.setText("Position   :");
        jPanelCreateNewEmployeeBox.add(jLabelPosition);
        jLabelPosition.setBounds(560, 20, 270, 40);

        jComboBoxPosition.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jComboBoxPosition.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select" }));
        jComboBoxPosition.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxPositionActionPerformed(evt);
            }
        });
        jPanelCreateNewEmployeeBox.add(jComboBoxPosition);
        jComboBoxPosition.setBounds(710, 20, 330, 40);

        jLabelRole.setBackground(new java.awt.Color(255, 255, 255));
        jLabelRole.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelRole.setForeground(new java.awt.Color(0, 0, 0));
        jLabelRole.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelRole.setText("Role   :");
        jPanelCreateNewEmployeeBox.add(jLabelRole);
        jLabelRole.setBounds(20, 500, 290, 40);

        jComboBoxRole.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jComboBoxRole.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select" }));
        jComboBoxRole.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxRoleActionPerformed(evt);
            }
        });
        jPanelCreateNewEmployeeBox.add(jComboBoxRole);
        jComboBoxRole.setBounds(170, 500, 350, 40);

        jLabelAsterisk5.setBackground(new java.awt.Color(255, 255, 255));
        jLabelAsterisk5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelAsterisk5.setForeground(new java.awt.Color(255, 0, 0));
        jLabelAsterisk5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelAsterisk5.setText("*");
        jPanelCreateNewEmployeeBox.add(jLabelAsterisk5);
        jLabelAsterisk5.setBounds(70, 50, 50, 50);

        jLabelAsterisk6.setBackground(new java.awt.Color(255, 255, 255));
        jLabelAsterisk6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelAsterisk6.setForeground(new java.awt.Color(255, 0, 0));
        jLabelAsterisk6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelAsterisk6.setText("*");
        jPanelCreateNewEmployeeBox.add(jLabelAsterisk6);
        jLabelAsterisk6.setBounds(70, 90, 50, 50);

        jLabelAsterisk7.setBackground(new java.awt.Color(255, 255, 255));
        jLabelAsterisk7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelAsterisk7.setForeground(new java.awt.Color(255, 0, 0));
        jLabelAsterisk7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelAsterisk7.setText("*");
        jPanelCreateNewEmployeeBox.add(jLabelAsterisk7);
        jLabelAsterisk7.setBounds(50, 130, 50, 50);

        jLabelAsterisk8.setBackground(new java.awt.Color(255, 255, 255));
        jLabelAsterisk8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelAsterisk8.setForeground(new java.awt.Color(255, 0, 0));
        jLabelAsterisk8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelAsterisk8.setText("*");
        jPanelCreateNewEmployeeBox.add(jLabelAsterisk8);
        jLabelAsterisk8.setBounds(80, 170, 60, 50);

        jLabelAsterisk9.setBackground(new java.awt.Color(255, 255, 255));
        jLabelAsterisk9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelAsterisk9.setForeground(new java.awt.Color(255, 0, 0));
        jLabelAsterisk9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelAsterisk9.setText("*");
        jPanelCreateNewEmployeeBox.add(jLabelAsterisk9);
        jLabelAsterisk9.setBounds(70, 210, 50, 50);

        jLabelAsterisk10.setBackground(new java.awt.Color(255, 255, 255));
        jLabelAsterisk10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelAsterisk10.setForeground(new java.awt.Color(255, 0, 0));
        jLabelAsterisk10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelAsterisk10.setText("*");
        jPanelCreateNewEmployeeBox.add(jLabelAsterisk10);
        jLabelAsterisk10.setBounds(40, 250, 50, 50);

        jLabelAsterisk11.setBackground(new java.awt.Color(255, 255, 255));
        jLabelAsterisk11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelAsterisk11.setForeground(new java.awt.Color(255, 0, 0));
        jLabelAsterisk11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelAsterisk11.setText("*");
        jPanelCreateNewEmployeeBox.add(jLabelAsterisk11);
        jLabelAsterisk11.setBounds(30, 330, 40, 50);

        jLabelAsterisk12.setBackground(new java.awt.Color(255, 255, 255));
        jLabelAsterisk12.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelAsterisk12.setForeground(new java.awt.Color(255, 0, 0));
        jLabelAsterisk12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelAsterisk12.setText("*");
        jPanelCreateNewEmployeeBox.add(jLabelAsterisk12);
        jLabelAsterisk12.setBounds(80, 450, 50, 50);

        jLabelAsterisk13.setBackground(new java.awt.Color(255, 255, 255));
        jLabelAsterisk13.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelAsterisk13.setForeground(new java.awt.Color(255, 0, 0));
        jLabelAsterisk13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelAsterisk13.setText("*");
        jPanelCreateNewEmployeeBox.add(jLabelAsterisk13);
        jLabelAsterisk13.setBounds(30, 490, 50, 50);

        jLabelAsterisk14.setBackground(new java.awt.Color(255, 255, 255));
        jLabelAsterisk14.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelAsterisk14.setForeground(new java.awt.Color(255, 0, 0));
        jLabelAsterisk14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelAsterisk14.setText("*");
        jPanelCreateNewEmployeeBox.add(jLabelAsterisk14);
        jLabelAsterisk14.setBounds(600, 10, 40, 50);

        jLabelAsterisk16.setBackground(new java.awt.Color(255, 255, 255));
        jLabelAsterisk16.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelAsterisk16.setForeground(new java.awt.Color(255, 0, 0));
        jLabelAsterisk16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelAsterisk16.setText("*");
        jPanelCreateNewEmployeeBox.add(jLabelAsterisk16);
        jLabelAsterisk16.setBounds(590, 130, 50, 50);

        jLabelAsterisk20.setBackground(new java.awt.Color(255, 255, 255));
        jLabelAsterisk20.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelAsterisk20.setForeground(new java.awt.Color(255, 0, 0));
        jLabelAsterisk20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelAsterisk20.setText("*");
        jPanelCreateNewEmployeeBox.add(jLabelAsterisk20);
        jLabelAsterisk20.setBounds(640, 170, 40, 50);

        jLabelAsterisk17.setBackground(new java.awt.Color(255, 255, 255));
        jLabelAsterisk17.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelAsterisk17.setForeground(new java.awt.Color(255, 0, 0));
        jLabelAsterisk17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelAsterisk17.setText("*");
        jPanelCreateNewEmployeeBox.add(jLabelAsterisk17);
        jLabelAsterisk17.setBounds(560, 210, 60, 50);

        jLabelAsterisk18.setBackground(new java.awt.Color(255, 255, 255));
        jLabelAsterisk18.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelAsterisk18.setForeground(new java.awt.Color(255, 0, 0));
        jLabelAsterisk18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelAsterisk18.setText("*");
        jPanelCreateNewEmployeeBox.add(jLabelAsterisk18);
        jLabelAsterisk18.setBounds(620, 250, 60, 50);

        jLabelAsterisk19.setBackground(new java.awt.Color(255, 255, 255));
        jLabelAsterisk19.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelAsterisk19.setForeground(new java.awt.Color(255, 0, 0));
        jLabelAsterisk19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelAsterisk19.setText("*");
        jPanelCreateNewEmployeeBox.add(jLabelAsterisk19);
        jLabelAsterisk19.setBounds(620, 290, 50, 50);

        jLabelSssNumber.setBackground(new java.awt.Color(255, 255, 255));
        jLabelSssNumber.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelSssNumber.setForeground(new java.awt.Color(0, 0, 0));
        jLabelSssNumber.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelSssNumber.setText("SSS No.   :");
        jPanelCreateNewEmployeeBox.add(jLabelSssNumber);
        jLabelSssNumber.setBounds(560, 140, 270, 40);

        jLabelSssNumber1.setBackground(new java.awt.Color(255, 255, 255));
        jLabelSssNumber1.setFont(new java.awt.Font("Segoe UI", 2, 11)); // NOI18N
        jLabelSssNumber1.setForeground(new java.awt.Color(0, 0, 0));
        jLabelSssNumber1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelSssNumber1.setText("Note: Employee allowances are based on the employee's position title.");
        jPanelCreateNewEmployeeBox.add(jLabelSssNumber1);
        jLabelSssNumber1.setBounds(710, 540, 330, 20);

        jTextFieldEmployeeID.setBackground(new java.awt.Color(240, 240, 240));
        jTextFieldEmployeeID.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jTextFieldEmployeeID.setEnabled(false);
        jTextFieldEmployeeID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldEmployeeIDActionPerformed(evt);
            }
        });
        jPanelCreateNewEmployeeBox.add(jTextFieldEmployeeID);
        jTextFieldEmployeeID.setBounds(170, 20, 350, 40);

        jButtonUpdate.setBackground(new java.awt.Color(0, 135, 0));
        jButtonUpdate.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButtonUpdate.setForeground(new java.awt.Color(255, 255, 255));
        jButtonUpdate.setText("Update");
        jButtonUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonUpdateActionPerformed(evt);
            }
        });
        jPanelCreateNewEmployeeBox.add(jButtonUpdate);
        jButtonUpdate.setBounds(680, 570, 170, 40);

        jButtonDelete.setBackground(new java.awt.Color(158, 0, 30));
        jButtonDelete.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButtonDelete.setForeground(new java.awt.Color(255, 255, 255));
        jButtonDelete.setText("Delete");
        jButtonDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDeleteActionPerformed(evt);
            }
        });
        jPanelCreateNewEmployeeBox.add(jButtonDelete);
        jButtonDelete.setBounds(870, 570, 170, 40);

        jLabelSupervisor.setBackground(new java.awt.Color(255, 255, 255));
        jLabelSupervisor.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelSupervisor.setForeground(new java.awt.Color(0, 0, 0));
        jLabelSupervisor.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelSupervisor.setText("Supervisor   :");
        jPanelCreateNewEmployeeBox.add(jLabelSupervisor);
        jLabelSupervisor.setBounds(560, 100, 270, 40);

        jPanel1.add(jPanelCreateNewEmployeeBox);
        jPanelCreateNewEmployeeBox.setBounds(30, 110, 1060, 640);

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonUpdateActionPerformed
        // TODO add your handling code here:
        updateEmployee();
    }//GEN-LAST:event_jButtonUpdateActionPerformed

    private void jButtonDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDeleteActionPerformed
        // TODO add your handling code here:
        deleteEmployee();
    }//GEN-LAST:event_jButtonDeleteActionPerformed

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

    private void jTextFieldFirstNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldFirstNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldFirstNameActionPerformed

    private void jTextFieldLastNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldLastNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldLastNameActionPerformed

    private void jTextFieldDepartmentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldDepartmentActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldDepartmentActionPerformed

    private void jComboBoxSupervisorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxSupervisorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxSupervisorActionPerformed

    private void jTextFieldBasicSalaryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldBasicSalaryActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldBasicSalaryActionPerformed

    private void jComboBoxGenderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxGenderActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxGenderActionPerformed

    private void jLabelBackMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelBackMouseClicked
        // TODO add your handling code here:
        CardLayout cardLayout = (CardLayout) hrPortal.getPanelParentCard().getLayout();
        cardLayout.show(hrPortal.getPanelParentCard(), "Employees");
    }//GEN-LAST:event_jLabelBackMouseClicked

    private void jTextFieldStreetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldStreetActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldStreetActionPerformed

    private void jTextFieldBarangayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldBarangayActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldBarangayActionPerformed

    private void jTextFieldCityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldCityActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldCityActionPerformed

    private void jTextFieldProvinceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldProvinceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldProvinceActionPerformed

    private void jTextFieldZipCodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldZipCodeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldZipCodeActionPerformed

    private void jComboBoxWorkStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxWorkStatusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxWorkStatusActionPerformed

    private void jComboBoxPositionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxPositionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxPositionActionPerformed

    private void jComboBoxRoleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxRoleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxRoleActionPerformed

    private void jTextFieldEmployeeIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldEmployeeIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldEmployeeIDActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonDelete;
    private javax.swing.JButton jButtonUpdate;
    private javax.swing.JComboBox<String> jComboBoxGender;
    private javax.swing.JComboBox<String> jComboBoxPosition;
    private javax.swing.JComboBox<String> jComboBoxRole;
    private javax.swing.JComboBox<String> jComboBoxSupervisor;
    private javax.swing.JComboBox<String> jComboBoxWorkStatus;
    private com.toedter.calendar.JDateChooser jDateChooserBirthday;
    private javax.swing.JLabel jLabelAsterisk10;
    private javax.swing.JLabel jLabelAsterisk11;
    private javax.swing.JLabel jLabelAsterisk12;
    private javax.swing.JLabel jLabelAsterisk13;
    private javax.swing.JLabel jLabelAsterisk14;
    private javax.swing.JLabel jLabelAsterisk16;
    private javax.swing.JLabel jLabelAsterisk17;
    private javax.swing.JLabel jLabelAsterisk18;
    private javax.swing.JLabel jLabelAsterisk19;
    private javax.swing.JLabel jLabelAsterisk20;
    private javax.swing.JLabel jLabelAsterisk5;
    private javax.swing.JLabel jLabelAsterisk6;
    private javax.swing.JLabel jLabelAsterisk7;
    private javax.swing.JLabel jLabelAsterisk8;
    private javax.swing.JLabel jLabelAsterisk9;
    private javax.swing.JLabel jLabelBack;
    private javax.swing.JLabel jLabelBarangay;
    private javax.swing.JLabel jLabelBasicSalary;
    private javax.swing.JLabel jLabelCity;
    private javax.swing.JLabel jLabelClothingAllowance;
    private javax.swing.JLabel jLabelDepartment;
    private javax.swing.JLabel jLabelEmployeeDateOfBirth;
    private javax.swing.JLabel jLabelEmployeeNumber;
    private javax.swing.JLabel jLabelFirstName;
    private javax.swing.JLabel jLabelGender;
    private javax.swing.JLabel jLabelHelloAdmin;
    private javax.swing.JLabel jLabelHourlyRate;
    private javax.swing.JLabel jLabelLastName;
    private javax.swing.JLabel jLabelPagIbigNumber;
    private javax.swing.JLabel jLabelPhilHealthNumber;
    private javax.swing.JLabel jLabelPhoneAllowance;
    private javax.swing.JLabel jLabelPhoneNumber;
    private javax.swing.JLabel jLabelPosition;
    private javax.swing.JLabel jLabelProvince;
    private javax.swing.JLabel jLabelRiceSubsidy;
    private javax.swing.JLabel jLabelRole;
    private javax.swing.JLabel jLabelSemiMonthlyRate;
    private javax.swing.JLabel jLabelSssNumber;
    private javax.swing.JLabel jLabelSssNumber1;
    private javax.swing.JLabel jLabelStreet;
    private javax.swing.JLabel jLabelSupervisor;
    private javax.swing.JLabel jLabelTin;
    private javax.swing.JLabel jLabelViewRecordSmall;
    private javax.swing.JLabel jLabelWorkStatus;
    private javax.swing.JLabel jLabelZipCode;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanelCreateNewEmployeeBox;
    private javax.swing.JTextField jTextFieldBarangay;
    private javax.swing.JTextField jTextFieldBasicSalary;
    private javax.swing.JTextField jTextFieldCity;
    private javax.swing.JTextField jTextFieldClothingAllowance;
    private javax.swing.JTextField jTextFieldDepartment;
    private javax.swing.JTextField jTextFieldEmployeeID;
    private javax.swing.JTextField jTextFieldFirstName;
    private javax.swing.JTextField jTextFieldHourlyRate;
    private javax.swing.JTextField jTextFieldLastName;
    private javax.swing.JTextField jTextFieldPagIbigNumber;
    private javax.swing.JTextField jTextFieldPhilHealthNumber;
    private javax.swing.JTextField jTextFieldPhoneAllowance;
    private javax.swing.JTextField jTextFieldPhoneNumber;
    private javax.swing.JTextField jTextFieldProvince;
    private javax.swing.JTextField jTextFieldRiceSubsidy;
    private javax.swing.JTextField jTextFieldSemiMonthlyRate;
    private javax.swing.JTextField jTextFieldSssNumber;
    private javax.swing.JTextField jTextFieldStreet;
    private javax.swing.JTextField jTextFieldTin;
    private javax.swing.JTextField jTextFieldZipCode;
    // End of variables declaration//GEN-END:variables
}