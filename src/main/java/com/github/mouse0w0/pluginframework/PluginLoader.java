package com.github.mouse0w0.pluginframework;

import com.github.mouse0w0.pluginframework.exception.PluginLoadException;

public interface PluginLoader {

    boolean isLoadable(PluginDescriptor descriptor);

    PluginClassLoader loadPlugin(PluginDescriptor descriptor) throws PluginLoadException;
}
