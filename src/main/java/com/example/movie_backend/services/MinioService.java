package com.example.movie_backend.services;

import com.example.movie_backend.model.file.UploadRequest;
import com.example.movie_backend.model.file.UploadResponse;
import com.example.movie_backend.services.interfaces.IMinioService;
import io.minio.*;
import io.minio.errors.*;
import io.minio.http.Method;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@RequiredArgsConstructor
public class MinioService implements IMinioService {
    private static final String DEFAULT_BUCKET = "movie";

    private final MinioClient minioClient;

    @PostConstruct
    public void makeBucketIfAbsent() throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        BucketExistsArgs bucketExistsArgs = BucketExistsArgs.builder()
            .bucket(DEFAULT_BUCKET)
            .build();

        boolean isExisted = this.minioClient.bucketExists(bucketExistsArgs);

        if (!isExisted) {
            MakeBucketArgs makeBucketArgs = MakeBucketArgs.builder()
                .bucket(MinioService.DEFAULT_BUCKET)
                .build();
            this.minioClient.makeBucket(makeBucketArgs);
        }
    }

    @Override
    public UploadResponse upload(UploadRequest request) {
        try {
            PutObjectArgs putObjectArgs = PutObjectArgs.builder()
                .object(request.getFile().getOriginalFilename())
                .bucket(DEFAULT_BUCKET)
                .stream(request.getFile().getInputStream(), request.getFile().getSize(), -1)
                .build();

            ObjectWriteResponse response = this.minioClient.putObject(putObjectArgs);
            return UploadResponse.builder()
                .object(response.object())
                .build();
        } catch (ErrorResponseException | InsufficientDataException | InternalException | InvalidKeyException |
                 InvalidResponseException | IOException | NoSuchAlgorithmException | ServerException |
                 XmlParserException e) {
            log.debug("Upload fail on {}, Message: {}", request.getFile().getOriginalFilename(), e.getMessage());
            throw new RuntimeException("Upload fail");
        }
    }

    @Override
    public String getPreSignedLink(String object) {
        try {
            GetPresignedObjectUrlArgs getPresignedObjectUrlArgs = GetPresignedObjectUrlArgs.builder()
                .object(object)
                .expiry(2, TimeUnit.HOURS)
                .bucket(DEFAULT_BUCKET)
                .method(Method.GET)
                .build();
            return this.minioClient.getPresignedObjectUrl(getPresignedObjectUrlArgs);
        } catch (ErrorResponseException | InsufficientDataException | InternalException | InvalidKeyException |
                 InvalidResponseException | IOException | NoSuchAlgorithmException | ServerException |
                 XmlParserException e) {
            throw new RuntimeException("Upload fail");
        }
    }

    @Override
    public byte[] getObjectBytes(String object) {
        try {
            GetObjectArgs getObjectArgs = GetObjectArgs.builder()
                .bucket(DEFAULT_BUCKET)
                .object(object)
                .build();
            GetObjectResponse response = this.minioClient.getObject(getObjectArgs);
            return response.readAllBytes();
        } catch (ErrorResponseException | InsufficientDataException | InternalException | InvalidKeyException |
                 InvalidResponseException | IOException | NoSuchAlgorithmException | ServerException |
                 XmlParserException e) {
            throw new RuntimeException("Upload fail");
        }
    }

}
