package com.github.mouse0w0.pluginframework;

import com.github.mouse0w0.pluginframework.exception.InvalidDependencyException;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class DefaultDependencyManager implements DependencyManager {

    @Override
    public void sort(List<PluginDescriptor> descriptors) {
        Collections.sort(descriptors, (o1, o2) -> {
            if (o1.getRequires().stream().anyMatch(item -> item.getId().equals(o2.getId())) ||
                    o1.getAfters().stream().anyMatch(item -> item.getId().equals(o2.getId())) ||
                    o2.getBefores().stream().anyMatch(item -> item.getId().equals(o1.getId()))) {
                return 1;
            } else if (o2.getRequires().stream().anyMatch(item -> item.getId().equals(o1.getId())) ||
                    o2.getAfters().stream().anyMatch(item -> item.getId().equals(o1.getId())) ||
                    o1.getBefores().stream().anyMatch(item -> item.getId().equals(o2.getId()))) {
                return -1;
            }
            return 0;
        });
    }

    @Override
    public void validateDependency(PluginManager pluginManager, PluginDescriptor descriptor) {
        for (DependencyItem item : descriptor.getRequires()) {
            if (item.getId().isEmpty()) { // Validate system version.
                if (pluginManager.getSystemVersion() != null && !item.getVersionRange().containsVersion(pluginManager.getSystemVersion())) {
                    throw new InvalidDependencyException(String.format("Unsupported system version. Required: %s", item.getVersionRange()));
                }
            } else {
                PluginContainer container = pluginManager.getPlugin(item.getId());
                if (container == null || !item.getVersionRange().containsVersion(container.getDescriptor().getVersion())) {
                    throw new InvalidDependencyException(String.format("Unsupported dependency version. Required: %s@%s", item.getId(), item.getVersionRange()));
                }
            }
        }
    }

    @Override
    public List<PluginContainer> getChildren(Collection<PluginContainer> plugins, PluginContainer container) {
        return plugins.stream().filter(plugin -> isDepended(plugin.getDescriptor(), container.getDescriptor())).collect(Collectors.toList());
    }

    @Override
    public boolean isDepended(PluginDescriptor descriptor, PluginDescriptor dependency) {
        return descriptor.getRequires().stream().anyMatch(item -> item.getId().equals(dependency.getId()));
    }
}
