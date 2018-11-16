package com.github.mouse0w0.pluginframework;

import com.github.mouse0w0.version.ComparableVersion;
import org.slf4j.Logger;

import java.util.Map;

public class PluginContainerImpl implements PluginContainer {

    @Override
    public String getId() {
        return null;
    }

    @Override
    public ComparableVersion getVersion() {
        return null;
    }

    @Override
    public Logger getLogger() {
        return null;
    }

    @Override
    public Object getInstance() {
        return null;
    }

    @Override
    public ClassLoader getClassLoader() {
        return null;
    }

    @Override
    public Map<String, Object> getMetadata() {
        return null;
    }
}
