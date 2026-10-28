
package testing;

import java.util.Base64;
import util.PiiCryptoUtil;

public class PIICiphertextTamperingTest {

    public static void main(String[] args) {

        String context = "MotorPH:govinformation:sssNumber:v1";
        String syntheticId = "1234567890";

        System.out.println("TC-PII-01: CIPHERTEXT TAMPERING");
        System.out.println("-----------------------------");

        String encrypted = PiiCryptoUtil.encrypt(syntheticId, context);
        String decrypted = PiiCryptoUtil.decrypt(encrypted, context);

        if (!syntheticId.equals(decrypted)) {
            throw new AssertionError("Normal decryption failed.");
        }

        System.out.println("[PASS] Normal encryption/decryption");

        byte[] envelope = Base64.getDecoder()
                .decode(encrypted.substring(3));

        // Modify one byte of the GCM authentication tag
        envelope[envelope.length - 1] ^= 0x01;

        String tampered = "v1:" +
                Base64.getEncoder().encodeToString(envelope);

        System.out.println("[INFO] Modified one byte of encrypted data");

        try {
            PiiCryptoUtil.decrypt(tampered, context);

            throw new AssertionError("Tampered data was accepted!");

        } catch (SecurityException expected) {
            System.out.println("[PASS] Tampered data rejected");
            System.out.println("[INFO] Exception: "
                    + expected.getClass().getSimpleName());
            System.out.println("[INFO] Message: "
                    + expected.getMessage());
        }

        System.out.println("-----------------------------");
        System.out.println("TC-PII-01 RESULT: PASSED");
    }
}
