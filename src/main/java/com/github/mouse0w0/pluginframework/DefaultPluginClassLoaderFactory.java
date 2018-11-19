package com.github.mouse0w0.pluginframework;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultPluginClassLoaderFactory implements PluginClassLoaderFactory {

    @Override
    public PluginClassLoader create(PluginDescriptor descriptor) {
        Logger logger = LoggerFactory.getLogger(descriptor.getId());
        return new DefaultPluginClassLoader(descriptor, Thread.currentThread().getContextClassLoader());
    }
}
