package com.pws.admin.utility;

import java.util.HashSet;
import java.util.Set;

public class InvalidTokenStore {

    private static Set<String> invalidatedTokens = new HashSet<>();

    public static boolean isTokenInvalid(String token) {
        return invalidatedTokens.contains(token);
    }

    public static void invalidateToken(String token) {
        invalidatedTokens.add(token);
    }

}
