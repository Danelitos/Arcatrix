package controlador;

import vista.VentanaLogin;
import vista.VentanaNivelElegido;
import vista.VentanaElegirNivel;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ControladorVentanaElegirNivel implements MouseListener, ItemListener {
    private static ControladorVentanaElegirNivel miControladorventanaElegirNivel;


    private ControladorVentanaElegirNivel(){}

    public static ControladorVentanaElegirNivel getInstance(){
        if(miControladorventanaElegirNivel == null) miControladorventanaElegirNivel = new ControladorVentanaElegirNivel();
        return miControladorventanaElegirNivel;
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {
        int codigoUsuario = VentanaElegirNivel.getInstance(0).codUsuario;
        if(e.getSource() instanceof JButton){
            JButton boton = (JButton) e.getSource();
            if (boton.getText().equals("Facil")){
                VentanaNivelElegido.getInstance(codigoUsuario,boton.getText()).setVisible(true);
                VentanaElegirNivel.getInstance(codigoUsuario).setVisible(false);
            }
            else if (boton.getText().equals("Medio")){
                VentanaNivelElegido.getInstance(codigoUsuario,boton.getText()).setVisible(true);
                VentanaElegirNivel.getInstance(codigoUsuario).setVisible(false);
            }
            else if (boton.getText().equals("Dificil")){
                VentanaNivelElegido.getInstance(codigoUsuario,boton.getText()).setVisible(true);
                VentanaElegirNivel.getInstance(codigoUsuario).setVisible(false);
            }
            else{
                System.exit(0);
            }
            System.out.println("Nivel elegido: " + boton.getText());
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
