package vista;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.zetcode.ListaRankings;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VentanaRankings extends JFrame implements ActionListener {

    public static VentanaRankings miVentanaRankings;

    private JPanel panel;

    private JTable tabla = null;
    private JsonArray lista;


    private JButton btnAtras;
    private String tipo;
    private int codUsusario;

    private VentanaRankings(String pTipo, int pCodUsu) {
        this.tipo = pTipo;
        this.codUsusario = pCodUsu;
        // crear ventana
        setTitle("Tetris");
        setSize(500, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);  //darle a la X, se acaba el proceso
        setLocationRelativeTo(null); //en el centro de la pantalla

        //inicar componentes
        this.setVisible(true);
        setComponentes();

    }

    private void setComponentes() {


        //crear panel
        panel = new JPanel();
        panel.setBackground(Color.darkGray);
        this.getContentPane().add(panel);
        panel.setLayout(null);
        panel.setVisible(true);

        //crear TEXTO
        JLabel texto = new JLabel();
        texto.setText("Ranking " + tipo);
        texto.setBounds(150, 75, 200, 20);
        texto.setFont(new Font(null, Font.PLAIN, 20));
        texto.setHorizontalAlignment(SwingConstants.CENTER);
        texto.setForeground(Color.white);
        panel.add(texto);

        //crear boton atras
        btnAtras = new JButton();
        btnAtras.setBounds(0, 0, 75, 50);
        btnAtras.setText("Atras");
        btnAtras.setBackground(new Color(0, 255, 255));
        texto.setFont(new Font(null, Font.PLAIN, 20));
        panel.add(btnAtras);
        btnAtras.addActionListener(this);

        //crear lista

        if (tipo == "General") {
            lista = ListaRankings.getInstance().getListaGeneral();
            this.crearTabla(lista);

        } else if (tipo == "Personal") {
            lista = ListaRankings.getInstance().getListaPersonal(this.codUsusario);
            this.crearTabla(lista);

        } else if (tipo == "facil") {
            lista = ListaRankings.getInstance().getListaNivel("facil");
            this.crearTabla(lista);
            ;
        } else if (tipo == "medio") {
            lista = ListaRankings.getInstance().getListaNivel("medio");
            this.crearTabla(lista);

        } else {
            lista = ListaRankings.getInstance().getListaNivel("dificil");
            this.crearTabla(lista);
        }


        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollLista = new JScrollPane();
        scrollLista.setViewportView(tabla);
        scrollLista.setVisible(true);
        scrollLista.setBounds(120, 110, 260, 260);
        panel.add(scrollLista);

    }

    public static VentanaRankings getInstance(String pTipo, int pCodUsu) {
        if (VentanaRankings.miVentanaRankings == null) {
            VentanaRankings.miVentanaRankings = new VentanaRankings(pTipo, pCodUsu);
        }
        return VentanaRankings.miVentanaRankings;
    }

    private void crearTabla(JsonArray jArray) {
        String[] nombreCol = {"Nombre", "Puntuacion"};
        Object[][] datos = new Object[jArray.size()][2];

        for (int i = 0; i < jArray.size(); i++) {
            JsonObject o = jArray.get(i).getAsJsonObject();
            datos[i][0] = o.get("Nombre Usuario").getAsString();
            datos[i][1] = o.get("Puntuacion").getAsInt();
        }

        tabla = new JTable(datos, nombreCol);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (btnAtras.equals(source)) {
            this.setVisible(false);
            VentanaElegirRanking.miVentanaElegirRanking = null;
            VentanaElegirRanking.getInstance(codUsusario);
        }

    }
}
