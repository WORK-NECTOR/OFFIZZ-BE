package com.worknector.offizz.domain.vacation.nature.domain.service;

import com.worknector.offizz.domain.vacation.nature.domain.repository.CourseRepository;
import com.worknector.offizz.domain.vacation.recommend.application.projection.VacationRecommendProjection;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CourseGetService {
    private final CourseRepository courseRepository;

    public List<VacationRecommendProjection> getAllCourseBySearch(String search, double lat, double lon, Long userId) {
        return courseRepository.findAllCourseBySearch(search, lat, lon, userId);
    }
}
