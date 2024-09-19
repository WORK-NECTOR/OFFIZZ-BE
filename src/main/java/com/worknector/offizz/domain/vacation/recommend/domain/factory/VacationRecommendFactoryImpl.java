package com.worknector.offizz.domain.vacation.recommend.domain.factory;

import com.worknector.offizz.domain.vacation.recommend.domain.strategy.CompositeVacationRecommendStrategy;
import com.worknector.offizz.domain.vacation.recommend.domain.strategy.RestaurantRecommendStrategy;
import com.worknector.offizz.domain.vacation.recommend.domain.strategy.VacationRecommendStrategy;
import com.worknector.offizz.domain.vacation.recommend.domain.strategy.NatureRecommendStrategy;
import com.worknector.offizz.domain.vacation.recommend.exception.InvalidVacationFilterException;
import com.worknector.offizz.domain.vacation.recommend.presentation.constant.Filter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class VacationRecommendFactoryImpl implements VacationRecommendFactory {
    private final NatureRecommendStrategy natureRecommendationStrategy;
    private final RestaurantRecommendStrategy restaurantRecommendationStrategy;

    @Override
    public VacationRecommendStrategy getRecommendationStrategy(Filter filter) {
        switch (filter) {
            case nature:
                return natureRecommendationStrategy;
            case restaurant:
                return restaurantRecommendationStrategy;
            case all:
                return new CompositeVacationRecommendStrategy(
                        Arrays.asList(
                                natureRecommendationStrategy,
                                restaurantRecommendationStrategy
                        )
                );
            default:
                throw new InvalidVacationFilterException();
        }
    }
}