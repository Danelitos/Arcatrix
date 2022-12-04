package com.zetcode;

import java.util.ArrayList;
import java.util.Date;

public class Usuario {
    private int codUsuario;
    private String nombre;
    private String password;
    private DatosPersonalizacion personalizacion;

    private ArrayList<PartidaGuardada> listaPartidasGuardadas = new ArrayList<PartidaGuardada>();

    public Usuario(int pCodUsuario,String pNombre,String pPassword,DatosPersonalizacion pPersonalizacion){
        codUsuario=pCodUsuario;
        nombre=pNombre;
        password=pPassword;
        personalizacion=pPersonalizacion;
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
}
