package com.example.movie_backend.dto.evaluation;
import com.example.movie_backend.entity.Evaluation;
import com.example.movie_backend.entity.User;
import org.springframework.stereotype.Component;

@Component
public class EvaluationMapper {
    public Evaluation toEntity(EvaluationDTO dto)
    {
        return Evaluation.builder()
                .id(dto.getId())
                .star(dto.getStar())
                .user(User.builder()
                        .id(dto.getUserId()).build())
                .movieId(dto.getMovieId())
                .build();
    }

    public Evaluation toEntity(EvaluationDTO dto, Long id)
    {
        return Evaluation.builder()
                .id(id)
                .star(dto.getStar())
                .user(User.builder()
                        .id(dto.getUserId()).build())
                .movieId(dto.getMovieId())
                .build();
    }



    public EvaluationDTO toDTO (Evaluation entity)
    {
        return EvaluationDTO.builder()
                .id(entity.getId())
                .star(entity.getStar())
                .userId(entity.getUser().getId())
                .movieId(entity.getMovieId())
                .build();
    }
}
