package com.worknector.offizz.domain.auth.application.usecase;

import com.worknector.offizz.domain.auth.application.dto.dto.res.JwtTokenResponse;
import com.worknector.offizz.domain.user.domain.entity.User;
import com.worknector.offizz.global.config.jwt.JwtProvider;
import com.worknector.offizz.global.config.jwt.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class JwtUseCase {
    @Value("${jwt.refreshExpiration}")
    private int refreshExpiration;
    @Value("${jwt.accessExpiration}")
    private int accessExpiration;
    private final JwtUtils jwtUtils;
    private final JwtProvider jwtProvider;

    public JwtTokenResponse signIn(User user) {
        return tokenResponse(user);
    }

    public void logout(User user) {
        jwtUtils.makeExpired(user.getUserId());
    }

    public JwtTokenResponse regenerateToken(User user, HttpServletRequest request) {
        jwtUtils.checkRedis(user.getUserId(), request);
        return tokenResponse(user);
    }

    private JwtTokenResponse tokenResponse(User user) {
        return generateToken(user);
    }

    private JwtTokenResponse generateToken(User user) {
        String accessToken = jwtProvider.generateAccessToken(user.getUserId());
        String refreshToken = jwtProvider.generateRefreshToken(user.getUserId());
        return new JwtTokenResponse(accessToken, accessExpiration, refreshToken, refreshExpiration);
    }
}