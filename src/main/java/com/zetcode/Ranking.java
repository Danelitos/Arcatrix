package com.zetcode;

public class Ranking implements Comparable<Ranking>{

    private String  nombreUsr, nivel;
    private int idUsr, puntuacion;

    public Ranking(int IdUsr, String NombreUsr, int Puntuacion, String Nivel){
        this.idUsr = IdUsr;
        this.nombreUsr = NombreUsr;
        this.puntuacion = Puntuacion;
        this.nivel = Nivel;


    }

    public String getNombreUsr(){

        return nombreUsr;
    }
    public int getIdUsr(){

        return idUsr;
    }
    public int getPuntuacion(){

        return puntuacion;
    }
    public String getNivel(){
        return this.nivel;}

    @Override
    public int compareTo(Ranking r) {
        if(r.getPuntuacion()>this.puntuacion){
            return 1;
        }else if(r.getPuntuacion()==this.puntuacion){
            return 0;
        }else{
            return -1;
        }

    }
}
