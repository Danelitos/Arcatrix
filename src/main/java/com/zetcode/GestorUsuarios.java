package com.zetcode;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

public class GestorUsuarios {

    private static GestorUsuarios miGestorUsuarios;
    private ArrayList<Usuario> lista;

    private GestorUsuarios(){
        lista = new ArrayList<Usuario>();
        Usuario admin = new Usuario(0,"admin","test",null);
        lista.add(admin);
    }
    public static GestorUsuarios getInstance(){
        if(miGestorUsuarios == null) miGestorUsuarios = new GestorUsuarios();
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

    public PartidaGuardada crearPartidaGuardada(Partida laPartida,Usuario elUsuario){
        Date fechaActual = new Date();
        PartidaGuardada nuevaPartida = new PartidaGuardada(elUsuario,laPartida,fechaActual);
        return nuevaPartida;
    }

    public PartidaGuardada anadirPartidaGuardada(Usuario elUsuario, PartidaGuardada laPartidaGuardada){
        if (this.buscarUsuario(elUsuario.getCodUsuario()) != null){
            elUsuario.getListaPartidasGuardadas().add(laPartidaGuardada);
        }
        return  laPartidaGuardada;
    }

    public void partidasGuardadas(Usuario elUsuario){
        elUsuario.partidasGuardadasUsuario();
    }


}
