package com.example.movie_backend.services;


import com.example.movie_backend.entity.User;
import com.example.movie_backend.model.user.RegisterRequest;
import com.example.movie_backend.repository.UserRepository;
import com.example.movie_backend.services.interfaces.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder; // ma hoa password

    @Override
    public User create(User e) {
        return repository.save(e);
    }

    @Override
    public List<User> getList() {
        return repository.findAll();
    }

    @Override
    public void register(RegisterRequest request) {
        User user = User.builder()
                .username(request.getUsername())
                .passwordHash(
                        passwordEncoder.encode(request.getPassword())
                        // password bắt buộc phải mã hóa, chứ k được lưu bản gốc
                )
                .firstName(request.getFistName())
                .lastName(request.getLastName())
                .build();

        this.repository.save(user);
    }
}
