package model.pojo;

import java.time.LocalDateTime;

public class UserAccount {
    private int userID;
    private int employeeID;
    private String username;
    private String email;
    private String password;
    private int roleID;
    private String accountStatus;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime reactivatedAt;
    private LocalDateTime deactivatedAt;
    private Boolean mustChangePassword;
    private String passwordSalt;

    public UserAccount(int userID, int employeeID, String username, String email, String password, String passwordSalt, int roleID, String accountStatus,
            LocalDateTime createdAt, LocalDateTime updatedAt, LocalDateTime reactivatedAt,
            LocalDateTime deactivatedAt) {
        this.userID = userID;
        this.employeeID = employeeID;
        this.username = username;
        this.email = email;
        this.password = password;
        this.passwordSalt = passwordSalt;
        this.roleID = roleID;
        this.accountStatus = accountStatus;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.reactivatedAt = reactivatedAt;
        this.deactivatedAt = deactivatedAt;
    }

    public UserAccount() {

    }

    public int getUserID() {
        return userID;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public int getRoleID() {
        return roleID;
    }

    public String getAccountStatus() {
        return accountStatus;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public LocalDateTime getReactivatedAt() {
        return reactivatedAt;
    }

    public LocalDateTime getDeactivatedAt() {
        return deactivatedAt;
    }

    public Boolean getMustChangePassword() {
        return mustChangePassword;
    }

    public String getPasswordSalt() {
        return passwordSalt;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRoleID(int roleID) {
        this.roleID = roleID;
    }

    public void setAccountStatus(String accountStatus) {
        this.accountStatus = accountStatus;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setReactivatedAt(LocalDateTime reactivatedAt) {
        this.reactivatedAt = reactivatedAt;
    }

    public void setDeactivatedAt(LocalDateTime deactivatedAt) {
        this.deactivatedAt = deactivatedAt;
    }

    public void setMustChangePassword(Boolean mustChangePassword) {
        this.mustChangePassword = mustChangePassword;
    }

    public void setPasswordSalt(String passwordSalt) {
        this.passwordSalt = passwordSalt;
    }

    @Override
    public String toString() {
        return "UserAccount{" + "userID=" + userID + '}';
    }

}
