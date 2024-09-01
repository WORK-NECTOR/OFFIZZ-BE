package com.worknector.offizz.domain.workation.domain.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum WorkKeyword {

    OFFICE("공유오피스"),
    CAFE("카페"),
    LIBRARY("도서관"),
    STUDYCAFE("스터디카페"),
    STUDYROOM("스터디룸"),
    ;

    private final String description;
}