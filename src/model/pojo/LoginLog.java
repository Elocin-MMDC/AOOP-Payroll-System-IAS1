package model.pojo;

import java.time.LocalDateTime;


public class LoginLog {
    private int loginID;
    private int userID;
    private LocalDateTime createdAt;
    private String status;          // Could use enum for SUCCESS/FAILED
    private int loginAttempt;
    private boolean isLocked;
    private LocalDateTime lockEndTime;  // Nullable

    public LoginLog(int loginID, int userID, LocalDateTime createdAt, String status, int loginAttempt, boolean isLocked, LocalDateTime lockEndTime) {
        this.loginID = loginID;
        this.userID = userID;
        this.createdAt = createdAt;
        this.status = status;
        this.loginAttempt = loginAttempt;
        this.isLocked = isLocked;
        this.lockEndTime = lockEndTime;
    }

    public int getLoginID() {
        return loginID;
    }

    public int getUserID() {
        return userID;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public String getStatus() {
        return status;
    }

    public int getLoginAttempt() {
        return loginAttempt;
    }

    public boolean isIsLocked() {
        return isLocked;
    }

    public LocalDateTime getLockEndTime() {
        return lockEndTime;
    }

    public void setLoginID(int loginID) {
        this.loginID = loginID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setLoginAttempt(int loginAttempt) {
        this.loginAttempt = loginAttempt;
    }

    public void setIsLocked(boolean isLocked) {
        this.isLocked = isLocked;
    }

    public void setLockEndTime(LocalDateTime lockEndTime) {
        this.lockEndTime = lockEndTime;
    }
    
}
