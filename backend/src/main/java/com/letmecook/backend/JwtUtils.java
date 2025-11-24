package com.letmecook.backend;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Base64;

@Component
public class JwtUtils {

    @Value("${jwt.secret:}") // Expect Base64-encoded secret; if empty we generate one (not ideal for prod)
    private String jwtSecret;

    @Value("${jwt.expirationMs:86400000}") // 24h default
    private int jwtExpirationMs;

    private SecretKey secretKey;

    @PostConstruct
    public void init() {
        try {
            if (jwtSecret != null && !jwtSecret.isEmpty()) {
                // Accept plain text or base64: prefer base64 for binary size
                // We'll try to decode; if it fails, treat as raw bytes of UTF-8
                byte[] keyBytes;
                try {
                    keyBytes = Decoders.BASE64.decode(jwtSecret);
                } catch (Exception ex) {
                    keyBytes = jwtSecret.getBytes(java.nio.charset.StandardCharsets.UTF_8);
                }

                if (keyBytes.length < 32) { // require >= 256 bits for HS256
                    System.err.println(
                            "Configured JWT secret is too short (must be >= 256 bits). Falling back to generated key.");
                    secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
                } else {
                    secretKey = Keys.hmacShaKeyFor(keyBytes);
                }
            } else {
                System.out.println(
                        "No JWT secret provided. Generating a secure random key (tokens will NOT survive restart).");
                secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
            }
        } catch (Exception e) {
            System.err.println("Failed to initialize JWT key: " + e.getMessage());
            secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
        }
    }

    public String generateJwtToken(org.springframework.security.core.Authentication authentication) {
        Object principal = authentication.getPrincipal();
        String username;
        if (principal instanceof org.springframework.security.core.userdetails.UserDetails) {
            username = ((org.springframework.security.core.userdetails.UserDetails) principal).getUsername();
        } else {
            username = String.valueOf(principal);
        }

        Date now = new Date();
        Date expiry = new Date(now.getTime() + jwtExpirationMs);

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(expiry)
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
            return false;
        }
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
