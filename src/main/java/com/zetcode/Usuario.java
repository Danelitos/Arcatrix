package com.zetcode;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

public class Usuario {
    private int codUsuario;
    private String nombre;
    private String password;
    private DatosPersonalizacion personalizacion;

    private ArrayList<PartidaGuardada> listaPartidasGuardadas = new ArrayList<PartidaGuardada>();
    private Tetris enJuego;


    public Usuario(int pCodUsuario,String pNombre,String pPassword,DatosPersonalizacion pPersonalizacion, ArrayList<PartidaGuardada> pLista, Tetris pEnJuego){
        codUsuario=pCodUsuario;
        nombre=pNombre;
        password=pPassword;
        personalizacion=pPersonalizacion;
        listaPartidasGuardadas = new ArrayList<PartidaGuardada>();
        enJuego = null;
    }

    public boolean esUsuario(String pNombre,String pPassword){
        return nombre==pNombre && password==pPassword;
    }

    public boolean esUsuario(String pNombre){
        return nombre==pNombre;
    }

    public void actualizarDatos(String pColorFondo,String pColorLadrillos,String pSonido){
        personalizacion.insertatDatos(pColorFondo,pColorLadrillos,pSonido);
    }

    public String getNombre() {
        return nombre;
    }

    //TODO TERMINAR
    public void añadir(Usuario elsuario,PartidaGuardada pPartidaGuardada){

    }

    //TODO TERMINAR
    /*public int obtCodYFechaPartida(int pCodPartida, Date pFechaHora){
        for(PartidaGuardada partidaG:pa){

        }
    }*/

    public int getCodUsuario(){return this.codUsuario;}

    public ArrayList<PartidaGuardada> getListaPartidasGuardadas() {
        return listaPartidasGuardadas;
    }

    public JsonArray partidasGuardadasUsuario(){
        Iterator<PartidaGuardada> itr = this.getIterador();
        PartidaGuardada partida = null;
        int i = 0;
        JsonArray array = new JsonArray();
        while (itr.hasNext()) {
            partida = itr.next();
            Date fechaHora = partida.obtFechaHora();
            String s = fechaHora.toString();
            //i++;
            array.add(s);
        }
        return array;
    }

    private Iterator<PartidaGuardada> getIterador() {
        return (this.listaPartidasGuardadas.iterator());
    }

    public void asignarPartida(Tetris laPartida){
        this.enJuego = laPartida;
    }

    public void eliminarPartidaAsignada(int codPartida){
        if(codPartida == this.enJuego.getCodPartida()){this.enJuego = null;}}

    public Tetris getEnJuego(){return enJuego;}

    public Tetris obtPartida(String fechaHora){
        Iterator<PartidaGuardada> itr = this.getIterador();
        PartidaGuardada partidaGuardada = null;
        Tetris partida = null;
        boolean enc = false;
        while (itr.hasNext() && !enc){
            partidaGuardada = itr.next();
            if (partidaGuardada.obtFechaHora().toString().equals(fechaHora)){
                enc = true;
                partida = partidaGuardada.getLaPartida();
            }
        }
        return partida;
    }
}
