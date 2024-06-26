package com.worknector.offizz.domain.auth.presentation.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AuthResponseCode {
    AUTH_DENIED("EX900", "권한이 없습니다."),
    AUTH_INVALID_KAKAO("EX901", "카카오 정보가 유효하지 않습니다."),
    AUTH_KAKAO_CODE("EX902", "카카오 코드가 유효하지 않습니다."),
    AUTH_FAILED("EX903", "사용자 인증에 실패했습니다."),
    PROVIDER_NOT_FOUND("EX904", "PROVIDER가 없습니다.");

    private final String code;
    private final String message;
}
