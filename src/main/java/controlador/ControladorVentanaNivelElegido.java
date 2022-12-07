package controlador;

import com.zetcode.*;
import vista.VentanaNivelElegido;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
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
                int codUsuario = VentanaNivelElegido.getInstance(0,"Nada").codigoUsuario;

                //añadir partida a la base datos
                int codPartida = 0;
                //try {
                //    codPartida=GestorBD.getInstance().insertPartida(codUsuario,nivel,0);
                //} catch (SQLException ex) {
                //    throw new RuntimeException(ex);
                //}

                //crear la instancia partida
                Partida partida= new Partida(codPartida,codUsuario,new ListaLadrillos(),nivel,0,new Ranking());
                System.out.println("partida instancia: " + partida.getCodPartida());
                //añadirle la partida al usuario
                Usuario user = GestorUsuarios.getInstance().buscarUsuario(codUsuario);
                user.asignarPartida(partida);
                //hacer cuenta atras
                VentanaNivelElegido.getInstance(0,"Nada").cuentaAtras();

                //crear interfaz del juego
                new Tetris(0,codPartida,nivel);
                VentanaNivelElegido.getInstance(0,"Nada").setVisible(false);
                //Añadido para probar sin BD
                codPartida++;
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
