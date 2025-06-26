package util;

import model.dao.UserAccountViewDAO;
import model.pojo.UserAccountView;

public class AccessControlUtil {
    
    private final static UserAccountViewDAO uaDao = new UserAccountViewDAO();
    
    // Ensures that the current user has one of the allowed roles
    public static void requireRole(String... allowedRoles) {
        if (allowedRoles == null || allowedRoles.length == 0) {
            throw new IllegalArgumentException("No roles specified for access control.");
        }

        int userID = Session.getCurrentUser().getUserID();
        UserAccountView user = uaDao.getById(userID);

        if (user == null) {
            throw new SecurityException("Access denied: user not found.");
        }

        String userRole = user.getRoleName();
        for (String role : allowedRoles) {
            if (role != null && userRole.equalsIgnoreCase(role.trim())) {
                return;
            }
        }

        throw new SecurityException("Access denied: role '" + userRole + "' is not permitted.");
    }
    
}
