package com.worknector.offizz.domain.auth.application.dto.dto.res;

import com.worknector.offizz.domain.user.domain.entity.User;

public record AuthUserResponse(User user, Long socialId) implements AuthResponse{
    public AuthUserResponse(Long socialId) {
        this(null, socialId);
    }
}