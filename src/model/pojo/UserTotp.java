package model.pojo;

import java.time.LocalDateTime;

public class UserTotp {
    private int userID;
    private String encryptedSecret;
    private LocalDateTime enrolledAt;
    private Long lastUsedTimeStep;
    private LocalDateTime createdAt;

    public UserTotp() {

    }

    public int getUserID() {
        return userID;
    }

    public String getEncryptedSecret() {
        return encryptedSecret;
    }

    public LocalDateTime getEnrolledAt() {
        return enrolledAt;
    }

    public Long getLastUsedTimeStep() {
        return lastUsedTimeStep;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public boolean isEnrolled() {
        return enrolledAt != null;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setEncryptedSecret(String encryptedSecret) {
        this.encryptedSecret = encryptedSecret;
    }

    public void setEnrolledAt(LocalDateTime enrolledAt) {
        this.enrolledAt = enrolledAt;
    }

    public void setLastUsedTimeStep(Long lastUsedTimeStep) {
        this.lastUsedTimeStep = lastUsedTimeStep;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        // Never include the secret
        return "UserTotp{" + "userID=" + userID + ", enrolled=" + isEnrolled() + '}';
    }
}
