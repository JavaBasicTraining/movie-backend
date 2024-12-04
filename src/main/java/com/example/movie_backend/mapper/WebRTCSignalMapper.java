package com.example.movie_backend.mapper;

import com.example.movie_backend.socket_controller.dto.request.WebRTCSignalRequest;
import com.example.movie_backend.socket_controller.dto.response.WebRTCSignalResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WebRTCSignalMapper {
    WebRTCSignalResponse toResponse(WebRTCSignalRequest request);
}
