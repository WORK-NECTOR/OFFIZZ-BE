package com.worknector.offizz.domain.workation.application.mapper;

import com.worknector.offizz.domain.workation.domain.entity.*;

public class WorkationKeywordMapper {

    private WorkationKeywordMapper() {
        throw new IllegalArgumentException();
    }

    public static WorkationVacationKeyword mapToWorkationVacationKeyword(Workation workation, VacationKeyword vacationKeyword) {
        return WorkationVacationKeyword.builder()
                .workation(workation)
                .keyword(vacationKeyword)
                .build();
    }

    public static WorkationWorkKeyword mapToWorkationWorkKeyword(Workation workation, WorkKeyword workKeyword) {
        return WorkationWorkKeyword.builder()
                .workation(workation)
                .keyword(workKeyword)
                .build();
    }
}
