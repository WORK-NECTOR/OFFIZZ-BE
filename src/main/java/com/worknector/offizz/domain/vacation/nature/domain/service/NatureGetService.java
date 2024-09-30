package com.worknector.offizz.domain.vacation.nature.domain.service;

import com.worknector.offizz.domain.vacation.nature.domain.repository.NatureRepository;
import com.worknector.offizz.domain.vacation.recommend.application.projection.VacationRecommendProjection;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class NatureGetService {
    private final NatureRepository natureRepository;

    public List<VacationRecommendProjection> getAllNatureBySearch(String search, double lat, double lon, Long userId) {
        return natureRepository.findAllNatureBySearch(search, lat, lon, userId);
    }
}
