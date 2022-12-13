package vista;


import com.google.gson.JsonArray;
import com.zetcode.*;
import com.zetcode.Shape;

import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sound.sampled.Clip;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class VentanaPartidasGuardadas extends JFrame {

    private JPanel contentPane, panel;
    public static VentanaPartidasGuardadas miVentana;

    public static JList<String> miListaString;

    private VentanaPartidasGuardadas() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 674, 398);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        panel = new JPanel();
        contentPane.add(panel, BorderLayout.CENTER);
        panel.setLayout(null);

        JButton cargarPartida = new JButton("Cargar Partida");
        cargarPartida.setBounds(438, 256, 120, 38);
        panel.add(cargarPartida);
        cargarPartida.addActionListener(evento -> cargarPartida());
        this.getPartidasGuardadas();

        JButton volver = new JButton("Volver");
        volver.setBounds(438, 300, 120, 38);
        panel.add(volver);
        volver.addActionListener(evento -> volverMenu());
    }

    public static VentanaPartidasGuardadas getInstance() {
        if (miVentana == null) {
            miVentana = new VentanaPartidasGuardadas();
        }
        return miVentana;
    }

    public JList<String> getListaString() {
        return miListaString;
    }

    public void getPartidasGuardadas() {
        JsonArray array = new JsonArray();
        array = Central.getInstance().obtPartidasGuardadas(VentanaMenu.getInstance(0).getCodigoUsu());
        //Asociar el modelo de lista al JList
        List<String> exampleList = new ArrayList<String>();
        for (int i = 0; i < array.size(); i++) {
            exampleList.add(array.get(i).getAsString());
        }
        int size = exampleList.size();
        String[] stringArray = exampleList.toArray(new String[size]);
        JList<String> listaString = new JList<String>(stringArray);
        listaString.setVisibleRowCount(4);
        JScrollPane lamDesp = new JScrollPane(listaString);
        listaString.setBounds(10, 10, 300, 300);
        panel.add(lamDesp);
        panel.add(listaString);
        miListaString = listaString;
    }

    public void volverMenu() {
        System.out.println("Vuelve al menu principal");
        //TODO PARA QUE FUNCIONE DE MOMENTO
        VentanaMenu.getInstance(0).setVisible(true);
        VentanaPartidasGuardadas.getInstance().setVisible(false);
    }

    public void cargarPartida() {
        System.out.println("Se carga la partida");
        //TODO PARA QUE FUNCIONE DE MOMENTO
        VentanaPartidasGuardadas.getInstance().setVisible(false);
        //Obtenemos la fechaHora de la ventana de Partidas guardadas y el código de usuario de la VentanaMenu
        String s = VentanaPartidasGuardadas.getInstance().getListaString().getSelectedValue();
        JsonArray laPartida = Central.getInstance().cargarPartida(VentanaMenu.getInstance(0).codigoUsu, s);
        //Crear la partida
        Integer codUsuarioPartida = laPartida.get(0).getAsInt();
        Integer codigoPartida = laPartida.get(1).getAsInt();
        String nivel = laPartida.get(2).getAsString();
        Integer puntos = laPartida.get(3).getAsInt();
        String[] array = Central.getInstance().obtLadrillos(codUsuarioPartida, codigoPartida);
        com.zetcode.Shape.Tetrominoe[] board = new com.zetcode.Shape.Tetrominoe[array.length];
        for (int i = 0; i < array.length; i++) {
            board[i] = Shape.Tetrominoe.valueOf(array[i]);
        }
        System.out.println("partida instancia: " + codigoPartida++);
        //hacer cuenta atras
        //VentanaNivelElegido.getInstance(codUsuarioPartida, nivel).cuentaAtras();
        //crear interfaz del juego
        try {
            String sonidoElegido= GestorBD.getInstance().obtColorPieza("SONIDO",VentanaMenu.getInstance(0).codigoUsu);
            Sonido.getInstance().reproducirSonido("src/main/resources/audio/" + sonidoElegido.toString() + ".wav","Clip Cancion");
            //Sonido.getInstance().getClip("Clip Cancion").loop(Clip.LOOP_CONTINUOUSLY);
            new Tetris(codUsuarioPartida, codigoPartida++, nivel, board, puntos);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        //VentanaNivelElegido.getInstance(codUsuarioPartida, nivel).setVisible(false);
    }
}
