package vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.security.GeneralSecurityException;

public class VentanaElegirRankingsPorNivel extends JFrame implements ActionListener {


    public static VentanaElegirRankingsPorNivel miVentanaElegirRankingsPorNivel;
    private JButton btnRFAcil, btnRMedio, btnRDificil;
    private JPanel panel;

    public int codUsuario;


    private VentanaElegirRankingsPorNivel(int pCodUsu) {

        this.codUsuario = pCodUsu;
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
        texto.setText("Tipo de Ranking:");
        texto.setBounds(150, 75, 200, 20);
        texto.setFont(new Font(null, Font.PLAIN, 20));
        texto.setHorizontalAlignment(SwingConstants.CENTER);
        texto.setForeground(Color.white);
        panel.add(texto);

        //crear BOTONES
        btnRFAcil = new JButton();
        btnRFAcil.setBounds(150, 150, 200, 35);
        btnRFAcil.setText("Nivel Facil");
        btnRFAcil.setBackground(new Color(146, 248, 133));
        btnRFAcil.setFocusPainted(false);
        panel.add(btnRFAcil);
        btnRFAcil.addActionListener(this);


        btnRMedio = new JButton();
        btnRMedio.setBounds(150, 200, 200, 35);
        btnRMedio.setText("Nivel Medio");
        btnRMedio.setBackground(new Color(248, 248, 133));
        btnRMedio.setFocusPainted(false);
        panel.add(btnRMedio);
        btnRMedio.addActionListener(this);

        btnRDificil = new JButton();
        btnRDificil.setBounds(150, 250, 200, 35);
        btnRDificil.setText("Nivel Dificil");
        btnRDificil.setBackground(new Color(248, 133, 133));
        btnRDificil.setFocusPainted(false);
        panel.add(btnRDificil);
        btnRDificil.addActionListener(this);

    }

    public static VentanaElegirRankingsPorNivel getInstance(int codUsuario) {
        if (VentanaElegirRankingsPorNivel.miVentanaElegirRankingsPorNivel == null) {
            VentanaElegirRankingsPorNivel.miVentanaElegirRankingsPorNivel = new VentanaElegirRankingsPorNivel(codUsuario);
        }
        return VentanaElegirRankingsPorNivel.miVentanaElegirRankingsPorNivel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        Object source = e.getSource();

        if (btnRFAcil.equals(source)) {
            this.setVisible(false);
            VentanaRankings.miVentanaRankings = null;
            VentanaRankings.getInstance("facil", codUsuario);

        } else if (btnRMedio.equals(source)) {
            this.setVisible(false);
            VentanaRankings.miVentanaRankings = null;
            VentanaRankings.getInstance("medio", codUsuario);

        } else if (btnRDificil.equals(source)) {
            this.setVisible(false);
            VentanaRankings.miVentanaRankings = null;
            VentanaRankings.getInstance("dificil", codUsuario);
        }

    }
}

