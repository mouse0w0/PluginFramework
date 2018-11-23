package com.github.mouse0w0.pluginframework.exception;

public class InvalidDependencyException extends PluginException {

    public InvalidDependencyException() {
    }

    public InvalidDependencyException(String message) {
        super(message);
    }

    public InvalidDependencyException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidDependencyException(Throwable cause) {
        super(cause);
    }
}
