package com.github.mouse0w0.pluginframework;

public interface PluginInstanceFactory {

    Object create(PluginDescriptor descriptor, PluginClassLoader classLoader);
}
