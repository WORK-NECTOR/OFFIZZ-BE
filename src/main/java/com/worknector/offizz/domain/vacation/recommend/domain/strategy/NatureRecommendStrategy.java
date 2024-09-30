package com.worknector.offizz.domain.vacation.recommend.domain.strategy;

import com.worknector.offizz.domain.vacation.nature.domain.service.CourseGetService;
import com.worknector.offizz.domain.vacation.nature.domain.service.NatureGetService;
import com.worknector.offizz.domain.vacation.recommend.application.dto.res.VacationRecommendResponse;
import com.worknector.offizz.domain.vacation.recommend.application.mapper.VacationRecommendMapper;
import com.worknector.offizz.domain.vacation.recommend.application.projection.VacationRecommendProjection;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class NatureRecommendStrategy implements VacationRecommendStrategy {

    private final NatureGetService natureGetService;
    private final CourseGetService courseGetService;

    /**
     * 자연 추천 리스트를 반환하는 Strategy
     * @param search 검색어
     * @param lat 유저의 현 위도
     * @param lon 유저의 현 경도
     * @return 주어진 검색어와 위치를 기반으로 자연 추천 목록을 {@link VacationRecommendResponse} 형식으로 반환
     */
    @Override
    public List<VacationRecommendResponse> recommend(String search, double lat, double lon, Long userId) {
        // Nature 과 Course 에 대한 검색 목록 조회
        List<VacationRecommendProjection> natures = natureGetService.getAllNatureBySearch(search, lat, lon, userId);
        List<VacationRecommendProjection> courses = courseGetService.getAllCourseBySearch(search, lat, lon, userId);

        // natures 와 courses 를 하나의 공통 API Response 로 매핑
        List<VacationRecommendResponse> recommendNatures = new ArrayList<>();
        recommendNatures.addAll(natures.stream().map(VacationRecommendMapper::fromVacationRecommendProjection).toList());
        recommendNatures.addAll(courses.stream().map(VacationRecommendMapper::fromVacationRecommendProjection).toList());

        return recommendNatures;
    }
}
