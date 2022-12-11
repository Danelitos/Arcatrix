package com.zetcode;

import com.zetcode.Shape.Tetrominoe;
import vista.VentanaElegirNivel;
import vista.VentanaMenu;

import javax.swing.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.*;
import java.sql.SQLException;

public class Board extends JPanel implements MouseListener {

    private int BOARD_WIDTH; //F-10 M-15 D-20
    private int BOARD_HEIGHT; // F-22 M-27 D-32
    private int PERIOD_INTERVAL; //F-300 M-200 D-100

    private Timer timer;
    private boolean isFallingFinished = false;
    private boolean isPaused = false;
    private int numLinesRemoved = 0; //Este es el número de puntos de la partida
    private int curX = 0;
    private int curY = 0;
    private JLabel statusbar;
    private Shape curPiece;
    private Tetrominoe[] board;
    private Tetris parent;
    private int codPartida;
    private String nivel;

    public Board(Tetris pParent, int pCodPartida, String pNivel) throws SQLException {
        parent = pParent;
        codPartida = pCodPartida;
        nivel = pNivel;
        setTamanoYVelocidad(nivel);
        String colorFondo = GestorBD.getInstance().obtColorPieza("COLORFONDO", parent.codigoUsuario);
        if (!colorFondo.equals("Classic Color")) {
            setBackground(obtColorFondo(colorFondo));
        }
        initBoard(parent);
        board = pParent.getCasillasOcupadas();
    }

    public Tetrominoe[] getBoard() {
        return board;
    }

    private void setTamanoYVelocidad(String nivel) {

        switch (nivel.toLowerCase()) {
            case "facil":
                System.out.printf("ha entrado");
                BOARD_WIDTH = 10;
                BOARD_HEIGHT = 22;
                PERIOD_INTERVAL = 300;
                break;
            case "medio":
                BOARD_WIDTH = 15;
                BOARD_HEIGHT = 27;
                PERIOD_INTERVAL = 200;
                break;

            case "dificil":
                BOARD_WIDTH = 20;
                BOARD_HEIGHT = 32;
                PERIOD_INTERVAL = 100;
                break;
        }

    }

    private void initBoard(Tetris parent) {

        setFocusable(true);
        statusbar = parent.getStatusBar();
        addKeyListener(new TAdapter());
    }

    private int squareWidth() {

        return (int) getSize().getWidth() / BOARD_WIDTH;
    }

    private int squareHeight() {

        return (int) getSize().getHeight() / BOARD_HEIGHT;
    }

    private Tetrominoe shapeAt(int x, int y) {

        return board[(y * BOARD_WIDTH) + x];
    }

    public void start() {

        curPiece = new Shape();
        board = new Tetrominoe[BOARD_WIDTH * BOARD_HEIGHT];

        clearBoard();
        if (parent.getCasillasOcupadas() != null) {
            board = parent.getCasillasOcupadas();
        }
        newPiece();

        timer = new Timer(PERIOD_INTERVAL, new GameCycle());
        timer.start();
    }

