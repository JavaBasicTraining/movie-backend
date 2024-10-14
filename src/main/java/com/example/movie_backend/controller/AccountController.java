package com.example.movie_backend.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.example.movie_backend.dto.user.UserMapper;
import com.example.movie_backend.repository.UserRepository;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.beans.factory.annotation.Autowired;
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
    // private final AuthenticationManagerBuilder authenticationManagerBuilder;

    private final IUserService userService;
public final UserMapper mapper;
//    private final KeycloakService keycloakService;
    private final UserRepository userRepository;
//    private final CommonProperties.Security securityProperties;

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

//    @PostMapping("login")
//    public ResponseEntity<JWTToken> login(@RequestBody LoginRequest request) {
//        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
//            request.getUsername(),
//            request.getPassword()
//
//        );
//        Authentication authentication = authenticationManagerBuilder.getObject()
//            .authenticate(authenticationToken);
//        SecurityContextHolder.getContext()
//            .setAuthentication(authentication);
    ////        String jwt = this.createToken(authentication, request.isRememberMe());
////        HttpHeaders httpHeaders = new HttpHeaders();
////        httpHeaders.setBearerAuth(jwt);
////        return new ResponseEntity<>(new JWTToken(jwt), httpHeaders, HttpStatus.OK);
//        return null;
//    }

    @PostMapping("getListUser")
    public ResponseEntity<List<User>> getListUser() {
        return ResponseEntity.ok(userService.getList());
    }

    @GetMapping("getUser")
    public ResponseEntity<UserDTO> getUser(@RequestParam String userName) {
        return ResponseEntity.ok(userService.getUser(userName));
    }

//    @GetMapping("users")
//    public List<UserRepresentation> getUsers() {
//        return keycloakService.getUsers();
//    }

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
