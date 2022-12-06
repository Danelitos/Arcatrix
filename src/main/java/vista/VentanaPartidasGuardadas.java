package vista;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.zetcode.Central;
import com.zetcode.PartidaGuardada;
import controlador.ControladorVentanaPartidasGuardadas;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class VentanaPartidasGuardadas extends JFrame {

    private JPanel contentPane;
    private static VentanaPartidasGuardadas miVentana;

    /**
     * Create the frame.
     */
    private VentanaPartidasGuardadas() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 674, 398);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        JPanel panel = new JPanel();
        contentPane.add(panel, BorderLayout.CENTER);
        panel.setLayout(null);

        JButton cargarPartida = new JButton("Cargar Partida");
        cargarPartida.setBounds(438, 256, 120, 38);
        panel.add(cargarPartida);
        cargarPartida.addMouseListener(ControladorVentanaPartidasGuardadas.getInstance());

        JButton volver = new JButton("Volver");
        volver.setBounds(438, 300, 120, 38);
        panel.add(volver);
        volver.addMouseListener(ControladorVentanaPartidasGuardadas.getInstance());

        String [] ejemplo = {"Hola", "Adiós"};
        JList<String> listaEjemplo = new JList<String>(ejemplo);
        listaEjemplo.setBackground(Color.red);
        listaEjemplo.setBounds(408, 347, -403, -343);
        listaEjemplo.setVisibleRowCount(4);
        JScrollPane lamDesp = new JScrollPane(listaEjemplo);
        panel.add(listaEjemplo);
        panel.setVisible(true);
        listaEjemplo.setVisible(true);
        this.paint(this.getGraphics());
        //JsonArray array;
        //DefaultListModel listModel = new DefaultListModel();
        //Central central = new Central();
        //array = central.obtPartidasGuardadas(VentanaMenu.getInstance(0).codigoUsuario);
        //Asociar el modelo de lista al JList
        //list.setModel(listModel);
        //for (int i = 0; i < array.size(); i++) {
        //    JsonObject object = array.getAsJsonObject();
        //    Gson gson = new Gson();
        //    PartidaGuardada p = gson.fromJson(object, PartidaGuardada.class);
        //    listModel.addElement(p);
        //}
    }

    public static VentanaPartidasGuardadas getInstance() {
        if (miVentana == null) {
            miVentana = new VentanaPartidasGuardadas();
        }
        return miVentana;
    }
}
