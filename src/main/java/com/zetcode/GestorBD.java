package com.zetcode;

import java.security.GeneralSecurityException;
import java.sql.*;

public class GestorBD {
    private static GestorBD miBaseDeDatos;
    Connection con = null;
    private GestorBD() throws SQLException {
        //nos conectamos a la base de datos en phpmyadmin
        //String sURL = "jdbc:mysql://localhost/Tetris";
        //con = DriverManager.getConnection(sURL,"root","");
        String dir= System.getProperty("user.dir");
        System.out.println(dir);
        String sURL = "jdbc:h2:"+ dir + "/src/main/resources/baseDatos/tetrisBD";
        con = DriverManager.getConnection(sURL,"admin","");

        PreparedStatement sql= con.prepareStatement("RUNSCRIPT FROM 'bd.sql'");
        sql.execute();

        //pruebah2();
    }
    private void pruebah2() throws SQLException {
        PreparedStatement sql= con.prepareStatement("RUNSCRIPT FROM 'bd.sql'");
        sql.execute();

        sql= con.prepareStatement("INSERT INTO Usuario(Nombre,Email,Contraseña,CodigoPersonalizacion) VALUES(?,?,?,?)",Statement.RETURN_GENERATED_KEYS);

        sql.setString(1,"hola");
        sql.setString(2,"jeje");
        sql.setString(3,"123");
        sql.setInt(4,4);


        sql.executeUpdate();
        ResultSet generatedKeys = sql.getGeneratedKeys();
        generatedKeys.next();
        int codUsuario= generatedKeys.getInt(1);
        System.out.println(codUsuario);
        Statement s = con.createStatement();
        ResultSet rs = s.executeQuery ("select nombre from Usuario where id=1");

        rs.next();
        String nombre=rs.getString(1);

        System.out.println("nombre: " + nombre);

        //exportar la bd para PROBAR
        //sql= con.prepareStatement("SCRIPT TO 'bd2.sql'");
        //sql.execute();

    }


    public static GestorBD getInstance() throws SQLException {
        if(miBaseDeDatos==null){
            miBaseDeDatos=new GestorBD();
        }
        return miBaseDeDatos;
    }

