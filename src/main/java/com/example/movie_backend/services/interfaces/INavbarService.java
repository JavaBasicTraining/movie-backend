package com.example.movie_backend.services.interfaces;

import com.example.movie_backend.dto.navbar.NavbarDTO;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface INavbarService {
    NavbarDTO create (NavbarDTO entity);
    NavbarDTO update (NavbarDTO entity, Long id);
    NavbarDTO getById (Long id);
    Boolean delete (Long id);
    List<NavbarDTO> getList ( );


}
