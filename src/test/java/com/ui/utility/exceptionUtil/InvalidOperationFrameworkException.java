package com.ui.utility.exceptionUtil;

public class InvalidOperationFrameworkException extends FrameworkException{

    public InvalidOperationFrameworkException(String message) {
        super(message);
    }

    public InvalidOperationFrameworkException(String message, Throwable cause) {
        super(message, cause);
    }
}
