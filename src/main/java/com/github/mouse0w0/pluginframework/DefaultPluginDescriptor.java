package com.github.mouse0w0.pluginframework;

import com.github.mouse0w0.version.ComparableVersion;

import java.nio.file.Path;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DefaultPluginDescriptor implements PluginDescriptor {

    private final String id;
    private final ComparableVersion version;
    private final Path pluginPath;
    private final String name;
    private final String mainClass;
    private final String description;
    private final List<String> authors;
    private final List<DependencyItem> befores;
    private final List<DependencyItem> afters;
    private final List<DependencyItem> requires;
    private final Map<String, Object> metadata = new HashMap<>();

    public DefaultPluginDescriptor(String id, ComparableVersion version, Path pluginPath, String name, String mainClass, String description, List<String> authors, List<DependencyItem> befores, List<DependencyItem> afters, List<DependencyItem> requires, Map<String, Object> metadata) {
        this.id = id;
        this.version = version;
        this.pluginPath = pluginPath;
        this.name = name;
        this.mainClass = mainClass;
        this.description = description;
        this.authors = authors;
        this.befores = befores;
        this.afters = afters;
        this.requires = requires;
        this.metadata.putAll(metadata);
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public ComparableVersion getVersion() {
        return version;
    }

    @Override
    public Path getPluginPath() {
        return pluginPath;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getMainClass() {
        return mainClass;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public List<String> getAuthors() {
        return authors;
    }

    @Override
    public List<DependencyItem> getBefores() {
        return befores;
    }

    @Override
    public List<DependencyItem> getAfters() {
        return afters;
    }

    @Override
    public List<DependencyItem> getRequires() {
        return requires;
    }

    @Override
    public Map<String, Object> getMetadata() {
        return metadata;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private String id;
        private ComparableVersion version;
        private Path pluginPath;
        private String name;
        private String mainClass;
        private String description;
        private List<String> authors = Collections.emptyList();
        private List<DependencyItem> befores = Collections.emptyList();
        private List<DependencyItem> afters = Collections.emptyList();
        ;
        private List<DependencyItem> requires = Collections.emptyList();
        ;
        private Map<String, Object> metadata = Collections.emptyMap();

        private Builder() {
        }

        public Builder withId(String id) {
            this.id = id;
            return this;
        }

        public Builder withVersion(ComparableVersion version) {
            this.version = version;
            return this;
        }

        public Builder withPluginPath(Path pluginPath) {
            this.pluginPath = pluginPath;
            return this;
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withMainClass(String mainClass) {
            this.mainClass = mainClass;
            return this;
        }

        public Builder withDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder withAuthors(List<String> authors) {
            this.authors = authors;
            return this;
        }

        public Builder withBefores(List<DependencyItem> befores) {
            this.befores = befores;
            return this;
        }

        public Builder withAfters(List<DependencyItem> afters) {
            this.afters = afters;
            return this;
        }

        public Builder withRequires(List<DependencyItem> requires) {
            this.requires = requires;
            return this;
        }

        public Builder withMetadata(Map<String, Object> metadata) {
            this.metadata = metadata;
            return this;
        }

        public DefaultPluginDescriptor build() {
            return new DefaultPluginDescriptor(id, version, pluginPath, name, mainClass, description, authors, befores, afters, requires, metadata);
        }
    }
}
