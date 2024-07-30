package com.worknector.offizz.domain.office.domain.repository;

import com.worknector.offizz.domain.office.domain.entity.Office;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OfficeDslRepository {
    List<Office> findRecommendByRegion(String region);

    Page<Office> findAllPagingByRegion(String region, Pageable pageable);
}
