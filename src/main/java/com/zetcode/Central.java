package com.zetcode;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;

import java.util.ArrayList;
import java.util.List;

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
        JsonArray arrayJson = GestorUsuarios.getInstance().partidasGuardadas(user);
        System.out.println(arrayJson.size());
        //Probando
        List<String> exampleList = new ArrayList<String>();
        for (int i = 0; i < arrayJson.size(); i++) {
            exampleList.add(arrayJson.get(i).getAsString());
        }
        int size = exampleList.size();
        String[] stringArray = exampleList.toArray(new String[size]);
        for (int i = 0; i < arrayJson.size(); i++) {
            System.out.println(stringArray[i]);
            System.out.println("He hecho algo");
        }
        return arrayJson;
    }

    public void cargarPartida(int codUsuario, int codPartida, String fechaHora){

    }

    public boolean obtPersonalizacion(int codUsu){
        return false;
    }
}
