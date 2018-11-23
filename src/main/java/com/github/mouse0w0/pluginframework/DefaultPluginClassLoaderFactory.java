package com.github.mouse0w0.pluginframework;

public class DefaultPluginClassLoaderFactory implements PluginClassLoaderFactory {

    @Override
    public PluginClassLoader create(PluginManager pluginManager, PluginDescriptor descriptor) {
        PluginClassLoader classLoader = new DefaultPluginClassLoader(descriptor, Thread.currentThread().getContextClassLoader());
        for (DependencyItem dependency : descriptor.getRequires()) {
            classLoader.getDependencyClassLoaders().add(pluginManager.getPlugin(dependency.getId()).getClassLoader());
        }
        for (DependencyItem dependency : descriptor.getAfters()) {
            PluginContainer container = pluginManager.getPlugin(dependency.getId());
            if (container != null) {
                classLoader.getDependencyClassLoaders().add(container.getClassLoader());
            }
        }
        return classLoader;
    }
}
