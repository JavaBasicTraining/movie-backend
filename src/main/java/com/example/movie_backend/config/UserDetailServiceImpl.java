package com.example.movie_backend.config;

import com.example.movie_backend.entity.Authority;
import com.example.movie_backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository
                .findByUsername(username)
                .map(user -> createSpringSecurityUser(username, user))
                .orElseThrow(() -> new UsernameNotFoundException("User " + username + " was not found in the database"));
    }

    private User createSpringSecurityUser(String lowercaseLogin, com.example.movie_backend.entity.User user) {
        List<SimpleGrantedAuthority> grantedAuthorities = user
            .getAuthorities()
            .stream()
            .map(Authority::getName)
            .map(SimpleGrantedAuthority::new)
            .collect(Collectors.toList());
        return new User(user.getUsername(), user.getPasswordHash(), grantedAuthorities);
    }
}


