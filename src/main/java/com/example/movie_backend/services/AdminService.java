package com.example.movie_backend.services;

import com.example.movie_backend.entity.Admin;
import com.example.movie_backend.repository.AdminRepository;
import com.example.movie_backend.services.interfaces.IAdminService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AdminService implements IAdminService {

    private final AdminRepository adminRepository;

    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    public Admin create(Admin e) {
        return adminRepository.save(e);
    }

    @Override
    public List<Admin> getList() {
        return adminRepository.findAll();
    }
}
