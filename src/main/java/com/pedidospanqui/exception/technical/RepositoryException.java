package com.pedidospanqui.exception.technical;

import com.pedidospanqui.exception.PanquiException;

public class RepositoryException extends PanquiException {
    public RepositoryException(String message, Throwable cause) {
        super(message, cause);
    }
}
