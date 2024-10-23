package com.example.movie_backend.services;

import com.example.movie_backend.controller.exception.BadRequestException;
import com.example.movie_backend.dto.comment.CommentDTO;
import com.example.movie_backend.dto.comment.CommentMapper;
import com.example.movie_backend.entity.Comment;
import com.example.movie_backend.repository.CommentRepository;
import com.example.movie_backend.repository.MovieRepository;
import com.example.movie_backend.services.interfaces.ICommentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class CommentService implements ICommentService {

    public final CommentMapper mapper;
    public final CommentRepository repository;
    public final MovieRepository movieRepository;

    @Override
    @Transactional
    public CommentDTO create(CommentDTO dto) {
        Comment comment = mapper.toEntity(dto);
        comment.setCurrentDate(new Date());
        comment = repository.save(comment);
        return mapper.toDTO(comment);
    }

    @Override
    public CommentDTO update(CommentDTO dto, Long id) {
        Comment comment = mapper.toEntity(dto, id);
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
        return repository.getCommentByMovieId(movieId);
    }

    @Override
    public List<CommentDTO> getListCommentByMovieIdUserId(Long userId, Long movieId) {
        return repository.getListCommentByMovieIdUserId(userId,movieId)
                .stream()
                .map(mapper::toDTO)
                .toList();
    }
}



