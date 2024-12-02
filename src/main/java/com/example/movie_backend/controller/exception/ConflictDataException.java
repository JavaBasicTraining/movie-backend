package com.example.movie_backend.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ConflictDataException extends RuntimeException {
    public ConflictDataException(String message) {
        super(message);
    }
}
