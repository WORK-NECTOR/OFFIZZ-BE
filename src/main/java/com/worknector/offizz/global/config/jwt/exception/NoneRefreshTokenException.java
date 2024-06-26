package com.worknector.offizz.global.config.jwt.exception;

import com.worknector.offizz.global.exception.ApplicationException;

import static com.worknector.offizz.global.config.jwt.constant.JwtResponseCode.NOT_FOUND_REFRESH;

public class NoneRefreshTokenException extends ApplicationException {
    public NoneRefreshTokenException() {
        super(NOT_FOUND_REFRESH.getCode(), NOT_FOUND_REFRESH.getMessage());
    }
}
