package com.worknector.offizz.domain.vacation.shopping.domain.repository;

import com.worknector.offizz.domain.vacation.shopping.domain.entity.Shopping;

import java.util.List;

public interface ShoppingDslRepository {
    List<Shopping> getAllShoppingBySearch(String search, double lat, double lon);
}