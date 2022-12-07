package vista;

import controlador.ControladorVentanaElegirNivel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaElegirNivel extends JFrame implements ActionListener{
    private static VentanaElegirNivel miVentanaElegirNivel;
    private JButton btnFacil, btnMedio, btnDificil;
    private JPanel panel;

    public static String nivel;
    public static int codUsuario;


    private VentanaElegirNivel(int codigoUsuario){
        codUsuario=codigoUsuario;
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
        texto.setText("Nivel de dificultad:");
        texto.setBounds(150,75,200,20);
        texto.setFont(new Font(null,Font.PLAIN, 20));
        //texto.setOpaque(true);
        //texto.setBackground(Color.red);
        texto.setHorizontalAlignment(SwingConstants.CENTER);
        texto.setForeground(Color.white);
        panel.add(texto);

        //crear BOTONES
        btnFacil= new JButton();
        btnFacil.setBounds(210,150,80,35);
        btnFacil.setText("Facil");
        btnFacil.setBackground(new Color(146, 248, 133));
        btnFacil.setFocusPainted(false);
        panel.add(btnFacil);
        //btnFacil.addMouseListener(ControladorVentanaElegirNivel.getInstance());
        btnFacil.addActionListener(this);

        btnMedio= new JButton();
        btnMedio.setBounds(210,200,80,35);
        btnMedio.setText("Medio");
        btnMedio.setBackground(new Color(248, 248, 133));
        btnMedio.setFocusPainted(false);
        panel.add(btnMedio);
        btnMedio.setHorizontalAlignment(SwingConstants.CENTER);
        //btnMedio.addMouseListener(ControladorVentanaElegirNivel.getInstance());
        btnMedio.addActionListener(this);

        btnDificil= new JButton();
        btnDificil.setBounds(210,250,80,35);
        btnDificil.setText("Dificil");
        btnDificil.setBackground(new Color(248, 133, 133));
        btnDificil.setFocusPainted(false);
        panel.add(btnDificil);
        //btnDificil.addMouseListener(ControladorVentanaElegirNivel.getInstance());
        btnDificil.addActionListener(this);
    }

    public static VentanaElegirNivel getInstance(int codUsuario){
        if(VentanaElegirNivel.miVentanaElegirNivel==null){
            VentanaElegirNivel.miVentanaElegirNivel=new VentanaElegirNivel(codUsuario);
        }
        return VentanaElegirNivel.miVentanaElegirNivel;
    }

    //CONTROLADOR
    @Override
    public void actionPerformed(ActionEvent e) {
        nivel="nada";
        Object source = e.getSource();
        if (btnFacil.equals(source)) {
            nivel = "facil";
        } else if (btnMedio.equals(source)) {
            nivel = "medio";
        } else if (btnDificil.equals(source)) {
            nivel = "dificil";
        }

        System.out.println("Nivel elegido: " + nivel);



        //habilitar siguiente interfaz
        this.setVisible(false);
        VentanaNivelElegido.getInstance(codUsuario,nivel).setVisible(true);
        //this.setVisible(false);
        //panelElegido.setVisible(true);



    }

}
