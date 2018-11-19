package com.github.mouse0w0.pluginframework;

import com.github.mouse0w0.version.ComparableVersion;

import java.nio.file.Path;
import java.util.List;

public interface PluginManager {

    PluginContainer loadPlugin(Path pluginPath);

    List<PluginContainer> loadPlugin(PluginSource pluginSource);

    void unloadPlugin(PluginContainer container);

    default void unloadPlugin(String pluginId) {
        unloadPlugin(getPlugin(pluginId));
    }

    List<PluginContainer> getPlugins();

    PluginContainer getPlugin(String pluginId);

    PluginContainer whichPlugin(Class<?> clazz);

    ComparableVersion getSystemVersion();

    void setSystemVersion(ComparableVersion version);

    void loadPlugins();

    default void unloadPlugins() {
        List<PluginContainer> containers = getPlugins();
        for (int i = containers.size() - 1; i >= 0; i--) {
            unloadPlugin(containers.get(i));
        }
    }
}
