package com.worknector.offizz.domain.auth.exception;

import com.worknector.offizz.global.exception.ApplicationException;

import static com.worknector.offizz.domain.auth.presentation.constant.AuthResponseCode.AUTH_KAKAO_CODE;
import static com.worknector.offizz.domain.auth.presentation.constant.AuthResponseMessage.KAKAO_CODE;

public class KakaoCodeException extends ApplicationException {
    public KakaoCodeException() {
        super(KAKAO_CODE.getMessage(), AUTH_KAKAO_CODE.getCode());
    }
}
