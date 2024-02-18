package com.example.movie_backend.dto.evaluation;
import com.example.movie_backend.entity.Evaluation;
import org.springframework.stereotype.Component;

@Component
public class EvaluationMapper {
    public Evaluation toEntity(EvaluationDTO dto)
    {
        return Evaluation.builder()
                .star(dto.getStar())
                .userId(dto.getUserId())
                .movieId(dto.getMovieId())
                .build();
    }

    public Evaluation toEntity(EvaluationDTO dto, Long id)
    {
        return Evaluation.builder()
                .star(dto.getStar())
                .userId(dto.getUserId())
                .movieId(dto.getMovieId())
                .build();
    }


    public EvaluationDTO toDTO (Evaluation entity)
    {
        return EvaluationDTO.builder()
                .id(entity.getId())
                .star(entity.getStar())
                .userId(entity.getUserId())
                .movieId(entity.getMovieId())
                .build();
    }
}
