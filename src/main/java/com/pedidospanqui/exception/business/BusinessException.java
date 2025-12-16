package com.pedidospanqui.exception.business;

import com.pedidospanqui.exception.PanquiException;

public abstract class BusinessException extends PanquiException {

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }
}
