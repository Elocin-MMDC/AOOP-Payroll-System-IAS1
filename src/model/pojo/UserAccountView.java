package model.pojo;

public class UserAccountView {

    private int userID;
    private int employeeID;
    private String fullName;
    private String username;
    private String roleName;
    private String accountStatus;

    public UserAccountView(int userID, int employeeID, String fullName, String username, String roleName, String accountStatus) {
        this.userID = userID;
        this.employeeID = employeeID;
        this.fullName = fullName;
        this.username = username;
        this.roleName = roleName;
        this.accountStatus = accountStatus;
    }
    
    public UserAccountView () {
        
    }

    public int getUserID() {
        return userID;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public String getFullName() {
        return fullName;
    }

    public String getUsername() {
        return username;
    }

    public String getRoleName() {
        return roleName;
    }

    public String getAccountStatus() {
        return accountStatus;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public void setAccountStatus(String accountStatus) {
        this.accountStatus = accountStatus;
    }
    
}
