package com.example.movie_backend.services.interfaces;

import com.example.movie_backend.dto.user.UserDTO;
import com.example.movie_backend.entity.User;
import com.example.movie_backend.model.user.RegisterRequest;

import java.util.List;
import java.util.Set;

public interface IUserService {
    User create(User user);

    List<User> getList();

    UserDTO getUser(String userName);

    void register(RegisterRequest request);


    Set<User> getUserAuthority();

}
