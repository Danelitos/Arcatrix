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
    public void iniciarSesion(String nombre, String password){

    }

    public void crearUsuario(int codUsu, String nombre, String password, String repetirPassword){
        GestorUsuarios.getInstance().crearUsuario(codUsu,nombre,password);

    }

    public void recuperarContrasena(String correo){

    }

    public void actualizarDatos(){

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

    public Tetris cargarPartida(int codUsuario, String fechaHora){
        Usuario user = GestorUsuarios.getInstance().buscarUsuario(codUsuario);
        Tetris partida = user.obtPartida(fechaHora);
        return partida;
    }

    public boolean obtPersonalizacion(int codUsu){
        return false;
    }
}
