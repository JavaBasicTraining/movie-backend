package com.example.movie_backend.service;

import com.example.movie_backend.dto.comment.CommentDTO;

import java.util.List;

public interface ICommentService {
    CommentDTO create(CommentDTO dto);

    CommentDTO update(CommentDTO dto, Long id);

    CommentDTO getById(Long id);

    Boolean delete(Long id);

    List<CommentDTO> getCommentByMovieId(Long movieId);

    List<CommentDTO> getListCommentByMovieIdUserId(Long userId, Long movieId);
    List<CommentDTO> getListReplies(Long movieId, Long parentComment);

}
