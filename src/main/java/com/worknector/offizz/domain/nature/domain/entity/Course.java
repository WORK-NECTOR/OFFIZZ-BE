package com.worknector.offizz.domain.nature.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Course {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long courseId;

  private String crsLevel;

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

  @Column(columnDefinition = "TEXT")
  private String gpxpath;

  @ColumnDefault("0")
  private long hit;
}
