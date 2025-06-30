package model.pojo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AuditLog {
    private int auditID;
    private int userID;
    private LocalDateTime createdAt;
    private String action;
    private String entityModified;
    private int entityID;
    private String attributeModified;
    private String oldValue;
    private String newValue;

    public AuditLog(int auditID, int userID, LocalDateTime createdAt, String action, String entityModified, int entityID, String attributeModified, String oldValue, String newValue) {
        this.auditID = auditID;
        this.userID = userID;
        this.createdAt = createdAt;
        this.action = action;
        this.entityModified = entityModified;
        this.entityID = entityID;
        this.attributeModified = attributeModified;
        this.oldValue = oldValue;
        this.newValue = newValue;
    }

    public AuditLog() {
        
    }

    public int getAuditID() {
        return auditID;
    }

    public int getUserID() {
        return userID;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public String getAction() {
        return action;
    }

    public String getEntityModified() {
        return entityModified;
    }

    public int getEntityID() {
        return entityID;
    }

    public String getAttributeModified() {
        return attributeModified;
    }

    public String getOldValue() {
        return oldValue;
    }

    public String getNewValue() {
        return newValue;
    }

    public void setAuditID(int auditID) {
        this.auditID = auditID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public void setEntityModified(String entityModified) {
        this.entityModified = entityModified;
    }

    public void setEntityID(int entityID) {
        this.entityID = entityID;
    }

    public void setAttributeModified(String attributeModified) {
        this.attributeModified = attributeModified;
    }

    public void setOldValue(String oldValue) {
        this.oldValue = oldValue;
    }

    public void setNewValue(String newValue) {
        this.newValue = newValue;
    }

    @Override
    public String toString() {
        return "AuditLog{" + "auditID=" + auditID + ", userID=" + userID + ", createdAt=" + createdAt.format(DateTimeFormatter.ISO_DATE) + ", action=" + action + ", entityModified=" + entityModified + ", entityID=" + entityID + ", attributeModified=" + attributeModified + ", oldValue=" + oldValue + ", newValue=" + newValue + '}';
    }

}
