package vista;

import com.zetcode.Central;
import com.zetcode.GestorBD;
import com.zetcode.GestorUsuarios;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class VentanaLogin extends JFrame {
    private static VentanaLogin miMenu;

    private JPanel panelLogin;

    private JLabel usuarioText;
    private JLabel passwordText;

    private JTextField usuario;
    private JPasswordField password;
    private JButton loginButton;
    private JButton registrarse;

    private VentanaLogin() {
        super("ARCATRIX - LOGIN");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);  //darle a la X, se acaba el proceso
        setLocationRelativeTo(null); //en el centro de la pantalla

        //inicar componentes
        this.setVisible(true);
        setComponentes();
    }

    public void setComponentes() {
        panelLogin = new JPanel();
        getContentPane().add(panelLogin);
        panelLogin.setLayout(null);
        panelLogin.setVisible(true);

        loginButton = new JButton("INICIAR SESION");
        loginButton.setBounds(140, 300, 200, 50);
        loginButton.setBackground(new Color(51, 159, 221));
        loginButton.setHorizontalAlignment(SwingConstants.CENTER);
        loginButton.addActionListener(evento -> {
            try {
                login();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
        registrarse = new JButton("REGISTRARSE");
        registrarse.setBounds(140, 375, 200, 50);
        registrarse.setBackground(new Color(51, 159, 221));
        registrarse.setHorizontalAlignment(SwingConstants.CENTER);
        registrarse.addActionListener(evento -> {
            try {
                registro();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
        usuarioText = new JLabel("Usuario");
        usuarioText.setBounds(100, 90, 150, 20);
        usuarioText.setFont(new Font(null, Font.BOLD, 20));
        usuarioText.setHorizontalAlignment(SwingConstants.CENTER);
        usuario = new JTextField();
        usuario.setBounds(140, 120, 200, 30);
        passwordText = new JLabel("Contraseña");
        passwordText.setBounds(120, 170, 150, 20);
        passwordText.setFont(new Font(null, Font.BOLD, 20));
        passwordText.setHorizontalAlignment(SwingConstants.CENTER);
        password = new JPasswordField();
        password.setBounds(140, 200, 200, 30);

        panelLogin.add(usuarioText);
        panelLogin.add(usuario);
        panelLogin.add(passwordText);
        panelLogin.add(password);
        panelLogin.add(loginButton);
        panelLogin.add(registrarse);
    }

    public static VentanaLogin getInstance() {
        if (VentanaLogin.miMenu == null) {
            VentanaLogin.miMenu = new VentanaLogin();
        }
        return VentanaLogin.miMenu;
    }

    public void login() throws SQLException {
        String usuarioVerificar = usuario.getText();
        String passwordVerificar = new String(password.getPassword());
        int loginCorrecto = GestorBD.getInstance().verificarLogin(usuarioVerificar, passwordVerificar);
        if (loginCorrecto != 0) {
            System.out.println(loginCorrecto);
            VentanaLogin.getInstance().setVisible(false);
            VentanaMenu.getInstance(loginCorrecto).setVisible(true);
            VentanaMenu.setCodigoUsu(loginCorrecto);
            int codUsu = GestorBD.getInstance().buscarUsuario(usuarioVerificar, passwordVerificar);
            Central.getInstance().crearUsuario(codUsu, usuarioVerificar, passwordVerificar);
            try {
                Central.getInstance().cargarPartidasGuardadas(codUsu);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else {
            JOptionPane.showMessageDialog(VentanaLogin.getInstance(), "Ha habido un error al logearse", "LOGIN ERRONEO", JOptionPane.ERROR_MESSAGE);
        }

    }

    public void registro() throws SQLException {
        VentanaLogin.getInstance().setVisible(false);
        VentanaRegistro.getInstance().setVisible(true);
    }


}
