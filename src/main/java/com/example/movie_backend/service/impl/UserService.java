package com.example.movie_backend.service.impl;

import com.example.movie_backend.dto.user.UserDTO;
import com.example.movie_backend.mapper.UserMapper;
import com.example.movie_backend.entity.Authority;
import com.example.movie_backend.entity.User;
import com.example.movie_backend.repository.UserRepository;
import com.example.movie_backend.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService implements IUserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserDTO create(UserDTO userDTO) {
        User user = userMapper.toEntity(userDTO);
        User savedUser = userRepository.save(user);
        return userMapper.toDTO(savedUser);
    }

    @Override
    public List<User> getList() {
        return userRepository.findAll();
    }

    @Override
    public UserDTO getUser(String username) {
        return this.userRepository.findByUsername(username)
                .map(this.userMapper::toDTO)
                .orElse(null);
    }

    @Override
    @SuppressWarnings("unchecked")
    public UserDTO getUserFromJwt(Jwt jwt) {
        String username = jwt.getClaimAsString("preferred_username");
        Map<String, Object> resourceAccess = jwt.getClaim("resource_access");
        Map<String, Object> clientAccess = (Map<String, Object>) resourceAccess.get("movie_website_client");
        List<String> roles = (List<String>) clientAccess.get("roles");

        User user = userRepository.findByUsername(username).orElse(null);
        if (user != null) {
            user.setAuthorities(
                    roles.stream().map(Authority::new).collect(Collectors.toSet())
            );
        } else {
            user = new User();
            user.setUsername(username);
            user.setAuthorities(roles.stream().map(Authority::new).collect(Collectors.toSet()));
            user.setFirstName(jwt.getClaimAsString("family_name"));
            user.setLastName(jwt.getClaimAsString("given_name"));
            user = this.userRepository.save(user);
        }

        return new UserDTO(user);
    }
}
