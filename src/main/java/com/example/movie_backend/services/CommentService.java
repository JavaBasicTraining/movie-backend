package com.example.movie_backend.services;

import com.example.movie_backend.dto.comment.CommentDTO;
import com.example.movie_backend.dto.comment.CommentMapper;
import com.example.movie_backend.entity.Comment;
import com.example.movie_backend.repository.CommentRepository;
import com.example.movie_backend.services.interfaces.ICommentService;
import org.springframework.stereotype.Service;

import javax.ws.rs.BadRequestException;
import java.time.temporal.ValueRange;

@Service
public class CommentService  implements ICommentService {


    public final CommentMapper mapper;
    public final CommentRepository repository;

    public CommentService(CommentMapper mapper, CommentRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    @Override
    public CommentDTO create(CommentDTO dto) {
        Comment comment = mapper.toEntity(dto);
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



