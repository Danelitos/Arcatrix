package controlador;

import com.zetcode.Tetris;
import vista.VentanaElegirNivel;
import vista.VentanaNivelElegido;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ControladorVentanaNivelElegido implements MouseListener, ItemListener {
    private static ControladorVentanaNivelElegido miControladorVentanaNivelElegidol;


    private ControladorVentanaNivelElegido(){}

    public static ControladorVentanaNivelElegido getInstance(){
        if(miControladorVentanaNivelElegidol == null) miControladorVentanaNivelElegidol = new ControladorVentanaNivelElegido();
        return miControladorVentanaNivelElegidol;
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {
        if(e.getSource() instanceof JButton){
            JButton boton = (JButton) e.getSource();
            if (boton.getText().equals("START")){
                new Tetris();
                VentanaNivelElegido.getInstance("Nada").setVisible(false);
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
