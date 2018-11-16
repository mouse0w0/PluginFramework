package com.github.mouse0w0.pluginframework.util;

import java.nio.file.Files;
import java.nio.file.Path;

public final class PathUtils {
    private PathUtils() {
    }

    public static Path checkExists(Path path) {
        if (!Files.exists(path)) {
            throw new IllegalArgumentException(String.format("Path \"%s\" is not exists.", path.toAbsolutePath().toString()));
        }
        return path;
    }

    public static Path checkFile(Path path) {
        if (Files.isDirectory(path)) {
            throw new IllegalArgumentException(String.format("Path \"%s\" is not file.", path.toAbsolutePath().toString()));
        }
        return path;
    }
}
