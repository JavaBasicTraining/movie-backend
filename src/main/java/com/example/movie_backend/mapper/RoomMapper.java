package com.example.movie_backend.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.movie_backend.dto.RoomDTO;
import com.example.movie_backend.entity.Room;

@Mapper(componentModel = "spring")
public interface RoomMapper {
    @Mapping(target = "host.authorities", source = "host.authorities", ignore = true)
    @Mapping(target = "password", source = "password", ignore = true)
    RoomDTO toDTO(Room room);

    @Mapping(target = "host.authorities", source = "host.authorities", ignore = true)
    Room toEntity(RoomDTO roomDTO);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "host.authorities", source = "host.authorities", ignore = true)
    Room toEntityCreate(RoomDTO roomDTO);
}
