package com.worknector.offizz.domain.vacation.nature.domain.repository;

import com.worknector.offizz.domain.likes.domain.entity.Likes;
import com.worknector.offizz.domain.vacation.nature.domain.entity.Nature;
import com.worknector.offizz.domain.vacation.recommend.application.projection.VacationRecommendProjection;

import java.util.List;

public interface NatureDslRepository {
    List<VacationRecommendProjection> findAllNatureBySearch(String search, double lat, double lon, Long userId);

    List<Nature> findAllNatureId(List<Likes> likes);
}
