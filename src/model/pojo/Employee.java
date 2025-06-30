package model.pojo;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Employee {
    private int employeeID;
    private String lastName;
    private String firstName;
    private LocalDate birthday;
    private String phoneNumber;
    private int genderID;
    private int addressID;
    private int workStatusID;
    private int positionID;
    private int govID;
    private int salaryID;
    private Integer supervisorID;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private boolean isDeleted;
    private LocalDateTime deletedAt;
    
    public Employee(int employeeID, String lastName, String firstName, LocalDate birthday, String phoneNumber, int genderID, int addressID, int workStatusID, int positionID, int govID, int salaryID, Integer supervisorID, LocalDateTime createdAt, LocalDateTime updatedAt, boolean isDeleted, LocalDateTime deletedAt) {
        this.employeeID = employeeID;
        this.lastName = lastName;
        this.firstName = firstName;
        this.birthday = birthday;
        this.phoneNumber = phoneNumber;
        this.genderID = genderID;
        this.addressID = addressID;
        this.workStatusID = workStatusID;
        this.positionID = positionID;
        this.govID = govID;
        this.salaryID = salaryID;
        this.supervisorID = supervisorID;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.isDeleted = isDeleted;
        this.deletedAt = deletedAt;
    }
    
    public Employee() {
        
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

    public int getGenderID() {
        return genderID;
    }

    public int getAddressID() {
        return addressID;
    }

    public int getWorkStatusID() {
        return workStatusID;
    }

    public int getPositionID() {
        return positionID;
    }

    public int getGovID() {
        return govID;
    }

    public int getSalaryID() {
        return salaryID;
    }

    public Integer getSupervisorID() {
        return supervisorID;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public boolean isIsDeleted() {
        return isDeleted;
    }

    public LocalDateTime getDeletedAt() {
        return deletedAt;
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

    public void setGenderID(int genderID) {
        this.genderID = genderID;
    }

    public void setAddressID(int addressID) {
        this.addressID = addressID;
    }

    public void setWorkStatusID(int workStatusID) {
        this.workStatusID = workStatusID;
    }

    public void setPositionID(int positionID) {
        this.positionID = positionID;
    }

    public void setGovID(int govID) {
        this.govID = govID;
    }

    public void setSalaryID(int salaryID) {
        this.salaryID = salaryID;
    }

    public void setSupervisorID(Integer supervisorID) {
        this.supervisorID = supervisorID;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setIsDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public void setDeletedAt(LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
    }

    @Override
    public String toString() {
        return "Employee{" + "employeeID=" + employeeID + ", lastName=" + lastName + ", firstName=" + firstName + ", birthday=" + birthday + ", phoneNumber=" + phoneNumber + ", genderID=" + genderID + ", addressID=" + addressID + ", workStatusID=" + workStatusID + ", positionID=" + positionID + ", govID=" + govID + ", salaryID=" + salaryID + ", supervisorID=" + supervisorID + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", isDeleted=" + isDeleted + ", deletedAt=" + deletedAt + '}';
    }
    
}
