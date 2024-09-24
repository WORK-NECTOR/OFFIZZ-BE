package com.worknector.offizz.domain.vacation.recommend.domain.factory;

import com.worknector.offizz.domain.vacation.recommend.domain.strategy.*;
import com.worknector.offizz.domain.vacation.recommend.exception.InvalidVacationFilterException;
import com.worknector.offizz.domain.vacation.recommend.presentation.constant.Filter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class VacationRecommendFactoryImpl implements VacationRecommendFactory {
    private final NatureRecommendStrategy natureRecommendStrategy;
    private final RestaurantRecommendStrategy restaurantRecommendStrategy;
    private final CultureRecommendStrategy cultureRecommendStrategy;
    private final ShoppingRecommendStrategy shoppingRecommendStrategy;

    @Override
    public VacationRecommendStrategy getRecommendationStrategy(Filter filter) {
        switch (filter) {
            case nature:
                return natureRecommendStrategy;
            case restaurant:
                return restaurantRecommendStrategy;
            case culture:
                return cultureRecommendStrategy;
            case shopping:
                return shoppingRecommendStrategy;
            case all:
                return new CompositeVacationRecommendStrategy(
                        Arrays.asList(
                                natureRecommendStrategy,
                                restaurantRecommendStrategy,
                                cultureRecommendStrategy,
                                shoppingRecommendStrategy
                        )
                );
            default:
                throw new InvalidVacationFilterException();
        }
    }
}