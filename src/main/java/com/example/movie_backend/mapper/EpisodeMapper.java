package com.example.movie_backend.mapper;

import com.example.movie_backend.dto.episode.EpisodeDTO;
import com.example.movie_backend.entity.Episode;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface EpisodeMapper {

    @Mapping(target = "movie.comments", ignore = true)
    EpisodeDTO toDTO(Episode episode);

    @Named("dtoWithoutMovie")
    @Mapping(target = "movie", ignore = true)
    EpisodeDTO toDTOWithoutMovie(Episode episode);

    @IterableMapping(qualifiedByName = "dtoWithoutMovie")
    List<EpisodeDTO> mapEpisodes(Set<Episode> episodes);
}
