package com.worknector.offizz.global.s3.exception;

import com.worknector.offizz.global.exception.ApplicationException;

import static com.worknector.offizz.global.s3.presentation.constant.S3ResponseCode.INVALID_EXTENSION;

public class InvalidExtensionException extends ApplicationException {
    public InvalidExtensionException() {
        super(INVALID_EXTENSION.getCode(), INVALID_EXTENSION.getMessage());
    }
}
