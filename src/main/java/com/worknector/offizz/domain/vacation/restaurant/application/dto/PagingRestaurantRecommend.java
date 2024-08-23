package com.worknector.offizz.domain.vacation.restaurant.application.dto;

import java.util.List;

public record PagingRestaurantRecommend(

        List<RestaurantRecommend> restaurantRecommend,
        int totalPage
) {
}
