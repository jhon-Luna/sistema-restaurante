package com.sazonfuego.sistema.exception;

import com.sazonfuego.sistema.exepcion.RecursoNoEncontradoException;
import com.sazonfuego.sistema.exepcion.SolicitudIncorrectaException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(RecursoNoEncontradoException.class)
    public ResponseEntity<Map<String, Object>> manejarNoEncontrado(RecursoNoEncontradoException ex) {
        return construirRespuesta(HttpStatus.NOT_FOUND, ex.getMessage());
    }


    @ExceptionHandler(SolicitudIncorrectaException.class)
    public ResponseEntity<Map<String, Object>> manejarSolicitudIncorrecta(SolicitudIncorrectaException ex) {
        return construirRespuesta(HttpStatus.BAD_REQUEST, ex.getMessage());
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> manejarErrorFatal(Exception ex) {

        ex.printStackTrace();
        return construirRespuesta(HttpStatus.INTERNAL_SERVER_ERROR, "Error interno del servidor. Contacte a soporte.");
    }


    private ResponseEntity<Map<String, Object>> construirRespuesta(HttpStatus estado, String mensaje) {
        Map<String, Object> body = new HashMap<>();
        body.put("codigo", estado.value());
        body.put("estado", estado.getReasonPhrase());
        body.put("mensaje", mensaje);
        body.put("timestamp", LocalDateTime.now());
        return ResponseEntity.status(estado).body(body);
    }
}