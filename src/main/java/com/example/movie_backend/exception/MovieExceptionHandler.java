package com.example.movie_backend.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;

@Slf4j
@ControllerAdvice
public class MovieExceptionHandler implements IGlobalExceptionHandler {
}
