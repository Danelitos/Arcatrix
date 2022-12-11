package vista;

import com.sun.jdi.Mirror;
import com.zetcode.Board;
import com.zetcode.Central;
import com.zetcode.GestorBD;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.Objects;

public class VentanaPersonalizacion extends JFrame{
    public static VentanaPersonalizacion miVentanaPersonalizacion;

    private JPanel panelPersonalizacion;
    private JCheckBox classicTetris;

    private JLabel colorFondoText;
    private JLabel colorLadrillosText;
    private JLabel sonidoText;
    private JLabel ZShapeText;
    private JLabel SShapeText;
    private JLabel LineShapeText;
    private JLabel TShapeText;
    private JLabel SquareShapeText;
    private JLabel LShapeText;
    private JLabel MirroredLShapeText;

    private JComboBox<String> colorFondo;
    private JComboBox<String> ZShape;
    private JComboBox<String> SShape;
    private JComboBox<String> LineShape;
    private JComboBox<String> TShape;
    private JComboBox<String> SquareShape;
    private JComboBox<String> LShape;
    private JComboBox<String> MirroredLShape;
    private JTextField sonido;


    private JButton guardarPersonalizacion;
    private JButton volver;

    private int codUsu;

    private VentanaPersonalizacion(int codigoUsu) throws SQLException {
        super("ARCATRIX - PERSONALIZACIÓN");
        codUsu=codigoUsu;
        // crear ventana
        setSize(500, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);  //darle a la X, se acaba el proceso
        setLocationRelativeTo(null); //en el centro de la pantalla

        //inicar componentes
        this.setVisible(true);
        setComponentes();
    }

    public static VentanaPersonalizacion getInstance(int codUsu) throws SQLException {
        if(miVentanaPersonalizacion==null){
            miVentanaPersonalizacion=new VentanaPersonalizacion(codUsu);
        }
        return miVentanaPersonalizacion;
    }

