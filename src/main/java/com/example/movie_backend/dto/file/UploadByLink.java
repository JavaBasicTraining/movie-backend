package com.example.movie_backend.dto.file;


import com.example.movie_backend.enumerate.MediaType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

@Getter
@Builder(toBuilder = true)
@AllArgsConstructor
public class UploadByLink {
    @NotNull
    private String link;

    @NotNull
    @Builder.Default
    private MediaType type = MediaType.FILE;
}