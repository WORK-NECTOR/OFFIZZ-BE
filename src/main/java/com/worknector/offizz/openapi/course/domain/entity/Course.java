package com.worknector.offizz.openapi.course.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Course {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

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
