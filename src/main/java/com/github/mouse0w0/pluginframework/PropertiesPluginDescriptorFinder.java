package com.github.mouse0w0.pluginframework;

import com.github.mouse0w0.pluginframework.exception.InvalidPluginException;
import com.github.mouse0w0.pluginframework.util.ParseUtils;
import com.github.mouse0w0.pluginframework.util.StringUtils;
import com.github.mouse0w0.version.ComparableVersion;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.Properties;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.regex.Pattern;

public class PropertiesPluginDescriptorFinder implements PluginDescriptorFinder {
    public static final Pattern DEFAULT_PLUGIN_NAME_PATTERN = Pattern.compile("[a-zA-Z0-9_$]+");
    public static final String DEFAULT_PLUGIN_DESCRIPTOR_FILE_NAME = "plugin.properties";

    private final String pluginDescriptorFileName;
    private final Pattern pluginNamePattern;

    public PropertiesPluginDescriptorFinder() {
        this(DEFAULT_PLUGIN_DESCRIPTOR_FILE_NAME, DEFAULT_PLUGIN_NAME_PATTERN);
    }

    public PropertiesPluginDescriptorFinder(String pluginDescriptorFileName, Pattern pluginNamePattern) {
        this.pluginDescriptorFileName = pluginDescriptorFileName;
        this.pluginNamePattern = pluginNamePattern;
    }

    public String getPluginDescriptorFileName() {
        return pluginDescriptorFileName;
    }

    @Override
    public PluginDescriptor findDescriptor(Path pluginPath) throws InvalidPluginException {
        try (JarFile jarFile = new JarFile(pluginPath.toFile())) {
            JarEntry entry = jarFile.getJarEntry(pluginDescriptorFileName);
            if (entry == null) {
                throw new InvalidPluginException(String.format("Cannot find plugin descriptor. Plugin path: %s", pluginPath.toAbsolutePath()));
            }

            Properties properties = new Properties();
            try (InputStream inputStream = jarFile.getInputStream(entry)) {
                properties.load(inputStream);
            }

            DefaultPluginDescriptor.Builder builder = DefaultPluginDescriptor.builder().withPluginPath(pluginPath);

            String pluginId = properties.getProperty("id");
            if (StringUtils.isNullOrEmpty(pluginId) && pluginNamePattern.matcher(pluginId).matches()) {
                throw new InvalidPluginException("Illegal plugin id.");
            }
            builder.withId(pluginId);

            String mainClass = properties.getProperty("mainClass");
            if (StringUtils.isNullOrEmpty(mainClass)) {
                throw new InvalidPluginException("Illegal plugin main class.");
            }
            builder.withMainClass(mainClass);

            String rawVersion = properties.getProperty("version");
            if (StringUtils.isNullOrEmpty(rawVersion)) {
                throw new InvalidPluginException("Illegal plugin version");
            }
            builder.withVersion(new ComparableVersion(rawVersion));

            String name = properties.getProperty("name");
            builder.withName(StringUtils.isNullOrEmpty(name) ? pluginId : name);

            String description = properties.getProperty("description");
            if (!StringUtils.isNullOrEmpty(description)) {
                builder.withDescription(description);
            }

            String authors = properties.getProperty("authors");
            if (!StringUtils.isNullOrEmpty(authors)) {
                builder.withAuthors(ParseUtils.parseList(authors));
            }

            String befores = properties.getProperty("befores");
            if (!StringUtils.isNullOrEmpty(befores)) {
                builder.withBefores(ParseUtils.parseList(befores, DependencyItem::parse));
            }

            String afters = properties.getProperty("afters");
            if (!StringUtils.isNullOrEmpty(afters)) {
                builder.withAfters(ParseUtils.parseList(afters, DependencyItem::parse));
            }

            String requires = properties.getProperty("requires");
            if (!StringUtils.isNullOrEmpty(requires)) {
                builder.withRequires(ParseUtils.parseList(requires, DependencyItem::parse));
            }

            return builder.build();
        } catch (IOException e) {
            throw new InvalidPluginException(String.format("Cannot find plugin descriptor. Plugin path: %s", pluginPath.toAbsolutePath()), e);
        }
    }
}
