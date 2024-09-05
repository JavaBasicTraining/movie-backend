package com.example.movie_backend.services;

import com.example.movie_backend.dto.comment.CommentDTO;
import com.example.movie_backend.dto.comment.CommentMapper;
import com.example.movie_backend.entity.Comment;
import com.example.movie_backend.entity.Movie;
import com.example.movie_backend.repository.CommentRepository;
import com.example.movie_backend.repository.MovieRepository;
import com.example.movie_backend.services.interfaces.ICommentService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.ws.rs.BadRequestException;
import java.util.List;
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
    @Transactional
    public CommentDTO create(CommentDTO dto) {
        Comment comment = mapper.toEntity(dto);
        comment = repository.save(comment);
        return mapper.toDTO(comment);
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

    @Override
    public List<CommentDTO> getCommentByMovieId(Long movieId) {
        return repository.getCommentByMovieId(movieId).stream().map(mapper::toDTO).toList();
    }

    @Override
    public List<CommentDTO> getListCommentByMovieIdUserId(Long userId, Long movieId) {
        return repository.getListCommentByMovieIdUserId(userId,movieId).stream().map(mapper::toDTO).toList();
    }
}



