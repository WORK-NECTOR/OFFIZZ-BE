package com.worknector.offizz.domain.vacation.restaurant.domain.repository;

import com.worknector.offizz.domain.likes.domain.entity.Likes;
import com.worknector.offizz.domain.vacation.recommend.application.projection.VacationRecommendProjection;
import com.worknector.offizz.domain.vacation.restaurant.domain.entity.Restaurant;

import java.util.List;

public interface RestaurantDslRepository {
    List<VacationRecommendProjection> getAllRestaurantBySearch(String search, double lat, double lon, Long userId);
    List<Restaurant> findAllRestaurantById(List<Likes> likes);
}
