package vista;

import com.zetcode.*;

import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class VentanaNivelElegido extends JFrame {
    public static VentanaNivelElegido miNivelElegido;
    public static String nivelElegido;
    public int codigoUsuario;
    JLabel texto, numero, nombre;
    JPanel panel;
    JButton btnEmpPartida;
    Tetris ventana;

    public VentanaNivelElegido(int codUsuario, String nivel) {
        codigoUsuario = codUsuario;
        nivelElegido = nivel;
        // crear ventana
        setTitle("Tetris");
        setSize(500, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        this.setVisible(true);
        //inicar componentes
        setComponentes(nivel);
    }

    private void setComponentes(String nivel) {
        // crear panel
        panel = new JPanel();
        panel.setBackground(Color.darkGray);
        this.getContentPane().add(panel);
        panel.setLayout(null);
        panel.setVisible(true);

        //texto del nivel elegido
        nombre = new JLabel();
        nombre.setText("ARCATRIX");
        nombre.setForeground(Color.white);
        nombre.setBounds(50, 10, 400, 100);
        nombre.setHorizontalAlignment(SwingConstants.CENTER);
        nombre.setFont(new Font("arial", Font.BOLD, 70));
        panel.add(nombre);


        //texto del nivel elegido
        texto = new JLabel();
        texto.setText("NIVEL " + nivel.toUpperCase());
        texto.setForeground(Color.white);
        texto.setBounds(50, 250, 400, 100);
        texto.setHorizontalAlignment(SwingConstants.CENTER);
        texto.setFont(new Font("arial", Font.BOLD, 50));
        panel.add(texto);

        //numero cuenta
        numero = new JLabel();

        numero.setForeground(Color.white);

        numero.setBounds(75, 75, 350, 200);
        numero.setHorizontalAlignment(SwingConstants.CENTER);
        numero.setFont(new Font("arial", Font.BOLD, 150));
        panel.add(numero);
        numero.setVisible(false);

        //boton empezar partida
        btnEmpPartida = new JButton();
        btnEmpPartida.setText("START");
        btnEmpPartida.setForeground(Color.white);
        btnEmpPartida.setFont(new Font("Arial Bold", Font.BOLD, 30));
        btnEmpPartida.setBounds(175, 350, 150, 30);
        btnEmpPartida.setBackground(new Color(245, 52, 52));
        btnEmpPartida.setFocusPainted(false);
        panel.add(btnEmpPartida);
        btnEmpPartida.addActionListener(evento -> start());


    }

    public static VentanaNivelElegido getInstance(int codigoUsuario, String nivelElegido) {
        if (miNivelElegido == null) {
            miNivelElegido = new VentanaNivelElegido(codigoUsuario, nivelElegido);
        }
        return miNivelElegido;
    }

    public void cuentaAtras() {
        try {
            btnEmpPartida.setVisible(false);

            System.out.println("3");
            numero.setVisible(true);
            numero.setText("3");

            this.paint(this.getGraphics());

            Thread.sleep(1000);
            numero.setText("2");

            this.paint(this.getGraphics());
            Thread.sleep(1000);
            System.out.println("");
            numero.setText("1");


            this.paint(this.getGraphics());
            Thread.sleep(1000);
            System.out.println("1");
            numero.setText("GO!");

            this.paint(this.getGraphics());


        } catch (InterruptedException e) {
        }
    }

    public void start() {
        int codPartida = 0;
        //hacer cuenta atras
        VentanaNivelElegido.getInstance(codigoUsuario, "Nada").cuentaAtras();

        //ToDo
        //crear instancia partida con nivel como param

        System.out.println("START  jeje!!");
        this.setVisible(false);

        //INICIAR JUEGO TETRIS
        try {
            String sonidoElegido = GestorBD.getInstance().obtColorPieza("SONIDO", codigoUsuario);
            Sonido.getInstance().reproducirSonido("/audio/" + sonidoElegido.toString() + ".wav", "Clip Cancion");
            Sonido.getInstance().getClip("Clip Cancion").loop(Clip.LOOP_CONTINUOUSLY);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        //CREAMOS LA PARTIDA NUEVA
        Central.getInstance().iniciarPartida(codigoUsuario, codPartida, nivelElegido, null, 0);


    }

}
