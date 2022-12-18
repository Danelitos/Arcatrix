package com.zetcode;

import java.sql.*;
import java.util.ArrayList;

public class GestorBD {
    private static GestorBD miBaseDeDatos;
    Connection con = null;

    private GestorBD() throws SQLException {
        //nos conectamos a la base de datos en phpmyadmin
        //String sURL = "jdbc:mysql://localhost/Tetris";
        //con = DriverManager.getConnection(sURL,"root","");
        String dir = System.getProperty("user.dir");
        System.out.println(dir);
        String sURL = "jdbc:h2:" + dir + "/src/main/resources/baseDatos/tetrisBD;DB_CLOSE_ON_EXIT=FALSE";
        con = DriverManager.getConnection(sURL, "admin", "");

        PreparedStatement sql = con.prepareStatement("RUNSCRIPT FROM 'bd.sql'");
        sql.execute();

        //AÑADIR USUARIO ADMIN SI NO EXISTE
        Statement s = con.createStatement();
        ResultSet rs = s.executeQuery("select * from Usuario WHERE Nombre='admin'");

        if (!rs.next()){
            sql = con.prepareStatement("INSERT INTO Usuario(Nombre,Email,Contraseña,CodigoPersonalizacion) Values('admin','','test',0)");
            sql.executeUpdate();
        }



    }


    public static GestorBD getInstance() throws SQLException {
        if (miBaseDeDatos == null) {
            miBaseDeDatos = new GestorBD();
        }
        return miBaseDeDatos;
    }

