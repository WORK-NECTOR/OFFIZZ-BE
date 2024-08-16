package com.worknector.offizz.domain.nature.domain.service;

import com.worknector.offizz.domain.nature.domain.entity.Course;
import com.worknector.offizz.domain.nature.domain.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CourseGetService {
    private final CourseRepository courseRepository;

    public List<Course> getAllCourseBySearch(String search) {
        return courseRepository.findAllCourseBySearch(search);
    }
}
