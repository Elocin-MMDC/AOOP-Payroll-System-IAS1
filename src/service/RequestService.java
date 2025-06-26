package service;

import model.dao.*;
import model.pojo.*;
import util.Session;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import util.AccessControlUtil;

public class RequestService {

    private final LeaveTypeDAO typeDao = new LeaveTypeDAO();
    private final LeaveBalanceDAO balanceDao = new LeaveBalanceDAO();
    private final LeaveRequestDAO leaveDao = new LeaveRequestDAO();
    private final OvertimeRequestDAO overtimeDao = new OvertimeRequestDAO();
    private final SupportRequestDAO supportDao = new SupportRequestDAO();
    private final EmployeeViewDAO empDao = new EmployeeViewDAO();
    private final AuditLogDAO auditDao = new AuditLogDAO();

    // Leave
    public List<LeaveType> getLeaveTypes() {
        return typeDao.getAll();
    }

    public List<LeaveBalance> getLeaveBalances(int empId) {
        return balanceDao.getByEmployeeId(empId);
    }

    public List<LeaveRequest> getLeaveHistory(int empId) {
        return leaveDao.getByEmployeeID(empId);
    }

    public LeaveRequest getLeaveDetails(int leaveID) {
        return leaveDao.getById(leaveID);
    }

    public List<LeaveRequest> getAllLeaveRequests() {
        AccessControlUtil.requireRole("HR Admin");
        return leaveDao.getAll();
    }
    
    public void submitLeaveRequest(
            int empId,
            LocalDate start,
            LocalDate end,
            int leaveTypeId,
            String reason
    ) throws ValidationException {
        int days = (int) ChronoUnit.DAYS.between(start, end) + 1;

        LeaveRequest lr = new LeaveRequest();
        lr.setEmployeeID(empId);
        lr.setDate(LocalDate.now());
        lr.setStartDate(start);
        lr.setEndDate(end);
        lr.setLeaveDays(days);
        lr.setLeaveTypeID(leaveTypeId);
        lr.setReason(reason);
        lr.setStatus("Pending");
        lr.setCreatedAt(LocalDateTime.now());
        lr.setUpdatedAt(LocalDateTime.now());

        int newId = leaveDao.insertAndGetId(lr);
        if (newId < 0) {
            throw new ValidationException("Failed to submit leave request.");
        }

        // audit
        AuditLog log = new AuditLog();
        log.setUserID(Session.getCurrentUser().getUserID());
        log.setCreatedAt(LocalDateTime.now());
        log.setAction("INSERT");
        log.setEntityModified("LeaveRequest");
        log.setEntityID(newId);
        log.setAttributeModified("all");
        auditDao.insert(log);
    }
    
    public void approveLeaveRequest(int leaveID) throws ValidationException {
        AccessControlUtil.requireRole("HR Admin");
        
        LeaveRequest lr = leaveDao.getById(leaveID);
        if (lr == null) {
            throw new ValidationException("Leave request not found.");
        }
        if (!"Pending".equalsIgnoreCase(lr.getStatus())) {
            throw new ValidationException("Only pending leave requests can be approved.");
        }

        // Fetch and deduct leave balance
        List<LeaveBalance> balances = balanceDao.getByEmployeeId(lr.getEmployeeID());
        LeaveBalance matched = balances.stream()
                .filter(b -> b.getLeaveTypeID() == lr.getLeaveTypeID())
                .findFirst()
                .orElse(null);

        if (matched == null) {
            throw new ValidationException("Employee has no balance for this leave type.");
        }

        if (lr.getLeaveDays() > matched.getRemainingDays()) {
            throw new ValidationException("Insufficient leave balance.");
        }

        matched.setUsedDays(matched.getUsedDays() + lr.getLeaveDays());
        matched.setRemainingDays(matched.getRemainingDays() - lr.getLeaveDays());

        boolean balanceUpdated = balanceDao.update(matched);
        if (!balanceUpdated) {
            throw new ValidationException("Failed to update leave balance.");
        }

        // Approve the request
        lr.setStatus("Approved");
        lr.setApprovedBy(Session.getCurrentUser().getUserID());
        lr.setUpdatedAt(LocalDateTime.now());

        boolean ok = leaveDao.update(lr);
        if (!ok) {
            throw new ValidationException("Failed to approve leave request.");
        }

        // Audit log
        AuditLog log = new AuditLog();
        log.setUserID(Session.getCurrentUser().getUserID());
        log.setCreatedAt(LocalDateTime.now());
        log.setAction("UPDATE");
        log.setEntityModified("LeaveRequest");
        log.setEntityID(leaveID);
        log.setAttributeModified("status, leave balance");
        log.setOldValue("Pending");
        log.setNewValue("Approved, used=" + matched.getUsedDays() + ", remaining=" + matched.getRemainingDays());
        auditDao.insert(log);
    }
    
