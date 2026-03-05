package com.sazonfuego.sistema.exepcion;

public class SolicitudIncorrectaException extends RuntimeException{
    public SolicitudIncorrectaException(String mensaje){
        super(mensaje);
    }
}
