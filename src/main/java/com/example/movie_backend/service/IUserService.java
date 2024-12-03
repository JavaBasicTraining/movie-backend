package com.example.movie_backend.service;

import com.example.movie_backend.dto.user.UserDTO;
import com.example.movie_backend.entity.User;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.List;

public interface IUserService {
    UserDTO create(UserDTO user);

    List<User> getList();

    UserDTO getUser(String userName);

    UserDTO getUser(Long id);

    UserDTO getUserFromJwt(Jwt jwt);
}
