package com.worknector.offizz.domain.vacation.culture.domain.service;

import com.worknector.offizz.domain.vacation.culture.domain.entity.Culture;
import com.worknector.offizz.domain.vacation.culture.domain.repository.CultureRepository;
import com.worknector.offizz.domain.vacation.recommend.application.projection.VacationRecommendProjection;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CultureGetService {
    private final CultureRepository cultureRepository;

    public List<VacationRecommendProjection> getAllCultureBySearch(String search, double lat, double lon, Long userId) {
        return cultureRepository.getAllCultureBySearch(search, lat, lon, userId);
    }

    public Culture findCultureById(long cultureId) {
        Culture culture = cultureRepository.findById(cultureId).orElseThrow();
        return culture;
    }
}
