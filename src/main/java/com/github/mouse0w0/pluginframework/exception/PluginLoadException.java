package com.github.mouse0w0.pluginframework.exception;

public class PluginLoadException extends PluginException {

    public PluginLoadException() {
    }

    public PluginLoadException(String message) {
        super(message);
    }

    public PluginLoadException(String message, Throwable cause) {
        super(message, cause);
    }

    public PluginLoadException(Throwable cause) {
        super(cause);
    }
}
