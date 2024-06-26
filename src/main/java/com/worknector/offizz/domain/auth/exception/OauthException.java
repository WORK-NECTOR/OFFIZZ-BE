package com.worknector.offizz.domain.auth.exception;

import com.worknector.offizz.global.exception.ApplicationException;

import static com.worknector.offizz.domain.auth.presentation.constant.AuthResponseCode.PROVIDER_NOT_FOUND;

public class OauthException extends ApplicationException {
    public OauthException() {
        super(PROVIDER_NOT_FOUND.getMessage(), PROVIDER_NOT_FOUND.getCode());
    }
}
