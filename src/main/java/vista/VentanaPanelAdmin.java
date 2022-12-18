package vista;

import com.zetcode.Central;
import com.zetcode.GestorBD;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class VentanaPanelAdmin extends JFrame {
    private static VentanaPanelAdmin miMenu;

    private JPanel panelAdmin;

    private JLabel usuarioText;

    private JTextField usuario;

    private JButton eliminarButton;
    private JButton volverButton;


    private VentanaPanelAdmin() {
        super("ARCATRIX - ADMINISTRADOR");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);  //darle a la X, se acaba el proceso
        setLocationRelativeTo(null); //en el centro de la pantalla

        //inicar componentes
        this.setVisible(true);
        setComponentes();
    }

    public void setComponentes() {
        panelAdmin = new JPanel();
        getContentPane().add(panelAdmin);
        panelAdmin.setLayout(null);
        panelAdmin.setVisible(true);

        eliminarButton = new JButton("Eliminar usuario");
        eliminarButton.setBounds(140, 240, 200, 50);
        eliminarButton.setBackground(new Color(51, 159, 221));
        eliminarButton.setHorizontalAlignment(SwingConstants.CENTER);
        eliminarButton.addActionListener(evento -> {

            try {
                eliminarUsuario();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });


        volverButton = new JButton("Volver");
        volverButton.setBounds(140, 300, 200, 50);
        volverButton.setBackground(new Color(51, 159, 221));
        volverButton.setHorizontalAlignment(SwingConstants.CENTER);
        volverButton.addActionListener(evento -> volver());


        usuarioText = new JLabel("Usuario");
        usuarioText.setBounds(100, 140, 150, 20);
        usuarioText.setFont(new Font(null, Font.BOLD, 20));
        usuarioText.setHorizontalAlignment(SwingConstants.CENTER);
        usuario = new JTextField();
        usuario.setBounds(140, 170, 200, 30);


        panelAdmin.add(usuarioText);
        panelAdmin.add(usuario);
        panelAdmin.add(eliminarButton);
        panelAdmin.add(volverButton);

    }

    public static VentanaPanelAdmin getInstance() {
        if (VentanaPanelAdmin.miMenu == null) {
            VentanaPanelAdmin.miMenu = new VentanaPanelAdmin();
        }
        return VentanaPanelAdmin.miMenu;
    }

    public void eliminarUsuario() throws SQLException{
        String nombre = usuario.getText();
        System.out.println(nombre);
        boolean borrarUsuario = GestorBD.getInstance().eliminarUsuario(nombre);

        if(borrarUsuario){
            JOptionPane.showMessageDialog(VentanaPanelAdmin.getInstance(),"Se ha borrado el usuario correctamente: " + nombre,"USUARIO BORRADO",JOptionPane.INFORMATION_MESSAGE);
        }
        else {
            JOptionPane.showMessageDialog(VentanaPanelAdmin.getInstance(), "Error al eliminar el usuario, inténtalo de nuevo", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }


    public void volver() {
        VentanaLogin.miMenu=null;
        VentanaPanelAdmin.getInstance().setVisible(false);
        VentanaLogin.getInstance().setVisible(true);
    }


}