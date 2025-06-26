package util;

import model.pojo.UserAccount;

public class Session {

    private static UserAccount currentUser;

    public static void setCurrentUser(UserAccount user) {
        currentUser = user;
    }

    public static UserAccount getCurrentUser() {
        return currentUser;
    }

    public static void clear() {
        currentUser = null;
    }
}