package com.worknector.offizz.domain.auth.exception;

import com.worknector.offizz.global.exception.ApplicationException;

import static com.worknector.offizz.domain.auth.presentation.constant.AuthResponseCode.AUTH_INVALID_KAKAO;
import static com.worknector.offizz.domain.auth.presentation.constant.AuthResponseMessage.KAKAO_INVALID;


public class KakaoException extends ApplicationException {
    public KakaoException() {
        super(KAKAO_INVALID.getMessage(), AUTH_INVALID_KAKAO.getCode());
    }
}
