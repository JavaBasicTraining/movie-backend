package com.example.movie_backend.services.interfaces;

import com.example.movie_backend.entity.User;
import com.example.movie_backend.model.user.LoginRequest;
import com.example.movie_backend.model.user.LoginResponse;
import com.example.movie_backend.model.user.RegisterRequest;
import com.example.movie_backend.model.user.RegisterResponse;

import java.util.UUID;

public interface IUserService  {
    LoginResponse login(LoginRequest request);

    RegisterResponse register(RegisterRequest request);
}