    private void pause() {

        isPaused = !isPaused;

        if (isPaused) {

            statusbar.setText("paused");
        } else {

            statusbar.setText(String.valueOf(numLinesRemoved));
        }

        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        try {
            doDrawing(g);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void doDrawing(Graphics g) throws SQLException {

        var size = getSize();
        int boardTop = (int) size.getHeight() - BOARD_HEIGHT * squareHeight();

        for (int i = 0; i < BOARD_HEIGHT; i++) {

            for (int j = 0; j < BOARD_WIDTH; j++) {

                Tetrominoe shape = shapeAt(j, BOARD_HEIGHT - i - 1);

                if (shape != Tetrominoe.NoShape) {

                    drawSquare(g, j * squareWidth(),
                            boardTop + i * squareHeight(), shape);
                }
            }
        }

        if (curPiece.getShape() != Tetrominoe.NoShape) {

            for (int i = 0; i < 4; i++) {

                int x = curX + curPiece.x(i);
                int y = curY - curPiece.y(i);

                drawSquare(g, x * squareWidth(),
                        boardTop + (BOARD_HEIGHT - y - 1) * squareHeight(),
                        curPiece.getShape());
            }
        }
    }

    private void dropDown() {

        int newY = curY;

        while (newY > 0) {

            if (!tryMove(curPiece, curX, newY - 1)) {

                break;
            }

            newY--;
        }

        pieceDropped();
    }

    private void oneLineDown() {

        if (!tryMove(curPiece, curX, curY - 1)) {

            pieceDropped();
        }
    }

    private void clearBoard() {

        for (int i = 0; i < BOARD_HEIGHT * BOARD_WIDTH; i++) {

            board[i] = Tetrominoe.NoShape;
        }
    }

    private void pieceDropped() {

        for (int i = 0; i < 4; i++) {

            int x = curX + curPiece.x(i);
            int y = curY - curPiece.y(i);
            board[(y * BOARD_WIDTH) + x] = curPiece.getShape();
        }

        removeFullLines();

        if (!isFallingFinished) {

            newPiece();
        }
    }

    private void newPiece() {

        curPiece.setRandomShape();
        curX = BOARD_WIDTH / 2 + 1;
        curY = BOARD_HEIGHT - 1 + curPiece.minY();

        if (!tryMove(curPiece, curX, curY)) {

            curPiece.setShape(Tetrominoe.NoShape);
            timer.stop();

            var msg = String.format("Game over. Score: %d", numLinesRemoved);
            statusbar.setText(msg);
        }
        //ACTUALIZAR RANKINGS
    }

    private boolean tryMove(Shape newPiece, int newX, int newY) {

        for (int i = 0; i < 4; i++) {

            int x = newX + newPiece.x(i);
            int y = newY - newPiece.y(i);

            if (x < 0 || x >= BOARD_WIDTH || y < 0 || y >= BOARD_HEIGHT) {

                return false;
            }

            if (shapeAt(x, y) != Tetrominoe.NoShape) {

                return false;
            }
        }

        curPiece = newPiece;
        curX = newX;
        curY = newY;

        repaint();

        return true;
    }

    private void removeFullLines() {

        int numFullLines = 0;

        for (int i = BOARD_HEIGHT - 1; i >= 0; i--) {

            boolean lineIsFull = true;

            for (int j = 0; j < BOARD_WIDTH; j++) {

                if (shapeAt(j, i) == Tetrominoe.NoShape) {

                    lineIsFull = false;
                    break;
                }
            }

            if (lineIsFull) {

                numFullLines++;

                for (int k = i; k < BOARD_HEIGHT - 1; k++) {
                    for (int j = 0; j < BOARD_WIDTH; j++) {
                        board[(k * BOARD_WIDTH) + j] = shapeAt(j, k + 1);
                    }
                }
            }
        }

        if (numFullLines > 0) {

            numLinesRemoved += numFullLines;

            statusbar.setText(String.valueOf(numLinesRemoved));
            isFallingFinished = true;
            curPiece.setShape(Tetrominoe.NoShape);
        }
    }

    private void drawSquare(Graphics g, int x, int y, Tetrominoe shape) throws SQLException {

        Color colors[] = {new Color(0, 0, 0),
                obtColorPieza(GestorBD.getInstance().obtColorPieza("COLORZSHAPE", parent.codigoUsuario), shape),
                obtColorPieza(GestorBD.getInstance().obtColorPieza("COLORSSHAPE", parent.codigoUsuario), shape),
                obtColorPieza(GestorBD.getInstance().obtColorPieza("COLORLINESHAPE", parent.codigoUsuario), shape),
                obtColorPieza(GestorBD.getInstance().obtColorPieza("COLORTSHAPE", parent.codigoUsuario), shape),
                obtColorPieza(GestorBD.getInstance().obtColorPieza("COLORSQUARESHAPE", parent.codigoUsuario), shape),
                obtColorPieza(GestorBD.getInstance().obtColorPieza("COLORLSHAPE", parent.codigoUsuario), shape),
                obtColorPieza(GestorBD.getInstance().obtColorPieza("COLORMIRROREDLSHAPE", parent.codigoUsuario), shape)
        };

        var color = colors[shape.ordinal()];

        g.setColor(color);
        g.fillRect(x + 1, y + 1, squareWidth() - 2, squareHeight() - 2);

        g.setColor(color.brighter());
        g.drawLine(x, y + squareHeight() - 1, x, y);
        g.drawLine(x, y, x + squareWidth() - 1, y);

        g.setColor(color.darker());
        g.drawLine(x + 1, y + squareHeight() - 1,
                x + squareWidth() - 1, y + squareHeight() - 1);
        g.drawLine(x + squareWidth() - 1, y + squareHeight() - 1,
                x + squareWidth() - 1, y + 1);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getSource() instanceof JButton) {
            this.timer.stop();
            System.out.println(parent.codigoUsuario);
            VentanaMenu.getInstance(parent.codigoUsuario).setVisible(true);
            parent.setVisible(false);
            Central.getInstance().guardarPartida(parent.codigoUsuario, parent);
        } else {
            System.exit(0);
        }
        System.out.println("Partida guardada");

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    private class GameCycle implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            doGameCycle();
        }
    }

