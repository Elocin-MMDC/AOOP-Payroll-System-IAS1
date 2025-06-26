package model.pojo;

public class PositionAllowance {
    private int positionAllowanceID;
    private int positionID;
    private int allowanceID;

    public PositionAllowance(int positionAllowanceID, int positionID, int allowanceID) {
        this.positionAllowanceID = positionAllowanceID;
        this.positionID = positionID;
        this.allowanceID = allowanceID;
    }

    public PositionAllowance() {

    }

    public int getPositionAllowanceID() {
        return positionAllowanceID;
    }

    public int getPositionID() {
        return positionID;
    }

    public int getAllowanceID() {
        return allowanceID;
    }

    public void setPositionAllowanceID(int positionAllowanceID) {
        this.positionAllowanceID = positionAllowanceID;
    }

    public void setPositionID(int positionID) {
        this.positionID = positionID;
    }

    public void setAllowanceID(int allowanceID) {
        this.allowanceID = allowanceID;
    }
    
}