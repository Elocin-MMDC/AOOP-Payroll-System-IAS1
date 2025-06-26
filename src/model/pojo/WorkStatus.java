package model.pojo;

public class WorkStatus {
    private int workStatusID;
    private String workStatusName;
    private String description;

    public WorkStatus(int workStatusID, String workStatusName, String description) {
        this.workStatusID = workStatusID;
        this.workStatusName = workStatusName;
        this.description = description;
    }
    
    public WorkStatus() {

    }

    public int getWorkStatusID() {
        return workStatusID;
    }

    public String getWorkStatusName() {
        return workStatusName;
    }

    public String getDescription() {
        return description;
    }

    public void setWorkStatusID(int workStatusID) {
        this.workStatusID = workStatusID;
    }

    public void setWorkStatusName(String workStatusName) {
        this.workStatusName = workStatusName;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
}
