package com.worknector.offizz.global.config.jwt.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum JwtResponseCode {
    EXPIRED_JWT("EX200", "만료된 토큰입니다."),
    INVALID_JWT("EX201", "잘못된 토큰입니다."),
    INVALID_REFRESH_JWT("EX202", "해당 리프레시 토큰이 존재하지 않습니다."),
    NOT_FOUND_REFRESH("EX203", "해당 토큰이 없습니다.");

    private final String code;
    private final String message;
}
