package service;

import model.dao.AttendanceDAO;
import model.pojo.Attendance;
import util.Session;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import model.dao.AuditLogDAO;
import model.pojo.AuditLog;
import util.AccessControlUtil;

public class AttendanceService {
    
    private final AuditLogDAO auditDao = new AuditLogDAO();
    private final AttendanceDAO dao = new AttendanceDAO();
    
    private static final LocalTime EARLIEST_IN = LocalTime.of(6, 0);
    private static final LocalTime LATEST_IN = LocalTime.of(23, 0);
    private static final LocalTime ONTIME_BY = LocalTime.of(10, 0);
    private static final DateTimeFormatter TIME_FMT = DateTimeFormatter.ofPattern("HH:mm");
    
    // The number of minutes an employee must work before a break is automatically deducted
    private static final int BREAK_THRESHOLD_MIN = 5 * 60;  // 5 hours = 300 minutes
    private static final int BREAK_MIN = 60;                // 1 hour break = 60 minutes
    
    public enum State {
        NOT_CLOCKED_IN, CLOCKED_IN, COMPLETED
    }

    public void clockIn() throws AttendanceException {
        LocalTime now = LocalTime.now().truncatedTo(ChronoUnit.MINUTES);
        
        if (now.isBefore(EARLIEST_IN) || now.isAfter(LATEST_IN)) {
            throw new AttendanceException(
                    "Clock‐in only allowed between 06:00 and 17:00."
            );
        }

        Attendance a = getTodayRecord();
        if (a != null) {
            throw new AttendanceException("Already clocked in today.");
        }

        a = new Attendance();
        a.setEmployeeID(Session.getCurrentUser().getEmployeeID());
        a.setDate(LocalDate.now());
        a.setClockIn(now);
        a.setCreatedAt(LocalDateTime.now());
        a.setUpdatedAt(LocalDateTime.now());
        
        // Insert the new record
        dao.insert(a);

        // Re-fetch it to get the generated ID
        Attendance a2 = getTodayRecord();

        // Log the clock-in for audit
        if (a2 != null) {
            AuditLogDAO auditDao = new AuditLogDAO();
            AuditLog log = new AuditLog();
            log.setUserID(Session.getCurrentUser().getUserID());
            log.setCreatedAt(LocalDateTime.now());
            log.setAction("INSERT");
            log.setEntityModified("Attendance");
            log.setEntityID(a2.getAttendanceID());
            log.setAttributeModified("clockIn");
            log.setOldValue("-");
            log.setNewValue(a2.getClockIn().format(TIME_FMT));
            auditDao.insert(log);
        }
    }

    public void clockOut() throws AttendanceException {
        Attendance a = getTodayRecord();
        if (a == null) {
            throw new AttendanceException("You haven't clocked in yet.");
        }
        if (a.getClockOut() != null) {
            throw new AttendanceException("Already clocked out today.");
        }

        // Record clock-out (truncate to minutes)
        LocalTime now = LocalTime.now().truncatedTo(ChronoUnit.MINUTES);
        a.setClockOut(now);

        // Compute total minutes between in/out
        long minutes = Duration.between(a.getClockIn(), now).toMinutes();

        // Subtract 1-hour break if worked more than 5 hours
        if (minutes > BREAK_THRESHOLD_MIN) {
            minutes -= BREAK_MIN;
        }

        // Convert to decimal hours
        BigDecimal totalHours = BigDecimal.valueOf(minutes)
                .divide(BigDecimal.valueOf(60), 2, RoundingMode.HALF_UP);

        // Split into regular (max 8) and overtime (excess)
        BigDecimal eight = BigDecimal.valueOf(8);
        BigDecimal regular = totalHours.min(eight);
        BigDecimal overtime = totalHours.subtract(regular);

        a.setRegularHours(regular);
        a.setOvertimeHours(overtime);

        // Determine status based purely on clock-in time
        a.setStatus(a.getClockIn().isAfter(ONTIME_BY) ? "Late" : "On-time");

        a.setUpdatedAt(LocalDateTime.now());
        
        // Update the attendance record to complete the missing fields
        dao.update(a);
        
        // Log the clock-out for audit
        AuditLogDAO auditDao = new AuditLogDAO();
        AuditLog log = new AuditLog();
        log.setUserID(Session.getCurrentUser().getUserID());
        log.setCreatedAt(LocalDateTime.now());
        log.setAction("UPDATE");
        log.setEntityModified("Attendance");
        log.setEntityID(a.getAttendanceID());
        log.setAttributeModified("clockOut");
        log.setOldValue("-");
        log.setNewValue(a.getClockOut().format(TIME_FMT));
        auditDao.insert(log);
    }

    public List<Attendance> getHistory(int empId) {
        return dao.getByEmployeeId(empId);
    }

