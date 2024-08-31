package com.worknector.offizz.domain.workation.domain.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum VacationKeyword {

    NATURE("자연"),
    RESTAURANT("맛집"),
    CULTURE("문화/예술"),
    SHOPPING("쇼핑"),
    ;

    private final String description;
}