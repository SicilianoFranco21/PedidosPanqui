package com.pedidospanqui.view;

public class PrincipalView extends BaseView {
    @Override
    public void mostrarMenu() {
        mostrarMensaje("\n");
        mostrarMensaje("======== PedidosPanqui ========");
        mostrarMensaje("[1] Ir al inventario");
        mostrarMensaje("[2] Ir al gestor de pedidos");
        mostrarMensaje("[3] Salir de PedidosPanqui");
    }
}
