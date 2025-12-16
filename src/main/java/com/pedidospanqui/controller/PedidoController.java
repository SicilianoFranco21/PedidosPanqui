package com.pedidospanqui.controller;

import com.pedidospanqui.exception.PanquiException;
import com.pedidospanqui.model.Pedido;
import com.pedidospanqui.service.PedidoService;
import com.pedidospanqui.view.PedidoView;

public class PedidoController {
    private final PedidoService pedidoService;
    private final PedidoView pedidoView;

    public PedidoController(PedidoService pedidoService,
                            PedidoView pedidoView) {
        this.pedidoService = pedidoService;
        this.pedidoView = pedidoView;
    }

    public void agregarPedido() {
        Pedido pedido = new Pedido();
        pedidoService.guardarPedido(pedido);
        pedidoView.mostrarMensaje("Pedido creado exitosamente!");
    }

    public void eliminarPedido() {
        int idPedido = pedidoView.leerEntero("Ingresar ID del Pedido: ");
        pedidoService.eliminarPedido(idPedido);
        pedidoView.mostrarMensaje("Pedido eliminado exitosamente!");
    }

    public void mostrarPedidos() {
        pedidoView.mostrarPedidos(pedidoService.listarPedidos());
    }

    public void mostrarPedidoElegido() {
        int idPedido = pedidoView.leerEntero("Ingresar ID del Pedido: ");
        pedidoView.mostrarPedido(pedidoService.buscarPedido(idPedido));
    }

    public void aumentarCantidadItem() {
        int idPedido = pedidoView.leerEntero("Ingresar el ID del Pedido: ");
        int idItem = pedidoView.leerEntero("Ingresar el ID del Item: ");
        int cantidad = pedidoView.leerEntero("Ingresar cantidad a aumentar: ");
        pedidoService.aumentarCantidad(idPedido, idItem, cantidad);
        pedidoView.mostrarMensaje("Cantidad aumentada exitosamente!");
    }

    public void disminuirCantidadItem() {
        int idPedido = pedidoView.leerEntero("Ingresar el ID del Pedido: ");
        int idItem = pedidoView.leerEntero("Ingresar el ID del Item: ");
        int cantidad = pedidoView.leerEntero("Ingresar cantidad a disminuir: ");
        pedidoService.disminuirCantidad(idPedido, idItem, cantidad);
        pedidoView.mostrarMensaje("Cantidad disminuida exitosamente!");
    }

    public void agregarItemAlPedido() {
        int idPedido = pedidoView.leerEntero("Ingresar el ID del Pedido: ");
        int idItem = pedidoView.leerEntero("Ingresar el ID del Item: ");
        int cantidad = pedidoView.leerEntero("Ingresar cantidad: ");
        pedidoService.agregarItemPedido(idPedido, idItem, cantidad);
        pedidoView.mostrarMensaje("Item agregado exitosamente!");
    }

    public void eliminarItemDelPedido() {
        int idPedido = pedidoView.leerEntero("Ingresar el ID del Pedido: ");
        int idItem = pedidoView.leerEntero("Ingresar el ID del Item: ");
        pedidoService.eliminarItemPedido(idPedido, idItem);
        pedidoView.mostrarMensaje("Item eliminado exitosamente!");
    }

    public void vaciarPedido() {
        int idPedido = pedidoView.leerEntero("Ingresar ID del Pedido: ");
        pedidoService.vaciarPedido(idPedido);
        pedidoView.mostrarMensaje("Los items del pedido fueron eliminados exitosamente!");
    }

    public void iniciarMenu() {
        int opcion = -1;
        do {
            try {
                pedidoView.mostrarMenu();
                opcion = pedidoView.leerEntero("Ingresar una opcion: ");
                switch (opcion) {
                    case 1 -> agregarPedido();
                    case 2 -> eliminarPedido();
                    case 3 -> mostrarPedidos();
                    case 4 -> mostrarPedidoElegido();
                    case 5 -> agregarItemAlPedido();
                    case 6 -> eliminarItemDelPedido();
                    case 7 -> aumentarCantidadItem();
                    case 8 -> disminuirCantidadItem();
                    case 9 -> vaciarPedido();
                    case 10 -> pedidoView.mostrarMensaje("Retornando al menu principal...");
                    default -> pedidoView.mostrarMensaje("Opcion invalida!");
                }
            }
            catch (PanquiException e) {
                pedidoView.mostrarError(e.getMessage());
            }
        } while (opcion != 10);
    }
}
