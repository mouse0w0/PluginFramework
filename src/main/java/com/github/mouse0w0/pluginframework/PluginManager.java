package com.github.mouse0w0.pluginframework;

import com.github.mouse0w0.version.ComparableVersion;

import java.nio.file.Path;
import java.util.List;

public interface PluginManager {

    PluginContainer loadPlugin(Path path);

    List<PluginContainer> loadPlugin(PluginSource pluginSource);

    default void unloadPlugin(PluginContainer container) {
        throw new UnsupportedOperationException("Cannot unload plugin");
    }

    default void unloadPlugin(String pluginId) {
        unloadPlugin(getPlugin(pluginId));
    }

    List<PluginContainer> getPlugins();

    PluginContainer getPlugin(String pluginId);

    PluginContainer whichPlugin(Class<?> clazz);

    ComparableVersion getSystemVersion();

    void setSystemVersion(ComparableVersion version);
}
