package com.worknector.offizz.domain.vacation.restaurant.application.usecase;

import com.worknector.offizz.domain.vacation.restaurant.application.dto.PagingRestaurantRecommendList;
import com.worknector.offizz.domain.vacation.restaurant.application.dto.RestaurantRecommendList;
import com.worknector.offizz.domain.vacation.restaurant.application.mapper.RestaurantRecommendMapper;
import com.worknector.offizz.domain.vacation.restaurant.domain.entity.Restaurant;
import com.worknector.offizz.domain.vacation.restaurant.domain.service.RestaurantGetService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class RestaurantRecommendUseCase {
    private final RestaurantGetService restaurantGetService;

    public PagingRestaurantRecommendList searchRecommendRestaurant(String search, int page, int size, double lat, double lon) {
        Page<Restaurant> restaurants = restaurantGetService.getAllRestaurantBySearch(search, page, size, lat, lon);
        List<RestaurantRecommendList> recOffices = restaurants.stream()
                .map(RestaurantRecommendMapper::mapToRestaurantRecommend)
                .toList();

        return new PagingRestaurantRecommendList(recOffices, restaurants.getTotalPages());
    }
}
