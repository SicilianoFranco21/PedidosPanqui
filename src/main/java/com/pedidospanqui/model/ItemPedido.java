package com.pedidospanqui.model;

import com.pedidospanqui.exception.business.ItemPedidoInvalidoException;

public class ItemPedido {
    private final Producto producto;
    private final double precio;
    private int cantidad;

    public ItemPedido(Producto producto, double precio, int cantidad) {
        validarProducto(producto);
        validarPrecio(precio);
        validarCantidad(cantidad);
        this.producto = new Producto(producto);
        this.precio = precio;
        this.cantidad = cantidad;
    }

    public static void validarProducto(Producto producto) {
        if (producto == null) {
            throw new ItemPedidoInvalidoException("El producto no puede ser null");
        }
    }

    public static void validarPrecio(double precio) {
        if (precio <= 0) {
            throw new ItemPedidoInvalidoException("El precio debe ser mayor a 0");
        }
    }

    public static void validarCantidad(int cantidad) {
        if (cantidad <= 0) {
            throw new ItemPedidoInvalidoException("La cantidad debe ser mayor a 0");
        }
    }

    public Producto getProducto() {
        return producto;
    }

    public double getPrecio() {
        return precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void aumentarCantidad(int cantidad) {
        validarCantidad(cantidad);
        this.cantidad += cantidad;
    }

    public void disminuirCantidad(int cantidad) {
        validarCantidad(cantidad);
        if (this.cantidad < cantidad) {
            throw new ItemPedidoInvalidoException("No hay cantidad suficiente para disminuir");
        }
        this.cantidad -= cantidad;
    }

    public double calcularSubTotal() {
        return cantidad*precio;
    }

    @Override
    public String toString() {
        return String.format("ItemPedido(producto=%s, precio=%.2f, cantidad=%d", producto, precio, cantidad);
    }
}
