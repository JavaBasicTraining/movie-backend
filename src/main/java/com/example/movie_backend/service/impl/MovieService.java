package com.example.movie_backend.service.impl;

import com.example.movie_backend.controller.dto.request.QueryMovieRequest;
import com.example.movie_backend.controller.exception.BadRequestException;
import com.example.movie_backend.controller.exception.EntityNotFoundException;
import com.example.movie_backend.controller.exception.ServerErrorException;
import com.example.movie_backend.dto.episode.EpisodeDTO;
import com.example.movie_backend.dto.movie.MovieDTO;
import com.example.movie_backend.dto.movie.MovieDTOWithoutJoin;
import com.example.movie_backend.entity.Episode;
import com.example.movie_backend.entity.Movie;
import com.example.movie_backend.mapper.MovieMapper;
import com.example.movie_backend.repository.EpisodeRepository;
import com.example.movie_backend.repository.MovieRepository;
import com.example.movie_backend.service.IMinioService;
import com.example.movie_backend.service.IMovieService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.text.Normalizer;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import static com.example.movie_backend.constant.SpecialCharacter.SLASH;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class MovieService implements IMovieService {
    private static final String POSTER = "poster";
    private static final String VIDEO = "video";

    private final IMinioService minioService;
    private final MovieRepository movieRepository;
    private final EpisodeRepository episodeRepository;
    private final MovieMapper movieMapper;

    public void uploadMovieFile(Movie movie, MultipartFile file, String type) {
        try {
            String objectName = "movies/" + movie.getId() + SLASH + type + SLASH + file.getOriginalFilename();
            switch (type) {
                case POSTER:
                    movie.setPosterUrl(objectName);
                    break;
                case VIDEO:
                    movie.setVideoUrl(objectName);
                    break;
                default:
                    movie.setTrailerUrl(objectName);
                    break;
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
    public void uploadEpisodeFile(Long movieId, Long episodeId, MultipartFile file, String type) {
        if (!movieRepository.existsById(movieId) && type == null) {
            throw new EntityNotFoundException();
        }
        Episode episode = episodeRepository.findById(episodeId).orElseThrow(EntityNotFoundException::new);
        uploadEpisodeFile(episode, movieId, file, type);
    }

    public void uploadEpisodeFile(Episode episode, Long movieId, MultipartFile file, String type) {
        try {
            String object = String.format("movies/%s/episodes/%s/%s/%s", movieId, episode.getId(), type,
                    file.getOriginalFilename());
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
            MovieDTO movieDTO = movieMapper.toDTO(item);
            movieDTO.setPosterPresignedUrl(getPresignedLink(item.getPosterUrl()));
            movieDTO.setVideoPresignedUrl(getPresignedLink(item.getVideoUrl()));
            movieDTO.setTrailerPresignedUrl(getPresignedLink(item.getTrailerUrl()));

            List<EpisodeDTO> episodeDTOS = movieDTO.getEpisodes()
                    .stream()
                    .sorted(Comparator.comparingLong(EpisodeDTO::getEpisodeCount))
                    .toList();

            episodeDTOS.forEach(episodeDTO -> {
                episodeDTO.setPosterPresignedUrl(getPresignedLink(episodeDTO.getPosterUrl()));
                episodeDTO.setVideoPresignedUrl(getPresignedLink(episodeDTO.getVideoUrl()));
            });

            movieDTO.setEpisodes(episodeDTOS);
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
        return movieRepository.query(name, genre, country, Pageable.ofSize(20)).stream()
                .map(movieMapper::toDTOWithoutJoin).toList();
    }

    @Override
    public Page<MovieDTOWithoutJoin> query(QueryMovieRequest request, Pageable pageable) {
        return movieRepository.query(request.getKeyword(), request.getGenre(), request.getCountry(), pageable)
                .map(item -> {
                    MovieDTOWithoutJoin movieDTO = movieMapper.toDTOWithoutJoin(item);
                    movieDTO.setPosterPresignedUrl(getPresignedLink(item.getPosterUrl()));
                    movieDTO.setVideoPresignedUrl(getPresignedLink(item.getVideoUrl()));
                    return movieDTO;
                });
    }

    public MovieDTO getByPath(String path) {
        return movieRepository.filterMovie(path)
                .map(item -> {
                    MovieDTO movieDTO = movieMapper.toDTO(item);
                    movieDTO.setPosterPresignedUrl(getPresignedLink(item.getPosterUrl()));
                    movieDTO.setVideoPresignedUrl(getPresignedLink(item.getVideoUrl()));
                    movieDTO.setTrailerPresignedUrl(getPresignedLink(item.getTrailerUrl()));
                    return movieDTO;
                })
                .orElseThrow(() -> new BadRequestException("Movie not found"));
    }

    @Override
    public Page<MovieDTO> getTrendingMovies(QueryMovieRequest request, Pageable pageable) {
        return null;
    }

    public MovieDTO create(MovieDTO dto) {
        Movie movie = movieMapper.toEntity(dto);
        String pathGenerated = generateUniquePath(movie.getNameMovie());
        movie.setPath(pathGenerated);
        return movieMapper.toDTO(movieRepository.save(movie));
    }

    public MovieDTO update(Long movieId, MovieDTO movieDTO) {
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(EntityNotFoundException::new);

        if (!movie.getNameMovie().equals(movieDTO.getNameMovie())) {
            String uniquePath = generateUniquePath(movie.getNameMovie());
            if (movie.getId().equals(movieId) && uniquePath.equals(movie.getPath())) {
                return null;
            }
            movie.setPath(uniquePath);
        }

        movieMapper.toEntityForUpdate(movieDTO, movie);

        movie = movieRepository.save(movie);

        mapEpisodes(movie.getEpisodes(), movieDTO.getEpisodes());

        return movieMapper.toDTO(movie);
    }

    private void mapEpisodes(Set<Episode> episodes, List<EpisodeDTO> episodeDTOS) {
        boolean isEpisodesEmpty = Objects.isNull(episodes) || episodes.isEmpty();
        boolean isEpisodeDTOSEmpty = Objects.isNull(episodeDTOS) || episodeDTOS.isEmpty();

        if (isEpisodesEmpty || isEpisodeDTOSEmpty) {
            return;
        }

        List<EpisodeDTO> sortedEpisodeDTOS = episodeDTOS.stream()
                .sorted(Comparator.comparingLong(EpisodeDTO::getEpisodeCount))
                .toList();

        List<Episode> sortedEpisodes = episodes.stream()
                .sorted(Comparator.comparingLong(Episode::getEpisodeCount))
                .toList();

        for (int i = 0; i < sortedEpisodes.size(); i++) {
            EpisodeDTO episodeDTO = sortedEpisodeDTOS.get(i);
            Episode episode = sortedEpisodes.get(i);
            episodeDTO.setId(episode.getId());
            episode.setTempId(episodeDTO.getTempId());
        }
    }

    private String getPresignedLink(String link) {
        if (Objects.isNull(link)) {
            return null;
        }
        return minioService.getPreSignedLink(link);
    }

    private String generateUniquePath(String movieName) {
        String basePath = Normalizer.normalize(movieName, Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "")
                .replaceAll("[^a-zA-Z0-9\\s-]", "")
                .trim()
                .toLowerCase()
                .replaceAll("\\s+", "-")
                .replace("đ", "d");

        String uniquePath = basePath;
        int counter = 1;
        while (movieRepository.existsByPath(uniquePath)) {
            uniquePath = basePath + "-" + counter;
            counter++;
        }

        return uniquePath;
    }
}
