package com.worknector.offizz.domain.vacation.culture.domain.entity;

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
public class Culture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cultureId;

    private String addr1;

    private double lon;

    private double lat;

    private String modifiedtime;

    private String title;

    private String contentid;

    private String contenttypeid;

    private String createdtime;

    private String firstimage;

    private String areacode;

    private String sigungucode;
}
