package com.zetcode;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Central {

    private static Central miCentral;

    public static Central getInstance() {
        if (miCentral == null) miCentral = new Central();
        return miCentral;
    }

    public void crearUsuario(int codUsu, String nombre, String password, String repetirPassword) {
        GestorUsuarios.getInstance().crearUsuario(codUsu, nombre, password);

    }

    public void recuperarContrasena(String correo) {

    }

    public void actualizarDatosPersonaliza(int codUsu, String colorFondo, String colorZHSAPE, String colorSSHAPE, String colorLINESHAPE, String colorTSHAPE, String colorSQUARESHAPE, String colorLSHAPE, String colorMIRROREDLSHAPE, String sonido) {
        GestorUsuarios.getInstance().actualizarDatosPersonaliza(codUsu, colorFondo, colorZHSAPE, colorSSHAPE, colorLINESHAPE, colorSQUARESHAPE, colorTSHAPE, colorLSHAPE, colorMIRROREDLSHAPE, sonido);
    }

    public void guardarPartida(int codUsuario, Tetris laPartida, Date fechaActual) {
        Usuario user = GestorUsuarios.getInstance().buscarUsuario(codUsuario);
        PartidaGuardada partidaCreada = GestorUsuarios.getInstance().crearPartidaGuardada(laPartida, user,fechaActual);
        GestorUsuarios.getInstance().anadirPartidaGuardada(user, partidaCreada);
        //FALTA LO DEL SGBD
        user.partidasGuardadasUsuario();
        Shape.Tetrominoe[] tablero= laPartida.getCasillasOcupadas();
        //System.out.println("tamaño tablero guardado: " + tablero.length);

        //pasar del tablero al string
        //System.out.println("pos 0 del tablero: " + tablero[0]);
        String tableroTexto= "";
        for (int i=0; i<tablero.length; i++){
            if (i==tablero.length -1) {
                tableroTexto = tableroTexto + tablero[i];
            }
            else{
                tableroTexto = tableroTexto + tablero[i] + " ";
            }

        }
        //System.out.println(tableroTexto);
        //String[] casillas = tableroTexto.split(" ");
        //System.out.println(Arrays.toString(casillas));
        //System.out.println(casillas.length);

        //el tamaño maximo del varchar en la BASE DATOS es 8960
        // el caso mas extremo es que sea nivel dificil (640 casillas y todos MirroredLShape)
    }

    public JsonArray obtPartidasGuardadas(int codUsuario) {
        Usuario user = GestorUsuarios.getInstance().buscarUsuario(codUsuario);
        JsonArray arrayJson = GestorUsuarios.getInstance().partidasGuardadas(user);
        return arrayJson;
    }

    public JsonArray cargarPartida(int codUsuario, String fechaHora) {
        Usuario user = GestorUsuarios.getInstance().buscarUsuario(codUsuario);
        JsonArray partida = user.obtPartida(fechaHora);
        return partida;
    }

    public String[] obtLadrillos(int codUsuario, int codPartida) {
        Usuario user = GestorUsuarios.getInstance().buscarUsuario(codUsuario);
        String[] array = user.getBoard(codPartida);
        return array;
    }

    public boolean obtPersonalizacion(int codUsu) {
        return GestorUsuarios.getInstance().obtPersonalizacion(codUsu);
    }
}
