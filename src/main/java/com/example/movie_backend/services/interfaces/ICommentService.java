package com.example.movie_backend.services.interfaces;

import com.example.movie_backend.dto.comment.CommentDTO;
import com.example.movie_backend.dto.comment.CommentDTO;
import com.example.movie_backend.entity.Category;
import com.example.movie_backend.entity.Comment;

import java.util.List;
import java.util.UUID;

public interface ICommentService  {
    CommentDTO create(CommentDTO dto);

    CommentDTO update(CommentDTO dto, Long id);

    CommentDTO getById(Long id);


    Boolean delete(Long id);

}
