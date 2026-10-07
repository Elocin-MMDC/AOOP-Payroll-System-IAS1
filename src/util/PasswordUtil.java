package util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import util.PasswordBreachChecker;
import model.pojo.UserAccount;
import util.PasswordCryptoUtil.HashResult;

public class PasswordUtil {

    // Applies salt and hash directly onto a UserAccount entity.
    public static void applyHashedPassword(UserAccount user, String plainPassword) {
        HashResult result = PasswordCryptoUtil.hashPassword(plainPassword);
        user.setPasswordSalt(result.salt());
        user.setPassword(result.hash());
    }

    // Verify a plain password against a UserAccount's stored hash and salt
    public static boolean verifyUserPassword(String plainPassword, UserAccount user) {

        //Check if the user has a salt value, if not, use SHA-256 hash for backward compatibility
        // Migration is handled in the AuthenticationService, where the password is rehashed with salt after successful verification.
        if (user.getPasswordSalt() == null || user.getPasswordSalt().isEmpty()) {
            return verify_sha256hash(plainPassword, user.getPassword());
        }

        return PasswordCryptoUtil.verifyPassword(plainPassword, user.getPassword(), user.getPasswordSalt());
    }

    // Generates a randomized password for user accounts
    public static String generateRandomPassword() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] randomBytes = new byte[12];
        secureRandom.nextBytes(randomBytes);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(randomBytes);
    }

    //Check if at least 8 characters long
    public static boolean isPasswordLengthOkay(String plainPassword){
        if (plainPassword.length() < 8) {
            return false;
        }
        return true;
    }

    public static boolean isPasswordCompromised(String plainPassword) {
        return PasswordBreachChecker.isPasswordCompromised(plainPassword);
    }

    // public static HashResult hashPassword(String plainPassword) {
    //     return PasswordCryptoUtil.hashPassword(plainPassword);
    // }

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
    public static boolean verify_sha256hash(String plainPassword, String hashedPassword) {
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