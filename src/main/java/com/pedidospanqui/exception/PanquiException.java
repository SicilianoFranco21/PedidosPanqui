package com.pedidospanqui.exception;

public abstract class PanquiException extends RuntimeException {
    public PanquiException(String message) {
        super(message);
    }

    public PanquiException(String message, Throwable cause) {
        super(message, cause);
    }
}
