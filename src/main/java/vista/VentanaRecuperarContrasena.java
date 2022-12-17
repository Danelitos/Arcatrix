package vista;

import com.zetcode.GestorBD;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.Objects;

public class VentanaRecuperarContrasena extends JFrame {
    private static VentanaRecuperarContrasena miMenu;

    private JPanel panelRecuperar;
    private JLabel correoText;
    private JTextField correo;
    private JButton recuperarButton;
    private JButton volverMenu;
    private JComboBox<String> preguntaSeguridad;
    private JTextField respuestaSeguridad;
    private JLabel preguntaSeguridadText;
    private JLabel respuestaSeguridadText;


    private VentanaRecuperarContrasena() {
        super("ARCATRIX - RECUPERAR CONTRASEÑA");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);  //darle a la X, se acaba el proceso
        setLocationRelativeTo(null); //en el centro de la pantalla

        //inicar componentes
        this.setVisible(true);
        setComponentes();
    }

    public void setComponentes(){

        panelRecuperar = new JPanel();
        getContentPane().add(panelRecuperar);
        panelRecuperar.setLayout(null);
        panelRecuperar.setVisible(true);

        correoText = new JLabel("Correo electrónico");
        correoText.setBounds(125, 20, 200, 20);
        correoText.setFont(new Font(null, Font.BOLD, 20));
        correoText.setHorizontalAlignment(SwingConstants.CENTER);

        correo = new JTextField();
        correo.setBounds(139, 50, 200, 30);

        preguntaSeguridadText = new JLabel("Pregunta de seguridad");
        preguntaSeguridadText.setBounds(125, 100, 200, 20);
        preguntaSeguridadText.setFont(new Font(null, Font.BOLD, 15));
        preguntaSeguridadText.setHorizontalAlignment(SwingConstants.CENTER);

        preguntaSeguridad = new JComboBox<>();
        preguntaSeguridad.addItem("Nombre de tu primera mascota");
        preguntaSeguridad.addItem("Nombre de tu primer colegio");
        preguntaSeguridad.addItem("Nombre de tu mejor amigo/a");
        preguntaSeguridad.addItem("Nombre de tu primo/a favorito/a");
        preguntaSeguridad.setBounds(139, 130, 200, 30);

        respuestaSeguridadText = new JLabel("Respuesta de seguridad");
        respuestaSeguridadText.setBounds(129, 180, 200, 20);
        respuestaSeguridadText.setFont(new Font(null, Font.BOLD, 15));
        respuestaSeguridadText.setHorizontalAlignment(SwingConstants.CENTER);

        respuestaSeguridad = new JTextField();
        respuestaSeguridad.setBounds(139, 210, 200, 30);

        recuperarButton = new JButton("RECUPERAR");
        recuperarButton.setBounds(140, 285, 200, 50);
        recuperarButton.setBackground(new Color(51, 159, 221));
        recuperarButton.setHorizontalAlignment(SwingConstants.CENTER);
        recuperarButton.addActionListener(evento -> recuperarContraseña());

        volverMenu = new JButton("VOLVER MENÚ");
        volverMenu.setBounds(140, 360, 200, 50);
        volverMenu.setBackground(new Color(51, 159, 221));
        volverMenu.setHorizontalAlignment(SwingConstants.CENTER);
        volverMenu.addActionListener(evento -> volverMenu());


        panelRecuperar.add(correoText);
        panelRecuperar.add(correo);
        panelRecuperar.add(preguntaSeguridadText);
        panelRecuperar.add(preguntaSeguridad);
        panelRecuperar.add(respuestaSeguridadText);
        panelRecuperar.add(respuestaSeguridad);
        panelRecuperar.add(recuperarButton);
        panelRecuperar.add(volverMenu);

    }

    public static VentanaRecuperarContrasena getInstance() {
        if (VentanaRecuperarContrasena.miMenu == null) {
            VentanaRecuperarContrasena.miMenu = new VentanaRecuperarContrasena();
        }
        return VentanaRecuperarContrasena.miMenu;
    }

    public void recuperarContraseña() {
        /*String correoVerificar=correoText.getText();
        String preguntaVerificar= Objects.requireNonNull(preguntaSeguridad.getSelectedItem()).toString();
        String respuestaVerifivar=respuestaSeguridadText.getText();
        boolean recuperarExito= GestorBD.getInstance().recuperarContraseña(correoVerificar,preguntaVerificar,respuestaVerifivar);
        if(recuperarExito){
            JOptionPane.showConfirmDialog(VentanaRecuperarContrasena.getInstance(),"")
        }*/
    }

    public void volverMenu(){
        VentanaRecuperarContrasena.getInstance().setVisible(false);
        VentanaLogin.getInstance().setVisible(true);
    }

}