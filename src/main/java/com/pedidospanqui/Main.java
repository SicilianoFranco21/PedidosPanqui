package com.pedidospanqui;

import com.pedidospanqui.controller.PedidoController;
import com.pedidospanqui.controller.ProductoController;
import com.pedidospanqui.repository.PedidoRepository;
import com.pedidospanqui.repository.ProductoRepository;
import com.pedidospanqui.service.PedidoService;
import com.pedidospanqui.service.ProductoService;
import com.pedidospanqui.view.PedidoView;
import com.pedidospanqui.view.PrincipalView;
import com.pedidospanqui.view.ProductoView;

public class Main {
    public static void main(String[] args) {
        ProductoRepository productoRepository = new ProductoRepository();
        ProductoService productoService = new ProductoService(productoRepository);
        ProductoView productoView = new ProductoView();

        PedidoRepository pedidoRepository = new PedidoRepository();
        PedidoService pedidoService = new PedidoService(pedidoRepository, productoService);
        PedidoView pedidoView = new PedidoView();

        ProductoController productoController = new ProductoController(productoService, productoView);
        PedidoController pedidoController = new PedidoController(pedidoService, pedidoView);

        iniciarMenu(productoController, pedidoController);
    }

    public static void iniciarMenu(ProductoController productoController, PedidoController pedidoController) {
        PrincipalView principalView = new PrincipalView();

        int opcion;
        do {
            principalView.mostrarMenu();
            opcion = principalView.leerEntero("Ingresar una opcion: ");
            switch (opcion) {
                case 1 -> productoController.iniciarMenu();
                case 2 -> pedidoController.iniciarMenu();
                case 3 -> principalView.mostrarMensaje("Gracias por elegir pedidos Panqui. Hasta Luego!");
                default -> principalView.mostrarMensaje("Opcion invalida!");
            }
        }
        while (opcion != 3);
    }
}
