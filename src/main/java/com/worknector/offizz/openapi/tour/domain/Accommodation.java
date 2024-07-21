package com.worknector.offizz.openapi.tour.domain;

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
public class Accommodation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String addr1;

    private String cpyrhtDivCd;

    private String mapy;

    private String mlevel;

    private String modifiedtime;

    private String sigungucode;

    private String tel;

    private String title;

    private String contentid;

    private String contenttypeid;

    private String createdtime;

    private String benikia;

    private String goodstay;

    private String hanok;

    private String firstimage;

    private String firstimage2;

    private String mapx;

    private String addr2;

    private String areacode;

    private String booktour;

    private String cat1;

    private String cat2;

    private String cat3;
}
