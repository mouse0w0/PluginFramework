package com.github.mouse0w0.pluginframework;

import java.util.List;

public interface DependencyManager {

    void sort(List<PluginDescriptor> descriptors);

    boolean validate(PluginManager pluginManager, PluginDescriptor descriptor);

    List<PluginContainer> getChildren(PluginManager pluginManager, PluginContainer container);
}
