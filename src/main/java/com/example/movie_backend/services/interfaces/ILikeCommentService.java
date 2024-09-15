package com.example.movie_backend.services.interfaces;

import com.example.movie_backend.dto.like_comment.LikeCommentDTO;
import com.example.movie_backend.entity.LikeComment;

import java.util.List;
import java.util.Set;

public interface ILikeCommentService {
    LikeCommentDTO create(LikeCommentDTO dto);

    LikeCommentDTO findById(Long id);


    List<LikeCommentDTO> findLikeCommentByUserIdAndMovieId (Long movieId , Long userId);
    Boolean delete(Long id);
}
