package com.zetcode;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;

import java.util.ArrayList;

public class Central {
    public void iniciarSesion(String nombre, String password){

    }

    public void crearUsuario(String nombre, String correo, String password, String repetirPassword){

    }

    public void recuperarContrasena(String correo){

    }

    public void actualizarDatos(){

    }

    public void guardarPartida(int codUsuario, int codPartida){
        Usuario user = GestorUsuarios.getInstance().buscarUsuario(codUsuario);
        if(user.getEnJuego().getCodPartida() == codPartida) {
            PartidaGuardada partidaCreada = GestorUsuarios.getInstance().crearPartidaGuardada(user.getEnJuego(), user);
            GestorUsuarios.getInstance().anadirPartidaGuardada(user, partidaCreada);
            //FALTA LO DEL SGBD
        }
        user.partidasGuardadasUsuario();
    }

    public JsonArray obtPartidasGuardadas(int codUsuario){
        Usuario user = GestorUsuarios.getInstance().buscarUsuario(codUsuario);
        //Devolvemos un array de Json pero los convertimos aquí
        ArrayList<PartidaGuardada> miLista = GestorUsuarios.getInstance().partidasGuardadas(user); // list filled with objects
        Gson json = new GsonBuilder().create();
        JsonArray arrayJson = json.toJsonTree(miLista).getAsJsonArray();
        return arrayJson;
    }

    public void cargarPartida(int codUsuario, int codPartida, String fechaHora){

    }

    public boolean obtPersonalizacion(int codUsu){
        return false;
    }
}
