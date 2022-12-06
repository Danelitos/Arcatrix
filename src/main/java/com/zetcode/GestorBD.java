package com.zetcode;

import java.security.GeneralSecurityException;
import java.sql.*;

public class GestorBD {
    Connection con = null;
    public GestorBD() throws SQLException {
        //nos conectamos a la base de datos en phpmyadmin
        String sURL = "jdbc:mysql://localhost/Tetris";
        con = DriverManager.getConnection(sURL,"root","");
    }
    public int insertPartida(int codUsuario, String nivel, int puntos) throws SQLException {

        PreparedStatement sql= con.prepareStatement("INSERT INTO Partida(codUsuario,nivel,puntos,listaLadrillos) VALUES(?,?,?,?)");

        sql.setInt(1,codUsuario);
        sql.setString(2,nivel);
        sql.setInt(3,0);
        sql.setString(4,"ladrillos");

        sql.executeUpdate();

        Statement s = con.createStatement();
        //obtener codigo incremetnal que acaba de crear
        ResultSet rs = s.executeQuery ("select last_insert_id()");
        rs.next();
        int codPartida=rs.getInt(1);

        System.out.println("codigo Partida: " + codPartida);
        //devuelve el codigo autoincremental de la base de datos
        return codPartida;
    }

}
