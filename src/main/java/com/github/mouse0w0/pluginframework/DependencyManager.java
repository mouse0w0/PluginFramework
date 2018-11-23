package com.github.mouse0w0.pluginframework;

import com.github.mouse0w0.pluginframework.exception.InvalidDependencyException;

import java.util.Collection;
import java.util.List;

public interface DependencyManager {

    void sort(List<PluginDescriptor> descriptors);

    void validateDependency(PluginManager pluginManager, PluginDescriptor descriptor) throws InvalidDependencyException;

    List<PluginContainer> getChildren(Collection<PluginContainer> plugins, PluginContainer container);

    boolean isDepended(PluginDescriptor descriptor, PluginDescriptor dependency);
}
