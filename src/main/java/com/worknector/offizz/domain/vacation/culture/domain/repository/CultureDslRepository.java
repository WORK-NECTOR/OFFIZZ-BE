package com.worknector.offizz.domain.vacation.culture.domain.repository;

import com.worknector.offizz.domain.likes.domain.entity.Likes;
import com.worknector.offizz.domain.vacation.culture.domain.entity.Culture;
import com.worknector.offizz.domain.vacation.recommend.application.projection.VacationRecommendProjection;

import java.util.List;

public interface CultureDslRepository {
    List<VacationRecommendProjection> getAllCultureBySearch(String search, double lat, double lon, Long userId);
    List<Culture> findAllCourseId(List<Likes> likes);
}
