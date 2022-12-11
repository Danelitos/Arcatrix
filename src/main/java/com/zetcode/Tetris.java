package com.zetcode;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.sql.SQLException;

/*
Java Tetris game clone

Author: Jan Bodnar
Website: https://zetcode.com
 */
public class Tetris extends JFrame {

    private static final Logger logger = LogManager.getLogger(Tetris.class);
    public int codigoPartida;

    public String nivel;
    public int codigoUsuario;
    private Shape.Tetrominoe[] casillasOcupadas;
    private int puntos;
    private Ranking rankings;
    private JLabel statusbar;

    public Tetris(int codUsuario, int codPartida, String pNivel, Shape.Tetrominoe[] pCasillasOcupadas, int pPuntos) throws SQLException {
        codigoUsuario = codUsuario;
        codigoPartida = codPartida;
        nivel = pNivel;
        casillasOcupadas = pCasillasOcupadas;
        puntos = pPuntos;
        logger.info("Playing");
        this.casillasOcupadas = initUI(codPartida, nivel);
        this.setVisible(true);
    }

    public Shape.Tetrominoe[] getCasillasOcupadas() {
        return casillasOcupadas;
    }

    public Integer getPuntos() {
        return puntos;
    }

    private Shape.Tetrominoe[] initUI(int codPartida, String nivel) throws SQLException {

        getContentPane().setLayout(null);

        statusbar = new JLabel(" 0");
        statusbar.setBounds(0, 344, 264, 28);
        getContentPane().add(statusbar);

        //Habrá que añadir los métodos para completar el tablero con las piezas en el caso de cargarPartida
        var board = new Board(this, codPartida, nivel);
        board.setBounds(0, 0, 264, 344);
        getContentPane().add(board);

        JButton btnNewButton = new JButton("Guardar");
        btnNewButton.addMouseListener(board);
        btnNewButton.setBounds(159, 344, 105, 28);
        getContentPane().add(btnNewButton);
        board.start();

        setTitle("Tetris");
        setSize(278, 412);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        return board.getBoard();
    }

    public JLabel getStatusBar() {

        return statusbar;
    }

    public int getCodPartida() {
        return this.codigoPartida;
    }
}