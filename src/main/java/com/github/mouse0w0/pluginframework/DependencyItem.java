package com.github.mouse0w0.pluginframework;

import com.github.mouse0w0.pluginframework.util.StringUtils;
import com.github.mouse0w0.version.ComparableVersion;

import java.util.Objects;

public class DependencyItem {

    public static final String SEPARATOR = "@";

    public static DependencyItem parse(String dependency) {
        if (StringUtils.isNullOrEmpty(dependency)) {
            throw new IllegalArgumentException("Dependency cannot be null or empty.");
        }
        String[] args = dependency.split(SEPARATOR, 2);
        if (args.length < 2) {
            throw new IllegalArgumentException("Illegal dependency.");
        }
        return new DependencyItem(args[0], new ComparableVersion(args[1]));
    }

    private final String id;
    private final ComparableVersion version;

    public DependencyItem(String id, ComparableVersion version) {
        this.id = Objects.requireNonNull(id);
        this.version = Objects.requireNonNull(version);
    }

    public String getId() {
        return id;
    }

    public ComparableVersion getVersion() {
        return version;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DependencyItem that = (DependencyItem) o;

        if (!id.equals(that.id)) return false;
        return version.equals(that.version);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + version.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "DependencyItem{" +
                "id='" + id + '\'' +
                ", version=" + version +
                '}';
    }
}
