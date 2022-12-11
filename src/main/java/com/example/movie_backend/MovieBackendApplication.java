package com.example.movie_backend;

import com.example.boot.configuration.annotation.EnableAutoConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfig
public class MovieBackendApplication {
    public static void main(String[] args) {
        SpringApplication.run(MovieBackendApplication.class, args);
    }
}
