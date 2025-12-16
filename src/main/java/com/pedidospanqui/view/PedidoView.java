package com.pedidospanqui.view;

import com.pedidospanqui.model.ItemPedido;
import com.pedidospanqui.model.Pedido;
import com.pedidospanqui.model.Producto;

import java.util.List;

public class PedidoView extends BaseView {
    public void mostrarMenu() {
        mostrarMensaje("\n");
        mostrarMensaje("======== Gestor de Pedidos ========");
        mostrarMensaje("[1] Agregar Pedido");
        mostrarMensaje("[2] Eliminar Pedido");
        mostrarMensaje("[3] Mostrar Pedidos");
        mostrarMensaje("[4] Mostrar Pedido Elegido");
        mostrarMensaje("[5] Agregar un Producto en un Pedido Elegido");
        mostrarMensaje("[6] Eliminar un Producto en un Pedido Elegido");
        mostrarMensaje("[7] Aumentar Cantidad de un Producto");
        mostrarMensaje("[8] Disminuir Cantidad de un Producto");
        mostrarMensaje("[9] Vaciar Pedido");
        mostrarMensaje("[10] Volver al Menu Principal");
    }

    public void mostrarPedido(Pedido pedido) {
        System.out.printf("\n>>> PEDIDO #%d%n", pedido.getId());
        System.out.printf(
                "%-4s | %-25s | %-9s | %-8s | %-10s%n",
                "ID", "Producto", "Precio", "Cantidad", "Subtotal"
        );
        System.out.println("-----+---------------------------+-----------+----------+----------");
        for (ItemPedido item : pedido.getItems()) {
            Producto p = item.getProducto();

            System.out.printf(
                    "%-4d | %-25s | $%-8.2f | %-8d | $%-10.2f%n",
                    p.getId(),
                    p.getNombre(),
                    item.getPrecio(),
                    item.getCantidad(),
                    item.calcularSubTotal()
            );
        }
        System.out.println("-------------------------------------------------------------------");
        System.out.printf("TOTAL: $%.2f%n", pedido.calcularTotal());
    }

    public void mostrarPedidos(List<Pedido> pedidos) {
        System.out.printf("%-5s | %-21s | %-19s | %-10s%n", "ID", "Cant. Items Distintos", "Cant. Items Totales" , "Total");
        System.out.println("------+-----------------------+---------------------+------------");

        for (Pedido p : pedidos) {
            System.out.printf(
                    "%-5d | %-21d | %-19d | $%-10.2f%n",
                    p.getId(),
                    p.contarCantidadItemsDiferentes(),
                    p.contarCantidadItemsTotales(),
                    p.calcularTotal()
            );
        }
    }
}
