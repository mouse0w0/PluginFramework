package com.github.mouse0w0.pluginframework;

import org.slf4j.Logger;

public interface PluginContainer {

    Logger getLogger();

    Object getInstance();

    ClassLoader getClassLoader();

    PluginDescriptor getDescriptor();
}
