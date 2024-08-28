package com.worknector.offizz.domain.workation.domain.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum Keyword {

    NATURE("자연"),
    RESTAURANT("맛집"),
    CULTURE("문화/예술"),
    SHOPPING("쇼핑"),
    ;

    private final String description;

    public static Keyword from(String keywordName) {
        if (keywordName == null) {
            return null;
        }

        return Arrays.stream(Keyword.values())
                .filter(type -> type.name().equalsIgnoreCase(keywordName))
                .findFirst()
                .orElse(null);
    }
}