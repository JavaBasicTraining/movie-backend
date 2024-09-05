package com.example.movie_backend.services;


import com.example.movie_backend.controller.exception.ConflictDataException;
import com.example.movie_backend.dto.user.UserDTO;
import com.example.movie_backend.dto.user.UserMapper;
import com.example.movie_backend.entity.Authority;
import com.example.movie_backend.entity.User;
import com.example.movie_backend.model.user.RegisterRequest;
import com.example.movie_backend.repository.AuthorityRepository;
import com.example.movie_backend.repository.UserRepository;
import com.example.movie_backend.services.interfaces.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.ws.rs.BadRequestException;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper mapper;

    @Override
    public User create(User e) {
        return userRepository.save(e);
    }

    @Override
    public List<User> getList() {
        return userRepository.findAll();
    }

    @Override
    public UserDTO getUser(String username) {
        return this.userRepository.findByUsername(username)
            .map(this.mapper::toDTO)
            .orElseThrow(
                () -> new BadRequestException("User not found")
            );
    }


    @Override
    public Set<User> getUserAuthority() {
        return userRepository.getUserAuthority();
    }

    @Override
    public void register(RegisterRequest request) {
        // check if existing
        User exitedUser = userRepository.findByUsername(request.getUsername()).orElse(null);
        if (Objects.nonNull(exitedUser)) {
            throw new ConflictDataException("This user is existed!");
        }
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
                        authority -> authorityRepository.findById(authority)
                            .orElse(new Authority("User"))
                    )
                    .collect(Collectors.toSet())
            )
            .build();
        this.userRepository.save(user);
    }
}
