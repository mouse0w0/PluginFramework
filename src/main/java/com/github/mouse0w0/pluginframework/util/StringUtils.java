package com.github.mouse0w0.pluginframework.util;

public final class StringUtils {
    private StringUtils() {
    }

    public static boolean isNullOrEmpty(String value) {
        return value == null || value.isEmpty();
    }
}
