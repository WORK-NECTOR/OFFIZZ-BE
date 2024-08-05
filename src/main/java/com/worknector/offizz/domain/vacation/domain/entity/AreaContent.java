package com.worknector.offizz.domain.vacation.domain.entity;

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
public class AreaContent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long areaContentId;

    private String addr1;

    private String addr2;

    private String mapx;

    private String mapy;

    private String mlevel;

    private String modifiedtime;

    private String tel;

    private String title;

    private String contentid;

    private String contenttypeid;

    private String createdtime;

    private String firstimage;

    private String firstimage2;

    private String areacode;

    private String sigungucode;

    private String booktour;

    private String cat1;

    private String cat2;

    private String cat3;
}
