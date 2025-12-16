package com.pedidospanqui.service;

import com.pedidospanqui.exception.business.NoEncontradoException;
import com.pedidospanqui.exception.business.PedidoInvalidoException;
import com.pedidospanqui.model.ItemPedido;
import com.pedidospanqui.model.Pedido;
import com.pedidospanqui.model.Producto;
import com.pedidospanqui.repository.PedidoRepository;

import java.util.List;

public class PedidoService implements IPedidoService {
    private static final double PORCENTAJE_GANANCIA = 1.30;

    private final PedidoRepository pedidoRepository;
    private final ProductoService productoService;

    private void validarId(int idPedido) {
        if (idPedido < 0) {
            throw new PedidoInvalidoException("El ID no puede ser menor a cero");
        }
    }

    private ItemPedido obtenerItem(int idPedido, int idProducto) {
        Pedido pedido = buscarPedido(idPedido);
        return pedido.seleccionarItemPedido(idProducto);
    }

    private void devolverStock(int idPedido) {
        Pedido pedido = buscarPedido(idPedido);
        int idProducto;
        int stockRecuperado;
        for (ItemPedido item : pedido.getItems()) {
            idProducto = item.getProducto().getId();
            stockRecuperado = item.getCantidad();
            productoService.aumentarStock(idProducto, stockRecuperado);
        }
    }

    public PedidoService(PedidoRepository pedidoRepository, ProductoService productoService) {
        this.pedidoRepository = pedidoRepository;
        this.productoService = productoService;
    }

    @Override
    public void guardarPedido(Pedido pedido) {
        if (pedido == null) {
            throw new PedidoInvalidoException("El pedido no puede ser null");
        }
        pedidoRepository.guardar(pedido);
    }

    @Override
    public List<Pedido> listarPedidos() {
        return pedidoRepository.encontrarTodos();
    }

    @Override
    public void eliminarPedido(int id) {
        buscarPedido(id);
        pedidoRepository.borrarPorId(id);
    }

    @Override
    public void aumentarCantidad(int idPedido, int idProducto, int cantidad) {
        Producto producto = productoService.buscarProducto(idProducto);
        productoService.disminuirStock(producto.getId(), cantidad);
        ItemPedido item = obtenerItem(idPedido, idProducto);
        item.aumentarCantidad(cantidad);

    }

    @Override
    public void disminuirCantidad(int idPedido, int idProducto, int cantidad) {
        Producto producto = productoService.buscarProducto(idProducto);
        productoService.aumentarStock(producto.getId(), cantidad);
        ItemPedido item = obtenerItem(idPedido, idProducto);
        item.disminuirCantidad(cantidad);
    }

    @Override
    public void agregarItemPedido(int idPedido, int idProducto, int cantidad) {
        Pedido pedido = buscarPedido(idPedido);
        Producto producto = productoService.buscarProducto(idProducto);
        productoService.disminuirStock(idProducto, cantidad);
        double precioItem = producto.getPrecio()*PORCENTAJE_GANANCIA;
        ItemPedido item = new ItemPedido(producto, precioItem, cantidad);
        pedido.agregarItem(item);
    }

    @Override
    public void eliminarItemPedido(int idPedido, int idProducto) {
        Pedido pedido = buscarPedido(idPedido);
        ItemPedido item = obtenerItem(idPedido, idProducto);
        Producto producto = productoService.buscarProducto(idProducto);
        productoService.aumentarStock(producto.getId(), item.getCantidad());
        pedido.eliminarItem(item);
    }

    @Override
    public void vaciarPedido(int id) {
        Pedido pedido = buscarPedido(id);
        devolverStock(id);
        pedido.vaciar();
    }

    @Override
    public Pedido buscarPedido(int id) {
        validarId(id);
        Pedido pedido = pedidoRepository.encontrarPorId(id);
        if (pedido == null) {
            throw new NoEncontradoException("Pedido", id);
        }
        return pedido;
    }
}
