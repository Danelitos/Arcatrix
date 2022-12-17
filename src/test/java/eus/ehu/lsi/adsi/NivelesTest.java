package eus.ehu.lsi.adsi;

import com.zetcode.*;
import org.junit.Before;
import org.junit.Test;
import vista.VentanaElegirNivel;
import vista.VentanaMenu;
import vista.VentanaNivelElegido;

import java.awt.event.ActionEvent;
//import java.sql.Date;
import java.sql.SQLException;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class NivelesTest {

    @Test
    public void NivelesTest() {
        Shape shape = new Shape();
        assertTrue(shape.minX() == 0);
    }

    @Before
    public void setUp() throws SQLException {
        //añadir usuario a base datos
        GestorBD.getInstance().addUsuario("Adrian","adrian@gmail.com","12345678");

        //añadir usuario a objetos con su personalizacion
        DatosPersonalizacion personalizacion1=new DatosPersonalizacion("Classic Color","Classic Color","Classic Color","Classic Color","Classic Color","Classic Color","Classic Color","Classic Color","Sonido");
        Usuario usu1=new Usuario(2,"Adrian","12345678",personalizacion1);
        Central.getInstance().crearUsuario(2,"Adrian","12345678");
    }

    @Test
    public void elegirNivelTest(){
        //al elegir nivel, se pasa a la siguiente interfaz el nivel correcto
        //se inicia nueva partdia sin haber iniciado ninguna antes

        //FACIL
        iniciarFacil();

        //MEDIO
        iniciarMedio();

        //DIFICIL
        iniciarDificil();

        //iniciar nueva juego habiendo iniciado otra anteriormente
        //cogemos la ultima de dificil y iniciamos una facil de nuevo
        //las demas ya estan probadas, ya que hemos iniciado una facil y despues una medio
        //y despues una dificil

        iniciarFacil();
        
        //CARGAR UNA PARTIDA DE OTRO NIVEL, Y INICIAR PARTIDA CON NUEVO NIVEL
        
        Partida partida = new Partida(1,"medio",2,new Shape.Tetrominoe[405],5);
        String fecha=new Date().toString();

        Central.getInstance().guardarPartida(1,"medio",2,partida.getCasillasOcupadas(),partida.puntos,fecha.toString());
        Central.getInstance().cargarPartida(2,fecha);

        //nueva partida en FACIL
        iniciarFacil();

        //CARGAMOS DIFICIL
        partida = new Partida(2,"dificil",2,new Shape.Tetrominoe[405],5);
        fecha=new Date().toString();

        Central.getInstance().guardarPartida(1,partida.nivel,partida.codigoUsuario,partida.getCasillasOcupadas(),partida.puntos,fecha);
        Central.getInstance().cargarPartida(2,fecha);

        //iniciamos en MEDIO
        iniciarMedio();

        //nueva partida en FACIL
        iniciarFacil();

        //CARGAMOS FACIL
        partida = new Partida(2,"facil",2,new Shape.Tetrominoe[405],5);
        fecha=new Date().toString();

        Central.getInstance().guardarPartida(1,partida.nivel,partida.codigoUsuario,partida.getCasillasOcupadas(),partida.puntos,fecha);
        Central.getInstance().cargarPartida(2,fecha);

        //iniciamos en DIFICIL
        iniciarDificil();
    }

    public void iniciarFacil(){
        //FACIL
        VentanaElegirNivel.nivel="facil";
        VentanaElegirNivel.getInstance(2);
        VentanaNivelElegido.miNivelElegido=null;
        VentanaNivelElegido.getInstance(2,VentanaElegirNivel.nivel);
        assertEquals("facil",VentanaNivelElegido.nivelElegido);

        Tetris juego= Central.getInstance().testIniciarPartida(2,0,VentanaNivelElegido.nivelElegido,null,0);

        //comprobar nivel, tamaño tablero y velocidad
        assertEquals("facil", juego.nivel);
        assertEquals(10, juego.board.getBoardWidth());
        assertEquals(22, juego.board.getBoardHeight());
        assertEquals(300, juego.board.getVelocidad());
        assertEquals(220, juego.board.getBoard().length);
    }

    public void iniciarMedio(){
        VentanaElegirNivel.nivel="medio";
        VentanaElegirNivel.getInstance(2);
        VentanaNivelElegido.miNivelElegido=null;
        VentanaNivelElegido.getInstance(2,VentanaElegirNivel.nivel);
        assertEquals("medio",VentanaNivelElegido.nivelElegido);


        Tetris juego = Central.getInstance().testIniciarPartida(2,0,VentanaNivelElegido.nivelElegido,null,0);
        //comprobar nivel, tamaño tablero y velocidad
        assertEquals("medio", juego.nivel);
        assertEquals(15, juego.board.getBoardWidth());
        assertEquals(27, juego.board.getBoardHeight());
        assertEquals(200, juego.board.getVelocidad());
        assertEquals(405, juego.board.getBoard().length);
    }

    public void iniciarDificil(){
        VentanaElegirNivel.nivel="dificil";
        VentanaElegirNivel.getInstance(1);
        VentanaNivelElegido.miNivelElegido=null;
        VentanaNivelElegido.getInstance(1,VentanaElegirNivel.nivel);
        assertEquals("dificil",VentanaNivelElegido.nivelElegido);

        Tetris juego= Central.getInstance().testIniciarPartida(2,0,VentanaNivelElegido.nivelElegido,null,0);
        //comprobar nivel, tamaño tablero y velocidad
        assertEquals("dificil", juego.nivel);
        assertEquals(20, juego.board.getBoardWidth());
        assertEquals(32, juego.board.getBoardHeight());
        assertEquals(100, juego.board.getVelocidad());
        assertEquals(640, juego.board.getBoard().length);
    }
}