    public void rejectLeaveRequest(int leaveID) throws ValidationException {
        AccessControlUtil.requireRole("HR Admin");
        
        LeaveRequest lr = leaveDao.getById(leaveID);
        if (lr == null) {
            throw new ValidationException("Leave request not found.");
        }
        if (!"Pending".equalsIgnoreCase(lr.getStatus())) {
            throw new ValidationException("Only pending leave requests can be rejected.");
        }

        lr.setStatus("Rejected");
        lr.setApprovedBy(Session.getCurrentUser().getUserID());
        lr.setUpdatedAt(LocalDateTime.now());

        boolean ok = leaveDao.update(lr);
        if (!ok) {
            throw new ValidationException("Failed to reject leave request.");
        }

        // Log audit
        AuditLog log = new AuditLog();
        log.setUserID(Session.getCurrentUser().getUserID());
        log.setCreatedAt(LocalDateTime.now());
        log.setAction("UPDATE");
        log.setEntityModified("LeaveRequest");
        log.setEntityID(leaveID);
        log.setAttributeModified("status");
        log.setOldValue("Pending");
        log.setNewValue("Rejected");
        auditDao.insert(log);
    }
    

    // Overtime
    public List<OvertimeRequest> getOvertimeHistory(int empId) {
        return overtimeDao.getByEmployeeID(empId);
    }

    public OvertimeRequest getOvertimeDetails(int overtimeID) {
        return overtimeDao.getById(overtimeID);
    }
    
    public List<OvertimeRequest> getAllOvertimeRequests() {
        AccessControlUtil.requireRole("HR Admin");
        return overtimeDao.getAll();
    }

    public void submitOvertimeRequest(
            int empId,
            LocalDate workDate,
            double hours,
            String reason
    ) throws ValidationException {
        if (hours <= 0) {
            throw new ValidationException("Overtime hours must be positive.");
        }
        OvertimeRequest or = new OvertimeRequest();
        or.setEmployeeID(empId);
        or.setDate(LocalDate.now());
        or.setOvertimeWorkDate(workDate);
        or.setOvertimeHours(BigDecimal.valueOf(hours));
        or.setReason(reason);
        or.setStatus("Pending");
        or.setCreatedAt(LocalDateTime.now());
        or.setUpdatedAt(LocalDateTime.now());

        int newId = overtimeDao.insertAndGetId(or);
        if (newId < 0) {
            throw new ValidationException("Failed to submit overtime request.");
        }

        AuditLog log = new AuditLog();
        log.setUserID(Session.getCurrentUser().getUserID());
        log.setCreatedAt(LocalDateTime.now());
        log.setAction("INSERT");
        log.setEntityModified("OvertimeRequest");
        log.setEntityID(newId);
        log.setAttributeModified("all");
        auditDao.insert(log);
    }
    
    public void approveOvertimeRequest(int overtimeID) throws ValidationException {
        AccessControlUtil.requireRole("HR Admin");
        
        OvertimeRequest request = overtimeDao.getById(overtimeID);

        if (request == null) {
            throw new ValidationException("Overtime request not found.");
        }

        String currentStatus = request.getStatus();
        if (!"Pending".equalsIgnoreCase(currentStatus)) {
            throw new ValidationException("Only pending requests can be approved.");
        }

        request.setStatus("Approved");
        request.setApprovedBy(Session.getCurrentUser().getUserID());
        request.setUpdatedAt(LocalDateTime.now());

        boolean success = overtimeDao.update(request);
        if (!success) {
            throw new ValidationException("Failed to approve overtime request.");
        }

        // Audit
        AuditLog log = new AuditLog();
        log.setUserID(Session.getCurrentUser().getUserID());
        log.setCreatedAt(LocalDateTime.now());
        log.setAction("UPDATE");
        log.setEntityModified("OvertimeRequest");
        log.setEntityID(overtimeID);
        log.setAttributeModified("status");
        log.setOldValue(currentStatus);
        log.setNewValue("Approved");
        auditDao.insert(log);
    }
    
    public void rejectOvertimeRequest(int overtimeID) throws ValidationException {
        AccessControlUtil.requireRole("HR Admin");
        
        OvertimeRequest request = overtimeDao.getById(overtimeID);

        if (request == null) {
            throw new ValidationException("Overtime request not found.");
        }

        String currentStatus = request.getStatus();
        if (!"Pending".equalsIgnoreCase(currentStatus)) {
            throw new ValidationException("Only pending requests can be rejected.");
        }

        request.setStatus("Rejected");
        request.setApprovedBy(Session.getCurrentUser().getUserID());
        request.setUpdatedAt(LocalDateTime.now());

        boolean success = overtimeDao.update(request);
        if (!success) {
            throw new ValidationException("Failed to reject overtime request.");
        }

        // Audit
        AuditLog log = new AuditLog();
        log.setUserID(Session.getCurrentUser().getUserID());
        log.setCreatedAt(LocalDateTime.now());
        log.setAction("UPDATE");
        log.setEntityModified("OvertimeRequest");
        log.setEntityID(overtimeID);
        log.setAttributeModified("status");
        log.setOldValue(currentStatus);
        log.setNewValue("Rejected");
        auditDao.insert(log);
    }

    
    // Support
    public List<SupportRequest> getSupportHistory(int empId) {
        return supportDao.getByEmployeeID(empId);
    }

