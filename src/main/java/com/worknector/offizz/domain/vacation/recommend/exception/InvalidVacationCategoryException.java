package com.worknector.offizz.domain.vacation.recommend.exception;

import com.worknector.offizz.global.exception.ApplicationException;

import static com.worknector.offizz.domain.vacation.recommend.presentation.constant.VacationResponseCode.INVALID_VACATION_CATEGORY;

public class InvalidVacationCategoryException extends ApplicationException {

    public InvalidVacationCategoryException() {
        super(INVALID_VACATION_CATEGORY.getCode(), INVALID_VACATION_CATEGORY.getMessage());
    }
}
