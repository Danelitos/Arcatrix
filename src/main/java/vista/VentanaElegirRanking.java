package vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.nio.file.FileSystemNotFoundException;

public class VentanaElegirRanking extends JFrame implements ActionListener{

    private static VentanaElegirRanking miVentanaElegirRanking;
    private JButton btnGeneral, btnPorNiveles, btnMiRancking;
    private JPanel panel;

    public String tRanking = "PorNiveles";

    public int codUsuario;

    private VentanaElegirRanking(int pCodUsu){

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
    private void setComponentes(){
        //crear panel
        panel = new JPanel();
        panel.setBackground(Color.darkGray);
        this.getContentPane().add(panel);
        panel.setLayout(null);
        panel.setVisible(true);

        //crear TEXTO
        JLabel texto = new JLabel();
        texto.setText("Tipo de Rancking:");
        texto.setBounds(150,75,200,20);
        texto.setFont(new Font(null,Font.PLAIN, 20));
        texto.setHorizontalAlignment(SwingConstants.CENTER);
        texto.setForeground(Color.white);
        panel.add(texto);

        //crear BOTONES
        btnGeneral= new JButton();
        btnGeneral.setBounds(150,150,200,35);
        btnGeneral.setText("Rancking General");
        btnGeneral.setBackground(new Color(146, 248, 133));
        btnGeneral.setFocusPainted(false);
        panel.add(btnGeneral);
        btnGeneral.addActionListener(this);

        btnPorNiveles= new JButton();
        btnPorNiveles.setBounds(150,200,200,35);
        btnPorNiveles.setText("Ranckings por Niveles ");
        btnPorNiveles.setBackground(new Color(248, 248, 133));
        btnPorNiveles.setFocusPainted(false);
        panel.add(btnPorNiveles);
        btnPorNiveles.setHorizontalAlignment(SwingConstants.CENTER);
        btnPorNiveles.addActionListener(this);

        btnMiRancking= new JButton();
        btnMiRancking.setBounds(150,250,200,35);
        btnMiRancking.setText("RanckingPersonal");
        btnMiRancking.setBackground(new Color(248, 133, 133));
        btnMiRancking.setFocusPainted(false);
        panel.add(btnMiRancking);
        btnMiRancking.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e){
        tRanking="nada";
        Object source = e.getSource();
        if (btnGeneral.equals(source)) {
            this.setVisible(false);
            VentanaRankings.miVentanaRankings=null;
            VentanaRankings.getInstance("General",codUsuario);

        } else if (btnPorNiveles.equals(source)) {
            this.setVisible(false);
            VentanaElegirRankingsPorNivel.miVentanaElegirRankingsPorNivel=null;
            VentanaElegirRankingsPorNivel.getInstance(codUsuario);

        } else if (btnMiRancking.equals(source)) {
            this.setVisible(false);
            VentanaRankings.miVentanaRankings=null;
            VentanaRankings.getInstance("Personal",codUsuario);
        }

    }
}