package com.example.app.core.http;

public class TokenContext {

    private static final ThreadLocal<String> TOKEN = new ThreadLocal<>();

    public static void set(String token) {
        TOKEN.set(token);
    }

    public static String get() {
        return TOKEN.get();
    }

    public static void clear() {
        TOKEN.remove();
    }
}
