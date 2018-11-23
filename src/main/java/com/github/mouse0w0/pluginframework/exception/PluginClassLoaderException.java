package com.github.mouse0w0.pluginframework.exception;

public class PluginClassLoaderException extends PluginException {

    public PluginClassLoaderException() {
    }

    public PluginClassLoaderException(String message) {
        super(message);
    }

    public PluginClassLoaderException(String message, Throwable cause) {
        super(message, cause);
    }

    public PluginClassLoaderException(Throwable cause) {
        super(cause);
    }
}
