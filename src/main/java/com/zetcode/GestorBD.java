package com.zetcode;

import java.security.GeneralSecurityException;
import java.sql.*;

public class GestorBD {
    private static GestorBD miBaseDeDatos;
    Connection con = null;
    private GestorBD() throws SQLException {
        //nos conectamos a la base de datos en phpmyadmin
        String sURL = "jdbc:mysql://localhost/Tetris";
        con = DriverManager.getConnection(sURL,"root","");
    }

    public static GestorBD getInstance() throws SQLException {
        if(miBaseDeDatos==null){
            miBaseDeDatos=new GestorBD();
        }
        return miBaseDeDatos;
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

    //TODO AÑADIR REGISTRO
    public boolean addUsuario(String nombreUsuario,String correo, String password) throws SQLException {
        int codigoPersonalizacion;
        Statement s = con.createStatement();
        ResultSet rs = s.executeQuery ("select CodigoPersonalizacion from usuario order by CodigoPersonalizacion desc limit 1");
        if(rs.next()){
            codigoPersonalizacion=rs.getInt("CodigoPersonalizacion");
        }
        else {
            codigoPersonalizacion=1;
        }

        PreparedStatement sql= con.prepareStatement("INSERT INTO Usuario(Nombre,Email, Contraseña,CodigoPersonalizacion) VALUES(?,?,?,?)");

        sql.setString(1,nombreUsuario);
        sql.setString(2,correo);
        sql.setString(3,password);
        sql.setInt(4,codigoPersonalizacion);

        return sql.executeUpdate()>0?true:false;

     }


}
