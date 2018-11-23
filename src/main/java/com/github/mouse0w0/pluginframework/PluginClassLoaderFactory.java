package com.github.mouse0w0.pluginframework;

public interface PluginClassLoaderFactory {

    PluginClassLoader create(PluginManager pluginManager, PluginDescriptor descriptor);
}
