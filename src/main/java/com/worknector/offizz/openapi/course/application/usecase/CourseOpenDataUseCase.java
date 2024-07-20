package com.worknector.offizz.openapi.course.application.usecase;

import com.worknector.offizz.openapi.course.application.dto.CourseResponse;
import com.worknector.offizz.openapi.course.application.dto.CourseResponse.Item;
import com.worknector.offizz.openapi.course.application.dto.CourseResponse.Items;
import com.worknector.offizz.openapi.course.application.mapper.CourseMapper;
import com.worknector.offizz.openapi.course.domain.entity.Course;
import com.worknector.offizz.openapi.course.domain.repository.CourseRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

      Items items = courseResponse.getResponse().getBody().getItems();
      if (items != null) {
        List<Item> courseItemList = courseResponse.getResponse().getBody().getItems().getItem();
        if (courseItemList.isEmpty()) {
          break;
        }

        for (CourseResponse.Item item : courseItemList) {
          Optional<Course> existingCourse = courseRepository.findByCrsIdxAndRouteIdx(
              item.getCrsIdx(), item.getRouteIdx()
          );

          if (existingCourse.isPresent()) {
//            Course existingEntity = existingCourse.get();
//            Course updatedEntity = CourseMapper.toEntity(item);
            // 기존 데이터 업데이트
          } else {
            // 새 데이터 삽입
            Course courseEntity = CourseMapper.toEntity(item);
            courseEntityList.add(courseEntity);
          }
        }
      } else {
        break;
      }

      pageNo++;
    }

    if (!courseEntityList.isEmpty()) {
      courseRepository.saveAll(courseEntityList);
    }
  }
}
