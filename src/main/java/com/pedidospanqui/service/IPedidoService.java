package com.pedidospanqui.service;

import com.pedidospanqui.model.Pedido;

import java.util.List;

public interface IPedidoService {
    void guardarPedido(Pedido pedido);
    List<Pedido> listarPedidos();
    void eliminarPedido(int id);
    void aumentarCantidad(int idPedido, int idProducto, int cantidad);
    void disminuirCantidad(int idPedido, int idProducto, int cantidad);
    void agregarItemPedido(int idPedido, int idProducto, int cantidad);
    void eliminarItemPedido(int idPedido, int idProducto);
    void vaciarPedido(int id);
    Pedido buscarPedido(int id);
}
