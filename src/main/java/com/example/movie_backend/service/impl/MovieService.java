package com.example.movie_backend.service.impl;

import com.example.movie_backend.controller.exception.BadRequestException;
import com.example.movie_backend.controller.exception.EntityNotFoundException;
import com.example.movie_backend.controller.exception.ServerErrorException;
import com.example.movie_backend.controller.dto.request.QueryMovieRequest;
import com.example.movie_backend.dto.episode.EpisodeDTO;
import com.example.movie_backend.dto.movie.MovieDTO;
import com.example.movie_backend.dto.movie.MovieDTOWithoutJoin;
import com.example.movie_backend.dto.movie.MovieEpisodeRequest;
import com.example.movie_backend.mapper.MovieMapper;
import com.example.movie_backend.entity.Episode;
import com.example.movie_backend.entity.Movie;
import com.example.movie_backend.repository.EpisodeRepository;
import com.example.movie_backend.repository.MovieRepository;
import com.example.movie_backend.service.IMovieService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import static com.example.movie_backend.constant.SpecialCharacter.SLASH;

@Slf4j
@Service
@RequiredArgsConstructor
public class MovieService implements IMovieService {
    private static final String POSTER = "poster";

    private final MinioService minioService;
    private final MovieMapper mapper;
    private final MovieRepository movieRepository;
    private final EpisodeRepository episodeRepository;

    public void uploadMovieFile(Movie movie, MultipartFile file, String type) {
        try {
            String objectName = "movies/" + movie.getId() + SLASH + type + SLASH + file.getOriginalFilename();
            if (POSTER.equals(type)) {
                movie.setPosterUrl(objectName);
            } else {
                movie.setVideoUrl(objectName);
            }
            movieRepository.save(movie);
            minioService.uploadByFile(file, objectName);
        } catch (Exception exception) {
            log.error("Upload movie file failed {}", exception.getMessage());
            throw new ServerErrorException("Upload movie file failed by id: " + movie.getId());
        }
    }

    @Override
    public void uploadMovieFile(Long id, MultipartFile file, String type) {
        Movie movie = movieRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        uploadMovieFile(movie, file, type);
    }


    @Override
    public void uploadEpisodeFile(Long movieId, Long episodeId, MultipartFile poster, MultipartFile video) {
        if (!movieRepository.existsById(movieId) && poster == null && video == null) {
            throw new EntityNotFoundException();
        }
        Episode episode = episodeRepository.findById(episodeId).orElseThrow(EntityNotFoundException::new);
        uploadEpisodeFile(episode, movieId, poster, POSTER);
        uploadEpisodeFile(episode, movieId, video, "video");
    }

    public void uploadEpisodeFile(Episode episode, Long movieId, MultipartFile file, String type) {
        try {
            String object = String.format("movies/%s/episodes/%s/%s/%s", movieId, episode.getId(), type, file.getOriginalFilename());
            if (POSTER.equals(type)) {
                episode.setPosterUrl(object);
            } else {
                episode.setVideoUrl(object);
            }
            episodeRepository.save(episode);
            minioService.uploadByFile(file, object);
        } catch (Exception ex) {
            log.error("Upload episode file failed {}", ex.getMessage());
            throw new ServerErrorException("Upload episode file failed by id: " + episode.getId());
        }
    }

