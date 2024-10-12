package com.worknector.offizz.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum GlobalExceptionCode {

    // COMMON
    INVALID_REQUEST("EX1000", "올바르지 않은 요청입니다."),
    INTERNAL_SERVER_ERROR("EX1001", "서버 에러가 발생하였습니다. 관리자에게 문의해 주세요."),
    OBJECT_NOT_FOUND("EX1002", "데이터가 존재하지 않습니다.")
    ;

    private final String code;
    private final String message;
}
