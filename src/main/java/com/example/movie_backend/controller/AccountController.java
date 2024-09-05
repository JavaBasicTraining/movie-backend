package com.example.movie_backend.controller;


import com.example.movie_backend.dto.user.UserDTO;
import com.example.movie_backend.entity.User;
import com.example.movie_backend.model.user.LoginRequest;
import com.example.movie_backend.model.user.RegisterRequest;
import com.example.movie_backend.services.interfaces.IUserService;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.example.movie_backend.config.SecurityJwtConfiguration.AUTHORITIES_KEY;
import static com.example.movie_backend.config.SecurityJwtConfiguration.JWT_ALGORITHM;

@RequestMapping("api/account")
@RestController
@RequiredArgsConstructor
public class AccountController {
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtEncoder jwtEncoder;
    private final IUserService userService;
    private static final long TOKEN_VALIDITY_IN_SECONDS = 86400;
    private static final long TOKEN_VALIDITY_IN_SECONDS_FOR_REMEMBER_ME = 86400;


    @PostMapping("register")
    public ResponseEntity<Void> register(@RequestBody @Valid RegisterRequest request) {
        userService.register(request);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("login")
    public ResponseEntity<JWTToken> login(@RequestBody LoginRequest request) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                request.getUsername(),
                request.getPassword()

        );
        Authentication authentication = authenticationManagerBuilder.getObject()
                .authenticate(authenticationToken);
        SecurityContextHolder.getContext()
                .setAuthentication(authentication);
        String jwt = this.createToken(authentication, request.isRememberMe());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setBearerAuth(jwt);
        return new ResponseEntity<>(new JWTToken(jwt), httpHeaders, HttpStatus.OK);
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
    public ResponseEntity<UserDTO> getAccountInfo(Principal principal) {
        if (principal instanceof JwtAuthenticationToken token) {
            UserDTO userDTO = userService.getUser(token.getName());
            return ResponseEntity.ok(userDTO);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("getUserAuthority")
    public ResponseEntity<Set<User>> getUserAuthority() {
        return ResponseEntity.ok(userService.getUserAuthority());
    }


    public String createToken(Authentication authentication, boolean rememberMe) {
        String authorities = authentication.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));

        Instant now = Instant.now();
        Instant validity;
        if (rememberMe) {
            validity = now.plus(TOKEN_VALIDITY_IN_SECONDS_FOR_REMEMBER_ME, ChronoUnit.SECONDS);
        } else {
            validity = now.plus(TOKEN_VALIDITY_IN_SECONDS, ChronoUnit.SECONDS);
        }

        // @formatter:off
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuedAt(now)
                .expiresAt(validity)
                .subject(authentication.getName())
                .claim(AUTHORITIES_KEY, authorities)
                .build();

        JwsHeader jwsHeader = JwsHeader.with(JWT_ALGORITHM).build();
        return this.jwtEncoder.encode(JwtEncoderParameters.from(jwsHeader, claims)).getTokenValue();
    }

    public static class JWTToken {

        private String idToken;

        JWTToken(String idToken) {
            this.idToken = idToken;
        }

        @JsonProperty("id_token")
        String getIdToken() {
            return idToken;
        }

        void setIdToken(String idToken) {
            this.idToken = idToken;
        }
    }
}
