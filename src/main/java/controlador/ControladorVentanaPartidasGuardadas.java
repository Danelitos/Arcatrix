package controlador;

import com.zetcode.Tetris;
import vista.*;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ControladorVentanaPartidasGuardadas implements MouseListener, ItemListener {
    private static ControladorVentanaPartidasGuardadas controladorPartidasGuardadas;


    private ControladorVentanaPartidasGuardadas(){}

    public static ControladorVentanaPartidasGuardadas getInstance(){
        if(controladorPartidasGuardadas == null) controladorPartidasGuardadas = new ControladorVentanaPartidasGuardadas();
        return controladorPartidasGuardadas;
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {

        if(e.getSource() instanceof JButton){
            JButton boton = (JButton) e.getSource();
            if (boton.getText().equals("Cargar Partida")){
                System.out.println("Se carga la partida");
                //TODO PARA QUE FUNCIONE DE MOMENTO
                new Tetris(0,0,"Facil");
                VentanaPartidasGuardadas.getInstance().setVisible(false);
            }
            else if(boton.getText().equals("Volver")){
                System.out.println("Vuelve al menu principal");
                //TODO PARA QUE FUNCIONE DE MOMENTO
                VentanaMenu.getInstance(0).setVisible(true);
                VentanaPartidasGuardadas.getInstance().setVisible(false);
            }
            else{
                System.exit(0);
            }
        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }


    @Override
    public void itemStateChanged(ItemEvent e) {

    }
}
