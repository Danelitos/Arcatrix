package com.zetcode;

import com.google.gson.JsonElement;

public class Partida {
    public int codigoPartida;

    public String nivel;
    public int codigoUsuario;
    private Shape.Tetrominoe[] casillasOcupadas;
    public int puntos;

    public Partida(int codPartida,String pNivel,int pCodUsuario, Shape.Tetrominoe[] pCasillasOcupadas, int pPuntos){
        codigoPartida=codPartida;
        nivel=pNivel;
        codigoUsuario=pCodUsuario;
        casillasOcupadas=pCasillasOcupadas;
        puntos=pPuntos;

    }

    public Shape.Tetrominoe[] getCasillasOcupadas() {
        return this.casillasOcupadas;
    }

    public int getCodPartida() {
        return codigoPartida;
    }

    public int getPuntos() {
        return this.puntos;
    }
}
