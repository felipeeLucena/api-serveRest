package core;

public final class AuthContext {

    private static String bearerToken;

    private AuthContext() {}

    public static void setToken(String token) {
        bearerToken = token;
    }

    public static String getToken() {
        if (bearerToken == null) {
            throw new IllegalStateException("Auth token was not initialized");
        }
        return bearerToken;
    }

    public static boolean hasToken() {
        return bearerToken != null && !bearerToken.isBlank();
    }
}
