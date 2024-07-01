package com.worknector.offizz.domain.auth.application.mapper;

import com.worknector.offizz.domain.auth.application.dto.dto.res.AuthUserResponse;
import com.worknector.offizz.domain.user.domain.entity.User;

public class AuthMapper {
    private AuthMapper() {
        throw new IllegalArgumentException();
    }
    public static AuthUserResponse mapToAuthUser(User user, Long socialId) {
        return new AuthUserResponse(user, socialId);
    }

    public static AuthUserResponse mapToAuthUser(Long socialId) {
        return new AuthUserResponse(socialId);
    }
}
