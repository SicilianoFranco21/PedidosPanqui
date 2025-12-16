package com.pedidospanqui.model;

import com.pedidospanqui.exception.business.ProductoInvalidoException;
import com.pedidospanqui.exception.business.StockInsuficienteException;

public class Producto {
    private static int generadorId = 0;
    private final int id;
    private String nombre;
    private double precio;
    private int stock;

    public Producto(String nombre, double precio, int stock) {
        this.id = ++generadorId;
        setNombre(nombre);
        setPrecio(precio);
        setStock(stock);
    }

    public Producto(Producto producto) {
        if (producto == null) {
            throw new ProductoInvalidoException("Producto nulo");
        }
        this.id = producto.id;
        setNombre(producto.nombre);
        setPrecio(producto.precio);
        setStock(producto.stock);
    }

    public static void validarNombre(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            throw new ProductoInvalidoException("El nombre no puede estar vacío");
        }
    }

    public static void validarPrecio(double precio) {
        if (precio <= 0) {
            throw new ProductoInvalidoException("El precio debe ser mayor a 0");
        }
    }

    public static void validarStock(int stock) {
        if (stock < 0) {
            throw new ProductoInvalidoException("El stock no puede ser negativo");
        }
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public int getStock() {
        return stock;
    }

    public void setNombre(String nombre) {
        validarNombre(nombre);
        this.nombre = nombre;
    }

    public void setPrecio(double precio) {
        validarPrecio(precio);
        this.precio = precio;
    }

    public void setStock(int stock) {
        validarStock(stock);
        this.stock = stock;
    }

    public void aumentarStock(int cantidad) {
        if (cantidad <= 0) {
            throw new ProductoInvalidoException("La cantidad debe ser mayor a 0");
        }
        this.stock += cantidad;
    }

    public void disminuirStock(int cantidad) {
        if (cantidad <= 0) {
            throw new ProductoInvalidoException("La cantidad debe ser mayor a 0");
        }
        if (this.stock < cantidad) {
            throw new StockInsuficienteException(nombre);
        }
        this.stock -= cantidad;
    }

    @Override
    public String toString() {
        return String.format("Producto(id=%d, nombre=%s, precio=%.2f, stock=%d)", id, nombre, precio, stock);
    }
}
