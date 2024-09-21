package com.worknector.offizz.domain.workation.application.mapper;

import com.worknector.offizz.domain.workation.domain.entity.Daily;
import com.worknector.offizz.domain.workation.domain.entity.Workation;

public class DailyMapper {
    public static Daily mapToDaily(Workation workation, int day) {
        return Daily.builder()
                .workation(workation)
                .day(day+1)
                .date(workation.getStartDate().plusDays(day))
                .build();
    }
}
