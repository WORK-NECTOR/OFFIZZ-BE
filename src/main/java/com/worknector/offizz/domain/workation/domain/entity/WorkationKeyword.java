package com.worknector.offizz.domain.workation.domain.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum WorkationKeyword {

    HEALING("힐링"),
    FOOD("맛집"),
    CULTURE_ART("문화/예술"),
    SHOPPING("쇼핑"),
    EVENT_FESTIVAL("공연/행사/축제"),
    ;

    private final String description;

    public static WorkationKeyword from(String keywordName) {
        if (keywordName == null) {
            return null;
        }

        return Arrays.stream(WorkationKeyword.values())
                .filter(type -> type.name().equalsIgnoreCase(keywordName))
                .findFirst()
                .orElse(null);
    }
}