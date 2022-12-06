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
import java.util.Date;

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

        //String [] ejemplo = {"Hola", "Adiós"};
        JsonArray array = new JsonArray();
        DefaultListModel listModel = new DefaultListModel();
        Central central = new Central();
        array = central.obtPartidasGuardadas(VentanaMenu.getInstance(0).codigoUsuario);
        //Asociar el modelo de lista al JList
        for (int i = 0; i < array.size(); i++) {
            JsonObject object = array.getAsJsonObject();
            Gson gson = new Gson();
            Date p = gson.fromJson(object, PartidaGuardada.class).obtFechaHora();
            String s = p.toString();
            listModel.add(i,s);
        }
        JList<String> listaEjemplo = new JList<String>(listModel);
        listaEjemplo.setModel(listModel);
        listaEjemplo.setBounds(10, 10, 300, 300);
        listaEjemplo.setVisibleRowCount(4);
        JScrollPane lamDesp = new JScrollPane(listaEjemplo);
        panel.add(listaEjemplo);
    }

    public static VentanaPartidasGuardadas getInstance() {
        if (miVentana == null) {
            miVentana = new VentanaPartidasGuardadas();
        }
        return miVentana;
    }
}
