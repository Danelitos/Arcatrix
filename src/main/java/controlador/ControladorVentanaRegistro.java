package controlador;

import vista.VentanaElegirNivel;
import vista.VentanaLogin;
import vista.VentanaRegistro;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ControladorVentanaRegistro implements MouseListener, ItemListener {
    private static ControladorVentanaRegistro controladorVentanaRegistro;


    private ControladorVentanaRegistro(){}

    public static ControladorVentanaRegistro getInstance(){
        if(controladorVentanaRegistro == null) controladorVentanaRegistro = new ControladorVentanaRegistro();
        return controladorVentanaRegistro;
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {

        if(e.getSource() instanceof JButton){
            JButton boton = (JButton) e.getSource();
            if (boton.getText().equals("CREAR CUENTA")){
                System.out.println("LOGIN");
                VentanaLogin.getInstance().setVisible(true);
                VentanaRegistro.getInstance().setVisible(false);
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
