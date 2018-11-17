package com.github.mouse0w0.pluginframework;

import org.slf4j.Logger;

public class PluginContainerImpl implements PluginContainer {

    private final Logger logger;
    private final Object instance;
    private final PluginClassLoader classLoader;
    private final PluginDescriptor descriptor;

    public PluginContainerImpl(Logger logger, Object instance, PluginClassLoader classLoader, PluginDescriptor descriptor) {
        this.logger = logger;
        this.instance = instance;
        this.classLoader = classLoader;
        this.descriptor = descriptor;
    }

    @Override
    public Logger getLogger() {
        return logger;
    }

    @Override
    public Object getInstance() {
        return instance;
    }

    @Override
    public PluginClassLoader getClassLoader() {
        return classLoader;
    }

    @Override
    public PluginDescriptor getDescriptor() {
        return descriptor;
    }

}
