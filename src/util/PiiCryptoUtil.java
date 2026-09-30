package util;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public final class PiiCryptoUtil {

    private static final String AES_ENV = "MOTORPH_PII_AES_KEY";
    private static final String HMAC_ENV = "MOTORPH_PII_HMAC_KEY";
    private static final String PREFIX = "v1:";
    private static final int KEY_LENGTH_BYTES = 32;
    private static final int IV_LENGTH_BYTES = 12;
    private static final int GCM_TAG_LENGTH_BITS = 128;
    private static final SecureRandom SECURE_RANDOM = new SecureRandom();

    private static final SecretKey AES_KEY = loadKey(AES_ENV, "AES");
    private static final SecretKey HMAC_KEY = loadKey(HMAC_ENV, "HmacSHA256");

    private PiiCryptoUtil() {
    }

    public static String encrypt(String plaintext, String context) {
        if (plaintext == null) {
            return null;
        }
        requireContext(context);

        try {
            byte[] iv = new byte[IV_LENGTH_BYTES];
            SECURE_RANDOM.nextBytes(iv);

            Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
            cipher.init(Cipher.ENCRYPT_MODE, AES_KEY, new GCMParameterSpec(GCM_TAG_LENGTH_BITS, iv));
            cipher.updateAAD(context.getBytes(StandardCharsets.UTF_8));

            byte[] encrypted = cipher.doFinal(plaintext.getBytes(StandardCharsets.UTF_8));
            byte[] envelope = ByteBuffer.allocate(iv.length + encrypted.length)
                    .put(iv)
                    .put(encrypted)
                    .array();

            return PREFIX + Base64.getEncoder().encodeToString(envelope);
        } catch (GeneralSecurityException ex) {
            throw new SecurityException("PII encryption failed.", ex);
        }
    }

    public static String decrypt(String storedValue, String context) {
        if (storedValue == null) {
            return null;
        }
        requireContext(context);

        if (!storedValue.startsWith(PREFIX)) {
            throw new SecurityException("PII value is not in the expected encrypted format.");
        }

        try {
            byte[] envelope = Base64.getDecoder().decode(storedValue.substring(PREFIX.length()));
            if (envelope.length < IV_LENGTH_BYTES + (GCM_TAG_LENGTH_BITS / 8)) {
                throw new SecurityException("PII encrypted value is invalid.");
            }

            byte[] iv = Arrays.copyOfRange(envelope, 0, IV_LENGTH_BYTES);
            byte[] encrypted = Arrays.copyOfRange(envelope, IV_LENGTH_BYTES, envelope.length);

            Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
            cipher.init(Cipher.DECRYPT_MODE, AES_KEY, new GCMParameterSpec(GCM_TAG_LENGTH_BITS, iv ));
            cipher.updateAAD(context.getBytes(StandardCharsets.UTF_8));

            return new String(cipher.doFinal(encrypted), StandardCharsets.UTF_8);
        } catch (IllegalArgumentException | GeneralSecurityException ex) {
            throw new SecurityException("PII decryption failed.", ex);
        }
    }

    public static byte[] lookupHmac(String plaintext, String context) {
        if (plaintext == null) {
            return null;
        }
        requireContext(context);

        try {
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(HMAC_KEY);
            mac.update(context.getBytes(StandardCharsets.UTF_8));
            mac.update((byte) 0);
            return mac.doFinal(plaintext.getBytes(StandardCharsets.UTF_8));
        } catch (GeneralSecurityException ex) {
            throw new SecurityException("PII lookup protection failed.", ex);
        }
    }

    public static boolean isEncrypted(String value) {
        return value != null && value.startsWith(PREFIX);
    }

    private static SecretKey loadKey(String environmentVariable, String algorithm) {
        String encoded = System.getenv(environmentVariable);
        if (encoded == null || encoded.isBlank()) {
            throw new IllegalStateException(environmentVariable + " is required.");
        }

        final byte[] keyBytes;
        try {
            keyBytes = Base64.getDecoder().decode(encoded);
        } catch (IllegalArgumentException ex) {
            throw new IllegalStateException(environmentVariable + " must be valid Base64.", ex);
        }

        if (keyBytes.length != KEY_LENGTH_BYTES) {
            throw new IllegalStateException(environmentVariable + " must decode to exactly 32 bytes.");
        }

        return new SecretKeySpec(keyBytes, algorithm);
    }

    private static void requireContext(String context) {
        if (context == null || context.isBlank()) {
            throw new IllegalArgumentException("PII field context is required.");
        }
    }
}
