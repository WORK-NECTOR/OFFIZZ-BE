package com.worknector.offizz.domain.vacation.shopping.domain.entity;

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
public class Shopping {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long shoppingId;

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
