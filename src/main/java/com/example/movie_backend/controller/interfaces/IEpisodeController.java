package com.example.movie_backend.controller.interfaces;

import com.example.movie_backend.dto.episode.EpisodeDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/episode/")
public interface IEpisodeController {
    @PostMapping("create")
    ResponseEntity<EpisodeDTO> create(@RequestBody EpisodeDTO episode);

    @PutMapping("update")
    ResponseEntity<EpisodeDTO> update(@RequestBody EpisodeDTO episode, @RequestParam Long id);

    @GetMapping("getById/{id}")
    ResponseEntity<EpisodeDTO> getById(@RequestParam Long id);
    @DeleteMapping("delete{id}")
    boolean delete(@RequestParam Long id);}
