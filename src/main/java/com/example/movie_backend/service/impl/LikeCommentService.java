package com.example.movie_backend.service.impl;

import com.example.movie_backend.controller.exception.BadRequestException;
import com.example.movie_backend.dto.like_comment.LikeCommentDTO;
import com.example.movie_backend.entity.Comment;
import com.example.movie_backend.entity.LikeComment;
import com.example.movie_backend.entity.User;
import com.example.movie_backend.mapper.LikeCommentMapper;
import com.example.movie_backend.repository.CommentRepository;
import com.example.movie_backend.repository.LikeCommentRepository;
import com.example.movie_backend.repository.UserRepository;
import com.example.movie_backend.service.ILikeCommentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class LikeCommentService implements ILikeCommentService {

    public final LikeCommentRepository repository;
    public final UserRepository userRepository;
    public final LikeCommentMapper likeCommentMapper;
    public final CommentRepository commentRepository;

    @Override
    @Transactional
    public LikeCommentDTO createOrUpdate(@Valid LikeCommentDTO dto) {
        User user = userRepository
                .findById(dto.getUser().getId())
                .orElseThrow(() -> new BadRequestException("User not found"));

        Comment comment = commentRepository
                .findById(dto.getComment().getId())
                .orElseThrow(() -> new BadRequestException("Comment not found"));

        LikeComment likeComment = this.repository
                .findByCommentIdAndUserId(user.getId(), comment.getId())
                .orElse(null);

        if (Objects.isNull(likeComment)) {
            likeComment = new LikeComment();
            likeComment.setUser(user);
            likeComment.setComment(comment);
        }
        likeComment = repository.save(likeComment);
        return likeCommentMapper.toDTO(likeComment);
    }

    @Override
    @Transactional(readOnly = true)
    public List<LikeCommentDTO> findLikeCommentByUserIdAndMovieId(Long movieId, Long userId) {
        return new ArrayList<>();
    }

    @Override
    public Boolean delete(Long id) {
        repository.deleteById(id);
        return true;
    }

    @Override
    @Transactional(readOnly = true)
    public LikeCommentDTO findByCommentIdAndUserId(Long commentId, Long userId) {
        return this.repository.findByCommentIdAndUserId(commentId, userId)
                .map(likeCommentMapper::toDTO)
                .orElse(null);
    }
}