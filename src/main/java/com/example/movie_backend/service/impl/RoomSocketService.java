package com.example.movie_backend.service.impl;

import com.example.movie_backend.controller.exception.ServerErrorException;
import com.example.movie_backend.dto.ChatMessage;
import com.example.movie_backend.dto.RoomDTO;
import com.example.movie_backend.dto.user.UserDTO;
import com.example.movie_backend.service.IRoomService;
import com.example.movie_backend.service.IRoomSocketService;
import com.example.movie_backend.service.IUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Service
@RequiredArgsConstructor
public class RoomSocketService implements IRoomSocketService {
    private static final Map<UUID, RoomDTO> roomMap = new ConcurrentHashMap<>();

    private final IRoomService roomService;
    private final IUserService userService;

    @Override
    public ChatMessage handleChatMessage(UUID roomId, ChatMessage chatMessage) {
        return chatMessage;
    }

    @Override
    public List<UserDTO> loadParticipants(UUID roomId, UserDTO user) {
        if (Objects.isNull(user) || Objects.isNull(user.getId())) {
            throw new ServerErrorException("User is null");
        }

        UserDTO fetchedUser = userService.getUser(user.getId());

        if (Objects.isNull(fetchedUser)) {
            throw new ServerErrorException("User not found: " + user.getId());
        }

        RoomDTO roomDTO = roomMap.get(roomId);
        if (Objects.isNull(roomDTO)) {
            roomDTO = roomService.getRoomById(roomId);
        }

        if (Objects.isNull(roomDTO)) {
            throw new ServerErrorException("Room not found by id: " + roomId);
        }

        List<UserDTO> currentParticipants = Optional
                .ofNullable(roomDTO.getParticipants())
                .orElse(new ArrayList<>());

        List<UserDTO> updatedParticipants = new ArrayList<>(currentParticipants);
        addIfAbsent(updatedParticipants, fetchedUser);
        roomDTO.setParticipants(updatedParticipants.stream().distinct().toList());
        roomMap.put(roomId, roomDTO);

        return currentParticipants;
    }

    private void addIfAbsent(List<UserDTO> participants, UserDTO user) {
        if (participants.stream().noneMatch(p -> p.getId().equals(user.getId()))) {
            participants.add(user);
        }
    }
}
