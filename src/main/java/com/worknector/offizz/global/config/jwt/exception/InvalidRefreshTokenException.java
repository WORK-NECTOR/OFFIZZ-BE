package com.worknector.offizz.global.config.jwt.exception;

import com.worknector.offizz.global.exception.ApplicationException;

import static com.worknector.offizz.global.config.jwt.constant.JwtResponseCode.INVALID_REFRESH_JWT;

public class InvalidRefreshTokenException extends ApplicationException {
    public InvalidRefreshTokenException() {
        super(INVALID_REFRESH_JWT.getCode(), INVALID_REFRESH_JWT.getMessage());
    }
}
