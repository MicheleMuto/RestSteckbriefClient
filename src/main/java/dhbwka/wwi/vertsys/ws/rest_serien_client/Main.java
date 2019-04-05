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

import com.mashape.unirest.http.exceptions.UnirestException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Hauptklasse unseres kleinen Beispielprogramms. Sie implementiert ein kleines
 * Menüsystem zur Anzeige der vorhandenen Serien und zur Anlage einer neuen
 * Serie. Dabei greift sie auf die Klassen SeriesCollection und Series zurück,
 * die passend zum Webservice angelegt wurden.
 */
public class Main {

    static BufferedReader fromKeyboard = new BufferedReader(new InputStreamReader(System.in));
    static SteckbriefResource steckbriefResource = new SteckbriefResource();

    /**
     * Hauptmenü des Programms. Zeigt alle vorhandenen Songs und fragt den
     * Anwender, ob er einen neuen Song anlegen oder das Programm beenden will.
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        boolean quit = false;

        while (!quit) {
            // Alle Serien vom Server abrufen und anzeigen
            System.out.println("=================");
            System.out.println("Alle meine Steckbriefe");
            System.out.println("=================");
            System.out.println();

            Steckbrief[] steckbriefArray = steckbriefResource.findSteckbriefe("");

            if (steckbriefArray != null) {
                for (Steckbrief steckbrief : steckbriefArray) {
                    System.out.println("Id:       " + steckbrief.id);
                    System.out.println("Name:    " + steckbrief.name);
                    System.out.println("Kopfgeld:    " + steckbrief.kopfgeld);
                    System.out.println("Personenbeschreibung:     " + steckbrief.personenBeschreibung);
                    System.out.println();
                }
            }

            // Benutzer fragen, ob er einen neue Serie anlegen will
            System.out.println("[A] Neuer Steckbrief anlegen");
            System.out.println("[E] Ende");
            System.out.println();

            System.out.print("Deine Auswahl: ");
            String command = fromKeyboard.readLine();

            System.out.println();

            switch (command.toUpperCase()) {
                case "A":
                    createNewSteckbrief();
                    break;
                case "E":
                    System.out.println("Bye, bye!");
                    quit = true;
                    break;
            }
        }
    }

    /**
     * Menü zum Anlegen einer neuen Serie.
     *
     * @throws IOException
     * @throws UnirestException
     * @throws WebAppException
     */
    public static void createNewSteckbrief() throws IOException, UnirestException, WebAppException {
        System.out.println("==================");
        System.out.println("Neuer Steckbrief anlegen");
        System.out.println("==================");
        System.out.println();

        Steckbrief steckbrief = new Steckbrief();

        System.out.print("Id: ");
        String i = fromKeyboard.readLine();
        steckbrief.id = Long.parseLong(i);

        System.out.print("Name: ");
        steckbrief.name = fromKeyboard.readLine();

        System.out.print("Kopfgeld: ");
        String k = fromKeyboard.readLine();
        steckbrief.kopfgeld = Integer.parseInt(k);

        System.out.print("Personenbeschreibung: ");
        steckbrief.personenBeschreibung = fromKeyboard.readLine();

        System.out.println();

        steckbriefResource.saveNewSteckbrief(steckbrief);
    }
}
