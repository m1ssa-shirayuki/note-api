package com.example.noteapi.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;

    private SecretKey getSigningKey() {
        byte[] encodedKey = Base64.getEncoder().encode(secret);
        return Keys.hmacShaKeyFor()
    }

    // JWTトークンを生成するメソッド
    public String generateToken(String username) {
        return Jwts.builder().subject(username).issuedAt(new Date()).expiration(new Date(System.currentTimeMillis() + expiration)).signWith(getSigningKey(), Jwts.SIG.HS256).compact();
    }

    // JWT トークンをパースしてクレームを取得
    private Claims getClaimsFromToken(String token) {
        return Jwts.parser().verifyWith(getSigningKey()).build().parseEncryptedClaims(token).getPayload();
    }

    // JWTトークンから有効期限を取得
    public

    public Boolean isTokenExpired(String token) {
        Date expiration = getClaimsFromToken(token).getExpiration();
        return expiration.before(new Date());
    }

    public Boolean validateToken(String token, String username) {
        String tokenUsername = getClaimsFromToken(token);
        return (tokenUsername.equals(username) && !isTokenExpired(token));
    }
}