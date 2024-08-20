package com.worknector.offizz.domain.vacation.restaurant.application.mapper;

import com.worknector.offizz.domain.vacation.restaurant.application.dto.RestaurantRecommendList;
import com.worknector.offizz.domain.vacation.restaurant.domain.entity.Restaurant;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RestaurantRecommendMapper {

    public static RestaurantRecommendList mapToRestaurantRecommend(Restaurant restaurant) {
        return new RestaurantRecommendList(
                restaurant.getRestaurantId(),
                restaurant.getAddr1(),
                restaurant.getLon(),
                restaurant.getLat(),
                restaurant.getTel(),
                restaurant.getTitle(),
                restaurant.getFirstimage(),
                restaurant.getFirstimage2(),
                restaurant.getHit()
        );
    }
}
