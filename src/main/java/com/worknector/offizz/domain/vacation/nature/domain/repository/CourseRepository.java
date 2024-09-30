package com.worknector.offizz.domain.vacation.nature.domain.repository;

import com.worknector.offizz.domain.vacation.nature.domain.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long>, CourseDslRepository {

  List<Course> findAllByCrsIdxAndCrsKorNm(String crsIdx, String crsKorNm);
}
