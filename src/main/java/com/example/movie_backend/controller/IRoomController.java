package com.example.movie_backend.controller;

import com.example.movie_backend.dto.RoomDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequestMapping("api/v1/rooms")
public interface IRoomController {

    /**
     * Get room by id
     *
     * @param id room id
     * @return RoomDTO
     */
    @GetMapping("{id}")
    ResponseEntity<RoomDTO> getRoomById(@PathVariable("id") UUID id);

    /**
     * Get room by id
     *
     * @param roomDTO create room data
     * @return RoomDTO
     */
    @PostMapping
    ResponseEntity<RoomDTO> create(@RequestBody RoomDTO roomDTO);
}
