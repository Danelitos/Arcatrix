package vista;

import controlador.ControladorVentanaPersonaliza;

import javax.swing.*;
import java.awt.*;

public class VentanaPersonalizacion extends JFrame{
    private static VentanaPersonalizacion miVentanaPersonalizacion;

    private JPanel panelPersonalizacion;

    private JLabel colorFondoText;
    private JLabel colorLadrillosText;
    private JLabel sonidoText;

    private JComboBox<String> colorFondo;
    private JComboBox<String> colorLadrillos;
    private JTextField sonido;

    private JButton guardarPersonalizacion;
    private JButton volver;

    private int codUsu;

    private VentanaPersonalizacion(int codigoUsu){
        super("ARCATRIX - PERSONALIZACIÓN");
        codUsu=codigoUsu;
        // crear ventana
        setSize(500, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);  //darle a la X, se acaba el proceso
        setLocationRelativeTo(null); //en el centro de la pantalla

        //inicar componentes
        this.setVisible(true);
        setComponentes();
    }

    public static VentanaPersonalizacion getInstance(int codUsu){
        if(miVentanaPersonalizacion==null){
            miVentanaPersonalizacion=new VentanaPersonalizacion(codUsu);
        }
        return miVentanaPersonalizacion;
    }

    private void setComponentes(){
        //crear panel
        panelPersonalizacion = new JPanel();
        this.getContentPane().add(panelPersonalizacion);
        panelPersonalizacion.setLayout(null);
        panelPersonalizacion.setVisible(true);

        //crear TEXTO
        colorFondoText = new JLabel();
        colorFondoText.setText("Color del fondo");
        colorFondoText.setBounds(0,20,150,20);
        colorFondoText.setFont(new Font(null,Font.PLAIN, 20));
        colorFondoText.setHorizontalAlignment(SwingConstants.CENTER);
        panelPersonalizacion.add(colorFondoText);


        colorFondo=new JComboBox<>();
        colorFondo.addItem("Rojo");
        colorFondo.addItem("Azul");
        colorFondo.addItem("Amarillo");
        colorFondo.addItem("Verde");
        colorFondo.addItem("Negro");
        colorFondo.setBounds(10,50,150,20);
        panelPersonalizacion.add(colorFondo);

        colorLadrillosText = new JLabel();
        colorLadrillosText.setText("Color de los ladrillos");
        colorLadrillosText.setBounds(0,150,200,20);
        colorLadrillosText.setFont(new Font(null,Font.PLAIN, 20));
        colorLadrillosText.setHorizontalAlignment(SwingConstants.CENTER);
        panelPersonalizacion.add(colorLadrillosText);

        colorLadrillos= new JComboBox<>();
        colorLadrillos.setBounds(10,180,150,20);
        panelPersonalizacion.add(colorLadrillos);

        sonidoText = new JLabel();
        sonidoText.setText("Sonido                    ");
        sonidoText.setBounds(0,280,200,20);
        sonidoText.setFont(new Font(null,Font.PLAIN, 20));
        sonidoText.setHorizontalAlignment(SwingConstants.CENTER);
        panelPersonalizacion.add(sonidoText);

        sonido=new JTextField();
        sonido.setBounds(10,310,150,20);
        panelPersonalizacion.add(sonido);


        //crear BOTONES
        guardarPersonalizacion= new JButton();
        guardarPersonalizacion.setBounds(150,350,200,35);
        guardarPersonalizacion.setText("Guardar Personalización");
        guardarPersonalizacion.setBackground(new Color(51,159,221));
        guardarPersonalizacion.setFocusPainted(false);
        panelPersonalizacion.add(guardarPersonalizacion);
        guardarPersonalizacion.addMouseListener(ControladorVentanaPersonaliza.getInstance());
        guardarPersonalizacion.addActionListener(evento -> actualizarPersonalizacion());

        volver= new JButton();
        volver.setBounds(150,400,200,35);
        volver.setText("Volver");
        volver.setBackground(new Color(51,159,221));
        volver.setFocusPainted(false);
        panelPersonalizacion.add(volver);
        volver.addActionListener(evento -> volverMenu());
    }

    public void volverMenu(){
        VentanaPersonalizacion.getInstance(codUsu).setVisible(false);
        VentanaMenu.getInstance(codUsu).setVisible(true);
    }

    public void actualizarPersonalizacion(){
        String colorFondoAtualizado=colorFondo.getName();
        String colorLadrillosActualizado=colorLadrillos.getName();
        String sonidoActualizado=sonido.getText();
    }

}
