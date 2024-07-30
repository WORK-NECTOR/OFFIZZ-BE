package com.worknector.offizz.domain.office.application.dto.res;

import com.worknector.offizz.domain.office.domain.entity.Facilities;
import com.worknector.offizz.domain.office.domain.entity.OperatingTime;

public record OfficeDetailResponse(
        Facilities facilities,
        String capacity,
        OperatingTime operatingTime
) {
}
