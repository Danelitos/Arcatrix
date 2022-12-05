package vista;

import controlador.ControladorVentanaRegistro;

import javax.swing.*;
import java.awt.*;

public class VentanaRegistro extends JFrame {
    private static VentanaRegistro miMenu;

    private JPanel panelRegistro;

    private JLabel usuarioText;
    private JLabel correoText;
    private JLabel passwordText;
    private JLabel repetirPasswordText;

    private JTextField usuario;
    private JTextField correo;
    private JPasswordField password;
    private JPasswordField repetirPassword;

    private JButton registrarse;

    private VentanaRegistro(){
        super("ARCATRIX - REGISTRO");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setMinimumSize(new Dimension(500,500));

        panelRegistro=new JPanel();

        panelRegistro.setLayout(new GridLayout(10,1,0,0));
        getContentPane().add(panelRegistro,BorderLayout.CENTER);

        setLocationRelativeTo(null);
        ;
        registrarse=getBoton("CREAR CUENTA");
        usuario=new JTextField();
        correo=new JTextField();
        password= new JPasswordField();
        repetirPassword = new JPasswordField();
        usuarioText=new JLabel("Usuario");
        correoText=new JLabel("Correo electrónico");
        passwordText=new JLabel("Contraseña");
        repetirPasswordText=new JLabel("Repetir contraseña");

        panelRegistro.add(usuarioText, BorderLayout.CENTER);
        panelRegistro.add(usuario, BorderLayout.CENTER);
        panelRegistro.add(correoText, BorderLayout.CENTER);
        panelRegistro.add(correo,BorderLayout.CENTER);
        panelRegistro.add(passwordText, BorderLayout.CENTER);
        panelRegistro.add(password,BorderLayout.CENTER);
        panelRegistro.add(repetirPasswordText, BorderLayout.CENTER);
        panelRegistro.add(repetirPassword,BorderLayout.CENTER);
        panelRegistro.add(registrarse,BorderLayout.LINE_END);


        setVisible(true);
    }

    public static VentanaRegistro getInstance(){
        if(VentanaRegistro.miMenu==null){
            VentanaRegistro.miMenu=new VentanaRegistro();
        }
        return VentanaRegistro.miMenu;
    }

    private JButton getBoton(String text){
        JButton boton = new JButton(text);
        boton.addMouseListener(ControladorVentanaRegistro.getInstance());
        boton.setHorizontalAlignment(SwingConstants.CENTER);

        return boton;
    }
}