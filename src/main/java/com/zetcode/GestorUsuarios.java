package com.zetcode;

import com.google.gson.JsonArray;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

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
        if (!enc) user = null;
        return (user);
    }

    private Iterator<Usuario> getIterador() {
        return (this.lista.iterator());
    }

    public PartidaGuardada crearPartidaGuardada(Tetris laPartida, Usuario elUsuario, Date fechaActual) {
        PartidaGuardada nuevaPartida = new PartidaGuardada(elUsuario, laPartida, fechaActual);
        return nuevaPartida;
    }

    public PartidaGuardada anadirPartidaGuardada(Usuario elUsuario, PartidaGuardada laPartidaGuardada) {
        if (this.buscarUsuario(elUsuario.getCodUsuario()) != null) {
            elUsuario.getListaPartidasGuardadas().add(laPartidaGuardada);

        }
        return laPartidaGuardada;
    }

    public JsonArray partidasGuardadas(Usuario elUsuario) {
        return elUsuario.partidasGuardadasUsuario();
    }

    public JsonArray buscarPartidaGuardada(Usuario elUsuario, String fechaHora) {
        return elUsuario.obtPartida(fechaHora);
    }

    public void crearUsuario(int codUsu, String nombre, String password) {
        System.out.println(codUsu);
        lista.add(new Usuario(codUsu, nombre, password, new DatosPersonalizacion("Classic Color", "Classic Color", "Classic Color", "Classic Color", "Classic Color", "Classic Color", "Classic Color", "Classic Color", "Sonido")));

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
}
