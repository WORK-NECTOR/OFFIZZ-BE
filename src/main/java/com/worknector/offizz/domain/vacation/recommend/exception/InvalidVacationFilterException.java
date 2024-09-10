package com.worknector.offizz.domain.vacation.recommend.exception;

import com.worknector.offizz.global.exception.ApplicationException;

import static com.worknector.offizz.domain.vacation.recommend.presentation.constant.VacationResponseCode.INVALID_VACATION_FILTER;

public class InvalidVacationFilterException extends ApplicationException {

    public InvalidVacationFilterException() {
        super(INVALID_VACATION_FILTER.getCode(), INVALID_VACATION_FILTER.getMessage());
    }
}
