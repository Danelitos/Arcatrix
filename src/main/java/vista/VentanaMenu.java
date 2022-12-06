package vista;

import controlador.ControladorVentanaMenu;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class VentanaMenu extends JFrame {
    private static VentanaMenu miVentanaMenu;

    private JPanel panelMenu;

    private JPanelImagen fondo;

    private JButton iniciarPartida;
    private JButton cargarPartida;
    private JButton personalizacion;
    private JButton cerrarSesion;
    private JButton rankings;
    public static int codigoUsuario;

    private VentanaMenu(int codigoUsuario) {
        super("ARCATRIX - MENÚ");
        // crear ventana
        setSize(500, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);  //darle a la X, se acaba el proceso
        setLocationRelativeTo(null); //en el centro de la pantalla

        //inicar componentes
        this.setVisible(true);
        setComponentes();
        this.pack();
    }

    public static VentanaMenu getInstance(int codigoUsuario) {
        if (miVentanaMenu == null) {
            miVentanaMenu = new VentanaMenu(codigoUsuario);
        }
        return miVentanaMenu;
    }

    private void setComponentes() {
        //crear panel
        panelMenu = new JPanel();
        //TODO MIRAR ESTO (DANEL)
        fondo=new JPanelImagen("MENU");
        //this.getContentPane().add(fondo,BorderLayout.EAST);

        //this.getContentPane().add(panelMenu);
        //panelMenu.setLayout(null);
        //panelMenu.setVisible(true);

        //TODO UNA MANERA
        panelMenu.setLayout(new GridLayout(5, 1, 5, 5));
        getContentPane().add(panelMenu, BorderLayout.EAST);

        setLocationRelativeTo(null);
        getContentPane().add(fondo,BorderLayout.WEST);


        //crear BOTONES
        iniciarPartida = new JButton();
        iniciarPartida.setBounds(150, 250, 200, 35);
        iniciarPartida.setText("Iniciar Partida");
        iniciarPartida.setBackground(new Color(51, 159, 221));
        iniciarPartida.setFocusPainted(false);
        panelMenu.add(iniciarPartida);
        iniciarPartida.addMouseListener(ControladorVentanaMenu.getInstance());

        cargarPartida = new JButton();
        cargarPartida.setBounds(150, 300, 200, 35);
        cargarPartida.setText("Cargar Partida");
        cargarPartida.setBackground(new Color(51, 159, 221));
        cargarPartida.setFocusPainted(false);
        panelMenu.add(cargarPartida);
        cargarPartida.addMouseListener(ControladorVentanaMenu.getInstance());

        personalizacion = new JButton();
        personalizacion.setBounds(150, 350, 200, 35);
        personalizacion.setText("Personalización");
        personalizacion.setBackground(new Color(51, 159, 221));
        personalizacion.setFocusPainted(false);
        panelMenu.add(personalizacion);
        personalizacion.addMouseListener(ControladorVentanaMenu.getInstance());

        rankings = new JButton();
        rankings.setBounds(150, 400, 200, 35);
        rankings.setText("Rankings");
        rankings.setBackground(new Color(51, 159, 221));
        rankings.setFocusPainted(false);
        panelMenu.add(rankings);
        rankings.addMouseListener(ControladorVentanaMenu.getInstance());

        cerrarSesion = new JButton();
        cerrarSesion.setBounds(355, 10, 125, 35);
        cerrarSesion.setText("Cerrar Sesión");
        cerrarSesion.setBackground(new Color(51, 159, 221));
        cerrarSesion.setFocusPainted(false);
        panelMenu.add(cerrarSesion);
        cerrarSesion.addMouseListener(ControladorVentanaMenu.getInstance());
    }

    static class ImagenFondo extends JPanel{
        private Image imagen;

        public void paint(Graphics g){
            imagen=new ImageIcon(Objects.requireNonNull(getClass().getResource("/main/java/img/tetrisFondo.png"))).getImage();
            g.drawImage(imagen,0,0,getWidth(),getHeight(),this);
            //setOpaque(false);
            super.paintChildren(g);
        }
    }
}