    private void doGameCycle() {

        update();
        repaint();
    }

    private void update() {

        if (isPaused) {

            return;
        }

        if (isFallingFinished) {

            isFallingFinished = false;
            newPiece();
        } else {

            oneLineDown();
        }
    }

    class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {

            if (curPiece.getShape() == Tetrominoe.NoShape) {

                return;
            }

            int keycode = e.getKeyCode();

            // Java 12 switch expressions
            switch (keycode) {

                case KeyEvent.VK_P -> pause();
                case KeyEvent.VK_LEFT -> tryMove(curPiece, curX - 1, curY);
                case KeyEvent.VK_RIGHT -> tryMove(curPiece, curX + 1, curY);
                case KeyEvent.VK_DOWN -> tryMove(curPiece.rotateRight(), curX, curY);
                case KeyEvent.VK_UP -> tryMove(curPiece.rotateLeft(), curX, curY);
                case KeyEvent.VK_SPACE -> dropDown();
                case KeyEvent.VK_D -> oneLineDown();
            }
        }
    }

    public Color obtColorPieza(String colorNombre, Tetrominoe shape) {
        //TODO COLORES DE LAS PIEZAS
        Color Black = new Color(0, 0, 0);
        Color Blue = new Color(0, 66, 255);
        Color Red = new Color(255, 0, 0);
        Color Green = new Color(42, 255, 0);
        Color Yellow = new Color(232, 255, 0);
        Color Purple = new Color(220, 0, 255);
        Color classicZSHAPE = new Color(204, 102, 102);
        Color classicSSHAPE = new Color(102, 204, 102);
        Color classicLINESHAPE = new Color(102, 102, 204);
        Color classicTSHAPE = new Color(204, 204, 102);
        Color classicSQUARESHAPE = new Color(204, 102, 204);
        Color classicLSHAPE = new Color(102, 204, 204);
        Color classicMIRROREDLSHAPE = new Color(218, 170, 0);
        if (colorNombre.equals("Negro")) {
            return Black;
        } else if (colorNombre.equals("Azul")) {
            return Blue;
        } else if (colorNombre.equals("Rojo")) {
            return Red;
        } else if (colorNombre.equals("Verde")) {
            return Green;
        } else if (colorNombre.equals("Amarillo")) {
            return Yellow;
        } else if (colorNombre.equals("Morado")) {
            return Purple;
        } else {
            if (shape.equals(Tetrominoe.ZShape)) {
                return classicZSHAPE;
            } else if (shape.equals(Tetrominoe.SShape)) {
                return classicSSHAPE;
            } else if (shape.equals(Tetrominoe.LineShape)) {
                return classicLINESHAPE;
            } else if (shape.equals(Tetrominoe.SquareShape)) {
                return classicSQUARESHAPE;
            } else if (shape.equals(Tetrominoe.TShape)) {
                return classicTSHAPE;
            } else if (shape.equals(Tetrominoe.LShape)) {
                return classicLSHAPE;
            } else {
                return classicMIRROREDLSHAPE;
            }


        }

    }

    public Color obtColorFondo(String colorNombre) {
        Color Black = new Color(0, 0, 0);
        Color Blue = new Color(0, 66, 255);
        Color Red = new Color(255, 0, 0);
        Color Green = new Color(42, 255, 0);
        Color Yellow = new Color(232, 255, 0);
        Color Purple = new Color(220, 0, 255);
        return switch (colorNombre) {
            case "Negro" -> Black;
            case "Azul" -> Blue;
            case "Rojo" -> Red;
            case "Verde" -> Green;
            case "Amarillo" -> Yellow;
            default -> Purple;
        };

    }
}