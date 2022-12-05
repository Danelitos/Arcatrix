package controlador;

import com.zetcode.*;
import vista.VentanaNivelElegido;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

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
                //coger nivel elegido
                String nivel= VentanaNivelElegido.getInstance(0,"Nada").nivelElegido;

                //crear la partida
                Random randomizer= new Random();
                int codPartida= randomizer.nextInt();
                Partida partida= new Partida(codPartida,new ListaLadrillos(),nivel,0,new Ranking());
                //añadirle la partida al usuario
                int codUsuario = VentanaNivelElegido.getInstance(0,"Nada").codigoUsuario;
                Usuario user = GestorUsuarios.getInstance().buscarUsuario(codUsuario);
                user.asignarPartida(partida);
                //hacer cuenta atras
                VentanaNivelElegido.getInstance(0,"Nada").cuentaAtras();

                //crear interfaz del juego
                new Tetris(0,codPartida,nivel);
                VentanaNivelElegido.getInstance(0,"Nada").setVisible(false);
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
