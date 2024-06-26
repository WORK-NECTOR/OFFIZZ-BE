package com.worknector.offizz.domain.auth.exception;

import com.worknector.offizz.global.exception.ApplicationException;

import static com.worknector.offizz.domain.auth.presentation.constant.AuthResponseCode.AUTH_INVALID_KAKAO;


public class KakaoException extends ApplicationException {
    public KakaoException() {
        super(AUTH_INVALID_KAKAO.getMessage(), AUTH_INVALID_KAKAO.getCode());
    }
}
