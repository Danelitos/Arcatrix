package com.zetcode;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;

public class Usuario {
    private int codUsuario;
    private String nombre;
    private String password;
    private DatosPersonalizacion personalizacion;

    private ArrayList<PartidaGuardada> listaPartidasGuardadas;


    public Usuario(int pCodUsuario,String pNombre,String pPassword,DatosPersonalizacion pPersonalizacion){
        codUsuario=pCodUsuario;
        nombre=pNombre;
        password=pPassword;
        personalizacion=pPersonalizacion;
        listaPartidasGuardadas = new ArrayList<PartidaGuardada>();
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

    public int getCodUsuario(){return this.codUsuario;}

    public ArrayList<PartidaGuardada> getListaPartidasGuardadas() {
        return listaPartidasGuardadas;
    }

    public JsonArray partidasGuardadasUsuario(){
        Iterator<PartidaGuardada> itr = this.getIterador();
        PartidaGuardada partida = null;
        JsonArray array = new JsonArray();
        while (itr.hasNext()) {
            partida = itr.next();
            Date fechaHora = partida.obtFechaHora();
            String s = fechaHora.toString();
            array.add(s);
        }
        return array;
    }

    private Iterator<PartidaGuardada> getIterador() {
        return (this.listaPartidasGuardadas.iterator());
    }

    public JsonArray obtPartida(String fechaHora){
        Iterator<PartidaGuardada> itr = this.getIterador();
        PartidaGuardada partidaGuardada = null;
        Tetris partida = null;
        JsonArray array = new JsonArray();
        boolean enc = false;
        while (itr.hasNext() && !enc){
            partidaGuardada = itr.next();
            if (partidaGuardada.obtFechaHora().toString().equals(fechaHora)){
                enc = true;
                partida = partidaGuardada.getLaPartida();
                array.add(partida.codigoUsuario);
                array.add(partida.codigoPartida);
                array.add(partida.nivel);
                array.add(partida.getPuntos());
                array.add(partida.getCasillasOcupadas().length);
                String[] pieza = new String[partida.getCasillasOcupadas().length];
                for (int i=0;i < partida.getCasillasOcupadas().length;i++){
                    pieza[i] = partida.getCasillasOcupadas()[i].toString();
                }
                array.add(Arrays.toString(pieza));
                System.out.println(array);
            }
        }
        return array;
    }
}
