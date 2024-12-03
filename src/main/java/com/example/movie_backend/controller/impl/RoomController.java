package com.example.movie_backend.controller.impl;

import java.util.UUID;

import com.example.movie_backend.service.IRoomService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.example.movie_backend.controller.IRoomController;
import com.example.movie_backend.dto.RoomDTO;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class RoomController implements IRoomController {
    private final IRoomService roomService;

    @Override
    public ResponseEntity<RoomDTO> getRoomById(UUID id) {
        return ResponseEntity.ok(roomService.getRoomById(id));
    }

    @Override
    public ResponseEntity<RoomDTO> create(RoomDTO roomDTO) {
        return ResponseEntity.ok(roomService.create(roomDTO));
    }
}
