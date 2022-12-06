package controlador;


import vista.*;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ControladorVentanaMenu implements MouseListener, ItemListener {
    private static ControladorVentanaMenu controladorMenu;


    private ControladorVentanaMenu(){}

    public static ControladorVentanaMenu getInstance(){
        if(controladorMenu == null) controladorMenu = new ControladorVentanaMenu();
        return controladorMenu;
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {

        if(e.getSource() instanceof JButton){
            JButton boton = (JButton) e.getSource();
            if (boton.getText().equals("Iniciar Partida")){
                System.out.println("ENTRA");
                //TODO De momento así para que funcione
                VentanaElegirNivel.getInstance(0);
                VentanaMenu.getInstance(0).setVisible(false);
            }
            else if(boton.getText().equals("Cargar Partida")){
                System.out.println("Pasa a cargar partida");
                VentanaPartidasGuardadas.getInstance().setVisible(true);
                VentanaMenu.getInstance(0).setVisible(false);
            }
            else if(boton.getText().equals("Personalización")){
                System.out.println("Pasa a personalizar");
                VentanaPersonalizacion.getInstance().setVisible(true);
                VentanaMenu.getInstance(0).setVisible(false);
            }
            else if(boton.getText().equals("Rankings")){
                System.out.println("Pasa a ver los rankings");
                //TODO De momento así para que funcione
                VentanaElegirRanking.getInstance(0).setVisible(true);
                VentanaMenu.getInstance(0).setVisible(false);
            }
            else if(boton.getText().equals("Cerrar Sesión")){
                System.out.println("Se cierra la sesion del usuario");
                VentanaLogin.getInstance().setVisible(true);
                VentanaMenu.getInstance(0).setVisible(false);
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
