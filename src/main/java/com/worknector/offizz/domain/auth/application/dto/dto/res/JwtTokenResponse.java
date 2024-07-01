package com.worknector.offizz.domain.auth.application.dto.dto.res;

public record JwtTokenResponse(String accessToken, int accessExpiration,
                               String refreshToken, int refreshExpiration) implements AuthResponse{}
