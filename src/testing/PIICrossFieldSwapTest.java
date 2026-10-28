
package testing;

import util.PiiCryptoUtil;

public class PIICrossFieldSwapTest {

    private static final String SSS_CONTEXT =
        "MotorPH:govinformation:sssNumber:v1";

    private static final String TIN_CONTEXT =
        "MotorPH:govinformation:tin:v1";

    public static void main(String[] args) {

        String syntheticId = "33-4444444-5";

        System.out.println("TC-PII-04: CROSS-FIELD CIPHERTEXT SWAP");
        System.out.println("-------------------------------------");

        String encrypted = PiiCryptoUtil.encrypt(
            syntheticId, SSS_CONTEXT
        );

        // Baseline: decrypt using the correct SSS context
        String decrypted = PiiCryptoUtil.decrypt(
            encrypted, SSS_CONTEXT
        );

        if (!syntheticId.equals(decrypted)) {
            throw new AssertionError("Baseline decryption failed.");
        }

        System.out.println("[PASS] Correct SSS context accepted");

        // Adversarial test: use the wrong field context
        try {
            PiiCryptoUtil.decrypt(encrypted, TIN_CONTEXT);

            throw new AssertionError(
                "Wrong field context was accepted!"
            );

        } catch (SecurityException expected) {
            System.out.println("[PASS] Wrong TIN context rejected");
            System.out.println("[INFO] Exception: "
                + expected.getClass().getSimpleName());
            System.out.println("[INFO] Message: "
                + expected.getMessage());
        }

        System.out.println("-------------------------------------");
        System.out.println("TC-PII-04 RESULT: PASSED");
    }
}
