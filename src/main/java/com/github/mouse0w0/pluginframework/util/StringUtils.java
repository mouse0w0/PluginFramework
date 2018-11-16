package com.github.mouse0w0.pluginframework.util;

public final class StringUtils {
    private StringUtils() {
    }

    public static boolean isNullOrEmpty(String value) {
        return value == null || value.isEmpty();
    }

    public static String notEmpty(String value) {
        if (value == null) {
            throw new NullPointerException("Object cannot be null.");
        }

        if (value.isEmpty()) {
            throw new IllegalArgumentException("String cannot be empty.");
        }

        return value;
    }
}
