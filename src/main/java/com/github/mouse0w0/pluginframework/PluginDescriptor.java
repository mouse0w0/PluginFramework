package com.github.mouse0w0.pluginframework;

import com.github.mouse0w0.version.ComparableVersion;

import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public interface PluginDescriptor {

    String getId();

    ComparableVersion getVersion();

    String getName();

    String getMainClass();

    String getDescription();

    List<String> getAuthors();

    List<DependencyItem> getBefores();

    List<DependencyItem> getAfters();

    List<DependencyItem> getRequires();

    Map<String, Object> getMetadata();

    Path getPluginPath();
}
