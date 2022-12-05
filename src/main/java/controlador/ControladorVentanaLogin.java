package controlador;

import vista.VentanaElegirNivel;
import vista.VentanaLogin;
import vista.VentanaRegistro;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ControladorVentanaLogin implements MouseListener, ItemListener {
    private static ControladorVentanaLogin controlador;


    private ControladorVentanaLogin(){}

    public static ControladorVentanaLogin getInstance(){
        if(controlador == null) controlador = new ControladorVentanaLogin();
        return controlador;
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {

        if(e.getSource() instanceof JButton){
            JButton boton = (JButton) e.getSource();
            if (boton.getText().equals("INICIAR SESION")){
                System.out.println("ENTRA");
                //De momento así para que funcione
                VentanaElegirNivel.getInstance(0);
                VentanaLogin.getInstance().setVisible(false);
            }
            else if(boton.getText().equals("REGISTRARSE")){
                System.out.println("Pasa a registrarse");
                VentanaRegistro.getInstance().setVisible(true);
                VentanaLogin.getInstance().setVisible(false);
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