    public int insertPartida(int codUsuario, String nivel, int puntos,String fechaActual, String ladrillos) throws SQLException {

        PreparedStatement sql = con.prepareStatement("INSERT INTO Partida(codUsuario,nivel,puntos,listaLadrillos,fechaHora) VALUES(?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);

        sql.setInt(1, codUsuario);
        sql.setString(2, nivel);
        sql.setInt(3, puntos);
        sql.setString(4, ladrillos);
        sql.setString(5,fechaActual);

        sql.executeUpdate();

        Statement s = con.createStatement();
        //obtener codigo incremetnal que acaba de crear
        ResultSet generatedKeys = sql.getGeneratedKeys();
        generatedKeys.next();
        int codPartida = generatedKeys.getInt(1);

        System.out.println("codigo Partida: " + codPartida);
        //devuelve el codigo autoincremental de la base de datos
        return codPartida;
    }

    //TODO REGISTRO
    public boolean addUsuario(String nombreUsuario, String correo, String password,String pregunta, String repuesta) throws SQLException {
        int codigoPersonalizacion;
        int codigoUsu=0;
        Statement s = con.createStatement();
        ResultSet rs = s.executeQuery("select CodigoPersonalizacion from Usuario order by CodigoPersonalizacion desc limit 1");
        if (rs.next()) {
            codigoPersonalizacion = rs.getInt("CodigoPersonalizacion");
        } else {
            codigoPersonalizacion = 0;
        }

        PreparedStatement sql1 = con.prepareStatement("INSERT INTO DatosPersonalizacion(CodigoPersonalizacion,ColorFondo,ColorZSHAPE,ColorSSHAPE,ColorLINESHAPE,ColorSQUARESHAPE,ColorTSHAPE,ColorLSHAPE,ColorMIRROREDLSHAPE,Sonido) VALUES(?,?,?,?,?,?,?,?,?,?)");

        sql1.setInt(1, codigoPersonalizacion + 1);
        sql1.setString(2, "Classic Color");
        sql1.setString(3, "Classic Color");
        sql1.setString(4, "Classic Color");
        sql1.setString(5, "Classic Color");
        sql1.setString(6, "Classic Color");
        sql1.setString(7, "Classic Color");
        sql1.setString(8, "Classic Color");
        sql1.setString(9, "Classic Color");
        sql1.setString(10, "Tetris Original");

        sql1.executeUpdate();


        PreparedStatement sql3 = con.prepareStatement("INSERT INTO Usuario(Nombre,Email, Contraseña,CodigoPersonalizacion) VALUES(?,?,?,?)");

        sql3.setString(1, nombreUsuario);
        sql3.setString(2, correo);
        sql3.setString(3, password);
        sql3.setInt(4, codigoPersonalizacion + 1);

        sql3.executeUpdate();

        PreparedStatement sql = con.prepareStatement("SELECT Id FROM `Usuario` WHERE Nombre=?");
        sql.setString(1,nombreUsuario);
        ResultSet rs1=sql.executeQuery();
        if (rs1.next()) {
            codigoUsu = rs1.getInt("Id");
            System.out.println(codigoUsu);
        }

        PreparedStatement sql2 = con.prepareStatement("INSERT INTO RecuperarContrasena(CodigoUsu,Pregunta,Respuesta) VALUES(?,?,?)");

        sql2.setInt(1, codigoUsu);
        sql2.setString(2, pregunta);
        sql2.setString(3, repuesta);

        return sql2.executeUpdate() > 0 ? true : false;

    }


    public int verificarLogin(String usuarioLogin, String password) throws SQLException {
        int codigoUsu = 0;
        PreparedStatement sql = con.prepareStatement("select Id, Nombre, Contraseña from Usuario where Nombre=? and Contraseña=?");
        sql.setString(1, usuarioLogin);
        sql.setString(2, password);
        ResultSet rs = sql.executeQuery();
        if (rs.next()) {
            String nombre = rs.getString("Nombre");
            String contraseña = rs.getString("Contraseña");
            codigoUsu = rs.getInt("Id");

            //Dejar registrado quien esta en el tablero

        }

        return codigoUsu;
    }

    public boolean actualizarPersonalizacion(String colorFondo, String colorZSHAPE, String colorSSHAPE, String colorLINESHAPE, String colorTSHAPE, String colorSQUARESHAPE, String colorLSHAPE, String colorMIRROREDLSHAPE, String sonido, int codigoUsu) throws SQLException {
        int codigoPersonalizacion = 0;
        PreparedStatement sql1 = con.prepareStatement("select CodigoPersonalizacion from Usuario where Id=?");
        sql1.setInt(1, codigoUsu);
        ResultSet rs = sql1.executeQuery();
        if (rs.next()) {
            codigoPersonalizacion = rs.getInt("CodigoPersonalizacion");
            System.out.println(codigoPersonalizacion);
        }

        PreparedStatement sql2 = con.prepareStatement("Update DatosPersonalizacion set ColorFondo=?,ColorZSHAPE=?,ColorSSHAPE=?,ColorLINESHAPE=?,ColorSQUARESHAPE=?,ColorTSHAPE=?,ColorLSHAPE=?,ColorMIRROREDLSHAPE=?,Sonido=? where CodigoPersonalizacion=?");

        sql2.setString(1, colorFondo);
        sql2.setString(2, colorZSHAPE);
        sql2.setString(3, colorSSHAPE);
        sql2.setString(4, colorLINESHAPE);
        sql2.setString(5, colorSQUARESHAPE);
        sql2.setString(6, colorTSHAPE);
        sql2.setString(7, colorLSHAPE);
        sql2.setString(8, colorMIRROREDLSHAPE);
        sql2.setString(9, sonido);
        sql2.setInt(10, codigoPersonalizacion);
        return sql2.executeUpdate() > 0 ? true : false;
    }

    public int buscarUsuario(String nombreUsuario, String password) throws SQLException {
        int codUsu = 0;
        PreparedStatement sql = con.prepareStatement("select Id from Usuario where Nombre=? and Contraseña=?");
        sql.setString(1, nombreUsuario);
        sql.setString(2, password);
        ResultSet rs = sql.executeQuery();
        if (rs.next()) {
            codUsu = rs.getInt("Id");
            System.out.println(codUsu);
        }
        return codUsu;

    }

    public String obtColorPieza(String columna, int codigoUsu) throws SQLException {
        int codigoPersonalizacion = 0;
        PreparedStatement sql1 = con.prepareStatement("select CodigoPersonalizacion from Usuario where Id=?");
        sql1.setInt(1, codigoUsu);
        ResultSet rs1 = sql1.executeQuery();
        if (rs1.next()) {
            codigoPersonalizacion = rs1.getInt("CodigoPersonalizacion");
            System.out.println(codigoPersonalizacion);
        }

        String colorFondo = null;
        String colorZSHAPE = null;
        String colorSSHAPE = null;
        String colorLINESHAPE = null;
        String colorSQUARESHAPE = null;
        String colorTSHAPE = null;
        String colorLSHAPE = null;
        String colorMIRRROREDLSHAPE = null;
        String sonido = null;

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
        if (columna.equals("COLORFONDO")) {
            return colorFondo;
        } else if (columna.equals("COLORZSHAPE")) {
            return colorZSHAPE;
        } else if (columna.equals("COLORSSHAPE")) {
            return colorSSHAPE;
        } else if (columna.equals("COLORLINESHAPE")) {
            return colorLINESHAPE;
        } else if (columna.equals("COLORSQUARESHAPE")) {
            return colorSQUARESHAPE;
        } else if (columna.equals("COLORTSHAPE")) {
            return colorTSHAPE;
        } else if (columna.equals("COLORLSHAPE")) {
            return colorLSHAPE;
        } else if (columna.equals("COLORMIRROREDLSHAPE")) {
            return colorMIRRROREDLSHAPE;
        } else {
            return sonido;
        }

    }

    public void cargarPartidasUsuario(int codUsu) throws SQLException {

        Usuario usuario= GestorUsuarios.getInstance().buscarUsuario(codUsu);

        //COGER LAS PARTIDAS DEL USUARIO
        PreparedStatement sql1 = con.prepareStatement("select * from Partida where codUsuario=?");
        sql1.setInt(1, codUsu);
        ResultSet rs1 = sql1.executeQuery();
        Partida partida=null;
        while (rs1.next()){
            int codPartida= rs1.getInt("codPartida");
            int codUsuario= rs1.getInt("codUsuario");
            String nivel = rs1.getString("nivel");
            int puntos = rs1.getInt("puntos");
            String ladrillos= rs1.getString("listaLadrillos");
            String fecha= rs1.getString("fechaHora");

            //tenemos los datos

            //pasar de String a Tetrominoe[]
            Shape.Tetrominoe[] casillasOcupadas;
            String[] casillas = ladrillos.split(" ");

            casillasOcupadas = new com.zetcode.Shape.Tetrominoe[casillas.length];
            for (int i = 0; i < casillas.length; i++) {
                casillasOcupadas[i] = Shape.Tetrominoe.valueOf(casillas[i]);
            }

            //creamos la partida
            partida=new Partida(codPartida,nivel,codUsuario,casillasOcupadas,puntos);

            //creamos la partida guardada
            PartidaGuardada partGuardada= new PartidaGuardada(usuario,partida,fecha);

            usuario.getListaPartidasGuardadas().add(partGuardada);

        }
    }

    public ArrayList<Ranking> cargarRankings() throws SQLException{

        ArrayList<Ranking> result = new ArrayList<Ranking>();

        PreparedStatement sql = con.prepareStatement("Select IdUsr,NombreUsr,Nivel,Puntuacion from Ranking");
        ResultSet rs1 = sql.executeQuery();
        while (rs1.next()){
            int UdSur = rs1.getInt("IdUsr");
            String Nombre = rs1.getString("NombreUsr");
            String Nivel = rs1.getString("Nivel");
            int Puntuacion = rs1.getInt("Puntuacion");
            Ranking r = new Ranking(UdSur,Nombre,Puntuacion,Nivel);
            result.add(r);
        }

        return result;
    }

    public boolean anadirRanking(Ranking r) throws SQLException{

        int CodRanking=0;
        PreparedStatement sql1 = con.prepareStatement("select CodRanking from Ranking order by CodRanking desc limit 1");
        ResultSet rs1 = sql1.executeQuery();

        if (rs1.next()) {
            CodRanking = rs1.getInt("CodRanking") + 1;
        } else {
            CodRanking = 0;
        }


        PreparedStatement sql2 = con.prepareStatement("Insert into Ranking(CodRanking,IdUsr,NombreUsr,Nivel,Puntuacion) values(?,?,?,?,?)" );
        sql2.setInt(1,CodRanking);
        sql2.setInt(2, r.getIdUsr());
        sql2.setString(3, r.getNombreUsr());
        sql2.setString(4, r.getNivel());
        sql2.setInt(5, r.getPuntuacion());

        System.out.println("Ranking Guardado");
        return sql2.executeUpdate() > 0 ? true : false;
    }

    public String conseguirNombreUsr(int codUsr) throws SQLException{
        String nombre ="";

        PreparedStatement sql = con.prepareStatement("Select Nombre from Usuario where Id=?");
        sql.setInt(1,codUsr);
        ResultSet rs1 = sql.executeQuery();

        if (rs1.next()) {
            nombre= rs1.getString("Nombre");
        }


        return nombre;
    }

    public int conseguirIdConNombre(String nombreUsu) throws SQLException{
        int id =-1;

        PreparedStatement sql = con.prepareStatement("Select Id from Usuario where Nombre=?");
        sql.setString(1,nombreUsu);
        ResultSet rs1 = sql.executeQuery();

        if (rs1.next()) {
            id= rs1.getInt("Id");
        }

        return id;
    }

    public String recuperarContraseña(String correo,String preguntaVerificar,String respuestaVerificar) throws SQLException {
        int codigoUsu=0;
        String pregunta=null;
        String respuesta=null;
        String password=null;
        PreparedStatement sql = con.prepareStatement("SELECT Id FROM `Usuario` WHERE Email=?");
        sql.setString(1,correo);
        ResultSet rs1=sql.executeQuery();
        if (rs1.next()) {
            codigoUsu = rs1.getInt("Id");
            System.out.println(codigoUsu);
        }



        PreparedStatement sql1 = con.prepareStatement("SELECT * FROM `RecuperarContrasena` WHERE CodigoUsu=?");
        sql1.setInt(1,codigoUsu);
        ResultSet rs2=sql1.executeQuery();
        if (rs2.next()) {
            pregunta = rs2.getString("Pregunta");
            respuesta = rs2.getString("Respuesta");
            System.out.println(pregunta);
            System.out.println(respuesta);
        }

        if(pregunta.equals(preguntaVerificar) && respuesta.equals(respuestaVerificar)){
            PreparedStatement sql2 = con.prepareStatement("select Contraseña from Usuario WHERE Id=?");
            sql2.setInt(1,codigoUsu);
            ResultSet rs3=sql2.executeQuery();
            if (rs3.next()) {
                password = rs3.getString("Contraseña");
            }
        }
        return password;
    }

    public boolean eliminarUsuario(String usuario) throws SQLException{

        PreparedStatement sql = con.prepareStatement("DELETE FROM `Usuario` WHERE Nombre=?");
        sql.setString(1,usuario);

        return sql.executeUpdate() > 0 ? true : false;
        //if (rs1.next()) {
        //    usu = rs1.getString("Nombre");
        //    System.out.println(usu);
        // }


    }


}
