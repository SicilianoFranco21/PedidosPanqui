package com.pedidospanqui.exception.business;

public class StockInsuficienteException extends BusinessException {
    public StockInsuficienteException(String nombreProducto) {
        super("Stock insuficiente para el producto: " + nombreProducto);
    }
}
