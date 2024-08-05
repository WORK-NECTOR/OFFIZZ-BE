package com.worknector.offizz.openapi.course.application.usecase;

import com.worknector.offizz.openapi.course.application.dto.CourseResponse;
import com.worknector.offizz.openapi.course.application.dto.CourseResponse.Item;
import com.worknector.offizz.openapi.course.application.dto.CourseResponse.Items;
import com.worknector.offizz.openapi.course.application.mapper.CourseMapper;
import com.worknector.offizz.domain.course.domain.entity.Course;
import com.worknector.offizz.domain.course.domain.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class CourseOpenDataUseCase {

  private final CourseOpenApiUseCase courseOpenApiUseCase;
  private final CourseRepository courseRepository;

  public void updateCourseData() {
    int pageNo = 1;
    int numOfRows = 100;
    List<Course> courseEntityList = new ArrayList<>();

    while (true) {
      CourseResponse courseResponse = courseOpenApiUseCase.fetchCourseData(pageNo, numOfRows);

      Items items = courseResponse.response().body().items();
      if (items == null) {
        break;
      }

      List<Item> courseItemList = items.item();
      if (courseItemList.isEmpty()) {
        break;
      }

      for (CourseResponse.Item item : courseItemList) {
        Optional<Course> existingCourse = courseRepository.findByCrsIdxAndRouteIdx(
                item.crsIdx(), item.routeIdx()
        );

        if (existingCourse.isPresent()) {
          // TODO: 기존 데이터 업데이트 로직 추가
          continue;
        }

        // 새 데이터 삽입
        Course courseEntity = CourseMapper.toEntity(item);
        courseEntityList.add(courseEntity);
      }

      pageNo++;
    }

    if (!courseEntityList.isEmpty()) {
      courseRepository.saveAll(courseEntityList);
    }
  }
}
