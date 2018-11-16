package com.github.mouse0w0.pluginframework;

import com.github.mouse0w0.version.ComparableVersion;

import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public class DefaultPluginDescriptor implements PluginDescriptor {
    @Override
    public String getId() {
        return null;
    }

    @Override
    public ComparableVersion getVersion() {
        return null;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public String getMainClass() {
        return null;
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public List<String> getAuthors() {
        return null;
    }

    @Override
    public List<DependencyItem> getBefores() {
        return null;
    }

    @Override
    public List<DependencyItem> getAfters() {
        return null;
    }

    @Override
    public List<DependencyItem> getRequires() {
        return null;
    }

    @Override
    public Map<String, Object> getMetadata() {
        return null;
    }

    @Override
    public Path getPluginPath() {
        return null;
    }
}
