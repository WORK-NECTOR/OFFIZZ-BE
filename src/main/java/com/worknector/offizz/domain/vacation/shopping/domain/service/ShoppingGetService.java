package com.worknector.offizz.domain.vacation.shopping.domain.service;

import com.worknector.offizz.domain.vacation.recommend.application.projection.VacationRecommendProjection;
import com.worknector.offizz.domain.vacation.shopping.domain.repository.ShoppingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ShoppingGetService {
    private final ShoppingRepository shoppingRepository;

    public List<VacationRecommendProjection> getAllShoppingBySearch(String search, double lat, double lon,  Long userId) {
        return shoppingRepository.getAllShoppingBySearch(search, lat, lon, userId);
    }
}
