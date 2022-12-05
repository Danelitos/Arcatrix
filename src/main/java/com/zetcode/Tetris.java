package com.zetcode;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;

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
    private JLabel statusbar;

    public Tetris(int codUsuario,int codPartida, String pNivel) {
        codigoUsuario = codUsuario;
        codigoPartida = codPartida;
        nivel = pNivel;
        logger.info("Playing");
        initUI(codPartida, nivel);
        this.setVisible(true);
    }


    private void initUI(int codPartida, String nivel) {

        getContentPane().setLayout(null);

        statusbar = new JLabel(" 0");
        statusbar.setBounds(0, 344, 264, 28);
        getContentPane().add(statusbar);

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
    }

    public JLabel getStatusBar() {

        return statusbar;
    }
}