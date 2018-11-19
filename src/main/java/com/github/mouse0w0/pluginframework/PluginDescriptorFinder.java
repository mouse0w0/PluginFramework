package com.github.mouse0w0.pluginframework;

import com.github.mouse0w0.pluginframework.exception.InvalidPluginException;

import java.nio.file.Path;

public interface PluginDescriptorFinder {

    PluginDescriptor findDescriptor(Path pluginPath) throws InvalidPluginException;
}
