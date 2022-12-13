package com.zetcode;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.PrintStream;
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


    public Usuario(int pCodUsuario, String pNombre, String pPassword, DatosPersonalizacion pPersonalizacion) {
        codUsuario = pCodUsuario;
        nombre = pNombre;
        password = pPassword;
        personalizacion = pPersonalizacion;
        listaPartidasGuardadas = new ArrayList<PartidaGuardada>();
    }

    public boolean esUsuario(String pNombre, String pPassword) {
        return nombre == pNombre && password == pPassword;
    }

    public boolean esUsuario(String pNombre) {
        return nombre == pNombre;
    }

    public void actualizarDatosPersonalizacion(String colorFondo, String colorZHSAPE, String colorSSHAPE, String colorLINESHAPE, String colorTSHAPE, String colorSQUARESHAPE, String colorLSHAPE, String colorMIRROREDLSHAPE, String sonido) {
        personalizacion.insertarDatos(colorFondo, colorZHSAPE, colorSSHAPE, colorLINESHAPE, colorSQUARESHAPE, colorTSHAPE, colorLSHAPE, colorMIRROREDLSHAPE, sonido);
    }

    public String getNombre() {
        return nombre;
    }

    public int getCodUsuario() {
        return this.codUsuario;
    }

    public ArrayList<PartidaGuardada> getListaPartidasGuardadas() {
        return listaPartidasGuardadas;
    }

    public JsonArray partidasGuardadasUsuario() {
        Iterator<PartidaGuardada> itr = this.getIterador();
        PartidaGuardada partida = null;
        JsonArray array = new JsonArray();
        while (itr.hasNext()) {
            partida = itr.next();
            String fechaHora = partida.obtFechaHora();
            String s = fechaHora.toString();
            array.add(s);
        }
        return array;
    }

    private Iterator<PartidaGuardada> getIterador() {
        return (this.listaPartidasGuardadas.iterator());
    }

    public JsonArray obtPartida(String fechaHora) {
        Iterator<PartidaGuardada> itr = this.getIterador();
        PartidaGuardada partidaGuardada = null;
        Partida partida = null;
        JsonArray array = new JsonArray();
        boolean enc = false;
        while (itr.hasNext() && !enc) {
            partidaGuardada = itr.next();
            if (partidaGuardada.obtFechaHora().toString().equals(fechaHora)) {
                enc = true;
                partida = partidaGuardada.getLaPartida();
                array.add(partida.codigoUsuario);
                array.add(partida.codigoPartida);
                array.add(partida.nivel);
                array.add(partida.getPuntos());
            }
        }
        return array;
    }

    public String[] getBoard(int codPartida) {
        Partida partida = obtTetris(codPartida);
        String[] pieza = null;
        pieza = new String[partida.getCasillasOcupadas().length];
        for (int i = 0; i < partida.getCasillasOcupadas().length; i++) {
            pieza[i] = partida.getCasillasOcupadas()[i].toString();
        }
        return pieza;
    }

    public Partida obtTetris(int codPartida) {
        Partida partida = null;
        Iterator<PartidaGuardada> itr = this.getIterador();
        PartidaGuardada partidaGuardada = null;
        boolean enc = false;
        while (itr.hasNext() && !enc) {
            partidaGuardada = itr.next();
            if (partidaGuardada.obtCodPartida() == codPartida) {
                enc = true;
                partida = partidaGuardada.getLaPartida();
                System.out.println(partida.getCasillasOcupadas().length);
                System.out.println(partida.nivel);
                System.out.println(partida.codigoPartida);
            }
        }
        return partida;
    }

    public DatosPersonalizacion obtPersonalizacion() {
        personalizacion.obtPersonalizacion(nombre);
        return personalizacion;
    }
}
