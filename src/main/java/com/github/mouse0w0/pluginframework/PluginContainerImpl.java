package com.github.mouse0w0.pluginframework;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PluginContainerImpl implements PluginContainer {

    private final PluginDescriptor descriptor;
    private final Logger logger;
    private final PluginClassLoader classLoader;
    private final Object instance;

    public PluginContainerImpl(PluginDescriptor descriptor, PluginClassLoader classLoader, Object instance) {
        this.descriptor = descriptor;
        this.logger = LoggerFactory.getLogger(descriptor.getId());
        this.classLoader = classLoader;
        this.instance = instance;
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
