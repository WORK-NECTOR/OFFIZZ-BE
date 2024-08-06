package com.worknector.offizz.domain.nature.domain.repository;

import com.worknector.offizz.domain.nature.domain.entity.Course;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long>, CourseDslRepository {

  Optional<Course> findByCrsIdxAndRouteIdx(String crsIdx, String routeIdx);
}
