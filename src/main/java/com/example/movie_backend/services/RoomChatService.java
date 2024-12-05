package com.example.movie_backend.services;


import com.example.movie_backend.controller.exception.BadRequestException;
import com.example.movie_backend.dto.room_chat.RoomChatDTO;
import com.example.movie_backend.dto.room_chat.RoomChatMapper;
import com.example.movie_backend.entity.RoomChat;
import com.example.movie_backend.repository.RoomChatRepository;
import com.example.movie_backend.services.interfaces.IRoomChatService;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoomChatService implements IRoomChatService {
    public final RoomChatRepository repository;
    public  final RoomChatMapper mapper;

    public RoomChatService(RoomChatRepository repository, RoomChatMapper mapper) {
        this.repository = repository;

        this.mapper = mapper;

    }

    @Override
    public RoomChatDTO create(RoomChatDTO friend) {
        RoomChat saveRoom = repository.save(mapper.toEntity(friend));
        return mapper.toDTO(saveRoom);
    }

    @Override
    public RoomChatDTO update(RoomChatDTO friend, Long id) {
        if (getById(id) == null) {
            return null;
        }
        RoomChat saveRoom = repository.save(mapper.toEntity(friend));
        return mapper.toDTO(saveRoom);
    }

    @Override
    public Boolean delete(Long id) {
        if (getById(id) == null) {
            return false;
        }
        return true;
    }

    @Override
    public RoomChatDTO getById(Long id) {
        return this.repository.findById(id)
                .map(this.mapper::toDTO)
                .orElseThrow(
                        () -> new BadRequestException("Movie not found")
                );
    }
}
