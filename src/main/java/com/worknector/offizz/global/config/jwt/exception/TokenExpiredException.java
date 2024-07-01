package com.worknector.offizz.global.config.jwt.exception;

import com.worknector.offizz.global.exception.ApplicationException;

import static com.worknector.offizz.global.config.jwt.constant.JwtResponseCode.EXPIRED_JWT;

public class TokenExpiredException extends ApplicationException {
    public TokenExpiredException() {
        super(EXPIRED_JWT.getCode(), EXPIRED_JWT.getMessage());
    }
}
