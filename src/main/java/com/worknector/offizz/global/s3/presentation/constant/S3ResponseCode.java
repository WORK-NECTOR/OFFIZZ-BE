package com.worknector.offizz.global.s3.presentation.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum S3ResponseCode {
    EMPTY_MULTIPART_FILE("EX300", "파일이 입력되지 않았습니다."),
    EMPTY_EXTENSION("EX301", "확장자가 입력되지 않았습니다."),
    INVALID_EXTENSION("EX302", "유효하지 않은 확장자입니다."),
    S3_UPLOAD_FAILED("EX303", "S3 업로드에 실패했습니다."),
    ;

    private final String code;
    private final String message;
}
