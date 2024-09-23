package com.worknector.offizz.domain.workation.application.dto.res;

import java.time.LocalTime;
import java.util.List;

public record WorkHours(
        List<TodoHours> todoHours,
        LocalTime startCoreTime,
        LocalTime endCoreTime
) {
}
