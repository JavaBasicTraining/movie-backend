package com.example.movie_backend.services;

import com.example.movie_backend.entity.User;
import com.example.movie_backend.entity.enumerate.UserType;
import com.example.movie_backend.keycloak.IKeycloakService;
import com.example.movie_backend.model.user.LoginRequest;
import com.example.movie_backend.model.user.LoginResponse;
import com.example.movie_backend.model.user.RegisterRequest;
import com.example.movie_backend.model.user.RegisterResponse;
import com.example.movie_backend.repository.UserRepository;
import com.example.movie_backend.services.interfaces.IUserService;
import org.keycloak.representations.AccessTokenResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService extends BaseService<User, UUID> implements IUserService {
    private final IKeycloakService iKeycloakService;
    private final UserRepository userRepository;

    public UserService(JpaRepository<User, UUID> repository, IKeycloakService iKeycloakService, UserRepository userRepository) {
        super(repository);
        this.iKeycloakService = iKeycloakService;
        this.userRepository = userRepository;
    }

    @Override
    public LoginResponse login(LoginRequest request) {
        AccessTokenResponse accessTokenResponse = iKeycloakService.token(request.getUsername(), request.getPassword());
        return LoginResponse.builder()
                .token(accessTokenResponse.getToken())
                .expiresIn(accessTokenResponse.getExpiresIn())
                .refreshToken(accessTokenResponse.getRefreshToken())
                .refreshExpiresIn(accessTokenResponse.getRefreshExpiresIn())
                .sessionState(accessTokenResponse.getSessionState())
                .build();
    }

    @Override
    public RegisterResponse register(RegisterRequest request) {
        UUID userId = iKeycloakService.register(request);
        User user = User.builder()
                .id(userId)
                .firstName(request.getFistName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .phoneNumber(request.getPhoneNumber())
                .type(UserType.NORMAL)
                .build();

        userRepository.save(user);

        return RegisterResponse.builder()
                .userId(userId)
                .build();
    }

    @Override
    public List<User> getList() {
        return userRepository.findAll();
    }
}
