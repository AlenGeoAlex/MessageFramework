package io.github.alen_alex.messageframework.exception;

public class MissingActionDefinition extends RuntimeException{

    public MissingActionDefinition(String message) {
        super(message);
    }

    public MissingActionDefinition(String message, Throwable cause) {
        super(message, cause);
    }
}
