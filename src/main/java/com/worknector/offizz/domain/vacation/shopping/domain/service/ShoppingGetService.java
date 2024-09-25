package com.worknector.offizz.domain.vacation.shopping.domain.service;

import com.worknector.offizz.domain.vacation.shopping.domain.entity.Shopping;
import com.worknector.offizz.domain.vacation.shopping.domain.repository.ShoppingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ShoppingGetService {
    private final ShoppingRepository shoppingRepository;

    public List<Shopping> getAllShoppingBySearch(String search, double lat, double lon) {
        return shoppingRepository.getAllShoppingBySearch(search, lat, lon);
    }
}
