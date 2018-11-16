package com.github.mouse0w0.pluginframework;

import com.github.mouse0w0.pluginframework.exception.PluginLoadException;

import java.nio.file.Path;

public interface PluginLoader {

    boolean isLoadable(PluginDescriptor descriptor);

    PluginContainer loadPlugin(PluginDescriptor descriptor) throws PluginLoadException;
}
