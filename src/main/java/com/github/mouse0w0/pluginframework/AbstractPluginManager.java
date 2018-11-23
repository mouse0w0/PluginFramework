package com.github.mouse0w0.pluginframework;

import com.github.mouse0w0.pluginframework.exception.PluginAlreadyLoadedException;
import com.github.mouse0w0.pluginframework.exception.PluginClassLoaderException;
import com.github.mouse0w0.pluginframework.exception.PluginException;
import com.github.mouse0w0.pluginframework.exception.PluginInitializationException;
import com.github.mouse0w0.version.ComparableVersion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Path;
import java.util.*;

public abstract class AbstractPluginManager implements PluginManager {

    private final Logger logger = LoggerFactory.getLogger(PluginManager.class);

    protected final Map<String, PluginContainer> pluginContainers = new HashMap<>();

    private ComparableVersion systemVersion;

    private PluginDescriptorFinder pluginDescriptorFinder = createPluginDescriptorFinder();
    private PluginClassLoaderFactory pluginClassLoaderFactory = createPluginClassLoaderFactory();
    private PluginInstanceFactory pluginInstanceFactory = createPluginInstanceFactory();
    private DependencyManager dependencyManager = createDependencyManager();

    abstract protected PluginDescriptorFinder createPluginDescriptorFinder();

    abstract protected PluginClassLoaderFactory createPluginClassLoaderFactory();

    abstract protected PluginInstanceFactory createPluginInstanceFactory();

    abstract protected DependencyManager createDependencyManager();

    @Override
    public PluginContainer loadPlugin(Path pluginPath) {
        return loadPlugin(pluginDescriptorFinder.findDescriptor(pluginPath));
    }

    @Override
    public Collection<PluginContainer> loadPlugin(PluginSource pluginSource) {
        List<PluginDescriptor> descriptors = new LinkedList<>();
        for (Path pluginPath : pluginSource.getPluginPaths()) {
            descriptors.add(pluginDescriptorFinder.findDescriptor(pluginPath));
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

        dependencyManager.validateDependency(this, descriptor);

        PluginClassLoader classLoader;
        try {
            classLoader = pluginClassLoaderFactory.create(this, descriptor);
        } catch (Exception e) {
            throw new PluginClassLoaderException(String.format("Cannot create plugin classloader. Plugin: %s", descriptor.getId()));
        }

        Object instance;
        try {
            instance = pluginInstanceFactory.create(descriptor, classLoader);
        } catch (Exception e) {
            throw new PluginInitializationException(String.format("Cannot create plugin instance. Plugin: %s", descriptor.getId()), e);
        }

        PluginContainer container = new PluginContainerImpl(descriptor, classLoader, instance);
        pluginContainers.put(descriptor.getId(), container);
        return container;
    }

    @Override
    public Collection<PluginContainer> getPlugins() {
        return pluginContainers.values();
    }

    @Override
    public PluginContainer getPlugin(String pluginId) {
        return pluginContainers.get(pluginId);
    }

    @Override
    public PluginContainer whichPlugin(Class<?> clazz) {
        for (PluginContainer container : getPlugins()) {
            if (container.getClassLoader() == clazz.getClassLoader())
                return container;
        }
        return null;
    }

    @Override
    public ComparableVersion getSystemVersion() {
        return systemVersion;
    }

    @Override
    public void setSystemVersion(ComparableVersion version) {
        this.systemVersion = version;
    }

    @Override
    public void unloadPlugin(PluginContainer container) {
        if (!pluginContainers.containsKey(container.getDescriptor().getId()))
            return;
        validateUnloadable(container);
        forceUnloadPlugin(container);
    }

    protected void validateUnloadable(PluginContainer container) {
        Object instance = container.getInstance();
        if (!(instance instanceof Unloadable))
            throw new PluginException(String.format("Cannot unload this plugin. Because plugin %s is not Unloadable.", container.getDescriptor().getId()));

        for (PluginContainer child : dependencyManager.getChildren(getPlugins(), container)) {
            validateUnloadable(child);
        }
    }

    protected void forceUnloadPlugin(PluginContainer container) {
        for (PluginContainer child : dependencyManager.getChildren(getPlugins(), container)) {
            forceUnloadPlugin(child);
        }

        ((Unloadable) container.getInstance()).onUnload();
        pluginContainers.remove(container);
    }
}
