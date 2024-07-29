package com.worknector.offizz.global.s3.exception;

import com.worknector.offizz.global.exception.ApplicationException;

import static com.worknector.offizz.global.s3.presentation.constant.S3ResponseCode.EMPTY_MULTIPART_FILE;

public class EmptyMultipartFileException extends ApplicationException {
    public EmptyMultipartFileException() {
        super(EMPTY_MULTIPART_FILE.getMessage(), EMPTY_MULTIPART_FILE.getCode());
    }
}
