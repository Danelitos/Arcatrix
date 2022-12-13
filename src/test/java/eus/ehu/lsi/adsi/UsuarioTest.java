package eus.ehu.lsi.adsi;

import com.google.gson.JsonArray;
import com.zetcode.*;
import org.junit.Test;

import java.sql.SQLException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import static org.junit.Assert.*;

public class UsuarioTest {
    private DatosPersonalizacion personalizacion1=new DatosPersonalizacion("Classic Color","Classic Color","Classic Color","Classic Color","Classic Color","Classic Color","Classic Color","Classic Color","Sonido");
    private DatosPersonalizacion personalizacion2=new DatosPersonalizacion("Rojo","Rojo","Rojo","Rojo","Rojo","Rojo","Rojo","Rojo","Hola");
    private Shape.Tetrominoe[] casillasOcupadas=new Shape.Tetrominoe[5];
    private Date fecha= Date.from(Instant.now());
    private Usuario usu1= new Usuario(1,"Danel","123",personalizacion1);
    private Tetris tetris=new Tetris(1,1,"Facil",casillasOcupadas,12);
    //private PartidaGuardada partidaGuardada=new PartidaGuardada(usu1,tetris,fecha);

    public UsuarioTest() throws SQLException {

    }


    @Test
    public void esUsuario() {
        assertTrue(usu1.esUsuario("Danel","123"));
        assertTrue(usu1.esUsuario("Danel"));
    }


    @Test
    public void partidasGuardadasUsuario() {
        System.out.println(usu1.partidasGuardadasUsuario());
    }

    @Test
    public void obtPartida() {

    }

    @Test
    public void getBoard() {

    }

    @Test
    public void obtTetris() {
        assertEquals(usu1.obtTetris(1),tetris);
    }

}
