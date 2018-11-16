package com.github.mouse0w0.pluginframework;

import java.nio.file.Path;

public interface PluginDescriptorFinder {

    PluginDescriptor findDescriptor(Path pluginPath);
}
