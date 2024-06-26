package com.worknector.offizz.domain.auth.exception;

import com.worknector.offizz.global.exception.ApplicationException;

import static com.worknector.offizz.domain.auth.presentation.constant.AuthResponseCode.AUTH_DENIED;

public class PermissionDeniedException extends ApplicationException {

    public PermissionDeniedException() {
        super(AUTH_DENIED.getMessage(), AUTH_DENIED.getCode());
    }
}
