package util;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Duration;
import java.util.HexFormat;

/* 
Query the "Have I Been Pwned" (HIBP) API to check if a password has been exposed in data breaches.
*/

public class PasswordBreachChecker {

    private static final String API_URL = "https://api.pwnedpasswords.com/range/";
    private static final HttpClient client = HttpClient.newBuilder()
            .connectTimeout(Duration.ofSeconds(10))
            .build();

    private record PasswordSha1Hash(String prefix, String suffix) {
    }

    /**
     * Checks if a plaintext password has been exposed in data breaches.
     */
    public static boolean isPasswordCompromised(String plainPassword) {
        if (plainPassword == null || plainPassword.isEmpty()) {
            return false;
        }

        try {
            PasswordSha1Hash hash = hashToSha1(plainPassword);
            String responseBody = fetchPwnedPrefixList(hash.prefix());
            return isSuffixInResponse(responseBody, hash.suffix());
        } catch (Exception e) {
            // Fail-open: If the HIBP service is offline/unreachable, allow normal
            // login/registration
            e.printStackTrace();
            return false;
        }
    }

    // Hash password to SHA-1 (Uppercase) and split into 5-char prefix & 35-char suffix
    private static PasswordSha1Hash hashToSha1(String plainPassword) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-1");
        byte[] hashBytes = digest.digest(plainPassword.getBytes(java.nio.charset.StandardCharsets.UTF_8));
        String fullSha1 = HexFormat.of().formatHex(hashBytes);
        String upperCaseFullSha1 = fullSha1.toUpperCase();

        return new PasswordSha1Hash(upperCaseFullSha1.substring(0, 5), upperCaseFullSha1.substring(5));
    }

    // Query HIBP range API with the 5-character prefix
    private static String fetchPwnedPrefixList(String prefix) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL + prefix))
                .timeout(Duration.ofSeconds(10))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        return response.body();
    }

    // Check if the returned suffix list contains the password's suffix
    private static boolean isSuffixInResponse(String responseBody, String suffix) {
        if (responseBody == null || responseBody.isEmpty()) {
            return false;
        }

        return responseBody.lines()
                .anyMatch(line -> line.startsWith(suffix));
    }
}