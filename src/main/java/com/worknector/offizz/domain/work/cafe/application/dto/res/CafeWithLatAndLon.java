package com.worknector.offizz.domain.work.cafe.application.dto.res;

public record CafeWithLatAndLon(
        long cafeId,
        String name,
        String address,
        double lat,
        double lon,
        String image
) {}
