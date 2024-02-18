package com.example.movie_backend.controller;

import com.example.movie_backend.controller.interfaces.IEpisodeController;
import com.example.movie_backend.dto.episode.EpisodeDTO;
import com.example.movie_backend.services.EpisodeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class EpisodeController implements IEpisodeController {

    public final EpisodeService service;

    public EpisodeController(EpisodeService service) {
        this.service = service;
    }

    @Override
    public ResponseEntity<EpisodeDTO> create(EpisodeDTO episode) {
        return ResponseEntity.ok(service.create(episode));

    }

    @Override
    public ResponseEntity<EpisodeDTO> update(EpisodeDTO episode, Long id) {
        return ResponseEntity.ok(service.update(episode,id));

    }

    @Override
    public ResponseEntity<EpisodeDTO> getById(Long id) {
        return ResponseEntity.ok(service.getById(id));

    }

    @Override
    public boolean delete(Long id) {
         ResponseEntity.ok(service.delete(id));

        return true;
    }
}
