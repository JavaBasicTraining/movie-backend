package com.example.movie_backend.dto.comment;

import com.example.movie_backend.dto.category.CategoryDTO;
import com.example.movie_backend.entity.Category;
import com.example.movie_backend.entity.Comment;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper {
    public Comment toEntity(CommentDTO dto) {
        return Comment.builder()
                .content(dto.getContent())
                .episodeId(dto.getEpisodeId())
                .userId(dto.getUserId())
                .build();

    }

    public CommentDTO toDTO (Comment entity)
    {
        return CommentDTO.builder()
                .id(entity.getId())
                .content(entity.getContent())
                .episodeId(entity.getEpisodeId())
                .userId(entity.getUserId())
                .build();
    }
    public Comment toEntity(CommentDTO dto,Long id) {
        return Comment.builder()
                .content(dto.getContent())
                .episodeId(dto.getEpisodeId())
                .userId(dto.getUserId())
                .build();

    }

}
