package util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.HexFormat;

public class OtpUtil {

    private static final SecureRandom RANDOM = new SecureRandom();


    public static String maskEmail(String email) {
        if (email == null || !email.contains("@")) {
            return email;
        }

        String[] parts = email.split("@", 2);
        String username = parts[0];
        String domain = parts[1];

        if (username.length() <= 2) {
            // For short usernames like "ab@gmail.com" -> "a*@gmail.com"
            return username.charAt(0) + "*@" + domain;
        }

        // Displays first character + *** + last character + domain
        return username.charAt(0)
                + "***"
                + username.charAt(username.length() - 1)
                + "@"
                + domain;
    }

    /**
     * Generates a random 6-digit numeric OTP string (e.g., "048291").
     */
    public static String generate6DigitOTP() {
        int number = RANDOM.nextInt(1_000_000);
        return String.format("%06d", number);
    }

    /**
     * Generates a cryptographically secure 32-byte hex recovery token.
     */
    public static String generateRecoveryToken() {
        byte[] tokenBytes = new byte[32];
        RANDOM.nextBytes(tokenBytes);
        return HexFormat.of().formatHex(tokenBytes);
    }

    /**
     * Computes the SHA-256 hash of a string (OTP or recovery token) for DB storage.
     */
    public static String hashValue(String rawValue) {
        if (rawValue == null || rawValue.isEmpty()) {
            throw new IllegalArgumentException("Value to hash cannot be null or empty.");
        }
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(rawValue.getBytes(java.nio.charset.StandardCharsets.UTF_8));
            return HexFormat.of().formatHex(hash);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error computing SHA-256 hash", e);
        }
    }
}