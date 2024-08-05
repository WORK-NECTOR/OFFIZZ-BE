package com.worknector.offizz.domain.course.domain.repository;

import com.worknector.offizz.domain.course.domain.entity.Course;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {

  Optional<Course> findByCrsIdxAndRouteIdx(String crsIdx, String routeIdx);
}