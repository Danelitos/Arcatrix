package vista;

import controlador.ControladorVentanaLogin;

import javax.swing.*;
import java.awt.*;

public class VentanaLogin extends JFrame{
    private static VentanaLogin miMenu;

    private JPanel panelLogin;

    private JLabel usuarioText;
    private JLabel passwordText;

    private JTextField usuario;
    private JPasswordField password;
    private JButton loginButton;
    private JButton registrarse;

    private VentanaLogin(){
        super("ARCATRIX");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setMinimumSize(new Dimension(500,500));

        panelLogin=new JPanel();

        panelLogin.setLayout(new GridLayout(6,1,0,0));
        getContentPane().add(panelLogin,BorderLayout.CENTER);

        setLocationRelativeTo(null);

        loginButton=getBoton("INICIAR SESION");
        registrarse=getBoton("REGISTRARSE");
        usuario=new JTextField();
        password= new JPasswordField();
        usuarioText=new JLabel("Usuario");
        passwordText=new JLabel("Contraseña");

        panelLogin.add(usuarioText, BorderLayout.CENTER);
        panelLogin.add(usuario, BorderLayout.CENTER);
        panelLogin.add(passwordText, BorderLayout.CENTER);
        panelLogin.add(password,BorderLayout.CENTER);
        panelLogin.add(loginButton,BorderLayout.CENTER);
        panelLogin.add(registrarse,BorderLayout.CENTER);


        setVisible(true);
    }

    public static VentanaLogin getInstance(){
        if(VentanaLogin.miMenu==null){
            VentanaLogin.miMenu=new VentanaLogin();
        }
        return VentanaLogin.miMenu;
    }

    private JButton getBoton(String text){
        JButton boton = new JButton(text);
        boton.addMouseListener(ControladorVentanaLogin.getInstance());
        boton.setHorizontalAlignment(SwingConstants.CENTER);

        return boton;
    }
}
