package com.worknector.offizz.domain.auth.exception;

import com.worknector.offizz.global.exception.ApplicationException;

import static com.worknector.offizz.domain.auth.presentation.constant.AuthResponseCode.PROVIDER_NOT_FOUND;
import static com.worknector.offizz.domain.auth.presentation.constant.AuthResponseMessage.NOT_FOUND_PROVIDER;

public class OauthException extends ApplicationException {
    public OauthException() {
        super(NOT_FOUND_PROVIDER.getMessage(), PROVIDER_NOT_FOUND.getCode());
    }
}
