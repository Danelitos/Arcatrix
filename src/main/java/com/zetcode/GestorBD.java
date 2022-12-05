package com.zetcode;

import java.security.GeneralSecurityException;
import java.sql.*;

public class GestorBD {
    Connection con = null;
    public GestorBD() throws SQLException {

        //prueba para base de datos
        String sURL = "jdbc:mysql://localhost/Tetris";
        con = DriverManager.getConnection(sURL,"root","");

        PreparedStatement sql= con.prepareStatement("INSERT INTO Partida VALUES(?,?,?,?)");

        sql.setInt(1,3);
        sql.setInt(2,5);
        sql.setString(3,"dificil");
        sql.setString(4,"ladrillos");

        sql.executeUpdate();

    }

}
