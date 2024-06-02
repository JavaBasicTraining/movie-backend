package com.example.movie_backend.services.interfaces;

import com.example.movie_backend.entity.Admin;
import com.example.movie_backend.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;
public interface IAdminService {
    Admin create (Admin e);

    List<Admin> getList ();
}
