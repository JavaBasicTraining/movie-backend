package com.example.movie_backend.controller.impl;

import com.example.movie_backend.controller.dto.request.QueryMovieRequest;
import com.example.movie_backend.dto.movie.MovieDTO;
import com.example.movie_backend.dto.movie.MovieDTOWithoutJoin;
import com.example.movie_backend.dto.movie.MovieEpisodeRequest;
import com.example.movie_backend.service.IMovieService;
import lombok.RequiredArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@PreAuthorize("hasAuthority('admin')")
@RequestMapping("api/v1/admin/movies")
@RequiredArgsConstructor
@RestController
public class MovieManageController {

    private final IMovieService movieService;

    @GetMapping
    public ResponseEntity<List<MovieDTOWithoutJoin>> query(@ParameterObject Pageable pageable,
                                                           @ParameterObject QueryMovieRequest request) {
        Page<MovieDTOWithoutJoin> movieDTOPage = movieService.query(request, pageable);
        return ResponseEntity.ok(movieDTOPage.getContent());
    }

    @GetMapping("{id}")
    public ResponseEntity<MovieDTO> getById(@PathVariable("id") Long id) {

        return ResponseEntity.ok(movieService.getById(id));
    }

    @PostMapping()
    public ResponseEntity<MovieDTO> createWithEpisode(@RequestBody MovieDTO movieEpisodeRequest) {
        return ResponseEntity.ok(
                movieService.createWithEpisode(movieEpisodeRequest)
        );
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<MovieDTO> updateWithEpisode(@PathVariable Long id, @RequestBody MovieDTO request) {
        return ResponseEntity.ok(
                movieService.updateWithEpisode(id, request)
        );
    }

    @PatchMapping(value = "{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> uploadFile(@PathVariable("id") Long id,
                                           @RequestParam("type") String type,
                                           @RequestPart("file")
                                           MultipartFile file) {
        movieService.uploadMovieFile(id, file, type);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping(value = "{id}/episodes/{episodeId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> uploadEpisodeFile(@PathVariable("id") Long id,
                                                  @PathVariable("episodeId") Long episodeId,
                                                  @RequestPart("file") MultipartFile file,
                                                  @RequestParam("type") String type
                                                  ) {
        movieService.uploadEpisodeFile(id, episodeId, file, type);
        return ResponseEntity.noContent().build();
    }


    @DeleteMapping("{id}")
    public boolean delete(@PathVariable Long id) {
        ResponseEntity.ok(movieService.delete(id));
        return true;
    }
}
  
