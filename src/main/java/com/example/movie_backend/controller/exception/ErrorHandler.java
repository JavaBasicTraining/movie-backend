package com.example.movie_backend.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;

@Component
@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(ConflictDataException.class)
    public ResponseEntity<Object> handleConflictDataException(ConflictDataException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getMessage());
    }

    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<Object> handleBadRequest(HttpClientErrorException exception) {
        return ResponseEntity.status(HttpStatus.valueOf(400)).body("Bad Request: " + exception.getMessage());
    }
}
