package com.worknector.offizz.domain.work.application.dto.res;

public record CafeWithLatAndLon(
        long cafeId,
        String name,
        String address,
        double lat,
        double lon,
        String image
) {}
