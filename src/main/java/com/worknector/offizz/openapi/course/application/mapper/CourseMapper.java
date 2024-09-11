package com.worknector.offizz.openapi.course.application.mapper;

import com.worknector.offizz.openapi.course.application.dto.CourseResponse.Item;
import com.worknector.offizz.domain.vacation.nature.domain.entity.Course;

public class CourseMapper {

  private CourseMapper() {
    throw new IllegalArgumentException();
  }

  public static Course toEntity(Item item) {
    return Course.builder()
            .crsLevel(item.crsLevel())
            .crsContents(item.crsContents())
            .createdtime(item.createdtime())
            .travelerinfo(item.travelerinfo())
            .crsTourInfo(item.crsTourInfo())
            .crsSummary(item.crsSummary())
            .routeIdx(item.routeIdx())
            .crsIdx(item.crsIdx())
            .crsKorNm(item.crsKorNm())
            .crsDstnc(item.crsDstnc())
            .crsTotlRqrmHour(item.crsTotlRqrmHour())
            .modifiedtime(item.modifiedtime())
            .sigun(item.sigun())
            .gpxpath(item.gpxpath())
            .build();
  }
}
