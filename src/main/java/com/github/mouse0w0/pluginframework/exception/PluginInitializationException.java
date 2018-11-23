package com.github.mouse0w0.pluginframework.exception;

public class PluginInitializationException extends PluginException {
    public PluginInitializationException() {
    }

    public PluginInitializationException(String message) {
        super(message);
    }

    public PluginInitializationException(String message, Throwable cause) {
        super(message, cause);
    }

    public PluginInitializationException(Throwable cause) {
        super(cause);
    }
}
