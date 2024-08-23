package com.worknector.offizz.domain.vacation.restaurant.application.mapper;

import com.worknector.offizz.domain.vacation.restaurant.application.dto.RestaurantRecommend;
import com.worknector.offizz.domain.vacation.restaurant.domain.entity.Restaurant;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RestaurantRecommendMapper {

    public static RestaurantRecommend mapToRestaurantRecommend(Restaurant restaurant) {
        return new RestaurantRecommend(
                restaurant.getRestaurantId(),
                restaurant.getAddr1(),
                restaurant.getLon(),
                restaurant.getLat(),
                restaurant.getTitle(),
                restaurant.getFirstimage(),
                restaurant.getFirstimage2()
        );
    }
}
