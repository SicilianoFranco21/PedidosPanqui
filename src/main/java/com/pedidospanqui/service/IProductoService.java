package com.pedidospanqui.service;

import com.pedidospanqui.model.Producto;

import java.util.List;

public interface IProductoService {
    void guardarProducto(Producto producto);
    List<Producto> listarProductos();
    Producto actualizarProducto(int id, String nombre, Double precio);
    void eliminarProducto(int id);
    void aumentarStock(int id, int cantidad);
    void disminuirStock(int id, int cantidad);
    Producto buscarProducto(int id);
    public void validarExistenciaProducto(int id);
}
