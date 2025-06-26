package model.pojo;

import java.math.BigDecimal;
import java.time.LocalDate;

public class EmployeeView {
    private int employeeID;
    private String lastName;
    private String firstName;
    private LocalDate birthday;
    private String phoneNumber;
    private String gender;
    private String workStatus;
    private String departmentName;
    private String positionTitle;
    private String fullAddress;
    private String sssNumber;
    private String philHealthNumber;
    private String tin;
    private String pagIbigNumber;
    private BigDecimal basicSalary;
    private BigDecimal semiMonthlyRate;
    private BigDecimal hourlyRate;
    private String supervisorName;
    private String role;
    private BigDecimal riceSubsidy;
    private BigDecimal phoneAllowance;
    private BigDecimal clothingAllowance;
    private boolean isDeleted;

    public EmployeeView(int employeeID, String lastName, String firstName, LocalDate birthday, String phoneNumber, String gender, String workStatus, String departmentName, String positionTitle, String fullAddress, String sssNumber, String philHealthNumber, String tin, String pagIbigNumber, BigDecimal basicSalary, BigDecimal semiMonthlyRate, BigDecimal hourlyRate, String supervisorName, String role, BigDecimal riceSubsidy, BigDecimal phoneAllowance, BigDecimal clothingAllowance, boolean isDeleted) {
        this.employeeID = employeeID;
        this.lastName = lastName;
        this.firstName = firstName;
        this.birthday = birthday;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.workStatus = workStatus;
        this.departmentName = departmentName;
        this.positionTitle = positionTitle;
        this.fullAddress = fullAddress;
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
    }

    public EmployeeView() {

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

    public String getFullAddress() {
        return fullAddress;
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

    public void setFullAddress(String fullAddress) {
        this.fullAddress = fullAddress;
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

    public String getFullName() {
        return getFirstName() + " " + getLastName();
    }
    
    public String getFullNameWithSurnameFirst() {
        return getLastName() + ", " + getFirstName();
    }
    
}