package com.worknector.offizz.domain.vacation.recommend.application.dto.res;

public record VacationRecommendResponse(
        Long natureId,
        String addr,
        double lon,
        double lat,
        String title,
        String firstImage,
        String secondImage
) {
}
