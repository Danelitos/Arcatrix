package com.zetcode;

import com.google.gson.JsonArray;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import static vista.VentanaElegirNivel.nivel;

public class GestorUsuarios {

    private static GestorUsuarios miGestorUsuarios;
    private ArrayList<Usuario> lista;

    private GestorUsuarios() {
        lista = new ArrayList<>();
    }

    public static GestorUsuarios getInstance() {
        if (miGestorUsuarios == null) miGestorUsuarios = new GestorUsuarios();
        return miGestorUsuarios;
    }

    public Usuario buscarUsuario(int codUsuario) {
        Iterator<Usuario> itr = this.getIterador();
        Usuario user = null;
        boolean enc = false;

        while (itr.hasNext() && !enc) {
            user = itr.next();
            if (user.getCodUsuario() == codUsuario) {
                enc = true;
            }
        }
        if (!enc) {user = null;};
        return (user);
    }

    private Iterator<Usuario> getIterador() {
        return (this.lista.iterator());
    }

    public PartidaGuardada crearPartidaGuardada(Partida laPartida, Usuario elUsuario, String fechaActual) {
        PartidaGuardada nuevaPartida = new PartidaGuardada(elUsuario, laPartida, fechaActual);
        return nuevaPartida;
    }

    public PartidaGuardada anadirPartidaGuardada(Usuario elUsuario, PartidaGuardada laPartidaGuardada) {
        if (elUsuario != null) {
            elUsuario.getListaPartidasGuardadas().add(laPartidaGuardada);
        }
        return laPartidaGuardada;
    }

    public JsonArray partidasGuardadas(Usuario elUsuario) {
        return elUsuario.partidasGuardadasUsuario();
    }

    public Partida crearPartida(int codPartida, String nivel, int codUsuario, Shape.Tetrominoe[] board, int puntos){
        Partida partida = new Partida(codPartida, nivel, codUsuario, board, puntos);
        return partida;
    }

    public void crearUsuario(int codUsu, String nombre, String password) {
        lista.add(new Usuario(codUsu, nombre, password, new DatosPersonalizacion("Classic Color", "Classic Color", "Classic Color", "Classic Color", "Classic Color", "Classic Color", "Classic Color", "Classic Color", "Sonido")));
        System.out.println(lista.size());
    }

    public void actualizarDatosPersonaliza(int codUsu, String colorFondo, String colorZHSAPE, String colorSSHAPE, String colorLINESHAPE, String colorTSHAPE, String colorSQUARESHAPE, String colorLSHAPE, String colorMIRROREDLSHAPE, String sonido) {
        Usuario user = buscarUsuario(codUsu);
        user.actualizarDatosPersonalizacion(colorFondo, colorZHSAPE, colorSSHAPE, colorLINESHAPE, colorSQUARESHAPE, colorTSHAPE, colorLSHAPE, colorMIRROREDLSHAPE, sonido);

    }

    public DatosPersonalizacion obtPersonalizacion(int codUsu) {
        DatosPersonalizacion personalizacion=null;
        Usuario user = buscarUsuario(codUsu);
        if (!(user == null)) {
            personalizacion=user.obtPersonalizacion();

        }
        return personalizacion;
    }

    public ArrayList<Usuario> getLista(){return lista;}

    public JsonArray obtenerPartida(Usuario elUsuario, String fechaHora){
        return elUsuario.obtPartida(fechaHora);
    }

    public String[] getBoardUsuario(Usuario elUsuario, int codPartida){
        return elUsuario.getBoard(codPartida);
    }
}
