package com.example.movie_backend.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.example.movie_backend.dto.user.UserMapper;
import com.example.movie_backend.repository.UserRepository;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.movie_backend.dto.user.UserDTO;
import com.example.movie_backend.entity.User;
import com.example.movie_backend.model.user.RegisterRequest;
import com.example.movie_backend.services.interfaces.IUserService;

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
            String username = jwt.getClaimAsString("preferred_username");
            Map<String, Object> resourceAccess = jwt.getClaim("resource_access");
            Map<String, Object> clientAccess = (Map<String, Object>) resourceAccess.get("movie_website_client");
            List<String>  roles = (List<String>) clientAccess.get("roles");

            UserDTO userDTO = userService.getUser(username);
            if (userDTO== null) {
                userDTO = new UserDTO();
                userDTO.setUserName(username);
                userDTO.setAuthorities(roles);
                User user = mapper.toEntity(userDTO);
                User savedUser = userRepository.save(user);
               userDTO = mapper.toDTO(savedUser);
            }
            return ResponseEntity.ok(userDTO);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

}
