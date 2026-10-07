package util;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.HexFormat;
import java.security.MessageDigest;

public class PasswordCryptoUtil {

    private static final String ALGORITHM = "PBKDF2WithHmacSHA256";
    private static final int ITERATIONS = 600000;
    private static final int KEY_LENGTH = 256;
    private static final int SALT_LENGTH = 16;

    public record HashResult(String hash, String salt) {}

    /**
     * Generates salt + computes PBKDF2 hash in one call.
     */
    public static HashResult hashPassword(String plainPassword) {
        String salt = generateSaltHex();
        String hash = hashPasswordHex(plainPassword, salt);
        return new HashResult(hash, salt);
    }

    /**
     * Verifies if a plain password matches the stored PBKDF2 hash and salt.
     */
    public static boolean verifyPassword(String plainPassword, String storedHash, String storedSalt) {
        if (plainPassword == null || storedHash == null || storedSalt == null) {
            return false;
        }
        String computedHash = hashPasswordHex(plainPassword, storedSalt);
        return constantTimeEquals(computedHash, storedHash);
    }

    //------------------------HELPERS-----------------------

    /**
     * Generates a secure 16-byte random salt using HexFormat.
     */
    private static String generateSaltHex() {
        byte[] salt = new byte[SALT_LENGTH];
        new SecureRandom().nextBytes(salt);
        return HexFormat.of().formatHex(salt);
    }

    /**
     * Derives a PBKDF2-HMAC-SHA256 key from password and salt.
     */
    private static String hashPasswordHex(String plainPassword, String saltHex) {
        if (plainPassword == null || plainPassword.isEmpty()) {
            throw new IllegalArgumentException("Password must not be null or empty.");
        }
        if (saltHex == null || saltHex.isEmpty()) {
            throw new IllegalArgumentException("Salt must not be null or empty.");
        }

        try {
            byte[] salt = HexFormat.of().parseHex(saltHex);
            SecretKeyFactory factory = SecretKeyFactory.getInstance(ALGORITHM);
            PBEKeySpec spec = new PBEKeySpec(
                    plainPassword.toCharArray(),
                    salt,
                    ITERATIONS,
                    KEY_LENGTH
            );
            byte[] hash = factory.generateSecret(spec).getEncoded();
            return HexFormat.of().formatHex(hash);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException("Error computing PBKDF2-HMAC-SHA256 hash", e);
        }
    }

    /**
     * Constant-time comparison using HexFormat.
     */
    private static boolean constantTimeEquals(String hexA, String hexB) {
        if (hexA == null || hexB == null) return false;

        byte[] a = HexFormat.of().parseHex(hexA);
        byte[] b = HexFormat.of().parseHex(hexB);

        return MessageDigest.isEqual(a, b); // Standard constant-time byte comparison
    }
}