package com.example.movie_backend.service.impl;

import com.example.movie_backend.controller.exception.BadRequestException;
import com.example.movie_backend.dto.like_comment.LikeCommentDTO;
import com.example.movie_backend.mapper.LikeCommentMapper;
import com.example.movie_backend.entity.Comment;
import com.example.movie_backend.entity.LikeComment;
import com.example.movie_backend.entity.User;
import com.example.movie_backend.repository.CommentRepository;
import com.example.movie_backend.repository.LikeCommentRepository;
import com.example.movie_backend.repository.MovieRepository;
import com.example.movie_backend.repository.UserRepository;
import com.example.movie_backend.service.ILikeCommentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
public class LikeCommentService implements ILikeCommentService {

    public final LikeCommentRepository repository;
    public final UserRepository userRepository;
    public final LikeCommentMapper mapper;
    public final MovieRepository movieRepository;
    public final CommentRepository commentRepository;

    @Override
    @Transactional
    public LikeCommentDTO create(LikeCommentDTO dto) {
        LikeComment likeComment = mapper.toEntity(dto);

        User user = userRepository
                .findById(dto.getIdUser())
                .orElseThrow(() -> new BadRequestException("User not found"));
        Comment comment = commentRepository
                .findById(dto.getIdComment())
                .orElseThrow(() -> new BadRequestException("Comment not found"));
        if (likeComment.getId() == null) {
            likeComment = new LikeComment();
            likeComment.setUser(user);
            likeComment.setComment(comment);
            likeComment.setLikeCount(1L);
        } else {
            if (likeComment.getLikeCount() > 0) {
                likeComment.setLikeCount(likeComment.getLikeCount() - 1);
            } else {
                throw new BadRequestException("Like count cannot be negative");
            }
        }
        likeComment = repository.save(likeComment);
        return mapper.toDTO(likeComment);
    }

    @Override
    public List<LikeCommentDTO> findLikeCommentByUserIdAndMovieId(Long movieId, Long userId) {
        return repository.findLikeCommentByUserIdAndMovieId(movieId, userId)
                .stream()
                .map(mapper::toDTO)
                .toList();
    }

    @Override
    public Boolean delete(Long id) {
        repository.deleteById(id);
        return true;
    }
}
