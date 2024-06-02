package com.example.movie_backend.services;

import com.example.movie_backend.dto.movie.MovieDTO;
import com.example.movie_backend.dto.movie.MovieDTOWithoutJoin;
import com.example.movie_backend.dto.movie.MovieMapper;
import com.example.movie_backend.entity.Movie;
import com.example.movie_backend.minio.service.MinioService;
import com.example.movie_backend.repository.MovieRepository;
import com.example.movie_backend.services.interfaces.IMovieService;
import org.springframework.stereotype.Service;

import javax.ws.rs.BadRequestException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieService implements IMovieService {

    public final MovieRepository repository;
    public final MovieMapper mapper;
    public final MinioService minioService;

    public MovieService(MovieRepository repository, MovieMapper mapper, MinioService minioService) {
        this.repository = repository;
        this.mapper = mapper;
        this.minioService = minioService;
    }

    @Override
    public MovieDTO create(MovieDTO dto) {
        Movie comment = mapper.toEntity(dto);
        return mapper.toDTO(repository.save(comment));
    }

    @Override
    public MovieDTO update(MovieDTO dto, Long id) {
        Movie comment = mapper.toEntity(dto, id);
        return mapper.toDTO(repository.save(comment));
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
    public List<MovieDTO> getList() {
        return repository.findAll()
                .stream()
                .map(
                        item -> {
                            MovieDTO movieDTO = mapper.toDTO(item);

                            if (item.getPosterUrl() != null) {
                                String link =  this.minioService.getPreSignedLink(item.getPosterUrl());
                                movieDTO.setPosterUrl(link);
                            }

                            if (item.getVideoMinioPath() != null) {
                                String link =  this.minioService.getPreSignedLink(item.getVideoMinioPath());
                                movieDTO.setVideoMinioPath(link);
                            }

                            return movieDTO;
                        }
                )
                .collect(Collectors.toList());
    }

    @Override
    public Boolean delete(Long id) {
        repository.deleteById(id);
        return true;
    }

    @Override
    public List<MovieDTOWithoutJoin> query(String name) {
        return repository.query(name).stream().map(
                mapper::toDTOWithoutJoin
        ).toList();
    }
}
