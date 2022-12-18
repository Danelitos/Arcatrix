package eus.ehu.lsi.adsi;

import static org.junit.Assert.*;
import com.zetcode.DatosPersonalizacion;
import com.zetcode.ListaRankings;
import com.zetcode.Ranking;
import com.zetcode.Usuario;
import org.junit.Test;

import java.sql.SQLException;

public class ListaRankingsTest {

    @Test
    public void probarOrdenar(){
        ListaRankings listaRankings = ListaRankings.getInstance();
        try {
            listaRankings.anadirRanking(1,"o",10,"facil");
            listaRankings.anadirRanking(1,"o",11,"facil");
            listaRankings.anadirRanking(1,"o",12,"facil");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        listaRankings.ordenar();

        //resultado esperado
        int resultado=12;

        Ranking primero =listaRankings.listaRankings.get(0);
        Ranking segundo = listaRankings.listaRankings.get(1);
        assertEquals(12,primero.getPuntuacion());
        assertEquals(11,segundo.getPuntuacion());

    }
}
