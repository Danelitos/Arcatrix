package vista;
import javax.swing.*;
import java.awt.*;

public class VentanaRecuperarContrasena extends JFrame {
    private static VentanaRecuperarContrasena miMenu;

    private JPanel panelRecuperar;
    private JLabel correoText;
    private JTextField correo;
    private JButton recuperarButton;


    private VentanaRecuperarContrasena(){
        super("ARCATRIX - RECUPERAR CONTRASEÑA");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setMinimumSize(new Dimension(500,500));

        panelRecuperar=new JPanel();

        panelRecuperar.setLayout(new GridLayout(10,1,0,0));
        getContentPane().add(panelRecuperar,BorderLayout.CENTER);

        setLocationRelativeTo(null);

        recuperarButton=getBoton("RECUPERAR CONTRASEÑA");
        correo=new JTextField();
        correoText=new JLabel("Correo electrónico");


        panelRecuperar.add(correoText, BorderLayout.CENTER);
        panelRecuperar.add(correo, BorderLayout.CENTER);
        panelRecuperar.add(recuperarButton,BorderLayout.CENTER);

        setVisible(true);
    }

    public static VentanaRecuperarContrasena getInstance(){
        if(VentanaRecuperarContrasena.miMenu==null){
            VentanaRecuperarContrasena.miMenu=new VentanaRecuperarContrasena();
        }
        return VentanaRecuperarContrasena.miMenu;
    }

    private JButton getBoton(String text){
        JButton boton = new JButton(text);
        //boton.addMouseListener(ControladorVentanaLogin.getInstance());
        boton.setHorizontalAlignment(SwingConstants.CENTER);

        return boton;
    }
}