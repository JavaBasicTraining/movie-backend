package com.example.movie_backend.controller.exception;

public class ConflictDataException extends RuntimeException {
    public ConflictDataException(String message) {
        super(message);
    }
}