    private void setComponentes() throws SQLException {
        //crear panel
        panelPersonalizacion = new JPanel();
        this.getContentPane().add(panelPersonalizacion);
        panelPersonalizacion.setLayout(null);
        panelPersonalizacion.setVisible(true);

        //crear TEXTO
        colorFondoText = new JLabel();
        colorFondoText.setText("Color del fondo");
        colorFondoText.setBounds(10,20,150,20);
        colorFondoText.setFont(new Font(null,Font.BOLD, 20));
        colorFondoText.setHorizontalAlignment(SwingConstants.CENTER);
        panelPersonalizacion.add(colorFondoText);

        String colorActualFondo=GestorBD.getInstance().obtColorPieza("COLORFONDO",codUsu);
        colorFondo=new JComboBox<>();
        colorFondo.addItem(colorActualFondo);
        colorFondo.addItem("Classic Color");
        colorFondo.addItem("Rojo");
        colorFondo.addItem("Azul");
        colorFondo.addItem("Amarillo");
        colorFondo.addItem("Verde");
        colorFondo.addItem("Negro");
        colorFondo.addItem("Morado");
        colorFondo.setBounds(10,50,150,20);
        panelPersonalizacion.add(colorFondo);

        colorLadrillosText = new JLabel();
        colorLadrillosText.setText("Color de los ladrillos");
        colorLadrillosText.setBounds(10,90,200,20);
        colorLadrillosText.setFont(new Font(null,Font.BOLD, 20));
        colorLadrillosText.setHorizontalAlignment(SwingConstants.CENTER);
        panelPersonalizacion.add(colorLadrillosText);

        //ZSHAPE
        ZShapeText = new JLabel();
        ZShapeText.setText("ZSHAPE");
        ZShapeText.setBounds(-60,120,200,20);
        ZShapeText.setFont(new Font(null,Font.PLAIN, 15));
        ZShapeText.setHorizontalAlignment(SwingConstants.CENTER);
        panelPersonalizacion.add(ZShapeText);

        String colorZSHAPE=GestorBD.getInstance().obtColorPieza("COLORZSHAPE",codUsu);
        ZShape= new JComboBox<>();
        ZShape.addItem(colorZSHAPE);
        ZShape.addItem("Classic Color");
        ZShape.addItem("Rojo");
        ZShape.addItem("Azul");
        ZShape.addItem("Amarillo");
        ZShape.addItem("Verde");
        ZShape.addItem("Negro");
        ZShape.addItem("Morado");
        ZShape.setBounds(10,140,150,20);
        panelPersonalizacion.add(ZShape);

        //SSHAPE
        SShapeText = new JLabel();
        SShapeText.setText("SSHAPE");
        SShapeText.setBounds(-60,160,200,20);
        SShapeText.setFont(new Font(null,Font.PLAIN, 15));
        SShapeText.setHorizontalAlignment(SwingConstants.CENTER);
        panelPersonalizacion.add(SShapeText);

        String colorSSHAPE=GestorBD.getInstance().obtColorPieza("COLORSSHAPE",codUsu);
        SShape= new JComboBox<>();
        SShape.addItem(colorSSHAPE);
        SShape.addItem("Classic Color");
        SShape.addItem("Rojo");
        SShape.addItem("Azul");
        SShape.addItem("Amarillo");
        SShape.addItem("Verde");
        SShape.addItem("Negro");
        SShape.addItem("Morado");
        SShape.setBounds(10,180,150,20);
        panelPersonalizacion.add(SShape);

        //LINESHAPE
        LineShapeText = new JLabel();
        LineShapeText.setText("LINE SHAPE");
        LineShapeText.setBounds(-45,200,200,20);
        LineShapeText.setFont(new Font(null,Font.PLAIN, 15));
        LineShapeText.setHorizontalAlignment(SwingConstants.CENTER);
        panelPersonalizacion.add(LineShapeText);

        String colorLINESHAPE=GestorBD.getInstance().obtColorPieza("COLORLINESHAPE",codUsu);
        LineShape= new JComboBox<>();
        LineShape.addItem(colorLINESHAPE);
        LineShape.addItem("Classic Color");
        LineShape.addItem("Rojo");
        LineShape.addItem("Azul");
        LineShape.addItem("Amarillo");
        LineShape.addItem("Verde");
        LineShape.addItem("Negro");
        LineShape.addItem("Morado");
        LineShape.setBounds(10,220,150,20);
        panelPersonalizacion.add(LineShape);

        //TSHAPE
        TShapeText = new JLabel();
        TShapeText.setText("LINE SHAPE");
        TShapeText.setBounds(-45,240,200,20);
        TShapeText.setFont(new Font(null,Font.PLAIN, 15));
        TShapeText.setHorizontalAlignment(SwingConstants.CENTER);
        panelPersonalizacion.add(TShapeText);

        String colorTSHAPE=GestorBD.getInstance().obtColorPieza("COLORTSHAPE",codUsu);
        TShape= new JComboBox<>();
        TShape.addItem(colorTSHAPE);
        TShape.addItem("Classic Color");
        TShape.addItem("Rojo");
        TShape.addItem("Azul");
        TShape.addItem("Amarillo");
        TShape.addItem("Verde");
        TShape.addItem("Negro");
        TShape.addItem("Morado");
        TShape.setBounds(10,260,150,20);
        panelPersonalizacion.add(TShape);

        //SQUARESHAPE
        SquareShapeText = new JLabel();
        SquareShapeText.setText("SQUARE SHAPE");
        SquareShapeText.setBounds(210,120,200,20);
        SquareShapeText.setFont(new Font(null,Font.PLAIN, 15));
        SquareShapeText.setHorizontalAlignment(SwingConstants.CENTER);
        panelPersonalizacion.add(SquareShapeText);

        String colorSQUARESHAPE=GestorBD.getInstance().obtColorPieza("COLORSQUARESHAPE",codUsu);
        SquareShape= new JComboBox<>();
        SquareShape.addItem(colorSQUARESHAPE);
        SquareShape.addItem("Classic Color");
        SquareShape.addItem("Rojo");
        SquareShape.addItem("Azul");
        SquareShape.addItem("Amarillo");
        SquareShape.addItem("Verde");
        SquareShape.addItem("Negro");
        SquareShape.addItem("Morado");
        SquareShape.setBounds(250,140,150,20);
        panelPersonalizacion.add(SquareShape);

        //LSHAPE
        LShapeText = new JLabel();
        LShapeText.setText("LSHAPE");
        LShapeText.setBounds(180,160,200,20);
        LShapeText.setFont(new Font(null,Font.PLAIN, 15));
        LShapeText.setHorizontalAlignment(SwingConstants.CENTER);
        panelPersonalizacion.add(LShapeText);

        String colorLSHAPE=GestorBD.getInstance().obtColorPieza("COLORLSHAPE",codUsu);
        LShape= new JComboBox<>();
        LShape.addItem(colorLSHAPE);
        LShape.addItem("Classic Color");
        LShape.addItem("Rojo");
        LShape.addItem("Azul");
        LShape.addItem("Amarillo");
        LShape.addItem("Verde");
        LShape.addItem("Negro");
        LShape.addItem("Morado");
        LShape.setBounds(250,180,150,20);
        panelPersonalizacion.add(LShape);

        //MIRRORED LSHAPE
        MirroredLShapeText = new JLabel();
        MirroredLShapeText.setText("MIRRORED LSHAPE");
        MirroredLShapeText.setBounds(225,200,200,20);
        MirroredLShapeText.setFont(new Font(null,Font.PLAIN, 15));
        MirroredLShapeText.setHorizontalAlignment(SwingConstants.CENTER);
        panelPersonalizacion.add(MirroredLShapeText);

        String colorMIRROREDLSHAPE=GestorBD.getInstance().obtColorPieza("COLORMIRROREDLSHAPE",codUsu);
        MirroredLShape= new JComboBox<>();
        MirroredLShape.addItem(colorMIRROREDLSHAPE);
        MirroredLShape.addItem("Classic Color");
        MirroredLShape.addItem("Rojo");
        MirroredLShape.addItem("Azul");
        MirroredLShape.addItem("Amarillo");
        MirroredLShape.addItem("Verde");
        MirroredLShape.addItem("Negro");
        MirroredLShape.addItem("Morado");
        MirroredLShape.setBounds(250,220,150,20);
        panelPersonalizacion.add(MirroredLShape);

        //SONIDO
        sonidoText = new JLabel();
        sonidoText.setText("Sonido                    ");
        sonidoText.setBounds(0,295,200,20);
        sonidoText.setFont(new Font(null,Font.BOLD, 20));
        sonidoText.setHorizontalAlignment(SwingConstants.CENTER);
        panelPersonalizacion.add(sonidoText);

        sonido=new JTextField();
        sonido.setBounds(10,320,150,20);
        panelPersonalizacion.add(sonido);

        //TETRIS CLASICO
        classicTetris=new JCheckBox("Tetris clásico");
        classicTetris.setBounds(250,50,150,20);
        panelPersonalizacion.add(classicTetris);



        //crear BOTONES
        guardarPersonalizacion= new JButton();
        guardarPersonalizacion.setBounds(150,350,200,35);
        guardarPersonalizacion.setText("Guardar Personalización");
        guardarPersonalizacion.setBackground(new Color(51,159,221));
        guardarPersonalizacion.setFocusPainted(false);
        panelPersonalizacion.add(guardarPersonalizacion);
        guardarPersonalizacion.addActionListener(evento -> {
            try {
                actualizarPersonalizacion();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });

        volver= new JButton();
        volver.setBounds(150,400,200,35);
        volver.setText("Volver");
        volver.setBackground(new Color(51,159,221));
        volver.setFocusPainted(false);
        panelPersonalizacion.add(volver);
        volver.addActionListener(evento -> {
            try {
                volverMenu();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
    }

    public void volverMenu() throws SQLException {
        VentanaPersonalizacion.getInstance(codUsu).setVisible(false);
        VentanaMenu.getInstance(codUsu).setVisible(true);
    }

    public void actualizarPersonalizacion() throws SQLException {
        boolean personActualizado;
        String sonidoActualizado=sonido.getText();;
        if (!classicTetris.isSelected()){
            String colorFondoAtualizado= Objects.requireNonNull(colorFondo.getSelectedItem()).toString();

            String colorZSHAPE= Objects.requireNonNull(ZShape.getSelectedItem()).toString();
            String colorSSHAPE= Objects.requireNonNull(SShape.getSelectedItem()).toString();
            String colorLINESHAPE= Objects.requireNonNull(LineShape.getSelectedItem()).toString();
            String colorTSHAPE= Objects.requireNonNull(TShape.getSelectedItem()).toString();
            String colorSQUARESHAPE= Objects.requireNonNull(SquareShape.getSelectedItem()).toString();
            String colorLSHAPE= Objects.requireNonNull(LShape.getSelectedItem()).toString();
            String colorMIRROREDLSHAPE= Objects.requireNonNull(MirroredLShape.getSelectedItem()).toString();
            personActualizado=GestorBD.getInstance().actualizarPersonalizacion(colorFondoAtualizado,colorZSHAPE,colorSSHAPE,colorLINESHAPE,colorTSHAPE,colorSQUARESHAPE,colorLSHAPE,colorMIRROREDLSHAPE,sonidoActualizado,codUsu);
            Central.getInstance().actualizarDatosPersonaliza(codUsu,colorFondoAtualizado,colorZSHAPE,colorSSHAPE,colorLINESHAPE,colorTSHAPE,colorSQUARESHAPE,colorLSHAPE,colorMIRROREDLSHAPE,sonidoActualizado);
        }
        else{
            String classicColor="Classic Color";
            personActualizado=GestorBD.getInstance().actualizarPersonalizacion(classicColor,classicColor,classicColor,classicColor,classicColor,classicColor,classicColor,classicColor,classicColor,codUsu);
            Central.getInstance().actualizarDatosPersonaliza(codUsu,classicColor,classicColor,classicColor,classicColor,classicColor,classicColor,classicColor,classicColor,sonidoActualizado);
            Central.getInstance().obtPersonalizacion(codUsu);
        }
        if (personActualizado){
            JOptionPane.showMessageDialog(VentanaPersonalizacion.getInstance(codUsu),"Se ha actualizado con exito","ACTUALIZACIÓN EXITOSO",JOptionPane.INFORMATION_MESSAGE);
            Central.getInstance().obtPersonalizacion(codUsu);
        }
        else {
            JOptionPane.showMessageDialog(VentanaPersonalizacion.getInstance(codUsu),"Ha habido un error al actualizar los datos","ACTUALIZACIÓN ERRONEO",JOptionPane.ERROR_MESSAGE);
        }


    }

}
