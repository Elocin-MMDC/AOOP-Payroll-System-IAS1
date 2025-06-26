package model.pojo;

public class AllowanceType {
    private int allowanceTypeID;
    private String allowanceName;

    public AllowanceType(int allowanceTypeID, String allowanceName) {
        this.allowanceTypeID = allowanceTypeID;
        this.allowanceName = allowanceName;
    }

    public AllowanceType() {
        
    }

    public int getAllowanceTypeID() {
        return allowanceTypeID;
    }

    public String getAllowanceName() {
        return allowanceName;
    }

    public void setAllowanceTypeID(int allowanceTypeID) {
        this.allowanceTypeID = allowanceTypeID;
    }

    public void setAllowanceName(String allowanceName) {
        this.allowanceName = allowanceName;
    }
    
}
