package com.example.movie_backend.controller.interfaces;

import com.example.movie_backend.dto.room_chat.RoomChatDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/v1/room-chat")
public interface IRoomChatController {
    @PostMapping("create")
    ResponseEntity<RoomChatDTO> create(@RequestBody  RoomChatDTO roomChat);
    @PutMapping("update")

    ResponseEntity<RoomChatDTO> update(@RequestBody RoomChatDTO roomChat, Long id);
    @DeleteMapping("{id}")

    ResponseEntity<Boolean> delete(@PathVariable Long id);

    @GetMapping("{id}")
    ResponseEntity<RoomChatDTO> getById(@PathVariable Long id);
}
