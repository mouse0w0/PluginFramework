package com.github.mouse0w0.pluginframework;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class JarPluginSource implements PluginSource {

    private final Path folder;

    public JarPluginSource(Path folder) {
        this.folder = folder;
    }

    @Override
    public List<Path> getPluginPaths() {
        try {
            return Files.list(folder).filter(path -> Files.isDirectory(path)).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace(); // TODO:
            return Collections.emptyList();
        }
    }
}
