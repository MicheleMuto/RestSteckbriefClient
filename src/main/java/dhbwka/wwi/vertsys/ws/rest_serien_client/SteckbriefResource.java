/*
 * Copyright © 2018 Dennis Schulmeister-Zimolong
 *
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 *
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package dhbwka.wwi.vertsys.ws.rest_serien_client;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

/**
 * Von der Klasse SeriesResource des Servers abgeleitete Klasse, die im Prinzip
 * dieselben Methoden besitzt. Hier rufen wir jedoch den REST-Webservice des
 * Servers auf, anstelle direkt auf eine Datenbank zuzugreifen.
 */
public class SteckbriefResource {

    public static final String URL = "http://localhost:8080/DeadOrAlive/api/";

    public String url = URL;
    public String username = "";
    public String password = "";

    Gson gson = new GsonBuilder().create();

    //<editor-fold defaultstate="collapsed" desc="Konstruktoren">
    public SteckbriefResource() {
    }

    public SteckbriefResource(String url) {
        this.url = url;
    }
    //</editor-fold>

    /**
     * Benutzername und Passwort für die Authentifizierung merken.
     *
     * @param username Benutzername
     * @param password Passwort
     */
    public void setAuthData(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * Serien suchen.
     *
     * @param query Suchbegriff
     * @return Gefundene Serien
     * @throws UnirestException HTTP-Fehler
     * @throws WebAppException Server-Fehler
     */
    public Steckbrief[] findSteckbriefe(String query) throws UnirestException, WebAppException {
        // Anfrage an den Server senden
        HttpResponse<String> httpResponse = Unirest.get(this.url)
                .queryString("query", query)
                .header("accept", "application/json")
                .asString();

        try {
            ExceptionResponse er = this.gson.fromJson(httpResponse.getBody(), ExceptionResponse.class);

            if (er.exception != null) {
                throw new WebAppException(er);
            }
        } catch (JsonSyntaxException ex) {
            // Okay, keine Exception empfangen
        }
        String json = httpResponse.getBody();
        return this.gson.fromJson(httpResponse.getBody(), Steckbrief[].class);
    }

    /**
     * Neuen Song speichern.
     *
     * @param series Zu speichernde Serie
     * @return Gespeicherte Serie
     * @throws UnirestException HTTP-Fehler
     * @throws WebAppException Server-Fehler
     */
    public Steckbrief saveNewSteckbrief(Steckbrief steckbrief) throws UnirestException, WebAppException {
        HttpResponse<String> httpResponse = Unirest.post(this.url)
                .header("accept", "application/json")
                .header("content-type", "application/json")
                .basicAuth(this.username, this.password)
                .body(this.gson.toJson(steckbrief))
                .asString();
        String res = httpResponse.getBody();
        try {
            ExceptionResponse er = this.gson.fromJson(httpResponse.getBody(), ExceptionResponse.class);

            if (er.exception != null) {
                throw new WebAppException(er);
            }
        } catch (JsonSyntaxException ex) {
            // Okay, keine Exception empfangen
        }

        return this.gson.fromJson(httpResponse.getBody(), Steckbrief.class);
    }

    /**
     * Einzelne Serie auslesen.
     *
     * @param id Serien-ID
     * @return Gefundener Song
     * @throws UnirestException HTTP-Fehler
     * @throws WebAppException Server-Fehler
     */
    public Steckbrief getSeries(String id) throws UnirestException, WebAppException {
        HttpResponse<String> httpResponse = Unirest.get(this.url + id + "/")
                .header("accept", "application/json")
                .asString();

        try {
            ExceptionResponse er = this.gson.fromJson(httpResponse.getBody(), ExceptionResponse.class);

            if (er.exception != null) {
                throw new WebAppException(er);
            }
        } catch (JsonSyntaxException ex) {
            // Okay, keine Exception empfangen
        }

        return this.gson.fromJson(httpResponse.getBody(), Steckbrief.class);
    }

    /**
     * Anlegen oder Aktualisieren einer Serie.
     *
     * @param series Zu speichernde Serie
     * @return Gespeicherte Serie
     * @throws UnirestException HTTP-Fehler
     * @throws WebAppException Server-Fehler
     */
    public Steckbrief saveSteckbrief(Steckbrief steckbrief) throws UnirestException, WebAppException {
        HttpResponse<String> httpResponse = Unirest.put(this.url + steckbrief.id + "/")
                .header("accept", "application/json")
                .header("content-type", "application/json")
                .basicAuth(this.username, this.password)
                .body(this.gson.toJson(steckbrief))
                .asString();

        try {
            ExceptionResponse er = this.gson.fromJson(httpResponse.getBody(), ExceptionResponse.class);

            if (er.exception != null) {
                throw new WebAppException(er);
            }
        } catch (JsonSyntaxException ex) {
            // Okay, keine Exception empfangen
        }

        return this.gson.fromJson(httpResponse.getBody(), Steckbrief.class);
    }

    /**
     * Serie löschen.
     *
     * @param id Serien-ID
     * @return Gelöschte Serie
     * @throws UnirestException HTTP-Fehler
     * @throws WebAppException Server-Fehler
     */
    public Steckbrief deleteSteckbrief(String id) throws UnirestException, WebAppException {
        HttpResponse<String> httpResponse = Unirest.delete(this.url + id + "/")
                .header("accept", "application/json")
                .basicAuth(this.username, this.password)
                .asString();

        try {
            ExceptionResponse er = this.gson.fromJson(httpResponse.getBody(), ExceptionResponse.class);
            
            if (er.exception != null) {
                throw new WebAppException(er);
            }
        } catch (JsonSyntaxException ex) {
            // Okay, keine Exception empfangen
        }

        return this.gson.fromJson(httpResponse.getBody(), Steckbrief.class);
    }

}
