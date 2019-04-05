/*
 * Copyright © 2019 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package dhbwka.wwi.vertsys.ws.rest_serien_client;

import java.util.List;

/**
 * Antwortobjekt, das bei Auftreten einer Exception an den Client gesendet
 * wird.
 */
public class ExceptionResponse {
    public String exception;
    public String message;
    public List<Violation> violations;
    
    public static class Violation {
        public String path = "";
        public String message = "";
    }
}
