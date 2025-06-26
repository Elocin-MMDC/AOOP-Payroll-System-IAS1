package util;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;

public class ValidationUtil {
    
    public static boolean isEmpty(String value) {
        return value == null || value.trim().isEmpty();
    }

    public static boolean isValidPhone(String phone) {
        return phone.matches("^[0-9]{3}-[0-9]{3}-[0-9]{3}$");
    }

    public static boolean isValidZip(String zip) {
        return zip.isEmpty() || zip.matches("^[0-9]{4}$");
    }

    public static boolean isValidSSS(String sss) {
        return sss.matches("^[0-9]{2}-[0-9]{7}-[0-9]{1}$");
    }

    public static boolean isValidPhilHealth(String philHealth) {
        return philHealth.matches("^[0-9]{12}$");
    }

    public static boolean isValidTIN(String tin) {
        return tin.matches("^[0-9]{3}-[0-9]{3}-[0-9]{3}-[0-9]{3}$");
    }

    public static boolean isValidPagIbig(String pagIbig) {
        return pagIbig.matches("^[0-9]{12}$");
    }

    public static boolean isAgeValid(LocalDate birthdate) {
        LocalDate today = LocalDate.now();
        Period age = Period.between(birthdate, today);
        return age.getYears() >= 18 && age.getYears() <= 100;
    }

    public static boolean isValidSalary(BigDecimal value) {
        return value != null && value.compareTo(BigDecimal.ZERO) >= 0;
    }
}
