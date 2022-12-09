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

    private JTextField usuario;
    private JTextField correo;
    private JPasswordField password;
    private JPasswordField repetirPassword;

    private JButton registrarse;
    private JButton volver;

    private VentanaRegistro(){
        super("ARCATRIX - REGISTRO");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setMinimumSize(new Dimension(500,500));

        panelRegistro=new JPanel();

        panelRegistro.setLayout(new GridLayout(10,1,0,0));
        getContentPane().add(panelRegistro,BorderLayout.CENTER);

        setLocationRelativeTo(null);

        registrarse=new JButton("Crear cuenta");
        registrarse.addActionListener(evento -> {
            try {
                this.registro();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
        volver=new JButton("Volver");
        volver.addActionListener(evento -> volver());
        usuario=new JTextField();
        correo=new JTextField();
        password= new JPasswordField();
        repetirPassword = new JPasswordField();
        usuarioText=new JLabel("Nombre");
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
        panelRegistro.add(volver,BorderLayout.LINE_END);


        setVisible(true);
    }

    public static VentanaRegistro getInstance(){
        if(VentanaRegistro.miMenu==null){
            VentanaRegistro.miMenu=new VentanaRegistro();
        }
        return VentanaRegistro.miMenu;
    }


    private void registro() throws SQLException {
        String usuarioInsert=usuario.getText();
        String correoInsert=correo.getText();
        String passwordInsert=new String(password.getPassword());
        String repetirPInsert=new String(repetirPassword.getPassword());
        if(passwordInsert.equals(repetirPInsert)){
            boolean registroCorrecto=GestorBD.getInstance().addUsuario(usuarioInsert,correoInsert,passwordInsert);
            if (registroCorrecto){
                JOptionPane.showMessageDialog(VentanaRegistro.getInstance(),"El registro ha sido exitoso","REGISTRO EXITOSO",JOptionPane.INFORMATION_MESSAGE);
            }
            else {
                JOptionPane.showMessageDialog(VentanaRegistro.getInstance(),"Ha habido un error al registrarse","REGISTRO ERRONEO",JOptionPane.ERROR_MESSAGE);
            }
            VentanaRegistro.getInstance().setVisible(false);
            VentanaLogin.getInstance().setVisible(true);
        }
        else{
            JOptionPane.showMessageDialog(VentanaRegistro.getInstance(),"Las contraseñas no coinciden.\nVuelve a escribir la contraseña","CONTRASEÑA INVALIDA",JOptionPane.ERROR_MESSAGE);
        }
    }

    public void volver(){
        VentanaRegistro.getInstance().setVisible(false);
        VentanaLogin.getInstance().setVisible(true);
    }

}