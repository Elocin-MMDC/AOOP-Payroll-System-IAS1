package model.pojo;


public class Positions {
    private int positionID;
    private String positionTitle;
    private int departmentID;

    public Positions(int positionID, String positionTitle, int departmentID) {
        this.positionID = positionID;
        this.positionTitle = positionTitle;
        this.departmentID = departmentID;
    }

    public int getPositionID() {
        return positionID;
    }

    public String getPositionTitle() {
        return positionTitle;
    }

    public int getDepartmentID() {
        return departmentID;
    }

    public void setPositionID(int positionID) {
        this.positionID = positionID;
    }

    public void setPositionTitle(String positionTitle) {
        this.positionTitle = positionTitle;
    }

    public void setDepartmentID(int departmentID) {
        this.departmentID = departmentID;
    }
    
}
