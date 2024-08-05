package com.example.movie_backend.services;

import com.example.movie_backend.controller.exception.EntityNotFoundException;
import com.example.movie_backend.controller.exception.ErrorHandler;
import com.example.movie_backend.controller.request.CreateMovieRequest;
import com.example.movie_backend.controller.request.QueryMovieRequest;
import com.example.movie_backend.dto.episode.EpisodeDTO;
import com.example.movie_backend.dto.episode.EpisodeMapper;
import com.example.movie_backend.dto.movie.*;
import com.example.movie_backend.entity.Episode;
import com.example.movie_backend.entity.Movie;
import com.example.movie_backend.minio.service.MinioService;
import com.example.movie_backend.repository.EpisodeRepository;
import com.example.movie_backend.repository.MovieRepository;
import com.example.movie_backend.services.interfaces.IMovieService;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.errors.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.multipart.MultipartFile;

import javax.ws.rs.BadRequestException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class MovieService implements IMovieService {
    private final MinioClient minioClient;
    private static final String BUCKET_NAME = "student";
    public final MovieRepository repository;
    public final MovieMapper mapper;
    public final EpisodeMapper episodeMapper;
    public final MinioService minioService;
    public final ErrorHandler errorHandler;
    public final EpisodeRepository episodeRepository;


    public void uploadByFile(MultipartFile file, String object) throws IOException, ServerException, InsufficientDataException, ErrorResponseException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        minioClient.putObject(
                PutObjectArgs.builder()
                        .bucket(BUCKET_NAME)
                        .object(object)
                        .contentType(file.getContentType())
                        .stream(file.getInputStream(), file.getSize(), -1)
                        .build()
        );
    }

    private boolean isImage(String contentType) {
        return contentType.equals("image/png")
                || contentType.equals("image/jpg")
                || contentType.equals("image/jpeg");
    }

    private boolean isVideo(String contentType) {
        return contentType.equals("video/mp4")
                || contentType.equals("video/quicktime")
                || contentType.equals("video/vnd.dlna.mpeg-tts");
    }


    @Override
    public MovieDTO createFileMovie(CreateRequestFileMovie fileMovie, Long movieId, Set<Long> episodeId) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        Movie movie = repository.findById(movieId).orElse(new Movie());
        Set<Episode> episodes = movie.getEpisodes();

        String contentTypePoster = fileMovie.getFilePoster().getContentType();
        if (isImage(contentTypePoster)) {
            String posterPathMovie = "poster" + "/" + fileMovie.getFilePoster().getOriginalFilename();
            uploadByFile(fileMovie.getFilePoster(), posterPathMovie);
            movie.setId(movieId);
            movie.setPosterUrl(posterPathMovie);
        } else {
            throw new RuntimeException();
        }
        String contentTypeMovie = fileMovie.getFileMovie().getContentType();
        if (isVideo(contentTypeMovie)) {
            String videoPath = "video" + "/" + fileMovie.getFileMovie().getOriginalFilename();
            uploadByFile(fileMovie.getFileMovie(), videoPath);
            movie.setId(movieId);
            movie.setVideoUrl(videoPath);

        } else {
            throw new RuntimeException();
        }

        if (episodeId == null) {
            return null;
        } else {
            Set<String> contentPosterEpisode = fileMovie.getFilePosterEpisode().stream().map(item -> item.getContentType()).collect(Collectors.toSet());
            for (String content : contentPosterEpisode) {
                if (isImage(content)) {
                    for (MultipartFile path : fileMovie.getFilePosterEpisode()) {
                        String posterPathEpisode = "poster/" + path.getOriginalFilename();
                        uploadByFile(path, posterPathEpisode);
                        for (Long id : episodeId) {
                            episodes.add(Episode.builder()
                                    .id(id)
                                    .movie(Movie.builder().id(movieId).build())
                                    .videoUrl(posterPathEpisode)
                                    .build());
                        }
                    }
                }
            }
        }
        Set<String> contentVideoEpisode = fileMovie.getFileMovieEpisode()
                .stream().map(item -> item.getContentType()).collect(Collectors.toSet());
        for (String content : contentVideoEpisode) {
            if (isVideo(content)) {
                for (MultipartFile path : fileMovie.getFileMovieEpisode()) {
                    String videoPathEpisode = "video/" + path.getOriginalFilename();
                    uploadByFile(path, videoPathEpisode);
                    for (Long id : episodeId) {
                        episodes.add(Episode.builder()
                                .id(id)
                                .movie(Movie.builder().id(movieId).build())
                                .videoUrl(videoPathEpisode)
                                .build());
                    }
                }
            }

        }
        return mapper.toDTO(repository.save(movie));
    }

    public void uploadMovieFile(Long movieId, MultipartFile file, String type) {
        try {
            Movie movie = repository.findById(movieId).orElseThrow(
                    () -> new EntityNotFoundException()
            );
            uploadMovieFile(movie, file, type);
        } catch (Exception exception) {
            log.error("Upload movie file failed {}", exception.getMessage());
            throw new RuntimeException("Upload movie file failed by id: " + movieId);
        }
    }

    public void uploadMovieFile(Movie movie, MultipartFile file, String type) {
        try {
            String object = "movies/" + movie.getId() + "/" + type + "/" + file.getOriginalFilename();
            if ("poster".equals(type)) {
                movie.setPosterUrl(object);
            } else {
                movie.setVideoUrl(object);
            }
                repository.save(movie);
            uploadByFile(file, object);
        } catch (Exception exception) {
            log.error("Upload movie file failed {}", exception.getMessage());
            throw new RuntimeException("Upload movie file failed by id: " + movie.getId());
        }
    }

    @Override
    public void uploadMovieFile(Long id, MultipartFile poster, MultipartFile video) {
        Movie movie = repository.findById(id).orElseThrow(
                () -> new EntityNotFoundException()
        );
        uploadMovieFile(movie, poster, video);
    }

    @Override
    public void uploadMovieFile(Movie movie, MultipartFile poster, MultipartFile video) {
        uploadMovieFile(movie, poster, "poster");
       if (video == null)
       {
           return;
       }else
       {
           uploadMovieFile(movie, video, "video");
       }

    }

    @Override
    public void uploadEpisodeFile(Long movieId, Long episodeId, MultipartFile poster, MultipartFile video) {
        if (!repository.existsById(movieId)) {
            throw new EntityNotFoundException();
        }
        Episode episode = episodeRepository.findById(episodeId).orElseThrow(
                () -> new EntityNotFoundException()
        );
        uploadEpisodeFile(episode, movieId, poster, "poster");
        uploadEpisodeFile(episode, movieId, video, "video");
    }
    public void uploadEpisodeFile(Episode episode, Long movieId, MultipartFile file, String type) {
        try {
            String object = String.format(
                    "movies/%s/episodes/%s/%s/%s",
                    movieId,
                    episode.getId(),
                    type,
                    file.getOriginalFilename()
            );
            if ("poster".equals(type)) {
                episode.setPosterUrl(object);
            } else {
                episode.setVideoUrl(object);
            }
            episodeRepository.save(episode);

            uploadByFile(file, object);
        } catch (Exception ex) {
            log.error("Upload episode file failed {}", ex.getMessage());
            throw new RuntimeException("Upload episode file failed by id: " + episode.getId());
        }
    }

    @Override
    public MovieDTO create(CreateMovieRequest dto, MultipartFile filePoster, MultipartFile fileMovie) throws
            IOException, ServerException, InsufficientDataException, ErrorResponseException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        Movie movie = mapper.toEntityCreateMovieRequest(dto);
        String contentTypePoster = filePoster.getContentType();
        if (isImage(contentTypePoster)) {
            String posterPath = "poster" + "/" + filePoster.getOriginalFilename();
            uploadByFile(dto.getFilePoster(), posterPath);
            movie.setPosterUrl(posterPath);
        } else {
            throw new RuntimeException();
        }
        String contentTypeMovie = fileMovie.getContentType();
        if (isVideo(contentTypeMovie)) {
            String videoPath = "video" + "/" + fileMovie.getOriginalFilename();
            uploadByFile(fileMovie, videoPath);
            movie.setVideoUrl(videoPath);
        } else {
            throw new RuntimeException();
        }
        if (Objects.nonNull(dto.getIdGenre()) && !dto.getIdGenre().isEmpty() && dto.getIdGenre().stream().noneMatch(id -> id == 0)) {
            for (EpisodeDTO episode : dto.getEpisodes()) {
                episode.setMovie(mapper.toDTO(movie));
            }
            movie.setEpisodes(dto.getEpisodes().stream().map(item -> episodeMapper.toEntity(item)).collect(Collectors.toSet()));
            return mapper.toDTO(repository.save(movie));
        } else {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Ids are null or empty or Ids = 0!!!");
        }
    }

    public ResponseEntity<Object> handleHttpClientErrorException(HttpClientErrorException ex) {
        ResponseEntity<Object> response = errorHandler.handleBadRequest(ex);
        return response;
    }

    @Override
    public MovieDTO update(CreateMovieRequest dto, Long id, MultipartFile filePoster, MultipartFile fileMovie) throws
            ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        Movie movie = mapper.toEntityCreateMovieRequest(dto, id);
        String contentTypePoster = filePoster.getContentType();
        if (isImage(contentTypePoster)) {

            String posterPath = "poster" + "/" + filePoster.getOriginalFilename();
            uploadByFile(filePoster, posterPath);
            movie.setPosterUrl(posterPath);
        } else {
            throw new RuntimeException();
        }
        String contentTypeMovie = fileMovie.getContentType();

        if (isVideo(contentTypeMovie)) {
            String videoPath = "video" + "/" + fileMovie.getOriginalFilename();
            uploadByFile(fileMovie, videoPath);
            movie.setVideoUrl(videoPath);

        } else {
            throw new RuntimeException();
        }

        if (Objects.nonNull(dto.getIdGenre()) && !dto.getIdGenre().isEmpty() && dto.getIdGenre().stream().noneMatch(ids -> ids == 0)) {
            return mapper.toDTO(repository.save(movie));
        } else {
            HttpClientErrorException ex = new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Bad Request");
            throw new HttpClientErrorException(handleHttpClientErrorException(ex).getStatusCode(), "ids are null or empty or ids = 0!!!");

        }

    }

    @Override
    public MovieDTO getById(Long id) {
        return this.repository.findById(id)
                .map(this.mapper::toDTO)
                .orElseThrow(
                        () -> new BadRequestException("Movie not found")
                );
    }


    @Override
    public Boolean delete(Long id) {
        repository.deleteById(id);
        return true;
    }

    @Override
    public List<MovieDTOWithoutJoin> query(String name) {
        return repository.query(name, Pageable.ofSize(20)).stream().map(
                mapper::toDTOWithoutJoin
        ).toList();
    }

    @Override
    public Page<MovieDTOWithoutJoin> query(QueryMovieRequest request, Pageable pageable) {
        return repository.query(request.getKeyword(), pageable)
                .map(item -> {
                            MovieDTOWithoutJoin movieDTO = mapper.toDTOWithoutJoin(item);
                            if (item.getPosterUrl() != null && item.getVideoUrl() != null) {
                                String linkPoster = this.minioService.getPreSignedLink(item.getPosterUrl());
                                movieDTO.setPosterUrl(linkPoster);
                                String linkVideo = this.minioService.getPreSignedLink(item.getVideoUrl());
                                movieDTO.setVideoUrl(linkVideo);
                            }
                            return movieDTO;
                        }
                );
    }


    public Set<MovieDTO> filterListMovie(String nameMovie) {

        return repository.filterListMovie(nameMovie).stream()
                .map((mapper::toDTO)).collect(Collectors.toSet());
    }

    public MovieDTO filterMovie(String nameMovie) {
        return repository.filterMovie(nameMovie)
                .map(item -> {
                    MovieDTO movieDTO = mapper.toDTO(item);
                    if (item.getPosterUrl() != null && item.getVideoUrl() != null) {
                        String linkPoster = this.minioService.getPreSignedLink(item.getPosterUrl());
                        movieDTO.setPosterUrl(linkPoster);
                        String linkVideo = this.minioService.getPreSignedLink(item.getVideoUrl());
                        movieDTO.setVideoUrl(linkVideo);
                    }
                    return movieDTO;
                })
                .orElseThrow(
                        () -> new BadRequestException("Movie not found")
                );
    }

    public MovieDTO createWithEpisode(MovieEpisodeRequest dto) {
        Movie movie = mapper.toNewMovieWithEpisodes(dto);
        return mapper.toDTO(repository.save(movie));
    }

    public MovieDTO updateWithEpisode(MovieEpisodeRequest dto, Long id ) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException();
        }
      {
          Movie movie = mapper.toNewMovieWithEpisodes(dto,id);
          return mapper.toDTO(repository.save(movie));
      }
    }

}
