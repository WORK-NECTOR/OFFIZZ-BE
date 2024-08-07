package com.worknector.offizz.global.s3.exception;

import com.worknector.offizz.global.exception.ApplicationException;

import static com.worknector.offizz.global.s3.presentation.constant.S3ResponseCode.S3_UPLOAD_FAILED;

public class S3UploadFailedException extends ApplicationException {
    public S3UploadFailedException() {
        super(S3_UPLOAD_FAILED.getCode(), S3_UPLOAD_FAILED.getMessage());
    }
}
