package com.example.movie_backend.service;

import com.example.movie_backend.dto.like_comment.LikeCommentDTO;

import java.util.List;

public interface ILikeCommentService {
    LikeCommentDTO create(LikeCommentDTO dto);

    List<LikeCommentDTO> findLikeCommentByUserIdAndMovieId (Long movieId , Long userId);

    Boolean delete(Long id);
}
