package com.example.movie_backend.controller;

import com.example.movie_backend.controller.interfaces.IUserController;
import com.example.movie_backend.entity.User;
import com.example.movie_backend.model.user.LoginRequest;
import com.example.movie_backend.model.user.LoginResponse;
import com.example.movie_backend.model.user.RegisterRequest;
import com.example.movie_backend.model.user.RegisterResponse;
import com.example.movie_backend.services.interfaces.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController implements IUserController {

    private final IUserService iUserService;

    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(iUserService.login(request));
    }

    @Override
    public ResponseEntity<RegisterResponse> register(RegisterRequest request) {
        return ResponseEntity.ok(iUserService.register(request));
    }

//    @Override
//    public ResponseEntity<List<User>> getList() {
//        return ResponseEntity.ok(iUserService.getList());
//    }

}
