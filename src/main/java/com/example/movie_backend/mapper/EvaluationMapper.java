package com.example.movie_backend.mapper;

import com.example.movie_backend.dto.evaluation.EvaluationDTO;
import com.example.movie_backend.entity.Evaluation;
import com.example.movie_backend.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public interface EvaluationMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "movies", ignore = true)
    Evaluation toEntity(EvaluationDTO dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "movies", ignore = true)
    Evaluation toEntity(EvaluationDTO dto, Long id);

    EvaluationDTO toDTO(Evaluation entity);
}
