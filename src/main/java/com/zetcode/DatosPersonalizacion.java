package com.zetcode;

public class DatosPersonalizacion {
    private String colorFondo;
    private String colorLadrillos;
    private String sonido;

    public DatosPersonalizacion(String pColorFondo,String pColorLadrillos,String pSonido){
        colorFondo=pColorFondo;
        colorLadrillos=pColorLadrillos;
        sonido=pSonido;
    }

    public void insertatDatos(String pColorFondo,String pColorLadrillos,String pSonido){
        colorFondo=pColorFondo;
        colorLadrillos=pColorLadrillos;
        sonido=pSonido;
    }
}
