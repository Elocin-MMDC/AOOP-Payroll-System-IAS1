package main;

import gui.admin.finance.AdminFinancePortal;
import gui.admin.hr.AdminHRPortal;
import gui.admin.it.AdminITPortal;
import gui.employee.EmployeePortal;
import gui.login.LoginPortal;

public class Main {

    public static void main(String[] args) {
        //hrPortal();
        //employeePortal();
        //financePortal();
        //itPortal();
        loginPortal();
    }
    
    public static void hrPortal() {
        AdminHRPortal hrPortal = new AdminHRPortal();
        hrPortal.setVisible(true);
    }
    
    public static void employeePortal() {
        EmployeePortal employeePortal = new EmployeePortal();
        employeePortal.setVisible(true);
    }
    
    public static void financePortal() {
        AdminFinancePortal financePortal = new AdminFinancePortal();
        financePortal.setVisible(true);
    }
    
    public static void itPortal() {
        AdminITPortal itPortal = new AdminITPortal();
        itPortal.setVisible(true);
    }
    
    public static void loginPortal() {
        LoginPortal loginPortal = new LoginPortal();
        loginPortal.setVisible(true);
    }
    
}
