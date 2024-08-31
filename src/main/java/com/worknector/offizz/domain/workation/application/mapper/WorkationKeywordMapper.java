package com.worknector.offizz.domain.workation.application.mapper;

import com.worknector.offizz.domain.workation.domain.entity.VacationKeyword;
import com.worknector.offizz.domain.workation.domain.entity.Workation;
import com.worknector.offizz.domain.workation.domain.entity.WorkationVacationKeyword;

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
}
