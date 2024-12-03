package com.example.movie_backend.controller.impl;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.movie_backend.dto.user.UserDTO;
import com.example.movie_backend.mapper.UserMapper;
import com.example.movie_backend.entity.User;
import com.example.movie_backend.service.IUserService;

@RequestMapping("api/account")
@RestController
public class AccountController {

    private final IUserService userService;
    public final UserMapper mapper;

    public AccountController(IUserService userService, UserMapper mapper) {
        this.userService = userService;
        this.mapper = mapper;
    }

    @PostMapping("create")
    public ResponseEntity<UserDTO> create(@RequestBody @Valid UserDTO request) {
        return ResponseEntity.ok(userService.create(request));
    }

    @PostMapping("getListUser")
    public ResponseEntity<List<User>> getListUser() {
        return ResponseEntity.ok(userService.getList());
    }

    @GetMapping("getUser")
    public ResponseEntity<UserDTO> getUser(@RequestParam String userName) {
        return ResponseEntity.ok(userService.getUser(userName));
    }

    @GetMapping("info")
    public ResponseEntity<UserDTO> getAccountInfo(@AuthenticationPrincipal Jwt jwt) {
        if (jwt != null) {
            return ResponseEntity.ok(userService.getUserFromJwt(jwt));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

}
