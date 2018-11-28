package com.github.mouse0w0.pluginframework;

public interface PluginClassLoaderFactory {

    ClassLoader create(PluginManager pluginManager, PluginDescriptor descriptor);
}
