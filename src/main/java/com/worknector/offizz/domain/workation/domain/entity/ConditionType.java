package com.worknector.offizz.domain.workation.domain.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ConditionType {

    GREAT("최고였어!"),
    GOOD("좋았어"),
    OKAY("괜찮았어"),
    BAD("별로였어"),
    HARD("힘들었어"),
    ;

    private final String description;
}
