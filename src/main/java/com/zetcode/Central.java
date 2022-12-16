package com.zetcode;

import com.google.gson.JsonArray;

import java.sql.SQLException;

public class Central {

    private static Central miCentral;

    public static Central getInstance() {
        if (miCentral == null) miCentral = new Central();
        return miCentral;
    }

    public void crearUsuario(int codUsu, String nombre, String password) {
        GestorUsuarios.getInstance().crearUsuario(codUsu, nombre, password);
    }

    public void recuperarContrasena(String correo) {

    }

    public void actualizarDatosPersonaliza(int codUsu, String colorFondo, String colorZHSAPE, String colorSSHAPE, String colorLINESHAPE, String colorTSHAPE, String colorSQUARESHAPE, String colorLSHAPE, String colorMIRROREDLSHAPE, String sonido) {
        GestorUsuarios.getInstance().actualizarDatosPersonaliza(codUsu, colorFondo, colorZHSAPE, colorSSHAPE, colorLINESHAPE, colorSQUARESHAPE, colorTSHAPE, colorLSHAPE, colorMIRROREDLSHAPE, sonido);
    }

    public void guardarPartida(int codPartida, String nivel, int codUsuario, Shape.Tetrominoe[] board,int puntos, String fechaActual) {
        Usuario user = GestorUsuarios.getInstance().buscarUsuario(codUsuario);
        if (user != null) {
            if (board != null && fechaActual != null) {
                Partida laPartida = GestorUsuarios.getInstance().crearPartida(codPartida, nivel, codUsuario, board, puntos);
                PartidaGuardada partidaCreada = GestorUsuarios.getInstance().crearPartidaGuardada(laPartida, user, fechaActual);
                GestorUsuarios.getInstance().anadirPartidaGuardada(user, partidaCreada);
            }
            else {System.out.println("ERROR, el tablero y/o la fecha es null");}
        }
        else {System.out.println("ERROR, no existe el usuario");}

        //el tamaño maximo del varchar en la BASE DATOS es 8960
        // el caso mas extremo es que sea nivel dificil (640 casillas y todos MirroredLShape)
    }

    public JsonArray obtPartidasGuardadas(int codUsuario) {
        JsonArray arrayJson = null;
        Usuario user = GestorUsuarios.getInstance().buscarUsuario(codUsuario);
        if(user != null) {
            arrayJson = GestorUsuarios.getInstance().partidasGuardadas(user);
        }
        return arrayJson;
    }

    public JsonArray cargarPartida(int codUsuario, String fechaHora) {
        Usuario user = GestorUsuarios.getInstance().buscarUsuario(codUsuario);
        JsonArray partida = new JsonArray();
        if (user != null) {
            if (fechaHora != null) {
                partida = GestorUsuarios.getInstance().obtenerPartida(user,fechaHora);
            }
            else {System.out.println("ERROR, la fecha es null");}
        }
        else {System.out.println("ERROR, no existe el usuario");}
        return partida;
    }

    public String[] obtLadrillos(int codUsuario, int codPartida) {
        Usuario user = GestorUsuarios.getInstance().buscarUsuario(codUsuario);
        String[] array = null;
        if (user != null) {
            array = GestorUsuarios.getInstance().getBoardUsuario(user,codPartida);
        }
        else {System.out.println("ERROR, el usuario no existe");}
        return array;
    }

    public DatosPersonalizacion obtPersonalizacion(int codUsu) {
        return GestorUsuarios.getInstance().obtPersonalizacion(codUsu);
    }

    public String pasarLadrillosTexto(Shape.Tetrominoe[] tablero){
        String tableroTexto= "";
        for (int i=0; i<tablero.length; i++){
            if (i==tablero.length -1) {
                tableroTexto = tableroTexto + tablero[i];
            }
            else{
                tableroTexto = tableroTexto + tablero[i] + " ";
            }

        }
        return tableroTexto;
    }

    public void cargarPartidasGuardadas(int codUsu) throws SQLException {
        GestorBD.getInstance().cargarPartidasUsuario(codUsu);
    }

    public void iniciarPartida(int codigoUsuario, int codPartida, String nivelElegido, Shape.Tetrominoe[] casillasOcupadas, int puntos){
        try {
            new Tetris(codigoUsuario,codPartida,nivelElegido,casillasOcupadas ,puntos);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int guardarPartidaEnBD(int codUsu, String nivel, int puntos, String fecha, String ladrillos) throws SQLException {
        int codPartida = GestorBD.getInstance().insertPartida(codUsu,nivel,puntos,fecha,ladrillos);
        return codPartida;
    }
}
