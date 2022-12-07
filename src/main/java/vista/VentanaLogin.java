package vista;

import com.zetcode.GestorBD;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

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

        loginButton=new JButton("INICIAR SESION");
        loginButton.addActionListener(evento -> {
            try {
                login();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
        registrarse=new JButton("REGISTRARSE");
        registrarse.addActionListener(evento -> registro());
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

    public void login() throws SQLException {
        String usuarioVerificar=usuario.getText();
        String passwordVerificar=new String(password.getPassword());
        int loginCorrecto=GestorBD.getInstance().verificarLogin(usuarioVerificar,passwordVerificar);
        if(loginCorrecto!=0){
            System.out.println(loginCorrecto);
            VentanaLogin.getInstance().setVisible(false);
            VentanaMenu.getInstance(loginCorrecto).setVisible(true);
            VentanaMenu.setCodigoUsu(loginCorrecto);
        }
        else{
            JOptionPane.showMessageDialog(VentanaLogin.getInstance(),"Ha habido un error al logearse","LOGIN ERRONEO",JOptionPane.ERROR_MESSAGE);
        }

    }

    public void registro(){
        VentanaLogin.getInstance().setVisible(false);
        VentanaRegistro.getInstance().setVisible(true);
    }



}
