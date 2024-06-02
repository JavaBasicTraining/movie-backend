package com.example.movie_backend.services.interfaces;

import com.example.movie_backend.entity.User;
import com.example.movie_backend.model.user.RegisterRequest;

import java.util.List;

public interface IUserService {
    User create (User e);

    List<User> getList ();

    void register(RegisterRequest request);
}
