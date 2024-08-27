package com.worknector.offizz.domain.work.cafe.domain.service;

import com.worknector.offizz.domain.work.cafe.domain.entity.Cafe;
import com.worknector.offizz.domain.work.cafe.domain.repository.CafeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CafeGetService {
    private final CafeRepository cafeRepository;

    public Page<Cafe> allSearchOrLocationPage(String search, int page, int size, double lat, double lon) {
        Pageable pageable = PageRequest.of(page-1, size);
        return cafeRepository.findAllPagingBySearchOrLocation(search, pageable, lat, lon);
    }
}
