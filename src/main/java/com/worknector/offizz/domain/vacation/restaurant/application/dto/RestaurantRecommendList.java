package com.worknector.offizz.domain.vacation.restaurant.application.dto;

public record RestaurantRecommendList(

        Long restaurantId,
        String addr1,
        double lon,
        double lat,
        String tel,
        String title,
        String firstimage,
        String firstimage2,
        long hit
) {
}
