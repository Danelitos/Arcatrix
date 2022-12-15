package vista;

import javax.swing.*;
import java.awt.*;

public class VentanaRecuperarContrasena extends JFrame {
    private static VentanaRecuperarContrasena miMenu;

    private JPanel panelRecuperar;
    private JLabel correoText;
    private JTextField correo;
    private JButton recuperarButton;
    private JComboBox<String> preguntaSeguridad;
    private JTextField respuestaSeguridad;
    private JLabel preguntaSeguridadText;
    private JLabel respuestaSeguridadText;


    private VentanaRecuperarContrasena() {
        super("ARCATRIX - RECUPERAR CONTRASEÑA");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setMinimumSize(new Dimension(500, 500));

        panelRecuperar = new JPanel();

        panelRecuperar.setLayout(new GridLayout(10, 1, 0, 0));
        getContentPane().add(panelRecuperar, BorderLayout.CENTER);

        setLocationRelativeTo(null);

        recuperarButton = new JButton("Recuperar");
        recuperarButton.setBounds(140, 410, 200, 50);
        recuperarButton.setBackground(new Color(51, 159, 221));
        recuperarButton.setHorizontalAlignment(SwingConstants.CENTER);


        correoText = new JLabel("Correo electrónico");
        correoText.setBounds(100, 20, 150, 20);
        correoText.setFont(new Font(null, Font.BOLD, 15));
        correoText.setHorizontalAlignment(SwingConstants.CENTER);

        correo = new JTextField();
        correo.setBounds(139, 45, 200, 30);

        preguntaSeguridadText = new JLabel("Pregunta de seguridad");
        preguntaSeguridadText.setBounds(125, 260, 200, 20);
        preguntaSeguridadText.setFont(new Font(null, Font.BOLD, 15));
        preguntaSeguridadText.setHorizontalAlignment(SwingConstants.CENTER);

        preguntaSeguridad = new JComboBox<>();
        preguntaSeguridad.addItem("Nombre de tu primera mascota");
        preguntaSeguridad.addItem("Nombre de tu primer colegio");
        preguntaSeguridad.addItem("Nombre de tu mejor amigo/a");
        preguntaSeguridad.addItem("Nombre de tu primo/a favorito/a");
        preguntaSeguridad.setBounds(139, 285, 200, 30);

        respuestaSeguridadText = new JLabel("Respuesta de seguridad");
        respuestaSeguridadText.setBounds(129, 320, 200, 20);
        respuestaSeguridadText.setFont(new Font(null, Font.BOLD, 15));
        respuestaSeguridadText.setHorizontalAlignment(SwingConstants.CENTER);

        respuestaSeguridad = new JTextField();
        respuestaSeguridad.setBounds(139, 345, 200, 30);



        panelRecuperar.add(correoText);
        panelRecuperar.add(correo);
        panelRecuperar.add(preguntaSeguridadText);
        panelRecuperar.add(preguntaSeguridad);
        panelRecuperar.add(respuestaSeguridadText);
        panelRecuperar.add(respuestaSeguridad);
        panelRecuperar.add(recuperarButton);

        setVisible(true);
    }

    public static VentanaRecuperarContrasena getInstance() {
        if (VentanaRecuperarContrasena.miMenu == null) {
            VentanaRecuperarContrasena.miMenu = new VentanaRecuperarContrasena();
        }
        return VentanaRecuperarContrasena.miMenu;
    }

    private JButton getBoton(String text) {
        JButton boton = new JButton(text);
        //boton.addMouseListener(ControladorVentanaLogin.getInstance());
        boton.setHorizontalAlignment(SwingConstants.CENTER);

        return boton;
    }
}