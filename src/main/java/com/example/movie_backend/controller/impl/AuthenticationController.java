package com.example.movie_backend.controller.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@Slf4j
@RequestMapping("api/authenticate")
@RestController
@RequiredArgsConstructor
public class AuthenticationController {

    @GetMapping
    public void authenticate(@AuthenticationPrincipal Jwt jwt) {
        if (Objects.isNull(jwt)) {
            throw new AuthenticationCredentialsNotFoundException("Invalid token");
        }
        log.info("This func call to check if token is available or not: {}", jwt.getSubject());
    }
}
