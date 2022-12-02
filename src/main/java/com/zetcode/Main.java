package com.zetcode;

import vista.VentanaLogin;

import java.awt.*;

public class Main {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            VentanaLogin.getInstance();
        });
    }
}
