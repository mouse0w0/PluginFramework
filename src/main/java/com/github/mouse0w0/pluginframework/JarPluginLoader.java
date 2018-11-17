package com.github.mouse0w0.pluginframework;

import com.github.mouse0w0.pluginframework.exception.PluginLoadException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JarPluginLoader implements PluginLoader {
    @Override
    public boolean isLoadable(PluginDescriptor descriptor) {
        return true;
    }

    @Override
    public PluginContainer loadPlugin(PluginDescriptor descriptor) throws PluginLoadException {
        Logger logger = LoggerFactory.getLogger(descriptor.getId());
        PluginClassLoader classLoader = new PluginClassLoader(descriptor.getPluginPath(), logger, Thread.currentThread().getContextClassLoader());
        try {
            Class<?> clazz = Class.forName(descriptor.getMainClass(), false, classLoader);
            Object instance = clazz.newInstance();
            return new PluginContainerImpl(logger, instance, classLoader, descriptor);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return null;
    }
}
