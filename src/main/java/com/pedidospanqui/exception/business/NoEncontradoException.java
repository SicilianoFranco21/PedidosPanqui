package com.pedidospanqui.exception.business;

public class NoEncontradoException extends BusinessException {
    public NoEncontradoException(String entidad, int id) {
        super("No existe un " + entidad + " con ID: " + id);
    }
}
