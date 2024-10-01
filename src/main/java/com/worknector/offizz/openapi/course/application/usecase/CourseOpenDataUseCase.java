package com.worknector.offizz.openapi.course.application.usecase;

import com.worknector.offizz.domain.vacation.nature.domain.entity.Course;
import com.worknector.offizz.domain.vacation.nature.domain.repository.CourseRepository;
import com.worknector.offizz.openapi.course.application.dto.CourseResponse;
import com.worknector.offizz.openapi.course.application.dto.CourseResponse.Item;
import com.worknector.offizz.openapi.course.application.dto.CourseResponse.Items;
import com.worknector.offizz.openapi.course.application.mapper.CourseMapper;
import com.worknector.offizz.openapi.utils.CourseGpxParser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CourseOpenDataUseCase {

  private final CourseOpenApiUseCase courseOpenApiUseCase;
  private final CourseRepository courseRepository;
  private final CourseGpxParser courseGpxParser;

  public void updateCourseData() throws Exception {
    int pageNo = 1;
    int numOfRows = 100;
    List<Course> courseEntityList = new ArrayList<>();
    boolean continueFlag = true;

    while (continueFlag) {
      CourseResponse courseResponse = courseOpenApiUseCase.fetchCourseData(pageNo, numOfRows);

      Items items = courseResponse.response().body().items();
      int totalCount = courseResponse.response().body().totalCount();

      if (items == null) {
        break;
      }

      List<Item> courseItemList = items.item();
      if (courseItemList.isEmpty()) {
        break;
      }

      for (CourseResponse.Item item : courseItemList) {
        List<Course> existingCourse = courseRepository.findAllByCrsIdxAndCrsKorNm(
                item.crsIdx(), item.crsKorNm()
        );

        if (existingCourse.size() >= 2) {
          // TODO: 기존 데이터 업데이트 로직 추가

          for (int i = 1; i < existingCourse.size(); i++) {
            existingCourse.remove(i);
          }

          continue;
        }

        // 새 데이터 삽입
        Course courseEntity = CourseMapper.toEntity(item);
        courseEntityList.add(courseEntity);
      }

      int lastPage = (int) Math.ceil((double) totalCount / numOfRows);
      if (pageNo >= lastPage) {
        continueFlag = false;
      }

      pageNo++;
    }

    if (!courseEntityList.isEmpty()) {
      courseRepository.saveAll(courseEntityList);
      courseGpxParser.parseGpxAndUpdateAll(courseEntityList);
    }
  }
}
