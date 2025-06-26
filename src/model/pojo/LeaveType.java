package model.pojo;

public class LeaveType {
    private int leaveTypeID;
    private String leaveName;
    private int entitlementDays;

    public LeaveType(int leaveTypeID, String leaveName, int entitlementDays) {
        this.leaveTypeID = leaveTypeID;
        this.leaveName = leaveName;
        this.entitlementDays = entitlementDays;
    }

    public LeaveType() {
        
    }

    public int getLeaveTypeID() {
        return leaveTypeID;
    }

    public String getLeaveName() {
        return leaveName;
    }

    public int getEntitlementDays() {
        return entitlementDays;
    }

    public void setLeaveTypeID(int leaveTypeID) {
        this.leaveTypeID = leaveTypeID;
    }

    public void setLeaveName(String leaveName) {
        this.leaveName = leaveName;
    }

    public void setEntitlementDays(int entitlementDays) {
        this.entitlementDays = entitlementDays;
    }
    
}
