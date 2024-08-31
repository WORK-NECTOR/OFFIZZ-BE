package com.worknector.offizz.domain.work.application.dto.res;

import com.worknector.offizz.domain.work.domain.entity.Facilities;
import com.worknector.offizz.domain.work.domain.entity.OperatingTime;

public record OfficeDetailResponse(
        Facilities facilities,
        String capacity,
        OperatingTime operatingTime
) {
}
