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

/**
 * Exception für vom Server zurückgelieferte Fehler.
 */
public class WebAppException extends Exception {

    private final ExceptionResponse exceptionResponse;

    public WebAppException(ExceptionResponse exceptionResponse) {
        super(exceptionResponse.message);
        this.exceptionResponse = exceptionResponse;
    }

    public ExceptionResponse getExceptionResponse() {
        return this.exceptionResponse;
    }
}