    @Override
    public MovieDTO getById(Long id) {
        return movieRepository.findById(id).map(item -> {
            MovieDTO movieDTO = mapper.toDTO(item);
            movieDTO.setPosterUrl(movieDTO.getPosterUrl() == null ? null : this.minioService.getPreSignedLink(movieDTO.getPosterUrl()));
            movieDTO.setVideoUrl(movieDTO.getVideoUrl() == null ? null : this.minioService.getPreSignedLink(movieDTO.getVideoUrl()));
            List<EpisodeDTO> episodeDTOs = item.getEpisodes().stream().map(episode -> {
                String linkPoster = episode.getPosterUrl() == null ? null : this.minioService.getPreSignedLink(episode.getPosterUrl());
                String linkVideo = episode.getVideoUrl() == null ? null : this.minioService.getPreSignedLink(episode.getVideoUrl());
                EpisodeDTO episodeDTO = new EpisodeDTO();
                episodeDTO.setId(episode.getId());
                episodeDTO.setEpisodeCount(episode.getEpisodeCount());
                episodeDTO.setDescriptions(episode.getDescriptions());
                episodeDTO.setMovieId(episode.getMovie().getId());
                episodeDTO.setPosterUrl(linkPoster);
                episodeDTO.setVideoUrl(linkVideo);
                return episodeDTO;
            }).sorted(Comparator.comparingLong(EpisodeDTO::getEpisodeCount)).toList();
            movieDTO.setEpisodes(episodeDTOs);

            return movieDTO;
        }).orElse(null);
    }

    @Override
    public Boolean delete(Long id) {
        movieRepository.deleteById(id);
        return true;
    }

    @Override
    public List<MovieDTOWithoutJoin> query(String name, String genre, String country) {
        return movieRepository.query(name, genre, country, Pageable.ofSize(20)).stream().map(mapper::toDTOWithoutJoin).toList();
    }

    @Override
    public Page<MovieDTOWithoutJoin> query(QueryMovieRequest request, Pageable pageable) {
        return movieRepository.query(request.getKeyword(), request.getGenre(), request.getCountry(), pageable).map(item -> {
            MovieDTOWithoutJoin movieDTO = mapper.toDTOWithoutJoin(item);

            if (Objects.nonNull(item.getPosterUrl())) {
                String linkPoster = this.minioService.getPreSignedLink(item.getPosterUrl());
                movieDTO.setPosterUrl(linkPoster);
            }

            if (Objects.nonNull(item.getVideoUrl())) {
                String linkVideo = this.minioService.getPreSignedLink(item.getVideoUrl());
                movieDTO.setVideoUrl(linkVideo);
            }

            return movieDTO;
        });
    }

    public MovieDTO filterMovie(Long id) {
        return movieRepository.filterMovie(id)
                .map(item -> {
                    MovieDTO movieDTO = mapper.toDTO(item);
                    if (item.getPosterUrl() != null) {
                        String linkPoster = this.minioService.getPreSignedLink(item.getPosterUrl());
                        movieDTO.setPosterUrl(linkPoster);
                    }

                    if (item.getVideoUrl() != null) {
                        String linkVideo = this.minioService.getPreSignedLink(item.getVideoUrl());
                        movieDTO.setVideoPresignedUrl(linkVideo);
                    }
                    return movieDTO;
                }).
                orElseThrow(() -> new BadRequestException("Movie not found"));


    }

    public MovieDTO createWithEpisode(MovieEpisodeRequest dto) {
        Movie movie = mapper.toUpdateMovieWithEpisodes(dto);
        return mapper.toDTO(movieRepository.save(movie));
    }

    public MovieDTO updateWithEpisode(Long movieId, MovieEpisodeRequest request) {
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(EntityNotFoundException::new);
        movie = mapper.toUpdateMovieWithEpisodes(request, movie);
        MovieDTO movieDTO = mapper.toDTO(movie);
        movie = movieRepository.save(movie);

        if (Objects.nonNull(movieDTO.getEpisodes()) && !movieDTO.getEpisodes().isEmpty() && Objects.nonNull(movie.getEpisodes()) && !movie.getEpisodes().isEmpty()) {
            for (int i = 0; i < movie.getEpisodes().size(); i++) {
                EpisodeDTO episodeDTO = movieDTO.getEpisodes().stream().sorted(Comparator.comparingLong(EpisodeDTO::getEpisodeCount)).toList().get(i);
                Episode episode = movie.getEpisodes().stream().sorted(Comparator.comparingLong(Episode::getEpisodeCount)).toList().get(i);
                episodeDTO.setId(episode.getId());
            }
        }
        return movieDTO.toBuilder().episodes(movieDTO.getEpisodes().stream().sorted(Comparator.comparingLong(EpisodeDTO::getEpisodeCount)).toList()).build();
    }
}
