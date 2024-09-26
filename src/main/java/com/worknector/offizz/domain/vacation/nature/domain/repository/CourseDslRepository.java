package com.worknector.offizz.domain.vacation.nature.domain.repository;

import com.worknector.offizz.domain.likes.domain.entity.Likes;
import com.worknector.offizz.domain.vacation.nature.domain.entity.Course;

import java.util.List;

public interface CourseDslRepository {
    List<Course> findAllCourseBySearch(String search, double lat, double lon);

    List<Course> findAllCourseId(List<Likes> likes);
}
