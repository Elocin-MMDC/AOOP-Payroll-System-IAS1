package util;

import java.net.URLEncoder;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.time.Instant;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/**
 * RFC 6238 time-based one-time passwords (HMAC-SHA1, 30-second steps, 6 digits),
 * compatible with Google Authenticator, Microsoft Authenticator, and similar apps.
 */
public final class TotpUtil {

    public static final String ISSUER = "MotorPH";
    private static final String HMAC_ALGORITHM = "HmacSHA1";
    private static final int SECRET_LENGTH_BYTES = 20; // 160 bits, as recommended by RFC 4226
    private static final int TIME_STEP_SECONDS = 30;
    private static final int DIGITS = 6;
    private static final int ALLOWED_DRIFT_STEPS = 1; // accept previous/current/next step for clock drift
    private static final String BASE32_ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ234567";
    private static final SecureRandom SECURE_RANDOM = new SecureRandom();

    private TotpUtil() {
    }

    /**
     * Generates a new random Base32-encoded TOTP secret.
     */
    public static String generateSecret() {
        byte[] secret = new byte[SECRET_LENGTH_BYTES];
        SECURE_RANDOM.nextBytes(secret);
        return base32Encode(secret);
    }

    /**
     * Returns the time step that the given code matches within the allowed drift window,
     * or -1 if the code is not valid. Callers must reject steps that were already used.
     */
    public static long findMatchingTimeStep(String base32Secret, String code, Instant now) {
        if (base32Secret == null || !isWellFormedCode(code)) {
            return -1;
        }

        byte[] key = base32Decode(base32Secret);
        long currentStep = now.getEpochSecond() / TIME_STEP_SECONDS;
        byte[] submitted = code.getBytes(StandardCharsets.US_ASCII);
        long matchedStep = -1;

        // Check every step in the window without returning early to keep timing uniform
        for (long step = currentStep - ALLOWED_DRIFT_STEPS; step <= currentStep + ALLOWED_DRIFT_STEPS; step++) {
            byte[] expected = generateCode(key, step).getBytes(StandardCharsets.US_ASCII);
            if (MessageDigest.isEqual(expected, submitted)) {
                matchedStep = step;
            }
        }
        return matchedStep;
    }

    public static long findMatchingTimeStep(String base32Secret, String code) {
        return findMatchingTimeStep(base32Secret, code, Instant.now());
    }

    /**
     * Builds the otpauth:// URI encoded in the enrollment QR code.
     */
    public static String buildOtpAuthUri(String accountName, String base32Secret) {
        String label = urlEncode(ISSUER + ":" + accountName);
        return "otpauth://totp/" + label
                + "?secret=" + base32Secret
                + "&issuer=" + urlEncode(ISSUER)
                + "&algorithm=SHA1&digits=" + DIGITS
                + "&period=" + TIME_STEP_SECONDS;
    }

    /**
     * Formats a secret in groups of four characters for manual entry.
     */
    public static String formatSecretForDisplay(String base32Secret) {
        return base32Secret.replaceAll("(.{4})(?!$)", "$1 ");
    }

    public static boolean isWellFormedCode(String code) {
        return code != null && code.matches("\\d{" + DIGITS + "}");
    }

    // ------------------------HELPERS-----------------------

    // RFC 4226 HOTP with dynamic truncation
    static String generateCode(byte[] key, long timeStep) {
        try {
            Mac mac = Mac.getInstance(HMAC_ALGORITHM);
            mac.init(new SecretKeySpec(key, HMAC_ALGORITHM));
            byte[] hash = mac.doFinal(ByteBuffer.allocate(Long.BYTES).putLong(timeStep).array());

            int offset = hash[hash.length - 1] & 0x0F;
            int binary = ((hash[offset] & 0x7F) << 24)
                    | ((hash[offset + 1] & 0xFF) << 16)
                    | ((hash[offset + 2] & 0xFF) << 8)
                    | (hash[offset + 3] & 0xFF);

            int otp = binary % (int) Math.pow(10, DIGITS);
            return String.format("%0" + DIGITS + "d", otp);
        } catch (GeneralSecurityException ex) {
            throw new SecurityException("TOTP generation failed.", ex);
        }
    }

    static String base32Encode(byte[] data) {
        StringBuilder sb = new StringBuilder((data.length * 8 + 4) / 5);
        int buffer = 0;
        int bitsLeft = 0;
        for (byte b : data) {
            buffer = (buffer << 8) | (b & 0xFF);
            bitsLeft += 8;
            while (bitsLeft >= 5) {
                sb.append(BASE32_ALPHABET.charAt((buffer >> (bitsLeft - 5)) & 0x1F));
                bitsLeft -= 5;
            }
        }
        if (bitsLeft > 0) {
            sb.append(BASE32_ALPHABET.charAt((buffer << (5 - bitsLeft)) & 0x1F));
        }
        return sb.toString();
    }

    static byte[] base32Decode(String encoded) {
        String normalized = encoded.replace(" ", "").replace("=", "").toUpperCase();
        ByteBuffer out = ByteBuffer.allocate(normalized.length() * 5 / 8);
        int buffer = 0;
        int bitsLeft = 0;
        for (char c : normalized.toCharArray()) {
            int value = BASE32_ALPHABET.indexOf(c);
            if (value < 0) {
                throw new IllegalArgumentException("Invalid Base32 character in TOTP secret.");
            }
            buffer = (buffer << 5) | value;
            bitsLeft += 5;
            if (bitsLeft >= 8) {
                out.put((byte) (buffer >> (bitsLeft - 8)));
                bitsLeft -= 8;
            }
        }
        return out.array();
    }

    private static String urlEncode(String value) {
        return URLEncoder.encode(value, StandardCharsets.UTF_8).replace("+", "%20");
    }
}
