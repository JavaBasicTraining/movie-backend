package com.example.movie_backend.controller.interfaces;

import com.example.movie_backend.model.file.GetFileUrlRequest;
import com.example.movie_backend.model.file.GetFileUrlResponse;
import com.example.movie_backend.model.file.UploadRequest;
import com.example.movie_backend.model.file.UploadResponse;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/api/v1/files")
public interface IFileController {

    @PostMapping(value = "upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResponseEntity<UploadResponse> upload(@ModelAttribute @Valid UploadRequest request);

    @GetMapping("url")
    ResponseEntity<GetFileUrlResponse> getUrl(@ParameterObject GetFileUrlRequest request);
}
