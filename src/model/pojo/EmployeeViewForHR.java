package model.pojo;

import java.math.BigDecimal;
import java.time.LocalDate;

public class EmployeeViewForHR {
    private int employeeID;
    private String lastName;
    private String firstName;
    private LocalDate birthday;
    private String phoneNumber;
    private String gender;
    private String workStatus;
    private String departmentName;
    private String positionTitle;

    // Address fields
    private String street;
    private String barangay;
    private String city;
    private String province;
    private String zipCode;

    // Government IDs
    private String sssNumber;
    private String philHealthNumber;
    private String tin;
    private String pagIbigNumber;

    // Salary
    private BigDecimal basicSalary;
    private BigDecimal semiMonthlyRate;
    private BigDecimal hourlyRate;

    // Supervisor
    private String supervisorName;

    // User Role
    private String role;

    // Allowances
    private BigDecimal riceSubsidy;
    private BigDecimal phoneAllowance;
    private BigDecimal clothingAllowance;

    private boolean isDeleted;

    public EmployeeViewForHR(int employeeID, String lastName, String firstName, LocalDate birthday, String phoneNumber, String gender, String workStatus, String departmentName, String positionTitle, String street, String barangay, String city, String province, String zipCode, String sssNumber, String philHealthNumber, String tin, String pagIbigNumber, BigDecimal basicSalary, BigDecimal semiMonthlyRate, BigDecimal hourlyRate, String supervisorName, String role, BigDecimal riceSubsidy, BigDecimal phoneAllowance, BigDecimal clothingAllowance, boolean isDeleted) {
        this.employeeID = employeeID;
        this.lastName = lastName;
        this.firstName = firstName;
        this.birthday = birthday;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.workStatus = workStatus;
        this.departmentName = departmentName;
        this.positionTitle = positionTitle;
        this.street = street;
        this.barangay = barangay;
        this.city = city;
        this.province = province;
        this.zipCode = zipCode;
        this.sssNumber = sssNumber;
        this.philHealthNumber = philHealthNumber;
        this.tin = tin;
        this.pagIbigNumber = pagIbigNumber;
        this.basicSalary = basicSalary;
        this.semiMonthlyRate = semiMonthlyRate;
        this.hourlyRate = hourlyRate;
        this.supervisorName = supervisorName;
        this.role = role;
        this.riceSubsidy = riceSubsidy;
        this.phoneAllowance = phoneAllowance;
        this.clothingAllowance = clothingAllowance;
        this.isDeleted = isDeleted;
    }
    
    public EmployeeViewForHR() {
        
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getGender() {
        return gender;
    }

    public String getWorkStatus() {
        return workStatus;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public String getPositionTitle() {
        return positionTitle;
    }

    public String getStreet() {
        return street;
    }

    public String getBarangay() {
        return barangay;
    }

    public String getCity() {
        return city;
    }

    public String getProvince() {
        return province;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getSssNumber() {
        return sssNumber;
    }

    public String getPhilHealthNumber() {
        return philHealthNumber;
    }

    public String getTin() {
        return tin;
    }

    public String getPagIbigNumber() {
        return pagIbigNumber;
    }

    public BigDecimal getBasicSalary() {
        return basicSalary;
    }

    public BigDecimal getSemiMonthlyRate() {
        return semiMonthlyRate;
    }

    public BigDecimal getHourlyRate() {
        return hourlyRate;
    }

    public String getSupervisorName() {
        return supervisorName;
    }

    public String getRole() {
        return role;
    }

    public BigDecimal getRiceSubsidy() {
        return riceSubsidy;
    }

    public BigDecimal getPhoneAllowance() {
        return phoneAllowance;
    }

    public BigDecimal getClothingAllowance() {
        return clothingAllowance;
    }

    public boolean isIsDeleted() {
        return isDeleted;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setWorkStatus(String workStatus) {
        this.workStatus = workStatus;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public void setPositionTitle(String positionTitle) {
        this.positionTitle = positionTitle;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setBarangay(String barangay) {
        this.barangay = barangay;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public void setSssNumber(String sssNumber) {
        this.sssNumber = sssNumber;
    }

    public void setPhilHealthNumber(String philHealthNumber) {
        this.philHealthNumber = philHealthNumber;
    }

    public void setTin(String tin) {
        this.tin = tin;
    }

    public void setPagIbigNumber(String pagIbigNumber) {
        this.pagIbigNumber = pagIbigNumber;
    }

    public void setBasicSalary(BigDecimal basicSalary) {
        this.basicSalary = basicSalary;
    }

    public void setSemiMonthlyRate(BigDecimal semiMonthlyRate) {
        this.semiMonthlyRate = semiMonthlyRate;
    }

    public void setHourlyRate(BigDecimal hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public void setSupervisorName(String supervisorName) {
        this.supervisorName = supervisorName;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setRiceSubsidy(BigDecimal riceSubsidy) {
        this.riceSubsidy = riceSubsidy;
    }

    public void setPhoneAllowance(BigDecimal phoneAllowance) {
        this.phoneAllowance = phoneAllowance;
    }

    public void setClothingAllowance(BigDecimal clothingAllowance) {
        this.clothingAllowance = clothingAllowance;
    }

    public void setIsDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

}