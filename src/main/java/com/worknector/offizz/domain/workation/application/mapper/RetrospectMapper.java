package com.worknector.offizz.domain.workation.application.mapper;

import com.worknector.offizz.domain.workation.application.dto.res.RecordVacation;
import com.worknector.offizz.domain.workation.application.dto.res.RetrospectResponse;
import com.worknector.offizz.domain.workation.domain.entity.Daily;

import java.util.List;

public class RetrospectMapper {

    private RetrospectMapper() {
        throw new IllegalArgumentException();
    }

    public static RetrospectResponse mapToRetrospectResponse(
            int actualWorkHours,
            int actualWorkMinutes,
            int completedVacation,
            List<RecordVacation> vacationRecords,
            Daily daily
    ) {
        return new RetrospectResponse(
                actualWorkHours,
                actualWorkMinutes,
                completedVacation,
                vacationRecords,
                daily.getWorkCondition(),
                daily.getVacationCondition(),
                daily.getDailyY(),
                daily.getDailyW(),
                daily.getDailyT()
        );
    }
}
