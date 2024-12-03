package com.example.movie_backend.service;

import com.example.movie_backend.dto.RoomDTO;

import java.util.UUID;

public interface IRoomService {

    /**
     * Get room by id
     *
     * @param id room id
     * @return RoomDTO room detail
     */
    RoomDTO getRoomById(UUID id);

    /**
     * Create room
     *
     * @param roomDTO create room data
     * @return RoomDTO room detail
     */
    RoomDTO create(RoomDTO roomDTO);
}
