package com.github.mouse0w0.pluginframework;

import com.github.mouse0w0.pluginframework.util.PathUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class FolderPluginSource implements PluginSource {

    private static final Logger LOGGER = LoggerFactory.getLogger(FolderPluginSource.class);

    private final Path path;

    public FolderPluginSource(Path path) {
        this.path = PathUtils.checkFile(PathUtils.checkExists(path));
    }

    @Override
    public List<Path> getPluginPaths() {
        try {
            return Files.list(path).filter(path -> Files.isDirectory(path)).collect(Collectors.toList());
        } catch (IOException e) {
            LOGGER.warn("Cannot get plugin paths", e);
            return Collections.emptyList();
        }
    }
}
