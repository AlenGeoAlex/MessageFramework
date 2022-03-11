package io.github.alen_alex.messageframework.core.exception;

public class MissingActionDefinition extends Exception{

    public MissingActionDefinition(String message) {
        super(message);
    }

    public MissingActionDefinition(String message, Throwable cause) {
        super(message, cause);
    }
}
