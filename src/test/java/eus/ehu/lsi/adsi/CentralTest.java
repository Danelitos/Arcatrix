package eus.ehu.lsi.adsi;

import static java.lang.Thread.*;
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

    @Before
    public void setUp() throws SQLException {
        GestorBD.getInstance().addUsuario("Danel","Danel","1");
        DatosPersonalizacion personalizacion1=new DatosPersonalizacion("Classic Color","Classic Color","Classic Color","Classic Color","Classic Color","Classic Color","Classic Color","Classic Color","Sonido");
        Usuario usu1=new Usuario(1,"Danel","1",personalizacion1);
        Central.getInstance().crearUsuario(1,"Danel","1");
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
        //¡¡¡¡IMPORTANTE!!!!
        //REALIZAR LAS PRUEBAS SÓLO DEL MÉTODO EN CUESTIÓN
        //USO THREAD.STOP PARA QUE CAMBIEN LAS FECHAS

        //Por cada prueba, vemos si la partida se guarda en la lista de partidas guardadas del usuario
        Usuario usu1=new Usuario(1,"Adrián","1",personalizacion1);
        Central.getInstance().crearUsuario(usu1.getCodUsuario(), usu1.getNombre(),"1");

        //Guardar partida por primera vez en modo fácil
        Date fecha1 = new Date();
        //CREAR NUEVA PARTIDA
        Shape.Tetrominoe[] boardFacil = new Shape.Tetrominoe[220];
        for (int i=0;i<boardFacil.length;i++){
            boardFacil[i] = Shape.Tetrominoe.NoShape;
        }
        Partida partidaFacil1 = new Partida(1,"Facil", usu1.getCodUsuario(), boardFacil,0);
        Central.getInstance().guardarPartida(partidaFacil1.codigoPartida,partidaFacil1.nivel, partidaFacil1.codigoUsuario, partidaFacil1.getCasillasOcupadas(), partidaFacil1.puntos, fecha1.toString());
        String s1 = Central.getInstance().obtPartidasGuardadas(1).get(0).toString();
        s1 = s1.replaceAll("\"", "");
        assertEquals(s1, fecha1.toString());

        //Guardar partida por primera vez en modo intermedio
        Date fecha2 = new Date();
        Shape.Tetrominoe[] boardMedio = new Shape.Tetrominoe[405];
        for (int i=0;i<boardMedio.length;i++){
            boardMedio[i] = Shape.Tetrominoe.NoShape;
        }
        Partida partidaMedio1 = new Partida(2,"Medio", usu1.getCodUsuario(), boardMedio,0);
        Central.getInstance().guardarPartida(partidaMedio1.codigoPartida,partidaMedio1.nivel, partidaMedio1.codigoUsuario, partidaMedio1.getCasillasOcupadas(), partidaMedio1.puntos, fecha2.toString());
        String s2 = Central.getInstance().obtPartidasGuardadas(1).get(1).toString();
        s2 = s2.replaceAll("\"", "");
        assertEquals(s2, fecha2.toString());

        //Guardar partida por primera vez en modo difícil
        Date fecha3 = new Date();
        Shape.Tetrominoe[] boardDificil = new Shape.Tetrominoe[640];
        for (int i=0;i<boardDificil.length;i++){
            boardDificil[i] = Shape.Tetrominoe.NoShape;
        }
        Partida partidaDificil1 = new Partida(3,"Dificil", usu1.getCodUsuario(), boardDificil,0);
        Central.getInstance().guardarPartida(partidaDificil1.codigoPartida, partidaDificil1.nivel,  partidaDificil1.codigoUsuario, partidaDificil1.getCasillasOcupadas(), partidaDificil1.puntos, fecha3.toString());
        String s3 = Central.getInstance().obtPartidasGuardadas(1).get(2).toString();
        s3 = s3.replaceAll("\"", "");
        assertEquals(s3, fecha3.toString());

        //Guardar partida por segunda vez en modo fácil
        JsonArray jsonArray1 = Central.getInstance().cargarPartida(usu1.getCodUsuario(),fecha1.toString());
        String[] ladrillos1 = Central.getInstance().obtLadrillos(jsonArray1.get(0).getAsInt(),jsonArray1.get(1).getAsInt());
        com.zetcode.Shape.Tetrominoe[] board1 = new com.zetcode.Shape.Tetrominoe[ladrillos1.length];
        for (int i = 0; i < ladrillos1.length; i++) {
            board1[i] = Shape.Tetrominoe.valueOf(ladrillos1[i]);
        }

        //PARO EL HILO PARA QUE CAMBIE LA FECHA
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        Date fecha4 = new Date();
        Partida partidaFacil2 = new Partida(4,jsonArray1.get(2).toString(), jsonArray1.get(0).getAsInt(), board1,jsonArray1.get(3).getAsInt());
        Central.getInstance().guardarPartida(partidaFacil2.codigoPartida,partidaFacil2.nivel, partidaFacil2.codigoUsuario, partidaFacil2.getCasillasOcupadas(), partidaFacil2.puntos, fecha4.toString());
        String s4 = Central.getInstance().obtPartidasGuardadas(1).get(3).toString();
        s4 = s4.replaceAll("\"", "");
        assertEquals(s4, fecha4.toString());

        //Guardar partida por segunda vez en modo intermedio
        JsonArray jsonArray2 = Central.getInstance().cargarPartida(usu1.getCodUsuario(),fecha2.toString());
        String[] ladrillos2 = Central.getInstance().obtLadrillos(jsonArray2.get(0).getAsInt(),jsonArray2.get(1).getAsInt());
        com.zetcode.Shape.Tetrominoe[] board2 = new com.zetcode.Shape.Tetrominoe[ladrillos2.length];
        for (int i = 0; i < ladrillos2.length; i++) {
            board2[i] = Shape.Tetrominoe.valueOf(ladrillos2[i]);
        }
        Date fecha5 = new Date();
        Partida partidaMedio2 = new Partida(5,jsonArray2.get(2).toString(), jsonArray2.get(0).getAsInt(), board2,jsonArray2.get(3).getAsInt());
        Central.getInstance().guardarPartida(partidaMedio2.codigoPartida,partidaMedio2.nivel, partidaMedio2.codigoUsuario, partidaMedio2.getCasillasOcupadas(), partidaMedio2.puntos, fecha5.toString());
        String s5 = Central.getInstance().obtPartidasGuardadas(1).get(4).toString();
        s5 = s5.replaceAll("\"", "");
        assertEquals(s5, fecha5.toString());

        //Guardar partida por segunda vez en modo difícil
        JsonArray jsonArray3 = Central.getInstance().cargarPartida(usu1.getCodUsuario(),fecha3.toString());
        String[] ladrillos3 = Central.getInstance().obtLadrillos(jsonArray3.get(0).getAsInt(),jsonArray3.get(1).getAsInt());
        com.zetcode.Shape.Tetrominoe[] board3 = new com.zetcode.Shape.Tetrominoe[ladrillos3.length];
        for (int i = 0; i < ladrillos3.length; i++) {
            board3[i] = Shape.Tetrominoe.valueOf(ladrillos3[i]);
        }
        Date fecha6 = new Date();
        Partida partidaDificil2 = new Partida(6,jsonArray3.get(2).toString(), jsonArray3.get(0).getAsInt(), board3,jsonArray3.get(3).getAsInt());
        Central.getInstance().guardarPartida(partidaDificil2.codigoPartida, partidaDificil2.nivel,  partidaDificil2.codigoUsuario, partidaDificil2.getCasillasOcupadas(), partidaDificil2.puntos, fecha6.toString());
        String s6 = Central.getInstance().obtPartidasGuardadas(1).get(5).toString();
        s6 = s6.replaceAll("\"", "");
        assertEquals(s6, fecha6.toString());

        //Guardar partida n veces (3) en modo fácil
        JsonArray jsonArray4 = Central.getInstance().cargarPartida(usu1.getCodUsuario(),fecha4.toString());
        String[] ladrillos4 = Central.getInstance().obtLadrillos(jsonArray1.get(0).getAsInt(),jsonArray1.get(1).getAsInt());
        com.zetcode.Shape.Tetrominoe[] board4 = new com.zetcode.Shape.Tetrominoe[ladrillos4.length];
        for (int i = 0; i < ladrillos4.length; i++) {
            board4[i] = Shape.Tetrominoe.valueOf(ladrillos4[i]);
        }

        //PARO EL HILO PARA QUE CAMBIE LA FECHA
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        Date fecha7 = new Date();
        Partida partidaFacil3 = new Partida(7,jsonArray1.get(2).toString(), jsonArray1.get(0).getAsInt(), board4,jsonArray1.get(3).getAsInt());
        Central.getInstance().guardarPartida(partidaFacil3.codigoPartida,partidaFacil3.nivel, partidaFacil3.codigoUsuario, partidaFacil3.getCasillasOcupadas(), partidaFacil3.puntos, fecha7.toString());
        String s7 = Central.getInstance().obtPartidasGuardadas(1).get(6).toString();
        s7 = s7.replaceAll("\"", "");
        assertEquals(s7, fecha7.toString());

        //Guardar partida n veces (3) en modo intermedio
        JsonArray jsonArray5 = Central.getInstance().cargarPartida(usu1.getCodUsuario(),fecha5.toString());
        String[] ladrillos5 = Central.getInstance().obtLadrillos(jsonArray1.get(0).getAsInt(),jsonArray1.get(1).getAsInt());
        com.zetcode.Shape.Tetrominoe[] board5 = new com.zetcode.Shape.Tetrominoe[ladrillos5.length];
        for (int i = 0; i < ladrillos5.length; i++) {
            board5[i] = Shape.Tetrominoe.valueOf(ladrillos5[i]);
        }
        Date fecha8 = new Date();
        Partida partidaMedio3 = new Partida(8,jsonArray1.get(2).toString(), jsonArray1.get(0).getAsInt(), board5,jsonArray1.get(3).getAsInt());
        Central.getInstance().guardarPartida(partidaMedio3.codigoPartida,partidaMedio3.nivel, partidaMedio3.codigoUsuario, partidaMedio3.getCasillasOcupadas(), partidaMedio3.puntos, fecha8.toString());
        String s8 = Central.getInstance().obtPartidasGuardadas(1).get(7).toString();
        s8 = s8.replaceAll("\"", "");
        assertEquals(s8, fecha8.toString());

        //Guardar partida n veces (3) en modo difícil
        JsonArray jsonArray6 = Central.getInstance().cargarPartida(usu1.getCodUsuario(),fecha6.toString());
        String[] ladrillos6 = Central.getInstance().obtLadrillos(jsonArray1.get(0).getAsInt(),jsonArray1.get(1).getAsInt());
        com.zetcode.Shape.Tetrominoe[] board6 = new com.zetcode.Shape.Tetrominoe[ladrillos6.length];
        for (int i = 0; i < ladrillos6.length; i++) {
            board6[i] = Shape.Tetrominoe.valueOf(ladrillos6[i]);
        }
        Date fecha9 = new Date();
        Partida partidaDificil3 = new Partida(9,jsonArray1.get(2).toString(), jsonArray1.get(0).getAsInt(), board6,jsonArray1.get(3).getAsInt());
        Central.getInstance().guardarPartida(partidaDificil3.codigoPartida, partidaDificil3.nivel,  partidaDificil3.codigoUsuario, partidaDificil3.getCasillasOcupadas(), partidaDificil3.puntos, fecha9.toString());
        String s9 = Central.getInstance().obtPartidasGuardadas(1).get(8).toString();
        s9 = s9.replaceAll("\"", "");
        assertEquals(s9, fecha9.toString());


        //PRUEBAS DE CAJA BLANCA

        //No existe el usuario
        Partida p11 = new Partida(11,"Facil",-1,new Shape.Tetrominoe[240],0);
        Central.getInstance().guardarPartida(p11.codigoPartida, p11.nivel,  p11.codigoUsuario, p11.getCasillasOcupadas(), p11.puntos,Date.from(Instant.now()).toString());
        //Printea un error porque no existe el usuario

        //El tablero es null
        Central.getInstance().guardarPartida(p11.codigoPartida, p11.nivel,  1, null, p11.puntos,Date.from(Instant.now()).toString());
        //Printea un error porque la partida es null

        //La fecha es null
        Central.getInstance().guardarPartida(p11.codigoPartida, p11.nivel,  1, p11.getCasillasOcupadas(), p11.puntos,null);
        //Printea un error porque la fecha es null

        //La partida y la fecha son null
        Central.getInstance().guardarPartida(p11.codigoPartida, p11.nivel,  1, null, p11.puntos,null);
        //Printea un error porque la partida y la fecha son null

    }

    @Test
    public void obtPartidasGuardadas() {
        //¡¡¡¡IMPORTANTE!!!!
        //REALIZAR LAS PRUEBAS SÓLO DEL MÉTODO EN CUESTIÓN
        //USO THREAD.STOP PARA QUE CAMBIEN LAS FECHAS


        //Obtiene correctamente las partidas guardadas del usuario
        //He llamado a este método en las pruebas de guardarPartida, pero aquí pruebo con tres partidas nuevas
        Usuario usu1=new Usuario(1,"Adrián","1",personalizacion1);
        Central.getInstance().crearUsuario(usu1.getCodUsuario(), usu1.getNombre(),"1");

        //Guardar tres partidas
        Date fecha10 = new Date();
        Partida partidaFacil10 = new Partida(1,"Facil", usu1.getCodUsuario(),new Shape.Tetrominoe[240],0);
        Central.getInstance().guardarPartida(partidaFacil10.codigoPartida, partidaFacil10.nivel, partidaFacil10.codigoUsuario, partidaFacil10.getCasillasOcupadas(), partidaFacil10.puntos, fecha10.toString());
        //PARO EL HILO PARA QUE CAMBIE LA FECHA
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Date fecha11 = new Date();
        Partida partidaFacil11 = new Partida(1,"Facil", usu1.getCodUsuario(),new Shape.Tetrominoe[240],0);
        Central.getInstance().guardarPartida(partidaFacil11.codigoPartida, partidaFacil11.nivel, partidaFacil11.codigoUsuario, partidaFacil11.getCasillasOcupadas(),partidaFacil11.puntos,fecha11.toString());
        //PARO EL HILO PARA QUE CAMBIE LA FECHA
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Date fecha12 = new Date();
        Partida partidaFacil12 = new Partida(1,"Facil", usu1.getCodUsuario(),new Shape.Tetrominoe[240],0);
        Central.getInstance().guardarPartida(partidaFacil12.codigoPartida, partidaFacil12.nivel, partidaFacil12.codigoUsuario, partidaFacil12.getCasillasOcupadas(), partidaFacil12.puntos, fecha12.toString());
        String s10 = Central.getInstance().obtPartidasGuardadas(usu1.getCodUsuario()).get(0).toString();
        s10 = s10.replaceAll("\"", "");
        assertEquals(s10, fecha10.toString());
        String s11 = Central.getInstance().obtPartidasGuardadas(usu1.getCodUsuario()).get(1).toString();
        s11 = s11.replaceAll("\"", "");
        assertEquals(s11, fecha11.toString());
        String s12 = Central.getInstance().obtPartidasGuardadas(usu1.getCodUsuario()).get(2).toString();
        s12 = s12.replaceAll("\"", "");
        assertEquals(s12, fecha12.toString());
        System.out.println(Central.getInstance().obtPartidasGuardadas(usu1.getCodUsuario()).toString());

        //CAJA BLANCA

        //No existe el usuario
        Central.getInstance().obtPartidasGuardadas(-1);
        //Printea un error porque no existe el usuario

    }

    @Test
    public void cargarPartida() {
        //¡¡¡¡IMPORTANTE!!!!
        //REALIZAR LAS PRUEBAS SÓLO DEL MÉTODO EN CUESTIÓN
        //USO THREAD.STOP PARA QUE CAMBIEN LAS FECHAS

        Usuario usu1=new Usuario(1,"Adrián","1",personalizacion1);
        Central.getInstance().crearUsuario(usu1.getCodUsuario(), usu1.getNombre(),"1");

        //Este método también lo he utilizado al guardarPartida, ya que para guardar más de una vez
        //una misma partida tengo que cargarla previamente
        Date fecha = new Date();
        //CREAR NUEVA PARTIDA FÁCIL
        Shape.Tetrominoe[] boardFacil = new Shape.Tetrominoe[220];
        for (int i=0;i<boardFacil.length;i++){
            boardFacil[i] = Shape.Tetrominoe.NoShape;
        }
        Partida partidaFacil1 = new Partida(1,"Facil", usu1.getCodUsuario(),boardFacil ,0);
        //Guardas la partida
        Central.getInstance().guardarPartida(partidaFacil1.codigoPartida, partidaFacil1.nivel, partidaFacil1.codigoUsuario, partidaFacil1.getCasillasOcupadas(), partidaFacil1.puntos, fecha.toString());
        //Cargas la partida
        JsonArray jsonArray1 = Central.getInstance().cargarPartida(usu1.getCodUsuario(),fecha.toString());
        String[] ladrillos1 = Central.getInstance().obtLadrillos(jsonArray1.get(0).getAsInt(),jsonArray1.get(1).getAsInt());
        com.zetcode.Shape.Tetrominoe[] board1 = new com.zetcode.Shape.Tetrominoe[ladrillos1.length];
        for (int i = 0; i < ladrillos1.length; i++) {
            board1[i] = Shape.Tetrominoe.valueOf(ladrillos1[i]);
        }

        //Compruebas que los valores:codUsuario,nivel,puntos y su tablero son iguales
        assertEquals(partidaFacil1.codigoUsuario,jsonArray1.get(0).getAsInt());
        String s = jsonArray1.get(2).toString();
        s = s.replaceAll("\"", "");
        assertEquals(partidaFacil1.nivel,s);
        assertEquals(partidaFacil1.puntos,jsonArray1.get(3).getAsInt());
        assertEquals(boardFacil,board1);

        //PARO EL HILO PARA QUE CAMBIE LA FECHA
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        Date fecha1 = new Date();
        //CREAR NUEVA PARTIDA MEDIO
        Shape.Tetrominoe[] boardMedio = new Shape.Tetrominoe[405];
        for (int i=0;i<boardMedio.length;i++){
            boardMedio[i] = Shape.Tetrominoe.NoShape;
        }
        Partida partidaMedio1 = new Partida(2,"Medio", usu1.getCodUsuario(),boardMedio ,0);
        //Guardas la partida
        Central.getInstance().guardarPartida(partidaMedio1.codigoPartida,partidaMedio1.nivel, partidaMedio1.codigoUsuario, partidaMedio1.getCasillasOcupadas(), partidaMedio1.puntos, fecha1.toString());
        //Cargas la partida
        JsonArray jsonArray2 = Central.getInstance().cargarPartida(usu1.getCodUsuario(),fecha1.toString());
        String[] ladrillos2 = Central.getInstance().obtLadrillos(jsonArray2.get(0).getAsInt(),jsonArray2.get(1).getAsInt());
        com.zetcode.Shape.Tetrominoe[] board2 = new com.zetcode.Shape.Tetrominoe[ladrillos2.length];
        for (int i = 0; i < ladrillos2.length; i++) {
            board2[i] = Shape.Tetrominoe.valueOf(ladrillos2[i]);
        }
        //Compruebas que los valores:codUsuario,nivel,puntos y su tablero son iguales
        assertEquals(partidaMedio1.codigoUsuario,jsonArray2.get(0).getAsInt());
        String s1 = jsonArray2.get(2).toString();
        s1 = s1.replaceAll("\"", "");
        assertEquals(partidaMedio1.nivel,s1);
        assertEquals(partidaMedio1.puntos,jsonArray2.get(3).getAsInt());
        assertEquals(boardMedio,board2);

        //PARO EL HILO PARA QUE CAMBIE LA FECHA
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        Date fecha2 = new Date();
        //CREAR NUEVA PARTIDA DIFÍCIL
        Shape.Tetrominoe[] boardDificil = new Shape.Tetrominoe[640];
        for (int i=0;i<boardDificil.length;i++){
            boardDificil[i] = Shape.Tetrominoe.NoShape;
        }
        Partida partidaDificil1 = new Partida(3,"Dificil", usu1.getCodUsuario(),boardDificil ,0);
        //Guardas la partida
        Central.getInstance().guardarPartida(partidaDificil1.codigoPartida, partidaDificil1.nivel, partidaDificil1.codigoUsuario,partidaDificil1.getCasillasOcupadas(), partidaDificil1.puntos, fecha2.toString());
        //Cargas la partida
        JsonArray jsonArray3 = Central.getInstance().cargarPartida(usu1.getCodUsuario(),fecha2.toString());
        String[] ladrillos3 = Central.getInstance().obtLadrillos(jsonArray3.get(0).getAsInt(),jsonArray3.get(1).getAsInt());
        com.zetcode.Shape.Tetrominoe[] board3 = new com.zetcode.Shape.Tetrominoe[ladrillos3.length];
        for (int i = 0; i < ladrillos3.length; i++) {
            board3[i] = Shape.Tetrominoe.valueOf(ladrillos3[i]);
        }
        //Compruebas que los valores:codUsuario,nivel,puntos y su tablero son iguales
        assertEquals(partidaDificil1.codigoUsuario,jsonArray3.get(0).getAsInt());
        String s2 = jsonArray3.get(2).toString();
        s2 = s2.replaceAll("\"", "");
        assertEquals(partidaDificil1.nivel,s2);
        assertEquals(partidaDificil1.puntos,jsonArray3.get(3).getAsInt());
        assertEquals(boardDificil,board3);

        //CAJA BLANCA

        //El usuario no está
        Central.getInstance().cargarPartida(-1,Date.from(Instant.now()).toString());
        //Printea un error porque no existe el usuario

        //La fecha hora es null
        Central.getInstance().cargarPartida(1,null);
        //Printea un error porque la fecha es null


        //PARO EL HILO PARA QUE CAMBIE LA FECHA
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        //La partida no existe
        Central.getInstance().cargarPartida(1,Date.from(Instant.now()).toString());
        //Printea un error porque la partida no existe
    }


    @Test
    public void obtLadrillos() {
        //Este método se usa tanto en guardarPartida como cargarPartida

        //Primero creamos tres partidas con sus distintos niveles
        Usuario usu1=new Usuario(1,"Adrián","1",personalizacion1);
        Central.getInstance().crearUsuario(usu1.getCodUsuario(), usu1.getNombre(),"1");

        //Guardar tres partidas

        //FÁCIL
        Date fecha = new Date();
        Shape.Tetrominoe[] boardFacil = new Shape.Tetrominoe[220];
        for (int i=0;i<boardFacil.length;i++){
            boardFacil[i] = Shape.Tetrominoe.NoShape;
        }
        Partida partidaFacil1 = new Partida(1,"Facil", usu1.getCodUsuario(),boardFacil ,0);
        //Guardas la partida
        Central.getInstance().guardarPartida(partidaFacil1.codigoPartida, partidaFacil1.nivel, partidaFacil1.codigoUsuario, partidaFacil1.getCasillasOcupadas(), partidaFacil1.puntos, fecha.toString());
        //Compruebas que los ladrillos son todos NoShape como hemos definido antes
        String[] board = Central.getInstance().obtLadrillos(partidaFacil1.codigoUsuario, partidaFacil1.codigoPartida);
        com.zetcode.Shape.Tetrominoe[] boardFacilNuevo = new com.zetcode.Shape.Tetrominoe[board.length];
        for (int i = 0; i < board.length; i++) {
            boardFacilNuevo[i] = Shape.Tetrominoe.valueOf(board[i]);
        }
        assertEquals(boardFacil,boardFacilNuevo);

        //MEDIO
        Date fecha1 = new Date();
        Shape.Tetrominoe[] boardMedio = new Shape.Tetrominoe[405];
        for (int i=0;i<boardMedio.length;i++){
            boardMedio[i] = Shape.Tetrominoe.LineShape;
        }
        Partida partidaMedio1 = new Partida(2,"Medio", usu1.getCodUsuario(),boardMedio ,0);
        //Guardas la partida
        Central.getInstance().guardarPartida(partidaMedio1.codigoPartida,partidaMedio1.nivel, partidaMedio1.codigoUsuario, partidaMedio1.getCasillasOcupadas(), partidaMedio1.puntos, fecha1.toString());
        //Compruebas que los ladrillos son todos NoShape como hemos definido antes
        String[] board1 = Central.getInstance().obtLadrillos(partidaMedio1.codigoUsuario, partidaMedio1.codigoPartida);
        com.zetcode.Shape.Tetrominoe[] boardMedioNuevo = new com.zetcode.Shape.Tetrominoe[board1.length];
        for (int i = 0; i < board1.length; i++) {
            boardMedioNuevo[i] = Shape.Tetrominoe.valueOf(board1[i]);
        }
        assertEquals(boardMedio,boardMedioNuevo);

        //DIFÍCIL
        Date fecha2 = new Date();
        Shape.Tetrominoe[] boardDificil = new Shape.Tetrominoe[640];
        for (int i=0;i<boardDificil.length;i++){
            boardDificil[i] = Shape.Tetrominoe.SShape;
        }
        Partida partidaDificil1 = new Partida(3,"Dificil", usu1.getCodUsuario(),boardDificil ,0);
        //Guardas la partida
        Central.getInstance().guardarPartida(partidaDificil1.codigoPartida, partidaDificil1.nivel, partidaDificil1.codigoUsuario,partidaDificil1.getCasillasOcupadas(), partidaDificil1.puntos, fecha2.toString());
        //Compruebas que los ladrillos son todos NoShape como hemos definido antes
        String[] board3 = Central.getInstance().obtLadrillos(partidaDificil1.codigoUsuario, partidaDificil1.codigoPartida);
        com.zetcode.Shape.Tetrominoe[] boardDificilNuevo = new com.zetcode.Shape.Tetrominoe[board3.length];
        for (int i = 0; i < board3.length; i++) {
            boardDificilNuevo[i] = Shape.Tetrominoe.valueOf(board3[i]);
        }
        assertEquals(boardDificil,boardDificilNuevo);

        //CAJA BLANCA

        //El usuario no existe
        Central.getInstance().obtLadrillos(-1,1);
        //Printea un error porque no existe el usuario

        //La partida no existe
        Central.getInstance().obtLadrillos(1,4);
        //Printea un error porque la partida no existe

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
