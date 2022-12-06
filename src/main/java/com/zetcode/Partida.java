package com.zetcode;

public class Partida {
    private int codPartida,codUsuario;
    private ListaLadrillos lista;
    private String nivel;
    private int puntos;
    private Ranking rankings;

    public Partida(int pCodPartida,int pCodUsuario, ListaLadrillos pLista, String pNivel, int pPuntos,Ranking pRankings){
        this.codPartida = pCodPartida;
        this.codUsuario = pCodUsuario;
        this.lista = pLista;
        this.nivel = pNivel;
        this.puntos=pPuntos;
        this.rankings = pRankings;
    }

    public void cambiarVelocidadLadrillos(){}

    public void cambiarTamañoTablero(){}

    public int getCodPartida(){return this.codPartida;}

    //public int obtCodPartida(){}

    //public int obtCodUsuario(){}
}
