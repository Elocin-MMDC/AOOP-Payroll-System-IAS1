
package testing;

import java.util.HashSet;
import java.util.Set;
import util.PiiCryptoUtil;

public class PIIEncryptionStressTest {

    public static void main(String[] args) {

        final int TOTAL_TESTS = 1000;

        String[] contexts = {
            "MotorPH:govinformation:sssNumber:v1",
            "MotorPH:govinformation:philHealthNumber:v1",
            "MotorPH:govinformation:tin:v1",
            "MotorPH:govinformation:pagIbigNumber:v1"
        };

        int passed = 0;
        int failed = 0;

        System.out.println("======================================");
        System.out.println("TC-PII-03: ENCRYPTION STRESS TEST");
        System.out.println("======================================");
        System.out.println("Total test cycles: " + TOTAL_TESTS);
        System.out.println("Test data: Synthetic identifiers");
        System.out.println("Encryption: AES-256-GCM");
        System.out.println();

        long startTime = System.nanoTime();

        for (int i = 0; i < TOTAL_TESTS; i++) {

            String context = contexts[i % contexts.length];
            String original = "SYNTHETIC-ID-" + i;

            try {
                String encrypted =
                        PiiCryptoUtil.encrypt(original, context);

                String decrypted =
                        PiiCryptoUtil.decrypt(encrypted, context);

                if (encrypted.startsWith("v1:")
                        && !encrypted.equals(original)
                        && original.equals(decrypted)) {

                    passed++;

                } else {
                    failed++;
                    System.out.println(
                        "[FAIL] Data mismatch at cycle " + (i + 1)
                    );
                }

            } catch (Exception e) {
                failed++;
                System.out.println(
                    "[FAIL] Cycle " + (i + 1)
                    + ": " + e.getClass().getSimpleName()
                );
            }
        }

        long endTime = System.nanoTime();

        double elapsedMs =
                (endTime - startTime) / 1_000_000.0;

        System.out.println("STRESS TEST RESULTS");
        System.out.println("--------------------------------------");
        System.out.println("Total cycles      : " + TOTAL_TESTS);
        System.out.println("Successful cycles : " + passed);
        System.out.println("Failed cycles     : " + failed);
        System.out.printf(
            "Execution time    : %.2f ms%n", elapsedMs
        );

        System.out.println();
        System.out.println("NONCE RANDOMNESS CHECK");
        System.out.println("--------------------------------------");

        Set<String> uniqueCiphertexts = new HashSet<>();

        for (int i = 0; i < 100; i++) {
            uniqueCiphertexts.add(
                PiiCryptoUtil.encrypt(
                    "SAME-SYNTHETIC-ID", contexts[0]
                )
            );
        }

        System.out.println(
            "Encryptions of same ID : 100"
        );
        System.out.println(
            "Unique ciphertexts     : "
            + uniqueCiphertexts.size()
        );

        boolean allPassed =
                failed == 0
                && passed == TOTAL_TESTS
                && uniqueCiphertexts.size() == 100;

        System.out.println();
        System.out.println("======================================");

        if (allPassed) {
            System.out.println("TC-PII-03 RESULT: PASSED");
        } else {
            System.out.println("TC-PII-03 RESULT: FAILED");
            throw new AssertionError(
                "Encryption stress test did not pass."
            );
        }

        System.out.println("======================================");
    }
}
