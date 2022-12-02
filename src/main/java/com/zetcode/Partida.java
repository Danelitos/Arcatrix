package com.zetcode;

public class Partida {
    private int codPartida;
    private ListaLadrillos lista;
    private Nivel nivel;
    private InfoPartida info;
    private Ranking rankings;

    public Partida(int pCodPartida, ListaLadrillos pLista, Nivel pNivel, InfoPartida pInfo,Ranking pRankings){
        this.codPartida = pCodPartida;
        this.lista = pLista;
        this.nivel = pNivel;
        this.rankings = pRankings;
    }

    public void cambiarVelocidadLadrillos(){}

    public void cambiarTamañoTablero(){}

    public int getCodPartida(){return this.codPartida;}

    //public int obtCodPartida(){}

    //public int obtCodUsuario(){}
}
