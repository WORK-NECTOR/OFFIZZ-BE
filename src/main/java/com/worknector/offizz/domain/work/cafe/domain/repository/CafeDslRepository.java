package com.worknector.offizz.domain.work.cafe.domain.repository;

import com.worknector.offizz.domain.work.cafe.domain.entity.Cafe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CafeDslRepository {
    Page<Cafe> findAllPagingBySearchOrLocation(String search, Pageable pageable, double lat, double lon);
}
