package com.worknector.offizz.domain.work.application.dto.res;

public record CafeAndOffice(
        String category,
        long id,
        String name,
        String address,
        double lat,
        double lon,
        String image,
        boolean isLike
) {
}
