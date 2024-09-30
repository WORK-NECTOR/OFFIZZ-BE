package com.worknector.offizz.domain.vacation.restaurant.domain.service;

import com.worknector.offizz.domain.vacation.recommend.application.projection.VacationRecommendProjection;
import com.worknector.offizz.domain.vacation.restaurant.domain.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantGetService {
    private final RestaurantRepository restaurantRepository;

    public List<VacationRecommendProjection> getAllRestaurantBySearch(String search, double lat, double lon, Long userId) {
        return restaurantRepository.getAllRestaurantBySearch(search, lat, lon, userId);
    }
}
