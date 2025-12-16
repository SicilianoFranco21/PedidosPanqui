package com.pedidospanqui.view;

import com.pedidospanqui.model.Producto;

import java.util.List;

public class ProductoView extends BaseView {
    @Override
    public void mostrarMenu() {
        mostrarMensaje("\n");
        mostrarMensaje("======== Inventario de Productos ========");
        mostrarMensaje("[1] Agregar Producto");
        mostrarMensaje("[2] Eliminar Producto");
        mostrarMensaje("[3] Mostrar Productos");
        mostrarMensaje("[4] Mostrar Producto Elegido");
        mostrarMensaje("[5] Actualizar Producto");
        mostrarMensaje("[6] Aumentar Stock de un Producto");
        mostrarMensaje("[7] Disminuir Stock de un Producto");
        mostrarMensaje("[8] Volver al Menu Principal");
    }

    public Double leerDouble(String msg) {
        System.out.print(msg);
        Double valor = scanner.nextDouble();
        scanner.nextLine();
        return valor;
    }

    public void mostrarProducto(Producto producto) {
        System.out.printf(
            "[ID: %d] %s - $%.2f | Stock: %d%n",
                producto.getId(),
                producto.getNombre(),
                producto.getPrecio(),
                producto.getStock()
        );
    }

    public void mostrarProductos(List<Producto> productos) {
        System.out.printf(
                "%-4s | %-25s | %-8s | %-5s%n",
                "ID", "Nombre", "Precio", "Stock"
        );
        System.out.println("-----+---------------------------+----------+-------");
        for (Producto p : productos) {
            System.out.printf(
                    "%-4d | %-25s | $%-7.2f | %-5d%n",
                    p.getId(),
                    p.getNombre(),
                    p.getPrecio(),
                    p.getStock()
            );
        }
    }

}
