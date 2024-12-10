package com.example.movie_backend.service;

import com.example.movie_backend.dto.like_comment.LikeCommentDTO;

import java.util.List;

public interface ILikeCommentService {
    LikeCommentDTO createOrUpdate(LikeCommentDTO dto);

    List<LikeCommentDTO> findLikeCommentByUserIdAndMovieId (Long movieId , Long userId);

    Boolean delete(Long id);

    LikeCommentDTO findByCommentIdAndUserId(Long commentId, Long userId);
}
