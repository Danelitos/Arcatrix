package eus.ehu.lsi.adsi;

import static org.junit.Assert.*;
import com.google.gson.JsonArray;
import com.zetcode.*;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.sql.SQLException;
import java.time.Instant;
import java.util.Date;

public class CentralTest {
    private DatosPersonalizacion personalizacion1;
    private Usuario usu1;
    private Tetris tetris;
    private PartidaGuardada partidaGuardada;

    @Before
    public void setUp() throws SQLException {
        GestorBD.getInstance().addUsuario("Danel","Danel","1");
        DatosPersonalizacion personalizacion1=new DatosPersonalizacion("Classic Color","Classic Color","Classic Color","Classic Color","Classic Color","Classic Color","Classic Color","Classic Color","Sonido");
        Usuario usu1=new Usuario(1,"Danel","1",personalizacion1);
        Tetris tetris=new Tetris(1,1,"Facil",new Shape.Tetrominoe[5],0);
        PartidaGuardada partidaGuardada=new PartidaGuardada(usu1,tetris, Date.from(Instant.now()));
    }


    public CentralTest() throws SQLException {
    }


    @Test
    public void crearUsuario() {
        Central.getInstance().crearUsuario(1,"Danel","1");
        assertEquals(usu1.getCodUsuario(),1);

    }

    public void recuperarContrasena(String correo) {

    }

    @Test
    public void actualizarDatosPersonaliza() {
        Central.getInstance().crearUsuario(1,"Danel","1");
        DatosPersonalizacion personalizacion=Central.getInstance().obtPersonalizacion(1);
        assertEquals(personalizacion.getColorFondo(),"Classic Color");
        assertEquals(personalizacion.getColorZSHAPE(),"Classic Color");
        assertEquals(personalizacion.getColorSSHAPE(),"Classic Color");
        assertEquals(personalizacion.getColorLINESHAPE(),"Classic Color");
        assertEquals(personalizacion.getColorSQUARESHAPE(),"Classic Color");
        assertEquals(personalizacion.getColorTSHAPE(),"Classic Color");
        assertEquals(personalizacion.getColorLSHAPE(),"Classic Color");
        assertEquals(personalizacion.getColorMIRROREDLSHAPE(),"Classic Color");
        assertEquals(personalizacion.getSonido(),"Sonido");
        Central.getInstance().actualizarDatosPersonaliza(1,"Rojo","Rojo","Rojo","Rojo","Rojo","Rojo","Rojo","Rojo","Hola");
        personalizacion=Central.getInstance().obtPersonalizacion(1);
        assertEquals(personalizacion.getColorFondo(),"Rojo");
        assertEquals(personalizacion.getColorZSHAPE(),"Rojo");
        assertEquals(personalizacion.getColorSSHAPE(),"Rojo");
        assertEquals(personalizacion.getColorLINESHAPE(),"Rojo");
        assertEquals(personalizacion.getColorSQUARESHAPE(),"Rojo");
        assertEquals(personalizacion.getColorTSHAPE(),"Rojo");
        assertEquals(personalizacion.getColorLSHAPE(),"Rojo");
        assertEquals(personalizacion.getColorMIRROREDLSHAPE(),"Rojo");
        assertEquals(personalizacion.getSonido(),"Hola");
    }

    @Test
    public void guardarPartida() {
        /*Central.getInstance().crearUsuario(1,"Danel","1","1");
        Central.getInstance().guardarPartida(1,tetris);
        System.out.println(Central.getInstance().obtPartidasGuardadas(1));
        */
    }

    @Test
    public void obtPartidasGuardadas() {

    }

    @Test
    public void cargarPartida() {

    }

    @Test
    public void obtLadrillos() {
        Central.getInstance().crearUsuario(1,"Danel","1");
        Central.getInstance().obtLadrillos(1,1);
    }

    @Test
    public void obtPersonalizacion() {
        Central.getInstance().crearUsuario(1,"Danel","1");
        DatosPersonalizacion personalizacion=Central.getInstance().obtPersonalizacion(1);
        assertEquals(personalizacion.getColorFondo(),personalizacion1.getColorFondo());
        assertEquals(personalizacion.getColorZSHAPE(),personalizacion1.getColorZSHAPE());
        assertEquals(personalizacion.getColorSSHAPE(),personalizacion1.getColorSSHAPE());
        assertEquals(personalizacion.getColorLINESHAPE(),personalizacion1.getColorLINESHAPE());
        assertEquals(personalizacion.getColorSQUARESHAPE(),personalizacion1.getColorSQUARESHAPE());
        assertEquals(personalizacion.getColorTSHAPE(),personalizacion1.getColorTSHAPE());
        assertEquals(personalizacion.getColorLSHAPE(),personalizacion1.getColorLSHAPE());
        assertEquals(personalizacion.getColorMIRROREDLSHAPE(),personalizacion1.getColorMIRROREDLSHAPE());
        assertEquals(personalizacion.getSonido(),personalizacion1.getSonido());
    }
}
