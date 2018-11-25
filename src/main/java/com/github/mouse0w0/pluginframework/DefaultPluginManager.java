package com.github.mouse0w0.pluginframework;

public class DefaultPluginManager extends AbstractPluginManager {

    private final PluginSource source;

    public DefaultPluginManager(PluginSource source) {
        this.source = source;
    }

    @Override
    protected PluginDescriptorFinder createPluginDescriptorFinder() {
        return new PropertiesPluginDescriptorFinder();
    }

    @Override
    protected PluginClassLoaderFactory createPluginClassLoaderFactory() {
        return new DefaultPluginClassLoaderFactory();
    }

    @Override
    protected PluginInstanceFactory createPluginInstanceFactory() {
        return new DefaultPluginInstanceFactory();
    }

    @Override
    protected DependencyManager createDependencyManager() {
        return new DefaultDependencyManager();
    }

    @Override
    public void loadPlugins() {
        loadPlugin(source);
    }
}
