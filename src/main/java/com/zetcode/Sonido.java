package com.zetcode;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Sonido{
    private static Sonido miSonido;
    private Clip clipCancion;
    private Clip clipBeep;


    private Sonido() {}

    public Clip reproducirSonido(String nombreSonido,String clipS){
        Clip clip=null;
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(nombreSonido).getAbsoluteFile());
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
            if(clipS.equals("Clip Beep")){
                clipBeep=clip;
            }
            else {
                clipCancion=clip;
            }
        } catch(UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
            System.out.println("Error al reproducir el sonido.");
        }
        return clip;
    }

    public static Sonido getInstance(){
        if(miSonido==null){
            miSonido=new Sonido();
        }
        return miSonido;
    }


    public void stop(String clip){
        if(clip.equals("Clip Beep")){
            clipBeep.stop();
        }
        else {
            clipCancion.stop();
        }
    }


    public Clip getClip(String clip) {
        if(clip.equals("Clip Beep")){
            return clipBeep;
        }
        else {
            return clipCancion;
        }
    }
}

