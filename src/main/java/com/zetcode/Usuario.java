package com.zetcode;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import java.util.ArrayList;
import java.util.Iterator;

public class Usuario {
    private int codUsuario;
    private String nombre;
    private String password;
    private DatosPersonalizacion personalizacion;

    private ArrayList<PartidaGuardada> listaPartidasGuardadas = new ArrayList<PartidaGuardada>();
    private Partida enJuego;


    public Usuario(int pCodUsuario,String pNombre,String pPassword,DatosPersonalizacion pPersonalizacion, ArrayList<PartidaGuardada> pLista, Partida pEnJuego){
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

    public ArrayList<PartidaGuardada> partidasGuardadasUsuario(){
        Iterator<PartidaGuardada> itr = this.getIterador();
        PartidaGuardada partida = null;
        ArrayList<PartidaGuardada> listaPartidas = new ArrayList<>();
        while (itr.hasNext()) {
            partida = itr.next();
            listaPartidas.add(partida);
        }
        return listaPartidas;
    }

    private Iterator<PartidaGuardada> getIterador() {
        return (this.listaPartidasGuardadas.iterator());
    }

    public void asignarPartida(Partida laPartida){
        this.enJuego = laPartida;
    }

    public void eliminarPartidaAsignada(int codPartida){
        if(codPartida == this.enJuego.getCodPartida()){this.enJuego = null;}}

    public Partida getEnJuego(){return enJuego;}
}
