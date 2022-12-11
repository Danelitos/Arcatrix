package vista;

import com.zetcode.GaleriaIconos;


import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class JPanelImagen extends JPanel {
    private ImageIcon imagen;
    private String tipoVentana;

    public JPanelImagen(String pTipoVentana) {
        super();
        tipoVentana = pTipoVentana;

        JLabel display = new JLabel();
        JLabel message = null;
        ImageIcon imageIcon = GaleriaIconos.getInstance().obtenerImagen(tipoVentana);


        display.setIcon(imageIcon);

        add(display);
    }
}