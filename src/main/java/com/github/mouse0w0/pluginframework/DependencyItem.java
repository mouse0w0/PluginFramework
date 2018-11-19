package com.github.mouse0w0.pluginframework;

import com.github.mouse0w0.pluginframework.util.StringUtils;
import com.github.mouse0w0.version.InvalidVersionSpecificationException;
import com.github.mouse0w0.version.VersionRange;

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
        try {
            return new DependencyItem(args[0], VersionRange.createFromVersionSpec(args[1]));
        } catch (InvalidVersionSpecificationException e) {
            throw new IllegalArgumentException("Illegal version range.");
        }
    }

    private final String id;
    private final VersionRange versionRange;

    public DependencyItem(String id, VersionRange versionRange) {
        this.id = Objects.requireNonNull(id);
        this.versionRange = Objects.requireNonNull(versionRange);
    }

    public String getId() {
        return id;
    }

    public VersionRange getVersionRange() {
        return versionRange;
    }

    @Override
    public String toString() {
        return "DependencyItem{" +
                "id='" + id + '\'' +
                ", versionRange=" + versionRange +
                '}';
    }
}
