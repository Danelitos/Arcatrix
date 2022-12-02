package vista;

import com.zetcode.Tetris;
import controlador.ControladorVentanaNivelElegido;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicBorders;
import javax.swing.plaf.basic.BasicTabbedPaneUI;

public class VentanaNivelElegido extends JFrame {
    private static VentanaNivelElegido miNivelElegido;
    JLabel texto;
    JPanel panel;
    JButton btnEmpPartida;
    Tetris ventana;
    public VentanaNivelElegido(String nivel){

        // crear ventana
        setTitle("Tetris");
        setSize(500, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        this.setVisible(true);
        //inicar componentes
        setComponentes(nivel);
    }

    private void setComponentes(String nivel){
        // crear panel
        panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.darkGray);
        this.getContentPane().add(panel);

        //texto del nivel elegido
        texto = new JLabel();
        texto.setText("NIVEL " + nivel.toUpperCase());
        texto.setForeground(Color.white);
        texto.setBounds(150,300,200,20);
        texto.setHorizontalAlignment(SwingConstants.CENTER);
        texto.setFont(new Font("arial", Font.BOLD, 15));
        panel.add(texto);

        //boton empezar partida
        btnEmpPartida = new JButton();
        btnEmpPartida.setText("START");
        btnEmpPartida.setForeground(Color.white);
        btnEmpPartida.setFont(new Font("Arial Bold", Font.BOLD,30));
        btnEmpPartida.setBounds(175,350,150,30);
        btnEmpPartida.setBackground(new Color(245, 52, 52));
        btnEmpPartida.setFocusPainted(false);
        panel.add(btnEmpPartida);
        btnEmpPartida.addMouseListener(ControladorVentanaNivelElegido.getInstance());

        //boton atras

    }

    public static VentanaNivelElegido getInstance(String nivelElegido) {
        if(miNivelElegido==null){
            miNivelElegido=new VentanaNivelElegido(nivelElegido);
        }
        return miNivelElegido;
    }

}
