package com.zetcode;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;

import java.util.ArrayList;
import java.util.List;

public class Central {

    private static Central miCentral;

    public static Central getInstance(){
        if(miCentral == null) miCentral = new Central();
        return miCentral;
    }

    public void crearUsuario(int codUsu, String nombre, String password, String repetirPassword){
        GestorUsuarios.getInstance().crearUsuario(codUsu,nombre,password);

    }

    public void recuperarContrasena(String correo){

    }

    public void actualizarDatosPersonaliza(int codUsu,String colorFondo,String colorZHSAPE, String colorSSHAPE, String colorLINESHAPE, String colorTSHAPE, String colorSQUARESHAPE, String colorLSHAPE, String colorMIRROREDLSHAPE,String sonido){
        GestorUsuarios.getInstance().actualizarDatosPersonaliza(codUsu,colorFondo,colorZHSAPE,colorSSHAPE,colorLINESHAPE,colorSQUARESHAPE,colorTSHAPE,colorLSHAPE,colorMIRROREDLSHAPE,sonido);
    }

    public void guardarPartida(int codUsuario, Tetris laPartida){
        Usuario user = GestorUsuarios.getInstance().buscarUsuario(codUsuario);
        PartidaGuardada partidaCreada = GestorUsuarios.getInstance().crearPartidaGuardada(laPartida, user);
        GestorUsuarios.getInstance().anadirPartidaGuardada(user, partidaCreada);
        //FALTA LO DEL SGBD
        user.partidasGuardadasUsuario();
    }

    public JsonArray obtPartidasGuardadas(int codUsuario){
        Usuario user = GestorUsuarios.getInstance().buscarUsuario(codUsuario);
        JsonArray arrayJson = GestorUsuarios.getInstance().partidasGuardadas(user);
        return arrayJson;
    }

    public JsonArray cargarPartida(int codUsuario, String fechaHora){
        Usuario user = GestorUsuarios.getInstance().buscarUsuario(codUsuario);
        JsonArray partida = user.obtPartida(fechaHora);
        return partida;
    }
    public String[] obtLadrillos(int codUsuario,int codPartida){
        Usuario user = GestorUsuarios.getInstance().buscarUsuario(codUsuario);
        String[] array = user.getBoard(codPartida);
        return array;
    }

    public boolean obtPersonalizacion(int codUsu){
        return GestorUsuarios.getInstance().obtPersonalizacion(codUsu);
    }
}
