package com.pedidospanqui.repository;

import com.pedidospanqui.model.Producto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProductoRepository {
    private final List<Producto> productos = new ArrayList<>();

    public void guardar(Producto producto) {
        productos.add(producto);
    }

    public Producto actualizar(int id, String nombre, Double precio) {
        Producto productoEncontrado = encontrarPorId(id);
        if (nombre != null && !nombre.isBlank()) {
            productoEncontrado.setNombre(nombre);
        }
        if (precio != null && precio != -1) {
            productoEncontrado.setPrecio(precio);
        }
        return productoEncontrado;
    }

    public Producto encontrarPorId(int id) {
        for (Producto p : productos) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    public List<Producto> encontrarTodos() {
        return Collections.unmodifiableList(productos);
    }

    public void borrarPorId(int id) {
        Producto producto = encontrarPorId(id);
        productos.remove(producto);
    }
}
