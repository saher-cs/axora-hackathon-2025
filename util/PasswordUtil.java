package com.axora.communityskillexchange.util;

import java.security.MessageDigest;

public class PasswordUtil {
    public static String hash(String plain) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] bytes = md.digest(plain.getBytes("UTF-8"));
            StringBuilder sb = new StringBuilder();
            for(byte b : bytes) sb.append(String.format("%02x", b));
            return sb.toString();
        } catch(Exception e) { throw new RuntimeException(e); }
    }

    public static boolean matches(String plain, String hash) {
        return hash(plain).equals(hash);
    }
}
