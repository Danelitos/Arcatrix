package vista;

import com.zetcode.Central;
import com.zetcode.GestorBD;
import com.zetcode.GestorUsuarios;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class VentanaRegistro extends JFrame {
    private static VentanaRegistro miMenu;

    private JPanel panelRegistro;

    private JLabel usuarioText;
    private JLabel correoText;
    private JLabel passwordText;
    private JLabel repetirPasswordText;
    private JLabel preguntaSeguridadText;
    private JLabel respuestaSeguridadText;
    private JLabel avisoUsuario;
    private JLabel avisoCorreo;
    private JLabel avisoPassword;
    private JLabel avisoRepetirPassword;

    private JTextField usuario;
    private JTextField correo;
    private JPasswordField password;
    private JPasswordField repetirPassword;
    private JComboBox<String> preguntaSeguridad;
    private JTextField respuestaSeguridad;
    private JButton registrarse;
    private JButton volver;

    private VentanaRegistro() {
        super("ARCATRIX - REGISTRO");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);  //darle a la X, se acaba el proceso
        setLocationRelativeTo(null); //en el centro de la pantalla

        //inicar componentes
        this.setVisible(true);
        setComponentes();

        validar();
    }

    public void setComponentes() {
        panelRegistro = new JPanel();
        getContentPane().add(panelRegistro);
        panelRegistro.setLayout(null);
        panelRegistro.setVisible(true);


        usuario = new JTextField();
        usuario.setBounds(139, 45, 200, 30);
        correo = new JTextField();
        correo.setBounds(139, 105, 200, 30);
        password = new JPasswordField();
        password.setBounds(139, 165, 200, 30);
        repetirPassword = new JPasswordField();
        repetirPassword.setBounds(139, 225, 200, 30);

        usuarioText = new JLabel("Nombre");
        usuarioText.setBounds(100, 20, 150, 20);
        usuarioText.setFont(new Font(null, Font.BOLD, 15));
        usuarioText.setHorizontalAlignment(SwingConstants.CENTER);
        correoText = new JLabel("Correo electrónico");
        correoText.setBounds(135, 80, 150, 20);
        correoText.setFont(new Font(null, Font.BOLD, 15));
        correoText.setHorizontalAlignment(SwingConstants.CENTER);
        passwordText = new JLabel("Contraseña");
        passwordText.setBounds(110, 140, 150, 20);
        passwordText.setFont(new Font(null, Font.BOLD, 15));
        passwordText.setHorizontalAlignment(SwingConstants.CENTER);
        repetirPasswordText = new JLabel("Repetir contraseña");
        repetirPasswordText.setBounds(137, 200, 150, 20);
        repetirPasswordText.setFont(new Font(null, Font.BOLD, 15));
        repetirPasswordText.setHorizontalAlignment(SwingConstants.CENTER);

        //parte para recuperar contraseña
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

        //BOTONES
        registrarse = new JButton("Crear cuenta");
        registrarse.setBounds(140, 410, 200, 50);
        registrarse.setBackground(new Color(51, 159, 221));
        registrarse.setHorizontalAlignment(SwingConstants.CENTER);
        registrarse.addActionListener(evento -> {
            try {
                this.registro();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
        volver = new JButton("Volver");
        volver.setBounds(140, 490, 200, 50);
        volver.setBackground(new Color(51, 159, 221));
        volver.setHorizontalAlignment(SwingConstants.CENTER);
        volver.addActionListener(evento -> volver());

        //validar
        avisoUsuario = new JLabel();
        avisoUsuario.setBounds(290, 45, 200, 30);
        avisoUsuario.setFont(new Font(null, Font.BOLD, 9));
        avisoUsuario.setForeground(Color.red);
        avisoUsuario.setHorizontalAlignment(SwingConstants.CENTER);

        avisoCorreo = new JLabel();
        avisoCorreo.setBounds(290, 105, 200, 30);
        avisoCorreo.setFont(new Font(null, Font.BOLD, 9));
        avisoCorreo.setForeground(Color.red);
        avisoCorreo.setHorizontalAlignment(SwingConstants.CENTER);

        avisoPassword = new JLabel();
        avisoPassword.setBounds(290, 165, 200, 30);
        avisoPassword.setFont(new Font(null, Font.BOLD, 9));
        avisoPassword.setForeground(Color.red);
        avisoPassword.setHorizontalAlignment(SwingConstants.CENTER);

        avisoRepetirPassword = new JLabel();
        avisoRepetirPassword.setBounds(290, 225, 200, 30);
        avisoRepetirPassword.setFont(new Font(null, Font.BOLD, 9));
        avisoRepetirPassword.setForeground(Color.red);
        avisoRepetirPassword.setHorizontalAlignment(SwingConstants.CENTER);

        panelRegistro.add(usuarioText);
        panelRegistro.add(usuario);
        panelRegistro.add(correoText);
        panelRegistro.add(correo);
        panelRegistro.add(passwordText);
        panelRegistro.add(password);
        panelRegistro.add(repetirPasswordText);
        panelRegistro.add(repetirPassword);
        panelRegistro.add(registrarse);
        panelRegistro.add(volver);

        panelRegistro.add(avisoUsuario);
        panelRegistro.add(avisoCorreo);
        panelRegistro.add(avisoPassword);
        panelRegistro.add(avisoRepetirPassword);

        panelRegistro.add(preguntaSeguridadText);
        panelRegistro.add(preguntaSeguridad);
        panelRegistro.add(respuestaSeguridadText);
        panelRegistro.add(respuestaSeguridad);



    }

    public static VentanaRegistro getInstance() {
        if (VentanaRegistro.miMenu == null) {
            VentanaRegistro.miMenu = new VentanaRegistro();
        }
        return VentanaRegistro.miMenu;
    }


    private void registro() throws SQLException {
        String usuarioInsert = usuario.getText();
        String correoInsert = correo.getText();
        String passwordInsert = new String(password.getPassword());
        String repetirPInsert = new String(repetirPassword.getPassword());
        if (passwordInsert.equals(repetirPInsert)) {
            boolean registroCorrecto = GestorBD.getInstance().addUsuario(usuarioInsert, correoInsert, passwordInsert);
            if (registroCorrecto) {
                JOptionPane.showMessageDialog(VentanaRegistro.getInstance(), "El registro ha sido exitoso", "REGISTRO EXITOSO", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(VentanaRegistro.getInstance(), "Ha habido un error al registrarse", "REGISTRO ERRONEO", JOptionPane.ERROR_MESSAGE);
            }
            VentanaRegistro.getInstance().setVisible(false);
            VentanaLogin.getInstance().setVisible(true);
        } else {
            JOptionPane.showMessageDialog(VentanaRegistro.getInstance(), "Las contraseñas no coinciden.\nVuelve a escribir la contraseña", "CONTRASEÑA INVALIDA", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void volver() {
        VentanaRegistro.getInstance().setVisible(false);
        VentanaLogin.getInstance().setVisible(true);
    }

    public void validar(){
        if(usuarioText.getText().isEmpty()){
            avisoUsuario.setText("*campo requerido");
        }else{
            avisoUsuario.setText("");
        }

        if(correoText.getText().isEmpty()){
            avisoCorreo.setText("*campo requerido");
        }else if(!correoText.getText().contains("@") || !correoText.getText().contains(".")){
            avisoCorreo.setText("¡no es válido!");
        }else{
            avisoCorreo.setText("");
        }

        if(passwordText.getText().isEmpty()){
            avisoPassword.setText("*campo requerido");
        }else{
            avisoPassword.setText("");
        }

        if(repetirPasswordText.getText().isEmpty()){
            avisoRepetirPassword.setText("*campo requerido");
        }else{
            avisoRepetirPassword.setText("");
        }

        if(usuarioText.getText().isEmpty() || correoText.getText().isEmpty() || passwordText.getText().isEmpty() || repetirPasswordText.getText().isEmpty()){
            registrarse.setEnabled(false);
        }else{
            registrarse.setEnabled(true);
        }
    }

    private void usuarioTextKeyReleased(java.awt.event.KeyEvent evt){
        validar();
    }

}