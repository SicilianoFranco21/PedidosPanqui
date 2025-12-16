package com.pedidospanqui.service;

import com.pedidospanqui.exception.business.*;
import com.pedidospanqui.model.Producto;
import com.pedidospanqui.repository.ProductoRepository;

import java.util.List;

public class ProductoService implements IProductoService {
    private final ProductoRepository productoRepository;

    private void validarId(int id) {
        if (id < 0) {
            throw new ProductoInvalidoException("El ID no puede ser menor a cero");
        }
    }

    private Producto obtenerProductoPorId(int id) {
        validarId(id);
        Producto producto = productoRepository.encontrarPorId(id);
        if (producto == null) {
            throw new NoEncontradoException("Producto", id);
        }
        return producto;
    }

    private void validarCantidad(int cantidad) {
        if (cantidad <= 0) {
            throw new ProductoInvalidoException("La cantidad debe ser mayor a 0");
        }
    }

    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @Override
    public void guardarProducto(Producto producto) {
        if (producto == null) {
            throw new ProductoInvalidoException("El producto no puede ser null");
        }
        productoRepository.guardar(producto);
    }

    @Override
    public List<Producto> listarProductos() {
        return productoRepository.encontrarTodos();
    }

    @Override
    public Producto actualizarProducto(int id, String nombre, Double precio) {
        if (nombre != null && !nombre.isBlank()) {
            Producto.validarNombre(nombre);
        }
        if (precio != null && precio != -1) {
            Producto.validarPrecio(precio);
        }
        obtenerProductoPorId(id);
        return productoRepository.actualizar(id, nombre, precio);
    }

    @Override
    public void eliminarProducto(int id) {
        if (listarProductos().isEmpty()) {
            throw new ProductoInvalidoException("El inventario de productos esta vacio");
        }
        Producto producto = obtenerProductoPorId(id);
        productoRepository.borrarPorId(producto.getId());
    }

    @Override
    public void aumentarStock(int id, int cantidad) {
        validarCantidad(cantidad);
        Producto producto = obtenerProductoPorId(id);
        producto.aumentarStock(cantidad);
    }

    @Override
    public void disminuirStock(int id, int cantidad) {
        validarCantidad(cantidad);
        Producto producto = obtenerProductoPorId(id);
        if (producto.getStock() < cantidad) {
            throw new StockInsuficienteException(producto.getNombre());
        }
        producto.disminuirStock(cantidad);
    }

    @Override
    public Producto buscarProducto(int id) {
        return obtenerProductoPorId(id);
    }

    @Override
    public void validarExistenciaProducto(int id) {
        obtenerProductoPorId(id);
    }
}
