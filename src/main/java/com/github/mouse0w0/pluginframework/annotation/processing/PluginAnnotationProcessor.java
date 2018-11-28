package com.github.mouse0w0.pluginframework.annotation.processing;

import com.github.mouse0w0.pluginframework.annotation.Plugin;
import com.github.mouse0w0.pluginframework.util.StringUtils;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import javax.tools.FileObject;
import javax.tools.StandardLocation;
import java.io.IOException;
import java.io.Writer;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

public class PluginAnnotationProcessor extends AbstractProcessor {

    private final Properties properties = new Properties();

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.RELEASE_8;
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        return arrayToSet(Plugin.class.getName());
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        if (!roundEnv.processingOver()) {
            Set<? extends Element> elements = roundEnv.getElementsAnnotatedWith(Plugin.class);
            if (elements.size() == 0) {
                return false;
            }

            if (elements.size() > 1) {
                processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, "Cannot have more than one plugin main class.");
            }

            Element element = elements.iterator().next();
            properties.put("mainClass", ((TypeElement) element).getQualifiedName().toString());

            Plugin plugin = element.getAnnotation(Plugin.class);
            if (StringUtils.isNullOrEmpty(plugin.id())) {
                processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, "Plugin id cannot be null or empty.");
            }
            properties.put("id", plugin.id());
            if (StringUtils.isNullOrEmpty(plugin.version())) {
                processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, "Plugin version cannot be null or empty.");
            }
            properties.put("version", plugin.version());
            if (!StringUtils.isNullOrEmpty(plugin.name())) {
                properties.put("name", plugin.name());
            }
            if (!StringUtils.isNullOrEmpty(plugin.description())) {
                properties.put("description", plugin.description());
            }
            if (plugin.authors().length != 0) {
                properties.put("authors", arrayToString(plugin.authors()));
            }
            if (plugin.befores().length != 0) {
                properties.put("befores", arrayToString(plugin.befores()));
            }
            if (plugin.afters().length != 0) {
                properties.put("afters", arrayToString(plugin.afters()));
            }
            if (plugin.requires().length != 0) {
                properties.put("requires", arrayToString(plugin.requires()));
            }
        } else {
            try {
                FileObject fileObject = processingEnv.getFiler().createResource(StandardLocation.CLASS_OUTPUT, "", "plugin.properties");
                try (Writer writer = fileObject.openWriter()) {
                    properties.store(writer, "Created by @Plugin");
                }
            } catch (IOException e) {
                processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, "Cannot create plugin.properties");
                e.printStackTrace();
            }
        }
        return false;
    }

    private static Set<String> arrayToSet(String... array) {
        Set<String> set = new HashSet<String>(array.length);
        for (String s : array) {
            set.add(s);
        }
        return set;
    }

    private static String arrayToString(String... array) {
        StringBuilder builder = new StringBuilder("[");
        if (array.length > 0) {
            builder.append(array[0]);
        }
        for (int i = 1; i < array.length; i++) {
            builder.append(", ").append(array[i]);
        }
        return builder.append("]").toString();
    }
}
