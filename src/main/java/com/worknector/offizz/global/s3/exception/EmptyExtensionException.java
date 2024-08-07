package com.worknector.offizz.global.s3.exception;

import com.worknector.offizz.global.exception.ApplicationException;

import static com.worknector.offizz.global.s3.presentation.constant.S3ResponseCode.EMPTY_EXTENSION;

public class EmptyExtensionException extends ApplicationException {
    public EmptyExtensionException() {
        super(EMPTY_EXTENSION.getCode(), EMPTY_EXTENSION.getMessage());
    }
}
