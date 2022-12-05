package com.zetcode;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class GaleriaIconos {
    private  static GaleriaIconos miGaleria;
    private String path="src/main/java/img/";

    private final String[] imagenes={"tetrisFondo.png"};

    private final ArrayList<ImageIcon> listaImagenes;

    private GaleriaIconos(){
       listaImagenes=new ArrayList<>();

        for (String elm: imagenes) {
            listaImagenes.add(crearImagen(elm));
        }

    }

    public static GaleriaIconos getInstance(){
        if(GaleriaIconos.miGaleria==null){
            GaleriaIconos.miGaleria=new GaleriaIconos();
        }
        return GaleriaIconos.miGaleria;
    }

    public ImageIcon obtenerImagen(String pTipoVentana){
        ImageIcon imagen = null;

        if(pTipoVentana.equals("MENU")){
            imagen=listaImagenes.get(0);
        }

        return imagen;
    }

    public ImageIcon crearImagen(String pInformacion){
        return new ImageIcon(Objects.requireNonNull(getClass().getResource("C:\\Users\\Danel\\UNI\\INGENIERIA INFOR\\3.Curso\\Análisis y Diseño de Sistemas de Información\\Arcatrix\\src\\main\\java\\img\\tetrisFondo.png")));
    }

}