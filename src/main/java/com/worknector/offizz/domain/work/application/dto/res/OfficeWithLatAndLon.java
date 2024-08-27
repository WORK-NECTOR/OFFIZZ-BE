package com.worknector.offizz.domain.work.application.dto.res;

public record OfficeWithLatAndLon(
        long officeId,
        String name,
        String address,
        double lat,
        double lon
) {
}
