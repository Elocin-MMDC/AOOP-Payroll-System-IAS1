package model.pojo;

public class Gender {
    private int genderID;
    private String genderName;

    public Gender(int genderID, String genderName) {
        this.genderID = genderID;
        this.genderName = genderName;
    }

    public Gender() {
    }

    public int getGenderID() {
        return genderID;
    }

    public String getGenderName() {
        return genderName;
    }

    public void setGenderID(int genderID) {
        this.genderID = genderID;
    }

    public void setGenderName(String genderName) {
        this.genderName = genderName;
    }

}
