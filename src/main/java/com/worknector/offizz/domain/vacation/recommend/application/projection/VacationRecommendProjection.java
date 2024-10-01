package com.worknector.offizz.domain.vacation.recommend.application.projection;

public record VacationRecommendProjection(
        String category,
        Long objectPk,
        String addr,
        double lon,
        double lat,
        String title,
        String firstImage,
        boolean isLike
) {
}
