package controlador;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.zetcode.Central;
import com.zetcode.Shape;
import com.zetcode.Tetris;
import vista.*;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

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
                VentanaPartidasGuardadas.getInstance().setVisible(false);
                //Obtenemos la fechaHora de la ventana de Partidas guardadas y el código de usuario de la VentanaMenu
                String s = VentanaPartidasGuardadas.getInstance().getListaString().getSelectedValue();
                JsonArray laPartida = Central.getInstance().cargarPartida(VentanaMenu.getInstance(0).codigoUsu,s);
                //Crear la partida
                Integer codUsuarioPartida = laPartida.get(0).getAsInt();
                Integer codigoPartida = laPartida.get(1).getAsInt();
                String nivel = laPartida.get(2).getAsString();
                Integer puntos = laPartida.get(3).getAsInt();
                Shape.Tetrominoe[] board = null;
                String s1 = laPartida.get(5).toString();
                System.out.println(s1);
                //Falla al pasar de String a String[]
                for (int i=0; i < laPartida.get(4).getAsInt();i++){
                    //board[i] = Shape.Tetrominoe.valueOf(array[i]);
                    System.out.println(array[i]);
                }
                System.out.println("partida instancia: " + codigoPartida++);
                //hacer cuenta atras
                VentanaNivelElegido.getInstance(codUsuarioPartida,nivel).cuentaAtras();
                //crear interfaz del juego
                new Tetris(codUsuarioPartida,codigoPartida++,nivel,board,puntos);
                VentanaNivelElegido.getInstance(codUsuarioPartida,nivel).setVisible(false);
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
