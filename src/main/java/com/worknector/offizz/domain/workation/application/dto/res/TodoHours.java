package com.worknector.offizz.domain.workation.application.dto.res;

import java.time.LocalTime;

public record TodoHours(
        String name,
        LocalTime startTime,
        LocalTime endTime
) {
}
