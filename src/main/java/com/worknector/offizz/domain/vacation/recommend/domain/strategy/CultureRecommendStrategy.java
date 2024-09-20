package com.worknector.offizz.domain.vacation.recommend.domain.strategy;

import com.worknector.offizz.domain.vacation.culture.domain.entity.Culture;
import com.worknector.offizz.domain.vacation.culture.domain.service.CultureGetService;
import com.worknector.offizz.domain.vacation.recommend.application.dto.res.VacationRecommendResponse;
import com.worknector.offizz.domain.vacation.recommend.application.mapper.VacationRecommendMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CultureRecommendStrategy implements VacationRecommendStrategy {

    private final CultureGetService cultureGetService;

    /**
     * 문화 추천 리스트를 반환하는 Strategy
     *
     * @param search 검색어
     * @param lat    유저의 현 위도
     * @param lon    유저의 현 경도
     * @return 주어진 검색어와 위치를 기반으로 문화 추천 목록을 {@link VacationRecommendResponse} 형식으로 반환
     */
    @Override
    public List<VacationRecommendResponse> recommend(String search, double lat, double lon) {
        List<Culture> cultures = cultureGetService.getAllCultureBySearch(search, lat, lon);

        return cultures.stream()
                .map(VacationRecommendMapper::fromCulture)
                .toList();
    }
}
