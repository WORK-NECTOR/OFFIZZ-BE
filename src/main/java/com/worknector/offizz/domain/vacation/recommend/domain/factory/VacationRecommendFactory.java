package com.worknector.offizz.domain.vacation.recommend.domain.factory;

import com.worknector.offizz.domain.vacation.recommend.domain.strategy.VacationRecommendStrategy;
import com.worknector.offizz.domain.vacation.recommend.presentation.constant.Filter;

public interface VacationRecommendFactory {
    VacationRecommendStrategy getRecommendationStrategy(Filter filter);
}