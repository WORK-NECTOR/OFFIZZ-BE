package com.worknector.offizz.domain.work.domain.repository;

import com.worknector.offizz.domain.work.domain.entity.Cafe;

import java.util.List;

public interface CafeDslRepository {
    List<Cafe> findAllPagingBySearchOrLocation(String search, double lat, double lon);
}
