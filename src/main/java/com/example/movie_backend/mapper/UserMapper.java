package com.example.movie_backend.mapper;

import com.example.movie_backend.dto.AuthorityDTO;
import com.example.movie_backend.dto.user.UserDTO;
import com.example.movie_backend.entity.Authority;
import com.example.movie_backend.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "passwordHash", ignore = true)
    @Mapping(target = "likeComments", ignore = true)
    @Mapping(target = "evaluations", ignore = true)
    @Mapping(target = "comments", ignore = true)
    @Mapping(source = "userName", target = "username")
    @Mapping(source = "authorities", target = "authorities", qualifiedByName = "toAuthoritySet")
    User toEntity(UserDTO dto);

    @Mapping(source = "username", target = "userName")
    @Mapping(source = "authorities", target = "authorities", qualifiedByName = "toAuthorityDTOList")
    UserDTO toDTO(User entity);

    @Named("toAuthoritySet")
    static Set<Authority> mapToAuthoritySet(List<AuthorityDTO> list) {
        if (list == null) return null;
        return list.stream()
            .map(dto -> Authority.builder().name(dto.getName()).build())
            .collect(Collectors.toSet());
    }

    @Named("toAuthorityDTOList")
    static List<AuthorityDTO> mapToAuthorityDTOList(Set<Authority> set) {
        if (set == null) return null;
        return set.stream()
            .map(entity -> AuthorityDTO.builder().name(entity.getName()).build())
            .collect(Collectors.toList());
    }
}
