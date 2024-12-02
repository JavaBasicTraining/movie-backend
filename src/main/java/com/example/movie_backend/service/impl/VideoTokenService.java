package com.example.movie_backend.service.impl;

import com.example.movie_backend.controller.exception.ServerErrorException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class VideoTokenService {
    @Value("${video.token.secret}")
    private String secretKey;

    @Value("${video.token.validity}")
    private Long tokenValidity;

    public String generateToken(String fileName) {
        try {
            Date now = new Date();
            Date expirationDate = new Date(now.getTime() + tokenValidity);

            return Jwts.builder()
                    .setSubject(fileName)
                    .setIssuedAt(now)
                    .setExpiration(expirationDate)
                    .signWith(SignatureAlgorithm.HS256, secretKey.getBytes())
                    .compact();
        } catch (Exception e) {
            throw new ServerErrorException("Error generating token", e);
        }
    }

    public boolean validateToken(String token, String fileName) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(secretKey.getBytes())
                    .parseClaimsJws(token)
                    .getBody();

            return claims.getSubject().equals(fileName) &&
                    !claims.getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }
}
