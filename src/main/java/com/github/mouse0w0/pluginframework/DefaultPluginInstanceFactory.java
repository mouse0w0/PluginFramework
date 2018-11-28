package com.github.mouse0w0.pluginframework;

import com.github.mouse0w0.pluginframework.exception.PluginException;

public class DefaultPluginInstanceFactory implements PluginInstanceFactory {
    @Override
    public Object create(PluginDescriptor descriptor, ClassLoader classLoader) {
        try {
            Class<?> clazz = Class.forName(descriptor.getMainClass(), false, classLoader);
            return clazz.newInstance();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            throw new PluginException("Cannot create plugin main class.", e);
        }
    }
}
