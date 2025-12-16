package com.pedidospanqui.repository;

import com.pedidospanqui.model.Pedido;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PedidoRepository {
    private final List<Pedido> pedidos = new ArrayList<>();

    public void guardar(Pedido pedido) {
        pedidos.add(pedido);
    }

    public Pedido encontrarPorId(int id) {
        for (Pedido p : pedidos) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    public List<Pedido> encontrarTodos() {
        return Collections.unmodifiableList(pedidos);
    }

    public void borrarPorId(int id) {
        Pedido pedido = encontrarPorId(id);
        pedidos.remove(pedido);
    }
}
