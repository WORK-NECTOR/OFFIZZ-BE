package com.worknector.offizz.domain.user.presentation.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum UserResponseCode {
    USER_NOT_FOUND("EX300", "등록된 사용자가 없습니다."),
    USER_DELETED("EX301", "탈퇴한 사용자 입니다.");

    private final String code;
    private final String message;
}
