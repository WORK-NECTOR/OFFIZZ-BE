package com.worknector.offizz.openapi.course.application.dto;

import java.util.List;

public record CourseResponse(Response response) {

  public record Response(Header header, Body body) {}

  public record Header(String resultCode, String resultMsg) {}

  public record Body(int totalCount, Items items, int numOfRows, int pageNo) {}

  public record Items(List<Item> item) {}

  public record Item(
          String crsLevel,
          String crsCycle,
          String crsContents,
          String createdtime,
          String travelerinfo,
          String crsTourInfo,
          String crsSummary,
          String routeIdx,
          String crsIdx,
          String crsKorNm,
          String crsDstnc,
          String crsTotlRqrmHour,
          String modifiedtime,
          String sigun,
          String brdDiv,
          String gpxpath
  ) {}
}
