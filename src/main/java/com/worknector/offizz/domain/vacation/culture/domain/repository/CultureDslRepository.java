package com.worknector.offizz.domain.vacation.culture.domain.repository;

import com.worknector.offizz.domain.likes.domain.entity.Likes;
import com.worknector.offizz.domain.vacation.culture.domain.entity.Culture;

import java.util.List;

public interface CultureDslRepository {
    List<Culture> getAllCultureBySearch(String search, double lat, double lon);
    List<Culture> findAllCourseId(List<Likes> likes);
}
