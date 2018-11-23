package com.github.mouse0w0.pluginframework;

import com.github.mouse0w0.version.ComparableVersion;

import java.nio.file.Path;
import java.util.Collection;

public interface PluginManager {

    PluginContainer loadPlugin(Path pluginPath);

    Collection<PluginContainer> loadPlugin(PluginSource pluginSource);

    void unloadPlugin(PluginContainer container);

    default void unloadPlugin(String pluginId) {
        unloadPlugin(getPlugin(pluginId));
    }

    Collection<PluginContainer> getPlugins();

    PluginContainer getPlugin(String pluginId);

    PluginContainer whichPlugin(Class<?> clazz);

    ComparableVersion getSystemVersion();

    void setSystemVersion(ComparableVersion version);

    void loadPlugins();

    default void unloadPlugins() {
        for (PluginContainer container : getPlugins()) {
            unloadPlugin(container);
        }
    }
}
