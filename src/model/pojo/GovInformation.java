package model.pojo;

import java.time.LocalDateTime;

public class GovInformation {
    private int govID;
    private String sssNumber;
    private String philHealthNumber;
    private String tin;
    private String pagIbigNumber;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public GovInformation(int govID, String sssNumber, String philHealthNumber, String tin, String pagIbigNumber, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.govID = govID;
        this.sssNumber = sssNumber;
        this.philHealthNumber = philHealthNumber;
        this.tin = tin;
        this.pagIbigNumber = pagIbigNumber;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public GovInformation() {
        
    }

    public int getGovID() {
        return govID;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setGovID(int govID) {
        this.govID = govID;
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

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
    
}
