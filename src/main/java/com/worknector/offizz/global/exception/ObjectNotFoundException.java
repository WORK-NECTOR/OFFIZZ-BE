package com.worknector.offizz.global.exception;

import static com.worknector.offizz.global.exception.GlobalExceptionCode.OBJECT_NOT_FOUND;

public class ObjectNotFoundException extends ApplicationException {
    public ObjectNotFoundException() {
        super(OBJECT_NOT_FOUND.getCode(), OBJECT_NOT_FOUND.getMessage());
    }
}
