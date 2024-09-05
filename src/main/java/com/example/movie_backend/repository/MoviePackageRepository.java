package com.example.movie_backend.repository;

import com.example.movie_backend.entity.MoviePackage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MoviePackageRepository extends JpaRepository<MoviePackage, Long> {

}
