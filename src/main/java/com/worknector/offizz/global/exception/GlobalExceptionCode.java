package com.worknector.offizz.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum GlobalExceptionCode implements ExceptionCode {

    // COMMON
    INVALID_REQUEST(1001, "올바르지 않은 요청입니다."),
    INTERNAL_SEVER_ERROR(1002, "서버 에러가 발생하였습니다. 관리자에게 문의해 주세요."),

    // IMAGE
    EXCEED_IMAGE_CAPACITY(2001, "업로드 가능한 이미지 용량을 초과했습니다."),
    NULL_IMAGE(2002, "업로드한 이미지 파일이 NULL입니다."),
    ;

    private final int code;
    private final String message;
}
