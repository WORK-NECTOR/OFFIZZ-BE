package com.worknector.offizz.domain.vacation.restaurant.domain.repository;

import com.worknector.offizz.domain.vacation.restaurant.domain.entity.Restaurant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RestaurantDslRepository {
    Page<Restaurant> findAllPagingBySearch(String search, Pageable pageable, double lat, double lon);
}
