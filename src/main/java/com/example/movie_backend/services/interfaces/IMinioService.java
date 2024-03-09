package com.example.movie_backend.services.interfaces;


import com.example.movie_backend.model.file.UploadRequest;
import com.example.movie_backend.model.file.UploadResponse;

public interface IMinioService {
    UploadResponse upload(UploadRequest request);

    String getPreSignedLink(String object);
    
    byte[] getObjectBytes(String object);
}
