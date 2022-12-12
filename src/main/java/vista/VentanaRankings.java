package vista;

import javax.swing.*;

public class VentanaRankings extends JFrame {

    public static VentanaRankings miVentanaRankings;

    private String tipo;
    private int codUsusario;

    private VentanaRankings(String pTipo, int pCodUsu){
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

    private void setComponentes(){

    }

    public static VentanaRankings getInstance(String pTipo, int pCodUsu){
        if(VentanaRankings.miVentanaRankings ==null){
            VentanaRankings.miVentanaRankings =new VentanaRankings(pTipo,pCodUsu);
        }
        return VentanaRankings.miVentanaRankings;
    }


}
