package com.example.movie_backend.controller;

import com.example.movie_backend.controller.interfaces.IEpisodeController;
import com.example.movie_backend.entity.Episode;
import com.example.movie_backend.services.interfaces.IBaseService;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class EpisodeController extends BaseController<Episode, UUID> implements IEpisodeController {
    public EpisodeController(IBaseService<Episode, UUID> service) {
        super(service);
    }

}
