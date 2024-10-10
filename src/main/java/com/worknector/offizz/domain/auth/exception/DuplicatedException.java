package com.worknector.offizz.domain.auth.exception;

import com.worknector.offizz.global.exception.ApplicationException;

import static com.worknector.offizz.domain.user.presentation.constant.UserResponseCode.USER_DUPLICATED;

public class DuplicatedException extends ApplicationException {
    public DuplicatedException() {
        super(USER_DUPLICATED.getMessage(), USER_DUPLICATED.getCode());
    }
}