package com.zetcode;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.Date;

public class PartidaGuardada {
    private Usuario elUsuario;
    private Partida laPartida;
    private Date cuando;

    public PartidaGuardada(Usuario pElUsuario, Partida pLaPartida, Date pCuando){
        this.elUsuario = pElUsuario;
        this.laPartida = pLaPartida;
        this.cuando = pCuando;
    }

    public int obtCodUsuario(){
        return this.elUsuario.getCodUsuario();
    }

    public int obtCodPartida(){
        return this.laPartida.getCodPartida();
    }

    public Date obtFechaHora(){return this.cuando;}
}
