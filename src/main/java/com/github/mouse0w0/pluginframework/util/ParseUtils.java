package com.github.mouse0w0.pluginframework.util;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public final class ParseUtils {
    private ParseUtils() {
    }

    public static List<String> parseList(String value) {
        if (value.startsWith("[") && value.endsWith("]")) {
            value = value.substring(1, value.length() - 1);
        }

        String[] values = value.split("[ ,]+");
        return Arrays.asList(values);
    }

    public static <T> List<T> parseList(String value, Function<String, T> function) {
        if (value.startsWith("[") && value.endsWith("]")) {
            value = value.substring(1, value.length() - 1);
        }

        String[] values = value.split("[ ,]+");
        return Arrays.stream(values).map(function).collect(Collectors.toList());
    }
}
