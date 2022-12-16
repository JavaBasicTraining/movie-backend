package com.example.movie_backend.exception;

import com.example.boot.exception.GlobalExceptionHandler;
import com.example.boot.model.response.IResponseFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;

@Slf4j
@ControllerAdvice
public class MovieExceptionHandler extends GlobalExceptionHandler {

    protected MovieExceptionHandler(IResponseFactory iResponseFactory) {
        super(iResponseFactory);
    }
}
