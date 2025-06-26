package util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordUtil {

    // Generate hash for the password
    public static String sha256Hash(String plainPassword) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] digest = md.digest(plainPassword.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-256 algorithm not available", e);
        }
    }

    // Verify a plain password against a stored hash
    public static boolean verify(String plainPassword, String hashedPassword) {
        if (hashedPassword == null) {
            throw new IllegalArgumentException("Hash must not be null");
        }
        
        // SHA-256 hex
        if (hashedPassword.matches("^[0-9a-fA-F]{64}$")) {
            return sha256Hash(plainPassword).equalsIgnoreCase(hashedPassword);
        }
        throw new IllegalArgumentException("Unrecognized hash format");
    }
}