package io.github.alen_alex.messageframework.core.exception;

public class IllegalActionDefinition extends RuntimeException{

    public IllegalActionDefinition(String message) {
        super(message);
    }

    public IllegalActionDefinition(String message, Throwable cause) {
        super(message, cause);
    }
}
