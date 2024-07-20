package com.worknector.offizz.openapi.course.domain.entity;

import jakarta.persistence.*;
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

  @Column(columnDefinition = "TEXT")
  private String crsContents;

  private String createdtime;

  @Column(columnDefinition = "TEXT")
  private String travelerinfo;

  @Column(columnDefinition = "TEXT")
  private String crsTourInfo;

  @Column(columnDefinition = "TEXT")
  private String crsSummary;

  private String routeIdx;

  private String crsIdx;

  private String crsKorNm;

  private String crsDstnc;

  private String crsTotlRqrmHour;

  private String modifiedtime;

  private String sigun;

  private String brdDiv;

  @Column(columnDefinition = "TEXT")
  private String gpxpath;
}
