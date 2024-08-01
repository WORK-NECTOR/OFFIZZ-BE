package com.worknector.offizz.domain.office.domain.repository;

import com.worknector.offizz.domain.office.application.dto.req.Region;
import com.worknector.offizz.domain.office.domain.entity.Office;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OfficeDslRepository {
    List<Office> findRecommendByRegion(Region region, int size);

    Page<Office> findAllPagingByRegion(Region region, Pageable pageable);

    Page<Office> findAllPagingBySearch(String search, Pageable pageable);
}
