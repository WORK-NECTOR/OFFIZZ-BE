package com.worknector.offizz.domain.vacation.recommend.domain.strategy;

import com.worknector.offizz.domain.vacation.recommend.application.dto.res.VacationRecommendResponse;

import java.util.List;

public interface VacationRecommendStrategy {
    List<VacationRecommendResponse> recommend(String search, double lat, double lon, Long userId);
}