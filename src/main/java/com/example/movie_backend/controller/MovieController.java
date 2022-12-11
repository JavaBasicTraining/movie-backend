package com.example.movie_backend.controller;

import com.example.movie_backend.controller.interfaces.IMovieController;
import com.example.movie_backend.entity.Movie;
import com.example.movie_backend.services.interfaces.IBaseService;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class MovieController extends BaseController<Movie, UUID> implements IMovieController {
    public MovieController(IBaseService<Movie, UUID> service) {
        super(service);
    }
}
