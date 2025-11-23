package com.letmecook.backend;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
@AllArgsConstructor
@NoArgsConstructor
public class JwtUtils {

    @Value("${jwt.secret:}") // optional, fallback to empty
    private String jwtSecret;

    @Value("${jwt.expirationMs:86400000}") // default 24h
    private int jwtExpirationMs;

    private SecretKey secretKey;

    @PostConstruct
    public void init() {
        try {
            if (jwtSecret != null && !jwtSecret.isEmpty()) {
                // Decode Base64 key from properties
                byte[] keyBytes = Decoders.BASE64.decode(jwtSecret);
                if (keyBytes.length < 32) { // less than 256 bits
                    System.err.println("JWT secret too short! Generating a secure random key.");
                    secretKey = Jwts.SIG.HS256.key().build();
                } else {
                    secretKey = Keys.hmacShaKeyFor(keyBytes);
                }
            } else {
                // No secret provided, generate a strong random key
                System.out.println("No JWT secret provided. Generating a secure random key.");
                secretKey = Jwts.SIG.HS256.key().build();
            }
        } catch (Exception e) {
            System.err.println("Failed to initialize JWT key: " + e.getMessage());
            // fallback to safe random key
            secretKey = Jwts.SIG.HS256.key().build();
        }
    }

    public String generateJwtToken(org.springframework.security.core.Authentication authentication) {
        org.springframework.security.core.userdetails.UserDetails userPrincipal = (org.springframework.security.core.userdetails.UserDetails) authentication
                .getPrincipal();

        return Jwts.builder()
                .setSubject(userPrincipal.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean validateJwtToken(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (JwtException e) {
            System.err.println("Invalid JWT token: " + e.getMessage());
        }
        return false;
    }

    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public Date getExpirationDateFromJwtToken(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getExpiration();
    }
}
