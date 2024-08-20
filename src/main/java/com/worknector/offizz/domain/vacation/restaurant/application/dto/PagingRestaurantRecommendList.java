package com.worknector.offizz.domain.vacation.restaurant.application.dto;

import java.util.List;

public record PagingRestaurantRecommendList(

        List<RestaurantRecommendList> restaurantRecommendList,
        int totalPage
) {
}
