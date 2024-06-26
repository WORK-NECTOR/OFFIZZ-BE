package com.worknector.offizz.global.config.jwt;

import com.worknector.offizz.global.config.jwt.constant.Type;
import com.worknector.offizz.global.redis.RedisRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtProvider {
    private final RedisRepository redisRepository;

    @Value("${jwt.secret-key}")
    private String secret;
    @Value("${jwt.refreshExpiration}")
    private int refreshExpiration;
    @Value("${jwt.accessExpiration}")
    private int accessExpiration;

    private static final String TYPE = "type";
    private static final String ROLE = "role";
    private static final String USER = "user";

    public String generateAccessToken(Long id) {
        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));

        Instant accessDate = LocalDateTime.now().plusSeconds(accessExpiration).atZone(ZoneId.systemDefault()).toInstant();
        return Jwts.builder()
                .claim(ROLE, USER)
                .claim(TYPE, Type.ACCESS)
                .setSubject(String.valueOf(id))
                .setExpiration(Date.from(accessDate))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public String generateRefreshToken(Long id) {
        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));

        Instant refreshDate = LocalDateTime.now().plusSeconds(refreshExpiration).atZone(ZoneId.systemDefault()).toInstant();
        String refreshToken = Jwts.builder()
                .claim(ROLE, USER)
                .claim(TYPE, Type.REFRESH)
                .setSubject(String.valueOf(id))
                .setExpiration(Date.from(refreshDate))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
        redisRepository.setValues(Type.REFRESH.toString() + id, refreshToken, Duration.ofSeconds(refreshExpiration));
        return refreshToken;
    }
}
