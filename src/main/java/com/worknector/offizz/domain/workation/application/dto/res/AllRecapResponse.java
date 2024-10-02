package com.worknector.offizz.domain.workation.application.dto.res;

import java.time.LocalDate;

public record AllRecapResponse(
        long workationId,
        String name,
        String address,
        LocalDate startDate,
        LocalDate endDate
) {
}
