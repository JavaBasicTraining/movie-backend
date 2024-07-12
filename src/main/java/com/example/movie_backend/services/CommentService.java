package com.example.movie_backend.services;

import com.example.movie_backend.dto.comment.CommentDTO;
import com.example.movie_backend.dto.comment.CommentMapper;
import com.example.movie_backend.entity.Comment;
import com.example.movie_backend.entity.Movie;
import com.example.movie_backend.repository.CommentRepository;
import com.example.movie_backend.repository.MovieRepository;
import com.example.movie_backend.services.interfaces.ICommentService;
import org.springframework.stereotype.Service;

import javax.ws.rs.BadRequestException;
import java.util.Objects;

@Service
public class CommentService  implements ICommentService {


    public final CommentMapper mapper;
    public final CommentRepository repository;
    public final MovieRepository movieRepository;


    public CommentService(CommentMapper mapper, CommentRepository repository, MovieRepository movieRepository) {
        this.mapper = mapper;
        this.repository = repository;
        this.movieRepository = movieRepository;
    }

    @Override
    public CommentDTO create(CommentDTO dto) {
        Comment comment = mapper.toEntity(dto);
        for (Long id: dto.getIdMovies()) {
            Movie movie = movieRepository.findById(id).orElse(null);
            if (Objects.nonNull(movie)) {
                movie.addComment(comment);
            }
        }
        comment = repository.save(comment);

        return mapper.toDTO(repository.save(comment));
    }

    @Override
    public CommentDTO update(CommentDTO dto, Long id) {
        Comment comment = mapper.toEntity(dto,id);
        return mapper.toDTO(repository.save(comment));
    }

    @Override
    public CommentDTO getById(Long id) {
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
}



