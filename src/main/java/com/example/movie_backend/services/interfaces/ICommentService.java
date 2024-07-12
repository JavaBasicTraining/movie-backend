package com.example.movie_backend.services.interfaces;

import com.example.movie_backend.dto.comment.CommentDTO;

public interface ICommentService  {
    CommentDTO create(CommentDTO dto);

    CommentDTO update(CommentDTO dto, Long id);

    CommentDTO getById(Long id);


    Boolean delete(Long id);

}
