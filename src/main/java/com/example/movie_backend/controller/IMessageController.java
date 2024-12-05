package com.example.movie_backend.controller.interfaces;

import com.example.movie_backend.dto.message.MessageDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/message")
public interface IMessageController {

    @PostMapping("create")
    ResponseEntity<MessageDTO> create(@RequestBody MessageDTO roomChat);


    @PutMapping("update")

    ResponseEntity<MessageDTO> update(@RequestBody MessageDTO roomChat, Long id);
    @DeleteMapping("{id}")

    ResponseEntity<Boolean> delete(@PathVariable Long id);
    @GetMapping("{id}")
    ResponseEntity<MessageDTO> getById(@PathVariable  Long id);

    @GetMapping("getAll")
    ResponseEntity<List<MessageDTO>> getListMessage();
}
