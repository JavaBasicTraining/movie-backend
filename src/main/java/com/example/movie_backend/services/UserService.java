package com.example.movie_backend.services;


import com.example.movie_backend.entity.Authority;
import com.example.movie_backend.entity.User;
import com.example.movie_backend.model.user.RegisterRequest;
import com.example.movie_backend.repository.AuthorityRepository;
import com.example.movie_backend.repository.UserRepository;
import com.example.movie_backend.services.interfaces.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {
    private final UserRepository repository;
    private final AuthorityRepository authorityRepository;
    private final PasswordEncoder passwordEncoder;

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
            )
            .firstName(request.getFistName())
            .lastName(request.getLastName())
            .authorities(
                request.getAuthorities()
                    .stream()
                    .map(
                        authority -> authorityRepository
                            .findById(authority)
                            .orElse(new Authority("USER"))
                    )
                    .collect(Collectors.toSet())
            )
            .build();

        this.repository.save(user);
    }
}
