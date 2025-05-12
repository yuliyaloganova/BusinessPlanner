package com.businessplanner.security;

import com.businessplanner.models.User;
//import com.businessplanner.security.UserDetailsImpl;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.io.Decoders;
import org.springframework.beans.factory.annotation.Value;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
//import java.util.stream.Collectors;

import javax.crypto.SecretKey;

@Component
public class JwtTokenProvider {

    @Value("${app.jwt.secret}")
    private String jwtSecret;

    @Value("${app.jwt.expiration}")
    private int jwtExpirationInMs;

    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtSecret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    public String generateToken(
        Map<String, Object> extraClaims,
            UserDetails userDetails
    ) {
        UserDetailsImpl userDetailsImpl = (UserDetailsImpl) userDetails;
        User user = userDetailsImpl.getUser();
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(Long.toString(user.getId()))
                .claim("username", user.getEmail())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
                .signWith(getSigningKey(), SignatureAlgorithm.HS512)
                .compact();
        //убираем , потому что есть в сервисе
        /*UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        User user = userDetails.getUser();
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpirationInMs);

        return Jwts.builder()
                .setSubject(Long.toString(user.getId()))
                .claim("username", user.getEmail()) // Используем email как username
                .claim("roles", userDetails.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.toList()))
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(getSigningKey(), SignatureAlgorithm.HS512)
                .compact();
        */
    }

    public boolean validateToken(String token) {
        try {

            Jwts.parserBuilder()
            .setSigningKey(jwtSecret).build()
            .parseClaimsJws(token);
        return true;

            /*Jwts.parser()
                    .setSigningKey(jwtSecret)
                    .build()
                    .parseClaimsJws(token);
            return true;*/
        } catch (JwtException | IllegalArgumentException ex) {
            logJwtError(ex);
            return false;
        }
    }

    private void logJwtError(Exception ex) {
        String errorType = "JWT error";
        if (ex instanceof MalformedJwtException) {
            errorType = "Invalid JWT token";
        } else if (ex instanceof ExpiredJwtException) {
            errorType = "Expired JWT token";
        } else if (ex instanceof UnsupportedJwtException) {
            errorType = "Unsupported JWT token";
        }
        System.err.println(errorType + ": " + ex.getMessage());
    }

    public String getUsernameFromToken(String token) {
        return getClaims(token).get("username", String.class);
    }

    public Long getUserIdFromToken(String token) {
        return Long.parseLong(getClaims(token).getSubject());
    }

    /*public Claims getClaims(String token) {
        return Jwts.parser()
            .verifyWith(jwtSecret) // вместо setSigningKey()
            .build()
            .parseSignedClaims(token)    // вместо parseClaimsJws()
            .getPayload();               // вместо getBody()
    }*/
    private Claims getClaims(String token) {
        // 1. Декодируем Base64 секретный ключ
        byte[] keyBytes = Decoders.BASE64.decode(jwtSecret);
        
        // 2. Создаем SecretKey
        SecretKey key = Keys.hmacShaKeyFor(keyBytes);
        
        // 3. Парсим токен с верификацией
        return 
        
        Jwts.parserBuilder()
            .setSigningKey(key)  // Используем setSigningKey вместо verifyWith
            .build()             // В 0.11.5 build() обязателен
            .parseClaimsJws(token)  // parseClaimsJws вместо parseSignedClaims
            .getBody();           // getBody() вместо getPayload()

        /*Jwts.parser()
                .verifyWith(key)  // Передаем SecretKey, а не String
                .build()
                .parseSignedClaims(token)
                .getPayload();
                */
    }
}