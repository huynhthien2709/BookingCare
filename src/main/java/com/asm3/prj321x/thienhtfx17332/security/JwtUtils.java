package com.asm3.prj321x.thienhtfx17332.security;

import org.springframework.util.StringUtils;

public final class JwtUtils {

    private JwtUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static String resolveToken(String bearerToken) {
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}
