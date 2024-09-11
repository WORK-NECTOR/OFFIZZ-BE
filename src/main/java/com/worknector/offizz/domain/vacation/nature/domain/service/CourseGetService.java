package com.worknector.offizz.domain.vacation.nature.domain.service;

import com.worknector.offizz.domain.vacation.nature.domain.entity.Course;
import com.worknector.offizz.domain.vacation.nature.domain.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CourseGetService {
    private final CourseRepository courseRepository;

    public List<Course> getAllCourseBySearch(String search, double lat, double lon) {
        return courseRepository.findAllCourseBySearch(search, lat, lon);
    }
}
