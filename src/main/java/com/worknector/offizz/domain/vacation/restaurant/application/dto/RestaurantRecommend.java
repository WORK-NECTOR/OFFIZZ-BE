package com.worknector.offizz.domain.vacation.restaurant.application.dto;

public record RestaurantRecommend(

        Long restaurantId,
        String addr1,
        double lon,
        double lat,
        String title,
        String firstimage,
        String firstimage2
) {
}
