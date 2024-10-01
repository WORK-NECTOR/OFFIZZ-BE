package com.worknector.offizz.domain.vacation.nature.domain.repository;

import com.worknector.offizz.domain.likes.domain.entity.Likes;
import com.worknector.offizz.domain.vacation.nature.domain.entity.Course;
import com.worknector.offizz.domain.vacation.recommend.application.projection.VacationRecommendProjection;

import java.util.List;

public interface CourseDslRepository {
    List<VacationRecommendProjection> findAllCourseBySearch(String search, double lat, double lon, Long userId);

    List<Course> findAllCourseId(List<Likes> likes);
}
