package controlador;

import vista.Tetris;
import vista.VentanaElegirNivel;
import vista.VentanaNivelElegido;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ControladorTetris implements MouseListener, ItemListener {

    private static ControladorTetris miControladorTetris;

    private ControladorTetris(){}

    public static ControladorTetris getInstance(){
        if(miControladorTetris == null) miControladorTetris = new ControladorTetris();
        return miControladorTetris;
    }
    @Override
    public void itemStateChanged(ItemEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource() instanceof JButton){
            VentanaElegirNivel.getInstance();
            //FALTA CERRAR JUEGO Y LLAMAR A CENTRAL
        }
        else{
            System.exit(0);
        }
        System.out.println("Partida guardada");
    }

    @Override
    public void mousePressed(MouseEvent e) {

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
}
