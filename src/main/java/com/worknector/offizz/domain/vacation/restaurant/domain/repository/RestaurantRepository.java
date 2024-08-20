package com.worknector.offizz.domain.vacation.restaurant.domain.repository;

import com.worknector.offizz.domain.office.domain.entity.Office;
import com.worknector.offizz.domain.vacation.restaurant.domain.entity.Restaurant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long>, RestaurantDslRepository {
    Optional<Restaurant> findByContentidAndTitle(String contentid, String title);
}