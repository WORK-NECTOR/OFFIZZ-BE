package com.worknector.offizz.database.course.presentation;

import com.worknector.offizz.database.course.application.usecase.CourseOpenDataUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class CourseTestApiController {

  private final CourseOpenDataUseCase courseOpenDataUseCase;

  @GetMapping("/test/course")
  public void updateCourseData(
  ) {
    courseOpenDataUseCase.updateCourseData();
  }
}