    private Attendance getTodayRecord() {
        return dao.getByEmployeeId(
                Session.getCurrentUser().getEmployeeID()
        ).stream()
                .filter(r -> r.getDate().equals(LocalDate.now()))
                .findFirst()
                .orElse(null);
    }

    public State getState() {
        Attendance a = getTodayRecord();
        if (a == null) {
            return State.NOT_CLOCKED_IN;
        }
        if (a.getClockOut() == null) {
            return State.CLOCKED_IN;
        }
        return State.COMPLETED;
    }

    public String getFormattedClockIn() {
        Attendance a = getTodayRecord();
        return (a != null && a.getClockIn() != null)
                ? a.getClockIn().format(TIME_FMT) : "";
    }

    public String getFormattedClockOut() {
        Attendance a = getTodayRecord();
        return (a != null && a.getClockOut() != null)
                ? a.getClockOut().format(TIME_FMT) : "";
    }

    public String getFormattedHoursWorked() {
        Attendance a = getTodayRecord();
        if (a == null || a.getClockOut() == null) {
            return "";
        }
        BigDecimal total = a.getRegularHours().add(a.getOvertimeHours());
        return total.toPlainString();
    }
    
    public List<Attendance> getAllRecords() {
        AccessControlUtil.requireRole("HR Admin");
        return dao.getAll();
    }
    
    public Attendance getById(int attendanceID) {
        AccessControlUtil.requireRole("HR Admin");
        return dao.getById(attendanceID);
    } 
    
    public boolean updateAttendance(int attendanceID, LocalTime newClockIn, LocalTime newClockOut) throws IllegalArgumentException, ValidationException {
        AccessControlUtil.requireRole("HR Admin");
        try {
            Attendance existing = dao.getById(attendanceID);
            if (existing == null) {
                throw new ValidationException("Attendance record not found.");
            }

            LocalTime oldClockIn = existing.getClockIn();
            LocalTime oldClockOut = existing.getClockOut();

            if (newClockIn.isAfter(newClockOut)) {
                throw new ValidationException("Clock-in cannot be after clock-out.");
            }

            long minutes = Duration.between(newClockIn, newClockOut).toMinutes();
            if (minutes > 60) {
                minutes -= 60;
            }

            BigDecimal totalHours = BigDecimal.valueOf(minutes)
                    .divide(BigDecimal.valueOf(60), 2, RoundingMode.HALF_UP);
            BigDecimal newRegular = totalHours.min(BigDecimal.valueOf(8));
            BigDecimal newOvertime = totalHours.subtract(newRegular);
            String newStatus = newClockIn.isAfter(LocalTime.of(10, 0)) ? "Late" : "On-time";

            List<AuditLog> logs = new ArrayList<>();

            if (!oldClockIn.equals(newClockIn)) {
                logs.add(createLog(attendanceID, "clockIn", oldClockIn.toString(), newClockIn.toString()));
            }

            if (!oldClockOut.equals(newClockOut)) {
                logs.add(createLog(attendanceID, "clockOut", oldClockOut.toString(), newClockOut.toString()));
            }

            if (existing.getRegularHours().compareTo(newRegular) != 0) {
                logs.add(createLog(attendanceID, "regularHours",
                        existing.getRegularHours().toPlainString(), newRegular.toPlainString()));
            }

            if (existing.getOvertimeHours().compareTo(newOvertime) != 0) {
                logs.add(createLog(attendanceID, "overtimeHours",
                        existing.getOvertimeHours().toPlainString(), newOvertime.toPlainString()));
            }

            if (!existing.getStatus().equalsIgnoreCase(newStatus)) {
                logs.add(createLog(attendanceID, "status",
                        existing.getStatus(), newStatus));
            }

            if (logs.isEmpty()) {
                throw new ValidationException("No changes were made. No updates needed.");
            }

            // Apply changes
            existing.setClockIn(newClockIn);
            existing.setClockOut(newClockOut);
            existing.setRegularHours(newRegular);
            existing.setOvertimeHours(newOvertime);
            existing.setStatus(newStatus);
            existing.setUpdatedAt(LocalDateTime.now());

            dao.update(existing);

            for (AuditLog log : logs) {
                auditDao.insert(log);
            }

            return true;

        } catch (IllegalArgumentException | ValidationException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    private AuditLog createLog(int entityID, String attr, String oldVal, String newVal) {
        AccessControlUtil.requireRole("HR Admin");
        AuditLog log = new AuditLog();
        log.setUserID(Session.getCurrentUser().getUserID());
        log.setCreatedAt(LocalDateTime.now());
        log.setAction("UPDATE");
        log.setEntityModified("Attendance");
        log.setEntityID(entityID);
        log.setAttributeModified(attr);
        log.setOldValue(oldVal);
        log.setNewValue(newVal);
        return log;
    }

    public static class AttendanceException extends Exception {

        public AttendanceException(String msg) {
            super(msg);
        }
    }  
}