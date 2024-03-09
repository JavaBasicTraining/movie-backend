package com.example.movie_backend.controller;

import com.example.movie_backend.controller.interfaces.IFileController;
import com.example.movie_backend.model.file.GetFileUrlRequest;
import com.example.movie_backend.model.file.GetFileUrlResponse;
import com.example.movie_backend.model.file.UploadRequest;
import com.example.movie_backend.model.file.UploadResponse;
import com.example.movie_backend.services.interfaces.IMinioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class FileController implements IFileController {

    private final IMinioService minioService;

    @Override
    public ResponseEntity<UploadResponse> upload(UploadRequest request) {
        return ResponseEntity.ok(this.minioService.upload(request));
    }

    @Override
    public ResponseEntity<GetFileUrlResponse> getUrl(GetFileUrlRequest request) {
        String preSignedUrl = minioService.getPreSignedLink(request.getObject());
        return ResponseEntity.ok(
            GetFileUrlResponse.builder()
                .url(preSignedUrl)
                .build()
        );
    }
}
