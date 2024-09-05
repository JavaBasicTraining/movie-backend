package com.example.movie_backend.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("api/authenticate")
@RestController
@RequiredArgsConstructor
@Slf4j
public class AuthenticationController {

    @GetMapping
    private void authenticate() {
        log.info("This func call to check if token is available or not");
    }
}
