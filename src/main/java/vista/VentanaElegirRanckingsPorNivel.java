package vista;

import controlador.ControladorVentanaElegirNivel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class VentanaElegirRanckingsPorNivel extends JFrame {


    private static VentanaElegirRanckingsPorNivel miVentanaElegirRanckingsPorNivel;
    private JButton btnGeneral, btnPorNiveles, btnMiRancking;
    private JPanel panel;

    public static int codUsuario;


    private VentanaElegirRanckingsPorNivel(int codUsuario){

        // crear ventana
        setTitle("Tetris");
        setSize(500, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);  //darle a la X, se acaba el proceso
        setLocationRelativeTo(null); //en el centro de la pantalla

        //inicar componentes
        this.setVisible(true);
        setComponentes();

    }
    private void setComponentes(){
        //crear panel
        panel = new JPanel();
        panel.setBackground(Color.darkGray);
        this.getContentPane().add(panel);
        panel.setLayout(null);
        panel.setVisible(true);

        //crear TEXTO
        JLabel texto = new JLabel();
        texto.setText("Tipo de Rancking:");
        texto.setBounds(150,75,200,20);
        texto.setFont(new Font(null,Font.PLAIN, 20));
        texto.setHorizontalAlignment(SwingConstants.CENTER);
        texto.setForeground(Color.white);
        panel.add(texto);

        //crear BOTONES
        btnGeneral= new JButton();
        btnGeneral.setBounds(210,150,80,35);
        btnGeneral.setText("Nivel Facil");
        btnGeneral.setBackground(new Color(146, 248, 133));
        btnGeneral.setFocusPainted(false);
        panel.add(btnGeneral);
        btnGeneral.addMouseListener(ControladorVentanaElegirNivel.getInstance());

        btnPorNiveles= new JButton();
        btnPorNiveles.setBounds(210,200,80,35);
        btnPorNiveles.setText("Nivel Medio");
        btnPorNiveles.setBackground(new Color(248, 248, 133));
        btnPorNiveles.setFocusPainted(false);
        panel.add(btnPorNiveles);
        btnPorNiveles.setHorizontalAlignment(SwingConstants.CENTER);
        btnPorNiveles.addMouseListener(ControladorVentanaElegirNivel.getInstance());

        btnMiRancking= new JButton();
        btnMiRancking.setBounds(210,250,80,35);
        btnMiRancking.setText("Nivel Dificil");
        btnMiRancking.setBackground(new Color(248, 133, 133));
        btnMiRancking.setFocusPainted(false);
        panel.add(btnMiRancking);
        btnMiRancking.addMouseListener(ControladorVentanaElegirNivel.getInstance());

    }

    public static VentanaElegirRanckingsPorNivel getInstance(int codUsuario){
        if(VentanaElegirRanckingsPorNivel.miVentanaElegirRanckingsPorNivel==null){
            VentanaElegirRanckingsPorNivel.miVentanaElegirRanckingsPorNivel= new VentanaElegirRanckingsPorNivel(codUsuario);
        }
        return VentanaElegirRanckingsPorNivel.miVentanaElegirRanckingsPorNivel;
    }
}


