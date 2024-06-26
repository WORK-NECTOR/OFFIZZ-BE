package com.worknector.offizz.global.config.jwt.exception;

import com.worknector.offizz.global.exception.ApplicationException;

import static com.worknector.offizz.global.config.jwt.constant.JwtResponseCode.INVALID_JWT;

public class InvalidTokenException extends ApplicationException {
    public InvalidTokenException() {
        super(INVALID_JWT.getCode(), INVALID_JWT.getMessage());
    }
}
