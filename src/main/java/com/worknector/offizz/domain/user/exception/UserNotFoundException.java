package com.worknector.offizz.domain.user.exception;

import com.worknector.offizz.global.exception.ApplicationException;

import static com.worknector.offizz.domain.user.presentation.constant.UserResponseCode.USER_NOT_FOUND;

public class UserNotFoundException extends ApplicationException {
    public UserNotFoundException() {
        super(USER_NOT_FOUND.getCode(), USER_NOT_FOUND.getMessage());
    }
}
