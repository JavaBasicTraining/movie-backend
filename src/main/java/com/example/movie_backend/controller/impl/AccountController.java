package com.example.movie_backend.controller.impl;

import com.example.movie_backend.dto.user.UserDTO;
import com.example.movie_backend.entity.User;
import com.example.movie_backend.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("api/account")
@RestController
@RequiredArgsConstructor
public class AccountController {

    private final IUserService userService;

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
