package com.pedidospanqui.controller;

import com.pedidospanqui.model.Producto;
import com.pedidospanqui.service.IProductoService;
import com.pedidospanqui.view.ProductoView;
import com.pedidospanqui.exception.PanquiException;

public class ProductoController {
    private final IProductoService productoService;
    private final ProductoView productoView;

    public ProductoController(IProductoService productoService, ProductoView productoView) {
        this.productoService = productoService;
        this.productoView = productoView;
    }

    public void agregarProducto() {
        String nombre = productoView.leerCadena("Ingresar nombre del producto: ");
        double precio = productoView.leerDouble("Ingresar precio del producto: ");
        int stock = productoView.leerEntero("Ingresar stock del producto: ");
        productoService.guardarProducto(new Producto(nombre, precio, stock));
        productoView.mostrarMensaje("Producto agregado exitosamente!");

    }

    public void eliminarProducto() {
        int id = productoView.leerEntero("Ingresar ID del producto: ");
        productoService.eliminarProducto(id);
        productoView.mostrarMensaje("Producto eliminado exitosamente!");
    }

    public void mostrarProductos() {
        productoView.mostrarMensaje(">>> Lista de productos: ");
        productoView.mostrarProductos(productoService.listarProductos());
    }

    public void mostrarProductoElegido() {
        int id = productoView.leerEntero("Ingresar ID del producto: ");
        Producto producto = productoService.buscarProducto(id);
        productoView.mostrarProducto(producto);
    }

    public void actualizarProducto() {
        int id = productoView.leerEntero("Ingresar ID del producto: ");
        productoService.validarExistenciaProducto(id);
        String nombre = productoView.leerCadena("Ingresar Nombre del producto (ENTER para no actualizar): ");
        Double precio = productoView.leerDouble("Ingresar precio del producto (-1 para no actualizar): ");
        Producto productoActualizado = productoService.actualizarProducto(id, nombre, precio);
        productoView.mostrarMensaje("Producto actualizado! Ver detalles a continuacion: ");
        productoView.mostrarProducto(productoActualizado);
    }

    public void aumentarStock() {
        int id = productoView.leerEntero("Ingresar ID del producto: ");
        productoService.validarExistenciaProducto(id);
        int stock = productoView.leerEntero("Ingresar cantidad de Stock a aumentar: ");
        productoService.aumentarStock(id, stock);
        productoView.mostrarMensaje("El stock fue aumentado en " + stock + " unidades!");
    }

    public void disminuirStock() {
        int id = productoView.leerEntero("Ingresar ID del producto: ");
        productoService.validarExistenciaProducto(id);
        int stock = productoView.leerEntero("Ingresar cantidad de Stock a disminuir: ");
        productoService.disminuirStock(id, stock);
        productoView.mostrarMensaje("El stock fue disminuido en " + stock + " unidades!");
    }

    public void iniciarMenu() {
        int opcion = -1;
        do {
            try {
                productoView.mostrarMenu();
                opcion = productoView.leerEntero("Ingresar una opcion: ");
                switch (opcion) {
                    case 1 -> agregarProducto();
                    case 2 -> eliminarProducto();
                    case 3 -> mostrarProductos();
                    case 4 -> mostrarProductoElegido();
                    case 5 -> actualizarProducto();
                    case 6 -> aumentarStock();
                    case 7 -> disminuirStock();
                    case 8 -> productoView.mostrarMensaje("Retornando al menu principal...");
                    default -> productoView.mostrarMensaje("Opcion invalida!");
                }
            }
            catch (PanquiException e) {
                productoView.mostrarError(e.getMessage());
            }
        }
        while (opcion != 8);
    }
}
