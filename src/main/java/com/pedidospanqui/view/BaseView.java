package com.pedidospanqui.view;

import java.util.Scanner;

public abstract class BaseView {
    protected Scanner scanner = new Scanner(System.in);

    public abstract void mostrarMenu();

    public int leerEntero(String msg) {
        System.out.print(msg);
        int valor = scanner.nextInt();
        scanner.nextLine();
        return valor;
    }

    public String leerCadena(String msg) {
        System.out.print(msg);
        return scanner.nextLine();
    }

    public void mostrarMensaje(String msg) {
        System.out.println(msg);
    }

    public void mostrarError(String msg) {
        System.err.println(msg);
    }
}
