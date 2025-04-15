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

    @Mapping(target = "movie", ignore = true)
    EpisodeDTO toDTO(Episode episode);

    @Mapping(target = "movie", ignore = true)
    Episode toEntity(EpisodeDTO episodeDTO);

    @Mapping(target = "movie", ignore = true)
    Episode toEntity(EpisodeDTO episodeDTO, Long id);

    @Mapping(target = "videoPresignedUrl", ignore = true)
    @Mapping(target = "posterPresignedUrl", ignore = true)
    @Mapping(target = "movieId", ignore = true)
    @Mapping(target = "movie", ignore = true)
    @Named("dtoWithoutMovie")
    EpisodeDTO toDTOWithoutMovie(Episode episode);

    @IterableMapping(qualifiedByName = "dtoWithoutMovie")
    List<EpisodeDTO> mapEpisodes(Set<Episode> episodes);
}
