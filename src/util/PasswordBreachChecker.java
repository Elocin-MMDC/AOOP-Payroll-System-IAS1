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

    // Thrown when the breach check cannot be completed (offline, timeout, or error response)
    public static class BreachCheckUnavailableException extends RuntimeException {

        public BreachCheckUnavailableException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    /**
     * Checks if a plaintext password has been exposed in data breaches.
     * Fails closed: if the HIBP service cannot be reached, a BreachCheckUnavailableException
     * is thrown so the password is not accepted unchecked.
     */
    public static boolean isPasswordCompromised(String plainPassword) {
        if (plainPassword == null || plainPassword.isEmpty()) {
            return false;
        }

        try {
            PasswordSha1Hash hash = hashToSha1(plainPassword);
            String responseBody = fetchPwnedPrefixList(hash.prefix());
            return isSuffixInResponse(responseBody, hash.suffix());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new BreachCheckUnavailableException("Password breach check was interrupted.", e);
        } catch (Exception e) {
            throw new BreachCheckUnavailableException("Password breach check service is unavailable.", e);
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
        // An error response is not a valid suffix list; treat it as unavailable rather than "not breached"
        if (response.statusCode() != 200) {
            throw new IllegalStateException("HIBP returned HTTP " + response.statusCode());
        }
        return response.body();
    }

    // Check if the returned suffix list contains the password's suffix
    private static boolean isSuffixInResponse(String responseBody, String suffix) {
        // HIBP always returns hundreds of suffixes per prefix; an empty body means the check did not work
        if (responseBody == null || responseBody.isEmpty()) {
            throw new IllegalStateException("HIBP returned an empty response");
        }

        return responseBody.lines()
                .anyMatch(line -> line.startsWith(suffix));
    }
}