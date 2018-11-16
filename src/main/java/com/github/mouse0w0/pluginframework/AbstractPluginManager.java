package com.github.mouse0w0.pluginframework;

import com.github.mouse0w0.version.ComparableVersion;

import java.nio.file.Path;
import java.util.List;

public abstract class AbstractPluginManager implements PluginManager {
    @Override
    public PluginContainer loadPlugin(Path path) {
        return null;
    }

    @Override
    public List<PluginContainer> loadPlugin(PluginSource pluginSource) {
        return null;
    }

    @Override
    public List<PluginContainer> getPlugins() {
        return null;
    }

    @Override
    public PluginContainer getPlugin(String pluginId) {
        return null;
    }

    @Override
    public PluginContainer whichPlugin(Class<?> clazz) {
        return null;
    }

    @Override
    public ComparableVersion getSystemVersion() {
        return null;
    }

    @Override
    public void setSystemVersion(ComparableVersion version) {

    }
}
