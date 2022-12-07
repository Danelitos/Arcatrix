package controlador;

import vista.*;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ControladorVentanaPersonaliza implements MouseListener, ItemListener {
    private static ControladorVentanaPersonaliza controladorPersonaliza;


    private ControladorVentanaPersonaliza(){}

    public static ControladorVentanaPersonaliza getInstance(){
        if(controladorPersonaliza == null) controladorPersonaliza = new ControladorVentanaPersonaliza();
        return controladorPersonaliza;
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {

        if(e.getSource() instanceof JButton){
            JButton boton = (JButton) e.getSource();
            if (boton.getText().equals("Guardar Personalización")){
                System.out.println("Se guarda");
                VentanaMenu.getInstance(0).setVisible(true);
                VentanaPersonalizacion.getInstance(0).setVisible(false);
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
