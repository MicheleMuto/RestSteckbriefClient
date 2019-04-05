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

/**
 * Von der Series-Entity des Servers abgeleitete POJO-Klasse, die alle Daten zu
 * einer Serie beinhaltet. Sie muss hier manuell angelegt (oder aus dem Server-
 * Projekt übernommen werden), da es keine Möglichkeit gibt, die benötigten
 * Klassen aus der WADL anzulegen bzw. die WADL hierfür nicht genügend
 * Informationen beinhaltet.
 */
public class Steckbrief {
    public Long id = 0L;
    public String name="";
    public int kopfgeld = 0;
    public String personenBeschreibung="";
    
}
