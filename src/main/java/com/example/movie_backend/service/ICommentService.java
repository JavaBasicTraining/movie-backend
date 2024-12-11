package com.example.movie_backend.service;

import com.example.movie_backend.controller.dto.response.LikeCountResponse;
import com.example.movie_backend.dto.comment.CommentDTO;
import com.example.movie_backend.dto.like_comment.LikeCommentDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICommentService {
    CommentDTO create(CommentDTO dto);

    CommentDTO update(CommentDTO dto, Long id);

    CommentDTO update(Long commentId, CommentDTO dto);

    CommentDTO getById(Long id);

    Boolean delete(Long id);

    Page<CommentDTO> getCommentByMovieId(Long movieId, Pageable pageable);

    List<CommentDTO> getListCommentByMovieIdUserId(Long userId, Long movieId);

    void like(Long id, LikeCommentDTO likeCommentDTO);

    void unLike(Long id, LikeCommentDTO likeCommentDTO);

    void likeOrUnlike(Long commentId, Boolean isLike);

    LikeCountResponse getLikeCount(Long commentId);
}
