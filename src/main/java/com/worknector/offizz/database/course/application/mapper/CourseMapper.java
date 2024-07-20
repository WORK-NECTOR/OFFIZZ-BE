package com.worknector.offizz.database.course.application.mapper;

import com.worknector.offizz.database.course.application.dto.CourseResponse.Item;
import com.worknector.offizz.database.course.domain.entity.Course;

public class CourseMapper {

  private CourseMapper() {
    throw new IllegalArgumentException();
  }

  public static Course toEntity(Item item) {
    return Course.builder()
        .crsLevel(item.getCrsLevel())
        .crsCycle(item.getCrsCycle())
        .crsContents(item.getCrsContents())
        .createdtime(item.getCreatedtime())
        .travelerinfo(item.getTravelerinfo())
        .crsTourInfo(item.getCrsTourInfo())
        .crsSummary(item.getCrsSummary())
        .routeIdx(item.getRouteIdx())
        .crsIdx(item.getCrsIdx())
        .crsKorNm(item.getCrsKorNm())
        .crsDstnc(item.getCrsDstnc())
        .crsTotlRqrmHour(item.getCrsTotlRqrmHour())
        .modifiedtime(item.getModifiedtime())
        .sigun(item.getSigun())
        .brdDiv(item.getBrdDiv())
        .gpxpath(item.getGpxpath())
        .build();
  }
}
