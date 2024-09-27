package com.worknector.offizz.domain.workation.application.dto.res;

import com.worknector.offizz.domain.workation.domain.entity.ConditionType;

import java.util.List;

public record RetrospectResponse(
        int actualWorkHours,
        int actualWorkMinutes,
        int completedVacation,
        List<RecordVacation> vacationRecords,
        ConditionType workCondition,
        ConditionType vacationCondition,
        String dailyY,
        String dailyW,
        String dailyT
) {
}
