package com.worknector.offizz.domain.vacation.nature.application.usecase;

import com.worknector.offizz.domain.vacation.nature.application.dto.res.RecommendNaturePagingResponse;
import com.worknector.offizz.domain.vacation.nature.application.dto.res.RecommendNature;
import com.worknector.offizz.domain.vacation.nature.application.mapper.RecommendNatureMapper;
import com.worknector.offizz.domain.vacation.nature.domain.entity.Course;
import com.worknector.offizz.domain.vacation.nature.domain.entity.Nature;
import com.worknector.offizz.domain.vacation.nature.domain.service.CourseGetService;
import com.worknector.offizz.domain.vacation.nature.domain.service.NatureGetService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class RecommendNatureUseCase {
    private final NatureGetService natureGetService;
    private final CourseGetService courseGetService;

    /**
     * VACATION > RECOMMEND [자연] 검색에 대한 전체목록을 반환한다.
     * @param search 검색 키워드
     * @param page 현재 페이지
     * @param size 한 페이지 당 데이터 수
     * @return 자연(Nature)과 산책로(Course)에 대한 페이지네이션 결과 목록
     */
    public RecommendNaturePagingResponse searchRecommendNature(String search, int page, int size, double lat, double lon) {
        // Nature 과 Course 에 대한 검색 목록 조회
        List<Nature> natures = natureGetService.getAllNatureBySearch(search, lat, lon);
        List<Course> courses = courseGetService.getAllCourseBySearch(search, lat, lon);

        // natures 와 courses 를 하나의 공통 API Response 로 매핑
        List<RecommendNature> recommendNatures = new ArrayList<>();
        recommendNatures.addAll(natures.stream().map(RecommendNatureMapper::fromNature).toList());
        recommendNatures.addAll(courses.stream().map(RecommendNatureMapper::fromCourse).toList());

        // 조회수 순으로 정렬
        recommendNatures.sort((response1, response2) -> {
            long hit1 = (response1.natures() != null) ? response1.natures().hit() : response1.courses().hit();
            long hit2 = (response2.natures() != null) ? response2.natures().hit() : response2.courses().hit();
            return Long.compare(hit2, hit1);
        });

        // 페이지네이션
        Pageable pageable = PageRequest.of(page, size);
        int start = Math.min((int) pageable.getOffset(), recommendNatures.size());
        int end = Math.min((start + pageable.getPageSize()), recommendNatures.size());
        List<RecommendNature> pagedRecommendNatures = recommendNatures.subList(start, end);

        Page<RecommendNature> recommendNaturePage = new PageImpl<>(pagedRecommendNatures, pageable, recommendNatures.size());

        return new RecommendNaturePagingResponse(recommendNaturePage.getContent(), recommendNaturePage.getTotalPages());
    }
}
