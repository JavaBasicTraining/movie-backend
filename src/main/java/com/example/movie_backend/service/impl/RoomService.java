package com.example.movie_backend.service.impl;

import com.example.movie_backend.controller.exception.BadRequestException;
import com.example.movie_backend.dto.RoomDTO;
import com.example.movie_backend.entity.Room;
import com.example.movie_backend.mapper.RoomMapper;
import com.example.movie_backend.repository.RoomRepository;
import com.example.movie_backend.service.IRoomService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class RoomService implements IRoomService {
    private final RoomRepository roomRepository;
    private final RoomMapper roomMapper;

    @Override
    public RoomDTO getRoomById(UUID id) {
        Room room = roomRepository
                .findById(id)
                .orElseThrow(() -> new BadRequestException("Room not found"));
        return roomMapper.toDTO(room);
    }

    @Override
    public RoomDTO create(RoomDTO roomDTO) {
        Room room = roomMapper.toEntityCreate(roomDTO);
        return roomMapper.toDTO(roomRepository.save(room));
    }
}