    public int insertPartida(int codUsuario, String nivel, int puntos) throws SQLException {

        PreparedStatement sql= con.prepareStatement("INSERT INTO Partida(codUsuario,nivel,puntos,listaLadrillos) VALUES(?,?,?,?)",Statement.RETURN_GENERATED_KEYS);

        sql.setInt(1,codUsuario);
        sql.setString(2,nivel);
        sql.setInt(3,0);
        sql.setString(4,"ladrillos");

        sql.executeUpdate();

        Statement s = con.createStatement();
        //obtener codigo incremetnal que acaba de crear
        ResultSet generatedKeys = sql.getGeneratedKeys();
        generatedKeys.next();
        int codPartida= generatedKeys.getInt(1);

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

        PreparedStatement sql1= con.prepareStatement("INSERT INTO DatosPersonalizacion(CodigoPersonalizacion,ColorFondo,ColorZSHAPE,ColorSSHAPE,ColorLINESHAPE,ColorSQUARESHAPE,ColorTSHAPE,ColorLSHAPE,ColorMIRROREDLSHAPE,Sonido) VALUES(?,?,?,?,?,?,?,?,?,?)");

        sql1.setInt(1,codigoPersonalizacion+1);
        sql1.setString(2,"Classic Color");
        sql1.setString(3,"Classic Color");
        sql1.setString(4,"Classic Color");
        sql1.setString(5,"Classic Color");
        sql1.setString(6,"Classic Color");
        sql1.setString(7,"Classic Color");
        sql1.setString(8,"Classic Color");
        sql1.setString(9,"Classic Color");
        sql1.setString(10,"sonido");

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

     public boolean actualizarPersonalizacion(String colorFondo,String colorZSHAPE, String colorSSHAPE, String colorLINESHAPE,String colorTSHAPE, String colorSQUARESHAPE,String colorLSHAPE,String colorMIRROREDLSHAPE,String sonido,int codigoUsu) throws SQLException {
         int codigoPersonalizacion = 0;
         PreparedStatement sql1= con.prepareStatement("select CodigoPersonalizacion from Usuario where Id=?");
         sql1.setInt(1,codigoUsu);
         ResultSet rs=sql1.executeQuery();
         if(rs.next()){
             codigoPersonalizacion=rs.getInt("CodigoPersonalizacion");
             System.out.println(codigoPersonalizacion);
         }

         PreparedStatement sql2= con.prepareStatement("Update DatosPersonalizacion set ColorFondo=?,ColorZSHAPE=?,ColorSSHAPE=?,ColorLINESHAPE=?,ColorSQUARESHAPE=?,ColorTSHAPE=?,ColorLSHAPE=?,ColorMIRROREDLSHAPE=?,Sonido=? where CodigoPersonalizacion=?");

         sql2.setString(1,colorFondo);
         sql2.setString(2,colorZSHAPE);
         sql2.setString(3,colorSSHAPE);
         sql2.setString(4,colorLINESHAPE);
         sql2.setString(5,colorSQUARESHAPE);
         sql2.setString(6,colorTSHAPE);
         sql2.setString(7,colorLSHAPE);
         sql2.setString(8,colorMIRROREDLSHAPE);
         sql2.setString(9,sonido);
         sql2.setInt(10,codigoPersonalizacion);
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

    public String obtColorPieza(String columna,int codigoUsu) throws SQLException {
        int codigoPersonalizacion = 0;
        PreparedStatement sql1 = con.prepareStatement("select CodigoPersonalizacion from Usuario where Id=?");
        sql1.setInt(1, codigoUsu);
        ResultSet rs1 = sql1.executeQuery();
        if (rs1.next()) {
            codigoPersonalizacion = rs1.getInt("CodigoPersonalizacion");
            System.out.println(codigoPersonalizacion);
        }

        String colorFondo=null;
        String colorZSHAPE=null;
        String colorSSHAPE=null;
        String colorLINESHAPE=null;
        String colorSQUARESHAPE=null;
        String colorTSHAPE=null;
        String colorLSHAPE=null;
        String colorMIRRROREDLSHAPE=null;
        String sonido=null;

        PreparedStatement sql2 = con.prepareStatement("select * from DatosPersonalizacion where CodigoPersonalizacion=?");
        sql2.setInt(1, codigoPersonalizacion);
        ResultSet rs2 = sql2.executeQuery();
        if (rs2.next()) {
            colorFondo = rs2.getString("COLORFONDO");
            colorZSHAPE = rs2.getString("COLORZSHAPE");
            colorSSHAPE = rs2.getString("COLORSSHAPE");
            colorLINESHAPE = rs2.getString("COLORLINESHAPE");
            colorSQUARESHAPE = rs2.getString("COLORSQUARESHAPE");
            colorTSHAPE = rs2.getString("COLORTSHAPE");
            colorLSHAPE = rs2.getString("COLORLSHAPE");
            colorMIRRROREDLSHAPE = rs2.getString("COLORMIRROREDLSHAPE");
            sonido = rs2.getString("SONIDO");
            System.out.println(codigoPersonalizacion);
        }
        if(columna.equals("COLORFONDO")){
            return colorFondo;
        }
        else if (columna.equals("COLORZSHAPE")){
            return colorZSHAPE;
        }
        else if (columna.equals("COLORSSHAPE")){
            return colorSSHAPE;
        }
        else if (columna.equals("COLORLINESHAPE")){
            return colorLINESHAPE;
        }
        else if (columna.equals("COLORSQUARESHAPE")){
            return colorSQUARESHAPE;
        }
        else if (columna.equals("COLORTSHAPE")){
            return colorTSHAPE;
        }
        else if (columna.equals("COLORLSHAPE")){
            return colorLSHAPE;
        }
        else if (columna.equals("COLORMIRROREDLSHAPE")){
            return colorMIRRROREDLSHAPE;
        }
        else {
            return sonido;
        }

    }


}
