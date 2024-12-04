package com.example.movie_backend.dto.file;

import com.example.movie_backend.enumerate.MediaType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

@Getter
@Setter
@Builder(toBuilder = true)
@AllArgsConstructor
public class UploadByFile implements Serializable {

    @NotNull
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private transient MultipartFile file;

    @Builder.Default
    @NotNull
    private MediaType type = MediaType.FILE;
}