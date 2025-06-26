package service;

import model.dao.DepartmentDAO;
import model.dao.EmployeeDAO;
import model.dao.EmployeeViewDAO;
import model.dao.LeaveRequestDAO;
import model.dao.OvertimeRequestDAO;
import model.dao.SupportRequestDAO;
import model.dao.UserAccountDAO;
import model.pojo.EmployeeView;
import util.AccessControlUtil;
import util.Session;

public class DashboardService {

    private final EmployeeViewDAO efdDao = new EmployeeViewDAO();
    private final EmployeeDAO employeeDao = new EmployeeDAO();
    private final DepartmentDAO departmentDao = new DepartmentDAO();
    private final LeaveRequestDAO leaveDao = new LeaveRequestDAO();
    private final OvertimeRequestDAO overtimeDao = new OvertimeRequestDAO();
    private final SupportRequestDAO supportDao = new SupportRequestDAO();
    private final UserAccountDAO userDao = new UserAccountDAO();
    
    public EmployeeView getProfile() {
        int empId = Session.getCurrentUser().getEmployeeID();
        return efdDao.getById(empId);
    }
    
    public long getTotalPendingLeavesByEmployeeId() {
        int empId = Session.getCurrentUser().getEmployeeID();
        return leaveDao.countPendingByEmployeeId(empId);
    }

    public long getTotalPendingOvertimeByEmployeeId() {
        int empId = Session.getCurrentUser().getEmployeeID();
        return overtimeDao.countPendingByEmployeeId(empId);
    }

    public long getTotalPendingTicketsByEmployeeId() {
        int empId = Session.getCurrentUser().getEmployeeID();
        return supportDao.countPendingByEmployeeId(empId);
    }

    public long getTotalDepartments() {
        return departmentDao.countAll();
    }
    
    public long getTotalEmployees() {
        AccessControlUtil.requireRole("HR Admin", "Finance Admin", "IT Admin");
        return employeeDao.countAll();
    }

    public long getTotalPendingLeaves() {
        AccessControlUtil.requireRole("HR Admin");
        return leaveDao.countPending();
    }

    public long getTotalPendingOvertime() {
        AccessControlUtil.requireRole("HR Admin");
        return overtimeDao.countPending();
    }

    public long getTotalPendingTicketsForTeam(String team) {
        AccessControlUtil.requireRole("HR Admin", "Finance Admin", "IT Admin");
        return supportDao.countPendingByTeam(team);
    }
    
    public String getCurrentUserPassword() {
        int userId = Session.getCurrentUser().getUserID();
        return userDao.getById(userId).getPassword();
    }
    
}
