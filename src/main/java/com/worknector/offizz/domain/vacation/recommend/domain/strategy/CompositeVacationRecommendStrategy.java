package com.worknector.offizz.domain.vacation.recommend.domain.strategy;

import com.worknector.offizz.domain.vacation.recommend.application.dto.res.VacationRecommendResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CompositeVacationRecommendStrategy implements VacationRecommendStrategy {
    private final List<VacationRecommendStrategy> strategies;

    @Override
    public List<VacationRecommendResponse> recommend(String search, double lat, double lon) {
        List<VacationRecommendResponse> combinedRecommendations = new ArrayList<>();

        for (VacationRecommendStrategy strategy : strategies) {
            combinedRecommendations.addAll(strategy.recommend(search, lat, lon));
        }

        return combinedRecommendations;
    }
}