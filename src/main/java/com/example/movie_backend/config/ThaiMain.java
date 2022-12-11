package com.example.movie_backend.config;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
@Tag(name = "tao ne")
public class ThaiMain {
    private final ThaiConfig thaiConfig;

    public ThaiMain(ThaiConfig thaiConfig) {
        this.thaiConfig = thaiConfig;
    }

    @GetMapping("/")
    public ThaiConfig get() {
        return thaiConfig;
    }

}
