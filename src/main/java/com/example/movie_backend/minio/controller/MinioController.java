package com.example.movie_backend.minio.controller;

import com.example.movie_backend.minio.entity.FileInfo;
import com.example.movie_backend.minio.entity.UploadByFile;
import com.example.movie_backend.minio.entity.UploadByLink;
import com.example.movie_backend.minio.service.IMinioService;
import com.example.movie_backend.services.RateLimiterService;
import com.example.movie_backend.services.VideoTokenService;
import io.minio.GetObjectArgs;
import io.minio.MinioClient;
import io.minio.StatObjectArgs;
import io.minio.StatObjectResponse;
import io.minio.errors.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MinioController implements IMinioController {
    public final IMinioService iMinioService;
    private final MinioClient minioClient;
    private final VideoTokenService tokenService;
    private final RateLimiterService rateLimiterService;

    private static final String BUCKET_NAME = "movie";
    private static final long CHUNK_SIZE = 1024 * 1024; // 1MB

    @Override
    public ResponseEntity<List<FileInfo>> getList() {
        return ResponseEntity.ok(iMinioService.getList());
    }

    @Override
    public ResponseEntity<FileInfo> uploadByLink(UploadByLink request) {
        try {
            return ResponseEntity.ok(
                    iMinioService.uploadByLink(
                            request.getLink(),
                            request.getType().getPath()));
        } catch (IOException | ServerException | InsufficientDataException | ErrorResponseException
                | NoSuchAlgorithmException | InvalidKeyException | InvalidResponseException | XmlParserException
                | InternalException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ResponseEntity<FileInfo> uploadByFile(UploadByFile request) {
        try {
            return ResponseEntity.ok(
                    iMinioService.uploadByFile(
                            request.getFile(),
                            request.getType().getPath()));
        } catch (IOException | ServerException | InsufficientDataException | ErrorResponseException
                | NoSuchAlgorithmException | InvalidKeyException | InvalidResponseException | XmlParserException
                | InternalException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ResponseEntity<String> getPreSignedLink(String object) {
        return ResponseEntity.ok(iMinioService.getPreSignedLink(object));
    }

    @GetMapping("/video/token")
    public ResponseEntity<Map<String, String>> getVideoToken(@RequestParam String fileName) {
        try {
            String token = tokenService.generateToken(fileName);
            return ResponseEntity.ok(Map.of("token", token));
        } catch (Exception e) {
            log.error("Error generating video token: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Stream video content with support for range requests and security measures
     * 
     * @param fileName    Name of the video file to stream
     * @param token       Security token for video access
     * @param rangeHeader HTTP Range header for partial content requests
     * @param request     HTTP request object for client IP
     * @return ResponseEntity containing the video stream
     */
    @GetMapping("video/stream")
    public ResponseEntity<StreamingResponseBody> streamVideo(
            @RequestParam String fileName,
            @RequestParam String token,
            @RequestHeader(value = "Range", required = false) String rangeHeader,
            HttpServletRequest request) {

        // Validate security token to ensure authorized access
        if (!tokenService.validateToken(token, fileName)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        // Check rate limiting based on client IP to prevent abuse
        String clientIp = getClientIp(request);
        if (!rateLimiterService.allowRequest(clientIp)) {
            return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).build();
        }

        try {
            // Get video file metadata from MinIO
            StatObjectResponse stat = minioClient.statObject(
                    StatObjectArgs.builder()
                            .bucket(BUCKET_NAME)
                            .object(fileName)
                            .build());

            // Calculate content range for partial content streaming
            long fileSize = stat.size();
            long rangeStart = 0;
            long rangeEnd = fileSize - 1;

            // Parse range header if present (supports video seeking)
            if (StringUtils.hasText(rangeHeader)) {
                String[] ranges = rangeHeader.substring("bytes=".length()).split("-");
                rangeStart = Long.parseLong(ranges[0]);
                if (ranges.length > 1 && !ranges[1].isEmpty()) {
                    rangeEnd = Long.parseLong(ranges[1]);
                } else {
                    // If end range is not specified, stream a fixed chunk size
                    rangeEnd = Math.min(rangeStart + CHUNK_SIZE - 1, fileSize - 1);
                }
            }

            long contentLength = rangeEnd - rangeStart + 1;

            // Configure MinIO object retrieval for the specified range
            GetObjectArgs getObjectArgs = GetObjectArgs.builder()
                    .bucket(BUCKET_NAME)
                    .object(fileName)
                    .offset(rangeStart)
                    .length(contentLength)
                    .build();

            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", stat.contentType());
            headers.add("Accept-Ranges", "bytes");
            headers.add("Content-Length", String.valueOf(contentLength));
            headers.add("Content-Range", String.format("bytes %d-%d/%d", rangeStart, rangeEnd, fileSize));
            // Prevent caching
            headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
            headers.add("Pragma", "no-cache");
            headers.add("Expires", "0");

            StreamingResponseBody responseStream = outputStream -> {
                try (InputStream inputStream = minioClient.getObject(getObjectArgs)) {
                    byte[] buffer = new byte[8192];
                    int bytesRead;
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                        outputStream.flush();
                    }
                } catch (ServerException | InsufficientDataException | ErrorResponseException | NoSuchAlgorithmException
                        | InvalidKeyException | InvalidResponseException | XmlParserException | InternalException e) {
                    throw new RuntimeException(e);
                }
            };

            return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT)
                    .headers(headers)
                    .body(responseStream);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Retrieves the actual client IP address by checking various HTTP headers
     * This method handles requests that may come through proxies or load balancers
     * 
     * @param request The HttpServletRequest object containing request information
     * @return The client's IP address as a String
     */
    public String getClientIp(HttpServletRequest request) {
        // Check X-Forwarded-For header first (standard header for identifying
        // originating IP)
        // Format: client, proxy1, proxy2, ...
        String ip = request.getHeader("X-Forwarded-For");

        // If X-Forwarded-For is not available, try Proxy-Client-IP
        // Used by some proxy servers
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }

        // Try WebLogic proxy header if previous attempts failed
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }

        // Check HTTP_CLIENT_IP header
        // Less common but sometimes used
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }

        // Try alternative forwarded header format
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }

        // If all proxy headers failed, get the direct remote address
        // This will be the actual connecting IP if no proxy is involved
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }

        return ip;
    }
}