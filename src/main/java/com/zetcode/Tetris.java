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
    public int puntos;
    private Ranking rankings;
    private JLabel statusbar;
    public Board board;

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

        statusbar = new JLabel(puntos + "");
        statusbar.setBounds(0, 344, 264, 28);
        getContentPane().add(statusbar);

        //Habrá que añadir los métodos para completar el tablero con las piezas en el caso de cargarPartida
        board = new Board(this, codPartida, nivel, codigoUsuario);
        board.setBounds(0, 0, 264, 344);
        getContentPane().add(board);

        JButton btnNewButton = new JButton("Guardar");
        btnNewButton.setBounds(122, 344, 80, 28);
        getContentPane().add(btnNewButton);
        btnNewButton.addActionListener(evento -> board.guardarPartida());

        JButton botonFinalizar = new JButton("Fin");
        botonFinalizar.setBounds(205, 344, 55, 28);
        getContentPane().add(botonFinalizar);
        botonFinalizar.addActionListener(evento -> board.finalizar());
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