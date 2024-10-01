package com.worknector.offizz.domain.vacation.recommend.presentation.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum VacationResponseCode {

    INVALID_VACATION_FILTER("EX400", "유효하지 않은 VACATION 필터입니다."),
    INVALID_VACATION_CATEGORY("EX401", "유효하지 않은 VACATION 카테고리입니다."),
    ;

    private final String code;
    private final String message;
}
