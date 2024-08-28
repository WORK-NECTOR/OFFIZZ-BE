package com.worknector.offizz.domain.workation.application.mapper;

import com.worknector.offizz.domain.workation.domain.entity.Keyword;
import com.worknector.offizz.domain.workation.domain.entity.Workation;
import com.worknector.offizz.domain.workation.domain.entity.WorkationKeyword;

public class WorkationKeywordMapper {

    private WorkationKeywordMapper() {
        throw new IllegalArgumentException();
    }

    public static WorkationKeyword mapToWorkationKeyword(Workation workation, Keyword keyword) {
        return WorkationKeyword.builder()
                .workation(workation)
                .keyword(keyword)
                .build();
    }
}
