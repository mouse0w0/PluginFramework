package com.github.mouse0w0.pluginframework;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PluginClassLoader extends URLClassLoader {

    private static final String JAVA_PACKAGE_PREFIX = "java.";

    private final PluginDescriptor descriptor;
    private final Logger logger;
    private final List<ClassLoader> dependencyClassLoaders = new ArrayList<>();

    private final Set<Class<?>> loadedClass = new HashSet<>();

    public PluginClassLoader(PluginDescriptor descriptor, ClassLoader parent) {
        super(new URL[0], parent);
        this.descriptor = descriptor;
        this.logger = LoggerFactory.getLogger(descriptor.getId());
        addPath(descriptor.getPluginPath());
    }

    public List<ClassLoader> getDependencyClassLoaders() {
        return dependencyClassLoaders;
    }

    protected void addPath(Path path) {
        try {
            addURL(path.toUri().toURL());
        } catch (MalformedURLException e) {
            logger.error(e.getMessage(), e);
        }
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        synchronized (getClassLoadingLock(name)) {
            if (name.startsWith(JAVA_PACKAGE_PREFIX)) {
                return findSystemClass(name);
            }

            Class<?> loadedClass;

            loadedClass = findLoadedClass(name);
            if (loadedClass != null) {
                return loadedClass;
            }

            try {
                return super.loadClass(name);
            } catch (ClassNotFoundException e) {

            }

            try {
                return findClass(name);
            } catch (ClassNotFoundException e) {

            }

            loadedClass = loadClassFromDependencies(name);
            if (loadedClass != null) {
                return loadedClass;
            }

            throw new ClassNotFoundException(name);
        }
    }

    protected Class<?> loadClassFromDependencies(String name) {
        for (ClassLoader classLoader : dependencyClassLoaders) {
            try {
                return classLoader.loadClass(name);
            } catch (ClassNotFoundException e) {
                // Try load class from next dependency.
            }
        }
        return null;
    }
}
