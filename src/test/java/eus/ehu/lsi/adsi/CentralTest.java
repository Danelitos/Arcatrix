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
        Tetris tetris=new Tetris(1,1,"Facil",new Shape.Tetrominoe[220],0);
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
        //Por cada prueba, vemos si en la partida se guarda tanto en el registro de partidas guardadas
        //como en la lista de partidas guardadas del usuario (BASE DE DATOS????)
        Central.getInstance().crearUsuario(1,"Danel","1");
        //Guardar partida por primera vez en modo fácil
        Date fecha1 = new Date();
        //CREAR NUEVA PARTIDA
        Central.getInstance().guardarPartida(1,tetris,fecha1);
        assertTrue(usu1.getListaPartidasGuardadas().contains(tetris));
        assertEquals(Central.getInstance().obtPartidasGuardadas(1).get(1).toString(), fecha1.toString());
        System.out.println(Central.getInstance().obtPartidasGuardadas(1));
        //Guardar partida por primera vez en modo intermedio
        Date fecha2 = new Date();
        Central.getInstance().guardarPartida(1,tetris,fecha2);
        assertEquals(Central.getInstance().obtPartidasGuardadas(1).get(2).toString(), fecha2.toString());
        System.out.println(Central.getInstance().obtPartidasGuardadas(1));
        //Guardar partida por primera vez en modo difícil
        Date fecha3 = new Date();
        Central.getInstance().guardarPartida(1,tetris,fecha3);
        assertEquals(Central.getInstance().obtPartidasGuardadas(1).get(3).toString(), fecha3.toString());
        System.out.println(Central.getInstance().obtPartidasGuardadas(1));
        //Guardar partida por segunda vez en modo fácil
        Central.getInstance().cargarPartida(usu1.getCodUsuario(),fecha1.toString());
        Date fecha4 = new Date();
        Central.getInstance().guardarPartida(1,tetris,fecha4);
        assertEquals(Central.getInstance().obtPartidasGuardadas(1).get(4).toString(), fecha4.toString());
        System.out.println(Central.getInstance().obtPartidasGuardadas(1));
        //Guardar partida por segunda vez en modo intermedio
        Central.getInstance().cargarPartida(usu1.getCodUsuario(),fecha2.toString());
        Date fecha5 = new Date();
        Central.getInstance().guardarPartida(1,tetris,fecha5);
        assertEquals(Central.getInstance().obtPartidasGuardadas(1).get(5).toString(), fecha5.toString());
        System.out.println(Central.getInstance().obtPartidasGuardadas(1));
        //Guardar partida por segunda vez en modo difícil
        Central.getInstance().cargarPartida(usu1.getCodUsuario(),fecha3.toString());
        Date fecha6 = new Date();
        Central.getInstance().guardarPartida(1,tetris,fecha6);
        assertEquals(Central.getInstance().obtPartidasGuardadas(1).get(6).toString(), fecha6.toString());
        System.out.println(Central.getInstance().obtPartidasGuardadas(1));
        //Guardar partida n veces en modo fácil
        Central.getInstance().cargarPartida(usu1.getCodUsuario(),fecha4.toString());
        Date fecha7 = new Date();
        Central.getInstance().guardarPartida(1,tetris,fecha7);
        Central.getInstance().cargarPartida(usu1.getCodUsuario(),fecha7.toString());
        Date fecha8 = new Date();
        Central.getInstance().guardarPartida(1,tetris,fecha8);
        Central.getInstance().cargarPartida(usu1.getCodUsuario(),fecha8.toString());
        Date fecha9 = new Date();
        Central.getInstance().guardarPartida(1,tetris,fecha9);
        assertEquals(Central.getInstance().obtPartidasGuardadas(1).get(9).toString(), fecha9.toString());
        System.out.println(Central.getInstance().obtPartidasGuardadas(1));
        //Guardar partida n veces en modo medio
        Central.getInstance().cargarPartida(usu1.getCodUsuario(),fecha5.toString());
        Date fecha10 = new Date();
        Central.getInstance().guardarPartida(1,tetris,fecha10);
        Central.getInstance().cargarPartida(usu1.getCodUsuario(),fecha10.toString());
        Date fecha11 = new Date();
        Central.getInstance().guardarPartida(1,tetris,fecha11);
        Central.getInstance().cargarPartida(usu1.getCodUsuario(),fecha11.toString());
        Date fecha12 = new Date();
        Central.getInstance().guardarPartida(1,tetris,fecha12);
        assertEquals(Central.getInstance().obtPartidasGuardadas(1).get(12).toString(), fecha12.toString());
        System.out.println(Central.getInstance().obtPartidasGuardadas(1));
        //Guardar partida n veces en modo difícil
        Central.getInstance().cargarPartida(usu1.getCodUsuario(),fecha6.toString());
        Date fecha13 = new Date();
        Central.getInstance().guardarPartida(1,tetris,fecha13);
        Central.getInstance().cargarPartida(usu1.getCodUsuario(),fecha13.toString());
        Date fecha14 = new Date();
        Central.getInstance().guardarPartida(1,tetris,fecha14);
        Central.getInstance().cargarPartida(usu1.getCodUsuario(),fecha14.toString());
        Date fecha15 = new Date();
        Central.getInstance().guardarPartida(1,tetris,fecha15);
        assertEquals(Central.getInstance().obtPartidasGuardadas(1).get(15).toString(), fecha15.toString());
        System.out.println(Central.getInstance().obtPartidasGuardadas(1));

        //PRUEBAS DE CAJA BLANCA
        //Creas nueva partida

        Central.getInstance().guardarPartida(-1,tetris,Date.from(Instant.now()));
        //Printea un error porque no existe el usuario

        //La partida es null
        Central.getInstance().guardarPartida(1,null,Date.from(Instant.now()));
        //Printea un error porque la partida es null

        //La fecha es null
        //Creas nueva partida

        Central.getInstance().guardarPartida(1,tetris,null);
        //Printea un error porque la fecha es null

        //La partida y la fecha son null
        //Creas nueva partida

        Central.getInstance().guardarPartida(1,null,null);
        //Printea un error porque la partida y la fecha son null
    }

    @Test
    public void obtPartidasGuardadas() {
        //No existe el usuario
        Central.getInstance().obtPartidasGuardadas(-1);
        //Printea un error porque no existe el usuario
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
