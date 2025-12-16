package com.pedidospanqui.model;

import com.pedidospanqui.exception.business.ItemPedidoInvalidoException;
import com.pedidospanqui.exception.business.NoEncontradoException;
import com.pedidospanqui.exception.business.PedidoInvalidoException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Pedido {
    private static int generadorId = 0;
    private final int id;
    private final List<ItemPedido> items;

    public Pedido() {
        id = ++generadorId;
        items = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public List<ItemPedido> getItems() {
        return Collections.unmodifiableList(items);
    }

    public void agregarItem(ItemPedido item) {
        if (item == null) {
            throw new ItemPedidoInvalidoException("El item no puede ser null");
        }
        if (item.getCantidad() <= 0) {
            throw new ItemPedidoInvalidoException("La cantidad debe ser mayor a 0");
        }
        ItemPedido existente = seleccionarItemPedido(item.getProducto().getId());
        if (existente != null) {
            throw new ItemPedidoInvalidoException("El producto ya existe en el pedido. Use actualizar cantidad");
        }
        items.add(item);
    }

    public void eliminarItem(ItemPedido item) {
        if (item == null) {
            throw new ItemPedidoInvalidoException("El item no puede ser null");
        }
        if (!items.remove(item)) {
            throw new NoEncontradoException("Item", item.getProducto().getId());
        }
    }

    public ItemPedido seleccionarItemPedido(int idProducto) {
        if (idProducto <= 0) {
            throw new ItemPedidoInvalidoException("ID de producto invalido");
        }
        for (ItemPedido item : items) {
            if (item.getProducto().getId() == idProducto) {
                return item;
            }
        }
        return null;
    }

    public void vaciar() {
        if (items.isEmpty()) {
            throw new PedidoInvalidoException("El pedido ya esta vacio");
        }
        Iterator<ItemPedido> it = items.iterator();
        while (it.hasNext()) {
            it.next();
            it.remove();
        }
    }

    public int contarCantidadItemsDiferentes() {
        return items.size();
    }

    public int contarCantidadItemsTotales() {
        int cantidadItems = 0;
        for (ItemPedido item : items) {
            cantidadItems += item.getCantidad();
        }
        return cantidadItems;
    }

    public double calcularTotal() {
        double total = 0;
        for (ItemPedido item : items) {
            total += item.calcularSubTotal();
        }
        return total;
    }

    @Override
    public String toString() {
        return String.format("Pedido(id=%d, items=%s)", id, items);
    }
}
