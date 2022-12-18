package com.zetcode;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import vista.VentanaElegirRanking;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class ListaRankings {

    public static ListaRankings miListaRankings;

    public ArrayList<Ranking> listaRankings;

    private JsonArray lista;


    private ListaRankings() {
        listaRankings = this.cargarRankings();
        lista = new JsonArray();


        //Ordenar y Cargar JsonArray
        this.ordenar();

    }

    public static ListaRankings getInstance() {
        if (ListaRankings.miListaRankings == null) {
            ListaRankings.miListaRankings = new ListaRankings();
        }
        return ListaRankings.miListaRankings;
    }

    public JsonArray getListaGeneral() {

        return this.lista;
    }

    public JsonArray getListaPersonal(int codUsr) {

        JsonArray resultado = new JsonArray();
        JsonObject json = new JsonObject();
        for (int i = 0; i < lista.size(); i++) {
            json = lista.get(i).getAsJsonObject();
            if (json.get("IdUsr").getAsInt() == codUsr) {
                resultado.add(json);
            }
        }
        return resultado;
    }

    public JsonArray getListaNivel(String dificultad) {
        JsonArray resultado = new JsonArray();
        JsonObject json = new JsonObject();
        for (int i = 0; i < lista.size(); i++) {
            json = lista.get(i).getAsJsonObject();
            if (json.get("Nivel").getAsString().equals(dificultad)) {
                resultado.add(json);
            }
        }
        return resultado;
    }

    private void ordenar() {

        //Ordenar Arraylist de objetos
        Collections.sort(listaRankings);
        //Resetara JsonArray
        lista = null;
        lista = new JsonArray();
        //Volver a rellanar JsonArray con la lista ordenalda
        Gson gson = new Gson();
        for (Ranking r : listaRankings) {
            JsonObject json = new JsonObject();
            json.addProperty("IdUsr", r.getIdUsr());
            json.addProperty("Nombre Usuario", r.getNombreUsr());
            json.addProperty("Puntuacion", r.getPuntuacion());
            json.addProperty("Nivel", r.getNivel());
            lista.add(json);
        }
    }

    public void anadirRanking(int IdUsr, String Nombre, int Puntuacion, String Nivel) throws SQLException {

        Ranking r = new Ranking(IdUsr, Nombre, Puntuacion, Nivel);

        //Añadir a la base de datos
        GestorBD.getInstance().anadirRanking(r);

        //Se añade ranking a la lista
        listaRankings.add(r);

        //Se reordenan las listas
        this.ordenar();
    }

    private ArrayList<Ranking> cargarRankings() {

        ArrayList<Ranking> resultado = new ArrayList<Ranking>();
        try {
            resultado = GestorBD.getInstance().cargarRankings();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return resultado;
    }

}