    public SupportRequest getSupportDetails(int ticketID) {
        return supportDao.getById(ticketID);
    }
    
    public List<SupportRequest> getSupportHistoryByTeam(String team) {
        AccessControlUtil.requireRole("HR Admin", "Finance Admin", "IT Admin");
        return supportDao.getByAssignedTeam(team);
    }

    public void submitSupportRequest(
            int empId,
            String team,
            String severity,
            String subject,
            String description
    ) throws ValidationException {
        SupportRequest sr = new SupportRequest();
        sr.setEmployeeID(empId);
        sr.setDate(LocalDate.now());
        sr.setAssignedTeam(team);
        sr.setSeverity(severity);
        sr.setSubject(subject);
        sr.setDescription(description);
        sr.setStatus("Pending");
        sr.setCreatedAt(LocalDateTime.now());
        sr.setUpdatedAt(LocalDateTime.now());

        int newId = supportDao.insertAndGetId(sr);
        if (newId < 0) {
            throw new ValidationException("Failed to submit support request.");
        }

        AuditLog log = new AuditLog();
        log.setUserID(Session.getCurrentUser().getUserID());
        log.setCreatedAt(LocalDateTime.now());
        log.setAction("INSERT");
        log.setEntityModified("SupportRequest");
        log.setEntityID(newId);
        log.setAttributeModified("all");
        auditDao.insert(log);
    }
    
    public void resolveSupportRequest(int ticketID) throws ValidationException {
        AccessControlUtil.requireRole("HR Admin", "Finance Admin", "IT Admin");
        
        SupportRequest request = supportDao.getById(ticketID);

        if (request == null) {
            throw new ValidationException("Support ticket not found.");
        }

        String currentStatus = request.getStatus();
        if (!"Pending".equalsIgnoreCase(currentStatus)) {
            throw new ValidationException("Only pending tickets can be resolved.");
        }

        request.setStatus("Resolved");
        request.setResolvedBy(Session.getCurrentUser().getUserID());
        request.setUpdatedAt(LocalDateTime.now());

        boolean success = supportDao.update(request);
        if (!success) {
            throw new ValidationException("Failed to resolve ticket.");
        }

        // Audit
        AuditLog log = new AuditLog();
        log.setUserID(Session.getCurrentUser().getUserID());
        log.setCreatedAt(LocalDateTime.now());
        log.setAction("UPDATE");
        log.setEntityModified("SupportRequest");
        log.setEntityID(ticketID);
        log.setAttributeModified("status");
        log.setOldValue(currentStatus);
        log.setNewValue("Resolved");
        auditDao.insert(log);
    }

    public void rejectSupportRequest(int ticketID) throws ValidationException {
        AccessControlUtil.requireRole("HR Admin", "Finance Admin", "IT Admin");
        
        SupportRequest request = supportDao.getById(ticketID);

        if (request == null) {
            throw new ValidationException("Support ticket not found.");
        }

        String currentStatus = request.getStatus();
        if (!"Pending".equalsIgnoreCase(currentStatus)) {
            throw new ValidationException("Only pending tickets can be rejected.");
        }

        request.setStatus("Rejected");
        request.setResolvedBy(Session.getCurrentUser().getUserID());
        request.setUpdatedAt(LocalDateTime.now());

        boolean success = supportDao.update(request);
        if (!success) {
            throw new ValidationException("Failed to reject ticket.");
        }

        // Audit
        AuditLog log = new AuditLog();
        log.setUserID(Session.getCurrentUser().getUserID());
        log.setCreatedAt(LocalDateTime.now());
        log.setAction("UPDATE");
        log.setEntityModified("SupportRequest");
        log.setEntityID(ticketID);
        log.setAttributeModified("status");
        log.setOldValue(currentStatus);
        log.setNewValue("Rejected");
        auditDao.insert(log);
    }
    
    public String getUserFullName(int userId) {
        UserAccount ua = new UserAccountDAO().getById(userId);
        if (ua == null) {
            return "";
        }
        EmployeeView efd = new EmployeeViewDAO().getById(ua.getEmployeeID());
        if (efd == null) {
            return "";
        }
        return efd.getFirstName() + " " + efd.getLastName();
    }

    public EmployeeView getEmployeeDetails(int empId) {
        return empDao.getById(empId);
    }
    
}


// Custom exception for validation errors
class ValidationException extends Exception {

    public ValidationException(String message) {
        super(message);
    }
}