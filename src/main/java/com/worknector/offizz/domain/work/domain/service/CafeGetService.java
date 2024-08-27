package com.worknector.offizz.domain.work.domain.service;

import com.worknector.offizz.domain.work.domain.entity.Cafe;
import com.worknector.offizz.domain.work.domain.repository.CafeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CafeGetService {
    private final CafeRepository cafeRepository;

    public List<Cafe> allSearchOrLocationPage(String search, double lat, double lon) {
        return cafeRepository.findAllPagingBySearchOrLocation(search, lat, lon);
    }
}
