package com.worknector.offizz.database.course.application.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class CourseResponse {

  private Response response;

  @Getter
  @NoArgsConstructor
  @AllArgsConstructor
  public static class Response {
    private Header header;
    private Body body;
  }


  @Getter
  @NoArgsConstructor
  @AllArgsConstructor
  public static class Header {
    private String resultCode;
    private String resultMsg;
  }

  @Getter
  @NoArgsConstructor
  @AllArgsConstructor
  public static class Body {
    private int totalCount;
    private Items items;
    private int numOfRows;
    private int pageNo;
  }

  @Getter
  @NoArgsConstructor
  @AllArgsConstructor
  public static class Items {
    private List<Item> item;
  }

  @Getter
  @NoArgsConstructor
  @AllArgsConstructor
  public static class Item {

    private String crsLevel;
    private String crsCycle;
    private String crsContents;
    private String createdtime;
    private String travelerinfo;
    private String crsTourInfo;
    private String crsSummary;
    private String routeIdx;
    private String crsIdx;
    private String crsKorNm;
    private String crsDstnc;
    private String crsTotlRqrmHour;
    private String modifiedtime;
    private String sigun;
    private String brdDiv;
    private String gpxpath;
  }
}
