package com.worknector.offizz.domain.vacation.restaurant.domain.service;

import com.worknector.offizz.domain.vacation.restaurant.domain.entity.Restaurant;
import com.worknector.offizz.domain.vacation.restaurant.domain.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RestaurantGetService {
    private final RestaurantRepository restaurantRepository;

    public Page<Restaurant> getAllRestaurantBySearch(String search, int page, int size, double lat, double lon) {
        Pageable pageable = PageRequest.of(page - 1, size);
        return restaurantRepository.getAllRestaurantBySearch(search, pageable, lat, lon);
    }
}
