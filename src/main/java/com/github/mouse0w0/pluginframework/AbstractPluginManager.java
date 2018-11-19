package com.github.mouse0w0.pluginframework;

import com.github.mouse0w0.pluginframework.exception.InvalidPluginException;
import com.github.mouse0w0.pluginframework.exception.PluginAlreadyLoadedException;
import com.github.mouse0w0.version.ComparableVersion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Path;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public abstract class AbstractPluginManager implements PluginManager {

    private final Logger logger = LoggerFactory.getLogger(PluginManager.class);

    private final List<PluginContainer> pluginContainers = new LinkedList<>();
    private final List<PluginContainer> unmodifiablePluginContainers = Collections.unmodifiableList(pluginContainers);

    private ComparableVersion systemVersion;

    private PluginDescriptorFinder pluginDescriptorFinder;
    private PluginClassLoaderFactory pluginClassLoaderFactory;
    private PluginInstanceFactory pluginInstanceFactory;
    private DependencyManager dependencyManager;

    @Override
    public PluginContainer loadPlugin(Path pluginPath) {
        try {
            PluginDescriptor pluginDescriptor = pluginDescriptorFinder.findDescriptor(pluginPath);
            return loadPlugin(pluginDescriptor);
        } catch (InvalidPluginException e) {
            logger.warn(String.format("Plugin descriptor not found. Plugin path: %s", pluginPath.toAbsolutePath()), e);
            throw e;
        }
    }

    @Override
    public List<PluginContainer> loadPlugin(PluginSource pluginSource) {
        List<PluginDescriptor> descriptors = new LinkedList<>();
        for (Path pluginPath : pluginSource.getPluginPaths()) {
            try {
                descriptors.add(pluginDescriptorFinder.findDescriptor(pluginPath));
            } catch (InvalidPluginException e) {
                logger.warn(String.format("Plugin descriptor not found. Plugin path: %s", pluginPath.toAbsolutePath()), e);
            }
        }

        dependencyManager.sort(descriptors);

        List<PluginContainer> pluginContainers = new LinkedList<>();
        for (PluginDescriptor descriptor : descriptors) {
            pluginContainers.add(loadPlugin(descriptor));
        }

        return pluginContainers;
    }

    protected PluginContainer loadPlugin(PluginDescriptor descriptor) {
        if (getPlugin(descriptor.getId()) != null) {
            throw new PluginAlreadyLoadedException(String.format("Plugin %s has been loaded.", descriptor.getId()));
        }

        PluginClassLoader classLoader = pluginClassLoaderFactory.create(descriptor);
        if (classLoader == null) {

        }

        Object instance = pluginInstanceFactory.create(descriptor, classLoader);
        if (instance == null) {

        }

        PluginContainer container = new PluginContainerImpl(descriptor, classLoader, instance);
        pluginContainers.add(container);
        return container;
    }

    @Override
    public List<PluginContainer> getPlugins() {
        return unmodifiablePluginContainers;
    }

    @Override
    public PluginContainer getPlugin(String pluginId) {
        return pluginContainers.stream().filter(container -> container.getDescriptor().getId().equals(pluginId)).findFirst().orElse(null);
    }

    @Override
    public PluginContainer whichPlugin(Class<?> clazz) {
        return pluginContainers.stream().filter(container -> container.getClassLoader().isBeLoaded(clazz)).findFirst().orElse(null);
    }

    @Override
    public ComparableVersion getSystemVersion() {
        return systemVersion;
    }

    @Override
    public void setSystemVersion(ComparableVersion version) {
        this.systemVersion = version;
    }
}
