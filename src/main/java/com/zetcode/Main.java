package com.zetcode;

import vista.*;

import java.awt.*;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        //new GestorBD();
        EventQueue.invokeLater(() -> {
            VentanaLogin.getInstance();
        });
    }
}
