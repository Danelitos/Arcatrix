package vista;

import com.google.gson.JsonArray;
import com.zetcode.Central;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;
import javax.swing.JButton;

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

        JButton btnNewButton = new JButton("Cargar Partida");
        btnNewButton.setBounds(438, 256, 120, 38);
        panel.add(btnNewButton);

        JList list = new JList();
        list.setBounds(408, 347, -403, -343);
        panel.add(list);
        ArrayList<JsonArray> listaJSON = new ArrayList<>();
        Central central = new Central();
        central.obtPartidasGuardadas(VentanaMenu.getInstance(0).codigoUsuario);
    }

    public static VentanaPartidasGuardadas getInstance() {
        if (miVentana == null) {
            miVentana = new VentanaPartidasGuardadas();
        }
        return miVentana;
    }
}
