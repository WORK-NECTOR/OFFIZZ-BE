package com.worknector.offizz.domain.auth.exception;

import com.worknector.offizz.global.exception.ApplicationException;

import static com.worknector.offizz.domain.auth.presentation.constant.AuthResponseCode.AUTH_DENIED;
import static com.worknector.offizz.domain.auth.presentation.constant.AuthResponseMessage.PERMISSION_DENIED;

public class PermissionDeniedException extends ApplicationException {

    public PermissionDeniedException() {
        super(PERMISSION_DENIED.getMessage(), AUTH_DENIED.getCode());
    }
}
