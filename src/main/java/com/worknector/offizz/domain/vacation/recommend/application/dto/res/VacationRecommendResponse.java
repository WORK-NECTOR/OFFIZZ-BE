package com.worknector.offizz.domain.vacation.recommend.application.dto.res;

public record VacationRecommendResponse(
        String category,
        Long id,
        String address,
        double lon,
        double lat,
        String name,
        String image,
        boolean isLike
) {
}
