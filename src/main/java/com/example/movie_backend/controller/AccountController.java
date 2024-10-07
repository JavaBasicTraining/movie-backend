package com.example.movie_backend.controller;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.keycloak.representations.account.UserRepresentation;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.movie_backend.config.CommonProperties;
import com.example.movie_backend.dto.user.UserDTO;
import com.example.movie_backend.entity.User;
import com.example.movie_backend.keycloak.KeycloakService;
import com.example.movie_backend.model.user.RegisterRequest;
import com.example.movie_backend.services.interfaces.IUserService;

@RequestMapping("api/account")
@RestController
public class AccountController {
    // private final AuthenticationManagerBuilder authenticationManagerBuilder;

    private final IUserService userService;
    @Autowired
    private KeycloakService keycloakService;
//    private final CommonProperties.Security securityProperties;

    public AccountController(IUserService userService, KeycloakService keycloakService) {
        this.userService = userService;
        this.keycloakService = keycloakService;
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

    @GetMapping("users")
    public List<UserRepresentation> getUsers() {
        return keycloakService.getUsers();
    }

    @GetMapping("info")
    public ResponseEntity<UserDTO> getAccountInfo(@AuthenticationPrincipal Jwt jwt) {
        if (jwt != null) {
            String username = jwt.getClaimAsString("preferred_username");
            UserDTO userDTO = userService.getUser(username);
            return ResponseEntity.ok(userDTO);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

}
