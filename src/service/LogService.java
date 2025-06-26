package service;

import model.dao.AuditLogDAO;
import model.dao.LoginLogDAO;
import model.pojo.AuditLog;
import model.pojo.LoginLog;
import java.util.List;
import util.AccessControlUtil;

public class LogService {

    private final AuditLogDAO auditLogDao = new AuditLogDAO();
    private final LoginLogDAO loginLogDao = new LoginLogDAO();

    public List<AuditLog> getAllAuditLogs() {
        AccessControlUtil.requireRole("IT Admin");
        return auditLogDao.getAll();
    }

    public List<LoginLog> getAllLoginLogs() {
        AccessControlUtil.requireRole("IT Admin");
        return loginLogDao.getAll();
    }
   
}
