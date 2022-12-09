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

    //TODO REGISTRO
    public boolean addUsuario(String nombreUsuario,String correo, String password) throws SQLException {
        int codigoPersonalizacion;
        Statement s = con.createStatement();
        ResultSet rs = s.executeQuery ("select CodigoPersonalizacion from Usuario order by CodigoPersonalizacion desc limit 1");
        if(rs.next()){
            codigoPersonalizacion=rs.getInt("CodigoPersonalizacion");
        }
        else {
            codigoPersonalizacion=0;
        }

        PreparedStatement sql1= con.prepareStatement("INSERT INTO DatosPersonalizacion(CodigoPersonalizacion,ColorFondo,ColorLadrillos,Sonido) VALUES(?,?,?,?)");

        sql1.setInt(1,codigoPersonalizacion+1);
        sql1.setString(2,"Azul");
        sql1.setString(3,"Azul");
        sql1.setString(4,"sonido");

        sql1.executeUpdate();

        PreparedStatement sql2= con.prepareStatement("INSERT INTO Usuario(Nombre,Email, Contraseña,CodigoPersonalizacion) VALUES(?,?,?,?)");

        sql2.setString(1,nombreUsuario);
        sql2.setString(2,correo);
        sql2.setString(3,password);
        sql2.setInt(4,codigoPersonalizacion+1);

        return sql2.executeUpdate()>0?true:false;

     }

     public int verificarLogin(String usuarioLogin, String password) throws SQLException {
         int codigoUsu=0;
         PreparedStatement sql= con.prepareStatement("select Id, Nombre, Contraseña from Usuario where Nombre=? and Contraseña=?");
         sql.setString(1,usuarioLogin);
         sql.setString(2,password);
         ResultSet rs=sql.executeQuery();
         if(rs.next()){
             String nombre=rs.getString("Nombre");
             String contraseña=rs.getString("Contraseña");
             codigoUsu=rs.getInt("Id");
         }
         return codigoUsu;
     }

     public boolean actualizarPersonalizacion(String colorFondo,String colorLadrillos,String sonido,int codigoUsu) throws SQLException {
         int codigoPersonalizacion = 0;
         PreparedStatement sql1= con.prepareStatement("select CodigoPersonalizacion from Usuario where Id=?");
         sql1.setInt(1,codigoUsu);
         ResultSet rs=sql1.executeQuery();
         if(rs.next()){
             codigoPersonalizacion=rs.getInt("CodigoPersonalizacion");
             System.out.println(codigoPersonalizacion);
         }

         PreparedStatement sql2= con.prepareStatement("Update DatosPersonalizacion set ColorFondo=?,ColorLadrillos=?,Sonido=? where CodigoPersonalizacion=?");

         sql2.setString(1,colorFondo);
         sql2.setString(2,colorLadrillos);
         sql2.setString(3,sonido);
         sql2.setInt(4,codigoPersonalizacion);
         return sql2.executeUpdate()>0?true:false;
     }

    public int buscarUsuario(String nombreUsuario,String password) throws SQLException {
        int codUsu=0;
        PreparedStatement sql = con.prepareStatement("select Id from Usuario where Nombre=? and Contraseña=?");
        sql.setString(1,nombreUsuario);
        sql.setString(2,password);
        ResultSet rs=sql.executeQuery();
        if(rs.next()){
            codUsu=rs.getInt("Id");
            System.out.println(codUsu);
        }
        return codUsu;

    }


}
