package com.example.movie_backend.controller;

import com.example.movie_backend.dto.user.UserDTO;
import com.example.movie_backend.dto.user.UserMapper;
import com.example.movie_backend.entity.User;
import com.example.movie_backend.model.user.RegisterRequest;
import com.example.movie_backend.repository.UserRepository;
import com.example.movie_backend.services.interfaces.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RequestMapping("api/account")
@RestController
public class AccountController {

    private final IUserService userService;
    public final UserMapper mapper;
    private final UserRepository userRepository;

    public AccountController(IUserService userService, UserMapper mapper, UserRepository userRepository) {
        this.userService = userService;
        this.mapper = mapper;
        this.userRepository = userRepository;
    }

    @PostMapping("create")
    public ResponseEntity<UserDTO> create(@RequestBody @Valid UserDTO request) {
        return ResponseEntity.ok(userService.create(request));
    }

    @PostMapping("register")
    public ResponseEntity<Void> register(@RequestBody @Valid RegisterRequest request) {
        userService.register(request);
        return ResponseEntity.noContent().build();
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
