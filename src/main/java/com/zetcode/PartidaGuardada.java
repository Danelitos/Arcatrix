package com.zetcode;

import com.google.gson.Gson;

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

    public void devJSONPartidaGuardada(){
        Gson json = new Gson();
        //String representacionJSON = json.toJson(this.);
    }


}
