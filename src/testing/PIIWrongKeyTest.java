
package testing;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Base64;
import util.PiiCryptoUtil;

public class PIIWrongKeyTest {

    private static final String CONTEXT =
        "MotorPH:govinformation:sssNumber:v1";

    public static void main(String[] args) throws Exception {

        // Child process: attempt decryption with a different AES key.
        if (args.length == 2 && args[0].equals("--decrypt")) {
            try {
                PiiCryptoUtil.decrypt(args[1], CONTEXT);
                System.out.println("WRONG_KEY_ACCEPTED");
                System.exit(1);
            } catch (SecurityException expected) {
                System.out.println("WRONG_KEY_REJECTED");
                System.out.println("Exception: " +
                    expected.getClass().getSimpleName());
                System.out.println("Message: " +
                    expected.getMessage());
                return;
            }
        }

        System.out.println("TC-PII-05: WRONG KEY DECRYPTION");
        System.out.println("--------------------------------");

        String syntheticId = "33-4444444-5";

        // Baseline using the existing application key.
        String encrypted =
            PiiCryptoUtil.encrypt(syntheticId, CONTEXT);

        String decrypted =
            PiiCryptoUtil.decrypt(encrypted, CONTEXT);

        if (!syntheticId.equals(decrypted)) {
            throw new AssertionError("Baseline failed.");
        }

        System.out.println("[PASS] Correct AES key accepted");

        // Generate a temporary AES key for the child only.
        byte[] randomKey = new byte[32];
        new SecureRandom().nextBytes(randomKey);

        String temporaryKey =
            Base64.getEncoder().encodeToString(randomKey);

        String javaExecutable =
            System.getProperty("java.home")
            + File.separator + "bin"
            + File.separator + "java";

        ProcessBuilder builder = new ProcessBuilder(
            javaExecutable,
            "-cp",
            System.getProperty("java.class.path"),
            "testing.PIIWrongKeyTest",
            "--decrypt",
            encrypted
        );

        // Override AES key ONLY in the child process.
        builder.environment().put(
            "MOTORPH_PII_AES_KEY", temporaryKey
        );

        builder.redirectErrorStream(true);

        Process child = builder.start();

        String output = new String(
            child.getInputStream().readAllBytes(),
            StandardCharsets.UTF_8
        );

        int exitCode = child.waitFor();

        if (exitCode != 0 ||
            !output.contains("WRONG_KEY_REJECTED")) {
            System.out.println("[FAIL] Wrong key test failed.");
            throw new AssertionError(output);
        }

        System.out.println("[PASS] Wrong AES key rejected");
        System.out.println("[INFO] SecurityException detected");
        System.out.println("[INFO] PII decryption failed.");
        System.out.println("--------------------------------");
        System.out.println("TC-PII-05 RESULT: PASSED");
    }
}
