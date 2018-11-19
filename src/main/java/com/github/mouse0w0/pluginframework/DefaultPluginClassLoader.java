package com.github.mouse0w0.pluginframework;

public class DefaultPluginClassLoader extends PluginClassLoader {
    public DefaultPluginClassLoader(PluginDescriptor descriptor, ClassLoader parent) {
        super(descriptor, parent);
    }
}
