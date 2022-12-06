package controlador;

import com.zetcode.Central;
import vista.VentanaElegirNivel;
import vista.VentanaMenu;
import vista.VentanaNivelElegido;
import vista.VentanaPartidasGuardadas;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ControladorPartidasGuardadas implements MouseListener {
    private static ControladorPartidasGuardadas miControladorVentanaPartidasGuardadas;


    private ControladorPartidasGuardadas(){}

    public static ControladorPartidasGuardadas getInstance(){
        if(miControladorVentanaPartidasGuardadas == null) miControladorVentanaPartidasGuardadas = new ControladorPartidasGuardadas();
        return miControladorVentanaPartidasGuardadas;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //Se carga la partida seleccionada
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
